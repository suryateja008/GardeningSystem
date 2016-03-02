package GardenCode;

/**
 * Created by suryaduggi on 2/20/16.
 */
public class Gardner {
   public static Gardner instance = new Gardner();

    private Gardner()
    {

    }

    public void mow()
    {
        SystemInterface[][] grid = GardenView.getGrid();
          if(SprinklerSystem.autoSprinkler)
               SprinklerSystem.sprinklerSwitch();
          for(int i=0;i<grid.length;i++)
               for(int j=0;j<grid[0].length;j++)
               {
                            if(grid[i][j] instanceof Grass)
                            {
                                Grass temp = (Grass)grid[i][j];
                                temp.setHeight(.5f);
                                Logger.log(String.format("Lawn Mow at Row %d and col %d",i,j));
                            }
               }
        if(!SprinklerSystem.autoSprinkler)
            SprinklerSystem.sprinklerSwitch();

    }


}
