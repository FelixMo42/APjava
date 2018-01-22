package networking;

import javax.swing.*;
import javax.swing.GroupLayout.*;

import java.awt.*;
import java.awt.event.*;

public class UI extends JFrame {
	private static final long serialVersionUID = 1L;
	Container pane = getContentPane();
	GroupLayout layout = new GroupLayout(pane);

	public UI() {
		pane.setLayout( layout );
		layout.setAutoCreateContainerGaps(true);
        initUI();
    }

    private void initUI() {
		//create layout
		JButton quitButton = new JButton("X");
		JButton infoButton = new JButton("I");
	
		quitButton.addActionListener( (ActionEvent event) -> {
			System.exit(0);
		});
		
		SequentialGroup hsg = layout.createSequentialGroup();
		SequentialGroup vsg = layout.createSequentialGroup();
		 
		layout.setHorizontalGroup( hsg.addComponent( quitButton ).addComponent( infoButton ) );
		layout.setVerticalGroup( vsg.addComponent( quitButton ).addComponent( infoButton ) );
    	 
		//set up window
		setTitle("[ ChaterBOX ]");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation( EXIT_ON_CLOSE );        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
        	UI ex = new UI();
            ex.setVisible(true);
        } );
    }
}