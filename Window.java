/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3nesbittr;

import java.awt.BorderLayout;
import static java.awt.Color.WHITE;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Ryan
 */
public class Window extends JFrame {

    private static final long serialVersionUID = 10102323;
    final int WINDOWHEIGHT = 600;
    final int WINDOWWIDTH = 800;

    Window() {
        super();
    }

    public Window initWin(eStoreSearch s) {
        setSize(WINDOWWIDTH, WINDOWHEIGHT);
        setTitle("eStore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(WHITE);
        setLayout(new BorderLayout());
        setResizable(false);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e){}
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    s.fileOutput(s.getFileName(), s.getArrayList());
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found, Output Cancelled");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("Unsupported Encoding, Output Cancelled");
                }
                System.exit(0);}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        //STYLE OPTIONS
        int style = Font.BOLD;
        Font font = new Font ("Garamond", style , 15);


        //PANEL FOR ADD PAGE
        JPanel addPanel = new JPanel();
        addPanel.setVisible(false);
        addPanel.setLayout(new GridLayout(7,2));

        JLabel itemTypeLabel = new JLabel("Type:");
        itemTypeLabel.setFont(font);
        itemTypeLabel.setHorizontalAlignment(CENTER);
        String [] itemTypes = {"Book","Electronic"};
        final JComboBox<String> itemType = new JComboBox<>(itemTypes);
        itemType.setSelectedIndex(0);
        addPanel.add(itemTypeLabel);
        addPanel.add(itemType);

        JLabel productIDLabel = new JLabel("Product ID:");
        productIDLabel.setFont(font);
        productIDLabel.setHorizontalAlignment(CENTER);
        JTextField idText = new JTextField();
        addPanel.add(productIDLabel);
        addPanel.add(idText);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        nameLabel.setHorizontalAlignment(CENTER);
        JTextField nameText = new JTextField();
        addPanel.add(nameLabel);
        addPanel.add(nameText);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(font);
        priceLabel.setHorizontalAlignment(CENTER);
        JTextField priceText = new JTextField();
        addPanel.add(priceLabel);
        addPanel.add(priceText);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(font);
        yearLabel.setHorizontalAlignment(CENTER);
        JTextField yearText = new JTextField();
        addPanel.add(yearLabel);
        addPanel.add(yearText);

        JLabel authorsLabel = new JLabel("Authors:");
        authorsLabel.setFont(font);
        authorsLabel.setHorizontalAlignment(CENTER);
        JTextField authorsText = new JTextField();
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setFont(font);
        publisherLabel.setHorizontalAlignment(CENTER);
        JTextField publisherText = new JTextField();
        JLabel makerLabel = new JLabel("Maker:");
        makerLabel.setFont(font);
        makerLabel.setHorizontalAlignment(CENTER);
        JTextField makerText = new JTextField();
        addPanel.add(authorsLabel);
        addPanel.add(authorsText);
        addPanel.add(publisherLabel);
        addPanel.add(publisherText);

        itemType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = (String)itemType.getSelectedItem();
                if(command.equals("Book")) {
                    addPanel.remove(makerLabel);
                    addPanel.remove(makerText);
                    addPanel.add(authorsLabel);
                    addPanel.add(authorsText);
                    addPanel.add(publisherLabel);
                    addPanel.add(publisherText);
                    repaint();
                    revalidate();
                } else {
                    addPanel.remove(authorsLabel);
                    addPanel.remove(authorsText);
                    addPanel.remove(publisherLabel);
                    addPanel.remove(publisherText);
                    addPanel.add(makerLabel);
                    addPanel.add(makerText);
                    repaint();
                    revalidate();
                }}});


        //PANEL FOR THE SEARCH PAGE
        JPanel searchPanel = new JPanel();
        JPanel searchLabelPanel = new JPanel();
        JPanel searchBoxPanel = new JPanel();
        searchLabelPanel.setLayout(new GridLayout(4,1));
        searchBoxPanel.setLayout(new GridLayout(4,1));
        searchPanel.setVisible(false);
        searchPanel.setLayout(new GridLayout(1,2));
        searchPanel.add(searchLabelPanel);
        searchPanel.add(searchBoxPanel);

        JLabel searchIDLabel = new JLabel("Product ID:");
        searchIDLabel.setFont(font);
        searchIDLabel.setHorizontalAlignment(CENTER);
        JTextField searchIDText = new JTextField();
        searchLabelPanel.add(searchIDLabel);
        searchBoxPanel.add(searchIDText);
        searchIDText.setPreferredSize(new Dimension(100,25));
        searchIDText.setMaximumSize(new Dimension(100,25));
        searchIDText.setMinimumSize(new Dimension(100,25));

        JLabel keywordsLabel = new JLabel("Name Keywords:");
        keywordsLabel.setFont(font);
        keywordsLabel.setHorizontalAlignment(CENTER);
        JTextField keywordsText = new JTextField();
        keywordsText.setPreferredSize(new Dimension(250,35));
        searchLabelPanel.add(keywordsLabel);
        searchBoxPanel.add(keywordsText);

        JLabel startYearLabel = new JLabel("Start Year:");
        startYearLabel.setFont(font);
        startYearLabel.setHorizontalAlignment(CENTER);
        JTextField startYearText = new JTextField();
        startYearText.setPreferredSize(new Dimension(100,35));
        searchLabelPanel.add(startYearLabel);
        searchBoxPanel.add(startYearText);

        JLabel endYearLabel = new JLabel("End Year:");
        endYearLabel.setHorizontalAlignment(CENTER);
        endYearLabel.setFont(font);
        JTextField endYearText = new JTextField();
        endYearText.setPreferredSize(new Dimension(100,35));
        searchLabelPanel.add(endYearLabel);
        searchBoxPanel.add(endYearText);


        //MAIN PANEL UPON OPENING OF PROGRAM
        JPanel mainPanel = new JPanel();
        JPanel mainPanelBottom = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("Welcome to eStore");
        JLabel mainText2 = new JLabel("Choose a command from the \"command's\" menu above for");
        JLabel mainText3 = new JLabel("adding a product, searching products or quitting the program");
        mainText.setFont(font);
        mainText.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(mainText);
        mainText2.setFont(font);
        mainText2.setHorizontalAlignment(JLabel.CENTER);
        mainText3.setFont(font);
        mainText3.setHorizontalAlignment(JLabel.CENTER);

        mainPanelBottom.add(mainText2);
        mainPanelBottom.add(mainText3);
        mainPanel.add(mainPanelBottom);
        add(mainPanel, BorderLayout.CENTER);

        //  BOTTOM PANEL FOR ADD/SEARCH
        JPanel bottom = new JPanel(new BorderLayout());

        JLabel addMsg = new JLabel("Messages:");
        addMsg.setFont(font);
        JLabel searchMsg = new JLabel("Search Results:");
        searchMsg.setFont(font);

        JTextArea bottomOutput = new JTextArea(10,800);
        bottomOutput.setLineWrap(false);
        bottomOutput.setEditable(false);
        JScrollPane scrolledOutput = new JScrollPane(bottomOutput);
        scrolledOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolledOutput.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bottom.add(scrolledOutput);

        JPanel sidePanel = new JPanel();
        sidePanel.setVisible(false);
        sidePanel.setLayout(new GridLayout(2,1));
        JButton resetButton = new JButton("Reset");
        sidePanel.add(resetButton);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchYear = startYearText.getText() + '-' + endYearText.getText();
                bottomOutput.setText(s.search(searchIDText.getText(), keywordsText.getText(), searchYear, s.getArrayList(), s.getHm()));
            }});
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(itemType.getSelectedItem() == "Book") {
                    bottomOutput.setText(s.createBook(s.getArrayList(), s.getHm(), idText.getText(), nameText.getText(), yearText.getText(), priceText.getText(), authorsText.getText(), publisherText.getText()));
                } else {
                    bottomOutput.setText(s.createElec(s.getArrayList(), s.getHm(), idText.getText(), nameText.getText(), yearText.getText(), priceText.getText(), makerText.getText()));
                }
                bottomOutput.repaint();
                bottomOutput.revalidate();
            }});

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idText.setText("");
                nameText.setText("");
                yearText.setText("");
                priceText.setText("");
                authorsText.setText("");
                publisherText.setText("");
                makerText.setText("");
                keywordsText.setText("");
                startYearText.setText("");
                endYearText.setText("");
                searchIDText.setText("");
                repaint();
                revalidate();
            }});

        JMenu commandMenu = new JMenu("Command");
        commandMenu.setFont(font);
        JMenuItem addChoice = new JMenuItem("Add");
        JMenuItem searchChoice = new JMenuItem("Search");
        JMenuItem quitChoice = new JMenuItem("Quit");
        addChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMsg.setVisible(false);
                addMsg.setVisible(true);
                sidePanel.remove(searchButton);
                sidePanel.add(addButton);
                mainPanel.setVisible(false);
                searchPanel.setVisible(false);
                addPanel.setVisible(true);
                sidePanel.setVisible(true);
                bottom.add(addMsg, BorderLayout.NORTH);
                bottom.setVisible(true);
                add(addPanel, BorderLayout.CENTER);
                add(sidePanel,BorderLayout.EAST);
                add(bottom, BorderLayout.SOUTH);
            }});
        searchChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMsg.setVisible(true);
                addMsg.setVisible(false);
                sidePanel.remove(addButton);
                sidePanel.add(searchButton);
                mainPanel.setVisible(false);
                addPanel.setVisible(false);
                searchPanel.setVisible(true);
                sidePanel.setVisible(true);
                bottom.add(searchMsg, BorderLayout.NORTH);
                bottom.setVisible(true);
                add(searchPanel, BorderLayout.CENTER);
                add(sidePanel,BorderLayout.EAST);
                add(bottom, BorderLayout.SOUTH);
            }});
        quitChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    s.fileOutput(s.getFileName(), s.getArrayList());
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found, Output Cancelled");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("Unsupported Encoding, Output Cancelled");
                }
                System.exit(0);
            }});
        commandMenu.add(addChoice);
        commandMenu.add(searchChoice);
        commandMenu.add(quitChoice);
        JMenuBar bar = new JMenuBar();
        bar.add(commandMenu);
        setJMenuBar(bar);

        return this;
    }
}
