package com.cadastro.clientes;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cadastro.clientes.mock.CadastroClientesSingleton;
import com.cadastro.clientes.model.Cliente;
import com.cadastro.clientes.model.Endereco;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroClientesApplicationTests {
	
	private CadastroClientesSingleton cadastro;
	
	public CadastroClientesApplicationTests() {
		cadastro = CadastroClientesSingleton.getInstance();
	}
	
	@Test
	public void testeIncluir() {
		Cliente cliente = new Cliente();
		cliente.setId(3);
		cliente.setNome("Teste");
		cliente.setCnpj("12345678910234");
		Endereco endereco = new Endereco();
		endereco.setRua("Avenida joão da mata");
		endereco.setBairro("Jaguaribe");
		endereco.setNumero(351);
		cliente.setEndereco(endereco);
		
		Cliente clienteIncluido = cadastro.incluirCliente(cliente);
		assertEquals(clienteIncluido, cliente);
	}

	@Test
	public void testeAlterar() {
		Cliente cliente = new Cliente();
		cliente.setId(3);
		cliente.setNome("ALTERAÇÃO");
		cliente.setCnpj("12345678910234");
		Endereco endereco = new Endereco();
		endereco.setRua("Avenida joão da mata");
		endereco.setBairro("Jaguaribe");
		endereco.setNumero(351);
		cliente.setEndereco(endereco);
		
		Cliente clienteAlterado = cadastro.alterarCliente(cliente);
		assertEquals(clienteAlterado.getNome(), "ALTERAÇÃO");
	}
	
	@Test
	public void testeListarTodos() {
		Set<Cliente> clientes = cadastro.getClientes();
		assertEquals(clientes.size(), 3);
	}

	@Test
	public void testeBuscarPorCNPJ() {
		Cliente cliente = cadastro.buscarPorCNPJ("12345678910235");
		assertEquals(cliente.getNome(), "CIELO");
	}
	
	@Test
	public void testeBuscarPorID() {
		Cliente cliente = cadastro.buscarPorId(2);
		assertEquals(cliente.getNome(), "CIELO");
	}
}
