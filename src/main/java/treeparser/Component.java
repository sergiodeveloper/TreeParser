package treeparser;

public abstract class Component {

	private final String name;

	public Component(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
