package ifpr.paranavai.jogo.modelo;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "tb_tiro")
public class SuperTiro extends ElementoGrafico{

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_superTiro;

    public Integer getId_superTiro() {
        return id_superTiro;
    }

    public void setId_superTiro(Integer id_superTiro) {
        this.id_superTiro = id_superTiro;
    }

    @Transient
    private static int VELOCIDADE = 3;

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
