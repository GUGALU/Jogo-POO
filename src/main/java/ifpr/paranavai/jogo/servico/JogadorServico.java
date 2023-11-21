package ifpr.paranavai.jogo.servico;
import ifpr.paranavai.jogo.modelo.Jogador;
import ifpr.paranavai.jogo.dao.JogadorDao;
import ifpr.paranavai.jogo.dao.JogadorDaoImpl;
import java.util.List;
public class JogadorServico {
    private static JogadorDao dao = new JogadorDaoImpl();
    public static List<Jogador> buscarTodos() {
        return dao.buscarTodos();
    }
    public static Jogador buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public static void inserir(Jogador jogador) {
        dao.inserir(jogador);
    }
    public static void atualizar(Jogador jogador) {
        dao.atualizar(jogador);
    }
    public static void excluir(Jogador jogador) {
        dao.excluir(jogador);
    }
}