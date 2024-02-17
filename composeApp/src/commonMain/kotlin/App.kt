import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.navigator.Navigator
import di.appModule
import di.countryListScreenModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinApplication
import presentation.CountryListScreen
import presentation.theme.CountryListAppTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    ScreenRegistry {
        screenModule { countryListScreenModule() }
    }
    KoinApplication(application = {
        modules(appModule())
    }) {
        CountryListAppTheme {
            Navigator(screen = CountryListScreen())
        }
    }
}