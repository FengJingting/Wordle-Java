package comp1721.cwk1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordList {
    private String List;
    // TODO: Implement constructor with a String parameter
    public WordList(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        List = stringBuilder.toString();

    };

    // TODO: Implement size() method, returning an int
    public int size(){
        return List.length()/7;
    };

    // TODO: Implement getWord() with an int parameter, returning a String
    public String getWord(int n){
        return List.substring(7*n,7*n+7);
    };
}
