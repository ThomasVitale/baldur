package io.arconia.demo;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.hilla.BrowserCallable;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@SpringBootApplication
@Theme("default")
@PWA(name = "Baldur", shortName = "Baldur", iconPath = "icons/icon.png")
public class Application implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

record Task(@Id Long id, String description, Instant creationDate, @Nullable LocalDate dueDate) {}

interface TaskRepository extends ListCrudRepository<Task, Long> {
	Slice<Task> findAllBy(Pageable pageable);
}

@BrowserCallable
@PermitAll
class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> list(Pageable pageable) {
		return taskRepository.findAllBy(pageable).toList();
	}

	@Transactional
	@RolesAllowed("ADMIN")
    public void createTask(String description, @Nullable LocalDate dueDate) {
        var task = new Task(null, description, Instant.now(), dueDate);
        taskRepository.save(task);
    }

}
