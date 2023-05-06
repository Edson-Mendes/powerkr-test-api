package br.com.emendes.powerkrtestapi.mapper.impl;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
import br.com.emendes.powerkrtestapi.mapper.TaskMapper;
import br.com.emendes.powerkrtestapi.model.TaskStatus;
import br.com.emendes.powerkrtestapi.model.entity.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapperImpl implements TaskMapper {

  @Override
  public Task createTaskRequestToTask(CreateTaskRequest createTaskRequest) {
    return Task.builder()
        .title(createTaskRequest.title())
        .description(createTaskRequest.description())
        .creationDate(LocalDateTime.parse(createTaskRequest.creationDate()))
        .status(TaskStatus.OPEN)
        .build();
  }

  @Override
  public TaskResponse taskToTaskResponse(Task task) {
    return TaskResponse.builder()
        .id(task.getId())
        .title(task.getTitle())
        .description(task.getDescription())
        .creationDate(task.getCreationDate())
        .status(task.getStatus().name())
        .conclusionDate(task.getConclusionDate())
        .build();
  }

  @Override
  public void merge(UpdateTaskRequest updateTaskRequest, Task task) {
    task.setTitle(updateTaskRequest.title());
    task.setDescription(updateTaskRequest.description());
    task.setCreationDate(LocalDateTime.parse(updateTaskRequest.creationDate()));
  }

}
