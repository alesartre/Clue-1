import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ControlGui extends JFrame {
	
	
	public ControlGui(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Control");
		setSize(1000,300);
		setLayout(new GridLayout(2,1));
		JPanel panel = createUpperPanel();
		add(panel);
		JPanel panel2 = createLowerPanel();
		add(panel2);
		
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
		panel.add(nextPlayer);
		panel.add(makeAccusation);
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
	
	
	
	
	public static void main(String[] args){
		
		ControlGui gui = new ControlGui();
		gui.setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
