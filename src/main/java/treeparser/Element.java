package treeparser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Element extends Component {

	private final Map<String, String> attributes = new LinkedHashMap<>();

	private final List<Element> children = new ArrayList<>();
	private final Map<String, Element> namedChildren = new LinkedHashMap<>();

	public Element(final String name) {
		super(name);
	}

	public boolean hasAttribute(final String name) {
		return attributes.containsKey(name);
	}

	public void setAttribute(final String name, final String value) {
		attributes.put(name, value);
	}

	public String getAttribute(final String name) {
		return attributes.get(name);
	}

	public Set<String> getAttributes() {
		return attributes.keySet();
	}

	public void addChild(final Element child) {
		children.add(child);
		namedChildren.put(child.getName(), child);
	}

	public List<Element> getChildren() {
		return children;
	}

	public Element getChild(final String name) {
		return namedChildren.get(name);
	}

	public boolean hasChild(final String name) {
		return namedChildren.containsKey(name);
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj;
	}

	private String getTabulation(final int tabulation, final String tabulationString) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tabulation; i++) {
			sb.append(tabulationString);
		}
		return sb.toString();
	}

	private String attributesToString(final int tabulation, final String tabulationString) {
		StringBuilder sb = new StringBuilder();
		if (!attributes.isEmpty()) {
			String innerTab = getTabulation(tabulation, tabulationString);

			List<String> att = new ArrayList<>();
			for (Entry<String, String> entry : attributes.entrySet()) {
				att.add(entry.getKey() + ": " + entry.getValue());
			}
			for (int i = 0; i < att.size(); i++) {
				sb.append(innerTab + att.get(i));
				if (i < att.size() - 1) {
					sb.append(",\n");
				}
			}
			sb.append(children.isEmpty() ? "\n" : ",\n\n");
		}
		return sb.toString();
	}

	public String stringify(final int tabulation, final String tabulationString) {
		String tab = getTabulation(tabulation, tabulationString);

		StringBuilder sb = new StringBuilder(tab + getName());

		if (!attributes.isEmpty() || !children.isEmpty()) {
			sb.append(" (\n");
		}

		sb.append(attributesToString(tabulation + 1, tabulationString));

		if (!children.isEmpty()) {
			int index = 0;
			for (Element e : children) {
				sb.append(e.stringify(tabulation + 1, tabulationString));
				if (index < children.size() - 1) {
					sb.append(e.children.isEmpty() && e.attributes.isEmpty() ? "," : "");
				}
				sb.append("\n");
				index++;
			}
		}

		if (!attributes.isEmpty() || !children.isEmpty()) {
			sb.append(tab + ")");
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return stringify(0, "  ");
	}

}
