package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class playernamePanel extends JPanel implements ActionListener{
	private  Mainview v;
	private JButton p3;

	private JButton p1;
	private JButton p2;

	private JLabel l1;
	private JLabel l2;
	private JLabel l3;

	private JTextField namep1;
	private JTextField namep2;
	private JButton next;
	private JLabel LB;
	private String pname1;
	private String pname2; 
	private JLabel background;



	public playernamePanel(Mainview main) {
//		JLabel background = new JLabel( new ImageIcon(54677.jpg) );
//		background.setLayout( new GridBagLayout() );
//		JPanel buttons = new JPanel();
//		buttons.setOpaque( false );
//		buttons.add(...);
//		background.add(buttons, new GridBagConstraints() );
		ImageIcon img1 = new ImageIcon("PHOTO-2022-06-16-23-39-14.jpg");

       background = new JLabel("",img1,JLabel.CENTER);
       background.setBounds(0,0,1500,800);
       this.add(background);

		this.v =main;
		this.setLayout(null);
      this.setSize(500,300);
      this.setBackground(Color.gray);
		p1 = new JButton("Player1 ");
		p1.setFont(new Font("Arial",Font.BOLD,20));
		p1.setLayout(null);
		p1.addActionListener(this);
		p1.setBounds(50, 200, 300, 150);
		this.add(p1);
		background.add(p1);
		 l1 = new JLabel("Player 1");
			this.add(l1);

			background.add(l1);

		p2 = new JButton("Player2 ");		
		p2.setFont(new Font("Arial",Font.BOLD,20));

		p2.addActionListener(this);
		p2.setBounds(700, 200, 300, 150);
		this.add(p2);

		background.add(p2);

		l2 = new JLabel("Player 2");
background.add(l2);

//		this.add(l2, BorderLayout.CENTER);
		p3 = new JButton("Please, enter the names for both Players....");
		l3 = new JLabel ("Please, enter the names for both Players....");
		l3.setFont(new Font("Arial",Font.BOLD,20));
		p3.setBounds(350,10,450,150);
		this.add(p3);
		
		background.add(p3);


//		p2.setOpaque(false);

	
		
		next = new JButton(" Press Next-> ");
		next.setBounds(900,500,300,100);
		next.setFont(new Font("Times",Font.BOLD,20));

		next.addActionListener(this);
		this.add(next);
		background.add(next);


		this.setOpaque(false);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==p1) {
		 pname1 = JOptionPane.showInputDialog("Player 1 enter your name");
		l1.setText(pname1);
		p1.setText(pname1);
		}
		else if(e.getSource()==p2) {
			 pname2 = JOptionPane.showInputDialog("Player 2 enter your name");
			l2.setText(pname2);
			p2.setText(pname2);
			}

		else if(e.getSource()==next) {
			if(pname1==null&& pname2== null) {
				pname2 = "player2";
				pname1="player1";
				JOptionPane.showMessageDialog(this, "please click next 2 times", "note", JOptionPane.PLAIN_MESSAGE);
			}
			
		else
			v.goChampionPanel(pname1,pname2);
		
			
		
		}
		
	}
	
		}