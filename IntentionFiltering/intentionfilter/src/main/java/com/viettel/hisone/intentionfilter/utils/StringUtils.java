package com.viettel.hisone.intentionfilter.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * @author hadtt17
 * @since Sep 5, 2016
 * @modified Sep 5, 2016
 * @by hadtt17
 */

public class StringUtils {

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static String[] splitWithSpace(String str) {
		String[] results = str.replaceAll("[-+.^:,!?/()]", "").toLowerCase().split("\\s+");
		return results;
	}

	public static String clean(String str) throws IOException {
		// CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
		// stopWords.addAll(Arrays.asList());

		StandardTokenizer standardTokenizer = new StandardTokenizer();
		standardTokenizer.setReader(new StringReader(str));
		TokenStream tokenStream = new StopFilter(standardTokenizer, StopFilter.makeStopSet(StopWords.STOP_WORDS));
		StringBuilder sb = new StringBuilder();
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			String term = charTermAttribute.toString();
			sb.append(term + " ");
		}
		return sb.toString();
	}
}
