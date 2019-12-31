package CoverLetter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class CoverLetterGenerator
{
	private static String hiringManager;
	private static String position;
	private static String company;

	public static void createCoverLetter() throws FileNotFoundException, IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Hiring Manager Name: ");
		hiringManager = scanner.nextLine();
		System.out.print("Enter Position: ");
		position = scanner.nextLine();
		System.out.print("Enter new file name: ");
		company = scanner.nextLine();

		XWPFDocument document = new XWPFDocument();
		XWPFDocument docx = new XWPFDocument(new FileInputStream("coverLetter.docx"));

		List<XWPFParagraph> paras = docx.getParagraphs();
		for (XWPFParagraph para : paras)
		{
			if (!para.getParagraphText().isEmpty())
			{
				XWPFParagraph newParagraph = document.createParagraph();
				setHiringManagerAndPosition(para, newParagraph);
			}
		}

		document.write(new FileOutputStream("SimonLim_CoverLetter_" + company + ".docx"));
	}

	private static void setHiringManagerAndPosition(XWPFParagraph oldParagraph, XWPFParagraph newParagraph)
	{
		for (XWPFRun run : oldParagraph.getRuns())
		{
			String textInRun = run.getText(0);
			System.out.println(textInRun);
			if (textInRun == null || textInRun.isEmpty())
			{
				continue;
			}

			if (textInRun.contains("<hiring manager>"))
			{
				textInRun = textInRun.replace("<hiring manager>", hiringManager);
			}

			if (textInRun.contains("<Position>"))
			{
				textInRun = textInRun.replace("<Position>", position);
			}
			newParagraph.setAlignment(oldParagraph.getAlignment());
			XWPFRun newRun = newParagraph.createRun();

			// Copy text
			newRun.setText(textInRun);

			// Apply the same style
			newRun.setFontSize(run.getFontSize());
			newRun.setFontFamily(run.getFontFamily());
			newRun.setBold(run.isBold());
			newRun.setItalic(run.isItalic());
			newRun.setStrike(run.isStrike());
			newRun.setColor(run.getColor());

		}
	}
}
