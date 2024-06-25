package com.zuzudev.hobbyapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.zuzudev.hobbyapp.viewmodel.LoginViewModel
import com.zuzudev.hobbyapp.viewmodel.ProfileViewModel


class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var password = binding.txtPassword.text.toString()
            if (username == "" || password == ""){
                Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.login(username, password)
            }
            //                val action = LoginFragmentDirections.ac()
//                Navigation.findNavController(it).navigate(action)
        }

        viewModel.passErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                Toast.makeText(requireContext(), "gagal login", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginStatLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "sukses login", Toast.LENGTH_SHORT).show()
                var loginInfo = "com.zuzudev.yarntopia"
                var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
                    Context.MODE_PRIVATE )
                var editor: SharedPreferences.Editor = shared.edit()
                editor.putString("username", binding.txtUsername.text.toString())
                editor.apply()
                binding.txtUsername.setText("")
                binding.txtPassword.setText("")

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                viewModel.loginStatLD.value = false

            }
//            else {
//                Toast.makeText(requireContext(), "gagal login", Toast.LENGTH_SHORT).show()
//            }
        })

//        viewModel.userLD.observe(viewLifecycleOwner, Observer{
//
//        })

        binding.btnRegister.setOnClickListener {
//            val beritaId = beritaList[position].idBerita
            val action = LoginFragmentDirections.actionRegisterFragment()
//            val action = HomeFragmentDirections.actionDetailBerita()
            Navigation.findNavController(it).navigate(action)
        }


    }

}