package stepdefinitions;

import com.opencsv.CSVWriter;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;

import java.io.*;

public class DataTransformStepDefinitions {
    @Given("I read data from input files and create output file")
    public void i_read_data_from_input_files_and_create_output_file() throws IOException {
         String InputFilepath1 =".//InstrumentDetails.csv";
        String InputFilepath2 =".//PositionDetails.csv";
        String line,name;
        String id = null,isin=null,unitPrice=null,instrumentId=null,quantity=null;
        File file1 = new File(InputFilepath1);

        BufferedReader bufRdr;
        bufRdr = new BufferedReader(new FileReader(file1));


        while((line = bufRdr.readLine()) != null){
            String[] cell= line.split(",");
             id=cell[0];
             name=cell[1];
             isin=cell[2];
             unitPrice=cell[3];
        }
        File file2 = new File(InputFilepath2);
        bufRdr = new BufferedReader(new FileReader(file2));
        while((line = bufRdr.readLine()) != null){
            String[] cell= line.split(",");
             instrumentId=cell[0];
             quantity=cell[1];
        }

        CSVWriter writer = new CSVWriter(new FileWriter(".//PositionReport.csv"));
        //Writing data to a csv file
        String line1[] = {"ID", "PositionID", "ISIN", "Quantity", "Total Price"};
        int totalPrice =Integer.parseInt(quantity) * Integer.parseInt(unitPrice);
        String line2[] = {id, id, isin, quantity, String.valueOf(totalPrice)};

        //Writing data to the csv file
        writer.writeNext(line1);
        writer.writeNext(line2);

        //Flushing data from writer to file
        writer.flush();
    }
}
