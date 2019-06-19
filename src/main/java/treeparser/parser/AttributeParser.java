package treeparser.parser;

import java.text.ParseException;

import parser.ParseableList;
import parser.Parser;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;
import treeparser.Attribute;
import treeparser.Component;
import treeparser.token.Name;
import treeparser.token.Token;
import treeparser.token.Value;

public class AttributeParser implements Parser<Token, Component> {

	@Override
	public Pattern<Token> getPattern() {
		return new PatternBuilder<Token>().a(Name.class).a(Value.class).build();
	}

	@Override
	public Component parse(final ParseableList<Token, Component> list, final int offset) throws ParseException {
		Name name = list.pop(Name.class);
		Value value = list.pop(Value.class);

		return new Attribute(name.getValue(), value.getValue());
	}

}
