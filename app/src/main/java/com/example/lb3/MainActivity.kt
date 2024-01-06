package com.example.lb3

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lb3.databinding.ActivityMainBinding

const val KEY_L = "login"
const val KEY_P = "password"
const val KEY_I = "id"
const val KEY_PR = "priority"
const val KEY_S = "spec"

class MainActivity : AppCompatActivity() {
    private val vb by viewBinding(
        ActivityMainBinding::bind,
        R.id.main_root
    )

    var sp : SharedPreferences? = null
    var log = ""
    var psw = ""
    var idn = 0
    var prior = 0
    var spcp = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getSharedPreferences("Table", Context.MODE_PRIVATE)

        log = sp?.getString(KEY_L, "").toString()
        psw = sp?.getString(KEY_P, "").toString()
        idn = sp?.getInt(KEY_I, 0) !!
        prior = sp?.getInt(KEY_PR, 0)!!
        spcp = sp?.getFloat(KEY_S, 0.0f)!!


        vb.loginEt.setText(log)
        vb.passwordEt.setText(psw)
        vb.idEt.setText(idn.toString())
        vb.priorityEt.setText(prior.toString())
        vb.specialEt.setText(spcp.toString())
    }

    fun SaveData (login: String, psw: String, id: Int, prior: Int, spec: Float){
        val editor = sp?.edit()
        editor?.putString(KEY_L, login)
        editor?.putString(KEY_P, psw)
        editor?.putInt(KEY_I, id)
        editor?.putInt(KEY_PR, prior)
        editor?.putFloat(KEY_S, spec)
        editor?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()

        SaveData(vb.loginEt.text.toString(),
            vb.passwordEt.text.toString(),
            vb.idEt.text.toString().toInt(),
            vb.priorityEt.text.toString().toInt(),
            vb.specialEt.text.toString().toFloat(),
            )
    }

    override fun onPause() {
        super.onPause()
        SaveData(vb.loginEt.text.toString(),
            vb.passwordEt.text.toString(),
            vb.idEt.text.toString().toInt(),
            vb.priorityEt.text.toString().toInt(),
            vb.specialEt.text.toString().toFloat(),
        )
    }

}