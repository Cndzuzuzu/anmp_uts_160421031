package com.zuzudev.hobbyapp.view

import android.content.Context
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
import com.zuzudev.hobbyapp.databinding.FragmentTambahBeritaBinding
import com.zuzudev.hobbyapp.databinding.FragmentTambahPageBinding
import com.zuzudev.hobbyapp.model.Berita
import com.zuzudev.hobbyapp.model.Page
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.viewmodel.AddBeritaViewModel


class TambahPageFragment : Fragment(), RegisterClickListener {
    private lateinit var binding: FragmentTambahPageBinding
    private lateinit var viewModel: AddBeritaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTambahPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddBeritaViewModel::class.java)
//        binding.user = Users("","","","")
//        binding.berita = Berita("","","","","")
        binding.page = Page(0,"")

        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        var username = shared.getString("username","").toString()

        var id = 0
        if(arguments != null){
            id = DetailBeritaFragmentArgs.fromBundle(requireArguments()).idBerita
        }
        viewModel.fetch(id)
        binding.listener = this
        observeModel()




    }
    fun observeModel(){
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            binding.berita = it
            binding.page!!.idBerita = it.idBerita

        })
        viewModel.pageLD.observe(viewLifecycleOwner, Observer {
            binding.page = it

        })
    }
    override fun onRegisterClick(v: View) {
        if(binding.page!!.kontenBerita != null){
            viewModel.addPage(binding.page!!)
            Toast.makeText(v.context,"Page berhasil disimpan", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }
}