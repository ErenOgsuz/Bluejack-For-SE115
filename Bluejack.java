import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.InputMismatchException;

public class BlueJack{
	
	private static Scanner sc = new Scanner(System.in);
	private static final int MAX_GAMES = 10;
    private static final String FILE_PATH = "game_winners.txt";
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	private static LocalDateTime now = LocalDateTime.now();
	private static String playerName = new String();
	
	public static String getPlayerName(){
		return playerName;
	}
	
	public static void main(String[] args){
		String[] scores = loadScores();
		
		System.out.println("");
		System.out.println("Play a new game(1) or Show previous games(2)");
		int a=0;
		while (true) {
            try {
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) {
					a = sc.nextInt();
					if(a>0 && a<= 2){
						break;
					}else{
						System.out.println("Invalid input. Please try again.");
					}
                } else {
                    System.out.println("Invalid input. Please try again.");
                    sc.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.next();
            }
        }
		
		System.out.println("");
		
		if(a == 1){
			System.out.println("What is the players name?");
			System.out.print("Enter your name: ");
			playerName = sc.next();
			System.out.println("");
			Decks.ConstructDecks();
			Game.GameRun();
			//String score = new String("Player:" + Game.GetPlayerWin() + " - " + "Computer:" + Game.GetCompWin() + ", " dtf.format(now));
			saveScore(scores);
		}else if(a == 2){
			printScores(scores);
		}
		
		//cd OneDrive\Masaüstü\BluejackProject\Bluejack-For-SE115
		
		
	}
	
	private static String[] loadScores() {
        String[] scores = new String[MAX_GAMES];
        try (Scanner reader = new Scanner(Paths.get(FILE_PATH))) {
            int i = 0;
            while (reader.hasNextLine() && i < MAX_GAMES) {
                scores[i++] = reader.nextLine();
            }
        }catch (IOException e) {
            System.out.println("No previous winners found. Starting fresh.");
        }
        return scores;
    }
	
	private static void printScores(String[] scores) {
        System.out.println("Last " + Math.min(MAX_GAMES, countScores(scores)) + " game winners:");
        for (String score : scores) {
            if (score != null) {
                System.out.println(score);
            }
        }
    }
	
	private static int countScores(String[] scores) {
        int count = 0;
        for (String score : scores) {
            if (score != null) {
                count++;
            }
        }
        return count;
    }
	
	private static void saveScore(String[] scores) {
		System.out.println("Saving game...");
		try {
			String[] newScores = new String[MAX_GAMES];
		
			for(int i = 0; i < scores.length-1; i++){
				newScores[i] = scores[i + 1];
			}

			newScores[Math.min(MAX_GAMES - 1, scores.length - 1)] = playerName + ":" + Game.GetPlayerWin() + " - " + "Computer:" + Game.GetCompWin() + ", " + dtf.format(now);

			FileWriter writer = new FileWriter(FILE_PATH);

			for (String score : newScores) {
				if (score != null) {
					writer.write(score + "\n");
				}
			}

			writer.close();
			System.out.println("Successfully saved the last game!");
		} catch (IOException e) {
			System.out.println("Error saving scores to file.");
		}
	}

}


    