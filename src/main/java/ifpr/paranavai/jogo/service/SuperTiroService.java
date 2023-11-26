package ifpr.paranavai.jogo.service;

import ifpr.paranavai.jogo.dao.SuperTiroDao;
import ifpr.paranavai.jogo.dao.SuperTiroDaoImpl;
import ifpr.paranavai.jogo.modelo.SuperTiro;

import java.util.List;

public class SuperTiroService {
    private static SuperTiroDao dao = new SuperTiroDaoImpl();
    public static List<SuperTiro> buscarTodos() {
        return dao.buscarTodos();
    }
    public static SuperTiro buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public static void inserir(SuperTiro superTiros) {
        dao.inserir(superTiros);
    }
    public static void atualizar(SuperTiro superTiros) {
        dao.atualizar(superTiros);
    }
    public static void excluir(SuperTiro superTiros) {
        dao.excluir(superTiros);
    }
}
