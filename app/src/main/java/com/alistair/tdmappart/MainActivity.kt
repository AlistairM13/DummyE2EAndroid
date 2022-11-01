package com.alistair.tdmappart

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

private const val BASE_URL = "http://10.0.2.2:8080/"
private const val TAG = "MapPart"
private const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGlAMTIzIiwiZXhwIjoxNjY3MzI5MDM5LCJpYXQiOjE2NjcyOTMwMzl9.NXxcGMD7AwoXK0Q3912Owxcex27CwL6yJCjPi36LbxE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val treeService = retrofit.create(TreeService::class.java)


        findViewById<Button>(R.id.btnSaveInfo).setOnClickListener {

            val treeName = findViewById<EditText>(R.id.etNameOfTree).text.toString()
            val localName = findViewById<EditText>(R.id.etLocalName).text.toString()
            val dbh = findViewById<EditText>(R.id.etDBH).text.toString()
            val speciesName = findViewById<EditText>(R.id.etSpeciesName).text.toString()
            val treeHeight = findViewById<EditText>(R.id.etHeight).text.toString().toDouble()
            val treeSpread = findViewById<EditText>(R.id.etSpread).text.toString().toDouble()
            val treeRemarks = findViewById<EditText>(R.id.etRemarks).text.toString()

            treeService.addTree( TOKEN, TreeInfo(
                treeName = treeName,
                localName = localName,
                dbh = dbh,
                species = speciesName,
                height = treeHeight,
                spread = treeSpread,
                remarks = treeRemarks
            )).enqueue(object : Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Log.d(TAG,"success ${response.body()}")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.d(TAG,"Failure $t")
                }
            })


        }
    }
}