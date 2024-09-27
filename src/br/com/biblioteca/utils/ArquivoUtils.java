package br.com.biblioteca.utils;

import br.com.biblioteca.modelo.Livro;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {

    public static void criarArquivoComLivros(String resposta, String nomeArquivo) {
        List<Livro> livros = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(resposta).getAsJsonObject();
        JsonArray items = jsonObject.getAsJsonArray("items");

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.get(i).getAsJsonObject();
                JsonObject volumeInfo = item.getAsJsonObject("volumeInfo");

                String titulo = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "";
                String autor = volumeInfo.has("authors") ? volumeInfo.getAsJsonArray("authors").get(0).getAsString() : "";
                int ano = volumeInfo.has("publishedDate") ? Integer.parseInt(volumeInfo.get("publishedDate").getAsString().split("-")[0]) : 0;
                String genero = volumeInfo.has("categories") ? volumeInfo.getAsJsonArray("categories").get(0).getAsString() : "";
                String sinopse = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "";

                Livro livro = new Livro(0, titulo, autor, ano, genero, sinopse);
                livros.add(livro);
            }
        }

        try (Writer writer = new FileWriter(nomeArquivo + ".txt")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(livros));
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
}