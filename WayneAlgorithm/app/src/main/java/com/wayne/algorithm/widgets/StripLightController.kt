package com.wayne.algorithm.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.wayne.algorithm.R
import com.wayne.algorithm.ktxs.wayneLogd

class StripLightController(ctx:Context, attrs: AttributeSet):View(ctx, attrs) {

    /**
     * properties
     */
    private var wickCount:Int = 10
    private var countInRow:Int = 4
    private var wickHeightRatio:Float = .1f //represents the ratio of wick's height to width of every rect.


    /**
     * paints
     */
    private var wickPaints:Array<Paint> = emptyArray()
    private var borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xffffff
    }

    /**
     * paths
     */
    private var wickPaths: Array<Path> = emptyArray()
    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.StripLightController, 0, 0).apply {
            try{
                wickCount = getInt(R.styleable.StripLightController_wicksCount, 0)
                countInRow = getInt(R.styleable.StripLightController_maxCountEveryRow, 4)
                wickPaints = Array(wickCount){
                    Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = ContextCompat.getColor(context, R.color.purple_200)
                        style = Paint.Style.STROKE
                        strokeWidth = 10F
                    }
                }
                wickPaths = Array(wickCount){
                    Path()
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
        wayneLogd(" received the size change w-> $w  h -> $h")
        val ROW = wickCount / countInRow + 1 // represents how many rows we are going to have 我们有多少行需要绘制
        val R_WIDTH = w / countInRow// represents the width for every single rect in drawing 每个小方块的长度
        val R_HEIGHT = h / ROW// represents the height for every single rect in drawing 每个小方块的高度
        val S_HEIGHT = R_WIDTH * wickHeightRatio //represents the height for strip string. 灯条的高度(厚度)
        val C_RATIO= S_HEIGHT / 2f //represents the ratio for every corner. 每个拐弯或者起始/终结位置的圆角半径
        val GAP_BETWEEN = C_RATIO //represents the gap between every wick. 灯芯之间的间隔
        var corner = 0//represents the index of corner we have passed by during drawing 表示在绘制过程中我们已经经过了第几个拐弯

        var cRLeft = 0
        var cRTop = 0
        var cRRight = R_WIDTH
        var cRBottom = R_HEIGHT
        wickPaints.forEachIndexed { i, p ->
            if(i == 0){
                //beginning
                with(wickPaths[i]){
                    moveTo(C_RATIO, 0f)
                    wayneLogd("line to ${cRRight - C_RATIO - GAP_BETWEEN/2}")
                    lineTo(cRRight - C_RATIO - GAP_BETWEEN/2, 0f)
                    lineTo(S_HEIGHT, 0f)
//                    arcTo(0f, 0f, 2 * C_RATIO, 2 * C_RATIO, -90f, -180f, false)//sweep corner
                    lineTo(cRRight - C_RATIO - GAP_BETWEEN/2, 0f)

                    close()

                }
            }else if((i + 1 + corner) % countInRow  == 0){
                //corner
                corner ++
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(wickPaths[0], wickPaints[0])
    }
}