package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Main extends Application {

    public Main() {
        File f = new File(data.indexFile);
        if(f.exists()==false)
        {
            createIndexFile();
        }
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("FS Mini Project");
        primaryStage.setScene(new Scene(root, 686.4, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createIndexFile()
    {
        try (RandomAccessFile file = new RandomAccessFile(data.indexFile, "rw"))
        {
            String blockNumber="Block 0";
            blockNumber=String.format("%-10s", blockNumber);
            file.write(blockNumber.getBytes());
            for (int i=0; i<10; i++)
            {
                String locationNumber=String.format("%-10s", i*1000);
                String link=String.format("%-10s", "-1");
                String indexRecord=locationNumber+link;
                int position=i*20+10;
                file.seek(position);
                file.write(indexRecord.getBytes());
            }
            file.close();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, File error!");

            alert.showAndWait();
        }
    }
    public int searchIndex(int id)
    {
        int dataBlock = -1;
        int level1=(id /1000)*1000;
        int indexBlock=0;
        int pointer=getPointer(indexBlock,level1);
        if (pointer<0)
        {
            pointer=createIndexBlock(level1, indexBlock, 2);
        }
        int level2=(id /100)*100;
        indexBlock=pointer;
        pointer=getPointer(indexBlock,level2);
        if (pointer<0)
        {
            pointer=createIndexBlock(level2, indexBlock, 3);
        }
        int level3=(id /10)*10;
        indexBlock=pointer;
        pointer=getPointer(indexBlock,level3);
        if (pointer<0)
        {
            pointer=createDataBlock(level3, indexBlock);
        }
        dataBlock=pointer;

        return dataBlock;
    }

    private int getPointer(int indexBlock, int searchValue)
    {
        int pointer=0;
        byte[] bytes=null;
        try (RandomAccessFile file = new RandomAccessFile(data.indexFile, "r"))
        {
            bytes = new byte[210];
            file.seek(indexBlock*210);
            file.read(bytes);
            file.close();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, File error!");

            alert.showAndWait();
        }
        String s = new String(bytes);
        int location;
        String w,x;
        s=s.substring(10);
        int i=0;
        boolean found=false;
        while(found==false)
        {
            w=s.substring(0,10);s=s.substring(10);
            x=s.substring(0,10);s=s.substring(10);
            location=Integer.parseInt(w.trim());
            pointer=Integer.parseInt(x.trim());
            if (location==searchValue)
            {
                found=true;
            }
            i++;
        }
        return pointer;
    }

    private int createIndexBlock(int base, int previous, int indexLevel)
    {
        int blockCount=0;
        try (RandomAccessFile file = new RandomAccessFile(data.indexFile, "rw"))
        {
            blockCount= (int) (file.length() /210);
            String blockNumber="Block "+blockCount;
            blockNumber=String.format("%-10s", blockNumber);
            int startPosition=blockCount*210;
            file.seek(startPosition);
            file.write(blockNumber.getBytes());
            int multiplier;
            if (indexLevel==2)
            {
                multiplier=100;
            }
            else
            {
                multiplier=10;
            }
            for (int i=0; i<10; i++)
            {
                String locationNumber=String.format("%-10s",base+ i*multiplier);
                String link=String.format("%-10s", "-1");
                String indexRecord=locationNumber+link;
                int position=startPosition + i*20+10;
                file.seek(position);
                file.write(indexRecord.getBytes());

            }
            startPosition=previous*210;
            int row;
            if(indexLevel==2)
            {
                row=base /1000;
            }
            else
            {
                row=base/100;
                row=row % 10;
            }
            String link =Integer.toString(blockCount);
            link=String.format("%-10s", link);
            file.seek(startPosition+row*20 + 20);
            file.write(link.getBytes());

            file.close();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, File error!");

            alert.showAndWait();

        }
        return blockCount;
    }

    public int createDataBlock(int base, int previous)
    {
        int blockCount=-1;

        try (RandomAccessFile file = new RandomAccessFile(data.travelFile, "rw"))
        {
            blockCount= (int) (file.length() /1210);
            String blockNumber="Block "+blockCount;
            blockNumber=String.format("%-10s", blockNumber);
            int startPosition=blockCount*1210;
            file.seek(startPosition);
            file.write(blockNumber.getBytes());
            for (int i=0; i<10; i++)
            {
                String locationNumber=String.format("%-120s",base+ i);
                String TravelRecord=locationNumber;
                int position=startPosition + i*120+10;
                file.seek(position);
                file.write(TravelRecord.getBytes());
            }
            file.close();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, File error!");

            alert.showAndWait();
        }

        try (RandomAccessFile file = new RandomAccessFile(data.indexFile, "rw"))
        {
            int startPosition=previous*210;
            int row;
            row=base/10;
            row=row % 10;
            String link =Integer.toString(blockCount);
            link=String.format("%-10s", link);
            file.seek(startPosition+row*20 + 20);
            file.write(link.getBytes());
            file.close();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, File error!");

            alert.showAndWait();
        }
        return blockCount;

    }

    public void storeRecord(int dataBlock, int holidayID, String holidayRecord)
    {
        try (RandomAccessFile file = new RandomAccessFile(data.travelFile, "rw"))
        {
            int startPosition=dataBlock*1210;
            int location = holidayID %10;
            int fileposition=startPosition + location*120 + 10;
            file.seek(fileposition);
            file.write(holidayRecord.getBytes());
            file.close();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, File error!");

            alert.showAndWait();
        }
    }

}