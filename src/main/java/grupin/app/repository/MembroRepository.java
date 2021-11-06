package grupin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import grupin.app.model.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {
    
}
