package com.leevinapp.chartdeom


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.leevinapp.chartdeom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnCreateChart.setOnClickListener {
            try {
                viewBinding.chartView.UA =  viewBinding.etUa.text.toString().toFloat()
                viewBinding.chartView.UB =  viewBinding.etUb.text.toString().toFloat()
                viewBinding.chartView.UC =  viewBinding.etUc.text.toString().toFloat()

                viewBinding.chartView.IA =  viewBinding.etIa.text.toString().toFloat()
                viewBinding.chartView.IB =  viewBinding.etIb.text.toString().toFloat()
                viewBinding.chartView.IC =  viewBinding.etIc.text.toString().toFloat()

                viewBinding.chartView.angelA =  viewBinding.etAngelA.text.toString().toFloat()
                viewBinding.chartView.angelB =  viewBinding.etAngelB.text.toString().toFloat()
                viewBinding.chartView.angelC =  viewBinding.etAngelC.text.toString().toFloat()

                viewBinding.chartView.calculateData()
                viewBinding.chartView.invalidate()

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "数据错误, 请检查", Toast.LENGTH_SHORT).show()
            }
        }
    }
}