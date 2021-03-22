# Android-AsyncTask-using-Kotlin
Android AsyncTask using Kotlin
Android-AsyncTask-using-Kotlin

In this Android-AsyncTask,we’ll learn and implement AsyncTask using Kotlin in our Android Application.

inner class SomeTask extends AsyncTask<Params, Progress, Result>


The three type parameters of an asynchronous task are:

1)Params: The type of the parameters sent to the AsyncTask.
2)Progress: The type of the progress units published during the background computation.
3)Result: The type of the result of the background computation.

AsyncTask Methods
AsyncTask has four methods that are triggered at different times during the life cycle of async task execution.

PreExecute: This is invoked in UI thread before the AsyncTask is executed. We can show a ProgressBar or perform any UI related tasks in this method.
doInBackground: The background execution code goes in this method. We cannot call the Activity’s UI instances in this method.
onProgressUpdate: This method gets triggered when the publish progress is invoked in the doInBackground method. This method comes handy if you wish to inform UI thread about the current progress.
onPostExecute: gets triggered after doInBackground is over. The values returned from the doInBackground are received here. We can do the UI changes here such as updating the views with the values returned.

ADD this in AndroidManifest.xml
 android:usesCleartextTraffic ="true"

1. XML Layout Code

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_above="@+id/btnDoAsync"
        android:gravity="center"
        android:text="Value if returned from the AsyncTask, will be displayed here" />

    <Button
        android:id="@+id/btnDoAsync"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:layout_margin="16dp"
        android:text="START ASYNC TASK" />


    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/btnDoAsync"
        android:visibility="gone"
        android:layout_centerHorizontal="true" />

</RelativeLayout>

2. Kotlin Activity Class Code

In Code Section Its easy with AsyncTask  follow me 
