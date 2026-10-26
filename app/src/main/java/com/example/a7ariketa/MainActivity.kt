package com.example.a7ariketa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a7ariketa.models.Bideojoakoak
import com.example.a7ariketa.ui.theme._7AriketaTheme
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _7AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    bideojokoenZerrenda(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun bideojokoenZerrenda(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bideojokoak = datuakKargatu(context, "bideojokoak.json")

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.irudia),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(bideojokoak) { bideojokoa ->
                BideojokoaCard(bideojokoa)
            }
        }
    }
}

fun datuakKargatu(context: Context, fitx: String): List<Bideojoakoak> {
    return try {
        val jsonString = context.assets.open(fitx)
            .bufferedReader()
            .use { it.readText() }

        Log.d("EL string: ", jsonString)

        Json { ignoreUnknownKeys = true }
            .decodeFromString<List<Bideojoakoak>>(jsonString)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

@Composable
fun BideojokoaCard(bideojokoa: Bideojoakoak, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = bideojokoa.titulua,
                    fontSize = 20.sp,
                    lineHeight = 22.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(text = "Mota: ${bideojokoa.mota}")
                    Text(text = "Plataforma: ${bideojokoa.plataforma}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _7AriketaTheme {
        bideojokoenZerrenda()
    }
}