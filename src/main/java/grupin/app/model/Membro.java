package grupin.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.NonNull;

import grupin.app.model.enums.Cargo;
import grupin.app.model.enums.StatusMembro;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Membro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private String nome;

    @Column
    @NonNull
    private String email;

    @Column(length = 100)
    @NonNull
    private String lattes;

    @Column(length = 200)
    @NonNull
    private String curso;

    @Column
    @NonNull
    private String fotoSrc;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    @NonNull
    private StatusMembro status;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    @NonNull
    private Cargo cargo;

    @ManyToMany(mappedBy = "membros", cascade = CascadeType.ALL)
    @Setter(value = AccessLevel.PRIVATE)
    private List<Projeto> projetos;

}
