package a3parser;

import java.io.File;
import java.io.IOException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;

public class Extractor {
	
	public static void main(String[] args) {
		File data = new File("data/output.xml");
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		
		try {
			cleaner.clean(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		Parser parser = new Parser();
//		parser.readXML(data);
	}
}
