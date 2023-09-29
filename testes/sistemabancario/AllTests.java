package sistemabancario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({GerenciadoraClientesTest.class, GerenciadoraClientesTestBefore.class, GerenciadoraClientesTestValidacaoIdade.class, GerenciadoraContasTest.class, GerenciadoraContasTestBefore.class})
public class AllTests {

}
