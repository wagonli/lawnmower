package fr.libon.lawnmower.api.controller.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorMessageResponseBuilder {
    private HttpStatus status;
    private String title;
    private String detail;

    public ErrorMessageResponseBuilder() {
    }

    public ErrorMessageResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ErrorMessageResponseBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ErrorMessageResponseBuilder detail(String detail) {
        this.detail = detail;
        return this;
    }

    public ResponseEntity<ErrorMessageDto> build() {
        if (this.status == null) {
            throw  new IllegalArgumentException("Status cannot be null.");
        } else {
            ErrorMessageDto errorMessageDto = new ErrorMessageDto(this.status.value(), this.title, this.detail);
            return new ResponseEntity<>(errorMessageDto, this.status);
        }
    }
}
