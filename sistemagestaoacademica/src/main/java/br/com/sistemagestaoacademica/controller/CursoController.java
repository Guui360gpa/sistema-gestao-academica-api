package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.CursoRepository;

import java.util.List;

public class CursoController extends Controller{

    private CursoRepository repository;

    public CursoController(CursoRepository cursoRepository) {
        this.repository = cursoRepository;
    }

    public CursoController() {}

    @Override
    public void menu() {
        System.out.println("""
                [1] Cadastrar Curso
                [2] Listar cursos ativos
                [3] Listar cursos desativados
                [4] Desativar curso
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                cadastrarCurso();
                break;
            case 2:
                listarCursosAtivos();
                break;
            case 3:
                listarCursosDesativados();
                break;
            case 4:
                desativarCurso();
                break;
            default:
                break;
        }
    }

    private void cadastrarCurso(){

        System.out.println("Digite o nome do curso: ");
        var nomeCurso = read.nextLine();

        System.out.printf("Descrição %s:",nomeCurso);
        var descricao = read.nextLine();

        System.out.println("Carga horária (h):");
        var cargaHoraria = read.nextInt();

        repository.save(new Curso(nomeCurso,descricao,cargaHoraria));
        System.out.println("\nCurso cadastrado com sucesso!");
    }

    private void listarCursosAtivos(){
        List<Curso> cursosAtivos = repository.findByStatus(Status.ATIVADA);

        if(cursosAtivos.isEmpty()){
            System.out.println("Nenhum curso ativo foi encontrado!");
        }

        cursosAtivos.forEach(c ->
                System.out.printf("\nCurso de %s\n%s\n%s h\n",
                        c.getNome(),
                        c.getDescricao(),
                        c.getCargaHoraria()));
    }

    private void listarCursosDesativados(){
        List<Curso> cursosDesativados = repository.findByStatus(Status.DESATIVADA);

        if(cursosDesativados.isEmpty()){
            System.out.println("Nenhum curso desativado foi encontrado!");
        }

        cursosDesativados.forEach(c ->
                System.out.printf("\nCurso de %s\n%s\n%s h\n",
                        c.getNome(),
                        c.getDescricao(),
                        c.getCargaHoraria()));
    }

    private void desativarCurso(){
        Curso cursoEncontrado = null;
        List<Curso> cursosEncontrados;

        System.out.println("Qual curso você deseja desativar ?");
        while (cursoEncontrado == null){
            var nomeCurso = read.nextLine();
            cursosEncontrados = repository.findByNomeContainingIgnoreCaseAndStatus(nomeCurso,Status.ATIVADA);
            if (cursosEncontrados.isEmpty()) {
                System.out.println("\nCurso não encontrado! Tente Novamente.");
            }else {
                cursosEncontrados.forEach(c ->
                        System.out.printf("%s | %s | %s h\n",c.getId(),c.getNome(),c.getCargaHoraria()));
                System.out.println("\nDigite o ID do curso desejado: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                cursoEncontrado = cursosEncontrados.stream()
                        .filter(p -> p.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (cursoEncontrado == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                } else if (cursoEncontrado.getStatus() != Status.ATIVADA) {
                    System.out.println("Erro: o curso \"" + cursoEncontrado.getNome() + "\" já está desativado.");
                    cursoEncontrado = null;
                }
            }
        }

        cursoEncontrado.setStatus(Status.DESATIVADA);
        repository.save(cursoEncontrado);
    }
}
