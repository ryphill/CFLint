package com.cflint;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class TestJSONOutput {

	private JSONOutput outputer;
	private BugList bugList;
	private Writer writer;

	@Before
	public void setUp(){
		outputer = new JSONOutput();
		outputer.setPrettyPrint(false);
		bugList = new BugList(null);
		writer = new StringWriter();
	}

	@Test
	public void testOutput() throws IOException {
		BugInfo bugInfo = new BugInfo.BugInfoBuilder().setFunction("testf").setMessageCode("PARSE_ERROR").setFilename("c:\\temp\\test.cfc").build();
		bugList.add(bugInfo);
		outputer.output(bugList, writer, false);
		String expectedText = "[{\"severity\":\"\",\"id\":\"PARSE_ERROR\",\"message\":\"PARSE_ERROR\",\"category\":\"CFLINT\",\"abbrev\":\"PE\",\"location\":{\"file\":\"c:\\\\temp\\\\test.cfc\",\"fileName\":\"test.cfc\",\"function\":\"testf\",\"column\":\"0\",\"line\":\"0\",\"message\":\"\",\"variable\":\"\",\"expression\":\"\"}}]";
//		assertEquals(JSONValue.parse(expectedText),JSONValue.parse(writer.toString()));
		assertEquals(expectedText,writer.toString());
	}

	@Test
	public void testStats() throws IOException {
		BugInfo bugInfo = new BugInfo.BugInfoBuilder().setFunction("testf").setMessageCode("PARSE_ERROR").setFilename("c:\\temp\\test.cfc").build();
		bugList.add(bugInfo);
		outputer.output(bugList, writer, true);
		String expectedText = "[{\"severity\":\"\",\"id\":\"PARSE_ERROR\",\"message\":\"PARSE_ERROR\",\"category\":\"CFLINT\",\"abbrev\":\"PE\",\"location\":{\"file\":\"c:\\\\temp\\\\test.cfc\",\"fileName\":\"test.cfc\",\"function\":\"testf\",\"column\":\"0\",\"line\":\"0\",\"message\":\"\",\"variable\":\"\",\"expression\":\"\"}},{\"code\":\"PARSE_ERROR\",\"count\":\"1\"}]";
		assertEquals(expectedText,writer.toString());
	}
	
}
