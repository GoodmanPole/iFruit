package com.example.ifruit.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.ifruit.model.*
import com.example.ifruit.model.User
import kotlin.collections.ArrayList

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("Hello","Hello")
       val CREATE_TABLE_QUERY:String="CREATE TABLE "+ TABLE_NAME1+"("+COL_ID + " INTEGER PRIMARY KEY ," + COL_NAME + " TEXT,"+ COL_EMAIL + " TEXT," + COL_PASSWORD + " TEXT)"
        db!!.execSQL(CREATE_TABLE_QUERY)


        val CREATE_TABLE_QUERY2:String="CREATE TABLE "+ TABLE_NAME2+"("+COL_ID1 + " INTEGER PRIMARY KEY ," + COL_NAME1 + " TEXT,"+ COL_PRICE + " INTEGER," + COL_QLT + " INTEGER)"
        db!!.execSQL(CREATE_TABLE_QUERY2)
        Log.e("1","Hello2")
        val CREATE_TABLE_QUERY3:String="CREATE TABLE "+ TABLE_NAME3+"("+ COL_ID2 + " INTEGER PRIMARY KEY ," + COL_NAME2 + " TEXT,"+ COL_SALARY + " INTEGER," + COL_PHONENUMBER + " INTEGER)"
        db!!.execSQL(CREATE_TABLE_QUERY3)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME1")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME2")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME3")
        onCreate(db!!)
    }

    //CRUD
    val allUser:List<User>
        get() {
            val listUsers=ArrayList<User>()
            val selectQuery="SELECT * FROM $TABLE_NAME1"
            val db=this.writableDatabase
            val cursor=db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    val user= User()
                    user.id=cursor.getInt(cursor.getColumnIndex(COL_ID))
                    user.name=cursor.getString(cursor.getColumnIndex(COL_NAME))
                    user.email=cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                    user.password=cursor.getString(cursor.getColumnIndex(COL_PASSWORD))

                    listUsers.add(user)

                }while (cursor.moveToNext())

            }
            db.close()
            return listUsers
        }

    fun addUser(user:User){
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID,user.id)
        values.put(COL_NAME,user.name)
        values.put(COL_EMAIL,user.email)
        values.put(COL_PASSWORD,user.password)
        db.insert(TABLE_NAME1,null,values)
        //db.close()
    }

