package br.com.despesas.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.com.despesas.dao.DespesaDAO;
import br.com.despesas.dao.impl.DespesaDAOImpl;
import br.com.despesas.model.Despesa;

public class MenuDespesas {

    private DespesaDAO dao = new DespesaDAOImpl();
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("===== MENU DE DESPESAS =====");
            System.out.println("1 - Adicionar despesa");
            System.out.println("2 - Atualizar despesa");
            System.out.println("3 - Excluir despesa");
            System.out.println("4 - Listar todas as despesas");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    adicionarDespesa();
                    break;
                case 2:
                    atualizarDespesa();
                    break;
                case 3:
                    excluirDespesa();
                    break;
                case 4:
                    listarDespesas();
                    break;
                case 5:
                    System.out.println("Saindo do sistema de gerenciamento de despesas...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 5);
    }

    private void adicionarDespesa() {
        Despesa despesa = new Despesa();
        System.out.print("Digite o valor da despesa: ");
        double valor = scanner.nextDouble();
        despesa.setValor(valor);
        scanner.nextLine(); // consome a quebra de linha após a entrada do valor
        System.out.print("Digite a data da despesa (no formato dd/mm/aaaa): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), formatter);
        despesa.setData(data);
        System.out.print("Digite a descrição da despesa: ");
        String descricao = scanner.nextLine();
        despesa.setDescricao(descricao);
        System.out.print("Digite a categoria da despesa: ");
        String categoria = scanner.nextLine();
        despesa.setCategoria(categoria);
        dao.adicionar(despesa);
        System.out.println("Despesa adicionada com sucesso.");
    }

    private void atualizarDespesa() {
        System.out.print("Digite o ID da despesa que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consome a quebra de linha após a entrada do ID
        Despesa despesa = dao.recuperarPorId(id);
        if (despesa == null) {
            System.out.println("Despesa não encontrada.");
            return;
        }
        System.out.println("Despesa encontrada:");
        System.out.println(despesa);
        System.out.print("Digite o novo valor da despesa: ");
        double valor = scanner.nextDouble();
        despesa.setValor(valor);
        scanner.nextLine(); // consome a quebra de linha após a entrada do valor
        System.out.print("Digite a nova data da despesa (no formato dd/mm/aaaa): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), formatter);
        despesa.setData(data);
        System.out.print("Digite a nova descrição da despesa: ");
        String descricao = scanner.nextLine();
        despesa.setDescricao(descricao);
        System.out.print("Digite a nova categoria da despesa: ");
        String categoria = scanner.nextLine();
        despesa.setCategoria(categoria);
        dao.adicionar(despesa);
        System.out.println("Despesa atualizada com sucesso.");
        
    }
    
    private void excluirDespesa() {
        System.out.println("Informe o ID da despesa a ser excluída: ");
        int id = scanner.nextInt();
        Despesa despesa = dao.recuperarPorId(id);
        
        if (despesa == null) {
            System.out.println("Despesa não encontrada!");
        } else {
            dao.excluir(id);
            System.out.println("Despesa excluída com sucesso!");
        }
    }

    private void listarDespesas() {
        List<Despesa> despesas = dao.recuperarTodos();
        if (despesas.isEmpty()) {
            System.out.println("Não existem despesas cadastradas!");
        } else {
            System.out.println("------ LISTA DE DESPESAS ------");
            for (Despesa despesa : despesas) {
                System.out.println("ID: " + despesa.getId());
                System.out.println("Descrição: " + despesa.getDescricao());
                System.out.println("Valor: " + despesa.getValor());
                System.out.println("Data: " + despesa.getData());
                System.out.println("Categoria: " + despesa.getCategoria());
                System.out.println("-----------------------------");
            }
        }
    }
}

