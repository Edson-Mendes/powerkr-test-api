package br.com.emendes.powerkrtestapi.service.impl;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import br.com.emendes.powerkrtestapi.dto.response.TaskResponse;
import br.com.emendes.powerkrtestapi.exception.ResourceNotFoundException;
import br.com.emendes.powerkrtestapi.mapper.TaskMapper;
import br.com.emendes.powerkrtestapi.model.TaskStatus;
import br.com.emendes.powerkrtestapi.model.entity.Task;
import br.com.emendes.powerkrtestapi.repository.TaskRepository;
import br.com.emendes.powerkrtestapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementação de TaskService.
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

  @Override
  public TaskResponse findById(Long id) {
    return taskMapper.taskToTaskResponse(findTaskById(id));
  }

  @Override
  public TaskResponse update(Long id, UpdateTaskRequest updateTaskRequest) {
    log.info("Attempt update task with id : {}", id);
    Task task = findTaskById(id);

    taskMapper.merge(updateTaskRequest, task);
    TaskStatus taskStatusUpdate = TaskStatus.valueOf(updateTaskRequest.status());

    if (!task.getStatus().equals(taskStatusUpdate)) {
      if (taskStatusUpdate.equals(TaskStatus.CONCLUDED)) {
        task.setConclusionDate(LocalDateTime.now());
      } else {
        task.setConclusionDate(null);
      }
      task.setStatus(taskStatusUpdate);
    }

    taskRepository.save(task);
    log.info("task successfully updated with id : {}", task.getId());

    return taskMapper.taskToTaskResponse(task);
  }

  @Override
  public void delete(Long id) {
    log.info("Attempt delete task with id : {}", id);
    Task task = findTaskById(id);

    taskRepository.delete(task);
    log.info("task with id : {} successfully deleted from database", id);
  }

  /**
   * Busca tarefa por id no banco de dados.
   *
   * @param id identificador da tarefa a ser buscada.
   * @return Task encontrado no banco de dados.
   * @throws ResourceNotFoundException caso não exista uma tarefa com o id informado.
   */
  private Task findTaskById(Long id) {
    log.info("Searching for task with id : {}", id);

    return taskRepository.findById(id)
        .orElseThrow(() -> {
          log.info("Task not found with id : {}", id);
          return new ResourceNotFoundException("Task not found with id " + id);
        });
  }

}
