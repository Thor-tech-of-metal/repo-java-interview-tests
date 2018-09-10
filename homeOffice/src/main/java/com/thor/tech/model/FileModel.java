package com.thor.tech.model;

public class FileModel {

    private final String fileName;
    private final long fileSize;
    private final String extension;
    private final FileMimeTypes  mimeType;

    public FileModel(String fileName, long fileSize, String extension, FileMimeTypes mimeType) {

        this.fileName = fileName;
        this.fileSize = fileSize;
        this.extension = extension;
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getExtension() {
        return extension;
    }

    public FileMimeTypes  getMimeType() {
        return mimeType;
    }
}
