package model;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        // Teste inicial dos repositorios
        try {
            // Adicionando algumas pessoas fisicas e juridicas para o teste
            repoFisica.inserir(new PessoaFisica(1, "Joao Silva", "123.456.789-00", 30));
            repoFisica.inserir(new PessoaFisica(2, "Maria Souza", "987.654.321-00", 25));
            repoFisica.persistir("pessoas_fisicas.dat");

            repoJuridica.inserir(new PessoaJuridica(1, "Empresa X", "12.345.678/0001-00"));
            repoJuridica.inserir(new PessoaJuridica(2, "Empresa Y", "98.765.432/0001-00"));
            repoJuridica.persistir("pessoas_juridicas.dat");

            // Recuperacao inicial
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar("pessoas_fisicas.dat");
            System.out.println("Pessoas Fisicas Recuperadas:");
            for (PessoaFisica p : repo2.obterTodos()) {
                p.exibir();
            }

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar("pessoas_juridicas.dat");
            System.out.println("Pessoas Juridicas Recuperadas:");
            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Cadastro em modo texto
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1: // Incluir
                    System.out.print("Escolha o tipo (1 - Fisica, 2 - Juridica): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    if (tipo == 1) {
                        System.out.print("ID: ");
                        int idFisica = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        repoFisica.inserir(new PessoaFisica(idFisica, nome, cpf, idade));
                    } else {
                        System.out.print("ID: ");
                        int idJuridica = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(idJuridica, nome, cnpj));
                    }
                    break;

                case 2: // Alterar
                    System.out.print("Escolha o tipo (1 - Fisica, 2 - Juridica): ");
                    tipo = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("ID: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    if (tipo == 1) {
                        PessoaFisica pessoa = repoFisica.obter(idAlterar);
                        if (pessoa != null) {
                            System.out.println("Dados atuais: " + pessoa.getNome());
                            System.out.print("Novo Nome: ");
                            pessoa.setNome(scanner.nextLine());
                            System.out.print("Novo CPF: ");
                            pessoa.setCpf(scanner.nextLine());
                            System.out.print("Nova Idade: ");
                            pessoa.setIdade(scanner.nextInt());
                            repoFisica.alterar(pessoa);
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                    } else {
                        PessoaJuridica pessoa = repoJuridica.obter(idAlterar);
                        if (pessoa != null) {
                            System.out.println("Dados atuais: " + pessoa.getNome());
                            System.out.print("Novo Nome: ");
                            pessoa.setNome(scanner.nextLine());
                            System.out.print("Novo CNPJ: ");
                            pessoa.setCnpj(scanner.nextLine());
                            repoJuridica.alterar(pessoa);
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                    }
                    break;

                case 3: // Excluir
                    System.out.print("Escolha o tipo (1 - Fisica, 2 - Juridica): ");
                    tipo = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("ID: ");
                    int idExcluir = scanner.nextInt();
                    if (tipo == 1) {
                        repoFisica.excluir(idExcluir);
                    } else {
                        repoJuridica.excluir(idExcluir);
                    }
                    break;

                case 4: // Exibir pelo ID
                    System.out.print("Escolha o tipo (1 - Fisica, 2 - Juridica): ");
                    tipo = scanner.nextInt();
                    System.out.print("ID: ");
                    int idExibir = scanner.nextInt();
                    if (tipo == 1) {
                        PessoaFisica pessoa = repoFisica.obter(idExibir);
                        if (pessoa != null) {
                            pessoa.exibir();
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                    } else {
                        PessoaJuridica pessoa = repoJuridica.obter(idExibir);
                        if (pessoa != null) {
                            pessoa.exibir();
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                    }
                    break;

                case 5: // Exibir todos
                    System.out.print("Escolha o tipo (1 - Fisica, 2 - Juridica): ");
                    tipo = scanner.nextInt();
                    if (tipo == 1) {
                        for (PessoaFisica p : repoFisica.obterTodos()) {
                            p.exibir();
                        }
                    } else {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                            pj.exibir();
                        }
                    }
                    break;

                case 6: // Salvar dados
                    System.out.print("Prefixo dos arquivos: ");
                    String prefixo = scanner.nextLine();
                    try {
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                    break;

                case 7: // Recuperar dados
                    System.out.print("Prefixo dos arquivos: ");
                    prefixo = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                    break;

                case 0: // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
