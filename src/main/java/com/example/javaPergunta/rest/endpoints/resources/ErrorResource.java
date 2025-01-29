package com.example.javaPergunta.rest.endpoints.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
public class ErrorResource {

    protected Integer status;
    protected String type;
    protected String title;
    protected String detail;
    protected String path;
    protected Instant timestamp;

    public static class ErrorResponseBuilder {

        private ErrorResource instance;

        public ErrorResponseBuilder() {
            this.instance = new ErrorResource();
        }

        public ErrorResponseBuilder withStatus(Integer status) {
            this.instance.status = status;
            return this;
        }

        public ErrorResponseBuilder withType(String type) {
            this.instance.type = type;
            return this;
        }

        public ErrorResponseBuilder withTitle(String title) {
            this.instance.title = title;
            return this;
        }

        public ErrorResponseBuilder withDetail(String detail) {
            this.instance.detail = detail;
            return this;
        }

        public ErrorResponseBuilder withPath(String path) {
            this.instance.path = path;
            return this;
        }

        public ErrorResponseBuilder withTimesTamp(Instant timesTamp) {
            this.instance.timestamp = timesTamp;
            return this;
        }

        public ErrorResource build() {
            return this.instance;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getPath() {
        return path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
