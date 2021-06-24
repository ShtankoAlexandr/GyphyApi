package com.example.myapplication.features.books.presentation.activities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.features.books.presentation.adapters.GifsAdapter
import com.example.myapplication.features.books.presentation.viewmodel.GifsViewModel
import com.example.myapplication.features.books.presentation.viewmodel.state.GifsVS
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: GifsViewModel by viewModel()
    private val mAdapter = GifsAdapter()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()

        rvPost.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        viewModel.getPosts(BuildConfig.GIPHY_API_KEY, 20)
    }

    private fun observeViewModel(){
        //Obtener los Posts
        viewModel.viewState.observe(this@MainActivity, {
            when (it) {
                is GifsVS.Entities -> {
                    Log.e("ii","${it.entities.data}")
                    mAdapter.addItems(it.entities.data)

                }
                is GifsVS.ShowLoader ->{
                    if(it.showLoader){
                        progressBar?.visibility = View.VISIBLE
                        //rvPost.visibility = View.GONE
                    }else{
                        progressBar?.visibility = View.GONE
                        //rvPost.visibility = View.VISIBLE
                    }
                }
                is GifsVS.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    /*override fun onPostClicked(id: Int) {
        startActivity(PostActivity.createInstance(context = this, id = id.toString()))
    }*/
}
