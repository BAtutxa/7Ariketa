package com.example.a7ariketa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a7ariketa.models.Bideojokoa
import com.example.a7ariketa.ui.theme._7AriketaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a7ariketa.models.Datuak
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            _7AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BideojokoakZerrenda(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BideojokoakZerrenda(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var bideojokoak by remember{ mutableStateOf<List<Bideojokoa>>(emptyList()) }

    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.fondoariketa),
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
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    bideojokoak = Datuak(context, "bideojokoak.json")
                    Log.d("Bideojokoen Lista", bideojokoak.toString())
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Kargatu JSON datuak")
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(bideojokoak) { jokoa ->
                    BideojokoaCard(bideojokoa = jokoa)
                }
            }
        }
    }
}

@Composable
fun BideojokoaCard(bideojokoa: Bideojokoa, modifier: Modifier = Modifier) {
    val irudia = when (bideojokoa.id) {
        1 -> R.drawable.zelda
        2 -> R.drawable.elden_ring
        3 -> R.drawable.cyberpunk
        4 -> R.drawable.hollow_knight
        else -> R.drawable.fondoariketa
    }

    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(irudia),
                contentDescription = bideojokoa.titulua,
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = bideojokoa.titulua,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Mota: ${bideojokoa.mota}", fontSize = 14.sp)
                Text(text = "Plataforma: ${bideojokoa.plataforma}", fontSize = 12.sp)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _7AriketaTheme {
        BideojokoakZerrenda()
    }
}





