package com.zuzudev.hobbyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuzudev.hobbyapp.R
import com.zuzudev.hobbyapp.databinding.FragmentHomeBinding
import com.zuzudev.hobbyapp.viewmodel.BeritaListViewModel


class HomeFragment : Fragment() {
    private lateinit var viewModel:BeritaListViewModel
    private val beritaListAdapter = BeritaAdapter(arrayListOf())
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeritaListViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = beritaListAdapter
        observeViewModel()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            beritaListAdapter.updateBeritaList(it)
        })


        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })



        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })

    }

}