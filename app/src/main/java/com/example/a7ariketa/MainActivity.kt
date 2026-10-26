package com.example.a7ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a7ariketa.models.Datuak
import com.example.a7ariketa.models.Pelikula
import com.example.a7ariketa.ui.theme._7AriketaTheme
import androidx.compose.foundation.lazy.items
import android.util.Log


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _7AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BideojokoakAPP(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    @Composable
    fun BideojokoakAPP (modifier:Modifier = Modifier){
        val datuak = Datuak().datuakLortu(LocalContext.current)
        Log.d ("Lista",datuak.toString())
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(R.drawable.fondoa),
                contentDescription = null
            )
            LazyColumn(modifier = Modifier) {
                items (datuak){ datua -> JokoaCard(datua = datua)}
            }
        }

    }

    @Composable
    fun JokoaCard (datua: Pelikula, modifier : Modifier = Modifier){
        Card() {
            Column() {
                Text(
                    text = "ID: ${datua.id} Izena: ${datua.titulua}"
                )
                Text(
                    text = "Mota: ${datua.mota} Plataforma: ${datua.plataforma}"
                )
            }
        }
    }

    @Preview
    @Composable
    fun BideoJokoaAPPPreview (){
        _7AriketaTheme() {
            BideojokoakAPP()
        }
    }

}





