
/******************************************************************************
 *
 * A CharNode is an instance of a node in a linked list that stores char
 * variables and a reference to the next char in the list.
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

public class CharNode
{
   // Invariant of the CharNode class:
   //   1. The node's char data is in the instance variable data.
   //   2. For the final node of a list, the link part is null.
   //      Otherwise, the link part is a reference to the
   //      next node of the list.
   private char data;
   private CharNode link;


   /**
   * Initialize a node with a specified initial data and link to the next
   * node. Note that the initialLink may be the null reference,
   * which indicates that the new node has nothing after it.
   * @param initialData
   *   the initial data of this new node
   * @param initialLink
   *   a reference to the node after this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * @postcondition
   *   This node contains the specified data and link to the next node.
   **/
   public CharNode(char initialData, CharNode initialLink)
   {
      data = initialData;
      link = initialLink;
   }


   /**
   * Modification method to add a new node after this node.
   * @param item
   *   the data to place in the new node
   * @postcondition
   *   A new node has been created and placed after this node.
   *   The data for the new node is item. Any other nodes
   *   that used to be after this node are now after the new node.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for a new
   *   CharNode.
   **/
   public void addNodeAfter(char item)
   {
      link = new CharNode(item, link);
   }


   /**
   * Accessor method to get the data from this node.
   * @param - none
   * @return
   *   the data from this node
   **/
   public char getData( )
   {
      return data;
   }


   /**
   * Accessor method to get a reference to the next node after this node.
   * @param - none
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
   public CharNode getLink( )
   {
      return link;
   }


   /**
   * Copy a list.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at
   *   source. The return value is the head reference for the
   *   copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.
   **/
   public static CharNode listCopy(CharNode source)
   {
      CharNode copyHead;
      CharNode copyTail;

      // Handle the special case of the empty list.
      if (source == null)
         return null;

      // Make the first node for the newly created list.
      copyHead = new CharNode(source.data, null);
      copyTail = copyHead;

      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }

      // Return the head reference for the new list.
      return copyHead;
   }


   /**
   * Copy a list, returning both a head and tail reference for the copy.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at
   *   source.  The return value is an
   *   array where the [0] element is a head reference for the copy and the [1]
   *   element is a tail reference for the copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.
   **/
   public static CharNode[ ] listCopyWithTail(CharNode source)
   {
      CharNode copyHead;
      CharNode copyTail;
      CharNode[ ] answer = new CharNode[2];

      // Handle the special case of the empty list.
      if (source == null)
         return answer; // The answer has two null references .

      // Make the first node for the newly created list.
      copyHead = new CharNode(source.data, null);
      copyTail = copyHead;

      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }

      // Return the head and tail references.
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }


   /**
   * Compute the number of nodes in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list
   *   with a null head)
   * @return
   *   the number of nodes in the list with the given head
   * @note
   *   A wrong answer occurs for lists longer than Int.MAX_VALUE.
   **/
   public static int listLength(CharNode head)
   {
      CharNode cursor;
      int answer;

      answer = 0;
      for (cursor = head; cursor != null; cursor = cursor.link)
         answer++;

      return answer;
   }


   /**
   * Copy part of a list, providing a head and tail reference for the new copy.
   * @param start/end
   *   references to two nodes of a linked list
   * @param copyHead/copyTail
   *   the method sets these to refer to the head and tail node of the new
   *   list that is created
   * @precondition
   *   start and end are non-null references to nodes
   *   on the same linked list,
   *   with the start node at or before the end node.
   * @return
   *   The method has made a copy of the part of a linked list, from the
   *   specified start node to the specified end node. The return value is an
   *   array where the [0] component is a head reference for the copy and the
   *   [1] component is a tail reference for the copy.
   * @exception IllegalArgumentException
   *   Indicates that start and end are not references
   *   to nodes on the same list.
   * @exception NullPointerException
   *   Indicates that start is null.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.
   **/
   public static CharNode[ ] listPart(CharNode start, CharNode end)
   {
      CharNode copyHead;
      CharNode copyTail;
      CharNode cursor;
      CharNode[ ] answer = new CharNode[2];

      // Make the first node for the newly created list. Notice that this will
      // cause a NullPointerException if start is null.
      copyHead = new CharNode(start.data, null);
      copyTail = copyHead;
      cursor = start;

      // Make the rest of the nodes for the newly created list.
      while (cursor != end)
      {
         cursor = cursor.link;
         if (cursor == null)
            throw new IllegalArgumentException
            ("end node was not found on the list");
         copyTail.addNodeAfter(cursor.data);
         copyTail = copyTail.link;
      }

      // Return the head and tail references
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }


   /**
   * Find a node at a specified position in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param position
   *   a node number
   * @precondition
   *   position > 0.
   * @return
   *   The return value is a reference to the node at the specified position in
   *   the list. (The head node is position 1, the next node is position 2, and
   *   so on.) If there is no such position (because the list is too short),
   *   then the null reference is returned.
   * @exception IllegalArgumentException
   *   Indicates that position is not positive.
   **/
   public static CharNode listPosition(CharNode head, int position)
   {
      CharNode cursor;
      int i;

      if (position <= 0)
           throw new IllegalArgumentException("position is not positive");

      cursor = head;
      for (i = 1; (i < position) && (cursor != null); i++)
         cursor = cursor.link;

      return cursor;
   }


   /**
   * Search for a particular piece of data in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param target
   *   a piece of data to search for
   * @return
   *   The return value is a reference to the first node that contains the
   *   specified target. If there is no such node, the null reference is
   *   returned.
   **/
   public static CharNode listSearch(CharNode head, char target)
   {
      CharNode cursor;

      for (cursor = head; cursor != null; cursor = cursor.link)
         if (target == cursor.data)
            return cursor;

      return null;
   }


   /**
   * Modification method to remove the node after this node.
   * @param - none
   * @precondition
   *   This node must not be the tail node of the list.
   * @postcondition
   *   The node after this node has been removed from the linked list.
   *   If there were further nodes after that one, they are still
   *   present on the list.
   * @exception NullPointerException
   *   Indicates that this was the tail node of the list, so there is nothing
   *   after it to remove.
   **/
   public void removeNodeAfter( )
   {
      link = link.link;
   }


   /**
   * Modification method to set the data in this node.
   * @param newData
   *   the new data to place in this node
   * @postcondition
   *   The data of this node has been set to newData.
   **/
   public void setData(char newData)
   {
      data = newData;
   }


   /**
   * Modification method to set the link to the next node after this node.
   * @param newLink
   *   a reference to the node that should appear after this node in the linked
   *   list (or the null reference if there is no node after this node)
   * @postcondition
   *   The link to the node after this node has been set to newLink.
   *   Any other node (that used to be in this link) is no longer connected to
   *   this node.
   **/
    public void setLink(CharNode newLink)
    {
        link = newLink;
    }


    /**
     * Removes the tail node.
     * @param head
     *   The head reference for a linked list (which may be an empty list in
     *   which case the head is null).
     * @precondition
     *   head is the head pointer of a linked list, with at least one node.
     * @postcondition
     *   The tail (last) node has been removed and returned to the heap.
     * @return
     *   The return value is the head pointer of the new, shorter linked list.
     **/
     public static CharNode listTailRemove(CharNode head)
     {
       CharNode cursor;

        for(cursor = head; cursor != null; cursor = cursor.link)
        {
            if(cursor.link == null)
            {
                cursor.setData('\u0000');
                cursor.setLink(null);
            }else if(cursor.link.link == null)
            {
                cursor.link = null;
            }
        }

        return head;
    }


    /**
     * Adds a new node to the tail of the linked list.
     * @param head
     *   The head reference for a linked list (which may be an empty list in
     *   which case the head is null).
     * @param entry
     *   The value of the new node to be added at the tail.
     * @precondition
     *   The head is the head pointer of a linked list.
     * @postcondition
     *   A new node containing the given entry has been added at the tail of the linked list.
     * @return
     *   The return value is a head pointer to the head of the new, longer linked list.
     **/
    public static CharNode listTailInsert(CharNode head, char entry)
    {
        if(head == null){
            head = new CharNode(entry, null);
        }
        else{
            CharNode cursor;
            cursor = head;
            boolean flag = false;
            while(flag == false)
            {
                if(cursor.link == null)
                {
                    cursor.addNodeAfter(entry);
                    flag = true;
                }
                cursor = cursor.link;
            }
        }
        return head;
    }


    /**
     * Returns the pointer to the last node in the list.
     * @param head
     *   The head reference for a linked list (which may be an empty list in
     *   which case the head is null).
     * @precondition
     *   The head is the head pointer of a linked list.
     * @postcondition
     *   The list is unchanged.
     * @return
     *   The return value is a a pointer to the last node in the list.
     **/
    public static CharNode getLast(CharNode head)
    {
        CharNode cursor;
        for(cursor = head; cursor != null; cursor = cursor.link)
        {
            if(cursor.getLink() == null)
                return cursor;
        }

        return null;
    }


    /**
     * Prints the list elements on the command line.
     * @param head
     *   The head reference for a linked list (which may be an empty list in
     *   which case the head is null).
     * @precondition
     *   The head is the head pointer of a linked list.
     * @postcondition
     *   The list elements are printed to screen.
     **/
    public static void outList(CharNode head)
    {
        CharNode cursor;
        for(cursor = head; cursor != null; cursor = cursor.link)
        {
            System.out.print(cursor.getData());
        }
    }
}
