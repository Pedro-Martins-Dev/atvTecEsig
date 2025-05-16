package com.crud.esig.service;

import com.crud.esig.model.Usuario;
import com.crud.esig.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Scanner;

@Service
public class UsuarioService
{
    private static final Scanner scanner = new Scanner(System.in);

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id)
    {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario salvarUsuario(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id)
        {
        usuarioRepository.deleteById(id);
    }

    public void cadastrarUsuario()
    {
        System.out.println("Insira o nome do usuário que você deseja cadastrar: ");
        String nome = scanner.nextLine();

        Usuario usuario = new Usuario(nome);
        salvarUsuario(usuario);
    }

    public Usuario buscarPorNome(String nome)
    {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);
        return usuarioOptional.orElse(null);
    }

    public List<Usuario> buscarPorNomeContendo(String nomeParcial) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nomeParcial);
    }

    public Usuario buscarUsuarioPorNome(UsuarioService usuarioService, TarefaService tarefaService, Scanner scanner) 
    {
        Usuario usuarioResponsavel = null;

        while (usuarioResponsavel == null) {
            System.out.println("Insira o nome do responsável: ");
            String nomeParcial = scanner.nextLine();

            List<Usuario> usuariosEncontrados = buscarPorNomeContendo(nomeParcial);

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
}
