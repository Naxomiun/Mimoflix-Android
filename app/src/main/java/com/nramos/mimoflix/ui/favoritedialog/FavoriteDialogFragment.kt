package com.nramos.mimoflix.ui.favoritedialog


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.DialogAddedToFavoritesBinding

private const val MOVIE_TITLE_TAG = "movie_title_tag"

class FavoriteDialogFragment : DialogFragment() {

    private lateinit var movieTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieTitle = it.getString(MOVIE_TITLE_TAG) ?: ""
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DialogAddedToFavoritesBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.tvStringMessageDialog.text = getString(R.string.detail_added_to_favorites, movieTitle)
            if (dialog != null && dialog?.window != null) {
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            }
        }.root
    }

    companion object {
        @JvmStatic
        fun newInstance(movieTitle : String) = FavoriteDialogFragment().apply {
            arguments = Bundle().apply {
                this.putString(MOVIE_TITLE_TAG, movieTitle)
            }
        }
    }

}