//    fun updateUser(user:User):Int{
//        val db=this.writableDatabase
//        val values=ContentValues()
//        values.put(COL_ID,user.id)
//        values.put(COL_NAME,user.name)
//        values.put(COL_EMAIL,user.email)
//        values.put(COL_PASSWORD,user.password)
//
//        return db.update(TABLE_NAME1,values,"$COL_ID=?", arrayOf(user.id.toString()))
//    }

    fun deleteUser(user:User){
        val db=this.writableDatabase

        db.delete(TABLE_NAME1,"$COL_ID=?", arrayOf(user.id.toString()))
        db.close()
    }


    /**
     * This method to check user exist or not
     * @return true/false
     */
    fun checkUser(email: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COL_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COL_EMAIL = ?"
        // selection argument
        val selectionArgs = arrayOf(email)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'xxxx@yyyy.zzz';
         */
        val cursor = db.query(
            TABLE_NAME1, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        //db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    /**
     * This method to check user exist or not
     * @return true/false
     */
    fun checkUser(email: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COL_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COL_EMAIL = ? AND $COL_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(email, password)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'xxxx@yyyy.zzz' AND user_password = 'xxx123';
         */
        val cursor = db.query(
            TABLE_NAME1, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        //db.close()
        if (cursorCount > 0)
            return true
        return false
    }


    //CRUD

//    val allFruit: List<Fruit>?
//        get() {
//            val listFruit=ArrayList<Fruit>()
//            val db=this.writableDatabase
//            var cursor = db.query(
//                    TABLE_NAME2, arrayOf(
//                    COL_NAME1,
//                    COL_PRICE,
//                    COL_QLT
//
//            ), COL_ID1 + " =?",null, null, null, null
//            )
//            if (cursor.moveToFirst()) {
//
//                do {
//                    val fruit= Fruit()
//                    fruit.id=cursor.getInt(cursor.getColumnIndex(COL_ID1))
//                    fruit.name=cursor.getString(cursor.getColumnIndex(COL_NAME1))
//                    fruit.price=cursor.getInt(cursor.getColumnIndex(COL_PRICE))
//                    fruit.qlt=cursor.getInt(cursor.getColumnIndex(COL_QLT))
//
//                    listFruit.add(fruit)
//
//                }while (cursor.moveToNext())
//
//            }
//
//            else
//                return null
//            db.close()
//            return listFruit
//        }

    fun readFruitData():MutableList<Fruit>{
        System.out.println("Hello from read fruit")
        var list:MutableList<Fruit> = ArrayList()
        val db=this.writableDatabase
        val query="Select * from " + TABLE_NAME2
        val cursor=db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {

                    var fruit= Fruit()
                    fruit.id=cursor.getString(cursor.getColumnIndex(COL_ID1)).toInt()
                    fruit.name=cursor.getString(cursor.getColumnIndex(COL_NAME1))
                    fruit.price=cursor.getString(cursor.getColumnIndex(COL_PRICE)).toInt()
                    fruit.qlt=cursor.getString(cursor.getColumnIndex(COL_QLT)).toInt()
                list.add(fruit)

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }


    //get all fruits by name
    fun getAllFruitByName():List<Fruit> {
            val listFruit=ArrayList<Fruit>()
            val selectQuery="SELECT * FROM $TABLE_NAME2"
            val db=this.writableDatabase
            val cursor=db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    val fruit= Fruit()
                    fruit.id=cursor.getInt(cursor.getColumnIndex(COL_ID1))
                    fruit.name=cursor.getString(cursor.getColumnIndex(COL_NAME1))
                    fruit.price=cursor.getInt(cursor.getColumnIndex(COL_PRICE))
                    fruit.qlt=cursor.getInt(cursor.getColumnIndex(COL_QLT))

                    listFruit.add(fruit)

                }while (cursor.moveToNext())

            }
//            db.close()
            return listFruit
        }

    //get a fruit by name
    fun getFruitByName(name:String?):MutableList<Fruit>{
            var listFruit:MutableList<Fruit> = ArrayList()
            val selectQuery= "SELECT name FROM " + TABLE_NAME2 + " where name like " + name
            val db=this.readableDatabase
            val cursor=db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    val fruit= Fruit()
                    fruit.id=cursor.getString(cursor.getColumnIndex(COL_ID1)).toInt()
                    fruit.name=cursor.getString(cursor.getColumnIndex(COL_NAME1))
                    fruit.price=cursor.getString(cursor.getColumnIndex(COL_PRICE)).toInt()
                    fruit.qlt=cursor.getString(cursor.getColumnIndex(COL_QLT)).toInt()

                    listFruit.add(fruit)

                }while (cursor.moveToNext())

            }

            cursor.close()
            db.close()
            return listFruit

    }


    fun readEmployeeData():MutableList<Employee>{
        System.out.println("Hello from read fruit")
        var list:MutableList<Employee> = ArrayList()
        val db=this.writableDatabase
        val query="Select * from " + TABLE_NAME3
        val cursor=db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {

                var employee= Employee()
                employee.id=cursor.getString(cursor.getColumnIndex(COL_ID1)).toInt()
                employee.name=cursor.getString(cursor.getColumnIndex(COL_NAME1))
                employee.salary=cursor.getString(cursor.getColumnIndex(COL_PRICE)).toInt()
                employee.phoneNumber=cursor.getString(cursor.getColumnIndex(COL_QLT)).toInt()
                list.add(employee)

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }


    fun addEmployee(employee:Employee){
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID2,employee.id)
        values.put(COL_NAME2,employee.name)
        values.put(COL_SALARY,employee.salary)
        values.put(COL_PHONENUMBER,employee.phoneNumber)
        db.insert(TABLE_NAME2,null,values)
//        db.close()
    }

    fun updateEmployee(employee:Employee):Int{
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID2,employee.id)
        values.put(COL_NAME2,employee.name)
        values.put(COL_SALARY,employee.salary)
        values.put(COL_PHONENUMBER,employee.phoneNumber)

        return db.update(TABLE_NAME3,values,"$COL_ID2=?", arrayOf(employee.id.toString()))
    }

    fun deleteEmployee(employee:Employee){
        val db=this.writableDatabase

        db.delete(TABLE_NAME3,"$COL_ID2=?", arrayOf(employee.id.toString()))
        db.close()
    }




    fun addFruit(fruit:Fruit){
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID1,fruit.id)
        values.put(COL_NAME1,fruit.name)
        values.put(COL_PRICE,fruit.price)
        values.put(COL_QLT,fruit.qlt)
        db.insert(TABLE_NAME2,null,values)
//        db.close()
    }

    fun updateFruit(fruit:Fruit):Int{
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COL_ID1,fruit.id)
        values.put(COL_NAME1,fruit.name)
        values.put(COL_PRICE,fruit.price)
        values.put(COL_QLT,fruit.qlt)

        return db.update(TABLE_NAME2,values,"$COL_ID1=?", arrayOf(fruit.id.toString()))
    }

    fun deleteFruit(fruit:Fruit){
        val db=this.writableDatabase

        db.delete(TABLE_NAME2,"$COL_ID1=?", arrayOf(fruit.id.toString()))
        db.close()
    }




}