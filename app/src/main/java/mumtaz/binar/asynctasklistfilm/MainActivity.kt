package mumtaz.binar.asynctasklistfilm

import android.annotation.SuppressLint
import android.content.Context
import android.nfc.tech.MifareUltralight.get
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mumtaz.binar.asynctasklistfilm.adapter.AdapterFilm
import mumtaz.binar.asynctasklistfilm.viewmodel.ViewModelFilm

class MainActivity : AppCompatActivity() {
    lateinit var adapterfilm : AdapterFilm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterfilm = AdapterFilm(){}
        rv_binar.layoutManager = LinearLayoutManager(this)
        rv_binar.adapter = adapterfilm

        AsyncTaskFilm()
    }

    inner class AsyncTaskFilm : AsyncTask<Int, Void, String>() {
        @SuppressLint("WrongThread")
        override fun doInBackground(vararg p0: Int?): String {
            val viewModel = ViewModelProvider(this@MainActivity).get(ViewModelFilm::class.java)
            viewModel.getLiveDataObserver().observe(this@MainActivity, Observer {
                if (it != null) {
                    adapterfilm.setDataFilm(it)
                    adapterfilm.notifyDataSetChanged()
                }
            })
            viewModel.getApiFilm()
            return viewModel.toString()
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }

    }
}