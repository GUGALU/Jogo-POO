package ifpr.paranavai.jogo.principal;

import javax.swing.JFrame;

import ifpr.paranavai.jogo.modelo.FaseUm;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import org.hibernate.Session;

public class Principal extends JFrame {
    public static final int LARGURA_DA_JANELA = 1220;
    public static final int ALTURA_DA_JANELA = 630;

    public Principal() {
        FaseUm fase = new FaseUm();
        super.add(fase);
        super.setTitle("Jogo Polli");
        super.setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        Session sessao = HibernateUtil.getSession();
        new Principal();
    }
}