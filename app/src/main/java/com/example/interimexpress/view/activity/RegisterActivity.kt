package com.example.interimexpress.view.activity


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.hbb20.CountryCodePicker
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var ccp: CountryCodePicker
    private lateinit var editTextPhoneNumber: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nationalities = resources.getStringArray(R.array.nationalities)
        val nationalitySpinner = findViewById<Spinner>(R.id.spinnerNationality)
        val adapter = object : ArrayAdapter<String>(this, R.layout.spinner_item, nationalities) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
                val globeImage = view.findViewById<ImageView>(R.id.globe_image)
                val nationalityText = view.findViewById<TextView>(R.id.nationality_text)

                nationalityText.text = nationalities[position]

                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_item, parent, false)
                val globeImage = view.findViewById<ImageView>(R.id.globe_image)
                val nationalityText = view.findViewById<TextView>(R.id.nationality_text)

                nationalityText.text = nationalities[position]

                return view
            }
        }

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        nationalitySpinner.adapter = adapter
        nationalitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Vous pouvez ajouter des actions à effectuer lorsqu'une nationalité est sélectionnée.
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Vous pouvez ajouter des actions à effectuer lorsqu'aucune nationalité n'est sélectionnée.
            }
        }



        ccp = findViewById(R.id.countryCodePicker)

        /* Ici on traduis en français les phrases par defaut de la libraire CountryCodePickerProject
        * https://github.com/hbb20/CountryCodePickerProject/wiki/Custom-Dialog-Title--%7C-Search-Hint-%7C-Empty-result-ACK
        * */
        ccp.setCustomDialogTextProvider(object : CountryCodePicker.CustomDialogTextProvider {
            override fun getCCPDialogTitle(language: CountryCodePicker.Language, defaultTitle: String): String {
                return when (language) {
                    CountryCodePicker.Language.FRENCH -> "Séléctionner un pays"
                    else -> defaultTitle
                }
            }

            override fun getCCPDialogSearchHintText(language: CountryCodePicker.Language, defaultSearchHintText: String): String {
                return when (language) {
                    CountryCodePicker.Language.FRENCH -> "Rechercher"
                    else -> defaultSearchHintText
                }
            }

            override fun getCCPDialogNoResultACK(language: CountryCodePicker.Language, defaultNoResultACK: String): String {
                return defaultNoResultACK
            }
        })

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)

        ccp.registerCarrierNumberEditText(editTextPhoneNumber)


        val datePickerLayout = findViewById<RelativeLayout>(R.id.datePickerLayout)

        datePickerLayout.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, R.style.CustomDatePickerDialog, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay}-${selectedMonth + 1}-${selectedYear}"
                val hintText = findViewById<TextView>(R.id.hintText)
                hintText.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }



    }


}