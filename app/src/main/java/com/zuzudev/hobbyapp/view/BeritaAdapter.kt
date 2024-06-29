package com.zuzudev.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zuzudev.hobbyapp.databinding.BeritaListItemBinding
import com.zuzudev.hobbyapp.model.Berita

class BeritaAdapter(val beritaList:ArrayList<Berita>): RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>(), ReadClickListener
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
        holder.binding.berita = beritaList[position]
        holder.binding.listener = this
        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = holder.itemView.getContext().getSharedPreferences(
            loginInfo,
            Context.MODE_PRIVATE
        )
        var editor: SharedPreferences.Editor = shared.edit()
        editor.putString("gambar", holder.binding.berita!!.gambar)
        editor.apply()

//        holder.binding.txtJudul.text = beritaList[position].judulBerita
//        holder.binding.txtPembuat.text = "by " + beritaList[position].idPembuat
//        holder.binding.txtTanggal.text = "on " + beritaList[position].tanggal
//        holder.binding.txtDeskripsi.text = beritaList[position].deskripsi

//        holder.binding.btnRead.setOnClickListener {
//            val beritaId = beritaList[position].idBerita
//            val action = HomeFragmentDirections.actionDetailBerita(beritaId!!)
////            val action = HomeFragmentDirections.actionDetailBerita()
//            Navigation.findNavController(it).navigate(action)
//
//        }
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//
//        picasso.build().load(beritaList[position].gambar)
//            .into(holder.binding.imageView, object: Callback {
//                override fun onSuccess() {
//                    holder.binding.progressBar.visibility = View.INVISIBLE
//                    holder.binding.imageView.visibility = View.VISIBLE
//                }
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//            })
//        var imageView = holder.binding.imageView2
//        var progressBar = holder.binding.progressBar3
//        imageView.loadImage(studentList[position].photoUrl, progressBar)

    }
    override fun getItemCount(): Int {
        return beritaList.size
    }
    fun updateBeritaList(newBeritaList: List<Berita>) {
        beritaList.clear()
        beritaList.addAll(newBeritaList)
        notifyDataSetChanged()
    }

    override fun onReadClick(v: View) {


        val idberita = v.tag.toString().toInt()
//        Toast.makeText(v.context,idberita.toString(), Toast.LENGTH_SHORT).show()
        val action = HomeFragmentDirections.actionDetailBerita(idberita)
        Navigation.findNavController(v).navigate(action)

    }

}