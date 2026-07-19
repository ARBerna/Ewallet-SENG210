package fileFix;

import java.io.FileWriter;

public class ReportExporter {
	
	public static void exportTextToFile(String text, String filePath) {
		
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
