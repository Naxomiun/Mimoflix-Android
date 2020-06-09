package com.nramos.mimoflix.ui.trailer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.databinding.ActivityTrailerBinding
import com.nramos.mimoflix.extension.setFullScreenActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class TrailerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrailerBinding
    private val viewModel : TrailerActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenActivity()
        binding = ActivityTrailerBinding.inflate(layoutInflater).also {
            viewModel.trailerId = intent.getStringExtra("trailerId") ?: ""
            it.lifecycleOwner = this
        }
        setContentView(binding.root)
        setupPlayer(viewModel.trailerId)
    }

    private fun setupPlayer(trailerId : String) {
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(trailerId, 0f)
            }
        })
    }

}
