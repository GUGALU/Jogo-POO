package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.principal.Principal;

public class FaseDois extends Fase {
    public static final int DELAY = 5;
    public static final int QTDE_DE_INIMIGOS = 40;

    protected Image fundo;
    protected Personagem personagem;
    protected ArrayList<Inimigo> inimigos;
    protected Timer timer;
    protected boolean emJogo;

    public FaseDois() { // Linha adicionada (+)
        setFocusable(true); // + define o foco inicial do jogo
        setDoubleBuffered(true); // + Otimização computacional
        ImageIcon carregando = new ImageIcon(getClass().getResource("/fundo.jpg"));
        fundo = carregando.getImage();

        personagem = new Personagem(); // + Criação do objeto Personagem

        this.inicializaInimigos();

        addKeyListener(this); // + Definindo que a própria classe irá controlar os eventos do teclado

        timer = new Timer(DELAY, this); // + Criação do objeto Timer
        timer.start(); // + Iniciando o nosso jogo

        emJogo = true;
    }

    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * Principal.LARGURA_DA_JANELA) + 8000);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
            // local chamada tiros.
            ArrayList<Tiro> tiros = personagem.getTiros();

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Tiro tiro : tiros) {
                // Desenhar o tiro na nossa tela.
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
            for (Inimigo inimigo : inimigos) {
                // Desenhar o inimigo na nossa tela.
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/fimdejogo.png"));
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, null);
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

        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();

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

    public void verificarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRectangle();

        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                this.personagem.setEhVisivel(false);
                inimigo.setEhVisivel(false);
                emJogo = false;
            }
            ArrayList<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(i);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicializaElementosGraficosAdicionais'");
    }

}
