package com.yclin.simplecarlease.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author LinYuchang
 */
public class FileUtil {

    public static byte[] readAsBytes(InputStream is) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            IOUtils.copy(is, os);
            return os.toByteArray();
        }
    }
}
