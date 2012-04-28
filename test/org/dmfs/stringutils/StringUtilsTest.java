package org.dmfs.stringutils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class StringUtilsTest
{

	@Test
	/**
	 * Test StringUtils.minIndexofOneOf(String, int, String).
	 */
	public void testMinIndexOfOneOf()
	{
		assertEquals(-1, StringUtils.minIndexOfOneOf(null, 0, ""));
		assertEquals(-1, StringUtils.minIndexOfOneOf(null, 5, ""));
		assertEquals(0, StringUtils.minIndexOfOneOf("", 0, ""));
		assertEquals(0, StringUtils.minIndexOfOneOf("abcdefg", 0, ""));
		assertEquals(0, StringUtils.minIndexOfOneOf("abcdefg", 5, ""));
		assertEquals(0, StringUtils.minIndexOfOneOf("abcdefg", 20, ""));
		assertEquals(-1, StringUtils.minIndexOfOneOf(null, 0, "xyz"));
		assertEquals(-1, StringUtils.minIndexOfOneOf(null, 5, "xyz"));
		assertEquals(-1, StringUtils.minIndexOfOneOf("", 0, "xyz"));
		assertEquals(-1, StringUtils.minIndexOfOneOf("abcdefg", 0, "xyz"));
		assertEquals(-1, StringUtils.minIndexOfOneOf("abcdefg", 5, "xyz"));
		assertEquals(-1, StringUtils.minIndexOfOneOf("abcdefg", 0, "xyz"));

		assertEquals(0, StringUtils.minIndexOfOneOf(",abcdefg", 0, ","));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 0, ","));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 0, ","));
		assertEquals(0, StringUtils.minIndexOfOneOf(",abcdefg", 0, "x,"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 0, "x,"));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 0, "x,"));
		assertEquals(0, StringUtils.minIndexOfOneOf(",abcdefg", 0, ",x"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 0, ",x"));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 0, ",x"));
		assertEquals(0, StringUtils.minIndexOfOneOf(",abcdefg", 0, "yz,x"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 0, "yz,x"));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 0, "yz,x"));

		assertEquals(-1, StringUtils.minIndexOfOneOf(",abcdefg", 3, ","));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 3, ","));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 3, ","));
		assertEquals(-1, StringUtils.minIndexOfOneOf(",abcdefg", 3, "x,"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 3, "x,"));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 3, "x,"));
		assertEquals(-1, StringUtils.minIndexOfOneOf(",abcdefg", 3, ",x"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 3, ",x"));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 3, ",x"));
		assertEquals(-1, StringUtils.minIndexOfOneOf(",abcdefg", 3, "yz,x"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,defg", 3, "yz,x"));
		assertEquals(7, StringUtils.minIndexOfOneOf("abcdefg,", 3, "yz,x"));

		assertEquals(-1, StringUtils.minIndexOfOneOf(",,abcdefg", 3, ","));
		assertEquals(4, StringUtils.minIndexOfOneOf(",abc,defg", 3, ","));
		assertEquals(8, StringUtils.minIndexOfOneOf(",abcdefg,", 3, ","));
		assertEquals(-1, StringUtils.minIndexOfOneOf(",abcdefg", 3, "x,"));
		assertEquals(4, StringUtils.minIndexOfOneOf("abcd,e,f,g", 3, "x,"));
		assertEquals(3, StringUtils.minIndexOfOneOf("abc,,,,,defg,", 3, "x,"));
		assertEquals(3, StringUtils.minIndexOfOneOf(",ab,,,,,cdefg", 3, ",x"));
		assertEquals(3, StringUtils.minIndexOfOneOf(",,,,,,,,abc,defg", 3, ",x"));
		assertEquals(-1, StringUtils.minIndexOfOneOf("abcd,e,f,g", 10, ",x"));

		assertEquals(0, StringUtils.minIndexOfOneOf(",ab:cd;ef.g", 0, ",.:;"));
		assertEquals(3, StringUtils.minIndexOfOneOf(",ab:cd;ef.g", 3, ",.:;"));
		assertEquals(6, StringUtils.minIndexOfOneOf(",ab:cd;ef.g", 4, ",.:;"));
		assertEquals(-1, StringUtils.minIndexOfOneOf(",ab:cd;ef.g", 20, ",.:;"));
		assertEquals(0, StringUtils.minIndexOfOneOf(":ab:cd:ef:g", 0, ",.:;"));
		assertEquals(3, StringUtils.minIndexOfOneOf(":ab:cd;ef:g", 3, ",.:;"));
		assertEquals(6, StringUtils.minIndexOfOneOf(":ab:cd:ef:g", 4, ",.:;"));
		assertEquals(-1, StringUtils.minIndexOfOneOf(":ab:cd:ef:g", 20, ",.:;"));
	}


	/**
	 * Test StringUtils.join(String, String...)
	 */
	@Test
	public void testJoinStringStringArray()
	{
		assertNull(StringUtils.join("", (String[]) null));
		assertEquals("", StringUtils.join("", ""));
		assertEquals("", StringUtils.join("", "", "", ""));
		assertEquals("1", StringUtils.join("", "1"));
		assertEquals("111", StringUtils.join("", "111"));
		assertEquals("12", StringUtils.join("", "1", "2"));
		assertEquals("111222", StringUtils.join("", "111", "222"));
		assertEquals("1234", StringUtils.join("", "1", "2", "3", "4"));
		assertEquals("111222333444", StringUtils.join("", "111", "222", "333", "444"));

		assertNull(StringUtils.join("-", (String[]) null));
		assertEquals("", StringUtils.join("-", ""));
		assertEquals("--", StringUtils.join("-", "", "", ""));
		assertEquals("1", StringUtils.join("-", "1"));
		assertEquals("111", StringUtils.join("-", "111"));
		assertEquals("1-2", StringUtils.join("-", "1", "2"));
		assertEquals("111-222", StringUtils.join("-", "111", "222"));
		assertEquals("1-2-3-4", StringUtils.join("-", "1", "2", "3", "4"));
		assertEquals("111-222-333-444", StringUtils.join("-", "111", "222", "333", "444"));

		assertNull(StringUtils.join("-.-", (String[]) null));
		assertEquals("", StringUtils.join("-.-", ""));
		assertEquals("-.--.-", StringUtils.join("-.-", "", "", ""));
		assertEquals("1", StringUtils.join("-.-", "1"));
		assertEquals("111", StringUtils.join("-.-", "111"));
		assertEquals("1-.-2", StringUtils.join("-.-", "1", "2"));
		assertEquals("111-.-222", StringUtils.join("-.-", "111", "222"));
		assertEquals("1-.-2-.-3-.-4", StringUtils.join("-.-", "1", "2", "3", "4"));
		assertEquals("111-.-222-.-333-.-444", StringUtils.join("-.-", "111", "222", "333", "444"));
	}


	/**
	 * Test StringUtils.join(String, Iterable<String>)
	 */
	@Test
	public void testJoinStringIterable()
	{
		assertNull(StringUtils.join("", (Iterable<String>) null));
		assertNull(StringUtils.join(",", (Iterable<String>) null));

		assertEquals("", StringUtils.join("", StringUtils.splitEscapedList("", '\\', ',')));
		assertEquals("", StringUtils.join("", StringUtils.splitEscapedList(",", '\\', ',')));
		assertEquals("", StringUtils.join("", StringUtils.splitEscapedList(",,", '\\', ',')));
		assertEquals("abc", StringUtils.join("", StringUtils.splitEscapedList("abc", '\\', ',')));
		assertEquals("abc", StringUtils.join("", StringUtils.splitEscapedList("abc,,", '\\', ',')));
		assertEquals("abcdefghi", StringUtils.join("", StringUtils.splitEscapedList("abc,def,ghi", '\\', ',')));

		assertEquals("", StringUtils.join(",", StringUtils.splitEscapedList("", '\\', ',')));
		assertEquals(",", StringUtils.join(",", StringUtils.splitEscapedList(",", '\\', ',')));
		assertEquals(",,", StringUtils.join(",", StringUtils.splitEscapedList(",,", '\\', ',')));
		assertEquals("abc", StringUtils.join(",", StringUtils.splitEscapedList("abc", '\\', ',')));
		assertEquals("abc,,", StringUtils.join(",", StringUtils.splitEscapedList("abc,,", '\\', ',')));
		assertEquals("abc,def,ghi", StringUtils.join(",", StringUtils.splitEscapedList("abc,def,ghi", '\\', ',')));

		assertEquals("", StringUtils.join("-.-", StringUtils.splitEscapedList("", '\\', ',')));
		assertEquals("-.-", StringUtils.join("-.-", StringUtils.splitEscapedList(",", '\\', ',')));
		assertEquals("-.--.-", StringUtils.join("-.-", StringUtils.splitEscapedList(",,", '\\', ',')));
		assertEquals("abc", StringUtils.join("-.-", StringUtils.splitEscapedList("abc", '\\', ',')));
		assertEquals("abc-.--.-", StringUtils.join("-.-", StringUtils.splitEscapedList("abc,,", '\\', ',')));
		assertEquals("abc-.-def-.-ghi", StringUtils.join("-.-", StringUtils.splitEscapedList("abc,def,ghi", '\\', ',')));
	}


	/**
	 * Test StringUtils.join(char, String...)
	 */
	@Test
	public void testJoinCharStringArray()
	{
		assertNull(StringUtils.join('-', (String[]) null));
		assertEquals("", StringUtils.join('-', ""));
		assertEquals("--", StringUtils.join('-', "", "", ""));
		assertEquals("1", StringUtils.join('-', "1"));
		assertEquals("111", StringUtils.join('-', "111"));
		assertEquals("1-2", StringUtils.join('-', "1", "2"));
		assertEquals("111-222", StringUtils.join('-', "111", "222"));
		assertEquals("1-2-3-4", StringUtils.join('-', "1", "2", "3", "4"));
		assertEquals("111-222-333-444", StringUtils.join('-', "111", "222", "333", "444"));
	}


	/**
	 * Test StringUtils.join(char, Iterable<String>)
	 */
	@Test
	public void testJoinCharIterable()
	{
		assertNull(StringUtils.join(',', (Iterable<String>) null));

		assertEquals("", StringUtils.join(',', StringUtils.splitEscapedList("", '\\', ',')));
		assertEquals(",", StringUtils.join(',', StringUtils.splitEscapedList(",", '\\', ',')));
		assertEquals(",,", StringUtils.join(',', StringUtils.splitEscapedList(",,", '\\', ',')));
		assertEquals("abc", StringUtils.join(',', StringUtils.splitEscapedList("abc", '\\', ',')));
		assertEquals("abc,,", StringUtils.join(',', StringUtils.splitEscapedList("abc,,", '\\', ',')));
		assertEquals("abc,def,ghi", StringUtils.join(',', StringUtils.splitEscapedList("abc,def,ghi", '\\', ',')));
	}


	/**
	 * Test StringUtils.join(Iterable<String>)
	 */
	@Test
	public void testJoinIterable()
	{
		assertNull(StringUtils.join(null));

		assertEquals("", StringUtils.join(StringUtils.splitEscapedList("", '\\', ',')));
		assertEquals("", StringUtils.join(StringUtils.splitEscapedList(",", '\\', ',')));
		assertEquals("", StringUtils.join(StringUtils.splitEscapedList(",,", '\\', ',')));
		assertEquals("abc", StringUtils.join(StringUtils.splitEscapedList("abc", '\\', ',')));
		assertEquals("abc", StringUtils.join(StringUtils.splitEscapedList("abc,,", '\\', ',')));
		assertEquals("abcdefghi", StringUtils.join(StringUtils.splitEscapedList("abc,def,ghi", '\\', ',')));
	}


	/**
	 * Test StringUtils.orderedJoin(String[], int...);
	 */
	@Test
	public void testorderedJoinStringArrayIntArray()
	{
		assertNull(StringUtils.orderedJoin(null, null));
		assertNull(StringUtils.orderedJoin(new String[] {}, null));
		assertNull(StringUtils.orderedJoin(new String[] { "test" }, null));

		assertNull(StringUtils.orderedJoin(new String[] {}, new int[] {}));
		assertNull(StringUtils.orderedJoin(new String[] { "test" }, new int[] {}));

		assertNull(StringUtils.orderedJoin(null, new int[] {}));
		assertNull(StringUtils.orderedJoin(null, 1, 2));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc" }, 0));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc" }, 1));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc" }, -1));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc", "def" }, 0));
		assertEquals("def", StringUtils.orderedJoin(new String[] { "abc", "def" }, 1));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def" }, 2));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def" }, -1));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0));
		assertEquals("def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1));
		assertEquals("ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, -1));

		assertEquals("abcdef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0, 1));
		assertEquals("abcghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0, 2));
		assertEquals("defabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1, 0));
		assertEquals("defghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1, 2));
		assertEquals("ghiabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2, 0));
		assertEquals("ghidef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2, 1));

		assertEquals("abcdefghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0, 1, 2));
		assertEquals("abcghidef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0, 2, 1));
		assertEquals("defabcghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1, 0, 2));
		assertEquals("defghiabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1, 2, 0));
		assertEquals("ghiabcdef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2, 0, 1));
		assertEquals("ghidefabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2, 1, 0));

		assertEquals("abcdefghiabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0, 1, 2, 0));
		assertEquals("abcghidef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 0, 2, 1, -1));
		assertEquals("defabcghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1, 0, 2, 5));
		assertEquals("defghiabcabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 1, 2, 0, -1, 0));
		assertEquals("ghiabcdefabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2, 0, 1, 5, 0));
		assertEquals("ghidefabcdefghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, 2, 1, 0, 1, 2));
	}


	/**
	 * Test StringUtils.orderedJoin(String[], String, int...);
	 */
	@Test
	public void testorderedJoinStringArrayStringIntArray()
	{
		assertNull(StringUtils.orderedJoin(null, "", null));
		assertNull(StringUtils.orderedJoin(new String[] {}, "", null));
		assertNull(StringUtils.orderedJoin(new String[] { "test" }, "", null));

		assertNull(StringUtils.orderedJoin(new String[] {}, "", new int[] {}));
		assertNull(StringUtils.orderedJoin(new String[] { "test" }, "", new int[] {}));

		assertNull(StringUtils.orderedJoin(null, "", new int[] {}));
		assertNull(StringUtils.orderedJoin(null, "", 1, 2));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc" }, "", 0));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc" }, "", 1));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc" }, "", -1));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc", "def" }, "", 0));
		assertEquals("def", StringUtils.orderedJoin(new String[] { "abc", "def" }, "", 1));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def" }, "", 2));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def" }, "", -1));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0));
		assertEquals("def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1));
		assertEquals("ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", -1));

		assertEquals("abcdef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0, 1));
		assertEquals("abcghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0, 2));
		assertEquals("defabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1, 0));
		assertEquals("defghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1, 2));
		assertEquals("ghiabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2, 0));
		assertEquals("ghidef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2, 1));

		assertEquals("abcdefghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0, 1, 2));
		assertEquals("abcghidef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0, 2, 1));
		assertEquals("defabcghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1, 0, 2));
		assertEquals("defghiabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1, 2, 0));
		assertEquals("ghiabcdef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2, 0, 1));
		assertEquals("ghidefabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2, 1, 0));

		assertEquals("abcdefghiabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0, 1, 2, 0));
		assertEquals("abcghidef", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 0, 2, 1, -1));
		assertEquals("defabcghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1, 0, 2, 5));
		assertEquals("defghiabcabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 1, 2, 0, -1, 0));
		assertEquals("ghiabcdefabc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2, 0, 1, 5, 0));
		assertEquals("ghidefabcdefghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "", 2, 1, 0, 1, 2));

	
		assertNull(StringUtils.orderedJoin(null, "-:-", null));
		assertNull(StringUtils.orderedJoin(new String[] {}, "-:-", null));
		assertNull(StringUtils.orderedJoin(new String[] { "test" }, "-:-", null));

		assertNull(StringUtils.orderedJoin(new String[] {}, "-:-", new int[] {}));
		assertNull(StringUtils.orderedJoin(new String[] { "test" }, "-:-", new int[] {}));

		assertNull(StringUtils.orderedJoin(null, "-:-", new int[] {}));
		assertNull(StringUtils.orderedJoin(null, "-:-", 1, 2));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc" }, "-:-", 0));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc" }, "-:-", 1));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc" }, "-:-", -1));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc", "def" }, "-:-", 0));
		assertEquals("def", StringUtils.orderedJoin(new String[] { "abc", "def" }, "-:-", 1));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def" }, "-:-", 2));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def" }, "-:-", -1));

		assertEquals("abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0));
		assertEquals("def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1));
		assertEquals("ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2));
		assertEquals("", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", -1));

		assertEquals("abc-:-def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0, 1));
		assertEquals("abc-:-ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0, 2));
		assertEquals("def-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1, 0));
		assertEquals("def-:-ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1, 2));
		assertEquals("ghi-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2, 0));
		assertEquals("ghi-:-def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2, 1));

		assertEquals("abc-:-def-:-ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0, 1, 2));
		assertEquals("abc-:-ghi-:-def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0, 2, 1));
		assertEquals("def-:-abc-:-ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1, 0, 2));
		assertEquals("def-:-ghi-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1, 2, 0));
		assertEquals("ghi-:-abc-:-def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2, 0, 1));
		assertEquals("ghi-:-def-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2, 1, 0));

		assertEquals("abc-:-def-:-ghi-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0, 1, 2, 0));
		assertEquals("abc-:-ghi-:-def", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 0, 2, 1, -1));
		assertEquals("def-:-abc-:-ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1, 0, 2, 5));
		assertEquals("def-:-ghi-:-abc-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 1, 2, 0, -1, 0));
		assertEquals("ghi-:-abc-:-def-:-abc", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2, 0, 1, 5, 0));
		assertEquals("ghi-:-def-:-abc-:-def-:-ghi", StringUtils.orderedJoin(new String[] { "abc", "def", "ghi" }, "-:-", 2, 1, 0, 1, 2));
}


	/**
	 * Test StringUtils.escape(String, char, String)
	 */
	@Test
	public void testEscapeStringCharString()
	{
		assertNull(StringUtils.escape(null, '\\', ""));
		assertNull(StringUtils.escape(null, '\\', "\\"));
		assertNull(StringUtils.escape(null, '\\', "\\,;:"));

		assertEquals("", StringUtils.escape("", '\\', ""));
		assertEquals("", StringUtils.escape("", '\\', "\\"));
		assertEquals("", StringUtils.escape("", '\\', "\\,;:"));

		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', ""));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\"));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\,;:"));

		assertEquals("abc\\defg", StringUtils.escape("abc\\defg", '\\', ""));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\"));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\,;:"));

		assertEquals("abc\\de\\fg", StringUtils.escape("abc\\de\\fg", '\\', ""));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\"));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\,;:"));

		assertEquals(",abc\\de\\fg", StringUtils.escape(",abc\\de\\fg", '\\', ""));
		assertEquals(",abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\"));
		assertEquals("\\,abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\,;:"));

		assertEquals("abcdefg\\\\", StringUtils.escape("abcdefg\\", '\\', "\\,;:"));
		assertEquals("abcdefg\\,", StringUtils.escape("abcdefg,", '\\', "\\,;:"));
		assertEquals("abcdefg\\;", StringUtils.escape("abcdefg;", '\\', "\\,;:"));
		assertEquals("abcdefg\\:", StringUtils.escape("abcdefg:", '\\', "\\,;:"));

		assertEquals("\\\\abcdefg", StringUtils.escape("\\abcdefg", '\\', "\\,;:"));
		assertEquals("\\,abcdefg", StringUtils.escape(",abcdefg", '\\', "\\,;:"));
		assertEquals("\\;abcdefg", StringUtils.escape(";abcdefg", '\\', "\\,;:"));
		assertEquals("\\:abcdefg", StringUtils.escape(":abcdefg", '\\', "\\,;:"));

		assertEquals("ab\\\\cdefg", StringUtils.escape("ab\\cdefg", '\\', "\\,;:"));
		assertEquals("ab\\,cdefg", StringUtils.escape("ab,cdefg", '\\', "\\,;:"));
		assertEquals("ab\\;cdefg", StringUtils.escape("ab;cdefg", '\\', "\\,;:"));
		assertEquals("ab\\:cdefg", StringUtils.escape("ab:cdefg", '\\', "\\,;:"));

		assertEquals("\\,ab\\\\cd\\;efg\\:", StringUtils.escape(",ab\\cd;efg:", '\\', "\\,;:"));
		assertEquals("\\,\\\\\\;\\:", StringUtils.escape(",\\;:", '\\', "\\,;:"));
		assertEquals("+,+,+,+,+,", StringUtils.escape(",,,,,", '+', "\\,;:+"));

	}


	/**
	 * Test StringUtils.escape(String, char, String, Map)
	 */
	@Test
	public void testEscapeStringCharStringMap()
	{
		Map<Integer, Integer> map0 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		map1.put((int) '\n', (int) 'n');
		map1.put((int) '\r', null);

		assertNull(StringUtils.escape(null, '\\', "", null));
		assertNull(StringUtils.escape(null, '\\', "\\", null));
		assertNull(StringUtils.escape(null, '\\', "\\,;:", null));

		assertEquals("", StringUtils.escape("", '\\', "", null));
		assertEquals("", StringUtils.escape("", '\\', "\\", null));
		assertEquals("", StringUtils.escape("", '\\', "\\,;:", null));

		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "", null));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\", null));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\,;:", null));

		assertEquals("abc\\defg", StringUtils.escape("abc\\defg", '\\', "", null));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\", null));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\,;:", null));

		assertEquals("abc\\de\\fg", StringUtils.escape("abc\\de\\fg", '\\', "", null));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\", null));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\,;:", null));

		assertEquals(",abc\\de\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "", null));
		assertEquals(",abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\", null));
		assertEquals("\\,abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\,;:\n", null));

		assertEquals("abcdefg\\\\", StringUtils.escape("abcdefg\\", '\\', "\\,;:\n", null));
		assertEquals("abcdefg\\,", StringUtils.escape("abcdefg,", '\\', "\\,;:\n", null));
		assertEquals("abcdefg\\;", StringUtils.escape("abcdefg;", '\\', "\\,;:\n", null));
		assertEquals("abcdefg\\:", StringUtils.escape("abcdefg:", '\\', "\\,;:\n", null));

		assertEquals("\\\\abcdefg", StringUtils.escape("\\abcdefg", '\\', "\\,;:\n", null));
		assertEquals("\\,abcdefg", StringUtils.escape(",abcdefg", '\\', "\\,;:\n", null));
		assertEquals("\\;abcdefg", StringUtils.escape(";abcdefg", '\\', "\\,;:\n", null));
		assertEquals("\\:abcdefg", StringUtils.escape(":abcdefg", '\\', "\\,;:\n", null));

		assertEquals("ab\\\\cdefg", StringUtils.escape("ab\\cdefg", '\\', "\\,;:\n", null));
		assertEquals("ab\\,cdefg", StringUtils.escape("ab,cdefg", '\\', "\\,;:\n", null));
		assertEquals("ab\\;cdefg", StringUtils.escape("ab;cdefg", '\\', "\\,;:\n", null));
		assertEquals("ab\\:cdefg", StringUtils.escape("ab:cdefg", '\\', "\\,;:\n", null));

		assertEquals("\\,ab\\\\cd\\;efn\\\ng\\:", StringUtils.escape(",ab\\cd;efn\ng:", '\\', "\\,;:\n", null));
		assertEquals("\\,\\\\\\;\\:", StringUtils.escape(",\\;:", '\\', "\\,;:\n", null));
		assertEquals("+,+,+,+,+,", StringUtils.escape(",,,,,", '+', "\\,;:+\n", null));

		assertNull(StringUtils.escape(null, '\\', "", map0));
		assertNull(StringUtils.escape(null, '\\', "\\", map0));
		assertNull(StringUtils.escape(null, '\\', "\\,;:", map0));

		assertEquals("", StringUtils.escape("", '\\', "", map0));
		assertEquals("", StringUtils.escape("", '\\', "\\", map0));
		assertEquals("", StringUtils.escape("", '\\', "\\,;:", map0));

		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "", map0));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\", map0));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\,;:", map0));

		assertEquals("abc\\defg", StringUtils.escape("abc\\defg", '\\', "", map0));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\", map0));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\,;:", map0));

		assertEquals("abc\\de\\fg", StringUtils.escape("abc\\de\\fg", '\\', "", map0));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\", map0));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\,;:", map0));

		assertEquals(",abc\\de\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "", map0));
		assertEquals(",abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\", map0));
		assertEquals("\\,abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\,;:\n", map0));

		assertEquals("abcdefg\\\\", StringUtils.escape("abcdefg\\", '\\', "\\,;:\n", map0));
		assertEquals("abcdefg\\,", StringUtils.escape("abcdefg,", '\\', "\\,;:\n", map0));
		assertEquals("abcdefg\\;", StringUtils.escape("abcdefg;", '\\', "\\,;:\n", map0));
		assertEquals("abcdefg\\:", StringUtils.escape("abcdefg:", '\\', "\\,;:\n", map0));

		assertEquals("\\\\abcdefg", StringUtils.escape("\\abcdefg", '\\', "\\,;:\n", map0));
		assertEquals("\\,abcdefg", StringUtils.escape(",abcdefg", '\\', "\\,;:\n", map0));
		assertEquals("\\;abcdefg", StringUtils.escape(";abcdefg", '\\', "\\,;:\n", map0));
		assertEquals("\\:abcdefg", StringUtils.escape(":abcdefg", '\\', "\\,;:\n", map0));

		assertEquals("ab\\\\cdefg", StringUtils.escape("ab\\cdefg", '\\', "\\,;:\n", map0));
		assertEquals("ab\\,cdefg", StringUtils.escape("ab,cdefg", '\\', "\\,;:\n", map0));
		assertEquals("ab\\;cdefg", StringUtils.escape("ab;cdefg", '\\', "\\,;:\n", map0));
		assertEquals("ab\\:cdefg", StringUtils.escape("ab:cdefg", '\\', "\\,;:\n", map0));

		assertEquals("\\,ab\\\\cd\\;efn\\\ng\\:", StringUtils.escape(",ab\\cd;efn\ng:", '\\', "\\,;:\n", map0));
		assertEquals("\\,\\\\\\;\\:", StringUtils.escape(",\\;:", '\\', "\\,;:\n", map0));
		assertEquals("+,+,+,+,+,", StringUtils.escape(",,,,,", '+', "\\,;:+\n", map0));

		assertNull(StringUtils.escape(null, '\\', "", map1));
		assertNull(StringUtils.escape(null, '\\', "\\", map1));
		assertNull(StringUtils.escape(null, '\\', "\\,;:", map1));

		assertEquals("", StringUtils.escape("", '\\', "", map1));
		assertEquals("", StringUtils.escape("", '\\', "\\", map1));
		assertEquals("", StringUtils.escape("", '\\', "\\,;:", map1));

		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "", map1));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\", map1));
		assertEquals("abcdefg", StringUtils.escape("abcdefg", '\\', "\\,;:", map1));

		assertEquals("abc\\defg", StringUtils.escape("abc\\defg", '\\', "", map1));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\", map1));
		assertEquals("abc\\\\defg", StringUtils.escape("abc\\defg", '\\', "\\,;:", map1));

		assertEquals("abc\\de\\fg", StringUtils.escape("abc\\de\\fg", '\\', "", map1));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\", map1));
		assertEquals("abc\\\\de\\\\fg", StringUtils.escape("abc\\de\\fg", '\\', "\\,;:", map1));

		assertEquals(",abc\\de\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "", map1));
		assertEquals(",abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\", map1));
		assertEquals("\\,abc\\\\de\\\\fg", StringUtils.escape(",abc\\de\\fg", '\\', "\\,;:\n", map1));

		assertEquals("abcdefg\\\\", StringUtils.escape("abcdefg\\", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\,", StringUtils.escape("abcdefg,", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\;", StringUtils.escape("abcdefg;", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\:", StringUtils.escape("abcdefg:", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\n", StringUtils.escape("abcdefg\n", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg", StringUtils.escape("abcdefg\r", '\\', "\\,;:\n\r", map1));

		assertEquals("\\\\abcdefg", StringUtils.escape("\\abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\,abcdefg", StringUtils.escape(",abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\;abcdefg", StringUtils.escape(";abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\:abcdefg", StringUtils.escape(":abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\nabcdefg", StringUtils.escape("\nabcdefg", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg", StringUtils.escape("\rabcdefg", '\\', "\\,;:\n\r", map1));

		assertEquals("ab\\\\cdefg", StringUtils.escape("ab\\cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\,cdefg", StringUtils.escape("ab,cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\;cdefg", StringUtils.escape("ab;cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\:cdefg", StringUtils.escape("ab:cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\ncdefg", StringUtils.escape("ab\ncdefg", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg", StringUtils.escape("ab\rcdefg", '\\', "\\,;:\n\r", map1));

		assertEquals("\\,ab\\\\cd\\;ef\ng\\:", StringUtils.escape(",ab\\cd;ef\ng:", '\\', "\\,;:", map1));
		assertEquals("\\,ab\\\\cd\\;ef\\ng\\:", StringUtils.escape(",ab\\cd;ef\r\ng:", '\\', "\\,;:\n\r", map1));
		assertEquals("\\,\\\\\\;\\:", StringUtils.escape(",\\;:", '\\', "\\,;:\n", map1));
		assertEquals("+,+,+,+,+,", StringUtils.escape(",,,,,", '+', "\\,;:+\n", map1));
	}


	private String wrapWriteEscaped(String raw, char escapeChar, String specialChars, Map<Integer, Integer> replacements) throws IOException
	{
		Writer w = new StringWriter();
		StringUtils.writeEscaped(w, raw, escapeChar, specialChars, replacements);
		return w.toString();
	}


	/**
	 * Test StringUtils.writeEscaped(Writer, String, char, String, Map)
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWriteEscapedWriterStringCharStringMap() throws IOException
	{
		Map<Integer, Integer> map0 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		map1.put((int) '\n', (int) 'n');
		map1.put((int) '\r', null);

		assertEquals("", wrapWriteEscaped(null, '\\', "", null));
		assertEquals("", wrapWriteEscaped(null, '\\', "\\", null));
		assertEquals("", wrapWriteEscaped(null, '\\', "\\,;:", null));

		assertEquals("", wrapWriteEscaped("", '\\', "", null));
		assertEquals("", wrapWriteEscaped("", '\\', "\\", null));
		assertEquals("", wrapWriteEscaped("", '\\', "\\,;:", null));

		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "", null));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "\\", null));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "\\,;:", null));

		assertEquals("abc\\defg", wrapWriteEscaped("abc\\defg", '\\', "", null));
		assertEquals("abc\\\\defg", wrapWriteEscaped("abc\\defg", '\\', "\\", null));
		assertEquals("abc\\\\defg", wrapWriteEscaped("abc\\defg", '\\', "\\,;:", null));

		assertEquals("abc\\de\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "", null));
		assertEquals("abc\\\\de\\\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "\\", null));
		assertEquals("abc\\\\de\\\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "\\,;:", null));

		assertEquals(",abc\\de\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "", null));
		assertEquals(",abc\\\\de\\\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "\\", null));
		assertEquals("\\,abc\\\\de\\\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "\\,;:\n", null));

		assertEquals("abcdefg\\\\", wrapWriteEscaped("abcdefg\\", '\\', "\\,;:\n", null));
		assertEquals("abcdefg\\,", wrapWriteEscaped("abcdefg,", '\\', "\\,;:\n", null));
		assertEquals("abcdefg\\;", wrapWriteEscaped("abcdefg;", '\\', "\\,;:\n", null));
		assertEquals("abcdefg\\:", wrapWriteEscaped("abcdefg:", '\\', "\\,;:\n", null));

		assertEquals("\\\\abcdefg", wrapWriteEscaped("\\abcdefg", '\\', "\\,;:\n", null));
		assertEquals("\\,abcdefg", wrapWriteEscaped(",abcdefg", '\\', "\\,;:\n", null));
		assertEquals("\\;abcdefg", wrapWriteEscaped(";abcdefg", '\\', "\\,;:\n", null));
		assertEquals("\\:abcdefg", wrapWriteEscaped(":abcdefg", '\\', "\\,;:\n", null));

		assertEquals("ab\\\\cdefg", wrapWriteEscaped("ab\\cdefg", '\\', "\\,;:\n", null));
		assertEquals("ab\\,cdefg", wrapWriteEscaped("ab,cdefg", '\\', "\\,;:\n", null));
		assertEquals("ab\\;cdefg", wrapWriteEscaped("ab;cdefg", '\\', "\\,;:\n", null));
		assertEquals("ab\\:cdefg", wrapWriteEscaped("ab:cdefg", '\\', "\\,;:\n", null));

		assertEquals("\\,ab\\\\cd\\;efn\\\ng\\:", wrapWriteEscaped(",ab\\cd;efn\ng:", '\\', "\\,;:\n", null));
		assertEquals("\\,\\\\\\;\\:", wrapWriteEscaped(",\\;:", '\\', "\\,;:\n", null));
		assertEquals("+,+,+,+,+,", wrapWriteEscaped(",,,,,", '+', "\\,;:+\n", null));

		assertEquals("", wrapWriteEscaped(null, '\\', "", map0));
		assertEquals("", wrapWriteEscaped(null, '\\', "\\", map0));
		assertEquals("", wrapWriteEscaped(null, '\\', "\\,;:", map0));

		assertEquals("", wrapWriteEscaped("", '\\', "", map0));
		assertEquals("", wrapWriteEscaped("", '\\', "\\", map0));
		assertEquals("", wrapWriteEscaped("", '\\', "\\,;:", map0));

		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "", map0));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "\\", map0));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "\\,;:", map0));

		assertEquals("abc\\defg", wrapWriteEscaped("abc\\defg", '\\', "", map0));
		assertEquals("abc\\\\defg", wrapWriteEscaped("abc\\defg", '\\', "\\", map0));
		assertEquals("abc\\\\defg", wrapWriteEscaped("abc\\defg", '\\', "\\,;:", map0));

		assertEquals("abc\\de\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "", map0));
		assertEquals("abc\\\\de\\\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "\\", map0));
		assertEquals("abc\\\\de\\\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "\\,;:", map0));

		assertEquals(",abc\\de\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "", map0));
		assertEquals(",abc\\\\de\\\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "\\", map0));
		assertEquals("\\,abc\\\\de\\\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "\\,;:\n", map0));

		assertEquals("abcdefg\\\\", wrapWriteEscaped("abcdefg\\", '\\', "\\,;:\n", map0));
		assertEquals("abcdefg\\,", wrapWriteEscaped("abcdefg,", '\\', "\\,;:\n", map0));
		assertEquals("abcdefg\\;", wrapWriteEscaped("abcdefg;", '\\', "\\,;:\n", map0));
		assertEquals("abcdefg\\:", wrapWriteEscaped("abcdefg:", '\\', "\\,;:\n", map0));

		assertEquals("\\\\abcdefg", wrapWriteEscaped("\\abcdefg", '\\', "\\,;:\n", map0));
		assertEquals("\\,abcdefg", wrapWriteEscaped(",abcdefg", '\\', "\\,;:\n", map0));
		assertEquals("\\;abcdefg", wrapWriteEscaped(";abcdefg", '\\', "\\,;:\n", map0));
		assertEquals("\\:abcdefg", wrapWriteEscaped(":abcdefg", '\\', "\\,;:\n", map0));

		assertEquals("ab\\\\cdefg", wrapWriteEscaped("ab\\cdefg", '\\', "\\,;:\n", map0));
		assertEquals("ab\\,cdefg", wrapWriteEscaped("ab,cdefg", '\\', "\\,;:\n", map0));
		assertEquals("ab\\;cdefg", wrapWriteEscaped("ab;cdefg", '\\', "\\,;:\n", map0));
		assertEquals("ab\\:cdefg", wrapWriteEscaped("ab:cdefg", '\\', "\\,;:\n", map0));

		assertEquals("\\,ab\\\\cd\\;efn\\\ng\\:", wrapWriteEscaped(",ab\\cd;efn\ng:", '\\', "\\,;:\n", map0));
		assertEquals("\\,\\\\\\;\\:", wrapWriteEscaped(",\\;:", '\\', "\\,;:\n", map0));
		assertEquals("+,+,+,+,+,", wrapWriteEscaped(",,,,,", '+', "\\,;:+\n", map0));

		assertEquals("", wrapWriteEscaped(null, '\\', "", map1));
		assertEquals("", wrapWriteEscaped(null, '\\', "\\", map1));
		assertEquals("", wrapWriteEscaped(null, '\\', "\\,;:", map1));

		assertEquals("", wrapWriteEscaped("", '\\', "", map1));
		assertEquals("", wrapWriteEscaped("", '\\', "\\", map1));
		assertEquals("", wrapWriteEscaped("", '\\', "\\,;:", map1));

		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "", map1));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "\\", map1));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg", '\\', "\\,;:", map1));

		assertEquals("abc\\defg", wrapWriteEscaped("abc\\defg", '\\', "", map1));
		assertEquals("abc\\\\defg", wrapWriteEscaped("abc\\defg", '\\', "\\", map1));
		assertEquals("abc\\\\defg", wrapWriteEscaped("abc\\defg", '\\', "\\,;:", map1));

		assertEquals("abc\\de\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "", map1));
		assertEquals("abc\\\\de\\\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "\\", map1));
		assertEquals("abc\\\\de\\\\fg", wrapWriteEscaped("abc\\de\\fg", '\\', "\\,;:", map1));

		assertEquals(",abc\\de\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "", map1));
		assertEquals(",abc\\\\de\\\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "\\", map1));
		assertEquals("\\,abc\\\\de\\\\fg", wrapWriteEscaped(",abc\\de\\fg", '\\', "\\,;:\n", map1));

		assertEquals("abcdefg\\\\", wrapWriteEscaped("abcdefg\\", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\,", wrapWriteEscaped("abcdefg,", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\;", wrapWriteEscaped("abcdefg;", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\:", wrapWriteEscaped("abcdefg:", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg\\n", wrapWriteEscaped("abcdefg\n", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg", wrapWriteEscaped("abcdefg\r", '\\', "\\,;:\n\r", map1));

		assertEquals("\\\\abcdefg", wrapWriteEscaped("\\abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\,abcdefg", wrapWriteEscaped(",abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\;abcdefg", wrapWriteEscaped(";abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\:abcdefg", wrapWriteEscaped(":abcdefg", '\\', "\\,;:\n", map1));
		assertEquals("\\nabcdefg", wrapWriteEscaped("\nabcdefg", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg", wrapWriteEscaped("\rabcdefg", '\\', "\\,;:\n\r", map1));

		assertEquals("ab\\\\cdefg", wrapWriteEscaped("ab\\cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\,cdefg", wrapWriteEscaped("ab,cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\;cdefg", wrapWriteEscaped("ab;cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\:cdefg", wrapWriteEscaped("ab:cdefg", '\\', "\\,;:\n", map1));
		assertEquals("ab\\ncdefg", wrapWriteEscaped("ab\ncdefg", '\\', "\\,;:\n", map1));
		assertEquals("abcdefg", wrapWriteEscaped("ab\rcdefg", '\\', "\\,;:\n\r", map1));

		assertEquals("\\,ab\\\\cd\\;ef\ng\\:", wrapWriteEscaped(",ab\\cd;ef\ng:", '\\', "\\,;:", map1));
		assertEquals("\\,ab\\\\cd\\;ef\\ng\\:", wrapWriteEscaped(",ab\\cd;ef\r\ng:", '\\', "\\,;:\n\r", map1));
		assertEquals("\\,\\\\\\;\\:", wrapWriteEscaped(",\\;:", '\\', "\\,;:\n", map1));
		assertEquals("+,+,+,+,+,", wrapWriteEscaped(",,,,,", '+', "\\,;:+\n", map1));
	}


	/**
	 * Test StringUtils.unescape(String, char, Map)
	 */
	@Test
	public void testUnescapeStringCharMap()
	{
		Map<Integer, Integer> map0 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		map1.put((int) 'n', (int) '\n');
		map1.put((int) 'N', (int) 'x');

		assertNull(StringUtils.unescape(null, '\\', null));
		assertNull(StringUtils.unescape(null, '\\', map0));
		assertNull(StringUtils.unescape(null, '\\', map1));

		assertEquals("", StringUtils.unescape("", '\\', null));
		assertEquals("", StringUtils.unescape("", '\\', map0));
		assertEquals("", StringUtils.unescape("", '\\', map1));

		assertEquals("abcdefg", StringUtils.unescape("abcdefg", '\\', null));
		assertEquals("abcdefg", StringUtils.unescape("abcdefg", '\\', map0));
		assertEquals("abcdefg", StringUtils.unescape("abcdefg", '\\', map1));

		assertEquals("abcdefg", StringUtils.unescape("\\abcdefg", '\\', null));
		assertEquals("abcdefg", StringUtils.unescape("\\abcdefg", '\\', map0));
		assertEquals("abcdefg", StringUtils.unescape("\\abcdefg", '\\', map1));

		assertEquals("abcdefg", StringUtils.unescape("abc\\defg", '\\', null));
		assertEquals("abcdefg", StringUtils.unescape("abc\\defg", '\\', map0));
		assertEquals("abcdefg", StringUtils.unescape("abc\\defg", '\\', map1));

		assertEquals("abcdefg\\", StringUtils.unescape("abcdefg\\", '\\', null));
		assertEquals("abcdefg\\", StringUtils.unescape("abcdefg\\", '\\', map0));
		assertEquals("abcdefg\\", StringUtils.unescape("abcdefg\\", '\\', map1));

		assertEquals("abcdefg", StringUtils.unescape("abcdef\\g", '\\', null));
		assertEquals("abcdefg", StringUtils.unescape("abcdef\\g", '\\', map0));
		assertEquals("abcdefg", StringUtils.unescape("abcdef\\g", '\\', map1));

		assertEquals("abcdefg\\", StringUtils.unescape("\\abc\\def\\g\\", '\\', null));
		assertEquals("abcdefg\\", StringUtils.unescape("\\abc\\def\\g\\", '\\', map0));
		assertEquals("abcdefg\\", StringUtils.unescape("\\abc\\def\\g\\", '\\', map1));

		assertEquals("\\abc\\def\\g\\", StringUtils.unescape("\\\\abc\\\\def\\\\g\\\\", '\\', null));
		assertEquals("\\abc\\def\\g\\", StringUtils.unescape("\\\\abc\\\\def\\\\g\\\\", '\\', map0));
		assertEquals("\\abc\\def\\g\\", StringUtils.unescape("\\\\abc\\\\def\\\\g\\\\", '\\', map1));

		assertEquals("\\abc\\def\\g\\\\", StringUtils.unescape("\\\\\\abc\\\\\\def\\\\\\g\\\\\\", '\\', null));
		assertEquals("\\abc\\def\\g\\\\", StringUtils.unescape("\\\\\\abc\\\\\\def\\\\\\g\\\\\\", '\\', map0));
		assertEquals("\\abc\\def\\g\\\\", StringUtils.unescape("\\\\\\abc\\\\\\def\\\\\\g\\\\\\", '\\', map1));

		assertEquals("abcnefg\\", StringUtils.unescape("\\abc\\nef\\g\\", '\\', null));
		assertEquals("abcnefg\\", StringUtils.unescape("\\abc\\nef\\g\\", '\\', map0));
		assertEquals("abc\nefg\\", StringUtils.unescape("\\abc\\nef\\g\\", '\\', map1));

		assertEquals("n\\abc\\nef\\g\\n", StringUtils.unescape("\\n\\\\abc\\\\nef\\\\g\\\\\\n", '\\', null));
		assertEquals("n\\abc\\nef\\g\\n", StringUtils.unescape("\\n\\\\abc\\\\nef\\\\g\\\\\\n", '\\', map0));
		assertEquals("\n\\abc\\nef\\g\\\n", StringUtils.unescape("\\n\\\\abc\\\\nef\\\\g\\\\\\n", '\\', map1));

		assertEquals("N\\abc\\Nef\\g\\N", StringUtils.unescape("\\N\\\\abc\\\\Nef\\\\g\\\\\\N", '\\', null));
		assertEquals("N\\abc\\Nef\\g\\N", StringUtils.unescape("\\N\\\\abc\\\\Nef\\\\g\\\\\\N", '\\', map0));
		assertEquals("x\\abc\\Nef\\g\\x", StringUtils.unescape("\\N\\\\abc\\\\Nef\\\\g\\\\\\N", '\\', map1));

		assertEquals("\\abc\\nef\\g\\\\", StringUtils.unescape("\\\\\\abc\\\\\\nef\\\\\\g\\\\\\", '\\', null));
		assertEquals("\\abc\\nef\\g\\\\", StringUtils.unescape("\\\\\\abc\\\\\\nef\\\\\\g\\\\\\", '\\', map0));
		assertEquals("\\abc\\\nef\\g\\\\", StringUtils.unescape("\\\\\\abc\\\\\\nef\\\\\\g\\\\\\", '\\', map1));

	}


	/**
	 * Test StringUtils.isEscaped(String, char, int)
	 */
	@Test
	public void testIsEscaped()
	{
		assertFalse(StringUtils.isEscaped("", '\\', -1));
		assertFalse(StringUtils.isEscaped("", '\\', 0));

		assertFalse(StringUtils.isEscaped("1234", '\\', 0));
		assertFalse(StringUtils.isEscaped("1234", '\\', 1));
		assertFalse(StringUtils.isEscaped("1234", '\\', 2));
		assertFalse(StringUtils.isEscaped("1234", '\\', 3));

		assertFalse(StringUtils.isEscaped("\\1234", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\1234", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\1234", '\\', 2));
		assertFalse(StringUtils.isEscaped("\\1234", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\1234", '\\', 4));

		assertFalse(StringUtils.isEscaped("1\\234", '\\', 0));
		assertFalse(StringUtils.isEscaped("1\\234", '\\', 1));
		assertTrue(StringUtils.isEscaped("1\\234", '\\', 2));
		assertFalse(StringUtils.isEscaped("1\\234", '\\', 3));
		assertFalse(StringUtils.isEscaped("1\\234", '\\', 4));

		assertFalse(StringUtils.isEscaped("\\\\1234", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\\\1234", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\\\1234", '\\', 2));
		assertFalse(StringUtils.isEscaped("\\\\1234", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\\\1234", '\\', 4));
		assertFalse(StringUtils.isEscaped("\\\\1234", '\\', 5));

		assertFalse(StringUtils.isEscaped("\\\\\\1234", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\\\\\1234", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\\\\\1234", '\\', 2));
		assertTrue(StringUtils.isEscaped("\\\\\\1234", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\\\\\1234", '\\', 4));
		assertFalse(StringUtils.isEscaped("\\\\\\1234", '\\', 5));
		assertFalse(StringUtils.isEscaped("\\\\\\1234", '\\', 6));

		assertFalse(StringUtils.isEscaped("1\\\\234", '\\', 0));
		assertFalse(StringUtils.isEscaped("1\\\\234", '\\', 1));
		assertTrue(StringUtils.isEscaped("1\\\\234", '\\', 2));
		assertFalse(StringUtils.isEscaped("1\\\\234", '\\', 3));
		assertFalse(StringUtils.isEscaped("1\\\\234", '\\', 4));
		assertFalse(StringUtils.isEscaped("1\\\\234", '\\', 5));

		assertFalse(StringUtils.isEscaped("1\\\\\\234", '\\', 0));
		assertFalse(StringUtils.isEscaped("1\\\\\\234", '\\', 1));
		assertTrue(StringUtils.isEscaped("1\\\\\\234", '\\', 2));
		assertFalse(StringUtils.isEscaped("1\\\\\\234", '\\', 3));
		assertTrue(StringUtils.isEscaped("1\\\\\\234", '\\', 4));
		assertFalse(StringUtils.isEscaped("1\\\\\\234", '\\', 5));
		assertFalse(StringUtils.isEscaped("1\\\\\\234", '\\', 6));

		assertFalse(StringUtils.isEscaped("\\1\\234", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\1\\234", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\1\\234", '\\', 2));
		assertTrue(StringUtils.isEscaped("\\1\\234", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\1\\234", '\\', 4));
		assertFalse(StringUtils.isEscaped("\\1\\234", '\\', 5));

		assertFalse(StringUtils.isEscaped("\\1\\23\\4", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\1\\23\\4", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\1\\23\\4", '\\', 2));
		assertTrue(StringUtils.isEscaped("\\1\\23\\4", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\1\\23\\4", '\\', 4));
		assertFalse(StringUtils.isEscaped("\\1\\23\\4", '\\', 5));
		assertTrue(StringUtils.isEscaped("\\1\\23\\4", '\\', 6));

		assertFalse(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 2));
		assertFalse(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 3));
		assertTrue(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 4));
		assertFalse(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 5));
		assertFalse(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 6));
		assertTrue(StringUtils.isEscaped("\\\\1\\23\\4", '\\', 7));

		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 2));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 4));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 5));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 6));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 7));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\\\x", '\\', 8));

		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 0));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 1));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 2));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 3));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 4));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 5));
		assertFalse(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 6));
		assertTrue(StringUtils.isEscaped("\\\\\\\\\\\\\\x", '\\', 7));
	}


	@Test
	public void testSplitEscapedListStringCharString()
	{
		assertNull(StringUtils.splitEscapedList(null, '\\', ","));

		List<String> testList = new ArrayList<String>();

		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("", '\\', ","));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList(",", '\\', ","));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList(",,", '\\', ","));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList(",,,", '\\', ","));

		testList.clear();
		testList.add("abc");
		assertEquals(testList, StringUtils.splitEscapedList("abc", '\\', ","));
		testList.add("def");
		assertEquals(testList, StringUtils.splitEscapedList("abc,def", '\\', ","));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("abc,def,ghi", '\\', ","));

		testList.clear();
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c", '\\', ","));
		testList.add("def\\,");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c,def\\,", '\\', ","));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c,def\\,,ghi", '\\', ","));

		testList.clear();
		testList.add("");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList(",ab\\c", '\\', ","));
		testList.add("def\\,");
		assertEquals(testList, StringUtils.splitEscapedList(",ab\\c,def\\,", '\\', ","));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList(",ab\\c,def\\,,ghi", '\\', ","));

		testList.clear();
		testList.add("\\\\\\,");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\,,ab\\c", '\\', ","));
		testList.add("def\\\\");
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\,,ab\\c,def\\\\,", '\\', ","));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\,,ab\\c,def\\\\,,ghi", '\\', ","));

		testList.clear();
		testList.add("\\\\");
		testList.add("");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\,,ab\\c", '\\', ","));
		testList.add("def\\\\\\,");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\,,ab\\c,def\\\\\\,", '\\', ","));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\,,ab\\c,def\\\\\\,,ghi", '\\', ","));

		// if escapeChar == delimiterChar splitEscapedList works just like a trivial split
		testList.clear();
		testList.add("");
		testList.add("abc");
		testList.add("");
		testList.add("");
		testList.add("");
		testList.add("def");
		testList.add("");
		testList.add("");
		testList.add("ghi");
		testList.add("");
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("\\abc\\\\\\\\def\\\\\\ghi\\\\", '\\', "\\"));

		testList.clear();
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("", '\\', "-.-"));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("-.-", '\\', "-.-"));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("-.--.-", '\\', "-.-"));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("-.--.--.-", '\\', "-.-"));

		testList.clear();
		testList.add("abc");
		assertEquals(testList, StringUtils.splitEscapedList("abc", '\\', "-.-"));
		testList.add("def");
		assertEquals(testList, StringUtils.splitEscapedList("abc-.-def", '\\', "-.-"));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("abc-.-def-.-ghi", '\\', "-.-"));

		testList.clear();
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c", '\\', "-.-"));
		testList.add("def\\-.-");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c-.-def\\-.-", '\\', "-.-"));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c-.-def\\-.--.-ghi", '\\', "-.-"));

		testList.clear();
		testList.add("");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("-.-ab\\c", '\\', "-.-"));
		testList.add("def\\-.-");
		assertEquals(testList, StringUtils.splitEscapedList("-.-ab\\c-.-def\\-.-", '\\', "-.-"));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("-.-ab\\c-.-def\\-.--.-ghi", '\\', "-.-"));

		testList.clear();
		testList.add("\\\\\\-.-");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\-.--.-ab\\c", '\\', "-.-"));
		testList.add("def\\\\");
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\-.--.-ab\\c-.-def\\\\-.-", '\\', "-.-"));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\-.--.-ab\\c-.-def\\\\-.--.-ghi", '\\', "-.-"));

		testList.clear();
		testList.add("\\\\");
		testList.add("");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\-.--.-ab\\c", '\\', "-.-"));
		testList.add("def\\\\\\-.-");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\-.--.-ab\\c-.-def\\\\\\-.-", '\\', "-.-"));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\-.--.-ab\\c-.-def\\\\\\-.--.-ghi", '\\', "-.-"));

		testList.clear();
		testList.add("\\abc\\\\\\\\.\\def\\\\");
		testList.add("ghi\\\\.\\");
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("\\abc\\\\\\\\.\\def\\\\\\.\\ghi\\\\.\\\\.\\", '\\', "\\.\\"));
	}


	@Test
	public void testSplitEscapedListStringCharChar()
	{
		assertNull(StringUtils.splitEscapedList(null, '\\', ','));

		List<String> testList = new ArrayList<String>();

		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("", '\\', ','));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList(",", '\\', ','));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList(",,", '\\', ','));
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList(",,,", '\\', ','));

		testList.clear();
		testList.add("abc");
		assertEquals(testList, StringUtils.splitEscapedList("abc", '\\', ','));
		testList.add("def");
		assertEquals(testList, StringUtils.splitEscapedList("abc,def", '\\', ','));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("abc,def,ghi", '\\', ','));

		testList.clear();
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c", '\\', ','));
		testList.add("def\\,");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c,def\\,", '\\', ','));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("ab\\c,def\\,,ghi", '\\', ','));

		testList.clear();
		testList.add("");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList(",ab\\c", '\\', ','));
		testList.add("def\\,");
		assertEquals(testList, StringUtils.splitEscapedList(",ab\\c,def\\,", '\\', ','));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList(",ab\\c,def\\,,ghi", '\\', ','));

		testList.clear();
		testList.add("\\\\\\,");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\,,ab\\c", '\\', ','));
		testList.add("def\\\\");
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\,,ab\\c,def\\\\,", '\\', ','));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\\\,,ab\\c,def\\\\,,ghi", '\\', ','));

		testList.clear();
		testList.add("\\\\");
		testList.add("");
		testList.add("ab\\c");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\,,ab\\c", '\\', ','));
		testList.add("def\\\\\\,");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\,,ab\\c,def\\\\\\,", '\\', ','));
		testList.add("ghi");
		assertEquals(testList, StringUtils.splitEscapedList("\\\\,,ab\\c,def\\\\\\,,ghi", '\\', ','));

		// if escapeChar == delimiterChar splitEscapedList works just like a trivial split
		testList.clear();
		testList.add("");
		testList.add("abc");
		testList.add("");
		testList.add("");
		testList.add("");
		testList.add("def");
		testList.add("");
		testList.add("");
		testList.add("ghi");
		testList.add("");
		testList.add("");
		assertEquals(testList, StringUtils.splitEscapedList("\\abc\\\\\\\\def\\\\\\ghi\\\\", '\\', '\\'));
	}
}
