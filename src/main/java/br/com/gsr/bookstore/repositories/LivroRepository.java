package br.com.gsr.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gsr.bookstore.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

	@Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = :idCat ORDER BY titulo")
	List<Livro> findByCategoria(@Param(value = "idCat") Long idCat);

}
