package sistemabancario;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre contas, realizadas pela classe {@link GerenciadoraContas}
 * 
 * @author Victor da Fonseca Barboza
 * @date 25/08/2023
 * 
 */
public class GerenciadoraContasTestBefore {

	private GerenciadoraContas gerContas;

	private int idConta01 = 1;
	private int idConta02 = 2;

	private ContaCorrente conta01;
	private ContaCorrente conta02;

	/**
	 * @Before: anotação que roda a montagem do cenário antes de chamar todo método
	 *          com @Test
	 */
	@Before
	public void setUp() {
		/* =====Montagem do cenário de teste===== */
//		criando alguns clientes
		conta01 = new ContaCorrente(idConta01, 0, true);
		conta02 = new ContaCorrente(idConta02, 0, true);

//		inserindo os clientes criados na lista de clientes do banco
		List<ContaCorrente> contaDoBanco = new ArrayList<ContaCorrente>();
		contaDoBanco.add(conta01);
		contaDoBanco.add(conta02);

		/* ========Preparação para execução======== */
		gerContas = new GerenciadoraContas(contaDoBanco);
	}

	@After
	public void tearDown() {
		// *****Desmontagem do cenário global*****//
		gerContas.limpa();
	}

	/**
	 * Função para fazer o teste básico de transferência bancária de um valor de uma
	 * conta de origem para uma conta de destino
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testTransfereValor1() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		conta01.setSaldo(200);
		conta02.setSaldo(0);
		boolean resultadoTranferencia = gerContas.transfereValor(1, 50, 2);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		// assertThat(resultadoTranferencia, is(true)); //é a mesma coisa que a linha 48
		assertTrue(resultadoTranferencia);
		assertThat(conta01.getSaldo(), is(150.0));
		assertThat(conta02.getSaldo(), is(50.0));
	}

	/**
	 * Função para fazer o teste básico de transferência bancária de um valor de uma
	 * conta de origem para uma conta de destino
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testTransfereValor2() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		conta01.setSaldo(100);
		conta02.setSaldo(0);
		boolean resultadoTranferencia = gerContas.transfereValor(1, 200, 2);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertTrue(resultadoTranferencia);
		assertThat(conta01.getSaldo(), is(-100.0));
		assertThat(conta02.getSaldo(), is(200.0));
	}

	/**
	 * Função para fazer o teste básico de transferência bancária de um valor de uma
	 * conta de origem para uma conta de destino
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testTransfereValor3() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		conta01.setSaldo(-100);
		conta02.setSaldo(0);
		boolean resultadoTranferencia = gerContas.transfereValor(1, 200, 2);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertTrue(resultadoTranferencia);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(200.0));
	}

	/**
	 * Função para fazer o teste básico de transferência bancária de um valor de uma
	 * conta de origem para uma conta de destino
	 * 
	 * @author Victor da Fonseca Barboza
	 * @date 25/08/2023
	 */
	@Test
	public void testTransfereValor4() {
		/* =====Montagem do cenário é chamada automaticamente pelo @Before===== */

		/* ========Execução da regra de negócio a ser testada======== */
		conta01.setSaldo(-100);
		conta02.setSaldo(-100);
		boolean resultadoTranferencia = gerContas.transfereValor(1, 200, 2);

		/* =====Execução dos testes pelo JUnit para Análises e Verificações===== */
		assertTrue(resultadoTranferencia);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(100.0));
	}
}
