/*
 * dmfs - http://dmfs.org/
 *
 * Copyright (C) 2011 Marten Gajda <marten@dmfs.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package org.dmfs.stringutils;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * A collection of several String helper routines.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 */
public final class StringUtils
{
	public final static String EMPTY_STRING = "";


	/**
	 * Private constructor.
	 */
	private StringUtils()
	{
	}


	/**
	 * Find the first occurrence of any character in needles in {@code str} from the position {@code start} on.
	 * 
	 * @param str
	 *            {@link String} to scan.
	 * 
	 * @param start
	 *            Position of first character to take into account.
	 * 
	 * @param needles
	 *            Characters to scan for.
	 * 
	 * @return The first occurrence of any element of needles or {@code -1} of none was found.
	 * 
	 */
	public static int minIndexOfOneOf(String str, int start, String needles)
	{
		if (str == null || needles == null || str.length() == 0 && needles.length() > 0)
		{
			return -1;
		}

		// empty needles always match first character
		if (needles.length() == 0)
		{
			return 0;
		}

		int len = str.length();

		while (start < len)
		{
			if (needles.indexOf(str.charAt(start)) >= 0)
			{
				return start;
			}
			++start;
		}
		return -1;
	}


	/**
	 * Join multiple strings with delimiter.
	 * 
	 * @param delimiter
	 *            A {@link String} that is used as glue between the elements of {@code strings}.
	 * 
	 * @param strings
	 *            Array of {@link String}s that are to be concatenated.
	 * 
	 * @return A {@link String} with the concatenated elements of {@code strings}.
	 */
	public static String join(String delimiter, String... strings)
	{
		if (strings == null)
		{
			return null;
		}
		int count;
		if ((count = strings.length) == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		int len = 0;
		for (String s : strings)
		{
			len += s.length();
		}

		StringBuilder sb = new StringBuilder((count - 1) * delimiter.length() + len);

		sb.append(strings[0]);
		for (int i = 1; i < count; ++i)
		{
			sb.append(delimiter);
			sb.append(strings[i]);
		}
		return sb.toString();
	}


	/**
	 * Join multiple strings with delimiter.
	 * 
	 * @param delimiter
	 *            A {@link String} that is used as glue between the elements of {@code strings}.
	 * 
	 * @param strings
	 *            An {@link Iterable} of {@link String}s that are to be concatenated.
	 * 
	 * @return A {@link String} with the concatenated elements of {@code strings}.
	 */
	public static String join(String delimiter, Iterable<String> strings)
	{
		if (strings == null)
		{
			return null;
		}

		int len = 0;
		int count = 0;
		for (String s : strings)
		{
			len += s.length();
			++count;
		}

		if (count == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		StringBuilder sb = new StringBuilder((count - 1) * delimiter.length() + len);

		Iterator<String> it = strings.iterator();
		sb.append(it.next());
		while (it.hasNext())
		{
			sb.append(delimiter);
			sb.append(it.next());
		}
		return sb.toString();
	}


	/**
	 * Join multiple strings with delimiter.
	 * 
	 * @param delimiter
	 *            A {@code char} that is used as glue between the elements of {@code strings}.
	 * 
	 * @param strings
	 *            Array of {@link String}s that are to be concatenated.
	 * 
	 * @return A {@link String} with the concatenated elements of {@code strings}.
	 */
	public static String join(char delimiter, String... strings)
	{
		if (strings == null)
		{
			return null;
		}
		int count;
		if ((count = strings.length) == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		int len = 0;
		for (String s : strings)
		{
			len += s.length();
		}

		StringBuilder sb = new StringBuilder(strings.length - 1 + len);

		sb.append(strings[0]);
		for (int i = 1; i < count; ++i)
		{
			sb.append(delimiter);
			sb.append(strings[i]);
		}
		return sb.toString();
	}


	/**
	 * Join multiple strings with delimiter.
	 * 
	 * @param delimiter
	 *            A {@code char} that is used as glue between the elements of {@code strings}.
	 * 
	 * @param strings
	 *            An {@link Iterable} of {@link String}s that are to be concatenated.
	 * 
	 * @return A {@link String} with the concatenated elements of {@code strings}.
	 */
	public static String join(char delimiter, Iterable<String> strings)
	{
		if (strings == null)
		{
			return null;
		}

		int len = 0;
		int count = 0;
		for (String s : strings)
		{
			len += s.length();
			++count;
		}

		if (count == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		StringBuilder sb = new StringBuilder(count - 1 + len);

		Iterator<String> it = strings.iterator();
		sb.append(it.next());
		while (it.hasNext())
		{
			sb.append(delimiter);
			sb.append(it.next());
		}
		return sb.toString();
	}


	/**
	 * Join multiple strings with delimiter.
	 * 
	 * @param strings
	 *            An {@link Iterable} of {@link String}s that are to be concatenated.
	 * 
	 * @return A {@link String} with the concatenated elements of {@code strings}.
	 */
	public static String join(Iterable<String> strings)
	{
		if (strings == null)
		{
			return null;
		}

		int len = 0;
		for (String s : strings)
		{
			len += s.length();
		}

		if (len == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		StringBuilder sb = new StringBuilder(len);

		for (String s : strings)
		{
			sb.append(s);
		}
		return sb.toString();
	}


	/***
	 * Joins the {@link String}s in {@code strings} in the order of {@code order}.
	 * 
	 * @param strings
	 *            The Strings to join.
	 * @param order
	 *            The ordered indexes of strings.
	 * @return The joined String of null if there is nothing to join.
	 */
	public static String orderedJoin(String[] strings, int... order)
	{
		if (strings == null || order == null || strings.length == 0 || order.length == 0)
		{
			return null;
		}

		int bufferSize = 0;
		int elements = strings.length;

		for (int number : order)
		{
			if (number >= 0 && number < elements)
			{
				bufferSize += strings[number].length();
			}
		}

		if (bufferSize == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		StringBuilder sb = new StringBuilder(bufferSize);
		for (int number : order)
		{
			if (number >= 0 && number < elements)
			{
				sb.append(strings[number]);
			}
		}
		return sb.toString();
	}


	/***
	 * Joins the {@link String}s in {@code strings} in the order of {@code order} with delimiter.
	 * 
	 * @param strings
	 *            The Strings to join.
	 * @param delimiter
	 *            The delimiter String.
	 * @param order
	 *            The ordered indexes of strings.
	 * @return The joined String of null if there is nothing to join.
	 */
	public static String orderedJoin(String[] strings, String delimiter, int... order)
	{
		if (strings == null || order == null || strings.length == 0 || order.length == 0)
		{
			return null;
		}

		int bufferSize = 0;
		int elements = strings.length;
		int count = 0;

		for (int number : order)
		{
			if (number >= 0 && number < elements)
			{
				bufferSize += strings[number].length();
				++count;
			}
		}

		if (bufferSize == 0)
		{
			// no need to instantiate a StringBuilder
			return EMPTY_STRING;
		}

		StringBuilder sb = new StringBuilder(bufferSize + (count - 1) * delimiter.length());
		boolean first = true;
		for (int number : order)
		{
			if (number >= 0 && number < elements)
			{
				if (!first)
				{
					sb.append(delimiter);
				}
				else
				{
					first = false;
				}
				sb.append(strings[number]);
			}
		}
		return sb.toString();
	}


	/**
	 * Escapes all occurrences of characters in {@code specialChars} in {@code raw} by preceding it with {@code escapeChar}.
	 * 
	 * @param raw
	 *            Unescaped {@link String}.
	 * 
	 * @param escapeChar
	 *            {@link char} that is inserted on each occurrence of the chars in {@code specialChars}.
	 * 
	 * @param specialChars
	 *            List of {@code char}s that are to be escaped (should contain {@code escapeChar}).
	 * 
	 * @return The escaped representation of {@code raw} or {@code null} if {@code raw == null}.
	 */
	public static String escape(String raw, char escapeChar, String specialChars)
	{
		if (raw == null || raw.length() == 0 || specialChars == null || specialChars.length() == 0)
		{
			return raw;
		}

		StringBuilder result = null;
		int pos;
		int copypos = 0;
		int scanpos = 0;

		while ((pos = minIndexOfOneOf(raw, scanpos, specialChars)) >= 0)
		{
			if (result == null)
			{
				result = new StringBuilder(raw.length() * 2);
			}
			result.append(raw.substring(copypos, pos));
			result.append(escapeChar);
			copypos = pos;
			scanpos = pos + 1;
		}

		if (scanpos == 0)
		{
			// nothing found that needs to be escaped
			return raw;
		}
		else
		{
			result.append(raw.substring(copypos));
			return result.toString();
		}
	}


	/**
	 * Escapes all occurrences of characters in {@code specialChars} in {@code raw} by preceding it with {@code escapeChar}.
	 * 
	 * Every special character that is a key in {@code replacements} will be replaced by the respective value.
	 * 
	 * If the replacement is {@code null} no escape character is written.
	 * 
	 * @param raw
	 *            Unescaped {@link String}.
	 * 
	 * @param escapeChar
	 *            {@link char} that is inserted in front of each occurrence of the chars in {@code specialChars}.
	 * 
	 * @param specialChars
	 *            List of {@link char}s that are to be escaped
	 * 
	 * @param replacements
	 *            {@link Map} of characters to characters that are to be replaced.
	 * 
	 * @return The escaped representation of {@code raw} or {@code null} if {@code raw == null}.
	 */

	public static String escape(String raw, char escapeChar, String specialChars, Map<Integer, Integer> replacements)
	{
		if (raw == null || raw.length() == 0 || specialChars == null || specialChars.length() == 0)
		{
			return raw;
		}

		StringBuilder result = null;
		int pos;
		int copypos = 0;
		int scanpos = 0;

		while ((pos = minIndexOfOneOf(raw, scanpos, specialChars)) >= 0)
		{
			if (result == null)
			{
				result = new StringBuilder(raw.length() * 2);
			}
			result.append(raw.substring(copypos, pos));

			char c = raw.charAt(pos);
			if (replacements != null && replacements.containsKey((int) c))
			{
				Integer replacement = replacements.get((int) c);
				if (replacement != null)
				{
					result.append(escapeChar);
					result.append((char) (int) replacement);
				}
				copypos = pos + 1;
			}
			else
			{
				result.append(escapeChar);
				copypos = pos;
			}
			scanpos = pos + 1;
		}

		if (scanpos == 0)
		{
			// nothing found that needs to be escaped
			return raw;
		}
		else
		{
			if (copypos < raw.length())
			{
				result.append(raw.substring(copypos));
			}
			return result.toString();
		}
	}


	/**
	 * Escapes all occurrences of characters in {@code specialChars} in {@code raw} by preceding it with {@code escapeChar}. The result is written to
	 * {@code out}
	 * 
	 * Every special character that is a key in {@code replacements} will be replaced by the respective value.
	 * 
	 * If the replacement is {@code null} no escape character is written.
	 * 
	 * @param out
	 *            The {link Writer} to write the result to.
	 * 
	 * @param raw
	 *            unescaped {@link String}
	 * 
	 * @param escapeChar
	 *            {@link char} that is set before each occurrence of the chars in protectedChars
	 * 
	 * @param specialChars
	 *            list of {@link char}s that are to be escaped
	 * 
	 * @param replacements
	 *            {@link Map} of characters to characters that are to be replaced.
	 * 
	 * @throws IOException
	 */
	public static void writeEscaped(Writer out, String raw, char escapeChar, String specialChars, Map<Integer, Integer> replacements) throws IOException
	{
		if (raw == null || raw.length() == 0)
		{
			return;
		}

		if (specialChars == null || specialChars.length() == 0)
		{
			out.append(raw);
			return;
		}

		int pos;
		int start = 0;

		while ((pos = minIndexOfOneOf(raw, start, specialChars)) >= 0)
		{
			out.write(raw, start, pos - start);
			char c = raw.charAt(pos);
			if (replacements != null && replacements.containsKey((int) c))
			{
				Integer replacement = replacements.get((int) c);
				if (replacement != null)
				{
					out.write(escapeChar);
					out.write((char) (int) replacement);
				}
			}
			else
			{
				out.write(escapeChar);
				out.write(c);
			}
			start = pos + 1;
		}

		if (start < raw.length())
		{
			out.write(raw, start, raw.length() - start);
		}
	}


	/**
	 * Unescape the {@link String} {@code cooked}.
	 * 
	 * @param cooked
	 *            The escaped {@link String}.
	 * 
	 * @param escapeChar
	 *            The escape character.
	 * 
	 * @param replacements
	 *            A map of escaped entities to their unescaped representation.
	 * 
	 * @return The unescaped {@link String}.
	 * 
	 */
	public static String unescape(String cooked, char escapeChar, Map<Integer, Integer> replacements)
	{
		if (cooked == null)
		{
			return null;
		}

		int length = cooked.length();

		if (length <= 1)
		{
			return cooked;
		}

		StringBuilder result = null;

		int pos;
		int start = 0;

		while ((pos = cooked.indexOf(escapeChar, start)) >= 0)
		{
			if (result == null)
			{
				// assume the raw string is not longer than the cooked string
				result = new StringBuilder(length);
			}

			result.append(cooked.substring(start, pos));
			if (pos + 1 < length)
			{
				char c = cooked.charAt(pos + 1);
				if (replacements != null && replacements.containsKey((int) c))
				{
					Integer replacement = replacements.get((int) c);
					if (replacement != null)
					{
						result.append((char) (int) replacement);
					}
				}
				else
				{
					result.append(c);
				}
			}
			else
			{
				// was the last character of cooked, we keep it since it doesn't escape any other character
				result.append(escapeChar);
			}
			// skip escape character and escaped character
			start = pos + 2;
		}

		if (start == 0)
		{
			// no escaped characters found, return original string
			return cooked;
		}
		else
		{
			if (start < length)
			{
				// append remainder
				result.append(cooked.substring(start));
			}

			return result.toString();
		}
	}


	/**
	 * Checks whether the character at {@code pos} in {@code value} is escaped (i.e. is preceded by an odd number of {@code escapeChar}s)
	 * 
	 * @param value
	 *            The {@link String} to check.
	 * 
	 * @param escapeChar
	 *            The escape character.
	 * 
	 * @param pos
	 *            The position of the character to check.
	 * 
	 * @return {@code true} if the character at {@code pos} in {@code value} is escaped, {@code false} otherwise.
	 * 
	 */
	public static boolean isEscaped(String value, char escapeChar, int pos)
	{
		int scanpos = pos - 1;

		while (scanpos >= 0 && value.charAt(scanpos) == escapeChar)
		{
			scanpos--;
		}
		return ((pos - scanpos) & 1) == 0;
	}


	/**
	 * Split a {@link String} at every {@code delimiter} that is not escaped. The delimiter is not part of the resulting sections.
	 * <p>
	 * <b>Note:</b> Empty delimiters are not supported.
	 * </p>
	 * 
	 * @param value
	 *            {@link String} to split.
	 * 
	 * @param delimiter
	 *            Delimiter at which to split value.
	 * 
	 * @return a A {@link List} of {@link String}s with all parts of value or {@code null} if {@code value == null}.
	 * 
	 */
	public static List<String> splitEscapedList(String value, char escapeChar, String delimiter)
	{
		if (value == null)
		{
			return null;
		}

		int delimiter_len = delimiter.length();

		ArrayList<String> result = new ArrayList<String>();
		int start = 0;
		int scanstart = 0;
		int pos = 0;
		while ((pos = value.indexOf(delimiter, scanstart)) >= 0)
		{
			if (scanstart == pos || !isEscaped(value, escapeChar, pos))
			{
				result.add(value.substring(start, pos));
				start = pos + delimiter_len;
				scanstart = start + (delimiter_len == 0 ? 1 : 0);
			}
			else
			{
				scanstart = pos + delimiter_len;
			}
		}
		result.add(value.substring(start));
		return result;
	}


	/**
	 * Split a {@link String} at every {@code delimiter} that is not escaped. The delimiter is not part of the resulting sections.
	 * 
	 * @param value
	 *            {@link String} to split.
	 * 
	 * @param delimiter
	 *            Delimiter at which to split {@code value}.
	 * 
	 * @return A {@link List} of {@link String}s with all parts of value or {@code null} if {@code value == null}.
	 * 
	 */
	public static List<String> splitEscapedList(String value, char escapeChar, char delimiter)
	{
		if (value == null)
		{
			return null;
		}

		ArrayList<String> result = new ArrayList<String>();
		int start = 0;
		int scanstart = 0;
		int pos = 0;
		while ((pos = value.indexOf(delimiter, scanstart)) >= 0)
		{
			if (scanstart == pos || !isEscaped(value, escapeChar, pos))
			{
				result.add(value.substring(start, pos));
				start = pos + 1;
				scanstart = start;
			}
			else
			{
				scanstart = pos + 1;
			}
		}
		result.add(value.substring(start));
		return result;
	}

}
