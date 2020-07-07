package com.nramos.mimoflix.ui.search

import android.Manifest
import android.app.ActivityOptions
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Pair
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ActivitySearchBinding
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.utils.StyleManager
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import java.util.*

private const val RC_SPEECH = 239

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel : SearchActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents() {
        with(viewModel) {
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let { pair ->
                    val intent = Intent(this@SearchActivity,  MovieDetailActivity::class.java)
                    intent.putExtra("movie", pair.first.id)
                    val options = ActivityOptions.makeSceneTransitionAnimation(this@SearchActivity, Pair(pair.second, pair.second.transitionName))
                    startActivity(intent, options.toBundle())
                }
            }
            observe(backActionEvent) {
                it.getContentIfNotHandled()?.let { goBack ->
                    if(goBack)
                        onBackPressed()
                }
            }
            observe(speechEvent) {
                it.getContentIfNotHandled()?.let { speech ->
                    if(speech)
                        requestSpeechPermission()
                }
            }
        }
    }

    private fun setStyle() {
        when(StyleManager(context = this).getMode())  {
            true -> setTheme(R.style.SearchActivity_Dark)
            false -> setTheme(R.style.SearchActivity_Light)
        }
    }

    private fun startRecordingSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")
        try {
            startActivityForResult(intent, RC_SPEECH)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "Sorry your device not supported", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestSpeechPermission(){
        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            startRecordingSpeech()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), RC_SPEECH)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == RC_SPEECH){
            val denied = grantResults.indices.filter { grantResults[it] != PackageManager.PERMISSION_GRANTED }
            if (denied.isEmpty()) {
                startRecordingSpeech()
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            RC_SPEECH -> {
                if(resultCode == RESULT_OK) {
                    val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    result?.let {
                        viewModel.searchMovies(it[0])
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}