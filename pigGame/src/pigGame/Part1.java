package pigGame;
import javax.swing.JOptionPane;
//By Alex Everitt
//Roll two dice each turn. 
//After the turn check if a dice is 1- If both dice are 1 it sets overall score to 0
//If both are the same and not 1s player must roll again.
//Else allow player to either roll again or hold and add score to their total

//To do: 1.Change rolls to be shown as text not #
//2. Change roll again in joption to have "roll" and "hold" as opposed to yes and no
//3. Add: if a dice is 1 then end turn and reset turn score to 0

public class Part1 {
	static int playerTotal=0;
	static int compTotal=0;
	static int lastRoll=0;
	public static final String title="Pig by: Alex Everitt";
	
	
	public static void main(String[] args) {
	//This function allows the player to decide if they want to play then calls the gameLoop or exits
		int option=0;
		
		option = JOptionPane.showConfirmDialog(null, "Welcome to the Game of Pig!\n\n" + "The Rules are as follows: \n" + "\t -Each turn two six-sided die will be rolled \n" + "\t -The sum of the die will be added to the score and the player can decide whether they want to roll again \n"+ "\t Unless both die are the same:\n"+"\t \t \t If both are 1's then the player's score will be reset to 0\n"+"\t \t \t If the die are the same then the player must roll again\n" + "The game continues with alternating turns until either player reaches 100 points and they are crowned winner\n\n"+"Would you like to play?","Game of Pig by: Alex Everitt" ,JOptionPane.YES_NO_CANCEL_OPTION);
		if (option==JOptionPane.YES_OPTION)
			gameLoop();
		else
			System.exit(0);
	}
	
	static void gameLoop(){
		//this will continue to run until someone wins, at which point it returns the final score
		int playerTotal=0;
		int compTotal=0;
		while(playerTotal<100 && compTotal<100){
			playerTotal=playerTurn(playerTotal,compTotal);
			if(playerTotal<100){
				compTotal=compTurn(playerTotal,compTotal);
			}
		}
		if(playerTotal>compTotal){
			JOptionPane.showMessageDialog(null, " Congratulations, you win!!!" + "\n The final score was:\n Player: "+playerTotal+" Computer: "+compTotal,title ,JOptionPane.DEFAULT_OPTION );
		}
		else{
			JOptionPane.showMessageDialog(null, "You lose!!! :(" + "\n The final score was:\n Player: "+playerTotal+" Computer: "+ compTotal,title ,JOptionPane.DEFAULT_OPTION );
		}
	}
	
	static int playerTurn(int playerScore, int compScore){
		//This function is called every player turn and will run until an exit condition is met. It returns the revised player score after the loop
		int rollTotal=0;
		int pturn=1;
		int option=0;
		int turnTotal=0;
		
		while(pturn==1 && (turnTotal+playerScore)<100){
			int[] lastRoll = diceRoll();
			int dice_1=lastRoll[0];
			int dice_2=lastRoll[1];
			rollTotal=dice_1+dice_2;
			turnTotal=turnTotal+rollTotal;
			
		if(dice_1==1||dice_2==1){
			if(dice_1==dice_2){
				playerScore=0;
				JOptionPane.showMessageDialog(null, " You rolled two ones :( !!! \n Your score has been reset to 0 and your turn is over"+"\n The current score is: \n Player: "+ (playerScore)+ "\t Computer: "+ compScore,title ,JOptionPane.DEFAULT_OPTION);
				pturn=0;
			}
			else{
				JOptionPane.showMessageDialog(null," You rolled "+ dice_1 + " and " + dice_2 + "\n Your turn is over and you've lost your turn score! :(\t\t\t\t\t\t"+"\n The current score is:\n Player: "+ (playerScore)+ "\t Computer: "+ compScore,title,JOptionPane.DEFAULT_OPTION);
				turnTotal=0;
					pturn=0;
			}
		}
		else if(dice_1==dice_2){
			JOptionPane.showMessageDialog(null," You rolled "+ dice_1 + " and " + dice_2 + "\n You must roll again!\t\t\t\t\t"+"\n Your score so far this turn is: "+turnTotal+"\n The current score is:\n Player: "+ (playerScore)+ "\t Computer: "+ compScore,title ,JOptionPane.DEFAULT_OPTION);
			if(playerScore>=100)
				pturn=0;
			//pturn stays at 1
		}
		else{
			option= JOptionPane.showOptionDialog(null," You rolled "+ dice_1 + " and " + dice_2 + "\n Your score so far this turn is: "+turnTotal+"\n The current score is:\n Player: "+ (playerScore)+ "\t Computer: "+ compScore,title,JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,new String[]{"Roll", "Hold"}, "default");
			if(playerScore>=100)
				pturn=0;
			else if(option==0){
				//pturn stays at 1
			}
			else{
				playerScore=playerScore+turnTotal;
				pturn=0;
			}
		}
		}
		return playerScore;
	}
	
	static int compTurn(int playerScore, int compScore){
		int rollTotal=0;
		int turnTotal=0;
		int turnsTaken=0;
		int cturn=1;		

		while(cturn==1 && compScore<100 && turnsTaken<4 && turnTotal<30){
			JOptionPane.showMessageDialog(null, "The computer will roll");
			int[] lastRoll = diceRoll();
			int dice_1=lastRoll[0];
			int dice_2=lastRoll[1];
			rollTotal=dice_1+dice_2;
			turnTotal=turnTotal+rollTotal;
			
		if(dice_1==1||dice_2==1){
			if(dice_1==dice_2){
				compScore=0;
				JOptionPane.showMessageDialog(null, " Computer rolled two ones :D !!! Their score has been reset to 0 and their turn is over"+ "\n The current score is: \n Player: "+ playerScore+ "\t Computer: "+ compScore,title ,JOptionPane.DEFAULT_OPTION);
				cturn=0;
			}
			else{
				turnTotal=0;
				JOptionPane.showMessageDialog(null," Computer rolled "+ dice_1 + " and " + dice_2 + "\n Their turn is over and they lost their turn score!"+ "\n The current score is: \n Player: "+ playerScore+ "\t Computer: "+ compScore,title ,JOptionPane.DEFAULT_OPTION );
				cturn=0;
			}
		}
		else if(dice_1==dice_2){
			JOptionPane.showMessageDialog(null," Computer rolled "+ dice_1 + " and " + dice_2 + "\n They must roll again!"+ "\n Their score so far this turn is: "+turnTotal+"\n The current score is: \n Player: "+ playerScore+ "\t Computer: "+ compScore,title ,JOptionPane.DEFAULT_OPTION );
			if(turnsTaken==4){
				//do nothing
			}
			else{
				turnsTaken++;
			}
		}
		else{
			JOptionPane.showMessageDialog(null," Computer rolled "+ dice_1 + " and " + dice_2 + "\n Their score so far this turn is: "+turnTotal+"\n The current score is: \n Player: "+ playerScore+ "\t Computer: "+ compScore,title ,JOptionPane.DEFAULT_OPTION);
			turnsTaken++;
			if((compScore+turnTotal)>=100)
				cturn=0;
		}
		}
		JOptionPane.showMessageDialog(null, " The computer's turn is now done",title,JOptionPane.DEFAULT_OPTION);
		compScore=compScore+turnTotal;
		return compScore;
	}

	static int[]  diceRoll(){
		//this function controls every roll and is called by the player and computer turns
		int roll_1=(int) (1+ (Math.random()*6));
		int roll_2=(int) (1+ (Math.random()*6));
		
		int diceRollTurn[] = {roll_1, roll_2};
		return diceRollTurn;
		
	}
	
}
