package com.example.a7ariketa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a7ariketa.models.Bideojokoa
import com.example.a7ariketa.ui.theme._7AriketaTheme
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _7AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BideojokoakScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BideojokoakScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var bideojokoak by remember { mutableStateOf<List<Bideojokoa>>(emptyList()) }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bideojokoak",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Button(
                onClick = {
                    bideojokoak = datuakKargatu(context, "bideojokoak.json")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Kargatu")
            }

            BideojokoakList(bideojokoak = bideojokoak)
        }
    }
}

@Composable
fun BideojokoakList(bideojokoak: List<Bideojokoa>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(bideojokoak) { bideojokoa ->
            BideojokoaCard(bideojokoa = bideojokoa)
        }
    }
}

fun datuakKargatu(context: Context, fitx: String): List<Bideojokoa> {
    return try {
        val jsonString = context.assets.open(fitx)
            .bufferedReader()
            .use { it.readText() }

        Json { ignoreUnknownKeys = true }
            .decodeFromString<List<Bideojokoa>>(jsonString)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

@Composable
fun BideojokoaCard(bideojokoa: Bideojokoa, modifier: Modifier = Modifier) {
    val irudia = when (bideojokoa.titulua){
        "The Legend of Zelda: Tears of the Kingdom" -> R.drawable.zelda
        "Elden Ring" -> R.drawable.elden_ring
        "Cyberpunk 2077" -> R.drawable.cyberpunk
        else -> R.drawable.hollowknight
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image (
                painter = painterResource(irudia),
                contentDescription = bideojokoa.titulua
            )

            Text(
                text = bideojokoa.titulua,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Mota: ${bideojokoa.mota}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Plataforma: ${bideojokoa.plataforma}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    _7AriketaTheme {
        Surface {
            BideojokoakScreen()
        }
    }
}