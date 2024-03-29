package com.ailton.cursomc.resources;

import java.net.URI;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ailton.cursomc.domain.Categoria;
import com.ailton.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")

public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)

	//	public String listar()
	
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
		//Categoria cat1 = new Categoria(1, "Informática"); 
		//Categoria cat2 = new Categoria (2, "Escritório");
		//List<Categoria> lista = new ArrayList<>();		
		//lista.add(cat1);
		//lista.add(cat2);
		//return lista;
		//return "Rest está funcionando!";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
