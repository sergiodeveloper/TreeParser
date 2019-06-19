package treeparser.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import org.junit.Test;

import treeparser.Element;
import treeparser.TreeParser;

public class TreeParserTest {

	private static final String PATH = "/test.tree";

	@Test
	public void test() throws IOException, ParseException {
		String string = new String(
				Files.readAllBytes(Paths.get(getClass().getResource(TreeParserTest.PATH).getPath())));

		Element element = new TreeParser(string).parse();
		System.out.println(element);
	}

}
