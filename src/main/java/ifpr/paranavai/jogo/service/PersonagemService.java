package ifpr.paranavai.jogo.service;
import ifpr.paranavai.jogo.dao.PersonagemDao;
import ifpr.paranavai.jogo.dao.PersonagemDaoImpl;
import ifpr.paranavai.jogo.modelo.Personagem;

import java.util.List;
public class PersonagemService {
    private static PersonagemDao dao = new PersonagemDaoImpl();
    public static List<Personagem> buscarTodos() {
        return dao.buscarTodos();
    }
    public static Personagem buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public static void inserir(Personagem personagem) {
        dao.inserir(personagem);
    }
    public static void atualizar(Personagem personagem) {
        dao.atualizar(personagem);
    }
    public static void excluir(Personagem personagem) {
        dao.excluir(personagem);
    }
}