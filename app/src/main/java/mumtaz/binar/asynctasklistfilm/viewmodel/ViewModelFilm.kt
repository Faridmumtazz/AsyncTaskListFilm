package mumtaz.binar.asynctasklistfilm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mumtaz.binar.asynctasklistfilm.model.GetAllFilmResponseItem
import mumtaz.binar.asynctasklistfilm.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var liveDataFIlm : MutableLiveData<List<GetAllFilmResponseItem>>

    init {
        liveDataFIlm = MutableLiveData()
    }

    fun getLiveDataObserver() : MutableLiveData<List<GetAllFilmResponseItem>> {
        return liveDataFIlm
    }

    fun getApiFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<GetAllFilmResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllFilmResponseItem>>,
                    response: Response<List<GetAllFilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFIlm.postValue(response.body())
                    }else{
                        liveDataFIlm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllFilmResponseItem>>, t: Throwable) {
                    liveDataFIlm.postValue(null)
                }

            })
    }
}