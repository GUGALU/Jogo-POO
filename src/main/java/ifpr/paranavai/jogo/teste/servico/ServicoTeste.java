package ifpr.paranavai.jogo.teste.servico;
import ifpr.paranavai.jogo.modelo.SuperTiro;
import ifpr.paranavai.jogo.service.SuperTiroService;

public class ServicoTeste {
    public static void main(String[] args) {
        SuperTiro superLocal = new SuperTiro();
        SuperTiroService.inserir(superLocal);
    }
}