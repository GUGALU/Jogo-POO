package ifpr.paranavai.jogo.modelo;

import javax.persistence.*;
import javax.swing.ImageIcon;

import ifpr.paranavai.jogo.principal.Principal;

@Entity
@Table(name = "tb_asteroide")
public class Asteroide extends ElementoGrafico {

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    private Fase fase;

    private static int VELOCIDADE = 1;

    public Asteroide(int xAleatorio, int yAleatorio) {
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/asteroide.png"));
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        if (this.getPosicaoEmX() < 0) {
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            super.setPosicaoEmX(Principal.LARGURA_DA_JANELA);
            super.setPosicaoEmY(y);
        } else {
            super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        }
    }
}
