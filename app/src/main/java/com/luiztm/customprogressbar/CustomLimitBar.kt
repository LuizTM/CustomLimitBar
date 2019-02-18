package com.luiztm.customprogressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomLimitBar : View {
    var mProgressItemsList = listOf<ProgressBarItem>()
    var mValorTotal: Float = 0.0f
    var progressPaint = Paint()
    var progressRect = RectF()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun initData(valorTotal: Float, progressItemsList: List<ProgressBarItem>) {
        mProgressItemsList = progressItemsList
        mValorTotal = valorTotal
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        if (mProgressItemsList.isNotEmpty()) {
            val progressBarWidth = width
            val progressBarHeight = height
            var lastProgressX = 0.0f
            var progressItemWidth: Float
            var progressItemRight: Int

            for (progressItem in mProgressItemsList) {
                progressPaint.color = progressItem.color

                progressItemWidth = getPercentage(progressItem.valor, mValorTotal) * progressBarWidth / 100

//                if (mProgressItemsList.indexOf(progressItem) == mProgressItemsList.size - 1
//                    && progressItemRight != progressBarWidth
//                ) {
//                    progressItemRight = progressBarWidth
//                }

                progressRect.set(
                    0.0f, 0.0f,
                    progressItemWidth, height.toFloat()
                )

                canvas?.drawRoundRect(progressRect, 50.0f, 50.0f, progressPaint)
                lastProgressX = progressItemWidth
            }
            super.onDraw(canvas)
        }
    }

    fun getPercentage(valorFinal: Float, valorTotal: Float): Float {
        return 100 - ((((valorFinal - valorTotal) / valorTotal) * 100) * -1)
    }
}
