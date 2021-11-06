package grupin.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import grupin.app.model.EmailModel;
import grupin.app.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/email")
@Tag(name = "Email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    EmailService service;

    /**
     * 
     * @param emailModel Objeto Json com dados necessários para o email
     * @return Email enviado
     */
    @PostMapping
    @ResponseBody
    @Operation(summary = "Envio de email", responses = {
            @ApiResponse(description = "Envio de email enviado com sucesso", responseCode = "201") })
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody EmailModel emailModel) {
        service.sendEmail(emailModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(emailModel);
    }

}
