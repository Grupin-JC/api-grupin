package grupin.app.model;

import lombok.Data;

@Data
public class EmailModel {

    private String destinario;
    private String assunto;
    private String corpo;

}
