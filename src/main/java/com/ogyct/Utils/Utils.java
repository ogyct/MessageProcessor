package com.ogyct.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static File getResource(String relativePath) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        File file = new File(classLoader.getResource(relativePath).getFile());
        return file;
    }

}
