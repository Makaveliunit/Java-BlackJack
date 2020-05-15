

public class Game_algo {

	static void Bj_Bot(int n) {
		

		String choice = "S";
		//new beyond this line
		int num_of_hands = n;
		int dealer_win_count = 0;
		int player_win_count = 0;
		int player_dealt_bj = 0;
		int dealer_dealt_bj = 0;
		int push_count = 0;

		for (int i = 0; i < num_of_hands; i++) {
			Player player = new Player("Bot");
			dealer Dealer = new dealer();

			/*
			 * break main when hand is over and outcome is achieved
			 */
			
				Main: do {

					// deals dealer
					Dealer.deal();
					Dealer.deal();

					//deal player
					player.deal();
					player.deal();
					
					//if dealer gets blackjack
					if (Dealer.getcardvalue() == 21) {
					   if(player.GetCardValue() != 21) {
						   dealer_win_count++;
						   dealer_dealt_bj++;   
					   }
					   else {
						   push_count++;
						   dealer_dealt_bj++;
						   player_dealt_bj++; 
					   }
						break Main;
					}

					//if player gets blackjack
					if (player.GetCardValue() == 21) {
						player_win_count++;
						player_dealt_bj++;
						break Main;
					}


					do {

						
						if(Dealer.Get_1st_Card() >= 7) {
							if(player.GetCardValue() >= 17)
								choice = "S";
							else
								choice = "H";
						}
						
						
						if(Dealer.Get_1st_Card() < 7) {
							if(player.GetCardValue() <= 11)
								choice = "H";
							else
								choice = "S";
						}
						

						// while player is hitting draw a card
						if (choice.toUpperCase().equals("H")) {
							player.deal();
						}
						
					} while (player.GetCardValue() < 22 && choice.toUpperCase().equals("H"));
					
					//player busted
					if (player.GetCardValue() > 21) {
						dealer_win_count++;
						break Main;
					}
					
					
				    //dealer stands at 17
					while (Dealer.getcardvalue() < 17) {
						Dealer.deal();
					}

					
					//dealer busted
					if (Dealer.getcardvalue() > 21) {
						player_win_count++;
						break Main;
					}


					//verifies who won
					if (Dealer.getcardvalue() < player.GetCardValue()) {
						player_win_count++;
					}
					if (Dealer.getcardvalue() > player.GetCardValue()) {
						dealer_win_count++;
					}
					if (Dealer.getcardvalue() == player.GetCardValue())
						push_count++;
					
					//exit loop
					break Main;
				} while (true);
			
				Dealer.resetcardvalue();
				player.resetcardvalue();
			
			

		}
        System.out.print("Number of hands played = " + (dealer_win_count + player_win_count + push_count) + "\nDealer win count = " + 
		dealer_win_count + "\nPlayer win count = " + player_win_count + "\nPush count = " + push_count
        		+"\nDealer dealt blackjack = "+ dealer_dealt_bj + "\nPlayer dealt blackjack = " + player_dealt_bj +
		"\nHouse Win % = " + (double)dealer_win_count/(double)(dealer_win_count + player_win_count));
	}
	
	
	public static void main(String[] args) {
		
		Bj_Bot(5000);
		
	}
	
	
}
