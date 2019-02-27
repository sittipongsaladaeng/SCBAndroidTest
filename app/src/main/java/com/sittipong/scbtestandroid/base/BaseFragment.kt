package com.sittipong.scbtestandroid.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.sittipong.scbtestandroid.activity.main.MainActivity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.sittipong.scbtestandroid.R


open class BaseFragment : Fragment() {

    val LIST = "0"
    val FAVORITE = "1"
    val REFRESH = "2"

    val items = arrayOf("Price low to high", "Price high to low", "Rating 5-1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            ?.replace(R.id.container, fragment, null)
            ?.commit()
    }

    fun showAlertDialog(act: AppCompatActivity, msg: String) {
        val builder = AlertDialog.Builder(act)
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}



