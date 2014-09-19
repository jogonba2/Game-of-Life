public class Board
{
    private byte[][] board;
    
    public Board(){
        this.board = new byte[Configuration.height][Configuration.width];
    }
    
    public void set_coords(int x,int y,byte value){ 
        try{this.board[x][y] = value; }
        catch(ArrayIndexOutOfBoundsException e){ System.out.println("Not valid coords");}
    }
    public byte get_coords(int x,int y){ return this.board[x][y]; }
           
    // Receives coords of interested point
    public int surrounders_alive(int x,int y){
        int count = 0;
        try{ if(this.board[x-1][y-1]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x+1][y-1]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x-1][y]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x+1][y]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x][y-1]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x][y+1]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x+1][y+1]==1) count++; }
        catch(Exception e){}
        try{ if(this.board[x-1][y+1]==1) count++; }
        catch(Exception e){}
        return count;
    }
    
    public void print_status(){
        for(int x=0;x<Configuration.height;x++){
            for(int y=0;y<Configuration.width;y++){
                if(this.board[x][y]==1) System.out.print("*");
                else System.out.print("0");
            }
            System.out.println();
        }
    }    
}
