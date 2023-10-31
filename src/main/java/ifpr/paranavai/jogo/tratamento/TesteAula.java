package ifpr.paranavai.jogo.tratamento;

public class TesteAula {
    public static void main(String[] args) {
        boolean carregando = true;
        
        try {
            Calculadora.divisao(10, 0);
        } catch (MinhaExcecao e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            System.out.println(MensagemUtil.MAIN_BANCO_DE_DADOS);
            carregando = false;
        } catch (ArithmeticException e){
            carregando = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            carregando = false;
        }catch (Exception e) {
            carregando = false;
        } 

        System.out.println("Janela sendo exibida: " + carregando);
    }
}
