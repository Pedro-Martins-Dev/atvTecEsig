package com.crud.esig.service;

import com.crud.esig.enuns.*;
import com.crud.esig.model.Tarefa;
import com.crud.esig.model.Usuario;
import com.crud.esig.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class TarefaService
{
    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas()
    {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> buscarPorTitulo(String titulo)
    {
        return tarefaRepository.findByTituloContaining(titulo);
    }

    public Tarefa salvar(Tarefa tarefa)
    {
        return tarefaRepository.save(tarefa);
    }

    public void deletar(Long id)
    {
        tarefaRepository.deleteById(id);
    }

    public void cadastrarTarefa(UsuarioService usuarioService, TarefaService tarefaService) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome da tarefa: ");
        String titulo = scanner.nextLine();

        System.out.println("Insira a descrição da tarefa: ");
        String descricao = scanner.nextLine();

        Usuario usuarioResponsavel = usuarioService.buscarUsuarioPorNome(usuarioService, tarefaService, scanner);
    
        Prioridades prioridade = null;
        while (prioridade == null) {
            System.out.println("Qual a prioridade da tarefa? (ALTA, MEDIA, BAIXA)");
            String prioridadeString = scanner.nextLine().toUpperCase();

            try {
                prioridade = Prioridades.valueOf(prioridadeString);
            } catch (IllegalArgumentException e) {
                System.out.println("""
                        Só trabalhamos com três tipos de prioridade:
                        
                        ALTA
                        MEDIA
                        BAIXA
                        
                        Por favor, insira uma prioridade válida.
                        """);
            }
        };

        LocalDate dataConclusaoPrevista = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (dataConclusaoPrevista == null) {
            System.out.println("Insira a data de conclusão prevista (dd/MM/yyyy): ");
            String dataString = scanner.nextLine();

            try {
                dataConclusaoPrevista = LocalDate.parse(dataString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Erro: Data inválida! Certifique-se de usar o formato correto (dd/MM/yyyy).");
            }
        }

        Tarefa tarefa = new Tarefa(titulo, descricao, prioridade, usuarioResponsavel, dataConclusaoPrevista);

        tarefaService.salvar(tarefa);

        System.out.println("Tarefa cadastrada com sucesso");
    }

    public String atualizarNomeTarefa()
    {
        System.out.println("Digite o novo nome da tarefa: ");
        String novoNome = scanner.nextLine();

        return novoNome;
    }

    public String atualizarDescricaoTarefa()
    {
        System.out.println("Digite a nova descrição da tarefa: ");
        String novaDescricao = scanner.nextLine();


        return novaDescricao;
    }

    public Prioridades atualizarPrioridadeTarefa()
    {
        System.out.println("Digite a nova prioridade da tarefa (ALTA, MEDIA, BAIXA): ");
        String novaPrioridade = scanner.nextLine().toUpperCase();

        try
        {
            return Prioridades.valueOf(novaPrioridade);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Prioridade inválida. Tente novamente.");
            return atualizarPrioridadeTarefa();
        }
    }

    public LocalDate atualizarDataConclusaoPrevistaTarefa()
    {
        System.out.println("Digite a nova data de conclusão (dd/MM/yyyy): ");
        String novaDataString = scanner.nextLine();
        LocalDate novaDataConclusao = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try
        {
            novaDataConclusao = LocalDate.parse(novaDataString, formatter);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Data inválida. Tente novamente.");
            return atualizarDataConclusaoPrevistaTarefa();
        }
        return novaDataConclusao;
    }

    public LocalDate atualizarDataConclusaoTarefa()
    {
        System.out.println("Digite a nova data de conclusão (dd/MM/yyyy): ");
        String novaDataString = scanner.nextLine();
        LocalDate novaDataConclusao = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try
        {
            novaDataConclusao = LocalDate.parse(novaDataString, formatter);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Data inválida. Tente novamente.");
            return atualizarDataConclusaoTarefa();
        }
        return novaDataConclusao;
    }

    public Status atualizarStatusTarefa()
    {
        System.out.println("Qual o status da tarefa? (CONCLUIDA, EM ANDAMENTO OU PENDENTE): ");
        String statusString = scanner.nextLine().toUpperCase();

        try
        {
            return Status.valueOf(statusString);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Status inválido. Tente novamente.");
            return atualizarStatusTarefa();
        }
    }

    public void atualizarTarefa(UsuarioService usuarioService) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do usuário responsável pela tarefa: ");
        Usuario usuarioBuscado = usuarioService.buscarUsuarioPorNome(usuarioService, this, scanner);

        List<Tarefa> tarefas = listarTodas()
                .stream()
                .filter(t -> t.getUsuarioResponsavel().getId().equals(usuarioBuscado.getId()))
                .toList();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para o usuário " + usuarioBuscado.getNome());
            return;
        }

        System.out.println("Tarefas encontradas para o usuário " + usuarioBuscado.getNome() + ":");
        for (Tarefa tarefa : tarefas) {
            System.out.println("\n");
            System.out.println(tarefa.toString());
        }

        System.out.println("Digite o ID da tarefa que deseja atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Tarefa tarefa = listarTodas()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        int opcoes = -1;

        while (opcoes != 0) {
            System.out.println("""
                    O que você deseja atualizar?

                    1 - Nome
                    2 - Descrição
                    3 - Prioridade
                    4 - Data de conclusão prevista
                    5 - Data de conclusão
                    6 - Status
                    0 - Sair""");

            opcoes = scanner.nextInt();
            scanner.nextLine();

            switch (opcoes) {
                case 1:
                    tarefa.setTitulo(atualizarNomeTarefa());
                    break;
                case 2:
                    tarefa.setDescricao(atualizarDescricaoTarefa());
                    break;
                case 3:
                    tarefa.setPrioridade(atualizarPrioridadeTarefa());
                    break;
                case 4:
                    tarefa.setDataConclusaoPrevista(atualizarDataConclusaoPrevistaTarefa());
                    break;
                case 5:
                    tarefa.setDataConclusao(atualizarDataConclusaoTarefa());
                    if (tarefa.getDataConclusao() != null)
                        tarefa.setStatus(Status.CONCLUIDA);
                    else
                        tarefa.setStatus(Status.PENDENTE);
                    break;
                case 6:
                    tarefa.setStatus(atualizarStatusTarefa());
                    if (tarefa.getStatus() == Status.CONCLUIDA)
                        tarefa.setDataConclusao(LocalDate.now());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        tarefaRepository.save(tarefa);
        System.out.println("Tarefa atualizada com sucesso!");
    }

}

