package ifpr.paranavai.jogo.dao;

import ifpr.paranavai.jogo.modelo.SuperTiro;

import java.util.List;

public interface SuperTiroDao {
    public List<SuperTiro> buscarTodos();
    public SuperTiro buscarPorId(Integer id);
    public void atualizar(SuperTiro superTiro);
    public void excluir(SuperTiro superTiro);
    public void inserir(SuperTiro superTiro);
}
