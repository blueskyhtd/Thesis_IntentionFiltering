package com.viettel.hisone.intentionfilter.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.viettel.hisone.intentionfilter.model.Post;

/**
 * @author hadtt17
 * @since Sep 5, 2016
 * @modified Sep 5, 2016
 * @by hadtt17
 */

public class Calculator {

	public static double ROUND_NUMBER = 100000;

	public static Map<Integer, Double> prequentlyOfClass(List<Post> sources) {
		Map<Integer, Integer> countOfPrequently = new HashMap<Integer, Integer>();
		Map<Integer, Double> prequentlyOfClass = new HashMap<Integer, Double>();

		for (Post p : sources) {
			int classOfSen = p.getCls();

			if (countOfPrequently.containsKey(classOfSen)) {
				countOfPrequently.replace(classOfSen, countOfPrequently.get(classOfSen) + 1);
			} else {
				countOfPrequently.put(classOfSen, 1);
			}
		}

		for (Integer key : countOfPrequently.keySet()) {
			prequentlyOfClass.put(key,
					calPrequently(countOfPrequently.get(key).doubleValue(), (double) sources.size()));
		}

		return prequentlyOfClass;
	}

	public static Map<String, Integer> countTerm(List<String> sentences) {
		List<String> listTerm = new ArrayList<String>();
		Map<String, Integer> maps = new HashMap<String, Integer>();

		if (!CollectionUtils.isNullOrEmpty(sentences)) {
			for (String sentence : sentences) {
				if (!StringUtils.isNullOrEmpty(sentence)) {
					listTerm.addAll(Arrays.asList(StringUtils.splitWithSpace(sentence)));
				}
			}

			for (String term : listTerm) {
				if (maps.containsKey(term)) {
					maps.replace(term, maps.get(term).intValue() + 1);
				} else {
					maps.put(term, 1);
				}
			}
			return maps;
		}
		return null;
	}

	public static Map<String, Double> prequentlyOfTerm(List<String> sentences) {
		Map<String, Integer> listTerm = new HashMap<String, Integer>();
		Map<String, Integer> maps = new HashMap<String, Integer>();
		Map<String, Double> results = new HashMap<String, Double>();

		if (!CollectionUtils.isNullOrEmpty(sentences)) {
			listTerm = countTerm(sentences);

			for (String term : listTerm.keySet()) {
				for (String sentence : sentences) {
					if (sentence.contains(term)) {
						if (maps.containsKey(term)) {
							maps.replace(term, maps.get(term).intValue() + 1);
						} else {
							maps.put(term, 1);
						}
					}
				}
			}

			for (String term : maps.keySet()) {
				System.out
						.println(term + " : " + calPrequently(maps.get(term).doubleValue(), (double) sentences.size()));
				results.put(term, calPrequently(maps.get(term).doubleValue(), (double) sentences.size()));
			}

			return results;
		}
		return null;

	}

	public static Map<String, Double> prequentlyNegativeOfTerm(List<String> sentences) {
		Map<String, Integer> listTerm = new HashMap<String, Integer>();
		Map<String, Integer> maps = new HashMap<String, Integer>();
		Map<String, Double> results = new HashMap<String, Double>();

		if (!CollectionUtils.isNullOrEmpty(sentences)) {
			listTerm = countTerm(sentences);

			for (String term : listTerm.keySet()) {
				for (String sentence : sentences) {
					boolean isExist = false;
					if (sentence.contains(term)) {
						isExist = true;
					}
					if (!isExist) {
						if (maps.containsKey(term)) {
							maps.replace(term, maps.get(term).intValue() + 1);
						} else {
							maps.put(term, 1);
						}
					}
				}
			}

			System.out.println("Prequently of Negative Term: ");

			for (String term : maps.keySet()) {
				System.out
						.println(term + " : " + calPrequently(maps.get(term).doubleValue(), (double) sentences.size()));
				results.put(term, calPrequently(maps.get(term).doubleValue(), (double) sentences.size()));
			}

			return results;
		}
		return null;
	}

	public static Map<String, Double> calEntropyPositiveInClass(List<Post> mapPost, int cls) {
		System.out.println("Prequently POSITIVE Of Term class:" + cls);
		List<String> sentences = new ArrayList<String>();
		for (Post p : mapPost) {
			if (p.getCls() == cls) {
				sentences.add(p.getContent());
			}
		}

		return prequentlyOfTerm(sentences);
	}

	public static Map<String, Double> calEntropyNegativeInClass(List<Post> mapPost, int cls) {
		System.out.println("Prequently NEGATIVE Of Term class:" + cls);
		List<String> sentences = new ArrayList<String>();
		for (Post p : mapPost) {
			if (p.getCls() == cls) {
				sentences.add(p.getContent());
			}
		}

		return prequentlyNegativeOfTerm(sentences);
	}

	public static double calPrequently(double num, double total) {
		return Math.round(num / total * ROUND_NUMBER) / ROUND_NUMBER;
	}

	public static double round(double num) {
		return Math.round(num * ROUND_NUMBER) / ROUND_NUMBER;
	}
}
