package br.com.rafael.syonet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rafael.syonet.model.Vendedor;

/**
 * Repositório para operações de {@link Vendedor}.
 *
 * @author Rafael Braga
 */
@Repository
public interface VendedorRepository extends CrudRepository<Vendedor, Long> {

}
