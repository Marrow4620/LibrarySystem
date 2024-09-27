package br.com.biblioteca.exceptions;

public class ErroApi extends Exception {
    private String mensagem;

    public ErroApi(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
