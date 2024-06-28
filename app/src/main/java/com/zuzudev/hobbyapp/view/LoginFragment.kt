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
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.viewmodel.LoginViewModel
import com.zuzudev.hobbyapp.viewmodel.ProfileViewModel


class LoginFragment : Fragment(), LoginClickListener {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

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
        binding.user = Users("","","","")
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginListener = this

        observeModel()

        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }


    }
fun observeModel() {
    viewModel.userLD.observe(viewLifecycleOwner, Observer {
        if(it != null){
            binding.user = it
            viewModel.loginStatLD.value = true
            viewModel.passErrorLD.value = false
        }
        else{
            viewModel.passErrorLD.value = true
            viewModel.loginStatLD.value = false
        }

    })
    viewModel.passErrorLD.observe(viewLifecycleOwner, Observer {
        if (it == true) {
            Toast.makeText(requireContext(), "username atau password salah", Toast.LENGTH_SHORT).show()
            viewModel.loginStatLD.value = false
        }
    })
    viewModel.loginStatLD.observe(viewLifecycleOwner, Observer {
        if (it == true) {
            Toast.makeText(requireContext(), "sukses login", Toast.LENGTH_SHORT).show()
            var loginInfo = "com.zuzudev.yarntopia"
            var shared: SharedPreferences = requireContext().getSharedPreferences(
                loginInfo,
                Context.MODE_PRIVATE
            )
            var editor: SharedPreferences.Editor = shared.edit()
            editor.putString("username", binding.user!!.username)
            editor.apply()
            binding.txtUsername.setText("")
            binding.txtPassword.setText("")

            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            viewModel.loginStatLD.value = false
        }
    })
}

    override fun onLoginClick(v: View) {
        if (binding.user!!.username == "" || binding.user!!.password == ""){
            Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_SHORT).show()
        }
        viewModel.login(binding.user!!.username, binding.user!!.password)
    }

}