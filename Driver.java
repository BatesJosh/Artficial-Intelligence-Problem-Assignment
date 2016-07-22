/**
 *
 * Public class to drive Search class
 *
 * This code was created by Joshua Bates and is his property.
 */
public class Driver
{
    public static void main(String [] args)
    {
	if(args.length > 0)
	    {
		Problem prob=null;
		int[][] box=null;
		String srchStr="";
		boolean debug=false;
		Search search=new Search();
		if(args[0].equalsIgnoreCase("FWGC"))
		    {
			System.out.println("Problem FWGC chosen");
			prob=new FWGC();
			if(args.length>=2)
			    {
				srchStr=args[1];
				prob=new FWGC();
				if(args.length>=3)
				    {
					if(args[2].equalsIgnoreCase("debug"))
					    {
						debug=true;
					    }
				    }
				if(srchStr.equalsIgnoreCase("bfs"))
				   {
				       System.out.println("Breadth First Search Chosen");
				       System.out.println(search.bFSearch(prob, debug));
				   }
				else if(srchStr.equalsIgnoreCase("dfs"))
				    {
					System.out.println("Depth First Search Chosen");
					System.out.println(search.dFSearch(prob, debug));
				    }
			    }
		    }
		else if(args[0].equalsIgnoreCase("8Puzzle") ) 
		   {
		       int index=1;
		       if(args.length>=3)
			   {
			       String toArr=args[index];
			       if( getNumbers(toArr, box))
				   {
				       	   
				       int counter=0;
				       box=new int[3][3];
				       for(int i=0; i< 3;i++)
					   {
					       for(int j=0; j<3; j++)
						   {
						       box[i][j]=Character.getNumericValue(toArr.charAt(counter));
						       counter++;
						   }
					   }
		 
				       prob=new Puzzle8(box);
				       index++;
				       srchStr=args[index];
				       index++;
				   }
			       else
				   {
				       System.out.println("Invalid Format");
				       index=-1;
				   }
			       if(args.length==4)
				   {
				       	if(args[index].equalsIgnoreCase("debug"))
					    {
						debug=true;
					    }
				  
				   }
			       if(index!=-1 && srchStr.equalsIgnoreCase("bfs"))
				   {

				       System.out.println("Breadth First Search Chosen");
				       System.out.println(search.bFSearch(prob, debug));
				   }
			       else if(index!=-1 && srchStr.equalsIgnoreCase("dfs"))
				   {
				       System.out.println("Depth First Search Chosen");
				       System.out.println(search.dFSearch(prob, debug));
				   }
			       else if(index!=-1 && srchStr.equalsIgnoreCase("a*"))
				   {
				       String hStr=args[index];
				       index++;
				       if(hStr.equalsIgnoreCase("manhattan") || hStr.equalsIgnoreCase("numbersoutofplace"))
					   {
					       if(index<args.length)
						   {
						       if(args[index].equalsIgnoreCase("debug"))
							   {
							       debug=true;
							       System.out.println("Debug on");
							   }
						   }
					       System.out.println(search.aSearch(prob, debug, hStr));
					   }else
					   {
					       System.out.println("Invalid format");
					   }
				   }
			   }
		   }
	       
	    }
	  
			     
    }

    //private method to make sure the numbers are valid 
    private static boolean getNumbers(String num, int[][] puz)
    {
	String check="123456780";
	boolean isValid=true;
	    if(num.length()==9)
		{
		    for(int i=0; i<9; i++)
			{
			    if(num.indexOf(String.valueOf(check.charAt(i)))==-1)
				{
				   isValid=false;
		        	}
			}
		}
	    else
		{
		    isValid=false;
		}
	    if(isValid)
		{
		   
		}
	    return isValid;
    }

}
