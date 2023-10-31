package ifpr.paranavai.jogo.tratamento;

public class Calculadora {
    public static double divisao(double dividendo, double divisor) throws MinhaExcecao {
        if (divisor == 0)
            throw new MinhaExcecao(MensagemUtil.EXCECAO_DIVISAO);

        return dividendo / divisor;

    }
}
