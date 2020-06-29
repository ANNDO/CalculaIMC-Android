package br.com.leonardo.calculaimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Cria delay de 4500 milisegundos para a exibição da activity Splash
        Handler().postDelayed({
            val intentlogin = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intentlogin)
            finish()
        },4500)
        txvSplash.text = "Calculadora de Índice de Massa Corporal (IMC) válida para adultos entre 20 e 59 anos."
    }
}
