package com.crud.esig.service;

import com.crud.esig.enuns.Prioridades;
import com.crud.esig.model.Tarefa;
import com.crud.esig.model.Usuario;
import com.crud.esig.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class TarefaService
{
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas()
    {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id)
    {
        return tarefaRepository.findById(id).orElse(null);
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

    public Usuario buscarUsuarioPorNome(UsuarioService usuarioService, TarefaService tarefaService, Scanner scanner) 
    {
        Usuario usuarioResponsavel = null;

        while (usuarioResponsavel == null) {
            System.out.println("Insira o nome do responsável: ");
            String nomeParcial = scanner.nextLine();

            List<Usuario> usuariosEncontrados = usuarioService.buscarPorNomeContendo(nomeParcial);

            if (usuariosEncontrados.isEmpty()) {
                System.out.println("Nenhum usuário encontrado com esse nome.");
                System.out.println("""
                        Escolha uma opção:
                        1 - Tentar buscar novamente
                        2 - Voltar para o menu
                        3 - Cadastrar um novo usuário
                        """);

                String escolhaStr = scanner.nextLine();
                int escolha;
                try {
                    escolha = Integer.parseInt(escolhaStr);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }

                switch (escolha) {
                    case 1:
                        continue;
                    case 2:
                        System.out.println("Voltando ao menu...");
                        return new Usuario("Padrão");
                    case 3:
                        System.out.println("Digite o nome do novo usuário: ");
                        String novoNome = scanner.nextLine();
                        Usuario novoUsuario = new Usuario(novoNome);
                        usuarioService.salvarUsuario(novoUsuario);
                        usuarioResponsavel = novoUsuario;
                        System.out.println("Usuário cadastrado com sucesso!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Usuários encontrados:");
                for (int i = 0; i < usuariosEncontrados.size(); i++) {
                    System.out.println((i + 1) + " - " + usuariosEncontrados.get(i).getNome());
                }

                System.out.println("Escolha um usuário pelo número correspondente:");
                String escolhaUsuarioStr = scanner.nextLine();
                int escolhaUsuario;

                try {
                    escolhaUsuario = Integer.parseInt(escolhaUsuarioStr);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }

                if (escolhaUsuario > 0 && escolhaUsuario <= usuariosEncontrados.size()) {
                    usuarioResponsavel = usuariosEncontrados.get(escolhaUsuario - 1);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                    usuarioResponsavel = null;
                }
            }
        }
        return usuarioResponsavel; 
    }

    public void cadastrarTarefa(UsuarioService usuarioService, TarefaService tarefaService) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome da tarefa: ");
        String titulo = scanner.nextLine();

        System.out.println("Insira a descrição da tarefa: ");
        String descricao = scanner.nextLine();

        Usuario usuarioResponsavel = tarefaService.buscarUsuarioPorNome(usuarioService, tarefaService, scanner);
    
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
        }

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
}

