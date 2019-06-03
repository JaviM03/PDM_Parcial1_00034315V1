package com.example.parcial1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewMatchActivity : AppCompatActivity() {

    private lateinit var editMatchView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)
        editMatchView = findViewById(R.id.edit_match)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editMatchView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val match = editMatchView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, match)

                val Team1 = editMatchView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, Team1)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.matchlistsql.REPLY"


    }
}