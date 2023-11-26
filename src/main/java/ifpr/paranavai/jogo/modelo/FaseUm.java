package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.persistence.*;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.dao.InimigoDao;
import ifpr.paranavai.jogo.dao.InimigoDaoImpl;
import ifpr.paranavai.jogo.principal.Principal;
import ifpr.paranavai.jogo.service.InimigoService;

@Entity
@Table(name = "tb_faseUm")
public class FaseUm extends Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fase_id", unique = true, nullable = false)
    private Integer idFaseUm;
    @Column
    private static final int DELAY = 5;
    @Column
    private static final int QTDE_DE_INIMIGOS = 40;
    @Column
    private static final int QTDE_DE_ASTEROIDES = 50;
    @Column
    private static final int PONTOS_POR_INIMIGO = 10;

    public FaseUm() { // Linha adicionada (+)
        super(); // Chamada do construtor da classe super
        this.emJogo = true;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/fundo.jpg"));
        this.fundo = carregando.getImage();

        this.personagem = new Personagem(); // + Criação do objeto Personagem

        this.inicializaElementosGraficosAdicionais();

        this.inicializaInimigos();

        this.timer = new Timer(DELAY, this); // + Criação do objeto Timer
        this.timer.start(); // + Iniciando o nosso jogo

    }

    @Override
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
            InimigoService.inserir(inimigo);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Asteroide asteroide : asteroides) {
                // Desenhar o asteroide na nossa tela.
                graficos.drawImage(asteroide.getImagem(), asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), this);
            }

            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
            // local chamada tiros.
            ArrayList<Tiro> tiros = personagem.getTiros();

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Tiro tiro : tiros) {
                // Desenhar o tiro na nossa tela.
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();

            for(SuperTiro superTiro : superTiros){
                graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), this);
            }

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Inimigo inimigo : inimigos) {
                // Desenhar o inimigo na nossa tela.
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            super.desenhaPontuacao(graficos);
            super.desenhaVida(graficos);
        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/fimdejogo.png"));
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
        }
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        else if (e.getKeyCode() == KeyEvent.VK_Q) {
            personagem.atirarSuper();
        } else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Asteroide asteroide : this.asteroides) {
            asteroide.atualizar();
        }

        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < tiros.size(); i++) {
            // Obter o objeto tiro da posicao i do ArrayList
            Tiro tiro = tiros.get(i);
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (tiro.getPosicaoEmX() > Principal.LARGURA_DA_JANELA || !tiro.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (LARGURA_DA_JANELA)
                tiros.remove(tiro);
            else
                // Atualizar a posição do tiro.
                tiro.atualizar();
        }
        for (int i = 0; i < superTiros.size(); i++) {
            SuperTiro superTiro = superTiros.get(i);
            if (superTiro.getPosicaoEmX() > Principal.LARGURA_DA_JANELA || !superTiro.getEhVisivel())
                superTiros.remove(superTiro);
            else
                superTiro.atualizar();
        }

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < this.inimigos.size(); i++) {
            // Obter o objeto inimigo da posicao i do ArrayList
            Inimigo inimigo = this.inimigos.get(i);
            // Verificar se (if) a posição do x (inimigo.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (0)
                inimigos.remove(inimigo);
            else
                // Atualizar a posição do inimigo.
                inimigo.atualizar();
        }
        this.verificarColisoes();
        repaint();
    }

    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRectangle();

        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                inimigos.remove(inimigo);
                int vidaAtual = this.personagem.getVidas();
                this.personagem.setVidas(vidaAtual - 1);

                if(personagem.getVidas() == 0) {
                    this.personagem.setEhVisivel(false);
                    inimigo.setEhVisivel(false);
                    emJogo = false;
                }

            }
            ArrayList<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    int pontuacaoAtual = this.personagem.getPontuacao();
                    this.personagem.setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }

            ArrayList<SuperTiro> superTiros = this.personagem.getSuperTiros();
            for (int j = 0; j < superTiros.size(); j++) {
                SuperTiro superTiro = superTiros.get(j);
                Rectangle formaSuperTiro = superTiro.getRectangle();
                if (formaInimigo.intersects(formaSuperTiro)) {
                    int pontuacaoAtual = this.personagem.getPontuacao();
                    this.personagem.setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    superTiro.setEhVisivel(false);
                }
            }
        }
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        super.asteroides = new ArrayList<Asteroide>();

        for (int i = 0; i < QTDE_DE_ASTEROIDES; i++) {
            int x = (int) (Math.random() * Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Asteroide asteroide = new Asteroide(x, y);
            super.asteroides.add(asteroide);
        }
    }
}
