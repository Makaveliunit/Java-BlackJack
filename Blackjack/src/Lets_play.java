import java.util.InputMismatchException;
import java.util.Scanner;

public class Lets_play {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		String choice;
		String Retry = "Y";
		int Hits = 0;
		int Pull = 0;
		int Bet = 0;

		do {
			System.out.println("********Game developer: Rami.A\n********Welcome To The BlackJack Table*******");

			System.out.print("\nPlease Enter your name to begin -->: ");

			Player player = new Player(userInput.next());
			dealer Dealer = new dealer();

			System.out.println("\n\nThank you " + player.getPlayername()
					+ ", you will be starting with 2000$ in your bank...Let the game begin!");

			while (player.getBank() != 0) {
				Main: do {
					do {
						try {
							System.out.print("\nBank: " + player.getBank() + "$");
							System.out.print("\nPlease place your Bet!  \nBET: ");
							Bet = (int) userInput.nextDouble();
						} catch (InputMismatchException e) {
							System.out.print("Did you just try to bet a letter??");
							Bet = -1;
						}
						userInput.nextLine();
						if (Bet > player.getBank() || Bet < 1)
							System.out.print("\nPlease Put a Valid Bet!\n");
					} while (Bet > player.getBank() || Bet < 1);
					System.out.println("===========================================================");
					System.out.print(player.toString() + "\n\n*******Dealer Hand*******\n");
					// deals dealer
					Dealer.deal();
					Dealer.deal();
					// shows only one dealer card
					if (Dealer.getcardvalue() == 21) {
						System.out.print("\nDealer Has BlackJack!\n\n");
						Dealer.GetCard(0);
						System.out.print(" , ");
						Dealer.GetCard(1);
						System.out.print("\n");
						player.LOST(Bet);
						break Main;
					}

					Dealer.GetCard(0);
					System.out.print(" , **HIDDEN**");
					// player hand
					System.out.print("\n\n*******Your Hand*******\n");
					player.deal();
					player.deal();
					player.GetCard(0);
					System.out.print(" , ");
					player.GetCard(1);
					if (player.GetCardValue() == 21) {
						System.out.print("\nCongrats, You have a BlackJack!\n\n");
						player.WINBJ(Bet);
						break Main;
					}

					// tells player hand total
					System.out.print("\n\nYour total is : " + player.GetCardValue());

					do {
						if (Hits == 0 && Bet * 2 < player.getBank()) {
							System.out.print(
									"\n\nPlease make your move by inputing a letter.\n'H' for hit\n'S' for stand\n'D' for double\n-->");
						} else
							System.out.print(
									"\n\nPlease make your move by inputing a letter.\n'H' for hit\n'S' for stand\n-->");
						choice = userInput.next();
						if (choice.toUpperCase().equals("D") && Hits == 0 && Bet * 2 < player.getBank()) {
							System.out.println("");
							Hits++;
							Bet *= 2;
							player.deal();
							System.out.print("\n\n*******Your Hand*******\n");
							player.GetCard(0);
							System.out.print(" , ");
							player.GetCard(1);
							System.out.print(" , ");
							player.GetCard(2);
							System.out.print("\nYour total is : " + player.GetCardValue());
							choice = "S";
						}
						// while player is hitting draw a card
						if (choice.toUpperCase().equals("H")) {
							System.out.println("");
							Hits++;
							player.deal();
							System.out.print("\n\n*******Your Hand*******\n");
							player.GetCard(0);
							System.out.print(" , ");
							player.GetCard(1);
							// displays all cards
							for (int i = 0; i < Hits; i++) {
								System.out.print(" , ");
								player.GetCard(i + 2);
							}
							System.out.print("\nYour total is : " + player.GetCardValue());
						}
					} while (player.GetCardValue() < 22 && choice.toUpperCase().equals("H")
							|| (!choice.toUpperCase().equals("H") && !choice.toUpperCase().equals("S")));
					if (player.GetCardValue() > 21) {
						// he lost so break
						System.out.print("\nYou busted!\n\n");
						player.LOST(Bet);
						break Main;
					}
					while (Dealer.getcardvalue() < 17) {
						Dealer.deal();
						Pull++;
					}
					System.out.print("\n\n*******Dealer Hand*******\n");
					Dealer.GetCard(0);
					System.out.print(" , ");
					Dealer.GetCard(1);
					// displays all cards
					for (int i = 0; i < Pull; i++) {
						System.out.print(" , ");
						Dealer.GetCard(i + 2);
					}
					if (Dealer.getcardvalue() > 21) {
						System.out.print("\nDealer's Total: " + Dealer.getcardvalue() + " *DEALER BUSTS YOU WIN*\n\n");
						player.WIN(Bet);
						break Main;
					}
					System.out.print("\nDealer's Total: " + Dealer.getcardvalue());
					System.out.print("\n\n*******Your Hand*******\n");
					player.GetCard(0);
					System.out.print(" , ");
					player.GetCard(1);
					// displays all cards
					for (int i = 0; i < Hits; i++) {
						System.out.print(" , ");
						player.GetCard(i + 2);
					}
					System.out.print("\nYour Total: " + player.GetCardValue());

					if (Dealer.getcardvalue() < player.GetCardValue()) {
						System.out.print("\nCongratulations!, You win\n\n");
						player.WIN(Bet);
					}
					if (Dealer.getcardvalue() > player.GetCardValue()) {
						System.out.print("\nWoops, House Wins\n\n");
						player.LOST(Bet);
					}
					if (Dealer.getcardvalue() == player.GetCardValue())
						System.out.print("\nIt's a Tie!\n\n");
					break Main;
				} while (true);
				Hits = 0;
				Pull = 0;
				Dealer.resetcardvalue();
				player.resetcardvalue();
			}
			System.out.print("\n\nWell Well Well, Looks like you went broke!\n\nWant to try again? Y/N\n-->: ");
			do {
				Retry = userInput.next().toUpperCase();
				if (Retry.equals("Y") || Retry.equals("N"))
					break;
			} while (true);
			player.setBank(2000);
			System.out.print("\n\n");
		} while (Retry.equals("Y"));
		userInput.close();
		System.exit(0);
	}

}
