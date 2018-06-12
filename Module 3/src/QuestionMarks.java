import java.util.Scanner;

public class QuestionMarks {

    public static void main(String[] args) {
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        System.out.print("How many question marks to display: ");
        int cnt = input.nextInt();
        System.out.print("What is your choice: ");
        int choice = input.nextInt();

        if(choice == 1) {
            for(int i=cnt; i>0; i--) {
                for(int j=0; j<i; j++) {
                    System.out.print("?");
                }
                System.out.print("\n");
            }
        }
        else if(choice == 2) {
            for(int i=1; i<=cnt; i++) {
                for(int j=0; j<i; j++) {
                    System.out.print("?");
                }
                System.out.print("\n");
            }
        }
    }
}
