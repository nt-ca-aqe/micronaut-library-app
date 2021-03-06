package library.service.business.exceptions.handlers

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import library.service.api.ErrorDescription
import library.service.business.exceptions.NotFoundException
import library.service.correlation.CorrelationIdHolder
import java.time.Clock
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [NotFoundException::class, ExceptionHandler::class])
class NotFoundExceptionHandler(private val clock: Clock, correlationIdHolder: CorrelationIdHolder) :
    BasicExceptionHandler<NotFoundException, MutableHttpResponse<ErrorDescription>>(clock, correlationIdHolder) {

    override fun handle(request: HttpRequest<*>, exception: NotFoundException): MutableHttpResponse<ErrorDescription> {

        return HttpResponse.notFound(
            errorDescription(
                httpStatus = HttpStatus.NOT_FOUND,
                message = exception.message!!
            )
        )
    }
}
