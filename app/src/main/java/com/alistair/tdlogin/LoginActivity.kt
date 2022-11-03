package com.alistair.tdlogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alistair.tdlogin.api.Client
import com.alistair.tdlogin.models.Token
import com.alistair.tdlogin.databinding.ActivityLoginBinding
import com.alistair.tdlogin.models.AppInfo
import com.alistair.tdlogin.models.LoginInfo
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etLoginMail.text.toString()
            val password = binding.etLoginPassword.text.toString()
            val loginInfo = LoginInfo(username, password)
            login(loginInfo)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun login(loginInfo: LoginInfo) {

        Client.treeService.authenticate(loginInfo).enqueue(object:Callback<Token>{
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if(response.code() == 200){
                    Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show();
                    AppInfo.token = "Bearer ${response.body()!!.jwt}"
                    AppInfo.currentUser = binding.etLoginMail.text.toString()
                    val sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("token", AppInfo.token)
                    editor.commit()

                   val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this@LoginActivity, "Error ${response.code()}", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Login Failed!", Toast.LENGTH_SHORT).show();
            }

        })
    }
}