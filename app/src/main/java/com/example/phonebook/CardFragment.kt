package com.example.phonebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.phonebook.databinding.FragmentCardBinding

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val args: CardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.imageUri.toString().isNotEmpty()) binding.circleImageView.setImageURI(args.imageUri)
        binding.nameDisplay.text = args.name
        binding.addressDisplay.text = args.address
        binding.phoenDisplay.text = args.phone
    }
}