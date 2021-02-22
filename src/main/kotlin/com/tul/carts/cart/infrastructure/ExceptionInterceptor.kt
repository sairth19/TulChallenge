package com.tul.carts.cart.infrastructure

import com.tul.carts.cart.domain.exception.BusinessException
import com.tul.carts.cart.infrastructure.rest.request.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.ResponseEntity




@ControllerAdvice
class ExceptionInterceptor : ResponseEntityExceptionHandler()  {

    @ExceptionHandler(BusinessException::class)
    fun handleSecurityException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(ex.code, ex.localizedMessage), HttpStatus.BAD_REQUEST)
    }
}
