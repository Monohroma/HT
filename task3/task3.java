import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

public class task3 {

    public static HashMap<String, String> GetHashMapFromValues(String value)
    {
        HashMap<String, String> rez = new HashMap<>();
        boolean nextisvalue = false;
        boolean writingvalue = false;
        boolean ignoresymbol = false;
        StringBuilder key= new StringBuilder();
        StringBuilder val= new StringBuilder();
        int l = 0;
        char c;
        for(int i=0;i<value.length();i++)
        {
            c = value.charAt(i);
            if(ignoresymbol) {
                if(writingvalue)
                {
                    if(l==0)
                        key.append(c);
                    else
                        val.append(c);
                }
                ignoresymbol = false;
                continue;
            }
            if(c == '{' || c == '}') {
                if(c=='}')
                {
                    if((!key.isEmpty()) && (!val.isEmpty()))
                    {
                        rez.put(key.toString(), val.toString());
                    }
                }
                key = new StringBuilder();
                val = new StringBuilder();
                l = 0;
                writingvalue = false;
                ignoresymbol = false;
                nextisvalue = false;
                continue;
            }
            if(c == ',') {
                l=1;
                writingvalue = false;
                nextisvalue = false;
                continue;
            }
            if(c == ':')
            {
                nextisvalue = true;
                continue;
            }
            if(c == '"')
            {
                if(writingvalue)
                {
                    writingvalue = false;
                    continue;
                }
                if(nextisvalue)
                {
                    nextisvalue = false;
                    writingvalue = true;
                    continue;
                }
                continue;
            }
            if(nextisvalue)
            {
                if(c==' ')
                    continue;
                writingvalue = true;

            }
            if(writingvalue)
            {
                if(c=='\\') {
                    ignoresymbol = true;
                    continue;
                }
                if(l==0)
                    key.append(c);
                else
                    val.append(c);
            }
        }
        return rez;
    }

    public static String PastValues(String value, HashMap<String,String> pastIds)
    {
        boolean nextisvalue = false;
        boolean writingvalue = false;
        StringBuilder key= new StringBuilder();
        StringBuilder rez = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String id = "";
        int l = 0;
        char c;
        for(int i=0;i<value.length();i++)
        {
            c = value.charAt(i);
            rez.append(c);
            if(c == '{' || c == '}') {
                key = new StringBuilder();
                val = new StringBuilder();
                writingvalue = false;
                nextisvalue = false;
                l = 0;
                continue;
            }
            if(c == ',') {
                writingvalue = false;
                nextisvalue = false;
                if(key.toString().equals("id"))
                    id = val.toString();
                key = new StringBuilder();
                val = new StringBuilder();
                l = 0;
                continue;
            }
            if(c == ':')
            {
                nextisvalue = true;
                l = 1;
                continue;
            }
            if(c == '"')
            {
                if(writingvalue)
                {
                    writingvalue = false;
                    continue;
                }
                else {
                    writingvalue = true;
                    if (nextisvalue) {
                        nextisvalue = false;
                        l = 1;
                        if(key.toString().equals("value"))
                            rez.append(pastIds.get(id));
                        continue;
                    }
                }
                continue;
            }
            if(nextisvalue)
            {
                if(c==' ')
                    continue;
                writingvalue = true;

            }
            if(writingvalue)
            {
                if(l==0)
                    key.append(c);
                else
                    val.append(c);
            }
        }
        return rez.toString();
    }

    public static void main(String[] args) {
        File test = new File("tests.json");
        Scanner test_scan;
        File values = new File("values.json");
        Scanner values_scan;
        File report = new File("report.json");

        try {
            test_scan = new Scanner(test);
            values_scan = new Scanner(values);
            report.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder test_text = new StringBuilder();
        while (test_scan.hasNextLine()) {
            if(!test_text.toString().isEmpty())
                test_text.append("\n");
            test_text.append(test_scan.nextLine());
        }

        StringBuilder values_text = new StringBuilder();
        while (values_scan.hasNextLine()) {
            if(!values_text.isEmpty())
                values_text.append("\n");
            values_text.append(values_scan.nextLine());
        }

        HashMap<String, String> values_dict = GetHashMapFromValues(values_text.toString());
        String rez = PastValues(test_text.toString(), values_dict);
        try {
            FileWriter fw = new FileWriter(report);
            fw.write(rez);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}