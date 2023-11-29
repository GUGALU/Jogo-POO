package ifpr.paranavai.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.swing.ImageIcon;

@Entity
@Table(name = "tb_personagem")
public class Personagem extends ElementoGrafico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonagem;

    @OneToOne(mappedBy = "personagem")
    private Fase fase;
    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;
    @Column
    private Integer vidas = 3;
    @Column
    private int pontuacao;
    @OneToMany(mappedBy = "personagem")
    @Transient
    private List<Tiro> tiros;
    @OneToMany(mappedBy = "personagem")
    @Transient
    private List<SuperTiro> superTiros;

    private static final int DESLOCAMENTO = 5;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;
    private int deslocamentoEmX;
    private int deslocamentoEmY;

    public Personagem(String nome) {
        this.nome = nome;
    }

    public Personagem() {
        this.carregar();
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
    }

    public void colissaoBorda() {
        this.getRectangle();
        if (getPosicaoEmX() < 0) {
            setPosicaoEmX(0);
        } else if (getPosicaoEmX() + getLarguraImagem() > 1220) {
            int borda = 1220 - getLarguraImagem();
            setPosicaoEmX(borda);
        }
        if (getPosicaoEmY() < 0) {
            setPosicaoEmY(0);
        } else if (getPosicaoEmY() + getLarguraImagem() > 630) {
            int bordaY = 630 - getLarguraImagem();
            setPosicaoEmY(bordaY);
        }
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/player.png"));
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + this.deslocamentoEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + this.deslocamentoEmY);
        colissaoBorda();
    }

    public void atirar() {
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
    }
    public void atirarSuper(){
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        SuperTiro superTiro = new SuperTiro(frenteDaNave, meioDaNave);
        this.superTiros.add(superTiro);
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            case KeyEvent.VK_W:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_S:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_A:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_D:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_W:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_S:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_A:
                this.deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_D:
                this.deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }


    public List<Tiro> getTiros() {
        return this.tiros;
    }

    public List<SuperTiro> getSuperTiros() {
        return superTiros;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}