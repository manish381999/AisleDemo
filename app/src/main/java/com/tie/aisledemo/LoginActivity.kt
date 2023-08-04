package com.tie.aisledemo

import ApiInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tie.aisledemo.ApiClient.ApiClient.getclient
import com.tie.aisledemo.ModelClass.LoginModel
import com.tie.aisledemo.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    var apiInterface: ApiInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val retrofit = getclient()
        apiInterface = retrofit!!.create(ApiInterface::class.java)
        binding!!.Continue.setOnClickListener {
            val countryCode = binding!!.etCountryCode.text.toString().trim { it <= ' ' }
            val mobileNumber = binding!!.etModileNo.text.toString().trim { it <= ' ' }
            val combinedNumber = countryCode + mobileNumber
            Log.d("CombinedNumber", combinedNumber)
            checkValidation(combinedNumber)
        }
    }

    private fun checkValidation(combinedNumber: String?) {
        combinedNumber?.let { callApi(it) }
    }

    private fun callApi(combinedNumber: String) {
        apiInterface!!.loginCall(combinedNumber)!!.enqueue(object : Callback<LoginModel?> {
            override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
                if (response.isSuccessful) {
                    val loginModel = response.body()
                    if (loginModel != null) {
                        if (loginModel.status) {
                            // Login successful
                            Toast.makeText(this@LoginActivity, "Enter Otp", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, OtpActivity::class.java)
                            intent.putExtra("phoneNumber", combinedNumber)
                            startActivity(intent)
                        } else {
                            // Error or unsuccessful login
                            Toast.makeText(applicationContext, "Error or unsuccessful login", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Response body is null, handle error if needed
                        Toast.makeText(applicationContext, "API response body is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle non-successful response (e.g., HTTP error code)
                    Toast.makeText(applicationContext, "API call unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}