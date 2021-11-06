package grupin.app.exception;

/**
 * @author Lucas-dev-back
 */
public class UsuarioNaoEncontradoException extends Exception {

    /**
     * 
     * @param mensagem Mensagem de erro
     */
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
