package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;

import java.util.List;

/**
 * Interface Service que contém as abstrações que manipulam o recurso Task.
 */
public interface TaskService {

  /**
   * Salva uma Tarefa no banco dados.
   *
   * @param createTaskRequest contém os dados da Tarefa que será salva.
   * @return TaskResponse contendo id, title, description, status and creationDate da tarefa salva.
   */
  TaskResponse create(CreateTaskRequest createTaskRequest);

  /**
   * Busca todas as Tarefas no banco de dados.
   *
   * @return List de TaskResponse
   */
  List<TaskResponse> fetchAll();

  /**
   * Busca uma Tarefa no banco de dados.
   *
   * @param id identificador da tarefa a ser buscada.
   * @return TaskResponse contendo id, title, description, status, creationDate and conclusionDate
   * da tarefa salva.
   */
  TaskResponse findById(Long id);

  /**
   * Método responsável por atualizar um Tarefa.
   *
   * @param id identificador do tarefa a ser atualizado.
   * @param updateTaskRequest contém os novos dados do Tarefa.
   * @return TaskResponse contendo id, title, description, status and creationDate da tarefa atualizada.
   */
  TaskResponse update(Long id, UpdateTaskRequest updateTaskRequest);

  /**
   * Deletar uma tarefa por id do banco de dados.
   *
   * @param id identificador da tarefa a ser deletada.
   */
  void delete(Long id);

}
