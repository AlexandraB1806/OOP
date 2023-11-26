package lab5.task2;

import lab5.task1.Task;

import java.util.ArrayList;

public abstract class AbstractContainer implements Container{
	private ArrayList<Task> list = new ArrayList<Task>();

	public ArrayList<Task> getList() {
		return list;
	}
	public void setList(ArrayList<Task> list) {
		this.list = list;
	}

	@Override
	public Task pop() {
		return list.get(0);
	}

	@Override
	public void push(Task task) {
		list.add(task);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void transferFrom(Container container) {
		while(!container.isEmpty()) {
			Task elemTask = container.pop();
			push(elemTask);
		}
	}

	public ArrayList<Task> getTasks(){
		return list;
	}
}
