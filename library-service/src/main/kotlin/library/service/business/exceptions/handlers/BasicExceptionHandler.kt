package library.service.business.exceptions.handlers

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import library.service.api.ErrorDescription
import library.service.correlation.CorrelationIdHolder
import org.slf4j.LoggerFactory
import java.time.Clock
import java.time.OffsetDateTime
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [Exception::class, ExceptionHandler::class])
abstract class BasicExceptionHandler<E : Exception, R : MutableHttpResponse<ErrorDescription>>(
    private val clock: Clock,
    private val correlationIdHolder: CorrelationIdHolder
) : ExceptionHandler<E, R> {
    companion object {
        public val LOG = LoggerFactory.getLogger(BasicExceptionHandler::class.java.name)
    }

    fun errorDescription(
        httpStatus: HttpStatus,
        message: String,
        details: List<String> = emptyList()
    ) = ErrorDescription(
        status = httpStatus.code,
        error = httpStatus.reason,
        timestamp = OffsetDateTime.now(clock).toString(),
        correlationId = correlationIdHolder.get(),
        message = message,
        details = details
    )
}
