package com.tie.aisledemo

import ApiInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tie.aisledemo.ApiClient.ApiClient.getclient
import com.tie.aisledemo.MainActivity
import com.tie.aisledemo.ModelClass.OtpModel
import com.tie.aisledemo.databinding.ActivityOtpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpActivity : AppCompatActivity() {
    var binding: ActivityOtpBinding? = null
    var phoneNumber: String? = null
    var apiInterface: ApiInterface? = null
    var otpModel: OtpModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val retrofit = getclient()
        apiInterface = retrofit!!.create(ApiInterface::class.java)

        phoneNumber = intent.getStringExtra("phoneNumber").toString()
        binding!!.phoneNumber.text = phoneNumber

        binding!!.Continue.setOnClickListener {
            val OTP = binding!!.etOtp.text.toString().trim()
            checkValidation(phoneNumber, OTP)
        }
    }

    private fun checkValidation(phoneNumber: String?, otp: String?) {
        if (phoneNumber != null && otp != null) {
            callApi(phoneNumber, otp)
        } else {
            Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun callApi(phoneNumber: String, otp: String) {
        apiInterface!!.otpCall(phoneNumber, otp)?.enqueue(object : Callback<OtpModel?> {
            override fun onResponse(call: Call<OtpModel?>, response: Response<OtpModel?>) {
                if (response.isSuccessful) {
                    otpModel = response.body()
                    if (otpModel != null) {
                        Toast.makeText(this@OtpActivity, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@OtpActivity, MainActivity::class.java)
                        intent.putExtra("authToken", otpModel!!.token)
                        startActivity(intent)
                    } else {
                        // Error or unsuccessful login
                        Toast.makeText(applicationContext, "Error or unsuccessful login", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle non-successful response (e.g., HTTP error code)
                    Toast.makeText(applicationContext, "API call unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OtpModel?>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
