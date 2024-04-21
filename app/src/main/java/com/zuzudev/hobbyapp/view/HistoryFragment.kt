package com.zuzudev.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuzudev.hobbyapp.R
import com.zuzudev.hobbyapp.databinding.FragmentHistoryBinding
import com.zuzudev.hobbyapp.databinding.FragmentHomeBinding
import com.zuzudev.hobbyapp.viewmodel.BeritaListViewModel
import com.zuzudev.hobbyapp.viewmodel.HistoryViewModel


class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel
    private val beritaListAdapter = BeritaAdapter(arrayListOf())
    private lateinit var binding: FragmentHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        var username = shared.getString("username","").toString()

        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        viewModel.refresh(username)
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = beritaListAdapter
        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh(username)
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