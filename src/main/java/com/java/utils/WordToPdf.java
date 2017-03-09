package com.java.utils;

import org.pdfbox.cos.COSArray;
import org.pdfbox.cos.COSString;
import org.pdfbox.exceptions.COSVisitorException;
import org.pdfbox.pdfparser.PDFStreamParser;
import org.pdfbox.pdfwriter.ContentStreamWriter;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.pdmodel.common.PDStream;
import org.pdfbox.pdmodel.edit.PDPageContentStream;
import org.pdfbox.pdmodel.font.PDFont;
import org.pdfbox.pdmodel.font.PDType1Font;
import org.pdfbox.util.PDFOperator;
import org.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * http://pdfbox.apache.org/ 
 *
 * @author fish
 *
 */
public class WordToPdf {

    public WordToPdf()
    {
        createHelloPDF();
        readPDF();
//        editPDF();
    }

    public void createHelloPDF() {
        PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();

            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            content.drawString("Hello,world");

            content.endText();
            content.close();
            doc.save("D:\\home\\pdfwithText.pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readPDF()
    {
        PDDocument helloDocument;
        try {
            helloDocument = PDDocument.load(new File(
                    "D:\\1n4148.pdf"));
            PDFTextStripper textStripper = new PDFTextStripper();
            System.out.println(textStripper.getText(helloDocument));
            String text=textStripper.getText(helloDocument);
            System.out.println(GoogleTranslate.translate(text));
            helloDocument.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
    }

    public void editPDF() {

        try {
            // pdfwithText  
            PDDocument helloDocument = PDDocument.load(new File("D:\\1n4148.pdf"));
            // PDDocument helloDocument = PDDocument.load(new File("D:\\gloomyfish\\hello.pdf"));  
            // int pageCount = helloDocument.getNumberOfPages();  
            PDPage firstPage = (PDPage)helloDocument.getDocumentCatalog().getAllPages().get(0);
            // PDPageContentStream content = new PDPageContentStream(helloDocument, firstPage);  
            PDStream contents = firstPage.getContents();

            PDFStreamParser parser = new PDFStreamParser(contents.getStream());
            parser.parse();
            List tokens = parser.getTokens();
            for (int j = 0; j < tokens.size(); j++)
            {
                Object next = tokens.get(j);
                if (next instanceof PDFOperator)
                {
                    PDFOperator op = (PDFOperator) next;
                    // Tj and TJ are the two operators that display strings in a PDF    
                    if (op.getOperation().equals("Tj"))
                    {
                        // Tj takes one operator and that is the string    
                        // to display so lets update that operator    
                        COSString previous = (COSString) tokens.get(j - 1);
                        String string = previous.getString();
                        System.out.println(string);
                        string = string.replaceFirst("Hello", "Hello World, fish");
                        //Word you want to change. Currently this code changes word "Solr" to "Solr123"    
                        previous.reset();
                        previous.append(string.getBytes("ISO-8859-1"));
                    }
                    else if (op.getOperation().equals("TJ"))
                    {
                        COSArray previous = (COSArray) tokens.get(j - 1);
                        for (int k = 0; k < previous.size(); k++)
                        {
                            Object arrElement = previous.getObject(k);
                            if (arrElement instanceof COSString)
                            {
                                COSString cosString = (COSString) arrElement;
                                String string = cosString.getString();
                                System.out.println(string);
                                string = string.replaceFirst("Hello", "Hello World, fish");

                                // Currently this code changes word "Solr" to "Solr123"    
                                cosString.reset();
                                cosString.append(string.getBytes("ISO-8859-1"));
                            }
                        }
                    }
                }
            }
            // now that the tokens are updated we will replace the page content stream.    
            PDStream updatedStream = new PDStream(helloDocument);
            OutputStream out = updatedStream.createOutputStream();
            ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
            tokenWriter.writeTokens(tokens);
            firstPage.setContents(updatedStream);
            helloDocument.save("D:\\home\\helloworld.pdf"); //Output file name
            helloDocument.close();
//          PDFTextStripper textStripper = new PDFTextStripper();  
//          System.out.println(textStripper.getText(helloDocument));  
//          helloDocument.close();  
        } catch (IOException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        } catch (COSVisitorException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WordToPdf();
    }
}  