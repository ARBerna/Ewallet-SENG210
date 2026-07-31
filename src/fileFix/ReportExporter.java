package fileFix;

import java.io.FileWriter;

import javax.swing.JOptionPane;

public class ReportExporter {
	
	public static void exportTextToFile(String text, String filePath) {
		
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(text);
			
			//Add feedback confirmation for saved report
			JOptionPane.showMessageDialog(null, "Report summary exported successfully to:\n" + filePath, "Export Successful", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			
			//Add feedback message for failed save report execution
			JOptionPane.showMessageDialog(null, "Failed to export report summary.", "Export Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
