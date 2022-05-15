package com.interactivestandard.test.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.interactivestandard.test.R
import com.interactivestandard.test.presentation.viewmodel.ErrorViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class ErrorFragment : Fragment(R.layout.fragment_error), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()

    private val viewModel: ErrorViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<AppCompatButton>(R.id.apply).setOnClickListener {
            viewModel.okay()
        }
    }
}