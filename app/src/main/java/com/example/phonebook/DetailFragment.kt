package com.example.phonebook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.example.phonebook.databinding.FragmentDetailBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var imageUriData: String = ""
    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data

        if (resultCode == Activity.RESULT_OK) {
            val fileURI = data?.data!!
            imageUriData = fileURI.toString()
            binding.userAvatarImage.setImageURI(fileURI)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set onclick listener on image view.
        binding.userAvatarImage.setOnClickListener {
            ImagePicker
                .with(this)
                .crop()
                .maxResultSize(50, 50)
                .createIntent {
                    startForProfileImageResult.launch(it)
                }
        }

        // set onclick listener on button
        binding.submitButton.setOnClickListener {
            navigateToCardView()
        }
    }


    private fun navigateToCardView() {
        val imageUri = imageUriData
        val name = binding.nameInput.editText?.text.toString()
        val address = binding.addressInput.editText?.text.toString()
        val phone = binding.phoneInput.editText?.text.toString()
        if (phone == "" && address == "" && name == "" && imageUri.isEmpty()) {
            Snackbar.make(binding.root, "Please fill all the fields", Snackbar.LENGTH_LONG).show()
        } else {
            val action = DetailFragmentDirections.actionDetailFragmentToCardFragment(
                name,
                phone,
                address,
                imageUri.toUri()
            )
            findNavController().navigate(action)
        }
    }
}