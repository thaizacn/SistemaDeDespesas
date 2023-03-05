package br.com.despesas.dao;

import java.util.List;

import br.com.despesas.model.Despesa;

public interface DespesaDAO {
    void adicionar(Despesa despesa);

    void atualizar(Despesa despesa);

    void excluir(int id);

    Despesa recuperarPorId(int id);

    List<Despesa> recuperarTodos();
}
