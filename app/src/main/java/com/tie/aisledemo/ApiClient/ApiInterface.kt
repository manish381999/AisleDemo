import com.tie.aisledemo.ModelClass.LoginModel
import com.tie.aisledemo.ModelClass.OtpModel
import com.tie.aisledemo.ModelClass.UserNotesResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("users/phone_number_login")
    fun loginCall(
        @Field("number") number: String?
    ): Call<LoginModel?>?

    @FormUrlEncoded
    @POST("users/verify_otp")
    fun otpCall(
        @Field("number") number: String?,
        @Field("otp") otp: String?
    ): Call<OtpModel?>?

    @GET("users/test_profile_list")
    fun getUserNotes(@Header("Authorization") authToken: HashMap<String, String>): Call<UserNotesResponse?>?

}
