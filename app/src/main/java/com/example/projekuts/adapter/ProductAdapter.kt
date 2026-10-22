package com.example.projekuts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projekuts.databinding.ItemProductBinding
import com.example.projekuts.model.Product

class ProductAdapter(
    private val items: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    class VH(val b: ItemProductBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(ItemProductBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(h: VH, pos: Int) {
        val p = items[pos]
        h.b.tvTitle.text = p.title
        h.b.tvPrice.text = "$ ${p.price}"
        h.b.tvStock.text = "Stok: ${p.stock}"
        h.b.tvRating.text = "★ ${p.rating}"
        Glide.with(h.b.root).load(p.thumbnail).into(h.b.ivThumb)
        h.b.root.setOnClickListener { onClick(p) }
    }
}