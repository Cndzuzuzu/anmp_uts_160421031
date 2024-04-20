package com.zuzudev.hobbyapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zuzudev.hobbyapp.R
import com.zuzudev.hobbyapp.databinding.FragmentDetailBeritaBinding
import com.zuzudev.hobbyapp.model.Page
import com.zuzudev.hobbyapp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class DetailBeritaFragment : Fragment() {
    private lateinit var binding:FragmentDetailBeritaBinding
    private lateinit var viewModel:DetailViewModel
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

        var id = 0
        if(arguments != null){
            id = DetailBeritaFragmentArgs.fromBundle(requireArguments()).idBerita
        }
        binding.btnPrev.visibility = View.GONE
//        binding.btnNext.visibility = View.VISIBLE
        viewModel.fetch(id)
//        binding.txtJudul2.setText(id.toString())
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            binding.txtJudul2.setText(it.judulBerita)
            binding.txtPembuat2.setText("by " + it.idPembuat)
            binding.txtTanggal2.setText("on: " + it.tanggal)
            binding.txtDeskripsi2.setText(it.deskripsi)


            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }

            picasso.build().load(it.gambar)
                .into(binding.imageView2, object: Callback {
                    override fun onSuccess() {
                        binding.progressBar2.visibility = View.INVISIBLE
                        binding.imageView2.visibility = View.VISIBLE
                    }
                    override fun onError(e: Exception?) {
                        Log.e("picasso_error", e.toString())
                    }
                })
        })
        var currPagee = 1
        fun each(it:List<Page>, idx:Int){
            it.forEach {
                if (it.idPage == idx){
                    currPagee = idx
                    binding.txtContent.setText(it.kontenBerita)
                }
            }
        }
        viewModel.listPage.observe(viewLifecycleOwner, Observer{
            var page = it
            each(it, 1)
            binding.btnNext.setOnClickListener {
                if (currPagee == 1){
                    each(page,currPagee+1)
                    binding.btnPrev.visibility = View.VISIBLE
                    binding.btnNext.visibility = View.VISIBLE
                }
                else if (currPagee == 2){
                    each(page,currPagee+1)
                    binding.btnNext.visibility = View.GONE
                    binding.btnPrev.visibility = View.VISIBLE
                }
            }
            binding.btnPrev.setOnClickListener {
                if (currPagee == 3) {
                    each(page, currPagee-1)
                    binding.btnNext.visibility = View.VISIBLE
                    binding.btnPrev.visibility = View.VISIBLE
                }
                else if (currPagee == 2) {
                    each(page, currPagee-1)
                    binding.btnPrev.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                }
            }
        })




    }
}