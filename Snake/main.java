import java.util.Iterator;
import java.util.LinkedList;

class BackEnd
{
    public static void main(String args[])
    {
        // System.out.println("this is the main file and object from which all the other files will be called\n");
        // System.out.println("setting new box of coordinates 0,0 with box type food\n");
        Box box1 = new Box(0, 0);
        box1.setboxType(BoxType.SNAKE_HEAD);
        // System.out.println("box1: x = " + box1.getColumn() + " y = " + box1.getRow() + " type = " + box1.getBoxType());
        // System.out.println("making snake body with a size of 0\n");
        Snake snake = new Snake(box1);
        // System.out.println("snake size = " + snake.getSize() + " direction = " +snake.getDirection());
        snake.setDirection(Direction.UP);
        // System.out.println("growing snake\n");
        snake.grow();
        // System.out.println("new snake size =" + snake.getSize() + " direction = " + snake.getDirection());
        // for(Iterator i = snake.getSnakLinkedList().iterator(); i.hasNext();)
        // {
        //     Box body = (Box)i.next();
        //     System.out.println("Snake node: r = " + body.getRow() + " c = " + body.getColumn());
        // }
        snake.grow();
        snake.move(Direction.DOWN);
        // System.out.println("new snake size =" + snake.getSize() + " direction = " + snake.getDirection());
        for(Iterator i = snake.getSnakLinkedList().iterator(); i.hasNext();)
        {
            Box body = (Box)i.next();
            System.out.println("Snake node: Type = " + body.getBoxType() + "  r = " + body.getRow() + " c = " + body.getColumn());
        }
    }
}

enum BoxType
{
    EMPTY, FOOD, SNAKE_NODE, SNAKE_HEAD;
}

enum Direction
{
    UP, DOWN, LEFT, RIGHT;
}

//class to set the boxes that make up the board of the game the outside does not have access
//to set the columns and rows but does have access to select what inside the box ie boxType
class Box   
{           
    private int row;
    private int column;
    private BoxType boxType;

    public Box(int r, int c)    //constructor where we initialize the variables and how we make objects in other classes.
    {
        this.column = c;
        this.row = r;
    }

    public int getRow() //getter functions an example of encapsulation where the only way to interact with private variables are through these functions
    {
        return row;
    }

    public void setRow(int r)
    {
        this.row = r;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int c)
    {
        this.column = c;
    }

    public void setboxType(BoxType bType)   //setter function for changing the value of a private variable 
    {
        this.boxType = bType;
    }

    public BoxType getBoxType()
    {
        return boxType;
    }

}

class Snake
{
    private LinkedList<Box> snakeLinkedList = new LinkedList<>();
    private Box head;
    private int size;
    private Direction direction;

    public Snake(Box initPos)
    {
        head = initPos;
        snakeLinkedList.add(head);
        size = 0;
        direction = Direction.RIGHT;
    }

    public void setHead(Box head)
    {
        this.head = head;
    }

    public Box getHead()
    {
        return head;
    }

    public void setSize(int s)
    {
        this.size = s;
    }

    public int getSize()
    {
        return size;
    }

    public void setDirection(Direction dir)
    {
        this.direction = dir;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public LinkedList getSnakLinkedList()
    {
        return snakeLinkedList;
    }

    private void addNode(int r, int c)
    {
        Box prev = snakeLinkedList.getLast();
        Box body = new Box(prev.getRow()+r, head.getColumn()+c);
        body.setboxType(BoxType.SNAKE_NODE);
        snakeLinkedList.add(body);
        setSize(size + 1);
    }

    private void shift(int r, int c)
    {
        for(Iterator i = snakeLinkedList.iterator(); i.hasNext();)
        {
            Box nodeBox = (Box)i.next();
            nodeBox.setRow(nodeBox.getRow()+r);
            nodeBox.setColumn(nodeBox.getColumn()+c);
        }
    }

    public void grow()
    {
        switch(direction)
        {
            case UP:
                addNode(-1, 0);
                break;
            case DOWN:
                addNode(1, 0);
                break;
            case RIGHT:
                addNode(0, -1);
                break;
            case LEFT:
                addNode(0, 1);
                break;
            default:
                System.out.println("Invalid direction\n");
                break;
        }
    }

    public void death()
    {
        snakeLinkedList.clear();
        setSize(0);
    }

    public void move(Direction inpuDirection)
    {
        if(inpuDirection == Direction.UP && direction == inpuDirection)
        {
            shift(1, 0);
        }

        else if(inpuDirection == Direction.UP && direction != inpuDirection)
        {
            if(direction == Direction.LEFT || direction == Direction.RIGHT)
            {} 
        }

        else if(direction == Direction.DOWN && direction == inpuDirection)
        {
            
        }

        else if(direction == Direction.DOWN && direction != inpuDirection)
        {
            shift(-1, 0);
            snakeLinkedList.getLast().setboxType(BoxType.SNAKE_HEAD);
            snakeLinkedList.getFirst().setboxType(BoxType.SNAKE_NODE);
            if(direction == Direction.LEFT || direction == Direction.RIGHT)
            {}
        }

        else if(direction == Direction.RIGHT && direction == inpuDirection)
        {
            
        }

        else if(direction == Direction.RIGHT && direction != inpuDirection)
        {
            if(direction == Direction.UP || direction == Direction.DOWN)
            {}
        }

        else if(direction == Direction.RIGHT && direction == inpuDirection)
        {
            
        }

        else if(direction == Direction.RIGHT && direction != inpuDirection)
        {
            if(direction == Direction.UP || direction == Direction.DOWN)
            {}
        }
    }
}