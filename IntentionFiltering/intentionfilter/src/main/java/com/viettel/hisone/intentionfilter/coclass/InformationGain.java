package com.viettel.hisone.intentionfilter.coclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.viettel.hisone.intentionfilter.model.Post;
import com.viettel.hisone.intentionfilter.utils.Calculator;
import com.viettel.hisone.intentionfilter.utils.CollectionUtils;
import com.viettel.hisone.intentionfilter.utils.IntentionUtils;

/**
 * @author hadtt17
 * @since Sep 5, 2016
 * @modified Sep 5, 2016
 * @by hadtt17
 */

public class InformationGain {

	private static Map<String, Double> prequentlyPositiveOfTermInClass0 = new HashMap<String, Double>();
	private static Map<String, Double> prequentlyNegativeOfTermInClass0 = new HashMap<String, Double>();
	private static Map<String, Double> prequentlyPositiveOfTermInClass1 = new HashMap<String, Double>();
	private static Map<String, Double> prequentlyNegativeOfTermInClass1 = new HashMap<String, Double>();

	public static Map<String, Double> calculateIG(List<Post> mapPost) {
		Map<String, Double> results = new HashMap<String, Double>();

		double igPrequently = calculateIGPrequentlyOfClass(mapPost);

		System.out.println("IG Frequently: " + igPrequently);

		Map<String, Double> igEntropy = calculateIGEntropyOfTerm(mapPost);

		System.out.println("Prequently IG Of Term :");
		for (String key : igEntropy.keySet()) {
			System.out.println(key + " : " + (igPrequently + igEntropy.get(key)));
			results.put(key, Calculator.round(igPrequently + igEntropy.get(key)));
		}

		return results;
	}

	public static Double calculateIGPrequentlyOfClass(List<Post> sources) {
		Map<Integer, Double> classes = Calculator.prequentlyOfClass(sources);

		double sum = 0;
		for (Integer key : classes.keySet()) {
			double prequentCi = classes.get(key).doubleValue();
			System.out.println("Class: " + key + " - " + prequentCi);
			sum = sum + Calculator.round((double) prequentCi * (double) Math.log10(prequentCi));
		}
		return sum * (-1);
	}

	public static Map<String, Double> calculateIGEntropyOfTerm(List<Post> mapPost) {
		List<String> lstSentence = new ArrayList<String>();

		for (Post p : mapPost) {
			lstSentence.add(p.getContent());
		}

		Map<String, Double> prequentlyOfTerm = Calculator.prequentlyOfTerm(lstSentence);
		Map<String, Double> results = new HashMap<String, Double>();

		calEntropyTermInClass(mapPost);

		if (prequentlyOfTerm != null) {
			for (String term : prequentlyOfTerm.keySet()) {
				double sumPositive = 0;
				double sumNegative = 0;
				for (int cls : IntentionUtils.NUM_CLASS) {
					Map<String, Double> prequentlyPositiveOfTermInClass = getResultEntropyTermInClass(true, cls);
					Map<String, Double> prequentlyNegativeOfTermInClass = getResultEntropyTermInClass(false, cls);
					if (!CollectionUtils.isNullOrEmpty(prequentlyPositiveOfTermInClass)
							&& prequentlyPositiveOfTermInClass.containsKey(term)) {
						double positive = prequentlyPositiveOfTermInClass.get(term);
						sumPositive += prequentlyOfTerm.get(term) * positive * Math.log10(positive);
					}

					if (!CollectionUtils.isNullOrEmpty(prequentlyNegativeOfTermInClass)
							&& prequentlyNegativeOfTermInClass.containsKey(term)) {

						double negative = prequentlyNegativeOfTermInClass.get(term);
						sumNegative += (1 - prequentlyOfTerm.get(term)) * negative * Math.log10(negative);
					}
					results.put(term, Calculator.round(sumPositive + sumNegative));
				}
			}
		}

		System.out.println("Entropy of Term: ");

		for (String key : results.keySet()) {
			double prequentCi = results.get(key).doubleValue();
			System.out.println("Key: " + key + " - " + prequentCi);
		}

		return results;
	}

	private static void calEntropyTermInClass(List<Post> mapPost) {
		prequentlyPositiveOfTermInClass0 = Calculator.calEntropyPositiveInClass(mapPost, 0);
		prequentlyNegativeOfTermInClass0 = Calculator.calEntropyNegativeInClass(mapPost, 0);
		prequentlyPositiveOfTermInClass1 = Calculator.calEntropyPositiveInClass(mapPost, 1);
		prequentlyNegativeOfTermInClass1 = Calculator.calEntropyNegativeInClass(mapPost, 1);
	}

	private static Map<String, Double> getResultEntropyTermInClass(boolean isPositive, int cls) {
		if (isPositive) {
			return (cls == 1) ? prequentlyPositiveOfTermInClass1 : prequentlyPositiveOfTermInClass0;
		}
		return (cls == 1) ? prequentlyNegativeOfTermInClass1 : prequentlyNegativeOfTermInClass0;
	}

}
