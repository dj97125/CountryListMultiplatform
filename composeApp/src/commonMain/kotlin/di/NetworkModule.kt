package di

import data.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


object CountryNetwork {
    const val BASE_URL =
        "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/"

    object EndPoint {
        const val ENDPOINT = "db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"
    }
}

val httpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)

            }
        }
    }

}


val repositoryModule = module {
    single<Repository> { Repository(get()) }
}

