package br.com.emendes.powerkrtestapi.mapper;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
import br.com.emendes.powerkrtestapi.model.entity.Task;

public interface TaskMapper {

  Task createTaskRequestToTask(CreateTaskRequest createTaskRequest);

  TaskResponse taskToTaskResponse(Task task);

  void merge(UpdateTaskRequest updateTaskRequest, Task task);

}
