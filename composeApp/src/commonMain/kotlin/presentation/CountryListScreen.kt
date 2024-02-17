package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import dev.icerock.moko.resources.compose.stringResource
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.countryList.shared.MR
import data.CountryResponse
import di.getScreenModel
import presentation.components.CountryCardView
import presentation.util.Dimens
import presentation.util.LoadingAnimation

class CountryListScreen: Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val countryListScreenModel = getScreenModel<CountryListScreenModel>()
        LaunchedEffect(Unit){
            countryListScreenModel.fetchCountries()
        }
        val state by countryListScreenModel.countryListScreenState.collectAsState()
        val currentNav = LocalNavigator.currentOrThrow

        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        Scaffold(topBar = {
            MediumTopAppBar(title = {
                Text(stringResource(MR.strings.app_name),color = androidx.compose.material.MaterialTheme.colors.primaryVariant,)
            }, scrollBehavior = scrollBehavior)

        }) {
            Column (modifier = Modifier.fillMaxSize().padding(it).padding(Dimens.doubleSpace).nestedScroll(scrollBehavior.nestedScrollConnection).verticalScroll(
                rememberScrollState()
            )) {
                when (state) {
                    is CountryListScreenState.Loading -> LoadingAnimation()

                    is CountryListScreenState.Error -> {
                    }

                    is CountryListScreenState.Success -> {
                        val retrievedElements = (state as CountryListScreenState.Success).countryList
                        RenderListScreen(countryList = retrievedElements, onClick = {item-> })
                    }
                }
            }
        }
    }
}

@Composable
fun RenderListScreen(countryList: List<CountryResponse>?, onClick:(country: CountryResponse)->Unit = {}) {
    countryList?.let { country ->
        Text(
            "Country List",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = androidx.compose.material.MaterialTheme.colors.primaryVariant,
        )
        Spacer(modifier = Modifier.height(Dimens.doubleSpace))
        country.forEach{ item ->
            Surface (modifier = Modifier.padding(top = Dimens.space).customShadow(), onClick = {
                onClick.invoke(item)
            }, shape = RoundedCornerShape(Dimens.space)) {
                Row (modifier = Modifier.fillMaxWidth().padding(Dimens.space), verticalAlignment = Alignment.CenterVertically) {
                    Column (modifier = Modifier.padding(start = Dimens.space).weight(1f)) {
                        Text(item.name.orEmpty(), style = MaterialTheme.typography.titleMedium, color = androidx.compose.material.MaterialTheme.colors.primaryVariant,)
                        Text(item.capital.orEmpty(), style = MaterialTheme.typography.bodyMedium, color = androidx.compose.material.MaterialTheme.colors.secondaryVariant,)
                    }
                    Column (modifier = Modifier.padding(start = Dimens.space, end = Dimens.space), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("REGION", style = MaterialTheme.typography.titleSmall, color = androidx.compose.material.MaterialTheme.colors.primaryVariant,)
                        Text(item.region.orEmpty(),style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = androidx.compose.material.MaterialTheme.colors.primaryVariant,)
                    }
                }
            }
        }

    }

}

fun Modifier.customShadow(elevation: Dp = Dimens.space): Modifier = composed {
    shadow(elevation = elevation, shape = RoundedCornerShape(elevation), clip = true)
}

