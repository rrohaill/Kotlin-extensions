package io.eliq.core.utilities.extensions

import android.os.Build
import android.text.InputFilter
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatSpinner
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText
import io.eliq.core.R
import kotlin.math.absoluteValue

fun MaterialRadioButton.updateTextAppearance() {
    val styleId = if (isChecked) R.style.eliqBodyRegularText_Bold else R.style.eliqBodyRegularText

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextAppearance(styleId)
    } else {
        @Suppress("DEPRECATION")
        setTextAppearance(context, styleId)
    }
}

fun View.visibleIf(condition: Boolean?) {
    val cond = condition ?: false
    if (cond && visibility == View.VISIBLE || !cond && visibility == View.GONE) return
    visibility = if (cond) View.VISIBLE else View.GONE
}

fun View.hideIf(condition: Boolean?) {
    val cond = condition ?: false
    if (cond && visibility == View.VISIBLE || !cond && visibility == View.INVISIBLE) return
    visibility = if (cond) View.VISIBLE else View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.remove() {
    visibility = View.GONE
}

fun ViewGroup.visibleIf(condition: Boolean?) {
    val cond = condition ?: false
    if (cond && visibility == ViewGroup.VISIBLE || !cond && visibility == ViewGroup.GONE) return
    visibility = if (cond) ViewGroup.VISIBLE else ViewGroup.GONE
}

fun ViewGroup.hideIf(condition: Boolean?) {
    val cond = condition ?: true
    if (cond && visibility == View.INVISIBLE || !cond && visibility == View.VISIBLE) return
    visibility = if (cond) View.INVISIBLE else View.VISIBLE
}

fun ViewGroup.show() {
    visibility = View.VISIBLE
}

fun ViewGroup.hide() {
    visibility = View.INVISIBLE
}

fun ViewGroup.remove() {
    visibility = View.GONE
}

fun AppCompatSpinner.alignDropDownWidth() {

    val customMargin = resources.getDimensionPixelSize(R.dimen.margin_standard) * 2
    dropDownWidth = resources.displayMetrics.widthPixels - customMargin

    post {
        val offset = -x.absoluteValue.toInt()
        dropDownHorizontalOffset =
            if (dropDownHorizontalOffset > offset) offset else dropDownHorizontalOffset
    }
}

// extension function to set edit text maximum length

fun TextInputEditText.setMaxLength(maxLength: Int) {

    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))

}

// Set spinner focusable to rotate arrow
fun AppCompatSpinner.setSpinnerFocusable() {
    isFocusableInTouchMode = true

    onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            if (windowToken != null) {
                performClick()

                viewTreeObserver?.addOnWindowFocusChangeListener(object :
                    ViewTreeObserver.OnWindowFocusChangeListener {
                    override fun onWindowFocusChanged(hasFocus: Boolean) {
                        if (hasFocus) {
                            clearFocus()
                            viewTreeObserver?.removeOnWindowFocusChangeListener(this)
                        }
                    }
                })
            }
        }
    }
}