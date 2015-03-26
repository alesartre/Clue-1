package clueGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class DetectiveNotes extends JDialog {
	private ClueGame game;
	public DetectiveNotes(ClueGame game){
		setTitle("Detective Notes");
		setSize(600,650);
		setLayout(new GridLayout(3,2));
		this.game = game;
		JPanel people = createPeople();
		JPanel weapons = createWeapons();
		JPanel rooms = createRooms();
		JPanel personGuess = createPersonGuess();
		JPanel weaponGuess = createWeaponGuess();
		JPanel roomGuess = createRoomGuess();
		
		add(people);
		add(personGuess);
		add(weapons);
		add(weaponGuess);
		add(rooms);
		add(roomGuess);
		
		setVisible(true);
		
	}
	
	private JPanel createPeople(){
		ArrayList<Player> players = game.getPeople();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		for( Player p: players){
			panel.add(new JCheckBox(p.getName()));
		}
		return panel;
		
	}
	
	private JPanel createWeapons(){
		ArrayList<Card> deck = game.getDeck();
		Set<Card> weapons = new HashSet<Card>();
		for(Card c : deck){
			if(c.getCardType()== CardType.WEAPON){
				weapons.add(c);
			}
		}
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		for(Card c: weapons){
			panel.add(new JCheckBox(c.getName()));
		}
		return panel;
		
	}
	
	private JPanel createRooms(){
		ArrayList<Card> deck = game.getDeck();
		Set<Card> rooms = new HashSet<Card>();
		for(Card c : deck){
			if(c.getCardType()== CardType.ROOM){
				rooms.add(c);
			}
		}
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		for(Card c: rooms){
			panel.add(new JCheckBox(c.getName()));
		}
		return panel;
	}
	
	private JPanel createPersonGuess(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		return panel;
	}
	
	private JPanel createWeaponGuess(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons Guess"));
		return panel;
		
	}
	
	private JPanel createRoomGuess(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "RoomGuess"));
		return panel;
	}
	
	public void showstuff(){
		//setVisible(true);
	}
	
	
}
