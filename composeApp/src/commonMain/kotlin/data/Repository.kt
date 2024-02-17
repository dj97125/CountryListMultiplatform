package data

import di.CountryNetwork
import di.isNetworkAvailable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val httpClient: HttpClient
) {

    suspend fun fetchCountries(): Flow<StateAction<List<CountryResponse>?>> {
        return makeSafeApiCall {
            val response =
                httpClient.get(CountryNetwork.BASE_URL.plus(CountryNetwork.EndPoint.ENDPOINT)).body<List<CountryResponse>>()
            StateAction.success(response)
        }
    }

    private suspend fun <T> makeSafeApiCall(api: suspend () -> StateAction<T?>) = flow {
        try {
            emit(StateAction.loading())
            if (isNetworkAvailable()) {
                val response = api.invoke()
                emit(StateAction.success(response.data))

            } else {
                emit(StateAction.error(error = onError(code = ErrorCode.NETWORK_NOT_AVAILABLE)))
            }

        } catch (ex: Exception) {
            println(ex.message)
            emit(StateAction.error(error = onError(code = ErrorCode.NETWORK_CONNECTION_FAILED)))
        }
    }

}