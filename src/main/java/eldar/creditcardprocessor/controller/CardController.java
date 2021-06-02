package eldar.creditcardprocessor.controller;

import eldar.creditcardprocessor.model.Operacion.Operacion;
import eldar.creditcardprocessor.model.Tasa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/obtenerTasa")
    public ResponseEntity<Tasa> getTasa(@RequestBody Operacion operacion){
        //si no se puede mapear una clase abstracta, entonces recibir por parametro el enum de la tarje y el monto
        final Tasa tasa = operacion.informarTasa();
        return new ResponseEntity<Tasa>(tasa, HttpStatus.OK);
    }
}
