package views;

import java.awt.Component;  
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.Champion;

import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;


public class Mainview extends JFrame{
	 private Game game;
	 private Effect effect;

	 private playernamePanel playerPanel;
	 private String pl1;
	 private String pl2;
	 private championdesignpanel championpane;
	 private gamePane gamepane;
	private JTextField numberJTextField;
	public Mainview() {
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setSize(1500, 800);
		playerPanel = new playernamePanel(this);
		this.getContentPane().add(playerPanel);
		this.setTitle("Marvel:Ultimate War");
		//this.setSize(800,600);
		ImageIcon image=new ImageIcon("54677.jpg");
		Image img = image.getImage();
		Image te=img.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		image = new ImageIcon(te);
		JLabel bl= new JLabel(image);
		bl.setLayout(null);
		bl.setBounds(0, 0, 500, 400);
					

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	

	public Effect getEffect() {
		return effect;
	}
	
	public void goChampionPanel(String s1, String s2) {
		this.getContentPane().remove(playerPanel);
		pl1=s1;
		pl2=s2;
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);

		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");

			
			this.setSize(1500,900);
			this.remove(playerPanel);


			championpane = new championdesignpanel(this);
			this.getContentPane().add(championpane);
			
			

			
			
		} catch(IOException e ) {
			e.printStackTrace();
			
		}
		this.revalidate();
		this.repaint();
	} 
	public  Game getGame() {
		return game;
	}

	public static String[] listofchamp() {
		int size = Game.getAvailableChampions().size();

		String[] r = new String[size + 1 ];
		r[0] = "pick your Champion";
		for(int i =0;i<size;i++){
			Champion index= Game.getAvailableChampions().get(i);
			r[i+1] = index.getName();
		}
		return r;
		
			
		}
	public void goGame(int i,int j,int k,int l,int m, int n, int fl, int sl) {
	
		this.remove(championpane);
		this.setSize(1500,850);
		

		Player p1 = new Player(pl1);
		p1.getTeam().add(Game.getAvailableChampions().get(i));
		p1.getTeam().add(Game.getAvailableChampions().get(j));
		p1.getTeam().add(Game.getAvailableChampions().get(k));
		p1.setLeader(Game.getAvailableChampions().get(fl));
		
		Player p2 = new Player(pl2);
		p2.getTeam().add(Game.getAvailableChampions().get(l));
		p2.getTeam().add(Game.getAvailableChampions().get(m));
		p2.getTeam().add(Game.getAvailableChampions().get(n));
		p2.setLeader(Game.getAvailableChampions().get(sl));
		game = new Game(p1,p2);
		
		gamepane = new gamePane(this);
		this.getContentPane().add(gamepane);
		
		this.revalidate();
		this.repaint();
		
	}
	public void goGametemp(ArrayList<String> r,int fl,int sl) {
		this.remove(championpane);

		Player p1 = new Player(pl1);
		int i =0;
		for( i=0;i<3;i++) {
			
		p1.getTeam().add(Game.getAvailableChampions().get(i));
		
		if(i==3)
			break;
		}
		p1.setLeader(Game.getAvailableChampions().get(fl));

		Player p2 = new Player(pl2);
		 i =4;
		for(i=4;i<=6;i++) {
			p2.getTeam().add(Game.getAvailableChampions().get(i));
			if(i==7)
				break;
			}
		p2.setLeader(Game.getAvailableChampions().get(sl));
		 game = new Game(p1,p2);
		 gamepane = new gamePane(this);
		 this.getContentPane().add(gamepane);
		 
		 this.revalidate();
		 this.repaint();
	}
	
	public int getCurrIndex() {
		int index = 0;
		for(int i = 0; i < game.getAvailableChampions().size(); i++) {
			if(game.getCurrentChampion() == game.getAvailableChampions().get(i)) {
				index = i;
			}
		}
		return index;
	}
	public int getChampIndex(String s) {
		int index = 0;
		for(int i = 0; i < game.getAvailableChampions().size(); i++) {
			if(s.equals(game.getAvailableChampions().get(i).getName())){
				index = i;
			}
		}
		return index;
	}


	public String toStringCh(int champIndex) {

			
		Champion ch=(Game.getAvailableChampions().get(champIndex));
		String res = " ";
			res= "Champion's name : " + ch.getName()+ "\n" + " Current health points : "+ ch.getCurrentHP()+"\n" + "Type :" +ch.getCondition()+" Mana: " + ch.getMana()+"\n"
					+ "AttackDamage : "+ch.getAttackDamage() +"\n" + "AttackRange: " + ch.getAttackRange()+ "\n"+
					 "Actions: " + ch.getCurrentActionPoints()+" \n";
			
		
		for(Ability ab: ch.getAbilities()) {
			res+=   ")" + ab.getName()+ "\n";
			
		}
	
		for(Effect e: ch.getAppliedEffects()) {
			res+= "effect name:" + e.getName()+"\n" + "Duration: " + e.getDuration() +"\n";
		}
		
		return res;
		
	
	}

	public String effect(int champIndex) {
		String res ="";
		Champion ch = Game.getAvailableChampions().get(champIndex);
		for(Effect e: ch.getAppliedEffects()) {
			res+= "effect name:" + e.getName()+"\n" + "Duration: " + e.getDuration() +"\n";
		}
		return res;
	}
	
	public String ability() {
		String res ="";
			for(Ability ab: Game.getAvailableAbilities()) {
			res+="Ability Name:" + ab.getName()+"\n" +"Type: "+getType(ab)+"\n"+ "Area Of effect :" +ab.getCastArea()+" CastRange: " + ab.getCastRange()+"\n"+" Mana Costs : " + ab.getManaCost()+"\n" +"Action Costs : "+ ab.getRequiredActionPoints()+"\n" +
					 "Current cooldowns : "+ab.getCurrentCooldown() +"\n" + "Base cooldowns : " + ab.getBaseCooldown()+ "\n"+
					 "Actions: " ;
					if(ab instanceof DamagingAbility) {
						res+= ((DamagingAbility) ab).getDamageAmount()+ " getDamageAmount" +"\n";
					}
					else if(ab instanceof HealingAbility) {
						res+= ((HealingAbility) ab).getHealAmount()+ " HealDamage" +"\n";
					}
					else if(ab instanceof CrowdControlAbility) {
						
							res+= ((CrowdControlAbility) ab).getEffect() + "EffectType"+"\n";
						}
					}
		
		return res;
	}
	
	public String getType(Ability a) {
		String r =" ";
		if(a instanceof DamagingAbility) {
			r = "DamagingAbility";
		}
		else if((a instanceof HealingAbility))
				r= "HealingAbility";
		else if((a instanceof CrowdControlAbility )){
			r="CrowdControlAbility";
		}
		else 
			r =null;
		return r;
	

}
	public String allinfoforcurrent() {
		String s = "Current Player;";
		Champion cc = game.getCurrentChampion();
		if(game.getSecondPlayer().getTeam().contains(cc)) {
			s+= " current player "+ "\n" + game.getSecondPlayer().getName()+"\n"+"\n";
		}
		else {
			s+= " current player"+ "\n" + game.getFirstPlayer().getName()+"\n" + "\n";

		}
		
		
		Champion c = game.getCurrentChampion();
		s+= "Current Champion information: " +"\n"+ "Name"+c.getName()+"\n" + " Current health points : "+ c.getCurrentHP()+"\n" + "Type :" +c.getCondition()+" Mana: " + c.getMana()+"\n"
				+ "AttackDamage : "+c.getAttackDamage() +"\n" + "AttackRange: " + c.getAttackRange()+ "\n"+
				 "Actions: " + c.getCurrentActionPoints()+" \n";
		
		for(Ability a: c.getAbilities()) {
			s+=  "Ability Name:" + a.getName()+"\n" +"Type: "+getType(a)+"\n"+ "Area Of effect :" +a.getCastArea()+" CastRange: " + a.getCastRange()+"\n"+" Mana Costs : " + a.getManaCost()+"\n" +"Action Costs : "+ a.getRequiredActionPoints()+"\n" +
					 "Current cooldowns : "+a.getCurrentCooldown() +"\n" + "Base cooldowns : " + a.getBaseCooldown()+ "\n"+
					 "Actions: " ;
					if(a instanceof DamagingAbility) {
						s+= ((DamagingAbility) a).getDamageAmount()+ " is the DamageAmount" +"\n";
					}
					else if(a instanceof HealingAbility) {
						s+= ((HealingAbility) a).getHealAmount()+ " is the HealDamage" +"\n";
					}
					else if(a instanceof CrowdControlAbility) {
						
							s+= ((CrowdControlAbility) a).getEffect() + "is the EffectType"+"\n";
						}
					}
		for(Effect e: c.getAppliedEffects()) {
			s+= "effect name:" + e.getName()+"\n" + "Duration: " + e.getDuration() +"\n";
		}
		return s;
		
		
	}
	public static void main(String[] args) throws Exception{
	       Mainview s = new Mainview();
	       URL file = new URL("https://medea-music.com/portfolio-item/the-avengers-theme-a-silvestri/the-avengers-theme-song/");

	        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
	        Clip clip = AudioSystem.getClip();
	        clip.open(ais);

	        JButton button = new JButton("Play Clip");
	        button.addActionListener( new ActionListener()
	        {
	            @Override
	            public void actionPerformed(ActionEvent ae)
	            {
	                clip.setFramePosition(0);
	                clip.start();
	            }
	        });

	        JOptionPane.showMessageDialog(null, button);
		}

}
