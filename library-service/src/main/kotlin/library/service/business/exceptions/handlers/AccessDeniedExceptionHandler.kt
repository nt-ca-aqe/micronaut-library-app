package library.service.business.exceptions.handlers

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import library.service.api.ErrorDescription
import java.time.Clock
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [AccessDeniedException::class, ExceptionHandler::class])
class AccessDeniedExceptionHandler (private val clock: Clock) :
        BasicExceptionHandler<AccessDeniedException, MutableHttpResponse<ErrorDescription>> (clock) {

    override fun handle(request: HttpRequest<*>, exception: AccessDeniedException):
            MutableHttpResponse<ErrorDescription> {

        exception.printStackTrace()

        return HttpResponse.badRequest(errorDescription(
                httpStatus = HttpStatus.FORBIDDEN,
                message = "You don't have the necessary rights to to this."))
    }
}
