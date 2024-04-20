package com.zuzudev.hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zuzudev.hobbyapp.R
import com.zuzudev.hobbyapp.databinding.FragmentLoginBinding
import com.zuzudev.hobbyapp.databinding.FragmentRegisterBinding
import com.zuzudev.hobbyapp.viewmodel.LoginViewModel
import com.zuzudev.hobbyapp.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var password = binding.txtPassword.text.toString()
            var confirm = binding.txtConfirm.text.toString()
            var email = binding.txtEmail.text.toString()
            var namaDepan = binding.txtNamaDepan.text.toString()
            var namaBelakang = binding.txtNamaBelakang.text.toString()

            if ((password == confirm) &&
                (username != "" && password != "" && confirm != "" && namaDepan != "" && namaBelakang != "" && email != "")
            ) {
                viewModel.fetch(username, password, email, namaDepan, namaBelakang)


            } else if (username == "" || password == "" || confirm == "" || namaDepan == "" || namaBelakang == "" || email == "") {
                Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "Pasword salah", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener{
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment2()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel.registerLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "Berhasil Register", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "gagal register", Toast.LENGTH_SHORT).show()
            }




        })
    }
}