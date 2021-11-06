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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.NonNull;

import grupin.app.model.enums.StatusProjeto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    @NonNull
    private String nome;

    @Column(length = 200)
    private String descricao;

    @Column
    @NonNull
    private String fotoSrc;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @NonNull
    private StatusProjeto status;

    @Column
    private String jogoSrc;

    @ManyToOne
    private Categoria categoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "membro_projeto", joinColumns = { @JoinColumn(name = "membro_id") }, inverseJoinColumns = {
            @JoinColumn(name = "projeto_id") })
    @Setter(value = AccessLevel.PRIVATE)
    private List<Membro> membros;

}
