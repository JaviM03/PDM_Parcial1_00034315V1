package com.example.parcial1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*


/*Cambios hechos de prueba*/
class MainActivity : AppCompatActivity() {

    private val newMatchActivityRequestCode = 1
    private lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MatchListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        matchViewModel.allMatches.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setMatches(it) }
        })


        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewMatchActivity::class.java)
            startActivityForResult(intent, newMatchActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newMatchActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val match = Match(it.getStringExtra(NewMatchActivity.EXTRA_REPLY))

                matchViewModel.insert(match)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
    }
