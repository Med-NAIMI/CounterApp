package com.example.counterapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    lateinit var submit:Button
    lateinit var currentCounter:TextView
    lateinit var startingNumber:EditText
    lateinit var randomStartingNumber:Button
    lateinit var plus:Button
    lateinit var interval:EditText
    lateinit var moins:Button
    lateinit var operations:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflating layout
        setContentView(R.layout.activity_main)

        //getting references to components
        submit=findViewById(R.id.main_activity_bt_submit)
        currentCounter=findViewById(R.id.main_activity_tv_number)
        startingNumber=findViewById(R.id.main_activity_et_starting_number)
        randomStartingNumber=findViewById(R.id.main_activity_bt_randomStartingNumber)
        plus=findViewById(R.id.main_activity_bt_increment)
        interval=findViewById(R.id.main_activity_et_interval)
        moins=findViewById(R.id.main_activity_bt_decrement)
        operations=findViewById(R.id.main_activity_tv_operations)

        //invoking event listeners
        submit.setOnClickListener { updateCounter() }
        randomStartingNumber.setOnClickListener { generateRandomNumber() }
        plus.setOnClickListener { changeNumberValue("+") }
        moins.setOnClickListener { changeNumberValue("-") }
    }

    private fun updateCounter(){
        //getting starting number value
        var startingNumberVal=startingNumber.text.toString()
        if(startingNumberVal == ""){
            //define a default value 0
            startingNumberVal="0"
        }

        //changing current value's value
        currentCounter.text="$startingNumberVal"

        //Clear starting number edittext
        startingNumber.setText("")

        //hiding keyboard
        hideKeyboard()
    }
    private fun generateRandomNumber(){
        //getting starting number value via random function
        val startingNumberRandomVal=(0..100).random()

        //changing current value's value
        currentCounter.text="$startingNumberRandomVal"
    }

    private fun changeNumberValue(operation :String ){
        //getting interval's value
        var intervalVal=interval.text.toString()

        //testing whether the value is set to not empty string if so 1 is a default value
        if(intervalVal=="") {
            intervalVal="1"
        }

        //getting current counter's value
        val currentCounterVal=currentCounter.text.toString().toInt()
        if(operation=="+"){
            //changing current counter's value
            val newNumber=currentCounterVal + intervalVal.toInt()
            currentCounter.text=newNumber.toString()

            //displaying operation in the textView
            operations.text="$currentCounterVal + $intervalVal"
        }else{
            //changing current counter's value
            val newNumber=currentCounterVal - intervalVal.toInt()
            currentCounter.text=newNumber.toString()

            //displaying operation in the textView
            operations.text="$currentCounterVal - $intervalVal"
        }

        //hiding keyboard
        hideKeyboard()

    }

//the commented code is refactored into one application changeNumberValue
    
    /*private fun incrementCounterBasedOnInterval(){
        //getting interval's value
        var intervalVal=interval.text.toString()

        //testing whether the value is set to not empty string if so 1 is a default value
        if(intervalVal=="") {
            intervalVal="1"
        }

        //getting current counter's value
        val currentCounterVal=currentCounter.text.toString().toInt()

        //changing current counter's value
        val newNumber=currentCounterVal + intervalVal.toInt()
        currentCounter.text=newNumber.toString()

        //displaying operation in the textView
        operations.text="$currentCounterVal + $intervalVal"

        //hiding keyboard
        hideKeyboard()
    }

    private fun decrementCounterBasedOnInterval(){
        //getting interval's value
        var intervalVal=interval.text.toString()

        //testing whether the value is set to not empty string if so 1 is a default value
        if(intervalVal=="") {
            intervalVal="1"
        }

        //getting current counter's value
        val currentCounterVal=currentCounter.text.toString().toInt()

        //changing current counter's value
        val newNumber=currentCounterVal - intervalVal.toInt()
        currentCounter.text=newNumber.toString()

        //displaying operation in the textView
        operations.text="$currentCounterVal - $intervalVal"

        //hiding keyboard
        hideKeyboard()
    }
*/
    private fun hideKeyboard(){
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentCounter.windowToken,0)
    }

}