package com.example.web.model;

import org.apache.commons.lang.StringUtils;

public final class FileData {

    private final String name;

    private final byte[] bytes;

    private final String contentType;

    private final String folderPath;
    
    private String source;

    public FileData(String name, byte[] bytes, String folderPath, String contentType) {
        this.name = name;
        this.bytes = bytes;
        if(StringUtils.isNotBlank(folderPath)) {
            if(folderPath.startsWith("/")) folderPath = folderPath.substring(1);
            if(folderPath.endsWith("/")) folderPath = folderPath.substring(0, folderPath.length() -1);
        } else {
            folderPath = null;
        }
        this.folderPath = folderPath;
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFolderPath() {
        return folderPath;
    }

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
		
}