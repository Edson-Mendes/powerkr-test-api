package br.com.emendes.powerkrtestapi.service.impl;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
import br.com.emendes.powerkrtestapi.mapper.TaskMapper;
import br.com.emendes.powerkrtestapi.model.entity.Task;
import br.com.emendes.powerkrtestapi.repository.TaskRepository;
import br.com.emendes.powerkrtestapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação de TasKService.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Override
  public TaskResponse create(CreateTaskRequest createTaskRequest) {
    Task task = taskMapper.createTaskRequestToTask(createTaskRequest);

    taskRepository.save(task);
    log.info("task successfully saved with id : {}", task.getId());

    return taskMapper.taskToTaskResponse(task);
  }

  @Override
  public List<TaskResponse> fetchAll() {
    log.info("Fetching all tasks");
    List<Task> taskList = taskRepository.findAll();

    return taskList.stream().map(taskMapper::taskToTaskResponse).toList();
  }

}
