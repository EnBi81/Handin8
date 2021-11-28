package maze;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import java.util.LinkedList;
import java.util.Queue;

public class Maze
{
  public int shortestPath(char[][] maze)
  {
    return runMaze(maze, 1, 1, Direction.Top, new HashSet<>());
  }
  enum Direction{
    Left, Right, Top, Bottom
  }
  public HashMap<Direction, Integer[]> map = new HashMap<>(){{
    put(Direction.Top, new Integer[]{-1,0});
    put(Direction.Bottom, new Integer[]{1,0});
    put(Direction.Right, new Integer[]{0,1});
    put(Direction.Left, new Integer[]{0,-1});
  }};
  public HashMap<Direction, Direction> pairs = new HashMap<>(){{
    put(Direction.Top, Direction.Bottom);
    put(Direction.Bottom, Direction.Top);
    put(Direction.Right, Direction.Left);
    put(Direction.Left, Direction.Right);
  }};
  public int runMaze(char[][] maze, int r, int c, Direction from, HashSet<Integer> visited)
  {
    //When we arrived to the destination
    if(r == maze.length - 2 && c == maze[r].length - 2)
      return 0;

    var currentHash = createHash(r, c); //Create our unique key for the current position
    visited.add(currentHash); // Add current position to the visited list

    int max = Integer.MAX_VALUE; //set starting value
    for(var direction : map.keySet()) //loop through every direction
    {
      var newRow = r + map.get(direction)[0]; //Calculate the new cell to visit
      var newCol = c + map.get(direction)[1]; //Depending on the direction

      if(direction == from) //If we came from this direction, we do not want to go back
        continue;
      if(maze[newRow][newCol] == 'o') //If it is a wall
        continue;
      if(visited.contains(createHash(newRow, newCol))) //If we have already been there
        continue;
      /*display(maze, r, c,visited);*/  //in case you want to see what is going on

      int result = runMaze(maze, newRow, newCol, pairs.get(direction), visited); //magic
      if(max > result) //saving result
        max = result + 1;

      if(max + visited.size() == maze.length + maze[0].length - 5)
        break; //If we have found the shortest possible solution
    }

    visited.remove(currentHash); //delete current position from the visited list
    return max;
  }

  public int createHash(int r, int c)
  {
    return r * 10000 + c;
  }


  void display(char[][] maze, int row, int col, HashSet<Integer> visited)
  {
    for (int i = 0; i < maze.length; i++)
    {
      for (int j = 0; j < maze[i].length; j++)
      {
        char out = maze[i][j];
        if(visited.contains(createHash(i, j)))
          out = ' ';
        else if(row == i && col == j)
          out = '+';

        System.out.print(out);
      }
      System.out.println();
    }
    new Scanner(System.in).nextLine();
  }
}
