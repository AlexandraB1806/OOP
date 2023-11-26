package lab5.task2;

import lab5.task1.Task;

public class Queue extends AbstractContainer implements Container{
	@Override
	public Task pop() {
		if(isEmpty())
			return null;
		Task popQueue = getList().get(0);
		getList().remove(0);
		return popQueue;
	}
}
