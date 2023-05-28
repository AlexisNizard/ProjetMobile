package com.example.interimexpress.adapter
import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet


class SiretEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : OtpEditText(context, attrs, defStyleAttr) {

    override var numChars = 17 // 14 digits + 3 spaces

    override var space = 8 // Space between lines
    override var maxLength = 17 // Limit text length to 14 digits + 3 spaces

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            this@SiretEditText.removeTextChangedListener(this)
            var text = this@SiretEditText.text.toString()
            text = text.replace(" ", "")
            if (text.isNotEmpty()) {
                val builder = StringBuilder(text)
                var offset = 0 // add an offset for each space that we insert
                for (i in 2 until text.length + offset step 4) {
                    if(text.length + offset - i > 1) {
                        builder.insert(i + 1, " ")
                        offset++
                    }
                }
                if(text.length + offset - 10 > 1) {
                    if (text.length + offset - 10 < 3){
                        builder.insert(10 + 1, " ")
                    }

                }
                text = builder.toString()
                if (text.length <= 17) { // 14 digits + 3 spaces
                    this@SiretEditText.setText(text)
                    this@SiretEditText.setSelection(text.length)
                }
            }
            this@SiretEditText.addTextChangedListener(this)
        }
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

        // Text Width
        val text: Editable? = text
        val textLength = text?.length
        val nonNullTextLength = textLength ?: 0
        val textWidths = FloatArray(nonNullTextLength)
        if (text != null && nonNullTextLength > 0) {
            paint.getTextWidths(text, 0, nonNullTextLength, textWidths)
        }

        for (i in 0 until numChars) {
            // Draw lines for digits, not spaces
            if ((i + 1) % 4 != 0 || i > 11) {
                canvas.drawLine(startX.toFloat(), bottom.toFloat(), startX + charSize, bottom.toFloat(), linesPaint)
            }

            // Draw text if it exists at this position
            if (text != null && i < text.length && textWidths.isNotEmpty()) {
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


    init {
        this.addTextChangedListener(textWatcher)
    }
}
