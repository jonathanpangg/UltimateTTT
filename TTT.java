import java.util.*;
public class TTT{
    static inProgress prog = inProgress.ON;
    private static String [][] arr1 = new String [3][3];
    private static String [][] arr2 = new String [3][3];
    private static String [][] arr3 = new String [3][3];
    private static String [][] arr4 = new String [3][3];
    private static String [][] arr5 = new String [3][3];
    private static String [][] arr6 = new String [3][3];
    private static String [][] arr7 = new String [3][3];
    private static String [][] arr8 = new String [3][3];
    private static String [][] arr9 = new String [3][3];
    private static status a1 = status.ON;
    private static status a2 = status.ON;
    private static status a3 = status.ON;
    private static status a4 = status.ON;
    private static status a5 = status.ON;
    private static status a6 = status.ON;
    private static status a7 = status.ON;
    private static status a8 = status.ON;
    private static status a9 = status.ON;
    private static Control c1 = null;
    private static Control c2 = null;
    private static Control c3 = null;
    private static Control c4 = null;
    private static Control c5 = null;
    private static Control c6 = null;
    private static Control c7 = null;
    private static Control c8 = null;
    private static Control c9 = null;
    private static choice y = choice.N;
    enum inProgress{
        ON, OFF;
    }
    enum status{
        ON, OFF;
    }
    enum turn{
        X, O;
    }
    enum Control{
        X, O;
    }
    enum choice{
        Y, N;
    }
    public static void main(String [] args){
        turn t = turn.X;
        clear();
        printBoard();
        int row = getFirstRows(t);
        int col = getFirstColumns(t);
        int tempr = toFit(row);
        int tempc = toFit(col);
        int firstregion = findRegion(tempr, tempc);
        row = toAdjust(row);
        col = toAdjust(col);
        modBoard(firstregion, row, col, t);
        printBoard();
        int x = 0;
        int starting = firstregion;
        while(prog==inProgress.ON){
            if(x%2==0)
                t = turn.O;
            else
                t = turn.X;
            if(y==choice.N){
                starting = findNewRegion(row, col);
                row = toAdjust(getGeneralRows(t));
                col = toAdjust(getGeneralCols(t));
                if(isValid(starting, row, col)){
                    modBoard(starting, row, col, t);
                    x++;
                }
                else{
                    System.out.println("Invalid Input");
                }
            }
            if(y==choice.Y){
                 row = getFirstRows(t);
                 col = getFirstColumns(t);
                 tempr = toFit(row);
                 tempc = toFit(col);
                 starting = findRegion(tempr, tempc);
                 row = toAdjust(row);
                 col = toAdjust(col);
                 if(isValid(starting, row, col)){
                     modBoard(starting, row, col, t);
                     x++;
                 }
                 else{
                     System.out.println("Invalid Input");
                 }
            }
            checkChoice(starting);
            printBoard();
            checkSmall();
            checkBig();
            System.out.println();
        } 
    }
    static void clear(){
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                arr1[i][j] = " ";
                arr2[i][j] = " ";
                arr3[i][j] = " ";
                arr4[i][j] = " ";
                arr5[i][j] = " ";
                arr6[i][j] = " ";
                arr7[i][j] = " ";
                arr8[i][j] = " ";
                arr9[i][j] = " ";
            }
        }
    }
    static void printBoard(){
        int count = 0;
        while(count<arr1.length){
            for(int i = 0; i < arr1.length; i++){
                System.out.print(arr1[count][i]);
            }  
            System.out.print("|");
            for(int i = 0; i < arr2.length; i++){
                System.out.print(arr2[count][i]);
            }
            System.out.print("|");
            for(int i = 0; i < arr3.length; i++){
                System.out.print(arr3[count][i]);
            }
            System.out.println();
            count++;
        }
        count = 0;
        for(int i = 0; i < 9; i++){
            if(i==3 || i==6)
                System.out.print("|");
            System.out.print("-");
        }
        System.out.println();
        while(count<arr4.length){
            for(int i = 0; i < arr4.length; i++){
                System.out.print(arr4[count][i]);
            }
            System.out.print("|");
            for(int i = 0; i < arr5.length; i++){
                System.out.print(arr5[count][i]);
            }  
            System.out.print("|");
            for(int i = 0; i < arr6.length; i++){
                System.out.print(arr6[count][i]);
            } 
            System.out.println();
            count++;
        }
        count = 0;
        for(int i = 0; i < 9; i++){
            if(i==3 || i==6)
                System.out.print("|");
            System.out.print("-");
        }
        System.out.println();
        while(count<arr7.length){
            for(int i = 0; i < arr7.length; i++){
                System.out.print(arr7[count][i]);
            }
            System.out.print("|");
            for(int i = 0; i < arr8.length; i++){
                System.out.print(arr8[count][i]);
            }
            System.out.print("|");
            for(int i = 0; i < arr9.length; i++){
                System.out.print(arr9[count][i]);
            }
            System.out.println();
            count++;
        }
    }
    static int getFirstRows(turn t ){
        Scanner kb = new Scanner(System.in);
        if(t==turn.X){
            System.out.println("Player X: Enter rows[1-9]");
            return kb.nextInt();
        }
        else{
            System.out.println("Player O: Enter rows[1-9]");
            return kb.nextInt();
        }
    }
    static int getFirstColumns(turn t){
        Scanner kb = new Scanner(System.in);
        if(t==turn.X){
            System.out.println("Player X: Enter column[1-9]");
            return kb.nextInt();
        }
        else{
            System.out.println("Player O: Enter column[1-9]");
            return kb.nextInt();
        }
    }
    static int getGeneralRows(turn t){
        Scanner kb = new Scanner(System.in);
        if(t==turn.X)
            System.out.println("Player X: Enter row[1-3]");
        
        else
            System.out.println("Player O: Enter row[1-3]");
        int temp = kb.nextInt();
        return temp;
    }
    static int getGeneralCols(turn t){
        Scanner kb = new Scanner(System.in);
        if(t==turn.X)
            System.out.println("Player X: Enter col[1-3]");
        
        else
            System.out.println("Player O: Enter col[1-3]");
        int temp = kb.nextInt();
        return temp;
    }
    static int toFit(int position){
        if(position<=3)
            position-=1;
        else if(position>=7)
            position+=1;
        return position;
    }
    static int toAdjust(int position){
        if(position>3){
            position = (position%3);
            if(position==0)
                position+=2;
            else
                position-=1;
        }
        else{
            position-=1;
        }
        return position;
    }
    static int findRegion(int row, int col){
        if(row<=3 && col <= 3){
            return 1;
        }
        else if(row<=3 && col>3 && col<=6){
            return 2;
        }
        else if(row<=3 && col>6 && col<=9){
            return 3;
        }
        else if(row>3 && row<=6 && col <=3){
            return 4;
        }
        else if(row>3 && row<=6 && col>3 && col<=6){
            return 5;
        }
        else if(row>3 && row<=6 && col>6 && col<=9){
            return 6;
        }
        else if(row>6 && row<=9 && col<=3){
            return 7;
        }
        else if(row>6 && row<=9 && col>3 && col<=6){
            return 8;
        }
        return 9;
    }
    static int findNewRegion(int r, int c){
        if(r==0 && c==0){
            return 1;
        }
        else if(r==0 && c==1){
            return 2;
        }
        else if(r==0 && c==2){
            return 3;
        }
        else if(r==1 && c==0){
            return 4;
        }
        else if(r==1 && c==1){
            return 5;
        }
        else if(r==1 && c==2){
            return 6;
        }
        else if(r==2 && c==0){
            return 7;
        }
        else if(r==2 && c==1){
            return 8;
        }
        return 9;
    }
    static void modBoard(int firstregion, int r, int c, turn t){
        switch(firstregion){
            case 1: 
                if(t==turn.X){
                    arr1[r][c] = "X";
                    break;
                }
                else{
                    arr1[r][c] = "O";
                    break;
                }
            case 2:
                if(t==turn.X){
                    arr2[r][c] = "X";
                    break;
                }
                else{
                    arr2[r][c] = "O";
                    break;
                }
            case 3:
                if(t==turn.X){
                    arr3[r][c] = "X";
                    break;
                }
                else{
                    arr3[r][c] = "O";
                    break;
                }
            case 4:
                if(t==turn.X){
                    arr4[r][c] = "X";
                    break;
                }
                else{
                    arr4[r][c] = "O";
                    break;
                }
            case 5:
                if(t==turn.X){
                    arr5[r][c] = "X";
                    break;
                }
                else{
                    arr5[r][c] = "O";
                    break;
                }
            case 6:
                if(t==turn.X){
                    arr6[r][c] = "X";
                    break;
                }
                else{
                    arr6[r][c] = "O";
                    break;
                }
            case 7:
                if(t==turn.X){
                    arr7[r][c] = "X";
                    break;
                }
                else{
                    arr7[r][c] = "O";
                    break;
                }
            case 8:
                if(t==turn.X){
                    arr8[r][c] = "X";
                    break;
                }
                else{
                    arr8[r][c] = "O";
                    break;
                }
            case 9:
                if(t==turn.X){
                    arr9[r][c] = "X";
                    break;
                }
                else{
                    arr9[r][c] = "O";
                    break;
                }
        }
    }
    static boolean isValid(int region, int row, int col){
        String temp = "";
        switch(region){
            case 1:
                temp = arr1[row][col];
                break;
            case 2:
                temp = arr2[row][col];
                break;
            case 3:
                temp = arr3[row][col];
                break;
            case 4:
                temp = arr4[row][col];
                break;
            case 5:
                temp = arr5[row][col];
                break;
            case 6:
                temp = arr6[row][col];
                break;
            case 7:
                temp = arr7[row][col];
                break;
            case 8:
                temp = arr8[row][col];
                break;
            case 9:
                temp = arr9[row][col];
                break;
        }
        if(temp!=" ")
            return false;
        return true;
    }
    static boolean isFull1(){
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1[i].length; j++){
                if(arr1[i][j]==" "){
                    return false;
                 }
            }
        }
        a1 = status.OFF;
        return true;
    }
    static boolean isFull2(){
        for(int i = 0; i < arr2.length; i++){
            for(int j = 0; j < arr2[i].length; j++){
                if(arr2[i][j]==" "){
                    return false;
                 }
            }
        }
        a2 = status.OFF;
        return true;
    }
    static boolean isFull3(){
        for(int i = 0; i < arr3.length; i++){
            for(int j = 0; j < arr3[i].length; j++){
                if(arr3[i][j]==" "){
                    return false;
                 }
            }
        }
        a3 = status.OFF;
        return true;
    }
    static boolean isFull4(){
        for(int i = 0; i < arr4.length; i++){
            for(int j = 0; j < arr4[i].length; j++){
                if(arr4[i][j]==" "){
                    return false;
                 }
            }
        }
        a4 = status.OFF;
        return true;
    }
    static boolean isFull5(){
        for(int i = 0; i < arr5.length; i++){
            for(int j = 0; j < arr5[i].length; j++){
                if(arr5[i][j]==" "){
                    return false;
                 }
            }
        }
        a5 = status.OFF;
        return true;
    }
    static boolean isFull6(){
        for(int i = 0; i < arr6.length; i++){
            for(int j = 0; j < arr6[i].length; j++){
                if(arr6[i][j]==" "){
                    return false;
                 }
            }
        }
        a6 = status.OFF;
        return true;
    }
    static boolean isFull7(){
        for(int i = 0; i < arr7.length; i++){
            for(int j = 0; j < arr7[i].length; j++){
                if(arr7[i][j]==" "){
                    return false;
                 }
            }
        }
        a7 = status.OFF;
        return true;
    }
    static boolean isFull8(){
        for(int i = 0; i < arr8.length; i++){
            for(int j = 0; j < arr8[i].length; j++){
                if(arr8[i][j]==" "){
                    return false;
                 }
            }
        }
        a8 = status.OFF;
        return true;
    }
    static boolean isFull9(){
        for(int i = 0; i < arr9.length; i++){
            for(int j = 0; j < arr9[i].length; j++){
                if(arr9[i][j]==" "){
                    return false;
                 }
            }
        }
        a9 = status.OFF;
        return true;
    }
    static boolean checkX1(){
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i][0].equals(arr1[i][1]) && arr1[i][0].equals(arr1[i][2]) && arr1[i][0].equals("X") && arr1[i][0]!=" " && arr1[i][1]!=" " && arr1[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr1[0].length; i++){
            if(arr1[0][i].equals(arr1[1][i]) && arr1[0][i].equals(arr1[2][i]) && arr1[0][i].equals("X") && arr1[0][i]!=" " && arr1[1][i]!=" " && arr1[2][i]!=" ")
                return true;
        }
        if(arr1[0][0].equals(arr1[1][1]) && arr1[0][0].equals(arr1[2][2]) && arr1[0][0].equals("X") && arr1[0][0]!=" ")
            return true;
        if(arr1[0][2].equals(arr1[1][1]) && arr1[0][2].equals(arr1[2][0]) && arr1[0][2].equals("X") && arr1[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX2(){
        for(int i = 0; i < arr2.length; i++){
            if(arr2[i][0].equals(arr2[i][1]) && arr2[i][0].equals(arr2[i][2]) && arr2[i][0].equals("X") && arr2[i][0]!=" " && arr2[i][1]!=" " && arr2[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr2[0].length; i++){
            if(arr2[0][i].equals(arr2[1][i]) && arr2[0][i].equals(arr2[2][i]) && arr2[0][i].equals("X") && arr2[0][i]!=" " && arr2[1][i]!=" " && arr2[2][i]!=" ")
                return true;
        }
        if(arr2[0][0].equals(arr2[1][1]) && arr2[0][0].equals(arr2[2][2]) && arr2[0][0].equals("X") && arr2[0][0]!=" ")
            return true;
        if(arr2[0][2].equals(arr2[1][1]) && arr2[0][2].equals(arr2[2][0]) && arr2[0][2].equals("X") && arr2[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX3(){
        for(int i = 0; i < arr3.length; i++){
            if(arr3[i][0].equals(arr3[i][1]) && arr3[i][0].equals(arr3[i][2]) && arr3[i][0].equals("X") && arr3[i][0]!=" " && arr3[i][1]!=" " && arr3[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr3[0].length; i++){
            if(arr3[0][i].equals(arr3[1][i]) && arr3[0][i].equals(arr3[2][i]) && arr3[0][i].equals("X") && arr3[0][i]!=" " && arr3[1][i]!=" " && arr3[2][i]!=" ")
                return true;
        }
        if(arr3[0][0].equals(arr3[1][1]) && arr3[0][0].equals(arr3[2][2]) && arr3[0][0].equals("X") && arr3[0][0]!=" ")
            return true;
        if(arr3[0][2].equals(arr3[1][1]) && arr3[0][2].equals(arr3[2][0]) && arr3[0][2].equals("X") && arr3[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX4(){
        for(int i = 0; i < arr4.length; i++){
            if(arr4[i][0].equals(arr4[i][1]) && arr4[i][0].equals(arr4[i][2]) && arr4[i][0].equals("X") && arr4[i][0]!=" " && arr4[i][1]!=" " && arr4[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr4[0].length; i++){
            if(arr4[0][i].equals(arr4[1][i]) && arr4[0][i].equals(arr4[2][i]) && arr4[0][i].equals("X") && arr4[0][i]!=" " && arr4[1][i]!=" " && arr4[2][i]!=" ")
                return true;
        }
        if(arr4[0][0].equals(arr4[1][1]) && arr4[0][0].equals(arr4[2][2]) && arr4[0][0].equals("X") && arr4[0][0]!=" ")
            return true;
        if(arr4[0][2].equals(arr4[1][1]) && arr4[0][2].equals(arr4[2][0]) && arr4[0][2].equals("X") && arr4[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX5(){
        for(int i = 0; i < arr5.length; i++){
            if(arr5[i][0].equals(arr5[i][1]) && arr5[i][0].equals(arr5[i][2]) && arr5[i][0].equals("X") && arr5[i][0]!=" " && arr5[i][1]!=" " && arr5[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr5[0].length; i++){
            if(arr5[0][i].equals(arr5[1][i]) && arr5[0][i].equals(arr5[2][i]) && arr5[0][i].equals("X") && arr5[0][i]!=" " && arr5[1][i]!=" " && arr5[2][i]!=" ")
                return true;
        }
        if(arr5[0][0].equals(arr5[1][1]) && arr5[0][0].equals(arr5[2][2]) && arr5[0][0].equals("X") && arr5[0][0]!=" ")
            return true;
        if(arr5[0][2].equals(arr5[1][1]) && arr5[0][2].equals(arr5[2][0]) && arr5[0][2].equals("X") && arr5[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX6(){
        for(int i = 0; i < arr6.length; i++){
            if(arr6[i][0].equals(arr6[i][1]) && arr6[i][0].equals(arr6[i][2]) && arr6[i][0].equals("X") && arr6[i][0]!=" " && arr6[i][1]!=" " && arr6[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr6[0].length; i++){
            if(arr6[0][i].equals(arr6[1][i]) && arr6[0][i].equals(arr6[2][i]) && arr6[0][i].equals("X") && arr6[0][i]!=" " && arr6[1][i]!=" " && arr6[2][i]!=" ")
                return true;
        }
        if(arr6[0][0].equals(arr6[1][1]) && arr6[0][0].equals(arr6[2][2]) && arr6[0][0].equals("X") && arr6[0][0]!=" ")
            return true;
        if(arr6[0][2].equals(arr6[1][1]) && arr6[0][2].equals(arr6[2][0]) && arr6[0][2].equals("X") && arr6[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX7(){
        for(int i = 0; i < arr7.length; i++){
            if(arr7[i][0].equals(arr7[i][1]) && arr7[i][0].equals(arr7[i][2]) && arr7[i][0].equals("X") && arr7[i][0]!=" " && arr7[i][1]!=" " && arr7[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr7[0].length; i++){
            if(arr7[0][i].equals(arr7[1][i]) && arr7[0][i].equals(arr7[2][i]) && arr7[0][i].equals("X") && arr7[0][i]!=" " && arr7[1][i]!=" " && arr7[2][i]!=" ")
                return true;
        }
        if(arr7[0][0].equals(arr7[1][1]) && arr7[0][0].equals(arr7[2][2]) && arr7[0][0].equals("X") && arr7[0][0]!=" ")
            return true;
        if(arr7[0][2].equals(arr7[1][1]) && arr7[0][2].equals(arr7[2][0]) && arr7[0][2].equals("X") && arr7[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX8(){
        for(int i = 0; i < arr8.length; i++){
            if(arr8[i][0].equals(arr8[i][1]) && arr8[i][0].equals(arr8[i][2]) && arr8[i][0].equals("X") && arr8[i][0]!=" " && arr8[i][1]!=" " && arr8[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr8[0].length; i++){
            if(arr8[0][i].equals(arr8[1][i]) && arr8[0][i].equals(arr8[2][i]) && arr8[0][i].equals("X") && arr8[0][i]!=" " && arr8[1][i]!=" " && arr8[2][i]!=" ")
                return true;
        }
        if(arr8[0][0].equals(arr8[1][1]) && arr8[0][0].equals(arr8[2][2]) && arr8[0][0].equals("X") && arr8[0][0]!=" ")
            return true;
        if(arr8[0][2].equals(arr8[1][1]) && arr8[0][2].equals(arr8[2][0]) && arr8[0][2].equals("X") && arr8[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkX9(){
        for(int i = 0; i < arr9.length; i++){
            if(arr9[i][0].equals(arr9[i][1]) && arr9[i][0].equals(arr9[i][2]) && arr9[i][0].equals("X") && arr9[i][0]!=" " && arr9[i][1]!=" " && arr9[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr9[0].length; i++){
            if(arr9[0][i].equals(arr9[1][i]) && arr9[0][i].equals(arr9[2][i]) && arr9[0][i].equals("X") && arr9[0][i]!=" " && arr9[1][i]!=" " && arr9[2][i]!=" ")
                return true;
        }
        if(arr9[0][0].equals(arr9[1][1]) && arr9[0][0].equals(arr9[2][2]) && arr9[0][0].equals("X") && arr9[0][0]!=" ")
            return true;
        if(arr9[0][2].equals(arr9[1][1]) && arr9[0][2].equals(arr9[2][0]) && arr9[0][2].equals("X") && arr9[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO1(){
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i][0].equals(arr1[i][1]) && arr1[i][0].equals(arr1[i][2]) && arr1[0][i].equals("O") && arr1[i][0]!=" " && arr1[i][1]!=" " && arr1[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr1[0].length; i++){
            if(arr1[0][i].equals(arr1[1][i]) && arr1[0][i].equals(arr1[2][i]) && arr1[0][i].equals("O") && arr1[0][i]!=" " && arr1[1][i]!=" " && arr1[2][i]!=" ")
                return true;
        }
        if(arr1[0][0].equals(arr1[1][1]) && arr1[0][0].equals(arr1[2][2]) && arr1[0][0].equals("O") && arr1[0][0]!=" ")
            return true;
        if(arr1[0][2].equals(arr1[1][1]) && arr1[0][2].equals(arr1[2][0]) && arr1[0][2].equals("O") && arr1[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO2(){
        for(int i = 0; i < arr2.length; i++){
            if(arr2[i][0].equals(arr2[i][1]) && arr2[i][0].equals(arr2[i][2]) && arr2[i][0].equals("O") && arr2[i][0]!=" " && arr2[i][1]!=" " && arr2[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr2[0].length; i++){
            if(arr2[0][i].equals(arr2[1][i]) && arr2[0][i].equals(arr2[2][i]) && arr2[0][i].equals("O") && arr2[0][i]!=" " && arr2[1][i]!=" " && arr2[2][i]!=" ")
                return true;
        }
        if(arr2[0][0].equals(arr2[1][1]) && arr2[0][0].equals(arr2[2][2]) && arr2[0][0].equals("O") && arr2[0][0]!=" ")
            return true;
        if(arr2[0][2].equals(arr2[1][1]) && arr2[0][2].equals(arr2[2][0]) && arr2[0][2].equals("O") && arr2[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO3(){
        for(int i = 0; i < arr3.length; i++){
            if(arr3[i][0].equals(arr3[i][1]) && arr3[i][0].equals(arr3[i][2]) && arr3[i][0].equals("O") && arr3[i][0]!=" " && arr3[i][1]!=" " && arr3[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr3[0].length; i++){
            if(arr3[0][i].equals(arr3[1][i]) && arr3[0][i].equals(arr3[2][i]) && arr3[0][i].equals("O") && arr3[0][i]!=" " && arr3[1][i]!=" " && arr3[2][i]!=" ")
                return true;
        }
        if(arr3[0][0].equals(arr3[1][1]) && arr3[0][0].equals(arr3[2][2]) && arr3[0][0].equals("O") && arr3[0][0]!=" ")
            return true;
        if(arr3[0][2].equals(arr3[1][1]) && arr3[0][2].equals(arr3[2][0]) && arr3[0][2].equals("O") && arr3[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO4(){
        for(int i = 0; i < arr4.length; i++){
            if(arr4[i][0].equals(arr4[i][1]) && arr4[i][0].equals(arr4[i][2]) && arr4[i][0].equals("O") && arr4[i][0]!=" " && arr4[i][1]!=" " && arr4[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr4[0].length; i++){
            if(arr4[0][i].equals(arr4[1][i]) && arr4[0][i].equals(arr4[2][i]) && arr4[0][i].equals("O") && arr4[0][i]!=" " && arr4[1][i]!=" " && arr4[2][i]!=" ")
                return true;
        }
        if(arr4[0][0].equals(arr4[1][1]) && arr4[0][0].equals(arr4[2][2]) && arr4[0][0].equals("O") && arr4[0][0]!=" ")
            return true;
        if(arr4[0][2].equals(arr4[1][1]) && arr4[0][2].equals(arr4[2][0]) && arr4[0][2].equals("O") && arr4[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO5(){
        for(int i = 0; i < arr5.length; i++){
            if(arr5[i][0].equals(arr5[i][1]) && arr5[i][0].equals(arr5[i][2]) && arr5[i][0].equals("O") && arr5[i][0]!=" " && arr5[i][1]!=" " && arr5[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr5[0].length; i++){
            if(arr5[0][i].equals(arr5[1][i]) && arr5[0][i].equals(arr5[2][i]) && arr5[0][i].equals("O") && arr5[0][i]!=" " && arr5[1][i]!=" " && arr5[2][i]!=" ")
                return true;
        }
        if(arr5[0][0].equals(arr5[1][1]) && arr5[0][0].equals(arr5[2][2]) && arr5[0][0].equals("O") && arr5[0][0]!=" ")
            return true;
        if(arr5[0][2].equals(arr5[1][1]) && arr5[0][2].equals(arr5[2][0]) && arr5[0][2].equals("O") && arr5[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO6(){
        for(int i = 0; i < arr6.length; i++){
            if(arr6[i][0].equals(arr6[i][1]) && arr6[i][0].equals(arr6[i][2]) && arr6[i][0].equals("O") && arr6[i][0]!=" " && arr6[i][1]!=" " && arr6[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr6[0].length; i++){
            if(arr6[0][i].equals(arr6[1][i]) && arr6[0][i].equals(arr6[2][i]) && arr6[0][i].equals("O") && arr6[0][i]!=" " && arr6[1][i]!=" " && arr6[2][i]!=" ")
                return true;
        }
        if(arr6[0][0].equals(arr6[1][1]) && arr6[0][0].equals(arr6[2][2]) && arr6[0][0].equals("O") && arr6[0][0]!=" ")
            return true;
        if(arr6[0][2].equals(arr6[1][1]) && arr6[0][2].equals(arr6[2][0]) && arr6[0][2].equals("O") && arr6[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO7(){
        for(int i = 0; i < arr7.length; i++){
            if(arr7[i][0].equals(arr7[i][1]) && arr7[i][0].equals(arr7[i][2]) && arr7[i][0].equals("O") && arr7[i][0]!=" " && arr7[i][1]!=" " && arr7[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr7[0].length; i++){
            if(arr7[0][i].equals(arr7[1][i]) && arr7[0][i].equals(arr7[2][i]) && arr7[0][i].equals("O") && arr7[0][i]!=" " && arr7[1][i]!=" " && arr7[2][i]!=" ")
                return true;
        }
        if(arr7[0][0].equals(arr7[1][1]) && arr7[0][0].equals(arr7[2][2]) && arr7[0][0].equals("O") && arr7[0][0]!=" ")
            return true;
        if(arr7[0][2].equals(arr7[1][1]) && arr7[0][2].equals(arr7[2][0]) && arr7[0][2].equals("O") && arr7[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO8(){
        for(int i = 0; i < arr8.length; i++){
            if(arr8[i][0].equals(arr8[i][1]) && arr8[i][0].equals(arr8[i][2]) && arr8[i][0].equals("O") && arr8[i][0]!=" " && arr8[i][1]!=" " && arr8[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr8[0].length; i++){
            if(arr8[0][i].equals(arr8[1][i]) && arr8[0][i].equals(arr8[2][i]) && arr8[0][i].equals("O") && arr8[0][i]!=" " && arr8[1][i]!=" " && arr8[2][i]!=" ")
                return true;
        }
        if(arr8[0][0].equals(arr8[1][1]) && arr8[0][0].equals(arr8[2][2]) && arr8[0][0].equals("O") && arr8[0][0]!=" ")
            return true;
        if(arr8[0][2].equals(arr8[1][1]) && arr8[0][2].equals(arr8[2][0]) && arr8[0][2].equals("O") && arr8[0][2]!=" ")
            return true;
        return false;
    }
    static boolean checkO9(){
        for(int i = 0; i < arr9.length; i++){
            if(arr9[i][0].equals(arr9[i][1]) && arr9[i][0].equals(arr9[i][2]) && arr9[i][0].equals("O") && arr9[i][0]!=" " && arr9[i][1]!=" " && arr9[i][2]!=" ")
                return true;
        }
        for(int i = 0; i < arr9[0].length; i++){
            if(arr9[0][i].equals(arr9[1][i]) && arr9[0][i].equals(arr9[2][i]) && arr9[0][i].equals("O") && arr9[0][i]!=" " && arr9[1][i]!=" " && arr9[2][i]!=" ")
                return true;
        }
        if(arr9[0][0].equals(arr9[1][1]) && arr9[0][0].equals(arr9[2][2]) && arr9[0][0].equals("O") && arr9[0][0]!=" ")
            return true;
        if(arr9[0][2].equals(arr9[1][1]) && arr9[0][2].equals(arr9[2][0]) && arr9[0][2].equals("O") && arr9[0][2]!=" ")
            return true;
        return false;
    }
    static void alterX1(){
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1[i].length; j++){
                arr1[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX2(){
        for(int i = 0; i < arr2.length; i++){
            for(int j = 0; j < arr2[i].length; j++){
                arr2[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX3(){
        for(int i = 0; i < arr3.length; i++){
            for(int j = 0; j < arr3[i].length; j++){
                arr3[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX4(){
        for(int i = 0; i < arr4.length; i++){
            for(int j = 0; j < arr4[i].length; j++){
                arr4[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX5(){
        for(int i = 0; i < arr5.length; i++){
            for(int j = 0; j < arr5[i].length; j++){
                arr5[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX6(){
        for(int i = 0; i < arr6.length; i++){
            for(int j = 0; j < arr6[i].length; j++){
                arr6[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX7(){
        for(int i = 0; i < arr7.length; i++){
            for(int j = 0; j < arr7[i].length; j++){
                arr7[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX8(){
        for(int i = 0; i < arr8.length; i++){
            for(int j = 0; j < arr8[i].length; j++){
                arr8[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterX9(){
        for(int i = 0; i < arr9.length; i++){
            for(int j = 0; j < arr9[i].length; j++){
                arr9[i][j] = "X";
            }
        }
        printBoard();
    }
    static void alterO1(){
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1[i].length; j++){
                arr1[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO2(){
        for(int i = 0; i < arr2.length; i++){
            for(int j = 0; j < arr2[i].length; j++){
                arr2[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO3(){
        for(int i = 0; i < arr3.length; i++){
            for(int j = 0; j < arr3[i].length; j++){
                arr3[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO4(){
        for(int i = 0; i < arr4.length; i++){
            for(int j = 0; j < arr4[i].length; j++){
                arr4[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO5(){
        for(int i = 0; i < arr5.length; i++){
            for(int j = 0; j < arr5[i].length; j++){
                arr5[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO6(){
        for(int i = 0; i < arr6.length; i++){
            for(int j = 0; j < arr6[i].length; j++){
                arr6[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO7(){
        for(int i = 0; i < arr7.length; i++){
            for(int j = 0; j < arr7[i].length; j++){
                arr7[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO8(){
        for(int i = 0; i < arr8.length; i++){
            for(int j = 0; j < arr8[i].length; j++){
                arr8[i][j] = "O";
            }
        }
        printBoard();
    }
    static void alterO9(){
        for(int i = 0; i < arr9.length; i++){
            for(int j = 0; j < arr9[i].length; j++){
                arr9[i][j] = "O";
            }
        }
        printBoard();
    }
    static void checkChoice(int s){
        if(s == 1 && a1 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 1 && a1 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 2 && a2 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 2 && a2 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 3 && a3 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 3 && a3 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 4 && a4 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 4 && a4 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 5 && a5 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 5 && a5 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 6 && a6 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 6 && a6 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 7 && a7 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 7 && a7 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 8 && a8 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 8 && a8 == status.ON){
            y = choice.N;
            return;
        }
        else if(s == 9 && a9 == status.OFF){
            y = choice.Y;
            return;
        }
        else if(s == 9 && a9 == status.ON){
            y = choice.N;
            return;
        }
    }
    static void checkSmall(){
        if(a1==status.ON){
            if(isFull1()){
                a1 = status.OFF;
            }
            if(checkX1()){
                alterX1();
                c1 = Control.X;
            }
            if(checkO1()){
                alterO1();
                c2 = Control.O;
            }
        }
        if(a2==status.ON){
            if(isFull2()){
                a2 = status.OFF;
            }
            if(checkX2()){
                alterX2();
                c2 = Control.X;
            }
            if(checkO2()){
                alterO2();
                c2 = Control.O;
            }
        }
        if(a3==status.ON){
            if(isFull3()){
                a3 = status.OFF;
            }
            if(checkX3()){
                alterX3();
                c3 = Control.X;
            }
            if(checkO3()){
                alterO3();
                c3 = Control.O;
            }
        }
        if(a4==status.ON){
            if(isFull4()){
                a4 = status.OFF;
            }
            if(checkX4()){
                alterX4();
                c4 = Control.X;
            }
            if(checkO4()){
                alterO4();
                c4 = Control.O;
            }
        }
        if(a5==status.ON){
            if(isFull5()){
                a5 = status.OFF;
            }
            if(checkX5()){
                alterX5();
                c5 = Control.X;
            }
            if(checkO5()){
                alterO5();
                c5 = Control.O;
            }
        }
        if(a6==status.ON){
            if(isFull6()){
                a6 = status.OFF;
            }
            if(checkX6()){
                alterX6();
                c6 = Control.X;
            }
            if(checkO6()){
                alterO6();
                c6 = Control.O;
            }
        }
        if(a7==status.ON){
            if(isFull7()){
                a7 = status.OFF;
            }
            if(checkX7()){
                alterX7();
                c7 = Control.X;
            }
            if(checkO7()){
                alterO7();
                c7 = Control.O;
            }
        }
        if(a8==status.ON){
            if(isFull8()){
                a8 = status.OFF;
            }
            if(checkX8()){
                alterX8();
                c8 = Control.X;
            }
            if(checkO8()){
                alterO8();
                c8 = Control.O;
            }
        }
        if(a9==status.ON){
            if(isFull9()){
                a9 = status.OFF;
            }
            if(checkX9()){
                alterX9();
                c9 = Control.X;
            }
            if(checkO9()){
                alterO9();
                c9 = Control.O;
            }
        }
    }
    static void checkBig(){
        if((c1 == c2 && c1 == c3 && c1!=null) || (c4 == c5 && c5 == c6 && c4!=null) || (c7 == c8 && c8 == c9 && c7!=null)){
            if(c1 == Control.X){
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player X is the winner.");
                return;
            }
            else{
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player O is the winner.");
                return;
            }
        }
        if((c1 == c4 && c1 == c7 && c1!=null) || (c2 == c5 && c2 == c8 && c2!=null) || (c3 == c6 && c3 == c9 && c3!=null)){
            if(c1 == Control.X){
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player X is the winner.");
                return;
            }
            else{
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player O is the winner.");
                return;
            }
        }
        if(c1 == c5 && c1 == c9 && c1!=null){
            if(c1 == Control.X){
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player X is the winner.");
                return;
            }
            else{
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player O is the winner.");
                return;
            }
        }
        if(c3 == c5 && c3 == c7 && c3!=null){
            if(c3 == Control.X){
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player X is the winner.");
                return;
            }
            else{
                prog = inProgress.OFF;
                printBoard();
                System.out.println("Player O is the winner.");
                return;
            }
        }
    }
}