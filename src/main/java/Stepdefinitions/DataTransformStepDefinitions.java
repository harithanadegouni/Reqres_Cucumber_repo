package Stepdefinitions;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import io.cucumber.java.en.Given;
import java.io.*;
import java.util.List;

public class DataTransformStepDefinitions {
    @Given("I read data from input files and create output file")
    public void i_read_data_from_input_files_and_create_output_file() throws IOException, CsvException {
        String InputFilepath1 =".//InstrumentDetails.csv";
        String InputFilepath2 =".//PositionDetails.csv";
        //create two empty string array for output file
        String reportFile_line1[] = new String[5];
        String reportFile_line2[] = new String[5];
        reportFile_line1[0] = "PR01";
        reportFile_line2[0] = "PR02";

        //Read all lines from input file1
        CSVReader reader = new CSVReader(new FileReader(InputFilepath1));
        List<String[]> allRows = reader.readAll();
        int inputFileRowsCount=allRows.size();

        if(inputFileRowsCount!=0) {
            //read data from input file1 and write into output file
            int k = 0;
            //Read CSV line by line and use the string array as you want
            for (String[] row : allRows) {
                if (k == 1) {
                    reportFile_line1[2] = row[2];
                    reportFile_line1[4] = row[3];
                }
                if (k == 2) {
                    reportFile_line2[2] = row[2];
                    reportFile_line2[4] = row[3];
                }
                k++;
            }
        }

        CSVReader reader_file2 = new CSVReader(new FileReader(InputFilepath2));
        //Read all lines from input file2
        List<String[]> allRows_PositionTable = reader_file2.readAll();
        int allRows_PositionTableRowsCount=allRows_PositionTable.size();
        int count=0;

        if(allRows_PositionTableRowsCount!=0) {
            //read data from input file2 and write into output file
            for (String[] row : allRows_PositionTable) {
                if (count == 1) {
                    reportFile_line1[1] = row[0];
                    reportFile_line1[3] = row[2];
                }
                if (count == 2) {
                    reportFile_line2[1] = row[0];
                    reportFile_line2[3] = row[2];
                }
                count++;
            }
        }
        CSVWriter writer = new CSVWriter(new FileWriter(".//PositionReport.csv"));
        //Writing data to a csv file
        String line1[] = {"ID", "PositionID", "ISIN", "Quantity", "Total Price(quantity*unit price)"};

        //Writing data to the csv file
        writer.writeNext(line1);
        String R1C3=reportFile_line1[3];
        String R1C4=reportFile_line1[4];

        int totalPriceRow1=Integer.parseInt(R1C3)*Integer.parseInt(R1C4);
        reportFile_line1[4]=String.valueOf(totalPriceRow1);


        String R2C3=reportFile_line2[3];
        String R2C4=reportFile_line2[4];

        int totalPriceRow2=Integer.parseInt(R2C3)*Integer.parseInt(R2C4);
        reportFile_line2[4]=String.valueOf(totalPriceRow2);

        writer.writeNext(reportFile_line1);
        writer.writeNext(reportFile_line2);

        //Flushing data from writer to file
        writer.flush();
    }
}
