package com.viettel.hisone.intentionfilter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.viettel.hisone.intentionfilter.model.Post;

/**
 * @author hadtt17
 * @since Sep 29, 2016
 * @modified Sep 29, 2016
 * @by hadtt17
 */

public class FileUtils {

	public static final String FILE_DIC = ".\\data";
	public static final String FILE_DIC_1 = ".\\data1";

	public static String readFile(File file) throws IOException {
		List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.forName("Cp1252"));

		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s + "\n");
		}

		return sb.toString();
	}

	public static Post readObject(File file) throws IOException, ParserConfigurationException {
		try {
			String xml = readFile(file);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(xml)));
			NodeList nodeList = doc.getElementsByTagName("post");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					int id = Integer.parseInt(element.getAttribute("id"));
					int cls = Integer.parseInt(element.getAttribute("class"));
					String title = element.getElementsByTagName("title").item(0).getTextContent();
					String username = element.getElementsByTagName("username").item(0).getTextContent();
					String datetime = element.getElementsByTagName("datetime").item(0).getTextContent();
					String content = StringUtils
							.clean(element.getElementsByTagName("content").item(0).getTextContent());
					Post post = new Post(id, cls, title, username, datetime, content);
					return post;
				}
			}
		} catch (SAXException e) {
			return null;
		}
		return null;
	}

	public static void convertTxtToXml(File file) throws IOException {
		if (FileUtils.getFileExtension(file).equals(".txt")) {
			String xml = readFile(file);
			String fileName = file.getParent().concat("\\")
					.concat(file.getName().substring(0, file.getName().lastIndexOf(".")).concat(".xml"));
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(xml.toLowerCase().replaceAll("[&]", "").replaceAll("<<", "").replaceAll(">>", ""));
			fileWriter.close();
		}
	}

	public static String getFileExtension(File file) {
		return file.getName().substring(file.getName().lastIndexOf('.'), file.getName().length());
	}

}
