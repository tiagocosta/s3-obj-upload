package com.tcs.service.exception;

import java.io.Serial;

public class FileUploadException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileUploadException() {
        super("File upload failed.");
    }

}