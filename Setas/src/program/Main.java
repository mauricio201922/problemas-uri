package program;

import java.io.IOException;
import java.util.*;

public class Main { 
    public static void main(String[] args) throws IOException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Main obj = new Main(n);

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < n; j++) {
                obj.arr[i][j] = line.charAt(j);
            }
        }

        obj.findDangerCellsInBorder(n);

        System.out.println(obj.safeCells);
        
        sc.close();
    }
    
    public char[][] arr;
    public int safeCells;
    public int n;

    public Main (int n) {
        this.arr = new char[n][n];
        this.safeCells = n * n;
        this.n = n;
    }

    public void findDangerCellsInBorder (int n) {
        int i = 1 , j = 1;
        this.checkTips(n);

        for (j = 1; j < n-1; j++) { // n - n
            if (arr[0][j] == 'A') this.findWaystoThatCell(0, j);
        }

        for (i = 1; i < n-1; i ++) { // n - 0
            if (arr[i][0] == '<') this.findWaystoThatCell(i, 0);

        }

       for (; j > 0; j --) { // 0 - n
           if (j > 0 && j < n-1){
               if (arr[n-1][j] == 'V') this.findWaystoThatCell(n-1, j);
           }
       }
       
       for (; i > 0; i --) { // 0 - 0
        if (i > 0 && i < n-1){
            if (arr[i][n-1] == '>') this.findWaystoThatCell(i, n-1);
        }
       }
    
    }
    public void checkTips (int n) {
        n--;
        if (arr[0][0] == '<' || arr[0][0] == 'A')
            this.findWaystoThatCell(0, 0);

        if (arr[0][n] == 'A' || arr[0][n] == '>')
            this.findWaystoThatCell(0, n);

        if (arr[n][n] == '>' || arr[n][n] == 'V')
            this.findWaystoThatCell(n, n);
        
        if (arr[n][0] == '<' || arr[n][0] == 'V')
            this.findWaystoThatCell(n, 0);
    }

    public void findWaystoThatCell (int i, int j) { 
        int[] result;
        Stack<int[]> prev = new Stack<>();
        prev.push(new int[]{i, j});

        while (true) {
            arr[prev.peek()[0]][prev.peek()[1]] = 'o';
            result = this.verifyAround(prev.peek()[0], prev.peek()[1]);

            if (result[0] == -1) {
                arr[prev.peek()[0]][prev.peek()[1]] = '-';
                this.safeCells--;
                if (prev.size() == 1) { break; }
                else { prev.pop(); continue; }
            } else prev.push(result);
        }
    }

    public int[] verifyAround (int i, int j) {
        if ((i - 1) >= 0 && arr[i - 1][j] == 'V')  return new int[]{i - 1, j};

        if ((j - 1) >= 0 && arr[i][j - 1] == '>') return new int[]{i, j - 1};

        if ((j + 1) < this.n && arr[i][j + 1] == '<') return new int[]{i, j + 1};

        if ((i + 1) < this.n && arr[i + 1][j] == 'A') return new int[]{i + 1, j};

		return new int[]{-1};
    }

}