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
    private val args: CardFragmentArgs by navArgs<CardFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCardBinding.inflate(inflater, container, false)
        binding.circleImageView.setImageURI(args.imageUri)
        binding.nameDisplay.text = args.name
        binding.addressDisplay.text = args.address
        binding.phoenDisplay.text = args.phone
        return binding.root
    }
}