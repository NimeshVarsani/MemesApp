package com.example.memes

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.memes.data.Meme
import com.example.memes.webservice.ApiInterface
import com.example.memes.webservice.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var memeImageView: ImageView
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        memeImageView = findViewById(R.id.memeImage)
        nextButton = findViewById(R.id.next)

        progressBar.visibility = View.VISIBLE

        fetchUser()

        nextButton.setOnClickListener{
            fetchUser()
        }
    }

    private fun fetchUser(){
        val request = ServiceBuilder.buildService(ApiInterface::class.java)
        val call = request.getUsers(getString(R.string.api_key))

        call.enqueue(object : Callback<Meme> {
            override fun onResponse(call: Call<Meme>, response: Response<Meme>) {
                if (response.isSuccessful){

                    val currentImageUrl = response.body()?.url
                    Log.d("lolo", "onResponse: ${response.body()!!}")

                    Glide.with(this@MainActivity).load(currentImageUrl).listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                        progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    }).into(memeImageView)
                }
            }
            override fun onFailure(call: Call<Meme>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("ioioo", "Failed  ${t.message}")

            }
        })
    }
}