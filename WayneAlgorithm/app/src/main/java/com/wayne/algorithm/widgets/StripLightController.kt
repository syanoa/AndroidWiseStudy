package com.wayne.algorithm.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.wayne.algorithm.R

class StripLightController(ctx:Context, attrs: AttributeSet):View(ctx, attrs) {

    /**
     * properties
     */
    private var wickCount:Int = 10
    private var countInRow:Int = 4


    /**
     * paints
     */
    private var wickPaints:Array<Paint> = emptyArray()
    private var borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xffffff
    }
    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.StripLightController, 0, 0).apply {
            try{
                wickCount = getInt(R.styleable.StripLightController_wicksCount, 0)
                countInRow = getInt(R.styleable.StripLightController_maxCountEveryRow, 4)
                wickPaints = Array(wickCount){
                    Paint(Paint.ANTI_ALIAS_FLAG)
                }
            }finally {
                recycle()
            }
        }
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        setMeasuredDimension()
        // Try for a width based on our minimum
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = View.resolveSizeAndState(minw, widthMeasureSpec, 1)

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        val minh: Int = View.MeasureSpec.getSize(w)  + paddingBottom + paddingTop
        val h: Int = View.resolveSizeAndState(minh, heightMeasureSpec, 0)

        setMeasuredDimension(w, h)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val r = wickCount / countInRow + 1
        var corner = 0
        wickPaints.forEachIndexed { i, p ->
            if(i == 0){
                //beginning

            }else if((i + 1 + corner) % countInRow  == 0){
                //corner
                corner ++
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {

        }
    }
}