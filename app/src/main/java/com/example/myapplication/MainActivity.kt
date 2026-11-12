package com.example.randomadvice

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.randomadvice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adviceList = listOf(
        "Начни день с улыбки — она заразительна.",
        "Сегодня отличный день, чтобы выучить что-то новое.",
        "Сделай паузу. Дыши. Всё идёт своим чередом.",
        "Позвони тому, с кем давно не общался.",
        "Выпей стакан воды прямо сейчас.",
        "Прогуляйся на свежем воздухе 15 минут.",
        "Запиши три вещи, за которые ты благодарен.",
        "Скажи «нет» тому, что тебе не по душе.",
        "Не сравнивай свой путь с чужим.",
        "Заверши сегодня одно дело, которое давно откладывал.",
        "Улыбнись незнакомцу — это бесплатно.",
        "Прочитай 10 страниц хорошей книги.",
        "Позволь себе отдохнуть без чувства вины.",
        "Слушай больше, чем говори.",
        "Сделай комплимент коллеге или другу.",
        "Попробуй сегодня новый рецепт.",
        "Убери одну вещь, которая тебе не нужна.",
        "Напиши план на завтра — вечером будет легче.",
        "Проведи 30 минут без телефона.",
        "Подумай: «Что сделает меня счастливее прямо сейчас?»"
    )

    private var lastIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNewAdvice.setOnClickListener { showRandomAdvice() }
        binding.btnShare.setOnClickListener { shareAdvice() }

        if (savedInstanceState != null) {
            binding.tvAdvice.text = savedInstanceState.getString(KEY_ADVICE)
        } else {
            showRandomAdvice()
        }
    }

    private fun showRandomAdvice() {
        var index: Int
        do {
            index = Random.nextInt(adviceList.size)
        } while (index == lastIndex && adviceList.size > 1)

        lastIndex = index
        binding.tvAdvice.text = adviceList[index]

        val anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        anim.duration = 400
        binding.tvAdvice.startAnimation(anim)
    }

    private fun shareAdvice() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, binding.tvAdvice.text.toString())
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_ADVICE, binding.tvAdvice.text.toString())
    }

    companion object {
        private const val KEY_ADVICE = "advice"
    }
}