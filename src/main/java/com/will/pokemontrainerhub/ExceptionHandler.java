package com.will.pokemontrainerhub;

import com.atlassian.oai.validator.report.ValidationReport;
import com.atlassian.oai.validator.springmvc.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorValidationReport> handle(final InvalidRequestException invalidRequestException) {
        final ErrorValidationReport report = new ErrorValidationReport(invalidRequestException
                .getValidationReport().getMessages());
        return new ResponseEntity<>(report, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static class ErrorValidationReport {

        private String errorCode = "1000";
        private String errorDescription = "";

        public ErrorValidationReport(final List<ValidationReport.Message> messages) {
            StringBuilder builder = new StringBuilder();
            for (ValidationReport.Message msg : messages) {
                builder.append(msg.getMessage()).append("\n");
            }

            errorDescription = builder.toString();
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorDescription() {
            return errorDescription;
        }
    }
}
