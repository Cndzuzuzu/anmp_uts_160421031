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
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.viewmodel.LoginViewModel
import com.zuzudev.hobbyapp.viewmodel.RegisterViewModel


class RegisterFragment : Fragment(), RegisterClickListener {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = Users("","","", "")
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.regisListener = this

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

    override fun onRegisterClick(v: View) {
        if ((binding.user!!.password == binding.txtConfirm.text.toString()) &&
            (binding.user!!.username != "" && binding.user!!.password != "" && binding.txtConfirm.text.toString() != "" &&
                    binding.user!!.namaDepan != "" && binding.user!!.namaBelakang != "" && binding.user!!.email != "")
        ) {
            viewModel.register(binding.user!!)
//            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment2()
//            Navigation.findNavController(v).navigate(action)


        } else if (binding.user!!.username == "" || binding.user!!.password == "" || binding.txtConfirm.text.toString() == "" ||
            binding.user!!.namaDepan == "" || binding.user!!.namaBelakang == "" || binding.user!!.email == "") {
            Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "Pasword salah", Toast.LENGTH_SHORT).show()
        }
    }
}