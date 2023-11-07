package ifpr.paranavai.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Personagem extends ElementoGrafico {
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;

    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private int pontuacao;

    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;

    public Personagem() {
        this.carregar();
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.colissaoBorda();
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/player.png"));
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + this.deslocamentoEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + this.deslocamentoEmY);
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

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return superTiros;
    }

    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}