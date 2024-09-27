package br.com.biblioteca.utils;

import br.com.biblioteca.exceptions.ErroApi;
import br.com.biblioteca.modelo.Livro;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiUtils {
    public static List<Livro> buscarLivros(String tituloLivro, String nomeArquivo) throws IOException, InterruptedException, ErroApi {
        String chave = "AIzaSyAj5EY72A3FzJ5bpSHmh8PCGNtUD1Qgg9E";
        String endereco = "https://www.googleapis.com/books/v1/volumes?q=" + tituloLivro.replace(" ", "+") + "&key=" + chave;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new ErroApi("Erro ao buscar livros: " + response.body());
        }

        JsonArray items = JsonParser.parseString(response.body()).getAsJsonObject().getAsJsonArray("items");
        List<Livro> livros = new ArrayList<>();
        for (JsonElement item : items) {
            JsonObject volumeInfo = item.getAsJsonObject().getAsJsonObject("volumeInfo");
            String titulo = volumeInfo.get("title").getAsString();
            String autor = volumeInfo.has("authors") ? volumeInfo.getAsJsonArray("authors").get(0).getAsString() : "Desconhecido";
            int ano = volumeInfo.has("publishedDate") ? Integer.parseInt(volumeInfo.get("publishedDate").getAsString().substring(0, 4)) : 0;
            String genero = volumeInfo.has("categories") ? volumeInfo.getAsJsonArray("categories").get(0).getAsString() : "Desconhecido";
            String sinopse = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "Sem descrição";

            Livro livro = new Livro(0, titulo, autor, ano, genero, sinopse);
            livros.add(livro);
        }

        return livros;
    }
}