
package com.viettel.hisone.intentionfilter.coclass;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.viettel.hisone.intentionfilter.model.Post;
import com.viettel.hisone.intentionfilter.utils.FileUtils;

/**
 * @author hadtt17
 * @since Sep 7, 2016
 * @modified Sep 7, 2016
 * @by hadtt17
 */

public class CoClassAlgorithm {
	public static void main(String[] args) throws Exception {
		List<Post> listPost = FileReader.getInstance().retrievePost(new File(FileUtils.FILE_DIC)).getPost();

		// List<Post> listPost = new ArrayList<Post>();
		// listPost.add(new Post(1, "want buy house"));
		// listPost.add(new Post(1, "where find house"));
		// listPost.add(new Post(1, "buy phone help"));
		// listPost.add(new Post(0, "feel bad house"));
		// listPost.add(new Post(0, "love book"));
		// listPost.add(new Post(0, "sell tv who want"));

		Map<String, Double> prequentlyOfClass = InformationGain.calculateIG(listPost);
	}
}
