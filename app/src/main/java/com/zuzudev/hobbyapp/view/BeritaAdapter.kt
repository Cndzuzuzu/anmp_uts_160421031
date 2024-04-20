package com.zuzudev.hobbyapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zuzudev.hobbyapp.databinding.BeritaListItemBinding
import com.zuzudev.hobbyapp.model.Berita

class BeritaAdapter(val beritaList:ArrayList<Berita>): RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>()
{
    class BeritaViewHolder(var binding: BeritaListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int):BeritaViewHolder {
        val binding = BeritaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.binding.txtJudul.text = beritaList[position].judulBerita
        holder.binding.txtPembuat.text = "by " + beritaList[position].idPembuat
        holder.binding.txtTanggal.text = "on " + beritaList[position].tanggal
        holder.binding.txtDeskripsi.text = beritaList[position].deskripsi

        holder.binding.btnRead.setOnClickListener {
            val beritaId = beritaList[position].idBerita
            val action = HomeFragmentDirections.actionDetailBerita(beritaId!!)
//            val action = HomeFragmentDirections.actionDetailBerita()
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }

        picasso.build().load(beritaList[position].gambar)
            .into(holder.binding.imageView, object: Callback {
                override fun onSuccess() {
                    holder.binding.progressBar.visibility = View.INVISIBLE
                    holder.binding.imageView.visibility = View.VISIBLE
                }
                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })
//        var imageView = holder.binding.imageView2
//        var progressBar = holder.binding.progressBar3
//        imageView.loadImage(studentList[position].photoUrl, progressBar)

    }
    override fun getItemCount(): Int {
        return beritaList.size
    }
    fun updateBeritaList(newBeritaList: ArrayList<Berita>) {
        beritaList.clear()
        beritaList.addAll(newBeritaList)
        notifyDataSetChanged()
    }

}