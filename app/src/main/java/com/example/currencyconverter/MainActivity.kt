package com.example.currencyconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Taux fixes pour 1 EUR
    private val taux = mapOf(
        "EUR" to 1.0,
        "USD" to 1.17,
        "TND" to 3.40,
        "GBP" to 0.87,
        "JPY" to 172.50,
        "CAD" to 1.62
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Remplissage des deux Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.devises,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spDeviseSource.adapter = adapter
        binding.spDeviseCible.adapter = adapter

        // USD par défaut comme source, EUR comme cible
        binding.spDeviseSource.setSelection(0)
        binding.spDeviseCible.setSelection(1)

        // Clavier numérique
        binding.btn0.setOnClickListener { ajouterCaractere("0") }
        binding.btn1.setOnClickListener { ajouterCaractere("1") }
        binding.btn2.setOnClickListener { ajouterCaractere("2") }
        binding.btn3.setOnClickListener { ajouterCaractere("3") }
        binding.btn4.setOnClickListener { ajouterCaractere("4") }
        binding.btn5.setOnClickListener { ajouterCaractere("5") }
        binding.btn6.setOnClickListener { ajouterCaractere("6") }
        binding.btn7.setOnClickListener { ajouterCaractere("7") }
        binding.btn8.setOnClickListener { ajouterCaractere("8") }
        binding.btn9.setOnClickListener { ajouterCaractere("9") }
        binding.btnPoint.setOnClickListener { ajouterCaractere(".") }

        // Bouton C : effacer le montant et le résultat
        binding.btnC.setOnClickListener {
            binding.tvMontant.text = "0"
            binding.tvResultat.text = ""
        }

        // Bouton Convertir
        binding.btnConvertir.setOnClickListener {
            effectuerConversion()
        }

        // Inversion des devises
        binding.btnInverser.setOnClickListener {
            val source = binding.spDeviseSource.selectedItemPosition
            val cible = binding.spDeviseCible.selectedItemPosition

            binding.spDeviseSource.setSelection(cible)
            binding.spDeviseCible.setSelection(source)

            // Recalculer uniquement si un résultat était déjà affiché
            if (binding.tvResultat.text.isNotEmpty()) {
                effectuerConversion()
            }
        }
    }

    // Ajouter un chiffre ou un point décimal
    private fun ajouterCaractere(c: String) {
        val montantActuel = binding.tvMontant.text.toString()

        // Maximum 10 caractères
        if (montantActuel.length >= 10) return

        if (c == ".") {
            // Un seul point décimal est autorisé
            if (!montantActuel.contains(".")) {
                binding.tvMontant.text = "$montantActuel."
            }
        } else {
            // Le premier chiffre remplace le zéro initial
            if (montantActuel == "0") {
                binding.tvMontant.text = c
            } else {
                binding.tvMontant.text = montantActuel + c
            }
        }
    }

    // Effectuer et afficher la conversion
    private fun effectuerConversion() {
        val montant = binding.tvMontant.text.toString().toDoubleOrNull()

        if (montant == null || montant == 0.0) {
            Toast.makeText(
                this,
                R.string.msg_montant_invalide,
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val source = binding.spDeviseSource.selectedItem.toString()
        val cible = binding.spDeviseCible.selectedItem.toString()

        val resultat = convertir(montant, source, cible)

        binding.tvResultat.text = String.format(
            Locale.US,
            "%.2f",
            resultat
        )
    }

    // Conversion en utilisant l'euro comme devise pivot
    private fun convertir(
        montant: Double,
        source: String,
        cible: String
    ): Double {
        if (source == cible) return montant

        val tauxSource = taux[source] ?: return 0.0
        val tauxCible = taux[cible] ?: return 0.0

        val montantEnEuro = montant / tauxSource

        return montantEnEuro * tauxCible
    }
}
