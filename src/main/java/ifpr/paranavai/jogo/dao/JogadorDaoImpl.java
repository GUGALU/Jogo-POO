package ifpr.paranavai.jogo.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Jogador;
public class JogadorDaoImpl implements JogadorDao {
    private Session sessao;
    public JogadorDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }
    @Override
    public List<Jogador> buscarTodos() {
        Query<Jogador> query = this.sessao.createQuery("from Jogador",
                Jogador.class);
        List<Jogador> jogadores = query.getResultList();
        return jogadores;
    }
    @Override
    public Jogador buscarPorId(Integer id) {
        return this.sessao.find(Jogador.class, id);
    }
    @Override
    public void inserir(Jogador jogador) {
        try {
            sessao.beginTransaction();
            sessao.persist(jogador);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void atualizar(Jogador jogador) {
        try {
            sessao.beginTransaction();
            sessao.merge(jogador);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void excluir(Jogador jogador) {
        try {
            sessao.beginTransaction();
            sessao.remove(jogador);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}