package com.sample.MavenProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.LinkedList;
// import com.opencsv.CSVWriter;
public class TextFileReader implements Iterable<String> {

    /**
     * The full name of the text file from which the content will be read.
     */
    private final String fileName;

    /**
     * Create a new instance of the text file reader to get the content of the
     * file represented by the specified {@code fileName}.
     *
     * @param fileName      the full path and file name of the file to be
     *                      read. Should not be {@code null}.
     * @throws AssertionError
     *                     if the {@code fileName} is {@code null} and
     *                     the {@literal -ea} flag on the vm has been set.
     */
    public TextFileReader(final String fileName) {
        assert fileName != null;
        this.fileName = fileName;
    }

    /**
     * Create a new iterator to iterate through the contents of the file.
     *
     * @return              an iterator to return the content of the file
     *                      line by line as a String. Never {@code null}.
     */
    public Iterator<String> iterator() {
    	System.out.println("===========");
    	return new TextFileIterator();
    }

    /**
     * An implementation of the Iterator pattern that iterates through the
     * contents of a text file returning each line in the file in turn.
     */
    private final class TextFileIterator implements Iterator<String> {

        /**
         * The input stream used to read the content of the text file.
         */
        final BufferedReader in;

        /**
         * The next line of text to be returned on the next call to {@link #next()}.
         */
        String nextline;

        /**
         * Create a new instance of the iterator class to read the content from
         * the {@link TextFileReader#fileName specified text file}.
         * This constructor is responsible for opening a stream to read the content
         * as well as reading the first line of text from the file.
         *
         * @throws IllegalArgumentException
         *                     if an error occurs opening the input stream or
         *                     reading the first line of text from the file.
         */
        public TextFileIterator() {
            try {
                in = new BufferedReader(new FileReader(fileName));
                nextline = in.readLine(); // We peek ahead like this for the benefit of hasNext( ).
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        /**
         * Check if there is another line of text to be returned from this
         * iterator.
         *
         * @return              {@code true} if there is another line to
         *                      be returned, {@code false} otherwise.
         */
        public boolean hasNext() {
            return nextline != null;
        }

        /**
         * Return the next line of text from the iterator. This method will
         * first read the line of text following the next line of text to be
         * returned. If no line follows the next line to be returned, this
         * method will close the input stream being used to read text.
         *
         * @throws IllegalArgumentException
         *                     if an error occurs trying to read from the
         *                     file or attempting to close the file after
         *                     all lines of text have been read.
         */
        public String next() {
            try {
                String result = nextline;
                // If we haven't reached EOF yet
                if (nextline != null) {
                    nextline = in.readLine();   // Read another line
                    if (nextline == null) {
                        in.close();     // And close on EOF
                    }
                }
                // Return the line we read last time through.
                return result;

            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

    }

    public static int countOccurrences(String st, String word){
        String temp[] = st.split(" ");
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (word.equals(temp[i]))
                count++;
        }
        return count;
    }
    // Function to print Strings present between any pair of delimiters
    static LinkedList<String> printSubsInDelimiters(String str){
        // Stores the indices of
        Stack<Integer> dels = new Stack<Integer>();
        LinkedList<String> records = new LinkedList<String>();

        for(int i = 0; i < str.length(); i++){
            // If opening delimiter
            // is encountered
            if (str.charAt(i) == '['){
                dels.add(i);
            }
            // If closing delimiter
            // is encountered
            else if (str.charAt(i) == ']' &&
                    !dels.isEmpty()){
                // Extract the position
                // of opening delimiter
                int pos = dels.peek();
                dels.pop();
                // Length of subString
                int len = i - 1 - pos;
                // Extract the subString
                String ans = str.substring(pos + 1, pos + 1 + len);
                if (countOccurrences(ans, "record") == 1 && countOccurrences(ans, "[record") == 0) {
                    String wordToSkip = "record ";
                    records.add(ans.substring(ans.indexOf(wordToSkip) + wordToSkip.length(),ans.length()));
                }
            }
        }
        return records;
    }


    // TODO : Write to file
    public static  Map<String, String> getWordMap(String data){
        String[] words = data.split(" ");
        Map<String, String> wordMap = new HashMap<String , String>();
        for (int i=0; i< words.length; i=i+2){
            wordMap.put(words[i], words[i+1]);
        }
        return wordMap;
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        final String fileName = (args.length > 0) ? args[0] : "./TextFileReader.java";
        LinkedList<String> strGroups = new LinkedList<String>();

        String before = "";
        for (final String line : new TextFileReader(fileName)) {
            // entireFile += line.replace("\n", "");
            // System.out.println(line);
            before += line;
        }
        String after = before.trim().replaceAll(" +", " "); // replaceAll("\\s{2,}", " ").trim();
        // Create groups of string
        Pattern p = Pattern.compile(Pattern.quote("SZ") + "(.*?)" + Pattern.quote("]]"));
        Matcher m = p.matcher(after);
        while (m.find()) {
            strGroups.add(m.group(1));
        }
        XSSFWorkbook workbook = new XSSFWorkbook();  
        
        for (int i = 0; i < strGroups.size(); i++) {
            // List<String[]> wordsarr = new ArrayList<>();
            LinkedList<String> tempstr = new LinkedList<String>();
            // System.out.println("GROUP : " + strGroups.get(i));
            System.out.println("GROUP : STARTS");
            // Open csv file to write
            tempstr = printSubsInDelimiters(strGroups.get(i));
            
       	 	//open a new sheet with SZ+i as name of the sheet.
            XSSFSheet sheet = workbook.createSheet("SZ_"+i);
            int row = 0;
            
            //opn csv file writer here
            PrintWriter pw = null;
            StringBuilder builder = new StringBuilder();
            try {
                pw = new PrintWriter(new File("key-value"+i+".csv"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            for (int j = 0; j < tempstr.size(); j++) {
                for (Map.Entry<String,String> entry : getWordMap(tempstr.get(j)).entrySet()){
                    // Write this Map to the file
                    System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
                    
                    //write to csv;
                    builder.append(entry.getKey()+" ");
                    builder.append(entry.getValue()+" ");
                   // builder.append('\n');                  
                    builder.append(", "); 
                    //writting to excel
                    int columnCount=0;
                    Row rRow = sheet.createRow(++row);
                    Cell cell = rRow.createCell(columnCount++);
                    cell.setCellValue(entry.getKey());
                    rRow.createCell(columnCount++).setCellValue(entry.getValue());
                }
            } 
            pw.write(builder.toString());
            pw.close();
        }
        try (FileOutputStream outputStream = new FileOutputStream("key-value.xlsx")) {
            workbook.write(outputStream);
        }
        // Close csv file
    }
}