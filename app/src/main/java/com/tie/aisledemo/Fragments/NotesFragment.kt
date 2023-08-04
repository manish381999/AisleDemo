package com.tie.aisledemo.Fragments

import ApiInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tie.aisledemo.ApiClient.ApiClient.getclient

import com.tie.aisledemo.ModelClass.UserNotesResponse
import com.tie.aisledemo.databinding.FragmentNotesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesFragment : Fragment() {
    private var binding: FragmentNotesBinding? = null
    private var apiInterface: ApiInterface? = null
    private var authToken: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the auth token from the arguments
        authToken = arguments?.getString("authToken")

        // Create the Retrofit instance using ApiClient class
        val retrofit = getclient()

        // Create the API interface
        apiInterface = retrofit!!.create(ApiInterface::class.java)

        // Make the Notes API call
        fetchUserNotes()
    }

    private fun fetchUserNotes() {
        // Add the auth token to the header
        val headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer $authToken"

        // Make the Notes API call using the safe call operator
        val call = apiInterface?.getUserNotes(headers)
        call?.enqueue(object : Callback<UserNotesResponse?> {
            override fun onResponse(
                call: Call<UserNotesResponse?>,
                response: Response<UserNotesResponse?>
            ) {
                if (response.isSuccessful) {
                    val userNotesResponse = response.body()
                    if (userNotesResponse != null) {
                        // Handle the user's notes here
                        // userNotesResponse.getNotes() will contain the list of notes
                    } else {
                        // Handle null response if needed
                    }
                } else {
                    // Handle non-successful response (e.g., HTTP error code)
                }
            }

            override fun onFailure(call: Call<UserNotesResponse?>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}