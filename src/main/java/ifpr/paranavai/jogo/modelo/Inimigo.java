package ifpr.paranavai.jogo.modelo;

import javax.persistence.*;
import javax.swing.ImageIcon;

@Entity
@Table(name = "tb_inimigo")
public class Inimigo extends ElementoGrafico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inimigo_id",unique = true, nullable = false)
    private Integer idInimigo;

    public Inimigo(Integer idInimigo) {
        this.idInimigo = idInimigo;
    }

    private static int VELOCIDADE = 2;

    public Inimigo(int xAleatorio, int yAleatorio) {
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }

    public Inimigo() {

    }



    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/inimigo.png"));
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
    }
}
