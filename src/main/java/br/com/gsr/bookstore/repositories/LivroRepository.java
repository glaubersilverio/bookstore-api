package br.com.gsr.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gsr.bookstore.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
