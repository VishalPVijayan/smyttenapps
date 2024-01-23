package com.vishalpvijayan.smyttens.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.databinding.FragmentMessageBinding
import com.vishalpvijayan.smyttens.viewModels.MessageViewModel

class MessageFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMessageBinding
    private lateinit var viewModel: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_message, container, false
        )

        viewModel = MessageViewModel()

        binding.viewModel = viewModel

        binding.buttonOk.setOnClickListener {
            dismiss()
        }


        isCancelable = false
        return binding.root
    }
}


