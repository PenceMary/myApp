package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val cards = listOf("3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "🃏")
    private val cardCounts = mutableMapOf<String, Int>()
    private val cardTextViews = mutableMapOf<String, TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化每张牌的计数
        cards.forEach { card ->
            cardCounts[card] = 4
        }

        // 绑定按钮和TextView，并设置点击事件
        cards.forEachIndexed { index, card ->
            val buttonId = resources.getIdentifier("button_$index", "id", packageName)
            val textViewId = resources.getIdentifier("text_$index", "id", packageName)

            val button = findViewById<Button>(buttonId)
            val textView = findViewById<TextView>(textViewId)

            cardTextViews[card] = textView
            textView.text = cardCounts[card].toString()

            button.setOnClickListener {
                val currentCount = cardCounts[card] ?: 4
                if (currentCount > 0) {
                    cardCounts[card] = currentCount - 1
                    textView.text = cardCounts[card].toString()
                }
            }
        }

        // 设置Reset按钮
        val resetButton = findViewById<Button>(R.id.button_reset)
        resetButton.setOnClickListener {
            cards.forEach { card ->
                cardCounts[card] = 4
                cardTextViews[card]?.text = "4"
            }
        }
    }
}
