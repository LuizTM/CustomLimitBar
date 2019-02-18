package com.luiztm.customprogressbar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

class CustomLimitBar2 : FrameLayout {

    var progressBarWidth = 0
    var progressBarHeight = 0
    var mProgressItemsList = listOf<ProgressBarItem>()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.v("Chart onMeasure w", View.MeasureSpec.toString(widthMeasureSpec))
        Log.v("Chart onMeasure h", View.MeasureSpec.toString(heightMeasureSpec))

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        progressBarWidth = measureDimension(desiredWidth, widthMeasureSpec)
        progressBarHeight = measureDimension(desiredHeight, heightMeasureSpec)

        setMeasuredDimension(progressBarWidth, progressBarHeight)
    }

    fun initData(progressItemsList: List<ProgressBarItem>){
        mProgressItemsList = progressItemsList
    }

    fun getPercentage(valorFinal: Float, valorTotal: Float): Float {
        return 100 - ((((valorFinal - valorTotal) / valorTotal) * 100) * -1)
    }

    fun initView() {

        for (progressLimitItem in mProgressItemsList){

        }

        val imageView = ImageView(context).apply {
            layoutParams = LayoutParams(progressBarWidth/2, progressBarHeight)
            setImageResource(R.drawable.shape_limit)
        }

        val layoutParamsImg = LayoutParams(progressBarWidth/3, progressBarHeight)
        layoutParamsImg.setMargins(progressBarWidth/2 - 35, 0,0,0)
        val imageView2 = ImageView(context).apply {
            layoutParams = layoutParamsImg
            setImageResource(R.drawable.shape_limit_blue)
        }

        addView(imageView2)
        addView(imageView)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        initView()
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }

        if (result < desiredSize) {
            Log.e("ChartView", "The view is too small, the content might get cut")
        }
        return result
    }
}