package com.example.studentmangement.service;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public interface PDFGeneratorService {

    public byte[] exportToPdf(String name, List<String> data, String fileName) throws FileNotFoundException, DocumentException;
}
