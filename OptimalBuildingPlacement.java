// Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid.
//The goal is for the furthest of all lots to be as near as possible to a building. Given an input n, which is the number of buildings to be placed in the lot,
//determine the building placement to minimize the distance the most distant empty lot is from the building. Movement is restricted to horizontal and vertical 
//i.e. diagonal movement is not required.

// For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.

// "0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

// 1 0 1 2

// 2 1 2 1

// 1 0 1 0

// 2 1 2 1

import java.util.*;

class Main {

  static int result = Integer.MAX_VALUE;
  
  public static void main(String[] args) {
    System.out.println("Hello world!");
    int result = findOptimalPlacement(3, 3, 1);
    System.out.println(result);
  }

  private static int findOptimalPlacement(int n, int m, int b) {
    OptimalBuildingPlacement odp = new OptimalBuildingPlacement(b, n, m);
    helper(odp, n, m, b);
    return result;
  }

  // try placing all b buildings in any random cells and find dist
  // time - O(nmCb)
  private static void helper(OptimalBuildingPlacement odp, int n, int m, int b) {
    //base
    //if buildings remaining drops to 0, find dist for current config and update result
    if(b == 0)
    {
      result = Math.min(result, odp.findMaxDistanceOfCurrentConfig());
      return;
    }
    //logic
    //try all possible configs
    for(int i = 0; i < n; i++)
      {
        for(int j = 0; j < m; j++)
          {
            if(odp.grid[i][j] == 1)
            {
              odp.grid[i][j] = 0;
              helper(odp, n, m, b - 1);
              odp.grid[i][j] = 1;
            }
          }
      }

    return;
  }
}

class OptimalBuildingPlacement {
  int n; //buildings to be placed in grid
  int[][] grid; //matrix
  int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //unit vectors to track neighbors

  public OptimalBuildingPlacement(int numberOfBuildings, int n, int m) {
    this.n = numberOfBuildings;
    this.grid = new int[n][m];
    //if a cell has 1 int it, then it is empty
    //initially all cells in grid are empty
    for(int i = 0; i < grid.length; i++)
      {
        for(int j = 0; j < grid[0].length; j++)
          {
            grid[i][j] = 1;
          }
      }
  }

  //calculates the shortest distance for each cell to nearest 0 cell (a cell which has building in it)
  // time - O(n*m) -> visits each cell once
  // space - O(n*m) -> dist matrix
  public int findMaxDistanceOfCurrentConfig() {
    int[][] dist = new int[grid.length][grid[0].length]; //tracks shortest dist for each cell
    Queue<int[]> support = new LinkedList<>();
    for(int i = 0; i < grid.length; i++)
      {
        for(int j = 0; j < grid[0].length; j++)
          {
            if(grid[i][j] == 0) //building cell so proccess it in level 1
            {
              support.offer(new int[]{i, j});
            }
            else
            {
              dist[i][j] = Integer.MAX_VALUE; //distance unknown, so set to inf
            }
          }
      }

    int maxDistance = Integer.MIN_VALUE;

    while(!support.isEmpty())
      {
        int layerSize = support.size();
        for(int i = 0; i < layerSize; i++)
          {
            int[] current = support.poll(); //find cell with largest dist
            maxDistance = Math.max(maxDistance, dist[current[0]][current[1]]);

            for(int[] dir : dirs)
              {
                int nr = current[0] + dir[0];
                int nc = current[1] + dir[1];
                //find neighbors and check if neighbor is within bounds
                if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length)
                {
                  //check if shorter dist for neighbor is found
                  //if true, update and process neighbor in next level
                  if(dist[nr][nc] > 1 + dist[current[0]][current[1]])
                  {
                    dist[nr][nc] = 1 + dist[current[0]][current[1]];
                    support.offer(new int[]{nr, nc});
                  }
                }
              }
          }
      }

    return maxDistance; //return cell with largest dist
  }
}
