package com.example.tp29_11

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class ListEtudiantActivity : AppCompatActivity() {

    private var adapter: SimpleCursorAdapter? = null
    private var dbHelper: EtudiantDBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_etudiant);

        var ListEtudiant = findViewById<View>(R.id.idlistetu) as ListView
        ListEtudiant.setAdapter(getAdapter())

    }

    fun getDbHelper(): EtudiantDBHelper? {
        if (dbHelper == null) {
            dbHelper = EtudiantDBHelper(this)
        }
        return dbHelper
    }

    private fun getAdapter(): SimpleCursorAdapter? {

        if (adapter == null) {
            val db = getDbHelper()?.readableDatabase
            val c = db?.rawQuery("SELECT * FROM etudiant", null)
            print(c)
            adapter = SimpleCursorAdapter(
                this,
                R.layout.ligne_etudiant,
                c,
                arrayOf(
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM,
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM
                ),
                intArrayOf(R.id.nometud, R.id.preetud),
                0
            )
        }
        return adapter!!
    }

}