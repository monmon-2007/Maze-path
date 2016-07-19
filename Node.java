import java.util.*;
class Node
{

    private Node parent;
    private ArrayList<Node> neighbors;
    private int x;
    private int y;
    private String value;


    public Node()
    {
        neighbors = new ArrayList<Node>();
        value = "";
    }
    void setNodeId(String value)
	{
			this.value = value;
	}

	String getNodeId()
	{
		return value;
	}

    void setPosition(int x, int y)
	{
			this.x=x;
			this.y=y;
	}

    public ArrayList<Node> getNeighbors()
    {
        return neighbors;
    }

    public void addNeighbor(Node node)
    {
        this.neighbors.add(node);
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Node n)
    {
        return this.x == n.x && this.y == n.y;
    }
}