package graphics;

import gameElements.Attack;
import gameElements.Pokemon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class BattleChoicePanel extends JPanel implements ActionListener{
	private Pokemon   			poke;
	private JButton   			attack;
	private JButton   			switchPokemon;
	private JButton[]			attacks;
	private JButton[] 			party;
	private JButton  			back;
	private int       			viewMenu;
	private unownInterpreter  	ui;
	
	public BattleChoicePanel(unownInterpreter ui){
		setLayout(new GridLayout(2, 4));

		this.ui=ui;
		
		poke = null;
		
		viewMenu = 0;
		attacks = null;
		party = null;
		
		attack = new JButton("Attack");
		attack.addActionListener(this);
		
		switchPokemon = new JButton("Switch");
		switchPokemon.addActionListener(this);
		
		back = new JButton("Back");
		back.addActionListener(this);
				
		add(attack);
		add(switchPokemon);
	}
	
	public void initButtons(ArrayList<Attack> attacks, ArrayList<Pokemon> party){
		this.attacks = new JButton[attacks.size()];
		this.party = new JButton[party.size()];
		
		for(int i = 0; i < this.attacks.length; i++){
			this.attacks[i] = new JButton(attacks.get(i).getName());
			this.attacks[i].addActionListener(this);
		}
		
		for(int i = 0; i < this.party.length; i++){
			this.party[i] = new JButton(party.get(i).name);
			this.party[i].setActionCommand(""+party.get(i).id);
			this.party[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == attack){
			removeAll();
			revalidate();
			repaint();
			if(attacks != null) for(int i = 0; i < attacks.length; i++) add(attacks[i]);
			add(back);
			viewMenu = 1;
		}else if(e.getSource() == back){
			removeAll();
			revalidate();
			repaint();
			add(attack);
			add(switchPokemon);
			viewMenu = 0;
		}else if(e.getSource() == switchPokemon){
			removeAll();
			revalidate();
			repaint();
			if(party != null) for(int i = 0; i < party.length; i++) add(party[i]);
			add(back);
			viewMenu = 2;
		}else if(viewMenu == 1){
			for(int i = 0; i < attacks.length; i++){
				if(e.getSource() == attacks[i]){
					ui.interpret("attack "+attacks[i].getText());
					return;
				}
			}
		}else if(viewMenu == 2){
			for(int i = 0; i < party.length; i++){
				if(e.getSource() == party[i]){
					String s = new String(party[i].getActionCommand());
					ui.interpret("swapParty "+"0 "+party[i].getActionCommand());
					return;
				}
			}
		}
	}

	public void reset()
	{
		removeAll();
		add(attack);
		add(switchPokemon);
		revalidate();
		repaint();
		viewMenu = 0;
	}
}