package com.cadastro.clientes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.clientes.exception.CustomException;
import com.cadastro.clientes.mock.CadastroClientesSingleton;
import com.cadastro.clientes.model.Cliente;

@RestController
public class ClienteController {

	private CadastroClientesSingleton singletonClientes = CadastroClientesSingleton.getInstance();

	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<String> incluirCliente(@RequestBody Cliente cliente) {
		try {
			singletonClientes.incluirCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (CustomException e) {
			e.printStackTrace();
			return ResponseEntity.status(e.getStatus()).build();
		}
	}
	
	@RequestMapping(value = "/cliente", method = RequestMethod.PUT)
	public ResponseEntity<String> alterarCliente(@RequestBody Cliente cliente) {
		try {
			singletonClientes.alterarCliente(cliente);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (CustomException e) {
			e.printStackTrace();
			return ResponseEntity.status(e.getStatus()).build();
		}
	}

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listarTodos() {
		return 
			new ResponseEntity<List<Cliente>>(new ArrayList<Cliente>(singletonClientes.getClientes()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Integer id) {
		try {
			Cliente cliente = singletonClientes.buscarPorId(id);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getStatus());
		}
	}
	
	@RequestMapping(value = "/cliente/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorCNPJ(@PathVariable("cnpj") String cnpj) {
		try {
			Cliente cliente = singletonClientes.buscarPorCNPJ(cnpj);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getStatus());
		}
	}

}
