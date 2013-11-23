/******************************************************************************
 *
 * A StringEditor object represents a string (collection of characters),
 * with a ‘cursor position’.  The implementation of the StringEditor class
 * uses two linked lists member variables.  One linked list contains all of
 * the characters which are to the left of the ‘cursor’.  The other linked
 * list contains all the characters to the right of the ‘cursor’.
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

public class StringEditor
{
    // Invariant of the StringEditor class:
    //   1. All link list char data to the left of the cursor is stored in leftList.
    //   2. All link list char data to the right of the cursor is stored in rightList.
    //   3. The current location of the cursor is stored in cursor.
    private CharNode leftList;
    private CharNode rightList;
    private CharNode cursor;


    /**
     * StringEditor object is created
     * @postcondition
     *   No characters are in the string (both lists are empty)
     **/
    StringEditor()
    {
        leftList = null;
        rightList = null;
        cursor = rightList;
    }


    /**
     * StringEditor object is created.
     * @param left
     *   Chars to the left of the 'cursor'.
     * @param right
     *   Chars to the right of the 'cursor'.
     * @postcondition
     *   Characters from the parameter left are to the left of the 'cursor', characters from
     *   the parameter right are to the right of the 'cursor'.
     **/
    StringEditor(String left, String right)
    {
        CharNode head;
        if(left != null && left.length() > 0)
        {
            leftList = new CharNode(left.toCharArray()[0], null);
            head = leftList;
            for(int i = 1; i < left.toCharArray().length; i++)
            {
                leftList.addNodeAfter(left.toCharArray()[i]);
                leftList = leftList.getLink();
            }
            leftList = head;
        }else{
            leftList = null;
        }

        if(right != null && right.length() > 0)
        {
            rightList = new CharNode(right.toCharArray()[0], null);
            head = rightList;
            for(int i = 1; i < right.toCharArray().length; i++)
            {
                rightList.addNodeAfter(right.toCharArray()[i]);
                rightList = rightList.getLink();
            }
            rightList = head;
            cursor = rightList;
        }else{
            rightList = null;
            cursor = rightList;
        }
    }


    /**
     * Determines if the ‘cursor’ is at the front of the string.
     * @postcondition
     *   The object is unchanged.
     * @return
     *   The return value is true if the cursor is at the front of the string.
     **/
    boolean isCursorAtFront()
    {
        if(leftList == null)
            return true;
        return false;
    }


    /**
     * Determines if the ‘cursor’ is at the end of the string.
     * @postcondition
     *   The object is unchanged.
     * @return
     *   The return value is true if the cursor is at the end of the string.
     **/
    boolean isCursorAtEnd()
    {
        if(rightList == null)
            return true;
        return false;
    }


    /**
     * Insert new character at prior to cursor.
     * @param c
     *   The value of the new node to be added before the cursor.
     * @postcondition
     *   The cursor should follow new character.
     **/
    void insertChar(char c)
    {
        if(!isCursorAtEnd())
            CharNode.getLast(leftList).addNodeAfter(c);
        else
            leftList = CharNode.listTailInsert(leftList, c);
    }


    /**
     * Removes the character directly following the cursor.
     * @postcondition
     *   If there was a character directly following to the cursor, it has been removed
     *   from the string.
     **/
    void deleteChar()
    {
        if(rightList != null)
        {
            rightList = rightList.getLink();
            cursor = rightList;
        }
    }


    /**
     * Removes the character directly prior the cursor.
     * @postcondition
     *   If there was a character directly prior to the cursor, it has been removed
     *   from the string.
     **/
    void backspace()
    {
        if(leftList != null)
            CharNode.listTailRemove(leftList);
    }


    /**
     * Cursor is moved to the front of the string.
     * @postcondition
     * The cursor is now at the front of the string.
     **/
    void frontOfString()
    {
        CharNode.getLast(leftList).setLink(rightList);
        rightList = CharNode.listCopy(leftList);
        leftList = null;
        cursor = rightList;
    }


    /**
     * Cursor is moved to the end of the string.
     * @postcondition
     * The cursor is now at the end of the string.
     **/
    void endOfString()
    {
        if(!isCursorAtEnd()){
            CharNode rightCopy = CharNode.listCopy(rightList);
            CharNode.getLast(leftList).setLink(rightCopy);
            rightList = null;
        }
    }


    /**
     * Cursor is moved to one position to the left.
     * @postcondition
     * The cursor is now one position to the left.
     **/
    void moveCursorLeft()
    {
        if(!isCursorAtFront())
        {
            if(leftList.getLink() == null)
            {
                rightList = new CharNode(leftList.getData(),
                        CharNode.listCopy(rightList));
                leftList = null;
            }
            else
            {
                for(CharNode workingList = leftList; workingList != null;
                    workingList = workingList.getLink())
                {
                    if(workingList.getLink().getLink() == null)
                    {
                        rightList = new CharNode(workingList.getLink().getData(),
                                CharNode.listCopy(rightList));
                        workingList.setLink(null);

                    }
                    cursor = rightList;
                }
            }
        }
    }


    /**
     * Cursor is moved one position to the right.
     * @postcondition
     * The cursor is now one position to the right.
     **/
    void moveCursorRight()
    {
        if(!isCursorAtEnd())
        {
            leftList = CharNode.listTailInsert(leftList, rightList.getData());
            rightList = rightList.getLink();
        }
    }


    /**
     * returns the string, in the following format   (^ represents cursor position)
     * For example:  [how now brown^cow]
     * Empty string:  [^]
     * @postcondition
     *   StringEditor object is unchanged
     **/
    @Override
    public String toString()
    {
        CharNode workingList;
        StringBuilder output = new StringBuilder();
        for(workingList = leftList; workingList != null; workingList = workingList.getLink())
        {
            output.append(workingList.getData());
        }
        output.append("^");

        if(rightList != null)
        {
            for(workingList = rightList; workingList != null; workingList = workingList.getLink())
            {
                output.append(workingList.getData());
            }
        }
        return output.toString();
    }
}
