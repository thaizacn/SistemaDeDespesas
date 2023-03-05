package br.com.despesas.dao.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.despesas.dao.DespesaDAO;
import br.com.despesas.model.Despesa;

public class DespesaDAOImpl implements DespesaDAO {
	
    private List<Despesa> listaDespesas = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void adicionar(Despesa despesa) {
        despesa.setId(proximoId);
        proximoId++;
        listaDespesas.add(despesa);
    }

    @Override
    public void atualizar(Despesa despesa) {
        for (Despesa d : listaDespesas) {
            if (d.getId() == despesa.getId()) {
                d.setValor(despesa.getValor());
                d.setData(despesa.getData());
                d.setDescricao(despesa.getDescricao());
                d.setCategoria(despesa.getCategoria());
                break;
            }
        }
    }

    @Override
    public void excluir(int id) {
        listaDespesas.removeIf(d -> d.getId() == id);
    }

    @Override
    public Despesa recuperarPorId(int id) {
        for (Despesa d : listaDespesas) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    @Override
    public List<Despesa> recuperarTodos() {
        return listaDespesas;
    }
}

