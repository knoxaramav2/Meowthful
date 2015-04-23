package graphics;

import gameElements.Player;
import gameElements.Pokemon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattleChoicePanel extends JPanel implements ActionListener{
	private Pokemon   poke;
	private JButton   attack;
	private JButton   switchPokemon;
	private JButton[] attacks;
	private JButton[] party;
	private JButton   back;
	private int       viewMenu;
	
	public BattleChoicePanel(Pokemon p, Player player){
		setLayout(new GridLayout(2, 4));
		
		poke = p;	
		viewMenu = 0;
		attacks = new JButton[poke.getAttackList().size()];
		party = new JButton[player.party.size()];
		
		attack = new JButton("Attack");
		attack.addActionListener(this);
		
		switchPokemon = new JButton("Switch");
		switchPokemon.addActionListener(this);
		
		back = new JButton("Back");
		back.addActionListener(this);
		
		for(int i = 0; i < poke.getAttackList().size(); i++){
			attacks[i] = new JButton(poke.getAttack(i).name);
			attacks[i].addActionListener(this);
		}
		
		for(int i = 0; i < party.length; i++) party[i] = new JButton(player.party.get(i).name);
		
		add(attack);
		add(switchPokemon);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == attack){
			removeAll();
			revalidate();
			repaint();
			for(int i = 0; i < attacks.length; i++) add(attacks[i]);
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
			for(int i = 0; i < party.length; i++) add(party[i]);
			add(back);
			viewMenu = 2;
		}else if(viewMenu == 1){
			for(int i = 0; i < attacks.length; i++){
				if(e.getSource() == attacks[i]){
					//Add attacking here
					return;
				}
			}
		}else if(viewMenu == 2){
			for(int i = 0; i < party.length; i++){
				if(e.getSource() == party[i]){
					//Add party switch here
					return;
				}
			}
		}
	}
}