package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.imageio.ImageIO;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class gamePane extends JPanel implements ActionListener {
	private Mainview v;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;

	private JLabel pname;
	private JLabel pname2;
	private JButton left;
	private JButton right;
	private JButton down;
	private JButton up;
	private JButton effect;

	private JButton[][] b;
	private JButton[] turnorder;
	private JButton player1;
	private JButton player2;
	private Direction direction;
	private JButton move;
	private JButton attack;
	private JButton useleaderAbility;
	private JButton castAbility;
	private JButton endTurn;
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private JButton[][] button;
	private JTextField champtargetx;
	private JTextField champtargety;
	private JButton endGame;

	private JTextArea order;
	private JTextArea text;
	private JLabel background;

	private JComboBox<String> abilities;

//    private ImageIcon board;
//    private JLabel boardLabel;

	public gamePane(Mainview vieww) {
		this.v = vieww;
		this.setLayout(null);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(5, 5));
		p1.setBackground(Color.GREEN);
//		ImageIcon image=new ImageIcon("E1rjDraWUAEzFlZ.jpg");
//		Image img = image.getImage();
//		p1.setBackground(img);
//		board = new ImageIcon(this.getClass().getResource("/E1rjDraWUAEzFlZ.jpg"));
//		boardLabel = new JLabel(board);
//		boardLabel.setSize(640, 570);
//		p1.add(boardLabel);

		p1.setBounds(10, 10, 640, 570);
//		v.setLocationRelativeTo(null);

		this.add(p1);
		b = new JButton[5][5];

		for (int i = b.length - 1; i >= 0; i--) {
			for (int j = 0; j < b.length; j++) {
				b[i][j] = new JButton();
				b[i][j].setFont(new Font("Times", Font.ITALIC, 10));
				b[i][j].addActionListener(this);

				p1.add(b[i][j]);
			}

		}
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(Color.gray);
		p2.setBounds(680, 20, 580, 290);
		this.add(p2);

		player1 = new JButton("Player one");
		player2 = new JButton("Player two");
		player1.addActionListener(this);
		player1.setBounds(30, 10, 120, 25);
		player2.addActionListener(this);

		player2.setBounds(150, 10, 120, 25);

		p2.add(player1);
		p2.add(player2);
		pname = new JLabel("Player1 :" + v.getGame().getFirstPlayer().getName());
		pname2 = new JLabel("Player2 :" + v.getGame().getSecondPlayer().getName());

		left = new JButton("LEFT");
		left.setBackground(Color.BLACK);
		left.setBounds(30, 90, 90, 45);
		left.addActionListener(this);
		p2.add(left);

		right = new JButton("RIGHT");
		right.setBackground(Color.BLACK);
		right.setBounds(130, 90, 90, 45);
		right.addActionListener(this);

		p2.add(right);

		up = new JButton("UP");

		up.setBounds(90, 140, 90, 45);
		up.setBackground(Color.MAGENTA);
		up.setBounds(90, 40, 90, 45);

		up.addActionListener(this);

		p2.add(up);

		down = new JButton("DOWN");
		down.setBounds(90, 140, 90, 45);

		down.setBackground(Color.MAGENTA);

		down.addActionListener(this);

		p2.add(down);

		move = new JButton("Move");
		move.setBounds(250, 70, 170, 60);
		move.setBackground(Color.CYAN);

		move.addActionListener(this);
		p2.add(move);

		attack = new JButton("Attack");
		attack.setBounds(250, 130, 170, 60);
		attack.setBackground(Color.CYAN);

		attack.addActionListener(this);
		p2.add(attack);

		useleaderAbility = new JButton("leaderAbility");
		useleaderAbility.setBounds(250, 200, 180, 60);
		useleaderAbility.setBackground(Color.CYAN);

		useleaderAbility.addActionListener(this);
		p2.add(useleaderAbility);

		p4 = new JPanel();
		p4.setLayout(null);
		p4.setBackground(Color.gray);
		p4.setBounds(680, 340, 450, 180);
		this.add(p4);

		abilities = new JComboBox<String>();
		abilities.setBounds(30, 60, 250, 25);
		p4.add(abilities);

		champtargetx = new JTextField();
		champtargetx.setBounds(30, 90, 100, 30);
		p4.add(champtargetx);

		champtargety = new JTextField();
		champtargety.setBounds(250, 90, 100, 30);
		p4.add(champtargety);

		castAbility = new JButton("Cast ");
		castAbility.setBounds(30, 130, 300, 25);
		castAbility.addActionListener(this);
		p4.add(castAbility);

		p5 = new JPanel();
		p5.setLayout(null);
		p5.setBackground(Color.GRAY);
		p5.setBounds(680, 570, 500, 500);
		this.add(p5);

		text = new JTextArea();
		text.setEditable(false);
		text.setBackground(Color.yellow);
		JScrollPane s = new JScrollPane(text);
		s.setBounds(0, 0, 500, 500);

		p5.add(s);
		
		endTurn = new JButton("end turn");
		endTurn.setBounds(815, 525, 100, 25);
		endTurn.addActionListener(this);
		this.add(endTurn);
		
		endGame = new JButton("end game");
		endGame.setBounds(930, 525, 100, 25);
		endGame.addActionListener(this);
		this.add(endGame);

		p6 = new JPanel();
		p6.setLayout(new GridLayout(1, 6));
		p6.setBackground(Color.GRAY);
		p6.setBounds(10, 600, 600, 130);
		this.add(p6);

		turnorder = new JButton[6];
		PriorityQueue temp = new PriorityQueue(6);

		for (int j = 0; j < 6; j++) {
			Champion c = (Champion) v.getGame().getTurnOrder().peekMin();

			temp.add(v.getGame().getTurnOrder().peekMin());
			v.getGame().getTurnOrder().remove();
			turnorder[j] = new JButton(c.getName());
			turnorder[j].setFont(new Font("Times", Font.ITALIC, 10));
			turnorder[j].addActionListener(this);
			p6.add(turnorder[j]);
		}

		while (!temp.isEmpty()) {
			v.getGame().getTurnOrder().insert((Comparable) temp.remove());
		}

		displayBoardH();
		displaychampioninfo();
		loadcastability();

	}
	


	public ArrayList<String> abilityname() {
		ArrayList<String> s = new ArrayList<String>();
		int j = 0;
		while (j < v.getGame().getCurrentChampion().getAbilities().size()) {
			s.add(v.getGame().getCurrentChampion().getAbilities().get(j).getName());
			j++;
		}
		return s;
	}

	public void loadcastability() {
		abilities.removeAllItems();
		int i = 0;
		ArrayList<String> s = abilityname();
		while (i < s.size()) {
			abilities.addItem(s.get(i));
			i++;
		}
	}

	public void displaychampioninfo() {
		text.setText(v.allinfoforcurrent());
	}

	public int changechampt(JTextField champtargety) {
		int n = Integer.parseInt(champtargetx.getText());
		int m = Integer.parseInt(champtargety.getText());
		return m;

	}

	public int changey(JTextField champtargetx) {
		int n = Integer.parseInt(champtargetx.getText());
		return n;
	}

	public void cham(Champion c, int i, int j) {
		int tmpx = (int) c.getLocation().getX();
		int tmpy = (int) c.getLocation().getY();

		if (c.getName().equals("Deadpool")) {
			b[i][j].setIcon(new ImageIcon("Deadpool.jpg"));
			b[i][j].setBackground(Color.PINK);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

//			try {
//				Image img = ImageIO.read(new File("Deadpool.jpg")).getScaledInstance( 110,110, Image.SCALE_DEFAULT);
//				b[i][j].setIcon(new ImageIcon(img));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			

		}

		else if (c.getName().equals("Dr Strange")) {
			b[i][j].setIcon(new ImageIcon("DrStrange.jpg"));
			b[i][j].setBackground(Color.red);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		}

		else if (c.getName().equals("Electro")) {
			b[i][j].setIcon(new ImageIcon("electro.jpg"));
			b[i][j].setBackground(Color.BLUE);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Ghost Rider")) {
			b[i][j].setIcon(new ImageIcon("ghost-rider.jpg"));
			b[i][j].setBackground(Color.CYAN);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Hela")) {
			b[i][j].setIcon(new ImageIcon("hela.jpg"));
			b[i][j].setBackground(Color.YELLOW);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");

			p1.add(b[i][j]);

		} else if (c.getName().equals("Hulk")) {
			b[i][j].setIcon(new ImageIcon("Hulk.jpg"));
			b[i][j].setBackground(Color.LIGHT_GRAY);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Iceman")) {
			b[i][j].setIcon(new ImageIcon("Iceman.jpg"));
			b[i][j].setBackground(Color.blue);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Ironman")) {
			b[i][j].setIcon(new ImageIcon("ironman.jpg"));
			b[i][j].setBackground(Color.ORANGE);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Loki")) {
			b[i][j].setIcon(new ImageIcon("loki.jpg"));
			b[i][j].setBackground(Color.DARK_GRAY);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Quicksilver")) {
			b[i][j].setIcon(new ImageIcon("Quicksilver.jpg"));
			b[i][j].setBackground(Color.red);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Spiderman")) {
			b[i][j].setIcon(new ImageIcon("SM.jpg"));
			b[i][j].setBackground(Color.green);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Thor")) {
			b[i][j].setIcon(new ImageIcon("Thor.jpg"));
			b[i][j].setBackground(Color.magenta);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Venom")) {
			b[i][j].setIcon(new ImageIcon("venom.jpg"));
			b[i][j].setBackground(Color.BLUE);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Yellow Jacket")) {
			b[i][j].setIcon(new ImageIcon("YS.jpg"));
			b[i][j].setBackground(Color.yellow);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		} else if (c.getName().equals("Captain America")) {
			b[i][j].setIcon(new ImageIcon("CA.jpg"));
			b[i][j].setBackground(Color.red);
			b[i][j].setText(c.getName() + "/" + c.getCurrentHP() + "");
			p1.add(b[i][j]);

		}
	}

	public void displayBoardH() {

		for (int i = v.getGame().getBoardheight() - 1; i >= 0; i--) {
			for (int j = 0; j < 5; j++) {
				if (v.getGame().getBoard()[i][j] != null) {
					if (v.getGame().getBoard()[i][j] instanceof Champion) {
						Champion c = (Champion) v.getGame().getBoard()[i][j];
						if (c == v.getGame().getCurrentChampion()) {
							cham(c, i, j);
						} else if (v.getGame().getFirstPlayer().getTeam().contains(c)) {
							cham(c, i, j);
						} else if (v.getGame().getSecondPlayer().getTeam().contains(c)) {
							cham(c, i, j);
						}
					} else if (v.getGame().getBoard()[i][j] instanceof Cover) {
						Cover cover = (Cover) v.getGame().getBoard()[i][j];
						b[i][j].setForeground(Color.DARK_GRAY);
						b[i][j].setBackground(Color.BLACK);
						b[i][j].setText(cover.getCurrentHP() + "");
						p1.add(b[i][j]);
					}

				} else {
					b[i][j].setForeground(Color.GRAY);
					b[i][j].setBackground(Color.WHITE);
					b[i][j].setText(" ");
					b[i][j].setIcon(new ImageIcon(""));
					p1.add(b[i][j]);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		String ss = v.ability();
		if (e.getSource() == player1) {
			player1.setText(v.getGame().getFirstPlayer().getName());
		}

		if (e.getSource() == player2) {
			player2.setText(v.getGame().getSecondPlayer().getName());

		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == b[i][j]) {
					if (v.getGame().getBoard()[i][j] instanceof Champion) {
						Champion c = (Champion) v.getGame().getBoard()[i][j];
						String s = "selected champion info : " + "\n" + c.getName() + "\n" + "attackdamage:"
								+ c.getAttackDamage() + "\n" + "attackrange:" + c.getAttackRange() + "\n"
								+ "currenthp is " + c.getCurrentHP() + "\n" + "Mana is " + c.getMana() + "\n"
								+ "current action points are " + c.getCurrentActionPoints() + "\n" + "abilities " + ss
								+ "\n";
						JOptionPane.showMessageDialog(this, s, "info", JOptionPane.INFORMATION_MESSAGE);

					}

				}
			}
		}
		for (int i = 0; i < 6; i++) {
			if (e.getSource() == turnorder[i]) {
				String s = "";
				Champion c = (Champion) v.getGame().getTurnOrder().peekMin();
				turnorder[i].setForeground(Color.BLUE);
				if (c == v.getGame().getTurnOrder().peekMin()) {
					s += c.getName() + " " + "is the current champion";
					JOptionPane.showMessageDialog(this, s, "info", JOptionPane.INFORMATION_MESSAGE);
//				} else {
//					s += c.getName() + " " + "is the upcoming champion";
//					JOptionPane.showMessageDialog(this, s, "info", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					if(c.getCurrentHP() == 0) {
						turnorder[i].setText("");
					}
				}
				turnorder[i].setForeground(Color.BLACK);
					
				}
			}

		if (e.getSource() == left) {
			direction = Direction.LEFT;

		}
		if (e.getSource() == right) {
			direction = Direction.RIGHT;

		}
		if (e.getSource() == up) {
			direction = Direction.UP;

		}
		if (e.getSource() == down) {
			direction = Direction.DOWN;

		}
		if (e.getSource() == move) {

			try {

				v.getGame().move(direction);
				for (int i = v.getGame().getBoardheight() - 1; i >= 0; i--) {
					for (int j = 0; j < 5; j++) {
						if (b[i][j].getIcon() != null) {
							b[i][j].setBackground(Color.WHITE);
							b[i][j].setIcon(new ImageIcon(""));
						}
					}
				}

				displayBoardH();
				displaychampioninfo();
				this.revalidate();
				this.repaint();
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == attack) {

			try {
				v.getGame().attack(direction);

				displayBoardH();
				displaychampioninfo();
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {

				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == endTurn) {
			for (int i = 0; i < 6; i++) {
				if (e.getSource() == turnorder[i]) {
					String s = "";
					Champion c = (Champion) v.getGame().getTurnOrder().peekMin();
					turnorder[i].setForeground(Color.BLUE);
					if (c == v.getGame().getTurnOrder().peekMin()) {
						s += c.getName() + " " + "is the current champion";
						JOptionPane.showMessageDialog(this, s, "info", JOptionPane.INFORMATION_MESSAGE);
					} else{
						if(c.getCurrentHP() == 0) {
							turnorder[i].setText("");
						}
					}
					turnorder[i].setForeground(Color.BLACK);
			}
				
				}
			v.getGame().endTurn();
			displayBoardH();
			displaychampioninfo();
			loadcastability();
		}
		if (e.getSource() == useleaderAbility) {
			try {
				v.getGame().useLeaderAbility();
				displayBoardH();
				displaychampioninfo();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		if (e.getSource() == castAbility) {
			int indexofa = abilities.getSelectedIndex();
			Ability a = v.getGame().getCurrentChampion().getAbilities().get(indexofa);
			try {
				if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {

					v.getGame().castAbility(a, direction);
				} else if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
					if (champtargety.getText().equals("") || champtargetx.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "You must choose target x,y position", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else if (a instanceof DamagingAbility) {
						if (a.getName().equals("Punch")) {
							abilities.addItem("Punch");
						}
					}
					v.getGame().castAbility(a, Integer.parseInt(champtargetx.getText()),
							Integer.parseInt(champtargety.getText()));

				} else
					v.getGame().castAbility(a);

				displayBoardH();
				displaychampioninfo();
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException
					| NumberFormatException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}

		}
		if (e.getSource() == endGame) {
			if (v.getGame().checkGameOver() == v.getGame().getFirstPlayer()) {
				JOptionPane.showMessageDialog(this, "AND THE WINNER IS :- " + v.getGame().getFirstPlayer().getName(),
						"GAMEOVER", JOptionPane.INFORMATION_MESSAGE);
				v.dispose();
			} else if (v.getGame().checkGameOver() == v.getGame().getSecondPlayer()) {
				JOptionPane.showMessageDialog(this, "AND THE WINNER IS :- " + v.getGame().getSecondPlayer().getName(),
						"GAMEOVER", JOptionPane.INFORMATION_MESSAGE);
				v.dispose();
			}
		}

	}
}
