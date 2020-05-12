import java.util.Random;

public class Player {

	private int cards[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 74, 81, 75, 65 };
	private int bank;
	private String playername;
	private int cardvalue = 0;
    private int card[] = new int[9];
	private static int i = 0;
    
	public Player() {
	}
	
	Random random = new Random();
	
	//starting instances
	public Player(String a) {
		playername = a;
		bank = 2000;
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
	    if (card[a] == 11 || card[a] == 1)
			System.out.print("A");
		}
	}
	
	public void WIN(int a) {
		this.bank += a;
	}
	
	public void LOST(int a) {
		this.bank -= a;
	}
	
	public void WINBJ(int a) {
		this.bank += (a*1.5);
	}
	
	//resets hand
	public void resetcardvalue() {
		this.cardvalue = 0;
		Player.i = 0;
	}
	
	//return total of hand
	public int GetCardValue() {
		return this.cardvalue;
	}
	
	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public String getPlayername() {
		return playername;
	}

	@Override
	public String toString() {
	return"Your Bank: "+bank +"$                         Player Name: " + playername;
	
	}
	
	
}
