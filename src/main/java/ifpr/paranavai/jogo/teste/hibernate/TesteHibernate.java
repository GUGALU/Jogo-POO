package ifpr.paranavai.jogo.teste.hibernate;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Personagem;
import org.hibernate.Session;
public class TesteHibernate {
    public static void main(String[] args) {
        Session sessao = HibernateUtil.getSession();
        sessao.beginTransaction();
        Personagem local = new Personagem("Polli");
        sessao.save(local);
        sessao.getTransaction().commit();
        HibernateUtil.encerraSession();
    }
}