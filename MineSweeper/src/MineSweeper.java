import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class MineSweeper extends JPanel implements ActionListener, MouseListener{
	
	JFrame frame;
	JMenuBar menuBar;
	JMenu menu;
	JMenu menu2;
	JMenu menu3;
	JMenuItem begin;
	JMenuItem inter;
	JMenuItem expert;
	JMenuItem icon1;
	JMenuItem icon2;
	JMenuItem icon3;
	JMenuItem flagc;
	JMenuItem controls;
	JMenuItem time;
	JButton smile;
	JPanel panel;
	JToggleButton[][] togglers;
	ImageIcon mine;
	ImageIcon flag;
	ImageIcon smiles;
	ImageIcon dead;
	int dimR = 9;
	int dimC = 9;
	int mines[][];
	int checkmines[][];
	int flags[][];
	int minenum = 10;
	boolean fclick = false;
	boolean end = false;
	int flagcount = 10;
	boolean win = false;
	java.util.Timer timer;
	int tcount = 0;
	
	
	public MineSweeper() {
		
		frame = new JFrame("MineSweeper");
		frame.add(this);
		frame.setSize(1000,800);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		menu2 = new JMenu("Icons");
		menu3 = new JMenu("Controls");
		begin = new JMenuItem("Beginner");
		inter = new JMenuItem("Intermediate");
		expert = new JMenuItem("Expert");
		icon1 = new JMenuItem("Icon 1");
		icon2 = new JMenuItem("Icon 2");
		icon3 = new JMenuItem("Icon 3");
		controls = new JMenuItem("Welcome to Minesweeper, here you have to eliminate all the mines in the grid by flagging them \n\n Left-click an empty square to reveal it.\n"
					 +"Right-click an empty square to flag it.");
		
		
		smiles = new ImageIcon("smile.png");
		smiles = new ImageIcon(smiles.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		
		dead = new ImageIcon("dead.png");
		dead = new ImageIcon(dead.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		
	    smile= new JButton(smiles);
		smile.addActionListener(this);
		smile.setAlignmentX(Component.CENTER_ALIGNMENT);
		mine = new ImageIcon("mine.png");
		mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		
		flag = new ImageIcon("flag.png");
		flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		togglers = new JToggleButton[dimR][dimC];
		panel = new JPanel();
		panel.setLayout(new GridLayout(togglers.length,togglers[0].length));
		
		flagc = new JMenuItem("FlagCount: 10");
		time = new JMenuItem("Time: 0");
		
		for(int i=0;i<togglers.length;i++) {
			for(int j=0;j<togglers[0].length;j++) {
				togglers[i][j] = new JToggleButton();
				togglers[i][j].addMouseListener(this);
				panel.add(togglers[i][j]);
				
			}
		}
		
		
		
		
		menu.add(begin);
		menu.add(inter);
		menu.add(expert);
		menu2.add(icon1);
		menu2.add(icon2);
		menu2.add(icon3);
		menu3.add(controls);

		begin.addActionListener(this);
		inter.addActionListener(this);
		expert.addActionListener(this);
		icon1.addActionListener(this);
		icon2.addActionListener(this);
		icon3.addActionListener(this);
		
		menuBar.add(menu);
		menuBar.add(menu2);
		menuBar.add(menu3);
		smile.setPreferredSize(new Dimension(80, 80));
		menuBar.add(smile);
		flagc.setPreferredSize(new Dimension(40, 40));
		menuBar.add(flagc);
		time.setPreferredSize(new Dimension(40, 40));
		menuBar.add(time);
	
		frame.add(menuBar,BorderLayout.NORTH);	
		frame.add(panel,BorderLayout.CENTER);
		
		
	
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer = new java.util.Timer();
		
		
		
	}
		

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			
			
			
			for(int x=0;x<togglers.length;x++)
			for(int y=0;y<togglers[0].length;y++)
			{
				
				if(e.getSource()==togglers[x][y]&&end ==false)
				{	
					
					//first click mines
					if(mines==null) {
				
						if(!togglers[x][y].isSelected()) {
							
							fclick = true;
							mines = new int[dimR][dimC];
							placeMines(x,y);
							flags = new int[dimR][dimC];
							flagPosition();
							
							
							}
						for(int i=0;i<togglers.length;i++) {
							for(int j=0;j<togglers[0].length;j++) {
								System.out.print(mines[i][j]);
								
							}
							System.out.println();
						}
							
						
						}
			
					if(!togglers[x][y].isSelected()&&flags[x][y]!=3)
					{
						togglers[x][y].setSelected(true);
						togglers[x][y].setIcon(flag);
						flags[x][y] = 1;
						flagcount--;
						System.out.println(flagcount);
						flagc.setText("FlagCount: "+flagcount);
					
						if(flagcount==0) {
							int c =0;
							for(int i=0;i<dimR;i++) {
								for(int j=0;j<dimC;j++) {
									if(flags[i][j]==1&&flags[i][j]==mines[i][j]) {
										c++;
									}
								}
							}
							if(c == minenum) {
								end = true;
								win = true;
								System.out.println(end);
								
							}
						
						}
						
					}
					else if(flags[x][y]!=3&&flagcount!=minenum)
					{
						togglers[x][y].setSelected(false);
						togglers[x][y].setIcon(null);
						flags[x][y] = 0;
						flagcount++;
						flagc.setText("FlagCount: "+flagcount);
						
					}
					
					
					
				}
				revalidate();
			}
			
		}//end of button3
		
		if(e.getButton() == MouseEvent.BUTTON1) {
		
			for(int x=0;x<togglers.length;x++)
			for(int y=0;y<togglers[0].length;y++)
			{
				
				
				
				 if(e.getSource()==togglers[x][y]&&end==false)
				{
					
					
					//mines
					if(mines!=null) {
						if(!togglers[x][y].isSelected()&&mines[x][y]==1&&flags[x][y]!=1)
						{
							togglers[x][y].setSelected(true);
							togglers[x][y].setIcon(mine);
							for(int i=0;i<mines.length;i++) {   
								for(int j=0;j<mines[0].length;j++)
									{
										if(mines[i][j]==1) {
											togglers[i][j].setSelected(false);
											togglers[i][j].setIcon(mine);
										}
								}
									
							}
							end = true;
							 smile.setIcon(dead);
							
						}
						else if(flags[x][y]!=1&&flags[x][y]!=3)
						{
						
							togglers[x][y].setIcon(null);
							togglers[x][y].setSelected(false);
							flags[x][y]=3;
							expansion(x,y);
							
						}
					}
					//first click mines
					if(mines==null) {
						
						if(!togglers[x][y].isSelected()) {
							
							fclick = true;
							mines = new int[dimR][dimC];
							placeMines(x,y);
							flags = new int[dimR][dimC];
							flagPosition();
							expansion(x,y);
							
							
							}
						for(int i=0;i<togglers.length;i++) {
							for(int j=0;j<togglers[0].length;j++) {
								System.out.print(mines[i][j]);
								
							}
							System.out.println();
						}
						System.out.println();
						tcount = 0;
						timer.schedule(new UpdateTimer(), 0, 1000);
						
						}
				}
			}
			revalidate();
		}//end of button1
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==icon1) {
			fclick=false;
			mines = null;
			end = false;
			if(dimR == 9) {
				flagcount = 10;
			}else if(dimC== 16) {
				flagcount = 40;
			}else {
				flagcount = 99;
			}
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
			smiles = new ImageIcon("smile.png");
			smiles = new ImageIcon(smiles.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			dead = new ImageIcon("dead.png");
			dead = new ImageIcon(dead.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			mine = new ImageIcon("mine.png");
			mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			flag = new ImageIcon("flag.png");
			flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
		}else if(e.getSource()==icon2) {
			fclick=false;
			mines = null;
			end = false;
			if(dimR == 9) {
				flagcount = 10;
			}else if(dimC== 16) {
				flagcount = 40;
			}else {
				flagcount = 99;
			}
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
			smiles = new ImageIcon("narutohappy.png");
			smiles = new ImageIcon(smiles.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			dead = new ImageIcon("narutosad.png");
			dead = new ImageIcon(dead.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			

			mine = new ImageIcon("narutobomb.png");
			mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			flag = new ImageIcon("narutoflag.png");
			flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		}else if(e.getSource()==icon3) {
			fclick=false;
			mines = null;
			end = false;
			if(dimR == 9) {
				flagcount = 10;
			}else if(dimC== 16) {
				flagcount = 40;
			}else {
				flagcount = 99;
			}
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
			smiles = new ImageIcon("smashappy.png");
			smiles = new ImageIcon(smiles.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			dead = new ImageIcon("smashsad.jpg");
			dead = new ImageIcon(dead.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			

			mine = new ImageIcon("smashbomb.png");
			mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
			
			flag = new ImageIcon("smashflag.png");
			flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		}
		if(e.getSource()==smile) {
			fclick=false;
			mines = null;
			end = false;
			if(dimR == 9) {
				flagcount = 10;
			}else if(dimC== 16) {
				flagcount = 40;
			}else {
				flagcount = 99;
			}
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
		}else if(e.getSource()==begin) {
			dimR =9;
			dimC = 9;
			minenum = 10;
			fclick=false;
			mines = null;
			end = false;
			flagcount = 10;
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
		}else if(e.getSource()==inter) {
			dimR =16;
			dimC = 16;
			minenum = 40;
			fclick=false;
			mines = null;
			end = false;
			flagcount = 40;
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
		}else if(e.getSource()==expert) {
			dimR = 16;
			dimC = 30;
			minenum = 99;
			fclick=false;
			mines = null;
			end = false;
			flagcount = 99;
			flagc.setText("FlagCount: "+flagcount);
			tcount = 0;
			timer.cancel();
			timer=new java.util.Timer();
			time.setText("Time: 0");
		}
		
	
		
	
		frame.remove(panel);
		togglers = new JToggleButton[dimR][dimC];
		panel = new JPanel();
		panel.setLayout(new GridLayout(togglers.length,togglers[0].length));
		
	
		
	
		
		for(int i=0;i<togglers.length;i++) {
			for(int j=0;j<togglers[0].length;j++) {
				togglers[i][j] = new JToggleButton();
				togglers[i][j].addMouseListener(this);
				panel.add(togglers[i][j]);
				
			}
		}
		smile.setIcon(smiles);
		mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimR, frame.getHeight()/dimC, Image.SCALE_SMOOTH));
		frame.add(panel);
		revalidate();
		repaint();
		
		
	}
	
	public void placeMines(int a,int b) {
		
		
		for(int i=0;i<dimR;i++) {
			for(int j=0;j<dimC;j++) {
				mines[i][j] = 0;
				
				
			}
		}
		mines[a][b] = 1;//mine is at that position
		for(int x=0;x<minenum-1;x++) {
			int randx = (int)(Math.random()*dimR);
			int randy = (int)(Math.random()*dimC);
			
				for(int i=a-1;i<=a+1;i++) {
					
					for(int j=b-1;j<=b+1;j++) {
						if(mines[i][j]==1) {
							mines[i][j] = 0;
							 randx = (int)(Math.random()*dimR);
							 randy = (int)(Math.random()*dimC);
							while(randx!=i&&randy!=j) {
								randx = (int)(Math.random()*dimR);
								randy = (int)(Math.random()*dimC);
							}
							mines[randx][randy] = 1;
						
						}
					}
				}
			
				while(mines[randx][randy]==1) {
					 randx = (int)(Math.random()*dimR);
					 randy = (int)(Math.random()*dimC);
					 
					 
				}
				
				mines[randx][randy] = 1;
		
			
			}
		int c =0;
		for(int i=0;i<dimR;i++) {
			for(int j =0;j<dimC;j++) {
				
				if(mines[i][j]==1) {
					c++;
				}
			}
		}
		System.out.println(c);
		
		mines[a][b] = 0;
		revalidate();
		
	}
	public void flagPosition() {
		
		for(int i=0;i<dimR;i++) {
			for(int j=0;j<dimC;j++) {
				flags[i][j] = 0;
				if(mines[i][j]==1) {
					flags[i][j]=2;//Mine at that position
				}
				
			}
		}
		revalidate();
	}
	
	public void checkMines() {
		checkmines = new int [2][minenum];
	}
	public void expansion(int x,int y) {
		int c = 0;
		
		
		if(flags[x][y]!=2) {
			
			try {
				if(x-1>-1&&y-1>-1) {
					if(flags[x-1][y-1]==2) {
						c++;
					}
				}
				if(y-1>-1) {
					if(flags[x][y-1]==2) {
						c++;
					}
				}
				if(x+1<dimR&&y-1>-1) {
					if(flags[x+1][y-1]==2) {
						c++;
					}
				}
				if(x+1<dimR) {
					if(flags[x+1][y]==2) {
						c++;
					}
				}
				if(x+1<dimR&&y+1<dimC) {
					if(flags[x+1][y+1]==2) {
						c++;
					}
				}
				if(y+1<dimC) {
					if(flags[x][y+1]==2) {
						c++;
					}
				}
				if(x-1>-1&&y+1<dimC) {
					if(flags[x-1][y+1]==2) {
						c++;
					}
				}
				if(x-1>-1) {
					if(flags[x-1][y]==2) {
						c++;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(c>0) {
			
			togglers[x][y].setText(String.valueOf(c));
			flags[x][y] = 3; //num in that position
		}
		else if (c==0) {
			
			try {
				for(int i=x-1;i<=x+1;i++) {
					
					for(int j=y-1;j<=y+1;j++) {
						if(!togglers[i][j].isSelected()) {
							togglers[i][j].setSelected(true);
							expansion(i,j);
							flags[i][j] = 3;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("!!");
			}
		}
		

		
	}
	
	/*public void MyTimer()
    {
        TimerTask task; 
    
        task = new TimerTask() 
        {

        @Override
        public void run() 
        { 
            if((!win)&&(!fclick)) 
            {
                System.out.println("Seconds = " + tcount);
                tcount++;
                time.setText(tcount+"");
            } else 
            {
                // stop the timer
                cancel();
                tcount = 0;
            }
        }
        };
       
    }*/class UpdateTimer extends TimerTask {
    	public void run() {
    		if(!end){
    			tcount++;
    			time.setText("Time: "+tcount);
    		}
    		
    	}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MineSweeper app = new MineSweeper();
	}

}
