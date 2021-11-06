package grupin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupin.app.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

}
