package lab5.task2;

import lab5.task1.Task;

public class Stack extends AbstractContainer implements Container{
	@Override
	public Task pop() {
		if (isEmpty())
			return null;
		Task popStack = getList().get(getList().size() - 1);
		getList().remove(getList().size() - 1);
		return popStack;
	}
}
