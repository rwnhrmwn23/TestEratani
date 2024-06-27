package com.onedev.testeratani._2_processing_data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onedev.testeratani.ui.theme.TestErataniTheme
import java.io.BufferedReader
import java.io.InputStreamReader

data class Barang(val id: Int, val nama: String, var stokAwal: Int, var pembelianHarian: Int)

class StockProductActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestErataniTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Processing Data App") },
                            navigationIcon = {
                                IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    StockProduct(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun StockProduct(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var barangList by remember { mutableStateOf(listOf<Barang>()) }
    var showEndOfMonthStock by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val inputStream = context.assets.open("mock_data_product.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val data = reader.readLines().drop(1).map { line ->
            val tokens = line.split(",")
            Barang(
                id = tokens[0].toInt(),
                nama = tokens[1],
                stokAwal = tokens[2].toInt(),
                pembelianHarian = tokens[3].toInt()
            )
        }
        barangList = data
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(text = "Daftar Barang", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        barangList.forEach { barang ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(text = barang.nama)
                Text(text = "Stok Awal: ${barang.stokAwal} Unit")
                Text(text = "Pembelian Harian: ${barang.pembelianHarian}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showEndOfMonthStock = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung Stok Akhir Bulan")
        }

        if (showEndOfMonthStock) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Stok Akhir Bulan", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))

            barangList.forEach { barang ->
                val penjualan = barang.stokAwal - (barang.pembelianHarian * 30)
                val remainingStock = if (penjualan < 0) 0 else penjualan

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(text = barang.nama)
                    Text(text = "Terjual: ${barang.pembelianHarian * 30} Unit")
                    Text(text = "Stok Akhir: $remainingStock Unit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StockProductPreview() {
    TestErataniTheme {
        StockProduct()
    }
}