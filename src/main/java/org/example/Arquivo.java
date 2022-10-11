package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Arquivo {
    public static void criar(String caminho, List<String> lines) throws IOException {
        Path path = Paths.get(caminho);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
