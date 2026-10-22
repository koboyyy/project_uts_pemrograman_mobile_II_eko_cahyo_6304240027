import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projekuts.databinding.ActivityDetailBinding
import com.example.projekuts.model.Product
import com.example.projekuts.model.Review

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        setSupportActionBar(b.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        @Suppress("DEPRECATION")
        val p = intent.getSerializableExtra("product") as Product
        supportActionBar?.title = p.title

        b.rvImages.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        b.rvImages.adapter = ImageAdapter(p.images)

        b.tvTitle.text = p.title
        b.tvCategory.text = "Kategori: ${p.category}"
        b.tvPrice.text = "Harga: $ ${p.price}"
        b.tvStock.text = "Stok: ${p.stock} (${p.availabilityStatus})"
        b.tvRating.text = "Rating: ★ ${p.rating}"
        b.tvTags.text = "Tags: ${p.tags.joinToString(", ")}"
        b.tvDim.text = "Dimensi (L×T×D): ${p.dimensions.width} × " +
                "${p.dimensions.height} × ${p.dimensions.depth}"
        b.tvDesc.text = p.description

        // 1 review teratas
        val first = p.reviews.firstOrNull()
        if (first != null) {
            b.tvReview.text = format(first)
        } else {
            b.tvReview.text = "Belum ada review"
            b.tvAllReviews.visibility = View.GONE
        }

        // Dialog semua review
        b.tvAllReviews.setOnClickListener {
            val lv = ListView(this)
            lv.adapter = ArrayAdapter(
                this, android.R.layout.simple_list_item_1, p.reviews.map { format(it) })
            AlertDialog.Builder(this)
                .setTitle("Semua Review (${p.reviews.size})")
                .setView(lv)
                .setPositiveButton("Tutup", null)
                .show()
        }
    }

    private fun format(r: Review) =
        "${r.reviewerName} (${r.reviewerEmail})\n★ ${r.rating}\n${r.comment}\n${r.date.take(10)}"

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    class ImageAdapter(private val urls: List<String>) :
        RecyclerView.Adapter<ImageAdapter.VH>() {
        class VH(val iv: ImageView) : RecyclerView.ViewHolder(iv)

        override fun onCreateViewHolder(p: ViewGroup, t: Int): VH {
            val iv = ImageView(p.context)
            iv.layoutParams = ViewGroup.LayoutParams(600, 600)
            iv.scaleType = ImageView.ScaleType.FIT_CENTER
            return VH(iv)
        }

        override fun getItemCount() = urls.size
        override fun onBindViewHolder(h: VH, i: Int) {
            Glide.with(h.iv).load(urls[i]).into(h.iv)
        }
    }
}