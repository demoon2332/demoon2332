package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.fathzer.soft.javaluator.DoubleEvaluator
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var display : EditText
    lateinit var result : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById<EditText>(R.id.display)
        display.showSoftInputOnFocus = true
        result = findViewById<TextView>(R.id.result)
        //val result = findViewById<TextView>(R.id.result)
        val board = findViewById<TableLayout>(R.id.tableLayout)

        for(i in 0..board.childCount-1){
            val row : TableRow = board.getChildAt(i) as TableRow
            for(j in 0..row.childCount-1)
            {
                val btn : Button = row.getChildAt(j) as Button
                if(btn.text != "="){
                    btn.setOnClickListener{
                        val b : Button = it as Button
                        val s : String = b.text as String
                        updateText(s)
                    }
                }
            }

        }
    }
    fun updateText(s : String){
        display.text.append(s)
    }

    fun clear(v : View){
        display.text.clear()
    }
    fun delete(v : View){
        display.setText(display.text.dropLast(1))
    }
    fun calculate(v : View){
        var math : String = display.text.toString()
        math = math.replace("×","*");
        math = math.replace("%","/100");
        math = math.replace("÷","/");
        Log.d("calculate",math.toString())
        try{
            val answer : Double = DoubleEvaluator().evaluate(math)
            result.text = answer.toString()
        }
        catch(e : Exception){
            Log.d("calculate","Error")
        }
    }


}