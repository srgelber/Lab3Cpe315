import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

//Simon Gelber: CPE315(SENG)
public class lab3 {

        public static void main(String[] args){
            Dictionary labelDict = new Hashtable();
            try{
                    Scanner scanner = new Scanner(new File(args[0]));
                    int count = 0;

                    //first pass to get label addresses
                    while(scanner.hasNextLine()){
                        boolean flag = false;
                        for (String x : splitLine(scanner.nextLine())){
                            if (x.contains(":")){
                                labelDict.put(x.substring(0,x.length()-1), count);
                            }
                            if(isInstruction(x)){
                                flag = true;
                            }
                        }
                        if(flag == true)
                        count += 1;
                    }
                    scanner.close();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }
            //second pass
            try{
                    Scanner scanner = new Scanner(new File(args[0]));
                    int lineCount = 0;
                    ArrayList<ArrayList<String>> instructionArray = new ArrayList<ArrayList<String>>();
                    while(scanner.hasNextLine()){
                        String line0 = scanner.nextLine().replaceAll(":(?!$)", ": ");
                        line0 = line0.replaceAll("#(?!$)", " #");
                        ArrayList<String> lineFinal = getInstruction(line0, labelDict, lineCount);
                        if(!lineFinal.isEmpty()){
                        	instructionArray.add(lineFinal);
                        }
                        //System.out.println(lineFinal);
                        if(!lineFinal.equals("")){
                            
                            lineCount += 1;
                    }
                    }
                    System.out.print(instructionArray);
                    
                    scanner.close();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        //function which processes each line
        public static ArrayList<String> getInstruction(String line, Dictionary labelDict, int lineCount){
            short fixedImm = 0000000000000000;
            String output = "";
            ArrayList<String> outputList = new ArrayList<String>();
            
            try{
            if(isBlankLine(line)){
                return outputList;
            }
            
            String[] tokenList = line.split("\\s+|,|:|\\$|\\(|\\)");
            ArrayList<String> list = new ArrayList<String>();
            for (String s : tokenList){
                if (!s.equals("")){
                    list.add(s);
                }
            }

            tokenList = list.toArray(new String[list.size()]);
            for (int x = 0; x < tokenList.length; x++){
                //do checks to decide what to do with each token
                //check if label 
                if(labelDict.get(tokenList[x]) != null){
                    continue;
                }
                //check if register
                else if(getRegister(tokenList[x]) != null){
                    continue;
                }
                //check if comment
                else if(tokenList[x].contains("#")){
                    return outputList;
                }
                else if(isInstruction2(tokenList[x])){
                    //check to see what instruction it is, error check, and append the opcode into the output string
                    if(tokenList[x].equals("add")){
                        //check that we have three valid registers after the add
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && getRegister(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("and")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && getRegister(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("or")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && getRegister(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("addi")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && isImmediate(tokenList[x+3]) != false){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("sll")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && isImmediate(tokenList[x+3]) != false){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("sub")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && getRegister(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("slt")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && getRegister(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("beq")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && labelDict.get(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("bne")){
                        if(getRegister(tokenList[x+1]) != null && getRegister(tokenList[x+2]) != null && labelDict.get(tokenList[x+3]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("lw")){
                        if(getRegister(tokenList[x+3]) != null && getRegister(tokenList[x+1]) != null && isImmediate(tokenList[x+2]) != false){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("sw")){
                        if(getRegister(tokenList[x+3]) != null && getRegister(tokenList[x+1]) != null && isImmediate(tokenList[x+2]) != false){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            outputList.add(tokenList[x+2]);
                            outputList.add(tokenList[x+3]);
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("j")){
                        if(labelDict.get(tokenList[x+1]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                    
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("jr")){
                        if(getRegister(tokenList[x+1]) != null){
                            outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }
                    else if(tokenList[x].equals("jal")){
                        if(labelDict.get(tokenList[x+1]) != null) {
                           outputList.add(tokenList[x]);
                            outputList.add(tokenList[x+1]);
                            
                            return outputList;
                        }else{
                            System.out.println("invalid syntax for instruction: " + tokenList[x]);
                            System.exit(0);
                        }
                    }

                }
                
                //since registers are handled above we just skip them when iterated
                return outputList;
              }

               
            }catch(Exception e2){
                e2.printStackTrace();
            }
            //return what should be the final line in binary
            return outputList;
        }
        //checks for blank lines
        public static boolean isBlankLine(String string){
            return string == null || string.trim().isEmpty();
        }
        //has functionality to exit invalid codeelse{

        public static boolean isInstruction2(String string){
            List<String> validInstructions = Arrays.asList("add","or","and","addi","sll","slt","beq","bne","lw","sw","j","jr","jal","sub");
            if(validInstructions.contains(string)){
                return true;
            }else{
                System.out.println("invalid instruction: " + string);
                System.exit(0);
                return false;
            }

        }

        public static boolean isInstruction(String string){
            List<String> validInstructions = Arrays.asList("add","or","and","addi","sll","slt","beq","bne","lw","sw","j","jr","jal","sub");
            if(validInstructions.contains(string)){
                return true;
            }else{
                return false;
            }

        }


        public static String getRegister(String reg) throws Exception{
            if(reg.equals("zero") || reg.equals("0")){
                return "00000";
            }
            else if (reg.equals("v0")){
                return"00010";
            }
            else if (reg.equals("v1")){
                return"00011";
            }
            else if (reg.equals("a0")){
                return"00100";
            }
            else if (reg.equals("a1")){
                return"00101";
            }
            else if (reg.equals("a2")){
                return"00110";
            }
            else if (reg.equals("a3")){
                return"00111";
            }
            else if (reg.equals("t0")){
                return"01000";
            }
            else if (reg.equals("t1")){
                return"01001";
            }
            else if (reg.equals("t2")){
                return"01010";
            }
            else if (reg.equals("t3")){
                return"01011";
            }
            else if (reg.equals("t4")){
                return"01100";
            }
            else if (reg.equals("t5")){
                return"01101";
            }
            else if (reg.equals("t6")){
                return"01110";
            }
            else if (reg.equals("t7")){
                return"01111";
            }
            else if (reg.equals("s0")){
                return"10000";
            }
            else if (reg.equals("s1")){
                return"10001";
            }
            else if (reg.equals("s2")){
                return"10010";
            }
            else if (reg.equals("s3")){
                return"10011";
            }
            else if (reg.equals("s4")){
                return"10100";
            }
            else if (reg.equals("s5")){
                return"10101";
            }
            else if (reg.equals("s6")){
                return"10110";
            }
            else if (reg.equals("s7")){
                return"10111";
            }
            else if (reg.equals("t8")){
                return"11000";
            }
            else if (reg.equals("t9")){
                return"11001";
            }
            else if (reg.equals("sp")){
                return"11101";
            }
            else if (reg.equals("ra")){
                return"11111";
            }
            else{
                return null;
            }
        }

        public static boolean isImmediate(String string){
            if(string == null){
                return false;
            }
            try{
                double d = Double.parseDouble(string);
            } catch(NumberFormatException nfe){
                return false;
            }
            return true;
        }

        public static String[] splitLine(String line){
            line = line.replaceAll(":(?!$)", ": ");
            line = line.replaceAll("#(?!$)", " #");
            String[] tokenList = line.split("\\s+|,|\\$|\\(|\\)");
            return tokenList;
        }

        public static String decimaltoImm(String imm){
        String bits = "0000000000000000";
        String immValue = Integer.toBinaryString(Integer.parseInt(imm));
        String[] immBits = immValue.split("");
        if (Integer.parseInt(imm) < 0) {
            return immValue.substring(16,32);
        }
        else {
            for (int i = 0;i <immBits.length; i++){
                if (immBits[i].equals("1")){

                    bits = bits.substring(0,15) + "1";
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }else {
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }
            }
        }
        return bits;
    }
    public static String decimaltoSham(String imm){
        String bits = "00000";
        String immValue = Integer.toBinaryString(Integer.parseInt(imm));
        String[] immBits = immValue.split("");
        if (Integer.parseInt(imm) < 0) {
            return immValue;
        }
        else {
            for (int i = 0;i <immBits.length; i++){
                if (immBits[i].equals("1")){

                    bits = bits.substring(0,4) + "1";
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }else {
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }
            }
        }
        return bits;
    }

    public static String decimaltoAddress(int addr){
        String bits = "00000000000000000000000000";
        String immValue = Integer.toBinaryString(addr);
        String[] immBits = immValue.split("");
        if (addr < 0) {
            return immValue.substring(6,32);
        }
        else {
            for (int i = 0;i <immBits.length; i++){
                if (immBits[i].equals("1")){

                    bits = bits.substring(0,25) + "1";
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }else {
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }
            }
        }
        return bits;
    }

    public static String branchAddress(int addr){
        String bits = "0000000000000000";
        String immValue = Integer.toBinaryString(addr);
        String[] immBits = immValue.split("");
        if (addr < 0) {
            return immValue.substring(16,32);
        }
        else {
            for (int i = 0;i <immBits.length; i++){
                if (immBits[i].equals("1")){

                    bits = bits.substring(0,25) + "1";
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }else {
                    if(i!= (immBits.length-1)) {
                        bits = bits.substring(1) + "0";
                    }
                }
            }
        }
        return bits;
    }


}
