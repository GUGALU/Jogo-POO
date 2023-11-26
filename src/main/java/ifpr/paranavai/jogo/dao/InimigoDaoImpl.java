package ifpr.paranavai.jogo.dao;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Inimigo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class InimigoDaoImpl implements InimigoDao{
    private Session sessao;
    public InimigoDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }
    @Override
    public List<Inimigo> buscarTodos() {
        Query<Inimigo> query = this.sessao.createQuery("from InimigoController",
                Inimigo.class);
        List<Inimigo> inimigos = query.getResultList();
        return inimigos;
    }
    @Override
    public Inimigo buscarPorId(Integer id) {
        return this.sessao.find(Inimigo.class, id);
    }

    @Override
    public void atualizar(Inimigo inimigo) {
        try {
            sessao.beginTransaction();
            sessao.merge(inimigo);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Inimigo inimigo) {
        try {
            sessao.beginTransaction();
            sessao.remove(inimigo);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Inimigo inimigo) {
        try {
            sessao.beginTransaction();
            sessao.persist(inimigo);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
