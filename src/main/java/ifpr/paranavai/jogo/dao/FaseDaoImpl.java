package ifpr.paranavai.jogo.dao;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Fase;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;


public class FaseDaoImpl implements FaseDao {
    private Session sessao;
    public FaseDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Fase> buscarTodos() {
        Query<Fase> query = this.sessao.createQuery("from Fase",
                Fase.class);
        List<Fase> fase = query.getResultList();
        return fase;
    }

    @Override
    public Fase buscarPorId(Integer id) {
        return this.sessao.find(Fase.class, id);
    }

    @Override
    public void atualizar(Fase fase) {
        try {
            sessao.beginTransaction();
            sessao.merge(fase);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Fase fase) {
        try {
            sessao.beginTransaction();
            sessao.remove(fase);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Fase fase) {
        try {
            sessao.beginTransaction();
            sessao.persist(fase);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
