package com.luiztm.customprogressbar

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.custom_seek_bar


class MainActivity : AppCompatActivity() {

    private lateinit var progressItemList: MutableList<ProgressBarItem>
    private lateinit var mProgressItem: ProgressBarItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDataToSeekbar()
    }

    fun initDataToSeekbar() {
//        custom_seek_bar.requestLayout()

        progressItemList = mutableListOf()
        // red span
        mProgressItem = ProgressBarItem(
            color = Color.RED,
            valor = 150.0f
        )
        progressItemList.add(mProgressItem)
        // blue span
        mProgressItem = ProgressBarItem(
            color = Color.BLUE,
            valor = 250.0f
        )
        progressItemList.add(mProgressItem)
        // green span
        mProgressItem = ProgressBarItem(
            color = Color.GREEN,
            valor = 350.0f
        )
        progressItemList.add(mProgressItem)

        //white span
        mProgressItem = ProgressBarItem(
            color = Color.YELLOW,
            valor = 250.0f
        )
//        progressItemList.add(mProgressItem)

        val sortedXx:List<ProgressBarItem> = progressItemList.sortedByDescending { it.valor }

//        custom_seek_bar.initData(1000.toFloat(), sortedXx)
    }
}
