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
import com.zuzudev.hobbyapp.model.Berita
import com.zuzudev.hobbyapp.model.Page
import com.zuzudev.hobbyapp.model.Users
import com.zuzudev.hobbyapp.viewmodel.AddBeritaViewModel
import com.zuzudev.hobbyapp.viewmodel.DetailViewModel
import java.text.SimpleDateFormat
import java.util.Date


class TambahBeritaFragment : Fragment(), RegisterClickListener {
    private lateinit var binding: FragmentTambahBeritaBinding
    private lateinit var viewModel: AddBeritaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTambahBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddBeritaViewModel::class.java)
        binding.user = Users("","","","")
        binding.berita = Berita("","","","","")
        binding.page = Page(0,"")

        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        var username = shared.getString("username","").toString()

        viewModel.fetchUser(username)
        observeModel()

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)
        binding.berita!!.tanggal = current

        binding.listener = this


    }
    fun observeModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.user = it
            binding.berita!!.idPembuat = it.username
        })
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            binding.berita = it
//            binding.page!!.idBerita = it.idBerita

        })
        viewModel.pageLD.observe(viewLifecycleOwner, Observer {
            binding.page = it

        })

    }
    override fun onRegisterClick(v: View) {
        if(binding.berita!!.judulBerita != "" && binding.berita!!.deskripsi != "" && binding.berita!!.gambar != "" && binding.page!!.kontenBerita != "") {
            viewModel.addBerita(binding.berita!!)

            Toast.makeText(v.context, "Berita Added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
        else{
            Toast.makeText(v.context, "Pastikan semua field ssudah terisi", Toast.LENGTH_SHORT).show()
        }

    }

}