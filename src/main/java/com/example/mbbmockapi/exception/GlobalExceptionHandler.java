package com.example.mbbmockapi.exception;

import com.example.mbbmockapi.model.dto.common.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiError> handleInsufficientBalance(
            InsufficientFundsException ex) {

        ApiError error = new ApiError(
                "INSUFFICIENT_BALANCE",
                ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        // You can customize the message or log the actual exception if needed
        String errorMessage = "Required request body is missing or malformed.";

        ApiError error = new ApiError(
                "BAD_REQUEST",
                errorMessage
        );
        log.info("Bad request: ", ex);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleArgumentRequestBody(MethodArgumentNotValidException ex) {
        String errorMessage = "Not enough argument";
        ApiError error = new ApiError(
                "BAD_REQUEST",
                "bad request"
        );
        log.info("Bad request: ", ex);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(
            Exception ex) {

        log.error("Unexpected exception: ", ex);

        ApiError error = new ApiError(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
