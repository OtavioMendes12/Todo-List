package com.otavio.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otavio.todoapp.domain.TodoItem;
import com.otavio.todoapp.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepo;

	public List<TodoItem> fetchAllTodos(){
		return todoRepo.fetchAllTodos(); 
	}
	
	public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
		
		Optional<TodoItem> todoOpt = todoRepo.fetchAllTodos()
				.stream()
				.filter(item -> item.getId().equals(id))
				.findAny();
		
		if (todoOpt.isPresent()) {
			TodoItem item = todoOpt.get();
			item.setIsDone(todoItem.getIsDone());
			item.setTask(todoItem.getTask());
			return item;
		}
		
		return null;
		
	}
	
	public TodoItem createNewTodoItem() {
		TodoItem todoItem = new TodoItem();
		todoItem.setIsDone(false);
		todoItem = todoRepo.save(todoItem);
		todoItem.setTask("");
		return todoItem;
	}
	
	public void deleteTodoItem(Integer id) {
		todoRepo.delete(id);
	}
}
