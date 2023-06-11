package mlclover.appplication.controllers.pedidos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mlclover.appplication.dtos.pedidos.PedidoDTO;
import mlclover.appplication.entities.pedidos.Pedido;
import mlclover.appplication.services.pedidos.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping(value="/v1/pedidos")
@CrossOrigin("*")
@Api("Address REST API")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @ApiOperation(value = "Busca pedido")
    public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
        PedidoDTO obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ApiOperation(value = "Efetua pedido do cliente")
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}