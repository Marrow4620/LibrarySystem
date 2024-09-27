package br.com.biblioteca.modelo;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String Tipo_usuario;
    private ArrayList<Livro> livrosEmprestados = new ArrayList<>();

    public Usuario(String nome, String tipo_usuario) {
        this.nome = nome;
        this.Tipo_usuario = tipo_usuario;
    }

    public int getQuantidadeLivrosEmprestados() {
        return livrosEmprestados.size();
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo_usuario() {
        return Tipo_usuario;
    }

    public void emprestrarLivro(Livro livro) {
        int limiteLivros = Tipo_usuario.equals("Professor") ? 5 : 3;
        if (getQuantidadeLivrosEmprestados() < limiteLivros) {
            livrosEmprestados.add(livro);
            System.out.println("Livro emprestado com sucesso: " + livro.getTitulo());
        } else {
            System.out.println("Limite de livros atingido, o máximo é " + limiteLivros);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", Tipo_usuario='" + Tipo_usuario + '\'' +
                '}';
    }
}