package com.example.studentmangement.service;

import java.io.ByteArrayInputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class PDFGeneratorServiceImpl implements PDFGeneratorService {
    @Override
    public byte[] exportToPdf(String name, List<String> data, String fileName) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        // Add a title (filename) to the PDF
        Paragraph title = new Paragraph("Course Schedule for: " + name, FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
        document.add(title);
        document.add(Chunk.NEWLINE);

        for (int i = 0; i < data.size(); i++ ) {
            Chunk chunk = new Chunk(data.get(i), font);
            document.add(new Paragraph(chunk));
        }

        document.close();

        return baos.toByteArray();
    }
}
