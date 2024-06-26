package com.zuzudev.hobbyapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zuzudev.hobbyapp.R
import com.zuzudev.hobbyapp.databinding.FragmentProfileBinding
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.viewmodel.DetailViewModel
import com.zuzudev.hobbyapp.viewmodel.ProfileViewModel



class ProfileFragment : Fragment() ,RegisterClickListener,UpdateClickListener,BackClickListener{
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private var username:String = ""
    private var user:Users = Users("","","","")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = Users("","","","")
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.regisListener=this
        binding.updateListener=this
        binding.logoutListener=this
        viewModel.updateDataLD.value = false
        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        username = shared.getString("username","").toString()
        Toast.makeText(requireContext(), username, Toast.LENGTH_SHORT).show()
        viewModel.fetch(username)
        observeModel()
//        viewModel.updateDataLD.value = false







    }
    fun UpdateUI(userLog: Users){
        binding.txtNamaDepan.setText(userLog.namaDepan)
        binding.txtNamaBelakang.setText(userLog.namaBelakang)
        binding.txtEmail.setText(userLog.email)
        binding.txtUsername.setText("Halo!! " + userLog.username)
    }

    fun observeModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                binding.user = it
                viewModel.oldPass.value = it.password
                UpdateUI(it)
            }
        })
//
        viewModel.updatePassLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "sukses update password", Toast.LENGTH_SHORT).show()
            }
        })
//
        viewModel.updateDataLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "sukses update data pengguna", Toast.LENGTH_SHORT).show()
            }
            //            else {
//                Toast.makeText(requireContext(), "gagal update data pengguna", Toast.LENGTH_SHORT).show()
//            }
        })
    }
    override fun onRegisterClick(v: View) {
        viewModel.updateUser(binding.user!!)
    }

    override fun onUpdateClick(v: View) {
//        val newPass = binding.txtNewPassword.text.toString()
        val oldPass = binding.txtOldPassword.text.toString()
        val confirm = binding.txtConfirm.text.toString()

        if (binding.user!!.password == confirm && oldPass == viewModel.oldPass.value)
        {
            viewModel.updatePassword(binding.user!!.username, binding.user!!.password)
            binding.txtNewPassword.setText("")
            binding.txtOldPassword.setText("")
            binding.txtConfirm.setText("")
//            Toast.makeText(requireContext(), "sukses update password", Toast.LENGTH_SHORT).show()
//                    viewModel.fetch(username)

        }
        else if (confirm == "" || binding.user!!.password == "" ||oldPass == "")
        {
            Toast.makeText(requireContext(), "Isi semua field password", Toast.LENGTH_SHORT)
                .show()
        }
        else
        {
            Toast.makeText(requireContext(), "Passwords do not match" + viewModel.oldPass.value, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onBackClick(v: View) {
        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences =
            requireContext().getSharedPreferences(loginInfo, Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()
        editor.clear()
        editor.apply()

//            WelcomeActivity.first = false
//            val intent = Intent(it.context, WelcomeActivity::class.java)
//            it.context.startActivity(intent)
        requireActivity().finish()
    }


}