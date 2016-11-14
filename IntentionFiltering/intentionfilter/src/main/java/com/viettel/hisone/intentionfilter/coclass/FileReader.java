package com.viettel.hisone.intentionfilter.coclass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.viettel.hisone.intentionfilter.model.Post;
import com.viettel.hisone.intentionfilter.utils.FileUtils;

/**
 * @author hadtt17
 * @since Sep 7, 2016
 * @modified Sep 7, 2016
 * @by hadtt17
 */

public class FileReader {

	public static final String FILE_DIC = ".\\data";
	public static int count = 0;
	private static FileReader reader;
	private List<Post> listPost = new ArrayList<Post>();

	public static FileReader getInstance() {
		reader = new FileReader();
		return reader;
	}

	public FileReader retrievePost(File file) throws Exception {
		for (File f : file.listFiles()) {
			if (f != null && f.isDirectory()) {
				retrievePost(f);
			} else {
				if (FileUtils.getFileExtension(f).equals(".xml")) {
					Post post = FileUtils.readObject(f);
					if (post != null)
						listPost.add(FileUtils.readObject(f));
				}
			}
		}

		return reader;
	}

	public List<Post> getPost() {
		return listPost;
	}
}
