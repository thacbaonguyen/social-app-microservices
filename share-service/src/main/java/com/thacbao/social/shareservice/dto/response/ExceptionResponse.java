package com.thacbao.social.shareservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private int code;
    private String message;
    private HttpStatusCode status;
    private String error;
    private String timestamp;
}