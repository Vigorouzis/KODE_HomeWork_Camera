package com.example.pc.kode_homework_camera


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val PHOTO = "uri"
    val CAMERA_REQUEST_CODE = 0

    private var mUriFromProvider: Uri? = null
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        TakePicture.setOnClickListener {

            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (captureIntent.resolveActivity(packageManager) != null) {

                try {
                    photoFile = File.createTempFile("photo_${Date().time}", ".jpg", this.filesDir)



                    photoFile.createNewFile()

                    mUriFromProvider = FileProvider.getUriForFile(this, "$packageName.fileprovider", photoFile)



                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUriFromProvider)
                    startActivityForResult(captureIntent, CAMERA_REQUEST_CODE)
                } catch (e: IOException) {
                    e.printStackTrace()

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                when (resultCode) {

                    Activity.RESULT_OK -> {


                        val second = Intent(this, SecondActivity::class.java)
                        second.putExtra(
                            PHOTO,
                            photoFile.absolutePath
                        )
                        val text = Name.text.toString()
                        second.putExtra("Text", text)
                        startActivity(second)
             
                    }


                    else -> {
                        Toast.makeText(this,"REQUEST_CAMERA -> else!? Crushing result",LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
