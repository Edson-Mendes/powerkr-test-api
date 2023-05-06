package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;

import java.util.List;

/**
 * Interface Service responsável pelas regras de negócio envolvendo o recurso Task.
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
   * Busca todas as Tarefas na base de dados.
   *
   * @return List de TaskResponse
   */
  List<TaskResponse> fetchAll();

  /**
   * Busca uma Tarefa na base de dados.
   *
   * @param id identificador da tarefa a ser buscada.
   * @return TaskResponse contendo id, title, description, status, creationDate and conclusionDate
   * da tarefa salva.
   */
  TaskResponse findById(Long id);

  TaskResponse update(Long id, UpdateTaskRequest updateTaskRequest);

}
