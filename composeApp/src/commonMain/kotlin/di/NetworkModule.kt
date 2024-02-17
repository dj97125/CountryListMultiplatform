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
        "https://api.controlbyweb.cloud/DAT/"

    object EndPoint {
        const val ENDPOINT = "{unit-dat}/state.json?"
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

