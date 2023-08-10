package com.example.qrscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.qrscanner.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener(View.OnClickListener {
            var intent: IntentIntegrator = IntentIntegrator(this)
            intent.setPrompt("Scan a BarCode or QR code")
//            intent.setOrientationLocked(false)
//            intent.setOrientationLocked(true)
            intent.initiateScan()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var intentResult : IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (intentResult!=null){
            if (intentResult.contents==null){
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
            }else{
                binding.data.text=intentResult.contents
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}