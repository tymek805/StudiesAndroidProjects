package com.tymek805.exercise_02

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tymek805.exercise_02.databinding.ActivityLeftBinding


class LeftActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeftBinding
    private var isBold: Boolean = false
    private var isItalic: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.textViewSize)
        registerForContextMenu(binding.textViewType)

        val backButton: Button = binding.backButton
        backButton.setOnClickListener { _ ->
            onBackPressed()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when (v) {
            binding.textViewSize -> {
                menu!!.setHeaderTitle("Font Size Menu")
                menuInflater.inflate(R.menu.cmenu_fontsize, menu)
            }
            binding.textViewType -> {
                menu!!.setHeaderTitle("Font Type Menu")
                menuInflater.inflate(R.menu.cmenu_fonttype, menu)
                menu.findItem(R.id.type_option_1).setChecked(isBold)
                menu.findItem(R.id.type_option_2).setChecked(isItalic)
            }
            else -> menuInflater.inflate(R.menu.menu_main, menu)
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.size_option_1 -> binding.textViewSize.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            R.id.size_option_2 -> binding.textViewSize.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F)
            R.id.size_option_3 -> binding.textViewSize.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
            R.id.type_option_1 -> {
                isBold = !isBold
                applyTypeface()
            }
            R.id.type_option_2 -> {
                isItalic = !isItalic
                applyTypeface()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun applyTypeface() {
        when {
            isBold && isItalic -> binding.textViewType.setTypeface(null, Typeface.BOLD_ITALIC)
            isBold -> binding.textViewType.setTypeface(null, Typeface.BOLD)
            isItalic -> binding.textViewType.setTypeface(null, Typeface.ITALIC)
            else -> binding.textViewType.setTypeface(null, Typeface.NORMAL)
        }
    }
}