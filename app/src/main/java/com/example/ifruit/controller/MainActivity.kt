package com.example.ifruit.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ifruit.R
import com.example.ifruit.controller.fragment.MainMenuFragment
import com.example.ifruit.helper.InputValidation
import com.example.ifruit.model.Fruit
import com.example.ifruit.model.User
import com.example.ifruit.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val activity = this@MainActivity
    private lateinit var mainConstraint: ConstraintLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var appCompatButtonLogin: AppCompatButton
    private lateinit var textViewForgotPass: AppCompatTextView
    private lateinit var inputValidation: InputValidation
    private var databaseHelper: DatabaseHelper?=null
    //private lateinit var addButton:AppCompatButton
    var listFruit:List<String> = listOf("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper=DatabaseHelper(this)

        // hiding the action bar
        supportActionBar!!.hide()
        // initializing the views
        initViews()
        // initializing the listeners
        initListeners()
        // initializing the objects
        initObjects()


        makeUser(databaseHelper!!)
        makeFruit(databaseHelper!!)

//        var result= databaseHelper!!.readFruitData()
//        for (i in 0..(result?.size-1)){
//            listFruit+= result.get(i).name.toString()
//        }
//        System.out.println(listFruit)
//
//        System.out.println(result.get(0).toString())

//        addButton.setOnClickListener {
//            val user= User(
//                2,
//                "Cassie Cage"
//                ,textInputEditTextEmail.text.toString(),
//                textInputEditTextPassword.text.toString()
//            )
//            databaseHelper!!.addUser(user)
//        }



    }

    private fun makeUser(databaseHelper: DatabaseHelper) {
        val user1= User(
                1,
                "Cassie Cage"
                ,"casie2021",
                "Hello_Casie123"
        )
        databaseHelper!!.addUser(user1)
        val user2= User(
                2,
                "George Gorgi"
                ,"Gorgi@123",
                "Gorgi&123"
        )
        databaseHelper!!.addUser(user2)



    }

    private fun makeFruit(databaseHelper: DatabaseHelper) {
        val fruit1= Fruit(
            100,
            "کیوی"
            ,30000,
            1
        )
        databaseHelper!!.addFruit(fruit1)
        val fruit2= Fruit(
            101,
            "پرتقال"
            ,15000,
            1
        )
        databaseHelper!!.addFruit(fruit2)



    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        mainConstraint=findViewById(R.id.mainconstarint) as ConstraintLayout
        textInputLayoutEmail = findViewById(R.id.username_field) as TextInputLayout
        textInputLayoutPassword = findViewById(R.id.password_field) as TextInputLayout
        textInputEditTextEmail = findViewById(R.id.editusername) as TextInputEditText
        textInputEditTextPassword = findViewById(R.id.editpassword) as TextInputEditText
        appCompatButtonLogin = findViewById(R.id.sign_in) as AppCompatButton
        textViewForgotPass = findViewById(R.id.forgot_password) as AppCompatTextView
       // addButton=findViewById(R.id.add_btn) as AppCompatButton
    }
    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        //addButton!!.setOnClickListener(this)
        appCompatButtonLogin!!.setOnClickListener(this)
        textViewForgotPass!!.setOnClickListener(this)
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in -> verifyFromSQLite()

            //R.id.add_btn->postDataToSQLite()

            R.id.forgot_password -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, Forgot_Password::class.java)
                startActivity(intentRegister)
            }
        }
    }
    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {
//        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(R.string.error_message_email))) {
//            return
//        }
//        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(R.string.error_message_email))) {
//            return
//        }
//        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!, textInputLayoutPassword!!, getString(R.string.error_message_email))) {
//            return
//        }
        if (databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, textInputEditTextPassword!!.text.toString().trim { it <= ' ' })) {
            val accountsIntent = Intent(activity, MenuActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(mainConstraint, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }


    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private fun postDataToSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return
        }
        if (!databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {
            var user = User(1,"George Gorgi",
                textInputEditTextEmail!!.text.toString().trim(),
                textInputEditTextPassword!!.text.toString().trim())
            databaseHelper!!.addUser(user)
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(mainConstraint!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(mainConstraint!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }




}