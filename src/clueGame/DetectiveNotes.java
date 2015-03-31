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
	private JButton hideButton;
	private ArrayList<Card> deck;
		
	public DetectiveNotes(ClueGame game){
		setTitle("Detective Notes");
		setSize(600,660);
		//setLayout(new GridLayout(3,2));
		this.game = game;
		deck = game.getDeck();

		JPanel mainPanel = new JPanel();
		GridLayout grid = new GridLayout(0,2);
		mainPanel.setLayout(grid);
		
		JPanel people = createCheckBox(Card.CardType.PERSON);
		JPanel rooms = createCheckBox(Card.CardType.ROOM);
		JPanel weapons = createCheckBox(Card.CardType.WEAPON);
		JPanel personGuess = createComboBox(Card.CardType.PERSON);
		JPanel weaponGuess = createComboBox(Card.CardType.ROOM);
		JPanel roomGuess = createComboBox(Card.CardType.WEAPON);
		
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
	
	public JPanel createCheckBox(Card.CardType t) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), panel.getName()));
		for(Card c: deck) {
			if(c.getCardType() == t) {
				panel.add(new JCheckBox(c.getName()));
			}
		}
		return panel;
	}
	
	public JPanel createComboBox(Card.CardType t) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), panel.getName()));
		JComboBox box = new JComboBox();
		box.addItem("Uncertain");
		for(Card c: deck) {
			if(c.getCardType() == t) {
				box.addItem(c.getName());
			}
		}
		panel.add(box);
		return panel;
	}
	
	public void showStuff(){
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
