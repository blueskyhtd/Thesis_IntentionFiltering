package com.viettel.hisone.intentionfilter.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author hadtt17
 * @since Sep 5, 2016
 * @modified Sep 5, 2016
 * @by hadtt17
 */

public class CollectionUtils {
	public static boolean isNullOrEmpty(Collection<?> objects) {
		if (objects == null || objects.isEmpty()) {
			return true;
		}

		return false;
	}

	public static boolean isNullOrEmpty(Map<?, ?> objects) {
		if (objects == null || objects.isEmpty()) {
			return true;
		}

		return false;
	}
}
