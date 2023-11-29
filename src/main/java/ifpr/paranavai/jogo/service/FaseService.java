package ifpr.paranavai.jogo.service;

import ifpr.paranavai.jogo.dao.FaseDaoImpl;
import ifpr.paranavai.jogo.modelo.Fase;

import java.util.List;

public class FaseService {
    private static FaseDaoImpl dao = new FaseDaoImpl();
    public static List<Fase> buscarTodos() {
        return dao.buscarTodos();
    }
    public static Fase buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public static void inserir(Fase fase) {
        dao.inserir(fase);
    }
    public static void atualizar(Fase fase) {
        dao.atualizar(fase);
    }
    public static void excluir(Fase fase) {
        dao.excluir(fase);
    }

}
