package de.presentation.bundesliga;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import de.business.TipicoModel;
import de.business.TipicoTableModel;
import de.presentation.AbstractPanelContainer;
import de.presentation.JSplitButton;

public class TipicoBetView extends AbstractPanelContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TipicoBetTable mTable;
	private TipicoTableModel mTableModel;
	private TableRowSorter<TipicoTableModel> mSorter;
	private JScrollPane mTablePane;	
	private JButton mBtnBetValue;
	private JButton mBtnNew;
	private JButton mBtnModify;	
	private JButton mBtnDelete;
	private JSplitButton mBtnDBSplit;
	private JTextArea mTextDescription;
	
	public TipicoBetView() {	
		// create an default panel
		initPanel("Tipico", new GridBagLayout(), Color.WHITE);
    }

	/** Creates Tipico panel. */
	@Override
	public void initView() {
		mBtnBetValue = new JButton("Compute");
		mBtnNew = new JButton("New");
		mBtnModify = new JButton("Modify");
		mBtnDelete = new JButton("Delete");
		mBtnDBSplit = new JSplitButton("DB");
		mTextDescription = new JTextArea(4,1);
			
		GridBagConstraints c = new GridBagConstraints();

		mTableModel = new TipicoTableModel();
		mTable = new TipicoBetTable(mTableModel);
		mTable.setPreferredScrollableViewportSize(mTable.getPreferredSize());
        mTable.setFillsViewportHeight(true);		
        
		mSorter = new TableRowSorter<TipicoTableModel>(mTableModel);
		mTable.setRowSorter(mSorter);
		
        mTablePane = new JScrollPane(mTable);
        mTablePane.setVisible(true);
	    
        mTextDescription.setEditable(false);
        
        JScrollPane scroll = new JScrollPane(mTextDescription);
        
        scroll.setBackground(Color.WHITE);
        
//		scroll.setBounds(10, 11, 455, 549); // <-- THIS	
		scroll.setBorder(BorderFactory.createTitledBorder("Description"));
        
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 5;
		c.weightx=0.1;
		c.weighty=0.1;	        
		this.add(mTablePane, c);
		c.gridy=1;
		c.weighty=0.0;	
		c.insets.set(0, 0, 10, 0); 
		this.add(scroll, c);
		c.fill = GridBagConstraints.HORIZONTAL;		
		c.gridwidth = 1;
		c.gridy = 2;
		c.insets.set(0,0,0,0);
		this.add(mBtnBetValue, c);
		c.gridx = 1;
		c.gridy = 2;
		this.add(mBtnModify, c);
		c.gridx = 2;
		c.gridy = 2;
		this.add(mBtnNew, c);
		c.gridx = 3;
		c.gridy = 2;
		this.add(mBtnDelete, c);
		c.gridx = 4;
		c.gridy = 2;		
		c.weightx = 0.0;
		this.add(mBtnDBSplit, c);
	}

	/**
	 * Updates the Tipico JTable
	 */		
	public void updateTable(){
		mTableModel.fireTableDataChanged();

		//mTable.revalidate();
    	//mTable.invalidate();
    	//mTablePane.repaint();		
	}
	
	/**
	 * ========================
	 * BEGIN LISTENER
	 * ========================
	 */	
	public void setButtonNewBetListener(ActionListener l){
		this.mBtnNew.addActionListener(l);
	}
	
	public void setButtonModifyListener(ActionListener l){
		this.mBtnModify.addActionListener(l);
	}	

	public void setButtonLadenListener(ActionListener l){

	}	
	
	public void setButtonBetValueListener(ActionListener l){
		this.mBtnBetValue.addActionListener(l);
	}	
	
	public void setButtonDeleteListener(ActionListener l){
		this.mBtnDelete.addActionListener(l);
	}	
	
	public void setButtonCommitListerner(ActionListener l){
		this.mBtnDBSplit.getCommitItem().addActionListener(l);
	}

	public void setButtonPullListerner(ActionListener l){
		this.mBtnDBSplit.getPullItem().addActionListener(l);
	}

	public void setButtonPullDetailListerner(ActionListener l) {
		this.mBtnDBSplit.getPullDetailItem().addActionListener(l);
	}

	public void setButtonRemoveListerner(ActionListener l){
		this.mBtnDBSplit.getRemoveItem().addActionListener(l);
	}	
	
	public void setButtonRevertListerner(ActionListener l){
		this.mBtnDBSplit.getRevertItem().addActionListener(l);
	}	
	
	public void setButtonDBBrowserListener(ActionListener l){
		this.mBtnDBSplit.getDBBrowserItem().addActionListener(l);
	}

	public void setMenuClearSelectionListener(ActionListener l) {
		this.mTable.setMenuClearSelectionListener(l);
	}

	public void setFilterPopupListener(MouseAdapter l) {
		this.mTable.getTableHeader().addMouseListener(l);
	}
	
	public void setTableSelectionListener(ListSelectionListener l){
		this.mTable.getSelectionModel().addListSelectionListener(l);
	}
	/**
	 * ========================
	 * END LISTENER
	 * ========================
	 */	
	
	
	/**
	 * ========================
	 * BEGIN GETTER AND SETTER
	 * ========================
	 */	
	public JButton getBtnBetValue() {
		return mBtnBetValue;
	}

	public void setBtnBetValue(JButton pBtnBetValue) {
		this.mBtnBetValue = pBtnBetValue;
	}

	public JButton getBtnNew() {
		return mBtnNew;
	}

	public void setBtnNew(JButton pBtnNew) {
		this.mBtnNew = pBtnNew;
	}

	public JButton getBtnModify() {
		return mBtnModify;
	}

	public void setmBtnModify(JButton pBtnModify) {
		this.mBtnModify = pBtnModify;
	}
	
	public JButton getBtnDelete() {
		return mBtnDelete;
	}

	public void setBtnDelete(JButton pBtnDelete) {
		this.mBtnDelete = pBtnDelete;
	}	
	
	public JTable getTable() {
		return mTable;
	}
	
	public void setTable(TipicoBetTable pTable) {
		this.mTable = pTable;
	}
	
	public TableRowSorter<TipicoTableModel> getSorter() {
		return mSorter;
	}

	public TipicoTableModel getTableModel() {
		return mTableModel;
	}

	public void setTableModel(TipicoTableModel pTableModel) {
		this.mTableModel = pTableModel;
	}	
	
	public void setDescriptionValue(String pText){
		mTextDescription.setText(pText);
	}
	
	public String getDescriptionValue(){
		return mTextDescription.getText();
	}
	/**
	 * ========================
	 * END GETTER AND SETTER
	 * ========================
	 */
	public void sortTableByDate(){
		getTableModel().getTipicoModelsAsList().sort(new Comparator<TipicoModel>() {
			@Override
			public int compare(TipicoModel o1, TipicoModel o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}	
}