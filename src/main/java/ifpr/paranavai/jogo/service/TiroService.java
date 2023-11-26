package ifpr.paranavai.jogo.service;

import ifpr.paranavai.jogo.dao.TiroDao;
import ifpr.paranavai.jogo.dao.TiroDaoImpl;
import ifpr.paranavai.jogo.modelo.Tiro;

import java.util.List;

public class TiroService {
    private static TiroDao dao = new TiroDaoImpl();
    public static List<Tiro> buscarTodos() { return dao.buscarTodos();}
    public static Tiro buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public static void inserir(Tiro tiros) {
        dao.inserir(tiros);
    }
    public static void atualizar(Tiro tiros) {
        dao.atualizar(tiros);
    }
    public static void excluir(Tiro tiros) {
        dao.excluir(tiros);
    }
}
