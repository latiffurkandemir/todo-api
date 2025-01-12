    package com.todoapp.todo_api.factory;

    import com.todoapp.todo_api.common.ErrorResponse;
    import com.todoapp.todo_api.common.Response;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;

    import java.time.LocalDateTime;

    public final class ResponseFactory {

        private ResponseFactory() {
        }

        private static final String SUCCESS_MESSAGE = "Success";

        public static <T> ResponseEntity<Response<T>> createResponse(T data, HttpStatus status) {
            return ResponseEntity.status(status).body(
                    Response.<T>builder()
                            .timestamp(LocalDateTime.now())
                            .status(status.value())
                            .data(data)
                            .build()
            );
        }

        public static ResponseEntity<Response<Void>> createResponse(HttpStatus status) {
            return ResponseEntity.status(status).body(
                    Response.<Void>builder()
                            .timestamp(LocalDateTime.now())
                            .status(status.value())
                            .build()
            );
        }


        public static <T> ResponseEntity<Response<T>> createSuccessResponse() {
            return ResponseEntity.status(HttpStatus.OK).body(
                    Response.<T>builder()
                            .timestamp(LocalDateTime.now())
                            .status(HttpStatus.OK.value())
                            .message(SUCCESS_MESSAGE)
                            .build()
            );
        }

        public static ResponseEntity<ErrorResponse> createErrorResponse(String error, String message, String apiPath, HttpStatus status) {
            return ResponseEntity.status(status).body(
                    ErrorResponse.builder()
                            .timestamp(LocalDateTime.now())
                            .status(status.value())
                            .error(error)
                            .message(message)
                            .apiPath(apiPath)
                            .build()
            );
        }

    }
