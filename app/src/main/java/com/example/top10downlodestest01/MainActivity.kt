@file:Suppress("DEPRECATION")
package com.example.top10downlodestest01

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate : called")
        val downlodedaya = downlodeData()
        downlodedaya.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d(TAG,"onCreate : working")
    }
    companion object {
        private class downlodeData : AsyncTask<String, Void, String>() {
            val TAG = "downlodeData"
            override fun onPreExecute() {
                super.onPreExecute()
                Log.d(TAG, "onPreExecute function: calling")
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground function called ${url[0]}")
                val rssFeeds = downLodeXml(url[0])
                Log.d(TAG,"doInBackground:rssFeeds Data in rssFeeds")
                if (rssFeeds.isEmpty())
                {
                  Log.e(TAG,"doInBackground:rssFeeds is empty")
                }
                return rssFeeds
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                Log.d(TAG,"doInBackground:onPostExecute called $result")
            }

            override fun onProgressUpdate(vararg values: Void?) {
                super.onProgressUpdate(*values)
                Log.d(TAG,"onProgressUpdate: calling ")
            }

            private fun downLodeXml(params: String?):String {
                val xmlResult = StringBuilder()

                try {
                    val url = URL(params)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val responce = connection.responseCode
                    Log.d(TAG, "downLodeXml: Responce $responce")
                    connection.inputStream.buffered().reader().use { xmlResult.append(it.readText()) }
                    Log.d(TAG,"downLodeXml: received data from Rss ${xmlResult.length} bytes")
                    return xmlResult.toString()
                } catch (e: MalformedURLException) {
                    Log.e(TAG, "downLodeXml :Invalid URL ${e.message}")
                } catch (e: IOException) {
                    Log.e(TAG, "downLodeXml :IOException Error read Data ${e.message}")
                } catch (e: SecurityException) {
                    Log.e(TAG, "downLodeXml :SecurityException need internet permition ${e.message}")
                } catch (e: Exception) {
                    Log.e(TAG, "UnKnown Exception  ${e.message}")
                }
                return ""
            }
        }
    }

}