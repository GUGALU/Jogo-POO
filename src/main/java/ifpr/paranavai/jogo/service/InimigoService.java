package ifpr.paranavai.jogo.service;

import ifpr.paranavai.jogo.dao.InimigoDao;
import ifpr.paranavai.jogo.dao.InimigoDaoImpl;
import ifpr.paranavai.jogo.modelo.Inimigo;


import java.util.List;

public class InimigoService {
    private static InimigoDao dao = new InimigoDaoImpl();
    public static List<Inimigo> buscarTodos() {
        return dao.buscarTodos();
    }
    public static Inimigo buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public static void inserir(Inimigo inimigo) {
        dao.inserir(inimigo);
    }
    public static void atualizar(Inimigo inimigo) {
        dao.atualizar(inimigo);
    }
    public static void excluir(Inimigo inimigo) {
        dao.excluir(inimigo);
    }
}
