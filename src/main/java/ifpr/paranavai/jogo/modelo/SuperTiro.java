package ifpr.paranavai.jogo.modelo;

import javax.swing.*;

public class SuperTiro extends ElementoGrafico{

    private static int VELOCIDADE = 3;

    public SuperTiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.carregar();
        super.setPosicaoEmX(posicaoPersonagemEmX);
        super.setPosicaoEmY(posicaoPersonagemEmY - (this.getAlturaImagem() / 2));
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/superTiro.png"));
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
    }
    
}
