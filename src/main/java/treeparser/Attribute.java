package treeparser;

public class Attribute extends Component {

	private final String value;

	public Attribute(final String name, final String value) {
		super(name);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}

}
