package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import engine.Game;
import model.abilities.Ability;
import model.effects.Effect;
import model.world.Champion;

public class championdesignpanel extends JPanel implements ActionListener {
	private JButton b1;
	private JPanel p1;
	private JPanel p2;
	private JButton b2;
	private JButton[][] buttons;
	private JTextArea infoArea;

	private JComboBox combo;
	private static ArrayList<String> aa = new ArrayList<String>();
	private JComboBox[] combos;

	private int count = 0;
	private int c1 = 0;
	private int c2 = 0;
	private int c3 = 0;
	private int c4 = 0;
	private int c5 = 0;
	private int c6 = 0;
	private int ind = 0;
	private int ind2 = 0;

	private Mainview v;
	private JButton start;

	public championdesignpanel(Mainview v) {
		this.v = v;
		ArrayList<String> aa = new ArrayList<String>();

		b1 = new JButton("pick players' champions");
		b1.setBounds(80, 20, 250, 50);
		b1.addActionListener(this);
		this.add(b1);

		p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 5));
		p1.setBounds(10, 80, 800, 550);
		this.add(p1);

		buttons = new JButton[3][5];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFont(new Font("Serif", 30, 20));

				p1.add(buttons[i][j]);
			}

		}
		infoArea = new JTextArea();
		infoArea.setBounds(850, 80, 590, 330);
		infoArea.setEditable(false);
		infoArea.setBackground(Color.yellow);
		infoArea.setForeground(Color.red);
		JScrollPane scroll = new JScrollPane();
		scroll.add(infoArea);
		this.add(scroll);
		this.add(infoArea);

		start = new JButton("start");
		start.setBounds(900, 600, 120, 30);
		start.addActionListener(this);
		this.add(start);

		this.setLayout(null);

	}

	public void actionPerformed(ActionEvent e) {
		int co = 0;
		if (e.getSource() == b1) {
			JOptionPane.showMessageDialog(this, "please choose the leader for player1", "note",
					JOptionPane.PLAIN_MESSAGE);

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					Champion c = Game.getAvailableChampions().get(co);
					buttons[i][j].setText(c.getName());
					if (c.getName().equals("Captain America")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("CA.jpg"));

					} else if (c.getName().equals("Deadpool")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("Deadpool.jpg"));

					} else if (c.getName().equals("Dr Strange")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("DrStrange.jpg"));

					} else if (c.getName().equals("Electro")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("electro.jpg"));

					} else if (c.getName().equals("Ghost Rider")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("ghost-rider.jpg"));

					} else if (c.getName().equals("Hela")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("hela.jpg"));

					} else if (c.getName().equals("Hulk")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("Hulk.jpg"));

					} else if (c.getName().equals("Iceman")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("Iceman.jpg"));

					} else if (c.getName().equals("Ironman")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("Ironman.jpg"));

					} else if (c.getName().equals("Loki")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("loki.jpg"));

					} else if (c.getName().equals("Quicksilver")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("Quicksilver.JPG"));

					} else if (c.getName().equals("Spiderman")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("SM.jpg"));

					} else if (c.getName().equals("Thor")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("Thor.jpg"));

					} else if (c.getName().equals("Venom")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("venom.jpg"));

					} else if (c.getName().equals("Yellow Jacket")) {
						buttons[i][j].setBackground(Color.WHITE);
						buttons[i][j].setIcon(new ImageIcon("YS.jpg"));

					}
					ind = getCurrIndex(buttons[i][j].getText());
					co++;

				}
			}
			b1.repaint();
			b1.revalidate();
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {

				if (e.getSource() == buttons[i][j] && count < 6) {

					if (count == 2) {
						JOptionPane.showMessageDialog(this, "please choose the leader for player2", "note",
								JOptionPane.PLAIN_MESSAGE);
						ind2 = getCurrIndex(buttons[i][j].getText());

					}
					if (count == 3) {
						JOptionPane.showMessageDialog(this, "please choose the rest of the team of player2", "note",
								JOptionPane.PLAIN_MESSAGE);

					}
					String s = buttons[i][j].getText();
					int n = getCurrIndex(s);
					infoArea.setText(v.toStringCh(n));
					aa.add(buttons[i][j].getText());
					buttons[i][j].setEnabled(false);

					count++;

				}

			}

			if (count == 6) {
				JOptionPane.showMessageDialog(this, "you can not choose more than 6", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}

		if (e.getSource() == start) {
			if (count == 0) {
				JOptionPane.showMessageDialog(this, "please choose the team for player1", "note",
						JOptionPane.PLAIN_MESSAGE);
			}
			int i1 = getCurrIndex(aa.get(0));
			int i2 = getCurrIndex(aa.get(1));
			int i3 = getCurrIndex(aa.get(2));
			int i4 = getCurrIndex(aa.get(3));
			int i5 = getCurrIndex(aa.get(4));
			int i6 = getCurrIndex(aa.get(5));
			v.goGame(i1, i2, i3, i4, i5, i6, i1, i4);
		}

		this.revalidate();
		this.repaint();

	}

	public int getCurrIndex(String s) {
		int index = 0;
		for (int i = 0; i < v.getGame().getAvailableChampions().size(); i++) {
			if (s.equals(v.getGame().getAvailableChampions().get(i).getName())) {
				index = i;
			}
		}
		return index;

	}

	public String toStringC(int champIndex) {

		Champion ch = (Game.getAvailableChampions().get(champIndex));
		String res = " ";
		res = "Champion TYPE: " + ch.getClass().getName() + "\n" + "Champion's name: " + ch.getName() + "\n"
				+ " Current health points : " + ch.getCurrentHP() + "\n" + "Type :" + ch.getCondition() + " Mana: "
				+ ch.getMana() + "\n" + "AttackDamage: " + ch.getAttackDamage() + "\n" + "AttackRange: "
				+ ch.getAttackRange() + "\n" + "Actions: " + ch.getCurrentActionPoints() + " \n";

		for (Ability ab : ch.getAbilities()) {
			res += ")" + ab.getName() + "\n";

		}

		for (Effect e : ch.getAppliedEffects()) {
			res += "effect name:" + e.getName() + "\n" + "Duration: " + e.getDuration() + "\n";
		}

		return res;

	}

}