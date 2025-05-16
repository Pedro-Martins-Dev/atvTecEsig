package com.crud.esig.main;

import com.crud.esig.model.Usuario;
import com.crud.esig.service.TarefaService;
import com.crud.esig.service.UsuarioService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@Component
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private final UsuarioService usuarioService;
    private final TarefaService tarefaService;

    @Autowired
    public Main(UsuarioService usuarioService, TarefaService tarefaService) {
        this.usuarioService = usuarioService;
        this.tarefaService = tarefaService;
    }

    public void menu()
    {
        int opcao = -1;
        while (opcao != 0)
        {
            System.out.println("""
                    O que você deseja fazer hoje? 
                    
                    1 - Cadastrar um usuário
                    2 - Cadastrar tarefa
                    3 - Atualizar status da tarefa
                    0 - Sair""");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    usuarioService.cadastrarUsuario();
                    break;
                case 2:
                    tarefaService.cadastrarTarefa(usuarioService, tarefaService);

                    break;

                    case 3:
                    tarefaService.atualizarTarefa(usuarioService);
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:

                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}