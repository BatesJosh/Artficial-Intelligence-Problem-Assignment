import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * This code implements Search on Problem classes.
 *
 * This code was created and is the property of Joshua Bates.
 */
public class Search
{
    /**
     *
     * Public constructor for search.
     */
    Search()
    {

    }

    /**
     * Takes in a problem, and a boolean to turn on and off debug, and searches
     * Using breadth first search.
     *
     * @param solve
     * @param debug
     */
    public  String bFSearch(Problem solve,  boolean debug)
    {
	String str="";
	int opened=0;
	Node anode=new Node(null, solve);
	boolean solving=true;
	if(anode.state.isGoal())
	    {
		str=solution(anode, debug, opened);
		solving=false;
	    }
	LinkedList<Node> nodeList=new LinkedList<Node>();
	nodeList.add(anode);
	
	while(solving)
	    {
		if(nodeList.isEmpty())
		    {
			solving=false;
			str="Unable to Solve";
		    }
		else
		    {
			anode=nodeList.pollFirst();
			ArrayList<? extends Problem> arr=anode.state.successors();
			opened++;
			for(int i=0; i<arr.size();i++)
			    {
				if(noCycle(anode, arr.get(i) ))
				    {
					Node cNode=new Node(anode, arr.get(i));
					if(cNode.state.isGoal())
					    {
						str=solution(cNode, debug, opened);
						solving=false;
						break;
					    }
					else
					    {
						nodeList.add(cNode);
					    }
				    }
			    } 
		    }
	    }
	   
	return str;
    }

    /**
     * Takes in a problem, and a boolean to turn on and off debug, and searches
     * Using depth first search.
     *
     * @param solve
     * @param debug
     */
    public  String dFSearch(Problem solve,  boolean debug)
    {
	String str="";
	int opened=0;
	Node anode=new Node(null, solve);
	boolean solving=true;
	Stack<Node> nodeList=new Stack<Node>();
	nodeList.push(anode);
	while(solving)
	    {
		if(nodeList.isEmpty())
		    {
			solving=false;
			str="Unable to Solve";
		    }
		else
		    {
			anode=nodeList.pop();
			ArrayList<? extends Problem> arr=anode.state.successors();
			opened++;
			if(anode.state.isGoal())
			    {
				str=solution(anode, debug, opened);
				solving=false;
			    }
			else
			    {
				for(int i=0; i<arr.size();i++)
				    {
					if(noCycle(anode, arr.get(i) ))
					    {
						Node cNode=new Node(anode, arr.get(i));
						        
						nodeList.push(cNode);
						
					    }
				    } 
			    }
		    }
	    }
	return str;
    }

    /**
     * Implements A* search by taking in a problem, and a heuristic and returns
     * the solution.
     *
     * @param solve problem to solve
     * @param debug to turn on debug
     * @param hStr heursitic value
     * @returns
     */
    public String aSearch(Problem solve, boolean debug, String hStr)
    {
	String str="";
	int opened=0;
	Node anode=new Node(null, solve);
	anode.state.setHeuristic(hStr);
	boolean solving=true;
	PriorityQueue<Node> nodeList=new PriorityQueue<Node>(10, hComparator);
	nodeList.add(anode);
	while(solving)
	    {
		if(nodeList.isEmpty())
		    {
			solving=false;
			str="Unable to Solve";
		    }
		else
		    {
			anode=nodeList.poll();
			opened++;
			if(anode.state.isGoal())
			    {
				str=solution(anode, debug, opened);
				solving=false;
			    }
			else
			    {
				ArrayList<? extends Problem> arr=anode.state.successors();
				for(int i=0; i<arr.size();i++)
				    {
					if(noCycle(anode, arr.get(i) ))
					    {
						Node cNode=new Node(anode, arr.get(i));
						cNode.state.setHeuristic(hStr);
						nodeList.add(cNode);
						
					    }
				    } 
			    }
		    }
	    }
	return str;
    }

    //Comparator class used by priority queue. Compares using heuristicValue()
    private static Comparator<Node> hComparator = new Comparator<Node>()
    {
	@Override
	public int compare(Node cNode, Node nNode)
	{
	    return (cNode.state.heuristicValue()-nNode.state.heuristicValue());
	}
    };
    
    //private method to check from the child to the root for no repeats
    private boolean noCycle(Node curr, Problem child)
    {
	boolean ncycle=true;
	Node check=curr;
	if(check.state.compare(child))
	    {
		ncycle=false;
	    }
	else
	    {
		ncycle=true;
	    }
	
	while(!(check.parent==null) &&  ncycle)
	      {
		  if(check.state.compare(child))
		      {
	       
			  ncycle=false;
		      }
		  check=check.parent;
	      }
/*
	if(cycle)
	{
	    if(check.state.compare(child))
		{
		    cycle=false;
		}
	}*/
	return ncycle;
    }

    //private method to get the solution
    private String solution(Node child, boolean debug, int opened)
    {
	StringBuilder build=new StringBuilder();
	int totalCost=child.state.getCost();
	Node curr=child;
	while(curr.parent!=null)
	    {
		build.insert(0, curr.state.toString());
		curr=curr.parent;
	    }
	build.insert(0, curr.state.toString());
	String dStr="";
	if(debug)
	    {
		dStr="\n Number of nodes opened: "+opened+"\n";
	    }
	return "Solution: \n"+ build.toString()+"\nCost: "+totalCost+dStr;
    }
    //private node class to create the tree used in the searches
    private class Node
    {
	Node parent;
	Problem state;
	
	private Node(Node ancestor, Problem cState)
	{
	    parent=ancestor;
	    state=cState;
	}
	
    }

}
