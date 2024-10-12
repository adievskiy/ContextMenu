package com.example.contextmenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

        private lateinit var mark_ET: EditText
        private lateinit var generate_BTN: Button
        private lateinit var random_number_TV: TextView

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            mark_ET = findViewById(R.id.mark_ET)
            registerForContextMenu(mark_ET)

            random_number_TV = findViewById(R.id.random_number_TV)
            registerForContextMenu(random_number_TV)

            generate_BTN = findViewById(R.id.generate_BTN)
            generate_BTN.setOnClickListener(this)
    }

    fun getRandom(): String {
        return Random.nextInt(1,50).toString()
    }



    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.mark_ET) {
        menuInflater.inflate(R.menu.mark_menu, menu)
        }
        else if (v?.id == R.id.random_number_TV) {
            menuInflater.inflate(R.menu.random_number_menu, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val mark = mark_ET.text.toString()
        val markGenerated = random_number_TV.text.toString()
        when(item.itemId) {
            R.id.сolor_quality_mark_menu -> {
                when(mark.toInt()) {
                    1 -> mark_ET.setBackgroundColor(Color.parseColor("#FFA500"))
                    2 -> mark_ET.setBackgroundColor(Color.parseColor("#FFFF33"))
                    3 -> mark_ET.setBackgroundColor(Color.parseColor("#009900"))
                    4 -> mark_ET.setBackgroundColor(Color.parseColor("#0000FF"))
                    5 -> mark_ET.setBackgroundColor(Color.parseColor("#FF0000"))
                    else -> {
                        Toast.makeText(
                            applicationContext,
                            "Неверный ввод",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            R.id.exit_mark_menu -> finish()
            R.id.сolor_random_menu -> {
                when(markGenerated.toInt()) {
                    in 1..10 -> random_number_TV.setBackgroundColor(Color.parseColor("#FF0000"))
                    in 11..20 -> random_number_TV.setBackgroundColor(Color.parseColor("#FFA500"))
                    in 21..30 -> random_number_TV.setBackgroundColor(Color.parseColor("#FFFF33"))
                    in 31..40 -> random_number_TV.setBackgroundColor(Color.parseColor("#009900"))
                    in 41..50 -> random_number_TV.setBackgroundColor(Color.parseColor("#0000FF"))
                }
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onClick(v: View?) {
        var number = ""
        when (v?.id) {
            R.id.generate_BTN -> {
                number = getRandom()
            }
        }
        random_number_TV.text = number
    }
}