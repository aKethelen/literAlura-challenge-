package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ------------------------------------
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    ------------------------------------
                    """;

            System.out.println(menu);
            if (leitura.hasNextInt()) {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1 -> buscarLivroWeb();
                    case 2 -> listarLivrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivos();
                    case 5 -> listarLivrosPorIdioma();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida");
                }
            } else {
                System.out.println("Por favor, digite um número.");
                leitura.nextLine();
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro para busca:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        
        DadosResposta dados = conversor.obterDados(json, DadosResposta.class);

        if (dados.resultadoLivros() != null && !dados.resultadoLivros().isEmpty()) {
            DadosLivro dadosLivro = dados.resultadoLivros().get(0);
            DadosAutor dadosAutor = dadosLivro.autores().get(0);

            // Busca se o autor já existe para evitar duplicados
            Autor autor = autorRepository.findByNomeContainingIgnoreCase(dadosAutor.nome())
                    .orElseGet(() -> autorRepository.save(new Autor(dadosAutor)));

            // Cria e salva o livro vinculado ao autor
            Livro livro = new Livro(dadosLivro, autor);
            try {
                livroRepository.save(livro);
                System.out.println("Livro salvo com sucesso: " + livro.getTitulo());
            } catch (Exception e) {
                System.out.println("Erro: Este livro já está registrado no seu catálogo.");
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livros.forEach(l -> System.out.println("Título: " + l.getTitulo() + " | Autor: " + l.getAutor().getNome()));
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(a -> System.out.println("Autor: " + a.getNome() + " (" + a.getAnoNascimento() + " - " + a.getAnoFalecimento() + ")"));
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Insira o ano que deseja pesquisar:");
        if (leitura.hasNextInt()) {
            var ano = leitura.nextInt();
            leitura.nextLine();
            
            List<Autor> autores = autorRepository.buscarAutoresVivosNoAno(ano);
            if (autores.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado no ano de " + ano);
            } else {
                autores.forEach(a -> System.out.println("Autor: " + a.getNome()));
            }
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Insira o idioma (pt, en, es, fr):");
        var idioma = leitura.nextLine();
        
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma: " + idioma);
        } else {
            livros.forEach(l -> System.out.println("Título: " + l.getTitulo() + " [" + l.getIdioma() + "]"));
        }
    }
}