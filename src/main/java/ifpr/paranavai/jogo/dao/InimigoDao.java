package ifpr.paranavai.jogo.dao;

import ifpr.paranavai.jogo.modelo.Inimigo;

import java.util.List;

public interface InimigoDao {
    public List<Inimigo> buscarTodos();
    public Inimigo buscarPorId(Integer id);
    public void atualizar(Inimigo inimigo);
    public void excluir(Inimigo inimigo);
    public void inserir(Inimigo inimigo);
}
