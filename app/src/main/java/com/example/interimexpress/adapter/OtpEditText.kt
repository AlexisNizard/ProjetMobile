package com.example.interimexpress.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.interimexpress.R
import kotlin.math.roundToInt

class OtpEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var space = 24 //24 dp by default, space between the lines
    private var numChars = 5
    private var lineSpacing = 8 //8dp by default, height of the text from our lines
    private var maxLength = 5
    private var lineStroke = 2f
    private val linesPaint: Paint
    private var clickListener: OnClickListener? = null

    init {
        val multi = context.resources.displayMetrics.density
        lineStroke *= multi
        linesPaint = Paint(paint)
        linesPaint.strokeWidth = lineStroke
        linesPaint.color = ContextCompat.getColor(context, R.color.colorPrimary) //replace with your color
        setBackgroundResource(0)
        space = (multi * space).roundToInt()
        lineSpacing = (multi * lineSpacing).roundToInt()
        numChars = maxLength

        super.setOnClickListener {
            // When tapped, move cursor to end of text.
            text?.let {
                setSelection(it.length)
            }
            clickListener?.onClick(it)
        }

    }

    override fun setOnClickListener(l: OnClickListener?) {
        clickListener = l
    }

    override fun onDraw(canvas: Canvas) {
        val availableWidth = width - paddingRight - paddingLeft
        val charSize: Float = if (space < 0) {
            (availableWidth / (numChars * 2 - 1)).toFloat()
        } else {
            (availableWidth - (space * (numChars - 1))).toFloat() / numChars
        }

        var startX = paddingLeft
        val bottom = height - paddingBottom

        //Text Width
        val text: Editable? = text
        val textLength = text?.length
        val nonNullTextLength = textLength ?: 0
        val textWidths = FloatArray(nonNullTextLength)
        if (text != null && nonNullTextLength > 0) {
            paint.getTextWidths(text, 0, nonNullTextLength, textWidths)
        }

        for (i in 0 until numChars) {
            canvas.drawLine(startX.toFloat(), bottom.toFloat(), startX + charSize, bottom.toFloat(), linesPaint)
            if (text != null && text.length > i && textWidths.isNotEmpty()) {
                val middle = startX + charSize / 2
                canvas.drawText(text, i, i + 1, middle - textWidths[0] / 2, bottom - lineSpacing.toFloat(), paint)
            }
            startX += if (space < 0) {
                (charSize * 2).toInt()
            } else {
                (charSize + space).toInt()
            }
        }


    }
}
