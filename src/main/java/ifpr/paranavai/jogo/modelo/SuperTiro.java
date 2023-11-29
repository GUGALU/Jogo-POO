package ifpr.paranavai.jogo.modelo;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "tb_superTiro")
public class SuperTiro extends ElementoGrafico{
    @ManyToOne
    @JoinColumn(name = "fk_personagem")
    private Personagem personagem;

    @Transient
    private static int VELOCIDADE = 10;

    public SuperTiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.carregar();
        super.setPosicaoEmX(posicaoPersonagemEmX);
        super.setPosicaoEmY(posicaoPersonagemEmY - (this.getAlturaImagem() / 2));
    }

    public SuperTiro() {

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
