package br.com.leonardo.calculaimc

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.math.RoundingMode
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Declaração de variáveis
        val dadosdeusuario = getSharedPreferences("dados-usuario", Context.MODE_PRIVATE)
        val editordedados = dadosdeusuario.edit()
        var altura: Float = skbAltura.progress.toFloat()
        var peso: Float = skbPeso.progress.toFloat()
        var imc: Float


        skbAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                altura = progress.times(0.01).toFloat() + 1
                txvAltura.text ="%.2f".format(altura) + "m"


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

    } )
        skbPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                peso = (progress*0.1).toFloat() +40
                txvPeso.text = "" + "%.1f".format(peso) + "kg" //
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


        btnMostrarIMC.setOnClickListener{
            editordedados.putFloat("altura",altura).apply()
            editordedados.putFloat("peso",peso).apply()
            imc = (peso/(altura*altura)).toFloat()
            editordedados.putFloat("imc",imc).apply()

            // Definição da variável "resultado" com seu valor sendo condicionado pelo IMC (Tabela do Ministério da Saúde)
            val resultado = when (imc){
                in 0.0..18.49 -> "Baixo peso."
                in 18.50 .. 24.99 ->"Peso adequado."
                in 25.0..29.99 -> "Sobrepeso."
                else -> "Obesidade."}

            // Cria Alert para mostrar informações ao usuário.
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Cadastro realizado com sucesso")
                .setMessage("Seu IMC é de " + "%.2f".format(imc) + ".\n" + "Segundo o Ministério da Saúde isso indica que você está com: " + resultado)
                .setPositiveButton("Ok", null)
                .setCancelable(false)
                .create()
                .show()


        }


    }



}
