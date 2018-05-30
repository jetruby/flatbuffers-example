package com.example.flatbuffers.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bluelinelabs.logansquare.LoganSquare
import com.example.flatbuffers.app.flatmodel.DeveloperList
import com.example.flatbuffers.app.jsonmodel.DeveloperListJson
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val tvFlatBufferResult by lazy { findViewById<TextView>(R.id.tv_flatbuffer_result) }
    val tvJsonGSONResult by lazy { findViewById<TextView>(R.id.tv_json_result_with_gson) }
    val tvJsonLoganSquareResult by lazy { findViewById<TextView>(R.id.tv_json_result_with_logansquare) }
    val btnStartFlatBuffer by lazy { findViewById<Button>(R.id.btn_start_flatbuffer) }
    val btnStartJsonWithGSON by lazy { findViewById<Button>(R.id.btn_start_json_with_gson) }
    val btnStartJsonWithLoganSquare by lazy { findViewById<Button>(R.id.btn_start_json_with_logansquare) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadFromFlatBuffer(view: View) {
        btnStartFlatBuffer.isEnabled = false
        val buffer = readRawResource(R.raw.example_flatbuffer)

        tvFlatBufferResult.text = getString(
            R.string.process_result,
            measureTimeMillis {
                val byteBuffer = ByteBuffer.wrap(buffer)
                val developerList = DeveloperList.getRootAsDeveloperList(byteBuffer)
            }.toString()
        )
        btnStartFlatBuffer.isEnabled = true
    }

    fun loadFromJsonWithGSON(view: View) {
        btnStartJsonWithGSON.isEnabled = false
        val jsonText = String(readRawResource(R.raw.example_json)!!)

        tvJsonGSONResult.text = getString(
            R.string.process_result,
            measureTimeMillis {
                val developerList = Gson().fromJson(jsonText, DeveloperListJson::class.java)
            }.toString()
        )
        btnStartJsonWithGSON.isEnabled = true
    }

    fun loadFromJsonWithLoganSquare(view: View) {
        btnStartJsonWithLoganSquare.isEnabled = false
        val jsonText = String(readRawResource(R.raw.example_json)!!)

        tvJsonLoganSquareResult.text = getString(
            R.string.process_result,
            measureTimeMillis {
                val developerList = LoganSquare.parse(jsonText, DeveloperListJson::class.java)
            }.toString()
        )
        btnStartJsonWithLoganSquare.isEnabled = true
    }

    private fun readRawResource(resId: Int): ByteArray? {
        var stream: InputStream? = null
        var buffer: ByteArray? = null

        try {
            stream = this.resources.openRawResource(resId)
            buffer = ByteArray(stream.available())
            while (stream.read(buffer) != -1) {
            }
        } catch (e: IOException) {
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                }
            }
        }
        return buffer
    }
}
