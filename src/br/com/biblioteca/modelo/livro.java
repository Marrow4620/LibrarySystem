package br.com.biblioteca.modelo;

public class livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String genero;
    private String sinopse;
    private boolean disponivel;

    public livro() {
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public boolean isDisponivel() {
        return disponivel;
    }


    public livro(int id, String titulo, String autor, int ano, String genero, String sinopse, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.genero = genero;
        this.sinopse = sinopse;
        this.disponivel = disponivel;

    }

    @Override
    public String toString() {
        return "livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                ", genero='" + genero + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }


}
