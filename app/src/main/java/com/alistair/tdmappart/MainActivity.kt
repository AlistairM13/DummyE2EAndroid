package com.alistair.tdmappart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "MapPart"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit =
            Retrofit.Builder().baseUrl(AppInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val treeService = retrofit.create(TreeService::class.java)


        findViewById<Button>(R.id.btnSaveInfo).setOnClickListener {

            val treeName = findViewById<EditText>(R.id.etNameOfTree).text.toString()
            val localName = findViewById<EditText>(R.id.etLocalName).text.toString()
            val dbh = findViewById<EditText>(R.id.etDBH).text.toString()
            val speciesName = findViewById<EditText>(R.id.etSpeciesName).text.toString()
            val treeHeight = findViewById<EditText>(R.id.etHeight).text.toString().toDouble()
            val treeSpread = findViewById<EditText>(R.id.etSpread).text.toString().toDouble()
            val treeRemarks = findViewById<EditText>(R.id.etRemarks).text.toString()
            val latitude = findViewById<EditText>(R.id.etLatitude).text.toString()
            val longitude = findViewById<EditText>(R.id.etLongitude).text.toString()

            treeService.addTree(AppInfo.TOKEN,
                TreeInfo(
                    treeName = treeName,
                    localName = localName,
                    dbh = dbh,
                    species = speciesName,
                    height = treeHeight,
                    spread = treeSpread,
                    remarks = treeRemarks,
                    latitude = latitude,
                    longitude = longitude
                )).enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Log.d(TAG, "success ${response.body()}")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.d(TAG, "Failure $t")
                }
            })

            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}