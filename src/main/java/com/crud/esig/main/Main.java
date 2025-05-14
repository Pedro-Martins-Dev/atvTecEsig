package com.crud.esig.main;

import com.crud.esig.model.Usuario;
import com.crud.esig.service.UsuarioService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@Component
public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    private final UsuarioService usuarioService;

    @Autowired
    public Main(UsuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }

    public void menu()
    {
        int opcao = -1;
        while (opcao != 0)
        {
            System.out.println("""
                    O que você deseja fazer hoje? 
                    
                    1 - Cadastrar um usuário
                    2 - Listar Todos os usuários
                    0 - Sair""");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao)
            {
                case 1 -> cadastrarUsuario();
                case 2 -> listarUsuarios();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.println("Insira o nome do usuário que você deseja cadastrar: ");
        String nome = scanner.nextLine();

        Usuario usuario = new Usuario(nome);
        usuarioService.salvar(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void listarUsuarios() {
        usuarioService.listarTodos().forEach(usuario ->
                System.out.println("Usuário: " + usuario.getNome()));
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> b7d63ae (fiz rodar o back sem as telas)
