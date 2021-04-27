package com.example.top10downlodestest01

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.lang.Exception

class ParseApplications {
private val TAG = "ParseApplications"
    val applications = ArrayList<FeedEntry>()

    fun parse(xmlData:String):Boolean
    {
        Log.d(TAG,"parse called with $xmlData")
        var status = true
        var inEntry = false
        var textValue =""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var evenType = xpp.eventType
            var currentRecord = FeedEntry()
            while (evenType!= XmlPullParser.END_DOCUMENT){
                val tagName = xpp.name?.toLowerCase()

                when(evenType){
                    XmlPullParser.START_TAG ->
                    {
                        Log.d(TAG,"parse: Starting Tag for "+tagName)
                        if (tagName == "entry")
                        {
                            inEntry = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG ->
                    {
                        Log.d(TAG,"parse: Ending Tag for "+tagName)
                        if (inEntry)
                        {
                          when(tagName)
                          {
                            "entry" -> {
                                applications.add(currentRecord)
                                inEntry = false
                                currentRecord = FeedEntry()
                            }
                              "name" -> currentRecord.name = textValue
                              "artist" -> currentRecord.artist = textValue
                              "releasedate" -> currentRecord.releaseDate = textValue
                              "summary" -> currentRecord.releaseDate = textValue
                              "imgage" -> currentRecord.imageURL = textValue

                          }
                        }
                    }
                }

                evenType = xpp.next()
            }
            for (app in applications)
            {
                Log.d(TAG,"****************")
                Log.d(TAG,app.toString())
            }

        }catch (e:Exception){
          e.printStackTrace()
            status = false
        }
        return status
    }
}