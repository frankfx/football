package de.presentation.bundesliga;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.business.teams.TeamIDEnum;
import de.presentation.AbstractPanelContainer;

public class BundesligaConsoleContainer  extends AbstractPanelContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea mConsole;
	private JButton mBtnClear;
	private JButton mBtnRequest;
	private JButton mBtnTeamRequest;
	private JComboBox<String> mComboLeague;
	private JComboBox<String> mComboSeason;
	private JComboBox<Integer> mComboMatchday;
	
	private JComboBox<TeamIDEnum> mComboTeamID;
	private JComboBox<String> mComboMatchType;
	
	private GridBagConstraints c;
	
	public BundesligaConsoleContainer() {	
		c = new GridBagConstraints(); 
		// create an default panel
		initPanel("Console", new GridBagLayout(), Color.WHITE);
    }

	@Override
	public void initView() {
		mConsole = new JTextArea();
		mBtnClear = new JButton("Clear");
		mBtnRequest = new JButton("Request");
		mBtnTeamRequest = new JButton("TeamRequest");
		
		mComboLeague = new JComboBox<String>();
		mComboSeason = new JComboBox<String>();
		mComboMatchday = new JComboBox<Integer>();
		mComboTeamID = new JComboBox<TeamIDEnum>();
		mComboMatchType = new JComboBox<String>(); 
		
		mComboLeague.addItem("bl1");
		mComboSeason.addItem("2015");
		mComboSeason.addItem("2016");
		for(int i = 1; i < 35; i++)
			mComboMatchday.addItem(i);
		mComboTeamID.addItem(TeamIDEnum.ZWICKAU);
		mComboTeamID.addItem(TeamIDEnum.ESSEN);
		mComboTeamID.addItem(TeamIDEnum.HSV);
		mComboTeamID.addItem(TeamIDEnum.VFR);
		
		mComboMatchType.addItem("all");
		mComboMatchType.addItem("home");
		mComboMatchType.addItem("away");
		
        JScrollPane scroll = new JScrollPane(mConsole);
        scroll.setBounds(10, 11, 455, 249);                     // <-- THIS		
		
//		mConsole.setMaximumSize(new Dimension(30, 100));;
//		mBtnClear.setPreferredSize(new Dimension(20, 20));
        // c.insets = new Insets(5, 10, 0, 0);
        
        Insets lLeft = new Insets(0, 10, 0, 0);
        Insets lDefault = new Insets(0, 0, 0, 0);
        
        c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 8;
        c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.weighty = 0.1;
        this.add(scroll, c);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
        c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.0;
		this.add(mComboMatchday, c);
        c.gridx = 2;
		this.add(mComboLeague, c);        
        c.gridx = 4;
		this.add(mComboSeason, c);  
		c.gridwidth = 1;
		c.gridx = 6;
		c.insets = lLeft;
		this.add(mComboTeamID, c);
		c.gridx = 7;
		c.insets = lDefault;
		this.add(mComboMatchType, c);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		this.add(mBtnClear, c);
		c.gridx = 3;
        this.add(mBtnRequest, c);
        c.gridwidth = 2;        
		c.gridx = 6;
		c.insets = lLeft;
		this.add(mBtnTeamRequest, c);
	}	
	
	public JTextArea getConsole() {
		return mConsole;
	}

	public void setConsole(String lText) {
		this.mConsole.setText(lText);
	}
	
	public void clearConsole(){
		this.setConsole("");
	}

	public void appendConsole(String lText) {
		this.mConsole.append(lText+"\n");
	}	
	
	public JButton getBtnClear() {
		return mBtnClear;
	}

	public void setBtnClear(JButton lBtnClear) {
		this.mBtnClear = lBtnClear;
	}

	public JButton getBtnRequest() {
		return mBtnRequest;
	}

	public void setBtnRequest(JButton pBtnRequest) {
		this.mBtnRequest = pBtnRequest;
	}

	public JComboBox<String> getComboLeague() {
		return mComboLeague;
	}

	public void setComboLeague(JComboBox<String> pComboLeague) {
		this.mComboLeague = pComboLeague;
	}

	public JComboBox<String> getComboSeason() {
		return mComboSeason;
	}

	public void setComboSeason(JComboBox<String> pComboSeason) {
		this.mComboSeason = pComboSeason;
	}

	public JComboBox<Integer> getComboMatchday() {
		return mComboMatchday;
	}

	public void setComboMatchday(JComboBox<Integer> pComboMatchday) {
		this.mComboMatchday = pComboMatchday;
	}

	public JButton getBtnTeamRequest() {
		return mBtnTeamRequest;
	}

	public void setBtnTeamRequest(JButton pBtnTeamRequest) {
		this.mBtnTeamRequest = pBtnTeamRequest;
	}

	public JComboBox<TeamIDEnum> getComboTeamID() {
		return mComboTeamID;
	}

	public void setComboTeamID(JComboBox<TeamIDEnum> pComboTeamID) {
		this.mComboTeamID = pComboTeamID;
	}

	public JComboBox<String> getComboMatchType() {
		return mComboMatchType;
	}

	public void setComboMatchType(JComboBox<String> pComboMatchType) {
		this.mComboMatchType = pComboMatchType;
	}	
}
