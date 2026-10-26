package com.example.a7ariketa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a7ariketa.ui.theme._7AriketaTheme
import com.example.classdata.models.Bideojokoak
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _7AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SacarDatos(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
// App
@Composable
fun SacarDatos(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var bideojokoak by remember {
        mutableStateOf<List<Bideojokoak>>(emptyList())
    }

    var irakurrita by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.fondo),
            contentDescription = "Fondo de videojuegos",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.40f))
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
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    bideojokoak = datuakKargatu(
                        context,
                        "datos.json"
                    )
                    irakurrita = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kargatu bideojokoak")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (irakurrita && bideojokoak.isEmpty()) {

                Text(
                    text = "Ez dira datuak kargatu",
                    color = Color.White
                )

            } else if (bideojokoak.isNotEmpty()) {

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = bideojokoak,
                        key = { it.id }
                    ) { juego ->

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {

                                val imageId = when (juego.id) {
                                    1 -> R.drawable.zelda
                                    2 -> R.drawable.elden_ring
                                    3 -> R.drawable.cyberpunk
                                    4 -> R.drawable.hollow_knight
                                    else -> R.drawable.fondo
                                }

                                Image(
                                    painter = painterResource(imageId),
                                    contentDescription = juego.titulua,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(
                                    modifier = Modifier.height(10.dp)
                                )

                                Text(
                                    text = juego.titulua,
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Spacer(
                                    modifier = Modifier.height(6.dp)
                                )

                                Text(
                                    text = "Mota: ${juego.mota}",
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Text(
                                    text = "Plataforma: ${juego.plataforma}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun datuakKargatu(context: Context, fitx: String): List<Bideojokoak> {
    return try {
        val jsonString = context.assets.open(fitx)
            .bufferedReader()
            .use { it.readText() }

        Log.d ("EL string: ",jsonString)

        Json { ignoreUnknownKeys = true }
            .decodeFromString<List<Bideojokoak>>(jsonString)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}


@Preview(showBackground = true)
@Composable
fun SacarDatosPreview() {
    _7AriketaTheme {
        SacarDatos()
    }
}





