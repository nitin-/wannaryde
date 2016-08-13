package com.example.web.component;

import java.io.IOException;
import java.io.InputStream;


public interface ImageProcessor {

    InputStream resize(InputStream is, int width, int height) throws IOException;

    InputStream resize(InputStream is, int width, int height, boolean aspect) throws IOException;

}
