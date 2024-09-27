package br.com.biblioteca.principal;

import br.com.biblioteca.exceptions.ErroApi;
import br.com.biblioteca.modelo.Estudante;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.modelo.Professor;
import br.com.biblioteca.modelo.Usuario;
import br.com.biblioteca.modelo.Emprestimo;
import br.com.biblioteca.utils.ApiUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoBiblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Escolha seu perfil:");
        System.out.println("1. Professor");
        System.out.println("2. Aluno");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Usuario usuario;
        if (escolha == 1) {
            usuario = new Professor(nome);
            System.out.println("Perfil selecionado: Professor");
        } else if (escolha == 2) {
            usuario = new Estudante(nome);
            System.out.println("Perfil selecionado: Aluno");
        } else {
            System.out.println("Escolha inválida.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo();

        while (true) {
            System.out.println("Digite o título do livro para a busca:");
            String tituloLivro = scanner.nextLine();

            try {
                List<Livro> livrosEncontrados = ApiUtils.buscarLivros(tituloLivro, nome);
                if (livrosEncontrados.isEmpty()) {
                    System.out.println("Nenhum livro encontrado.");
                } else {
                    System.out.println("Livros encontrados:");
                    for (int i = 0; i < livrosEncontrados.size(); i++) {
                        System.out.println((i + 1) + ". " + livrosEncontrados.get(i).getTitulo());
                    }

                    System.out.println("Digite o número do livro que deseja emprestar ou 0 para cancelar:");
                    int numeroLivro = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    if (numeroLivro > 0 && numeroLivro <= livrosEncontrados.size()) {
                        Livro livro = livrosEncontrados.get(numeroLivro - 1);
                        emprestimo.emprestrarLivro(usuario, livro);
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                }
            } catch (IOException | InterruptedException | ErroApi e) {
                e.printStackTrace();
            }

            System.out.println("Deseja buscar outro livro? (s/n)");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                break;
            }
        }
    }
}