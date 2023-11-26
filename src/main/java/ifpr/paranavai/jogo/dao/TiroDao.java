package ifpr.paranavai.jogo.dao;
import ifpr.paranavai.jogo.modelo.Tiro;

import java.util.List;

public interface TiroDao {
    public List<Tiro> buscarTodos();
    public Tiro buscarPorId(Integer id);
    public void atualizar(Tiro tiro);
    public void excluir(Tiro tiro);
    public void inserir(Tiro tiro);
}
