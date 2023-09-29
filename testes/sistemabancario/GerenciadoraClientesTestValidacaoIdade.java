package sistemabancario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clinetes, realizadas pela classe {@link GerenciadoraClientes}
 * 
 * @author Victor da Fonseca Barboza
 * @date 18/08/2023
 * 
 */

public class GerenciadoraClientesTestValidacaoIdade {

	private GerenciadoraClientes gerClientes;

	private int idCliente01 = 1;
	private int idCliente02 = 2;

	/**
	 * @Before: anotação que roda a montagem do cenário antes de chamar todo método
	 *com @Test
	 */
	@Before
	public void setUp() {
		/* =====Montagem do cenário de teste===== */
//		criando alguns clientes
		Cliente cliente01 = new Cliente(idCliente01, "Joao da Silva", 47, "joaodasilva@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 10, "mariadasilva@gmail.com", 1, true);

//		inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		/* ========Preparação para execução======== */
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		// *****Desmontagem do cenário global*****//
		gerClientes.limpa();
	}

	/**
	 * Teste basico da pesquisa de um cliente a partir do seu ID
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 18/08/2023
	 */
	@Test
	public void testPesquisaCliente() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertThat(cliente.getId(), is(idCliente01));
	}

	/**
	 * Teste básico da remoção de um cliente a partir do ID
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testRemoveCliente() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		boolean resultadoRemocaoCliente = gerClientes.removeCliente(2);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertThat(resultadoRemocaoCliente, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));
	}

	/**
	 * Teste básico da remoção de um cliente inexistente a partir do ID
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testRemoveClienteInexistente() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		boolean resultadoRemocaoCliente = gerClientes.removeCliente(10);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertThat(resultadoRemocaoCliente, is(false));
		assertFalse(resultadoRemocaoCliente);
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}

	/**
	 * Teste basico da pesquisa de um cliente inexistente a partir do seu ID
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testPesquisaClienteInexistente() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		Cliente cliente = gerClientes.pesquisaCliente(13);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertNull(cliente);
	}

	/**
	 * Validação para quando o cliente está no intervalo de idade permitido
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 08/09/2023
	 */
	@Test
	public void testClienteIdadePermitida1() throws IdadeNaoPermitidaException {
		/* ========Montagem do cenário de teste======== */
		Cliente cliente = new Cliente(3, "Victor", 30, "victor@email.com", 3, true);
		/* ========Execução da regra de negócio a ser testada======== */
		boolean resultadoIdade = gerClientes.validaIdade(cliente.getIdade());
		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertTrue(resultadoIdade);
	}

	/**
	 * Validação para quando o cliente está no intervalo de idade permitido dentro
	 * da borda inferior
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 08/09/2023
	 */
	@Test
	public void testClienteIdadePermitida2() throws IdadeNaoPermitidaException {
		/* ========Montagem do cenário de teste======== */
		Cliente cliente = new Cliente(4, "Pedro", 18, "pedro@email.com", 4, true);
		/* ========Execução da regra de negócio a ser testada======== */
		boolean resultadoIdade = gerClientes.validaIdade(cliente.getIdade());
		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertTrue(resultadoIdade);
	}

	/**
	 * Validação para quando o cliente está no intervalo de idade permitido dentro
	 * da borda superior
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 08/09/2023
	 */
	@Test
	public void testClienteIdadePermitida3() throws IdadeNaoPermitidaException {
		/* ========Montagem do cenário de teste======== */
		Cliente cliente = new Cliente(5, "Clayton", 65, "clayton@email.com", 5, true);
		/* ========Execução da regra de negócio a ser testada======== */
		boolean resultadoIdade = gerClientes.validaIdade(cliente.getIdade());
		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertTrue(resultadoIdade);
	}

	/**
	 * Validação para quando o cliente está no intervalo de idade permitido fora da
	 * borda superior
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 08/09/2023
	 */
	@Test
	public void testClienteIdadePermitida4() throws IdadeNaoPermitidaException {
		/* ========Montagem do cenário de teste======== */
		Cliente cliente = new Cliente(6, "Victor", 17, "victor@email.com", 6, true);
		/* ========Execução da regra de negócio a ser testada======== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

	/**
	 * Validação para quando o cliente está no intervalo de idade permitido fora da
	 * borda superior
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 08/09/2023
	 */
	@Test
	public void testClienteIdadePermitida5() throws IdadeNaoPermitidaException {
		/* ========Montagem do cenário de teste======== */
		Cliente cliente = new Cliente(7, "Jose", 66, "jose@email.com", 7, true);
		/* ========Execução da regra de negócio a ser testada======== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

}
