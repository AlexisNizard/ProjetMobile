package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.adapter.OtpEditText
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Employeur
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class ConfirmationInscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_inscription)


        var employeur = intent.getSerializableExtra("Employeur") as Employeur
        var verificationCode = intent.getStringExtra("VerificationCode")

        val submit = findViewById<Button>(R.id.submit);
        submit.setOnClickListener {
            val enteredCode = findViewById<OtpEditText>(R.id.et_otp).text.toString()
            println("Notre input : $enteredCode et le bon code de vérification : $verificationCode")
            if (enteredCode == verificationCode) {
                EmployeurController().insertEmployeur(employeur)
                val intent = Intent(this, EmployeurSlidesActivity::class.java)
                Toast.makeText(this, "Votre compte est activé.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Code de vérification incorrect, veuillez réessayer.", Toast.LENGTH_SHORT).show()
            }
        }

        val nouveauCode = findViewById<TextView>(R.id.nouveauCode)
        nouveauCode.setOnClickListener {

            verificationCode = generateVerificationCode()
            sendEmail( employeur?.adresseMail.toString(), "InterimExpress : Nouveau code de vérification", "Bonjour, \nvotre nouveau code de vérification est : $verificationCode\n\nInterimEpress.")
            Toast.makeText(this, "Un nouveau code a été envoyé à votre adresse e-mail.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun generateVerificationCode(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..5)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun sendEmail(to: String, subject: String, message: String) {
        Thread {
            try {
                val properties = Properties()
                properties.put("mail.smtp.host", "smtp.gmail.com")
                properties.put("mail.smtp.port", "465")
                properties.put("mail.smtp.auth", "true")
                properties.put("mail.smtp.socketFactory.port", "465")
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")

                val session = Session.getInstance(properties, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication("interimexpressmail@gmail.com", "bidqiljxrnpcfyjt")
                    }
                })

                val mimeMessage = MimeMessage(session)
                mimeMessage.setFrom(InternetAddress("interimexpressmail@gmail.com"))
                mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(to))
                mimeMessage.subject = subject
                mimeMessage.setText(message)

                Transport.send(mimeMessage)
            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }.start()
    }
}