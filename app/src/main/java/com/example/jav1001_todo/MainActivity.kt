package com.example.jav1001_todo

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAdd: Button
    private lateinit var listViewTasks: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var tasksList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAdd = findViewById(R.id.buttonAdd)
        listViewTasks = findViewById(R.id.listViewTasks)

        tasksList = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_checked, tasksList)
        listViewTasks.adapter = adapter

        buttonAdd.setOnClickListener {
            val task = editTextTask.text.toString().trim()
            if (task.isNotEmpty()) {
                tasksList.add(task)
                adapter.notifyDataSetChanged()
                editTextTask.setText("")
            }
        }

        listViewTasks.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val checkedTextView = view as CheckedTextView
                checkedTextView.isChecked = !checkedTextView.isChecked
            }

        listViewTasks.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { parent, view, position, id ->
                tasksList.removeAt(position)
                adapter.notifyDataSetChanged()
                true
            }
    }
}
