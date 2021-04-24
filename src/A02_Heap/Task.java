package A02_Heap;

public class Task {

	private final int id;
	
	private final int priority;
	
	public Task(int id, int priority) {
		this.id = id;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public int getPriority() {
		return priority;
	}
}
