package com.marwaeltayeb.banking.ui.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.databinding.ActivityDetailsBinding
import com.marwaeltayeb.banking.ui.transfer.TransferActivity
import com.marwaeltayeb.banking.util.Const.Companion.CLIENT

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var currentClient: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (intent.getParcelableExtra(CLIENT) as Client?)?.let {
            currentClient = it
        }

        binding.txtName.text = currentClient.name
        binding.txtEmail.text = currentClient.email
        binding.txtPhoneNumber.text = currentClient.phoneNumber
        binding.txtBalance.text = currentClient.balance.toString()

        binding.btnTransferMoney.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog(){
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        val editAmount = EditText(this)
        alert.setTitle("Enter Amount")
        alert.setView(editAmount)

        alert.setPositiveButton("Send") { _, _ ->
            val amountString: String = editAmount.text.toString()

            if (amountString.isNotEmpty()) {
                Toast.makeText(applicationContext, amountString, Toast.LENGTH_SHORT).show()
                val amount: Double = amountString.toDouble()
                intent = Intent(this, TransferActivity::class.java)
                intent.putExtra("amount", amount)
                startActivity(intent)
            }
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        alert.show()
    }
}