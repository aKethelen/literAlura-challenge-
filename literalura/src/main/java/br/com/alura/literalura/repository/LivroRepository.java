package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Esse método é o que a sua Principal chama no caso 5
    List<Livro> findByIdioma(String idioma);
}