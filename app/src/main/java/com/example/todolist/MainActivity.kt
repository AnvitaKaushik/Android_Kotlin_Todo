package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var item: EditText
    lateinit var add : Button
    lateinit var listView : ListView

    var itemList = ArrayList<String>()

    var fileHelper = FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)
        itemList = fileHelper.readData(this)
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        listView.adapter = adapter

        add.setOnClickListener {
            var itemName: String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writeData(itemList, this)
            adapter.notifyDataSetChanged()
        }
        listView.setOnItemClickListener {
        arrayAdapter, view, position, l ->
        var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete the task?")
            alert.setCancelable(true)
            alert.setNegativeButton("No",{
                dialogueInterface, i ->  dialogueInterface.cancel()})
            alert.setPositiveButton("Yes",{dialogInterface,i ->
                itemList.removeAt(position)
                adapter.notifyDataSetChanged()
                fileHelper.writeData(itemList,applicationContext)

            })
            alert.create().show()
        }


    }


}