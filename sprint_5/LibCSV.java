

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Reads and writes data from the csv file.
 * @author Arola Sébastien, Palhière Aymeric, Retornard Vianney, Vermande Mathieu, Vidal Ghislain
 *
 */
public class LibCSV {
	/**
	 * Reads a csv file and saves it as an ArrayList<String[]>.
	 * @param fileName
	 * @return ArrayList<String[]>
	 */
	public ArrayList<String[]> Read(String fileName){
		String content = ""; 
		String tabTemp[];
		ArrayList<String> temp= new ArrayList<String>();
		ArrayList<String[]> DataTab= new ArrayList<String[]>();
		FileInputStream fin= null;
		try{
			fin= new FileInputStream(new File(fileName));
			byte[] reader= new byte[8];
			while((fin.read(reader))>=0){ //While theirs something to read
				for(byte bit : reader){
					
						if( (char) bit != ';'&& (char) bit != '\n'){
							content += (char) bit;// We save the current letter in the string
						}
						else {
							temp.add(content);//We add the word in the array
							content= "";
							if((char) bit == '\n'){
								tabTemp= new String[temp.size()];// When we read all the line we save it in the data array
								temp.toArray(tabTemp);
								DataTab.add(tabTemp);
								temp = new ArrayList<String>();
							}
						}
					
				}
				
				reader= new byte[8];
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
	  } catch (IOException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (fin != null)
	               fin.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
		
		return DataTab;
	}
	/**
	 * Takes the data from an ArrayList<String[]> and saves it as a csv file.
	 * @param data
	 * @param fileName
	 */
	public void Write(ArrayList<String[]> data, String fileName){
		FileOutputStream fon= null;
		String actualData = "";
		try{
			fon= new FileOutputStream(new File(fileName));
			for (int i=0; i< data.size();i++){
				for(int j=0; j< data.get(i).length;j++){
					if (j!=data.get(i).length-1){
						actualData+=data.get(i)[j]+";"; //We write a word
					}
					else
					{
						actualData+=data.get(i)[j];// Write the last word
					}	
				}
				actualData+="\n";//Add a back space to change the line
			}
			fon.write(actualData.getBytes());	
		}catch (FileNotFoundException e) {
			e.printStackTrace();
	  } catch (IOException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (fon != null)
	               fon.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }	
	}
}
