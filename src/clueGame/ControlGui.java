package clueGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ControlGui extends JFrame {
	private final int WIDTH = 1000;
	private final int HEIGHT = 300;
	private JButton showNotes;
	private DetectiveNotes notes;
	private ClueGame game;
	
	public ControlGui(ClueGame game){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Control");
		setSize(WIDTH,HEIGHT);
		setLayout(new GridLayout(2,1));
		JPanel panel = createUpperPanel();
		add(panel);
		JPanel panel2 = createLowerPanel();
		add(panel2);
		this.game = game;
		
		notes = new DetectiveNotes(game);
		
	}
	
	private JPanel createUpperPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		JLabel turnLabel = new JLabel("Whose turn is it?");
		JTextField turn = new JTextField(20);
		JPanel turnPanel = new JPanel();
		turnPanel.setLayout(new BorderLayout());
		turnPanel.add(turnLabel, BorderLayout.CENTER);
		turnPanel.add(turn, BorderLayout.SOUTH);
		panel.add(turnPanel);
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccusation = new JButton("Make an accusation");
		showNotes = new JButton("Show Detective Notes");
		panel.add(nextPlayer);
		panel.add(makeAccusation);
		panel.add(showNotes);
		showNotes.addActionListener(new ButtonListener());
		return panel;
		
	}
	
	private JPanel createLowerPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		JPanel dice = new JPanel();
		JPanel guess = new JPanel();
		JPanel result = new JPanel();
		
		
		dice.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		JLabel roll = new JLabel("Roll     ");
		dice.add(roll);
		JTextField rolly = new JTextField(25);
		rolly.setEditable(false);
		dice.add(rolly);
		
		
		guess.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		JLabel g = new JLabel("Guess");
		guess.add(g);
		JTextField fullGuess = new JTextField(25);
		fullGuess.setEditable(false);
		guess.add(fullGuess);
		
		
		result.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		JLabel response = new JLabel("Response");
		result.add(response);
		JTextField res = new JTextField(25);
		res.setEditable(false);
		result.add(res);
		
		
		panel.add(dice);
		panel.add(guess);
		panel.add(result);
		return panel;
		
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == showNotes){
				System.out.println("HERE");
				notes.showStuff();
			}
		}	
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
