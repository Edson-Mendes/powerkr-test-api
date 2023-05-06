package br.com.emendes.powerkrtestapi.service;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;

import java.util.List;

/**
 * Interface Service responsável pelas regras de negócio envolvendo o recurso Task.
 */
public interface TaskService {

  /**
   * Método responsável por persistir uma Tarefa no banco dados.
   *
   * @param createTaskRequest contém os dados da Tarefa que será salva.
   * @return TaskResponse contendo id, title, description, status, creationDate da tarefa salva.
   */
  TaskResponse create(CreateTaskRequest createTaskRequest);

  /**
   * Método responsável por buscar todas as Tarefas na base de dados.
   *
   * @return List de TaskResponse
   */
  List<TaskResponse> fetchAll();

}
