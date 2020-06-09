package com.nramos.mimoflix.ui.favoritedialog


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.nramos.mimoflix.databinding.DialogAddedToFavoritesBinding

class FavoriteDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DialogAddedToFavoritesBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            if (dialog != null && dialog?.window != null) {
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            }
        }.root
    }

}