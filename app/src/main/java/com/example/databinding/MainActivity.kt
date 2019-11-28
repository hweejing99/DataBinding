package com.example.databinding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create an event handler for buttonSend
        buttonSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage(){
        //Create an Explicit Intent for the SecondActivity
        val intent = Intent(this,SecondActivity::class.java)

        //Prepare extra
        val message = editTextMessage.text.toString()
        //EXTRA_MESSAGE is a constant value
        intent.putExtra(EXTRA_MESSAGE, message)
        //start an activity with no return value
        //startActivity(intent)
        //start an activity with return value(s)/result(s)
        startActivityForResult(intent, REQUEST_REPLY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //if-else or switch statement will be used when there is multiple request code
        if(requestCode == REQUEST_REPLY){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(MainActivity.EXTRA_REPLY)
                textViewReply.text = String.format("%s : %s", getString(R.string.reply),reply)
            }
            else{
                textViewReply.text = String.format("%s : %s", getString(R.string.reply),"No reply")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        //it is unique
        const val EXTRA_MESSAGE = "com.example.databinding.MESSAGE"
        const val EXTRA_REPLY = "com.example.databinding.REPLY"
        const val REQUEST_REPLY = 1   //in integer type
    }
}//End of class
