package com.dicoding.bangkit2023.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ShareCompat
import androidx.core.graphics.drawable.toBitmap


@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.apply {
            title = "Motor Matic Honda"
            setDisplayHomeAsUpEnabled(true)
        }


        val dataMotorcycle = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("DATA", Motorcycle::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("DATA")
        }

        val tvDetailPhoto = findViewById<ImageView>(R.id.img_item_detail_photo)
        val tvDetailName = findViewById<TextView>(R.id.tv_item_detail_name)
        val tvDetailInfo = findViewById<TextView>(R.id.tv_item_detail_info)
        val tvDetailHarga = findViewById<TextView>(R.id.tv_item_detail_harga)
        val tvDetailDescription = findViewById<TextView>(R.id.tv_item_detail_description)

        tvDetailPhoto.setImageResource(dataMotorcycle?.photo!!)
        tvDetailName.text = dataMotorcycle.name
        tvDetailInfo.text = dataMotorcycle.info
        tvDetailHarga.text = dataMotorcycle.harga
        tvDetailDescription.text = if (Build.VERSION.SDK_INT >= 33) {
            Html.fromHtml("<b><big>Deskripsi</big></b><br><br>${dataMotorcycle.description}", Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml("<b><big>Deskripsi</big></b><br><br>${dataMotorcycle.description}")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val dataMotorcycle = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra("DATA", Motorcycle::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra("DATA")
                }

                val mimeType = "text/plain"
                val title = "Bagikan info tentang Motor ${dataMotorcycle?.name}"
                val text = "Saya ingin berbagi informasi tentang ${dataMotorcycle?.name}.\n\nHarga mulai dari ${dataMotorcycle?.harga}.\n\n${dataMotorcycle?.description}"
                val bitmap = (findViewById<ImageView>(R.id.img_item_detail_photo)).drawable.toBitmap()
                val uri = getImageUri(this, bitmap)

                ShareCompat.IntentBuilder.from(this)
                    .setType(mimeType)
                    .setChooserTitle(title)
                    .setText(text)
                    .setStream(uri)
                    .startChooser()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "sssssssssssssssssssssss", null)
        return Uri.parse(path)
    }

}