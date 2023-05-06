package br.com.emendes.powerkrtestapi.mapper;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
import br.com.emendes.powerkrtestapi.model.entity.Task;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade Task e vice-versa.
 */
public interface TaskMapper {

  /**
   * Mapeia o DTO CreateTaskRequest para a entidade Task.
   * @param createTaskRequest que deve ser mapeado para Task
   * @return Task com dados vindo de createTaskRequest.
   */
  Task createTaskRequestToTask(CreateTaskRequest createTaskRequest);

  /**
   * Mapeia uma entidade Task para o DTO TaskResponse.
   * @param task que deve ser mapeado para TaskResponse
   * @return TaskResponse com as informações necessárias de Task.
   */
  TaskResponse taskToTaskResponse(Task task);

  /**
   * Insere os dados vindo do DTO UpdateTaskRequest em um objeto Task
   * @param updateTaskRequest que contém os novos dados de Task.
   * @param task que receberá os novos dados vindo de updateTaskRequest
   */
  void merge(UpdateTaskRequest updateTaskRequest, Task task);

}
