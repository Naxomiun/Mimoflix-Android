package com.nramos.mimoflix.ui.tabprofile

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.FragmentProfileBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.ui.actordetail.ActorDetailActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.settings.SettingsActivity
import com.nramos.mimoflix.ui.trailer.TrailerActivity
import com.nramos.mimoflix.utils.Constants.Companion.RESULT_LOGOUT
import com.nramos.mimoflix.utils.StyleManager
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

private const val RC_SIGN_IN = 492
private const val RC_SETTINGS = 592

class ProfileFragment : Fragment() {

    private var isLoggedIn : GoogleSignInAccount? = null
    private lateinit var mGoogleSignInClient : GoogleSignInClient
    private val viewModel : ProfileFragmentViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentProfileBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
            setEvents()
            mGoogleSignInClient = GoogleSignIn.getClient(
                context as Activity,
                GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .build()
            )
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isLoggedIn = GoogleSignIn.getLastSignedInAccount(context as Activity)

        if(isLoggedIn != null) {
            updateUI(isLoggedIn)
        }
    }

    private fun setEvents() {
        with(viewModel) {
            observe(loginActionEvent) {
                it.getContentIfNotHandled()?.let {event ->
                    if (event) {
                        login()
                    }
                }
            }
            observe(settingsActionEvent) {
                it.getContentIfNotHandled()?.let { event ->
                    if (event) {
                        startActivityForResult(Intent(context, SettingsActivity::class.java), RC_SETTINGS)
                    }
                }
            }
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let { movieId ->
                    context?.goTo<MovieDetailActivity>(Pair("movie", movieId))
                }
            }
            observe(castActionEvent) {
                it.getContentIfNotHandled()?.let { castId ->
                    context?.goTo<ActorDetailActivity>(Pair("actorId", castId))
                }
            }
        }
    }

    private fun login() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleLogin(completedTask: Task<GoogleSignInAccount>) {
        runCatching {
            completedTask.getResult(ApiException::class.java)
        }.onSuccess {
            updateUI(it)
        }.onFailure {
            Toast.makeText(context, resources.getString(R.string.profile_gservices_error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account : GoogleSignInAccount?) {
        viewModel.setData(account)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("RESULT", "ACTIVITY RESULT")
        when(requestCode) {
            RC_SIGN_IN -> {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleLogin(task)
            }
            RC_SETTINGS -> {
                Log.e("RESULT", "SETTINGS")
                when(resultCode) {
                    RESULT_OK -> {
                        StyleManager(requireContext()).recreate(false)
                        activity?.recreate()
                    }
                    RESULT_LOGOUT -> {
                        viewModel.logOut()
                    }
                }
            }
        }
    }
}