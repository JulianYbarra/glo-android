package com.junka.glo.data.common

import arrow.core.Either
import com.junka.glo.domain.Error
import com.junka.glo.domain.Resource
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Either<Error, RequestType>,
    crossinline saveFetchResult: suspend (RequestType) -> Error?,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(true,data))

        fetch().fold(
            ifLeft = { error ->
                query().map {  Resource.Failure(error, data) }
            },
            ifRight = {
                val error = saveFetchResult(it)

                error?.let { query().map { Resource.Failure(error,it) } }
                    ?: run { query().map { Resource.Success(it) } }
            }
        )
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}