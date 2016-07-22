import java.util.*;
/**
 * Interface class for creating problems
 *
 */
public interface Problem
{
    /**
     * Checks for goal state
     */
   public boolean isGoal();

    /**
     * Returns an array of successor states
     */
   public ArrayList<? extends Problem> successors();

    /**
     * Gets pathcost.
     */
   public int getCost();
    /**
     * Returns a toString.
     */
   public String toString();
    /**
     * Gets a heuristic value.
     */
   public int heuristicValue();
    /**
     * Sets a path cost.
     */
   public void setCost(int value);
    /**
     * Compares two problems and returns if their states are equal.
     */
    public boolean compare( Problem state);

    /**
     * Sets heuristic, so different heuristics can be used for the same problem.
     */
    public void setHeuristic(String str);
}
