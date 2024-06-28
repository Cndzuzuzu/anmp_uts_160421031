package com.zuzudev.hobbyapp.view

import android.content.Context
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
import com.zuzudev.hobbyapp.databinding.FragmentDetailBeritaBinding
import com.zuzudev.hobbyapp.model.Berita
import com.zuzudev.hobbyapp.model.Page
import com.zuzudev.hobbyapp.viewmodel.DetailViewModel
import com.zuzudev.hobbyapp.viewmodel.HistoryViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class DetailBeritaFragment : Fragment(), RegisterClickListener {
    private lateinit var binding:FragmentDetailBeritaBinding
    private lateinit var viewModel:DetailViewModel
    private lateinit var histViewModel:HistoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        var id = DetailBeritaFragmentArgs.fromBundle(requireArguments()).idBerita
        viewModel.fetch(id)
        viewModel.detailBerita(id)

        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        var username = shared.getString("username","").toString()

        binding.berita = Berita("","","","","")
        binding.kontenpage = Page(id, "")


        binding.listener = this
//        viewModel.fetchPage(binding.kontenpage!!.idPage)

        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            binding.berita = it
//            binding.kontenpage!!.idBerita = it.idBerita

            if(binding.berita!!.idPembuat == username){
                binding.btnAddPage.visibility = View.VISIBLE
//                binding.txtAddPage.visibility = View.VISIBLE
            }
            else{
                binding.btnAddPage.visibility = View.INVISIBLE
//                binding.txtAddPage.visibility = View.INVISIBLE
            }
        })
        viewModel.pageLD.observe(viewLifecycleOwner, Observer {
            binding.kontenpage = it
            viewModel.fetchPage(binding.kontenpage!!.idPage)
        })

//        histViewModel.addHistLD.observe(viewLifecycleOwner, Observer {
//            if (it==true){
//                Toast.makeText(requireContext(), "Add to History", Toast.LENGTH_SHORT).show()
//            }
//        })



        var currentPageIdx = 0
        fun updateTextView(listPage:List<Page>) {
            binding.kontenpage!!.kontenBerita = listPage[currentPageIdx].kontenBerita
            binding.txtPage.text = listPage[currentPageIdx].idPage.toString() + "/" + listPage.size.toString()
        }
        fun updateButtonVisibility(listPage:List<Page>) {
            binding.btnPrev.isEnabled = currentPageIdx > 0
            binding.btnNext.isEnabled = currentPageIdx < listPage.size - 1
        }

        viewModel.listPage.observe(viewLifecycleOwner, Observer{
            var page = it
//            binding.kontenpage!!.idPage = page[currentPageIdx+1].idPage
            if(page.size >0) {
                updateTextView(page)
                updateButtonVisibility(page)

                binding.btnNext.setOnClickListener {
                    currentPageIdx = (currentPageIdx + 1)
                    updateTextView(page)
                    updateButtonVisibility(page)
                    viewModel.fetch(id)
                    viewModel.detailBerita(id)
                    viewModel.fetchPage(page[currentPageIdx].idPage)
//                    viewModel.fetchPage(currentPageIdx)
                }

                binding.btnPrev.setOnClickListener {
                    currentPageIdx = (currentPageIdx - 1)
                    updateTextView(page)
                    updateButtonVisibility(page)
                    viewModel.fetch(id)
                    viewModel.detailBerita(id)
//                    viewModel.fetchPage(currentPageIdx)
                }
            }
            else{
                binding.kontenpage!!.kontenBerita = "Belum ada isi"
            }
        })




    }

    override fun onRegisterClick(v: View) {
        val idBerita = v.tag.toString().toInt()
        val action = DetailBeritaFragmentDirections.actionTambahPage(idBerita)
        Navigation.findNavController(v).navigate(action)
//        binding.addpage!!.idBerita = idBerita
//        viewModel.addPage(binding.addpage!!)
//        Toast.makeText(v.context,"Page berhasil disimpan", Toast.LENGTH_SHORT).show()
    }
}