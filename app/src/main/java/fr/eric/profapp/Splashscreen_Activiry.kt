 package fr.eric.profapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import fr.eric.profapp.ui.theme.ProfAppTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

 class Splashscreen_Activiry : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LaunchedEffect(Unit){
                delay(3.seconds)
                //Intent Explicit
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }

                //Autre moyen de creer Intent Explicit
                /*Intent(Intent.ACTION_MAIN).also {
                    it.`package`="com.google.android.youtube"
                    try {
                        startActivity(it)
                    }
                    catch (e: ActivityNotFoundException){
                        e.printStackTrace()
                    }
                }*/

                //Intent impicilt
                /*val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayListOf("toto@mail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Le sujet du projet")
                    putExtra(Intent.EXTRA_TEXT, "Le message du sujet")
                }

                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }*/
            }

            ProfAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen("Salut")
                }
            }
        }
    }
}

@Composable
fun SplashScreen(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.eric),
            contentDescription = "Ma photo de profile")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    ProfAppTheme {
        SplashScreen("Android")
    }
}