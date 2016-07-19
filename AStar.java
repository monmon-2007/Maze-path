import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.*;

public class AStar
{

    private PriorityQueue<Node> openList;
    private ArrayList<Node> closedList;
    HashMap<Node, Integer> gVals;
    HashMap<Node, Integer> fVals;
    private int initialCapacity = 100;


    public AStar()
    {
        gVals = new HashMap<Node, Integer>();
        fVals = new HashMap<Node, Integer>();
        openList = new PriorityQueue<Node>(initialCapacity, new fCompare());
        closedList = new ArrayList<Node>();
    }

    public static void main(String[] args)throws Exception
    {
        Node[][] n = new Node[101][101];



        BufferedReader br = new BufferedReader(new FileReader("maze_No_2.txt"));
		int columnCount = 101;
		int rowCount = 101;

		 String[][] map = new String[rowCount][columnCount];

		 for (int i = 0; i < rowCount; i++)
		 {
			String line = br.readLine();
		 	for (int j = 0; j < columnCount; j++)
			{
				map[i][j] = String.valueOf(line.charAt(j));
			}
		  }
		  br.close();

		/*
			transforming the Mize into a node Grid
		*/

		for (int i = 0; i < rowCount; i++)
		{
			for (int j = 0; j < columnCount; j++)
			{
				n[i][j] = new Node();	 		//set the node to defult
				n[i][j].setNodeId(map[i][j]);	//set the value of the node
				n[i][j].setPosition(i+1,j+1);	//set postion
			}
		}


 		for (int i = 0; i < rowCount; i++)
		{
			for (int j = 0; j < columnCount; j++)
			{
				if(!n[i][j].getNodeId().equals("#"))
				{
					n[i][j].setXY(n[i][j].getX(),n[i][j].getY());
				}
			}
		}

		for (int i = 0; i < rowCount; i++)
		{
			for (int j = 0; j < columnCount; j++)
			{
				if((i-1)>0 && !n[i-1][j].getNodeId().equals("#"))
				{
					n[i][j].addNeighbor(n[i-1][j]);
				}
				if((j-1)>0 && !n[i][j-1].getNodeId().equals("#"))
				{
					n[i][j].addNeighbor(n[i][j-1]);
				}
				if((j+1)<n.length && !n[i][j+1].getNodeId().equals("#"))
				{
					n[i][j].addNeighbor(n[i][j+1]);
				}
					if((i+1)<n.length && !n[i+1][j].getNodeId().equals("#"))
				{
					n[i][j].addNeighbor(n[i+1][j]);
				}

			}
		}



new AStar().traverse(n[0][52], n[100][100]);

}
    public void traverse(Node start, Node end)
    {
        openList.clear();
        closedList.clear();

        gVals.put(start, 0);
        openList.add(start);

        while (!openList.isEmpty())
        {
            Node current = openList.element();
            if (current.equals(end)) //x0=x1 && y0 = y1
            {
                System.out.println("You Reached Your Goal!");
                printPath(current);
                return;
            }
            closedList.add(openList.poll());
            ArrayList<Node> neighbors = current.getNeighbors();

            for (Node neighbor : neighbors)
            {
                int gScore = gVals.get(current) + 1;
                int fScore = gScore + h(neighbor, current);

                if (closedList.contains(neighbor))
                {

                    if (gVals.get(neighbor) == null)
                    {
                        gVals.put(neighbor, gScore);
                    }
                    if (fVals.get(neighbor) == null)
                    {
                        fVals.put(neighbor, fScore);
                    }

                    if (fScore >= fVals.get(neighbor))
                    {
                        continue;
                    }
                }
                if (!openList.contains(neighbor) || fScore < fVals.get(neighbor))
                {
                    neighbor.setParent(current);
                    gVals.put(neighbor, gScore);
                    fVals.put(neighbor, fScore);
                    if (!openList.contains(neighbor))
                    {
                        openList.add(neighbor);
                    }
                }
            }
        }
        System.out.println("Failed to find Path");
    }

    private int h(Node node, Node goal)
    {
        int x = Math.abs(node.getX() - goal.getX());
        int y = Math.abs(node.getY() - goal.getY());
        return x * x + y * y;
    }

    private void printPath(Node node)
    {

        System.out.println("[" + node.getX() + " ," + node.getY() + "]");

        while (node.getParent() != null)
        {
            node = node.getParent();
            System.out.println("[" + node.getX() + " ," + node.getY() + "]");
        }
    }

    class fCompare implements Comparator<Node>
    {

        public int compare(Node o1, Node o2)
        {
            if (fVals.get(o1) < fVals.get(o2))
            {
                return -1;
            }
            else if (fVals.get(o1) > fVals.get(o2))
            {
                return 1;
            }
            else
            {
                return 1;
            }
        }
    }
}


