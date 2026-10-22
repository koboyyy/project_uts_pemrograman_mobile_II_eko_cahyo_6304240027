package com.example.projekuts

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekuts.databinding.FragmentProductListBinding
import retrofit2.*
import com.example.projekuts.model.ProductResponse
import com.example.projekuts.adapter.ProductAdapter
import com.example.projekuts.retrofit.RetrofitClient

class ProductListFragment : Fragment() {
    private var _b: FragmentProductListBinding? = null
    private val b get() = _b!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentProductListBinding.inflate(i, c, false)
        return b.root
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        b.rv.layoutManager = LinearLayoutManager(requireContext())
        b.progress.visibility = View.VISIBLE

        RetrofitClient.api.getProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, r: Response<ProductResponse>) {
                _b ?: return
                b.progress.visibility = View.GONE
                val list = r.body()?.products ?: emptyList()
                b.rv.adapter = ProductAdapter(list) { p ->
                    startActivity(Intent(requireContext(), DetailActivity::class.java)
                        .putExtra("product", p))
                }
            }
            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _b ?: return
                b.progress.visibility = View.GONE
                Toast.makeText(context, "Gagal memuat: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}