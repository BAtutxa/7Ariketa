package com.example.a7ariketa

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a7ariketa.models.Bideojokoa
import com.example.a7ariketa.models.Datuak
import com.example.a7ariketa.ui.theme._7AriketaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _7AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BideojokoZerrenda(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun BideojokoZerrenda(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val bideojokoak = remember { Datuak().datuakKargatu(context, "bideojokoak.json") }
    Log.d("Bideojoko zerrenda", bideojokoak.toString())


    Box(
        modifier = modifier.fillMaxSize(),
    ) {

        val fondoa = painterResource(R.drawable.fondoa)

        Image(
            painter = fondoa,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Bideojokoak",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(bideojokoak) { bideojokoa -> BideojokoCard(bideojokoa) }
            }
        }


    }

}

@Composable
fun BideojokoCard(bideojokoa: Bideojokoa, modifier: Modifier = Modifier) {
    val irudia = when (bideojokoa.id) {
        1 -> R.drawable.zelda
        2 -> R.drawable.eldenring
        3 -> R.drawable.cyberpunk
        4 -> R.drawable.hollowknight
        else -> R.drawable.zelda
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(irudia),
                contentDescription = bideojokoa.titulua,
                modifier = Modifier
                    .padding(5.dp)
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = bideojokoa.titulua,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "ID: ${bideojokoa.id}", fontSize = 14.sp)
                Text(text = "Mota: ${bideojokoa.mota}", fontSize = 14.sp)
                Text(text = "Plataforma: ${bideojokoa.plataforma}", fontSize = 14.sp)

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun BideojokoZerrendaPreview() {
    _7AriketaTheme {
        BideojokoZerrenda()
    }
}





