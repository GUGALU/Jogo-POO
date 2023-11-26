package ifpr.paranavai.jogo.dao;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Tiro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TiroDaoImpl implements TiroDao {
    private Session sessao;
    public TiroDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Tiro> buscarTodos() {
        Query<Tiro> query = this.sessao.createQuery("from TiroController",
                Tiro.class);
        List<Tiro> tiros = query.getResultList();
        return tiros;
    }

    @Override
    public Tiro buscarPorId(Integer id) {
        return this.sessao.find(Tiro.class, id);}

    @Override
    public void atualizar(Tiro tiro) {
        try {
            sessao.beginTransaction();
            sessao.merge(tiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Tiro tiro) {
        try {
            sessao.beginTransaction();
            sessao.remove(tiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Tiro tiro) {
        try {
            sessao.beginTransaction();
            sessao.persist(tiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
