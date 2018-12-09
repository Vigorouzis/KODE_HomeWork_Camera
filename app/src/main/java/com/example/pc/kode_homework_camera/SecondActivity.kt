package com.example.pc.kode_homework_camera


import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import java.io.File


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val photoPath = intent.getStringExtra("uri")

        val uriFromProvider = FileProvider.getUriForFile(this, "$packageName.fileprovider", File(photoPath))

        picture.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver, uriFromProvider))

        val str = intent.getStringExtra("Text")
        result.text = str.toString()


    }


}