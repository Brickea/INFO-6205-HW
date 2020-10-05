package edu.neu.coe.info6205.util;

import java.io.*;
import java.util.List;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description: Export CSV file
 **/

public class CSVExport {


    /**
     * @author: Zixiao Wang
     * @date: 9/20/2020
     * @param: head
     * @param: dataList
     * @param: outputPath
     * @param: filename
     * @return: java.io.File
     * @description: Create the file object for writing the data
     **/
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList, String outputPath, String filename) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outputPath + File.separator + filename + ".csv");

            // if parent filepath exist or not
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312 use split character ","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // write head
            writeRow(head, csvWtriter);

            // write data
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * @author: Zixiao Wang
     * @date: 9/20/2020
     * @param: row
     * @param: csvWriter
     * @return: void
     * @description: Write all data into file
     **/
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // write head
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

}