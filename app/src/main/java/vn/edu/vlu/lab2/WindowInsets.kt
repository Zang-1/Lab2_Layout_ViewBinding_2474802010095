package vn.edu.vlu.lab2

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * App chạy edge-to-edge (bắt buộc từ targetSdk 35), nên phải tự chừa chỗ cho
 * thanh trạng thái, thanh điều hướng và bàn phím, nếu không nội dung sẽ bị che.
 */
fun View.applySystemBarsPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val bars = insets.getInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
        )
        v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
        insets
    }
}
