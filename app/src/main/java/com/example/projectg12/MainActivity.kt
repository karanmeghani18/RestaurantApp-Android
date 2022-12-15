package com.example.projectg12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectg12.models.MenuItem
import com.google.gson.Gson
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun getMenuItems(): MenuItem.CREATOR {
        val inputStream: InputStream = resources.openRawResource(R.raw.menu)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n : Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)

            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val restaurantModel = gson.fromJson<Array<MenuItem>>(jsonStr, Array<MenuItem>::class.java).toList()

        return MenuItem
    }
}