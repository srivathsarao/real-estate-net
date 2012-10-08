package com.vertexnet.realestate.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class DocumentUtility {
	
	private Document document;
	private File file;
	
	public DocumentUtility() {
		try {
			file = getRandomFile();
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void setInputStream(String text) {
		try {
			List<Element> objects;
			objects = HTMLWorker.parseToList(new StringReader(text), null);
			for (Element element : objects) {
				document.add(element);
			}
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void openPDF() {
		try {
			Desktop dt = Desktop.getDesktop();
			dt.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private File getRandomFile() {
		Integer randomNumber = getRandomInteger(10000, 30000, new Random());
		String tempDir = System.getProperty("java.io.tmpdir");
		
		File folder = new File(tempDir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String filename = listOfFiles[i].getName();
				if(filename.lastIndexOf('.') == -1) {
					continue;
				}
				String fileExtension = filename.substring(filename.lastIndexOf('.') + 1);
				if(filename.contains("realEstate-") && !fileExtension.isEmpty() && fileExtension.equalsIgnoreCase("PDF"))
					try {
					listOfFiles[i].delete();
					} catch(Exception e) {}
				}
		}
	
		File file = new File(tempDir + File.separator + "realEstate-" + randomNumber + ".pdf");
		return file;
	}

	private int getRandomInteger(int aStart, int aEnd, Random aRandom) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}
}
