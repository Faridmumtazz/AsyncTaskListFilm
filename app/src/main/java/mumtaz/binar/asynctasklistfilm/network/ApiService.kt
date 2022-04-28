package mumtaz.binar.asynctasklistfilm.network

import mumtaz.binar.asynctasklistfilm.model.GetAllFilmResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("apifilm.php")
    fun getAllFilm() : Call<List<GetAllFilmResponseItem>>
}