import java.util.Random; 

public class dealer {

	private int cards[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 74, 81, 75, 65 };
	private int cardvalue = 0;
    private int card[] = new int[9];
	private static int i = 0;
	
	Random random = new Random();

	public dealer() {
	}

	//deals the card and adds the sum
	public void deal() {
		card[i] = cards[random.nextInt(13)];
	    setCardCalc(card[i]);
		i++;
		if(cardvalue > 21)
			for(int i = 0; i < card.length-1; i++) {
			ace: if(card[i] == 65) {
				card[i] = 1;
					this.cardvalue -=10;
			break ace;	
			}
			}
	}

	
	//calculates the card and adds to the hand
	public void setCardCalc(int cardval) {
		int realvalue = cardval;
		switch (cardval) {
		case 65:
			if(cardvalue > 10) {
				  card[i] = 1;
				realvalue = 1;
			break;	}
			else	
			realvalue = 11;
			break;
		case 74:
			realvalue = 10;
			break;
		case 81:
			realvalue = 10;
			break;
		case 75:
			realvalue = 10;
			break;
		default:
			realvalue = cardval;
		}
		cardvalue += realvalue;
	}

	//resets hand
	public void resetcardvalue() {
		this.cardvalue = 0;
	   dealer.i = 0;
	}

	//returns the card
	public void GetCard(int a) { 
		if (card[a] < 11)
			if(card[a] == 1)
				System.out.print("A");
			else
		System.out.print(card[a]);
		if (card[a] > 11) {
	  char FACE = (char)(card[a]);
		System.out.print(FACE);		
		if (card[a] == 11)
			System.out.print("A");
		}
	}
	
	//returns dealer first card value
	public int Get_1st_Card() { 
		if (card[0] < 11)
			if(card[0] == 1)
				return 11;
			else
				return card[0];
		if (card[0] > 11) {
				return 10;
		}
		return 0;
	}
	
	//returns hand total value
	public int getcardvalue() {
		return cardvalue;
	}
	

	
}
