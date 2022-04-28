package mumtaz.binar.asynctasklistfilm

import android.annotation.SuppressLint
import android.app.ProgressDialog
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

        AsyncTaskFilm()
    }

    inner class AsyncTaskFilm : AsyncTask<Void, Void, Void>() {
        lateinit var pdialog : ProgressDialog

        override fun onPreExecute() {
            super.onPreExecute()
            pdialog = ProgressDialog(this@MainActivity)
            pdialog.show()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            rv_binar.layoutManager = LinearLayoutManager(this@MainActivity)
            adapterfilm = AdapterFilm()
            rv_binar.adapter = adapterfilm

            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            pdialog.dismiss()

            val viewModel = ViewModelProvider(this@MainActivity).get(ViewModelFilm::class.java)

            viewModel.getLiveDataObserver().observe(this@MainActivity, Observer {
                if (it != null){
                    adapterfilm.setDataFilm(it)
                    adapterfilm.notifyDataSetChanged()
                }
            })

            viewModel.getApiFilm()

        }

    }

}