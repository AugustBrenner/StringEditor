/******************************************************************************
 *
 * Test class for StringEditor.java allows user to enter an initial string
 * and have the cursor follow it.  Allows user to test all of the classes
 * in StringEditor.  The String will display after each edit.
 *
 * @see
 *   <A HREF="https://github.com/AugustBrenner">
 *       Checkout my GitHub</A>
 *
 * @author
 * August Brenner
 * G00682282
 *
 * @version
 *   October 8th, 2013
 ******************************************************************************/
import java.util.Scanner;

public class StringEditorTest
{

    public static void main(String [] args)
    {
        //instantiate variables to use
        int index;
        double value;

        // instantiate scanner object
        Scanner input = new Scanner(System.in);


        // creates one Polynomial using value provided by user
        System.out.println("Please enter an initial string:");
        StringEditor workingString = new StringEditor(input.nextLine(), null);
        System.out.printf("%s\n\n", workingString.toString());
        int userInput = 8;
        while(userInput != 0){
            System.out.println("Please select from one of the following menu " +
                    "options:\n0: Exit\n1: Insert Character\n2: Move cursor right" +
                    "\n3: Move cursor left\n4: Move cursor to front\n5: Move" +
                    "cursor to rear\n6: Delete character following\n7: Delete " +
                    "character preceding");
            userInput = input.nextInt();
            switch(userInput){
                case 0:
                    break;
                case 1:
                    System.out.println("Input Character:");
                    String userEntry = input.next();
                    if(userEntry != null && userEntry.length() > 0)
                    {
                        for(int i = 0; i < userEntry.toCharArray().length; i++)
                        {
                            workingString.insertChar(userEntry.toCharArray()[i]);
                        }
                    }
                    break;
                case 2:
                    workingString.moveCursorRight();
                    break;
                case 3:
                    workingString.moveCursorLeft();
                    break;
                case 4:
                    workingString.frontOfString();
                    break;
                case 5:
                    workingString.endOfString();
                    break;
                case 6:
                    workingString.deleteChar();
                    break;
                case 7:
                    workingString.backspace();
                    break;
            }
            System.out.println(workingString.toString());
        }
    }
}
