package ifpr.paranavai.jogo.dao;
import java.util.List;

import ifpr.paranavai.jogo.modelo.Personagem;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;

public class PersonagemDaoImpl implements PersonagemDao {
    private Session sessao;
    public PersonagemDaoImpl() {
        this.sessao = HibernateUtil.getSession();
    }
    @Override
    public List<Personagem> buscarTodos() {
        Query<Personagem> query = this.sessao.createQuery("from Personagem",
                Personagem.class);
        List<Personagem> jogadores = query.getResultList();
        return jogadores;
    }
    @Override
    public Personagem buscarPorId(Integer id) {
        return this.sessao.find(Personagem.class, id);
    }
    @Override
    public void inserir(Personagem personagem) {
        try {
            sessao.beginTransaction();
            sessao.persist(personagem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void atualizar(Personagem personagem) {
        try {
            sessao.beginTransaction();
            sessao.merge(personagem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void excluir(Personagem personagem) {
        try {
            sessao.beginTransaction();
            sessao.remove(personagem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}