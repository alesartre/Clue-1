package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class DetectiveNotes extends JDialog {
	private ClueGame game;
	private Set<Card> weps;
	private Set<Card> peeps;
	private Set<Card> roomies;
	private JButton hideButton;
	
	
	public DetectiveNotes(ClueGame game){
		setTitle("Detective Notes");
		setSize(600,660);
		//setLayout(new GridLayout(3,2));
		this.game = game;
		
		//Separate the deck into three smaller sets of weapon, people, and room cards
		//to use to make different parts of the detective notes
		ArrayList<Card> deck = game.getDeck();
		weps = new HashSet<Card>();
		peeps = new HashSet<Card>();
		roomies = new HashSet<Card>();
		for(Card c : deck){
			if(c.getCardType()== CardType.WEAPON){
				weps.add(c);
			}
			else if(c.getCardType()==CardType.PERSON){
				peeps.add(c);
			}
			else if(c.getCardType()==CardType.ROOM){
				roomies.add(c);
			}
		}
		JPanel mainPanel = new JPanel();
		GridLayout grid = new GridLayout(0,2);
		mainPanel.setLayout(grid);
		JPanel people = createPeople();
		JPanel weapons = createWeapons();
		JPanel rooms = createRooms();
		JPanel personGuess = createPersonGuess();
		JPanel weaponGuess = createWeaponGuess();
		JPanel roomGuess = createRoomGuess();
		hideButton = new JButton("HIDE");
		hideButton.addActionListener(new ButtonListener());
		
		
		
		mainPanel.add(people);
		mainPanel.add(personGuess, BorderLayout.NORTH);
		mainPanel.add(weapons);
		mainPanel.add(weaponGuess);
		mainPanel.add(rooms);
		mainPanel.add(roomGuess);
		add(mainPanel, BorderLayout.CENTER);
		add(hideButton, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	
	private JPanel createPeople(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		for( Card p: peeps){
			panel.add(new JCheckBox(p.getName()));
		}
		return panel;
		
	}
	
	private JPanel createWeapons(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		for(Card c: weps){
			panel.add(new JCheckBox(c.getName()));
		}
		return panel;
		
	}
	
	private JPanel createRooms(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		for(Card c: roomies){
			panel.add(new JCheckBox(c.getName()));
		}
		return panel;
	}
	
	private JPanel createPersonGuess(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		JComboBox<String> combo = new JComboBox<String>();
		for(Card c: peeps){
			combo.addItem(c.getName());
		}
		panel.add(combo);
		return panel;
	}
	
	private JPanel createWeaponGuess(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons Guess"));
		JComboBox<String> combo = new JComboBox<String>();
		for(Card c: weps){
			combo.addItem(c.getName());
		}
		panel.add(combo);
		return panel;
		
	}
	
	private JPanel createRoomGuess(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "RoomGuess"));
		JComboBox<String> combo = new JComboBox<String>();
		for(Card c: peeps){
			combo.addItem(c.getName());
		}
		panel.add(combo);
		return panel;
	}
	
	public void showstuff(){
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == hideButton){
				setVisible(false);
			}
			
		}
		
	}
}
