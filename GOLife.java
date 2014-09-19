import java.util.Scanner;
public class GOLife
{
   private Board board;
   private byte[][] aux_board;
   private Scanner sc_key;
   
   public GOLife(){
       this.board       = new Board();
       this.aux_board   = new byte[Configuration.height][Configuration.width];
       this.sc_key      = new Scanner(System.in);
       this.print_banner();
       this.set_board_user();
       this.game_process();
   }
   
   private void print_banner(){
       System.out.println("******************** Game of life ********************\n");
       System.out.println("· The size of matrix is specified in Configuration.java, please refer it to change this options");
       System.out.println("· Now, set initial configuration of automata, to put ON a cell (value 1), input cell coords by this way:");
       System.out.println("\t\tValid Examples:\n\t\t2 3\n\t\t0 0\n");
       System.out.println("When you finish configuration,please insert an invalid coords like: -1 -1 :)");
       System.out.println("Please enter a key...");
       this.sc_key.nextLine();
       this.clear_window();
    }  
   
   private void set_board_user(){
       int x,y = 0;
       boolean flag = false;
       do{
           System.out.println("Current configuration: "); this.board.print_status();
           System.out.println("\n\nCoords:\t");
           x = this.sc_key.nextInt();
           y = this.sc_key.nextInt();
           flag = x>=0 && x<=Configuration.height && y>=0 && y<=Configuration.width;
           if(flag) this.board.set_coords(x,y,(byte)1);
       }while(flag);
   }
       
   private void game_process(){
       int surrounders_alive = 0;
       this.board.print_status();
       while(true){
           try{ Thread.sleep(Configuration.tCycle); }
           catch(InterruptedException e){}
           finally{             
               for(int c=1500;c>=0;c--){
                   for(int x=0;x<Configuration.height;x++){
                       for(int y=0;y<Configuration.width;y++){
                           surrounders_alive = board.surrounders_alive(x,y);
                           if(this.board.get_coords(x,y)==1){
                               if(surrounders_alive>=Configuration.celLives0 && surrounders_alive<=Configuration.celLives1){ aux_board[x][y] = (byte)1;}
                               else if(surrounders_alive<Configuration.celLives0 || surrounders_alive>Configuration.celLives1){ aux_board[x][y] = (byte)0;}
                               }
                       else if(surrounders_alive==Configuration.celLives2){ aux_board[x][y] = (byte)1;} 
                       }             
                   }
               }
           }  
           if(this.check_break_flag()) break;
           this.copy_boards();
           this.clear_window();
           this.board.print_status();                  
       }
    }
    
   private boolean check_break_flag(){
       for(int i=0;i<Configuration.height;i++) for(int j=0;j<Configuration.width;j++) if(this.aux_board[i][j]!=this.board.get_coords(i,j)) return false;
       return true;
   }
   
   private void copy_boards(){ for(int i=0;i<Configuration.height;i++) for(int j=0;j<Configuration.width;j++) this.board.set_coords(i,j,this.aux_board[i][j]); }
   
   private void clear_window(){for(int i=0;i<50;++i,System.out.println());}
   public static void main(String args[]){
       GOLife gol = new GOLife();
   }

}
