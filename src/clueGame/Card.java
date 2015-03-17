package clueGame;

public class Card {
	private String name;
	public enum CardType {PERSON, WEAPON, ROOM};
	private CardType cardType;
	
	public Card(){
		super();
	}
	
	public Card(char type, String name){
		this.name = name;
		switch (type){
		case 'W':	cardType = CardType.WEAPON;
					break;
		case 'P': 	cardType = CardType.PERSON;
					break;
		case 'R': 	cardType = CardType.ROOM;
					break;
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	
	
	@Override
	public String toString() {
		return "Card [name=" + name + ", cardType=" + cardType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardType != other.cardType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
