package com.interactivestandard.test.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.interactivestandard.test.R
import com.interactivestandard.test.presentation.viewmodel.MainViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class MainFragment : Fragment(R.layout.fragment_main), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()

    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countEditText = requireView().findViewById<AppCompatEditText>(R.id.count)
        requireView().findViewById<AppCompatButton>(R.id.apply).setOnClickListener {
            viewModel.applyCount(countEditText.text)
        }
    }
}