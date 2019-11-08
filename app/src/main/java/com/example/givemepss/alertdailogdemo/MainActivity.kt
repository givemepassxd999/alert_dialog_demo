package com.example.givemepss.alertdailogdemo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*




class MainActivity : AppCompatActivity() {
    private lateinit var lunch: List<String>
    private val buttonListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.normal_dialog -> normalDialogEvent()
            R.id.list_dialog -> listDialogEvent()
            R.id.single_choice -> singleDialogEvent()
            R.id.multi_check -> multiDialogEvent()
            R.id.custom_dialog -> customDialogEvent()
        }
    }
    private var singleChoiceIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        setButtonEvent()
    }

    private fun initData() {
        lunch = listOf(getString(R.string.lunch1),
                getString(R.string.lunch2),
                getString(R.string.lunch3),
                getString(R.string.lunch4),
                getString(R.string.lunch5),
                getString(R.string.lunch6))
    }

    private fun setButtonEvent() {
        normal_dialog.setOnClickListener(buttonListener)
        list_dialog.setOnClickListener(buttonListener)
        single_choice.setOnClickListener(buttonListener)
        multi_check.setOnClickListener(buttonListener)
        custom_dialog.setOnClickListener(buttonListener)
    }

    private fun normalDialogEvent() {
        AlertDialog.Builder(this@MainActivity)
                .setTitle(R.string.lunch_time)
                .setMessage(R.string.want_to_eat)
                .setPositiveButton(R.string.ok) { _, _ -> Toast.makeText(applicationContext, R.string.gogo, Toast.LENGTH_SHORT).show() }
                .setNegativeButton(R.string.wait_a_minute) { _, _ -> Toast.makeText(applicationContext, R.string.i_am_hungry, Toast.LENGTH_SHORT).show() }
                .setNeutralButton(R.string.not_hungry) { _, _ -> Toast.makeText(applicationContext, R.string.diet, Toast.LENGTH_SHORT).show() }
                .show()
    }

    private fun customDialogEvent() {
        val item = LayoutInflater.from(this@MainActivity).inflate(R.layout.item_layout, null)
        AlertDialog.Builder(this@MainActivity)
                .setTitle(R.string.input_ur_name)
                .setView(item)
                .setPositiveButton(R.string.ok) { _, _ ->
                    val editText = item.findViewById(R.id.edit_text) as EditText
                    val name = editText.text.toString()
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(applicationContext, R.string.input_ur_name, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, getString(R.string.hi) + name, Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
    }

    private fun listDialogEvent() {
        AlertDialog.Builder(this@MainActivity)
                .setItems(lunch.toTypedArray()) { _, which ->
                    val name = lunch[which]
                    Toast.makeText(applicationContext, getString(R.string.u_eat) + name, Toast.LENGTH_SHORT).show()
                }
                .show()
    }

    private fun singleDialogEvent() {
        AlertDialog.Builder(this@MainActivity)
                .setSingleChoiceItems(lunch.toTypedArray(), singleChoiceIndex
                ) { _, which -> singleChoiceIndex = which }
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    Toast.makeText(this@MainActivity, "你選擇的是" + lunch[singleChoiceIndex], Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .show()
    }

    private fun multiDialogEvent() {
        val checkedStatusList = ArrayList<Boolean>()
        for (s in lunch) {
            checkedStatusList.add(false)
        }
        AlertDialog.Builder(this@MainActivity)
                .setMultiChoiceItems(lunch.toTypedArray(), BooleanArray(lunch.size)
                ) { _, which, isChecked -> checkedStatusList[which] = isChecked }
                .setPositiveButton(R.string.ok) { _, _ ->
                    val sb = StringBuilder()
                    var isEmpty = true
                    checkedStatusList.forEachIndexed { index, b ->
                        if (b) {
                            sb.append(lunch[index])
                            sb.append(" ")
                            isEmpty = false
                        }
                    }
                    if (!isEmpty) {
                        Toast.makeText(this@MainActivity, "你選擇的是$sb", Toast.LENGTH_SHORT).show()
                        for (s in lunch) {
                            checkedStatusList.add(false)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "請勾選項目", Toast.LENGTH_SHORT).show()
                    }
                }
                .show()

    }
}
