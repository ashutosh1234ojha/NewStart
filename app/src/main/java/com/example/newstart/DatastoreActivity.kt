package com.example.newstart

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by Ashutosh Ojha on 28,May,2022
 */
class DatastoreActivity : AppCompatActivity() {

    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)
        val input = findViewById<EditText>(R.id.input)
        val output = findViewById<EditText>(R.id.output)
        val btn = findViewById<Button>(R.id.btn)


        val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
        val exampleCounterFlow: Flow<Int> = dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[EXAMPLE_COUNTER] ?: 0
            }

        lifecycleScope.launch {
            exampleCounterFlow.collect { it ->
                output.setText(it.toString())
            }
        }

        btn.setOnClickListener {
            lifecycleScope.launch {
                incrementCounter()

            }
        }
    }

    suspend fun incrementCounter() {
        val EXAMPLE_COUNTER = intPreferencesKey("example_counter")

        dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }
}