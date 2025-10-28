package org.example.Controller;

import org.example.Models.AdministradorContenido;
import org.example.Models.Categoria;
import org.example.Models.Producto;
import org.example.Services.AdministradorContenidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/administradorcontenido")
public class AdministradorContenidoController {

    private AdministradorContenidoService objAdministradorContenido;

    @GetMapping
    public List<AdministradorContenido> getAll() {
        return objAdministradorContenido.getAllAdministradoresContenido();
    }

    @PostMapping("/producto")
    public Producto agregarProducto(@RequestBody AdministradorContenido admin, @RequestBody Producto producto) {
        return objAdministradorContenido.agregarProducto(admin, producto);
    }

    @PutMapping("/producto/{id}")
    public Producto actualizarProducto(@PathVariable UUID id, @RequestBody AdministradorContenido admin, @RequestBody Producto producto) {
        producto.setId(id);
        return objAdministradorContenido.actualizarProducto(admin, producto);
    }

    @DeleteMapping("/producto/{id}")
    public void eliminarProducto(@PathVariable Producto p, @RequestBody AdministradorContenido admin) {
        objAdministradorContenido.eliminarProducto(admin, p);
    }

    @PostMapping("/categoria")
    public Categoria gestionarCategoria(@RequestBody AdministradorContenido admin, @RequestBody Categoria categoria) {
        return objAdministradorContenido.gestionarCategorias(admin, categoria);
    }

    @GetMapping("/producto/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable UUID id) {
        return objAdministradorContenido.obtenerProductoPorId(id);
    }

}
