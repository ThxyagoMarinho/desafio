package com.cadastro.clientes.mock;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;

import com.cadastro.clientes.exception.CustomException;
import com.cadastro.clientes.model.Cliente;
import com.cadastro.clientes.model.Endereco;

public class CadastroClientesSingleton {

	private static CadastroClientesSingleton instancia;
	private Set<Cliente> clientes;

	private CadastroClientesSingleton() {
		this.clientes = new HashSet<Cliente>();
		inicializarListaDeClientes();
	}

	public static CadastroClientesSingleton getInstance() {
		if (instancia == null)
			instancia = new CadastroClientesSingleton();
		return instancia;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}
	
	public Cliente buscarPorId(Integer id) {

		for (Cliente cliente : clientes) {
			if (cliente.getId().equals(id)) {
				return cliente;
			}
		}

		throw new CustomException(Constantes.CLIENTE_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
	}
	
	public Cliente buscarPorCNPJ(String cnpj) {

		for (Cliente cliente : clientes) {
			if (cliente.getCnpj().equals(cnpj)) {
				return cliente;
			}
		}

		throw new CustomException(Constantes.CLIENTE_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
	}

	public Cliente incluirCliente(Cliente cliente) {

		if (cliente == null || !cliente.isValido()) {
			throw new CustomException(Constantes.DADOS_CLIENTE_INVALIDOS, HttpStatus.BAD_REQUEST);
		}

		this.clientes.add(cliente);
		return cliente;
	}
	
	/**
	 * @see Como a interface SET não aceita elementos repetidos, e nesse caso o equals foi sobrescrito na classe cliente e realizará a 
	 * comparação por ID, então o método add irá excluir e inserir o novo elemento que representa a alteração.
	 * */
	public Cliente alterarCliente(Cliente cliente) {
		
		if (cliente == null || !cliente.isValido()) {
			throw new CustomException(Constantes.DADOS_CLIENTE_INVALIDOS, HttpStatus.BAD_REQUEST);
		}
		
		for (Cliente registro : clientes) {
			if (registro.equals(cliente)) {
				this.clientes.remove(registro);
				this.clientes.add(cliente);
				return cliente;
			}
		}

		throw new CustomException(Constantes.CLIENTE_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST);
	}

	private void inicializarListaDeClientes() {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Thiago Solutions");
		cliente.setCnpj("12345678910234");
		Endereco endereco = new Endereco();
		endereco.setRua("Avenida joão da mata");
		endereco.setBairro("Jaguaribe");
		endereco.setNumero(351);
		cliente.setEndereco(endereco);

		Cliente cliente2 = new Cliente();
		cliente2.setId(2);
		cliente2.setNome("CIELO");
		cliente2.setCnpj("12345678910235");
		Endereco endereco2 = new Endereco();
		endereco2.setRua("Alameda Xingu");
		endereco2.setBairro("Alphaville – Barueri");
		endereco2.setNumero(512);
		cliente2.setEndereco(endereco2);

		this.clientes.add(cliente);
		this.clientes.add(cliente2);
	}

}
