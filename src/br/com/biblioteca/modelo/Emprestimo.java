package br.com.biblioteca.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Emprestimo {
    public void emprestrarLivro(Usuario usuario, Livro livro) {
        int limiteLivros = usuario.getTipo_usuario().equals("Professor") ? 5 : 3;
        if (usuario.getQuantidadeLivrosEmprestados() < limiteLivros) {
            usuario.getLivrosEmprestados().add(livro);
            System.out.println("Livro emprestado com sucesso para: " + usuario.getNome());
            salvarEmprestimo(usuario, livro);
        } else {
            System.out.println("Limite de livros atingido, o máximo é " + limiteLivros);
        }
    }

    private void salvarEmprestimo(Usuario usuario, Livro livro) {
        String nomeArquivo = usuario.getNome() + ".txt";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String livroJson = gson.toJson(livro);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(" Livro: " + livroJson);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o empréstimo: " + e.getMessage());
        }
    }
}