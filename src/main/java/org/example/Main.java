package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        String HOME_PATH = System.getProperty("user.dir");
        System.out.println(HOME_PATH);
        String participantes = HOME_PATH.concat("/files/participantes.txt");

        Path path = Paths.get(participantes);
        List<String> lines = Files.readAllLines(path);
        List<Pessoa> pessoaList = new ArrayList<>();
        lines.forEach(line -> {
            String[] campos = line.split(",");
            String nome = campos[0];
            String zona = campos[1];
            LocalDate dataNascimento = LocalDate.parse(campos[2]);

            Pessoa pessoa = new Pessoa(nome, zona, dataNascimento);
            pessoaList.add(pessoa);
        });


        pessoaList.stream().parallel().forEach((pessoa -> {
            String caminho = HOME_PATH.concat("/files/" + pessoa.getNome() + ".txt");
            try {
                Arquivo.criar(caminho, pessoa.gerarRelatorio());
                var execution = System.currentTimeMillis();
                System.out.println(execution+ Thread.currentThread().getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

    }
}