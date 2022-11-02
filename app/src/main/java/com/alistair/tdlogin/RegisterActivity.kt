package com.alistair.tdlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alistair.tdlogin.api.Client
import com.alistair.tdlogin.databinding.ActivityRegisterBinding
import com.alistair.tdlogin.models.RegisterInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "RegisterActivity"

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterNewUser.setOnClickListener {
            val newUserName = binding.etRegisterName.text.toString()
            val newUserMail = binding.etRegisterEmail.text.toString()
            val newUserPhoneNumber = binding.etRegisterPhoneNumber.text.toString()
            val newUserPassword = binding.etRegisterPassword.text.toString()

            register(RegisterInfo(0, newUserName, newUserMail, newUserPhoneNumber, newUserPassword))

        }
    }

    private fun register(registerInfo: RegisterInfo) {
        Client.treeService.addUser(registerInfo).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
if(response.code() == 200){
    Toast.makeText(this@RegisterActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
    startActivity(intent)
}else{
    Toast.makeText(this@RegisterActivity, "Registration failed, error code ${response.code()}", Toast.LENGTH_SHORT).show()
}
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Registration failed $t", Toast.LENGTH_SHORT).show()
            }

        })
    }
}