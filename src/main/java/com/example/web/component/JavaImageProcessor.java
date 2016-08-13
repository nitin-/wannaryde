package com.example.web.component;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import net.coobird.thumbnailator.Thumbnails;


@Component
public class JavaImageProcessor implements ImageProcessor {

    @Override
    public InputStream resize(InputStream is, int width, int height) throws IOException {
        return this.resize(is, width, height, true);
    }

    @Override
    public InputStream resize(InputStream is, int width, int height, boolean aspect) throws IOException {
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            Thumbnails.of(is)
                    .size(width, height)
                    .keepAspectRatio(aspect)
                    .toOutputStream(os);
        } finally {
            // IOUtils.closeQuietly(os);
        }

        InputStream returnInputStream = new ByteArrayInputStream(os.toByteArray());
        return returnInputStream;
    }
   
}
