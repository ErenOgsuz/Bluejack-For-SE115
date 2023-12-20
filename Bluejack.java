import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BlueJack{
	
	private static final int MAX_GAMES = 10;
    private static final String FILE_PATH = "game_winners.txt";
	
	public static void main(String[] args){
		String[] winners = loadWinners();
		
		//cd OneDrive\Masaüstü\BluejackProject\Bluejack-For-SE115
		Decks.ConstructDecks();
		
		Game.GameRun();
		
		saveWinners(winners);
        printWinners(winners);
		
	}
}


    