package com.dicoding.bangkit2023.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMotorcycle: RecyclerView
    private val list = ArrayList<Motorcycle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMotorcycle = findViewById(R.id.rv_motorcycle)
        rvMotorcycle.setHasFixedSize(true)

        list.addAll(getListMotorcycle())
        showRecyclerList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListMotorcycle(): ArrayList<Motorcycle> {

        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataInfo = resources.getStringArray(R.array.data_info)
        val dataHarga = resources.getStringArray(R.array.data_harga)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listMotorcycle = ArrayList<Motorcycle>()
        for (i in dataName.indices) {
            val motorcycle = Motorcycle(
                dataName[i],
                dataHarga[i],
                dataInfo[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listMotorcycle.add(motorcycle)
        }
        return listMotorcycle
    }

    private fun showRecyclerList() {
        rvMotorcycle.layoutManager = LinearLayoutManager(this)
        val listMotorcycleAdapter = ListMotorcycleAdapter(list)
        rvMotorcycle.adapter = listMotorcycleAdapter

        listMotorcycleAdapter.setOnItemClickCallback(object :
            ListMotorcycleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Motorcycle) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutPage::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}