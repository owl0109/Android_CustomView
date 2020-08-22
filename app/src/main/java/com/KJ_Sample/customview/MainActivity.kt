package com.KJ_Sample.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var indicator:MyCustomView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indicator = findViewById(R.id.indicator)
        findViewById<Button>(R.id.button).setOnClickListener(onClick())
    }

    //タップ時の処理定義
    private inner class onClick():View.OnClickListener{
        override fun onClick(p0: View?) {
            var selected = indicator.getSelected()
            //selectが2の時,0に戻る
            if(selected == 2){
                selected = 0
            }else{
                selected += 1
            }
            Log.d("[Log_Onclick]","selected = ${selected}")
            indicator.setSelected(selected)
        }

    }
}