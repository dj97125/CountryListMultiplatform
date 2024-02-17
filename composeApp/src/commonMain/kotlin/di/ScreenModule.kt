package di

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.screenModule
import presentation.CountryListScreen


sealed class CountryScreen:ScreenProvider {
    object countryList:CountryScreen()
}

val countryListScreenModule = screenModule {
    register<CountryScreen.countryList> {
        CountryListScreen()
    }
}