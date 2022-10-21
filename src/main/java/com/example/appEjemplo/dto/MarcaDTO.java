package com.example.appEjemplo.dto;

import com.example.appEjemplo.model.Producto;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaDTO {
    
    @NotBlank
    private int id;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private Set<Producto> productos = new HashSet<>();

    public MarcaDTO() {
    }

    public MarcaDTO(String nombre) {
        this.nombre = nombre;
    }
   
    
    
}
