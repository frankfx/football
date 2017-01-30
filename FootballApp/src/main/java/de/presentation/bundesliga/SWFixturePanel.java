package de.presentation.bundesliga;


import java.awt.BorderLayout;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import de.business.SWFixtureTableModel;
import de.business.SWResultTableModel;
import de.business.SoccerwayMatchModel;
import de.utils.Utils;

public class SWFixturePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable mFixtureTable;
	
	public SWFixturePanel() {
		this.setLayout(new BorderLayout());
		
		SWFixtureTableModel model = new SWFixtureTableModel();
		mFixtureTable = new JTable(model);
	
		this.add(new JScrollPane(mFixtureTable), BorderLayout.CENTER);
	}

	/**
	 * adds a new row to the table Aufgaben.
	 * 
	 * @param dataVec data for one table row
	 */
	public void addToTable(SoccerwayMatchModel dataVec){
		((SWFixtureTableModel) mFixtureTable.getModel()).addToTable(dataVec);
	}
	
	public void updateTableMarker(String pID){
		SWFixtureTableModel lModel = (SWFixtureTableModel) mFixtureTable.getModel();
		ListSelectionModel selectionModel = mFixtureTable.getSelectionModel();
		
		selectionModel.clearSelection();
	
		for (int row = 0; row < lModel.getRowCount(); row++){
			if (pID.equals(lModel.getSoccerwayMatchModel(row).getTeamID())){
				selectionModel.addSelectionInterval(row, row);
			}
		}		
	}	
	
	public void clearTable(){
		((SWFixtureTableModel) mFixtureTable.getModel()).clear();
		mFixtureTable.revalidate();
	}
	
	@SuppressWarnings("unchecked")
	public void sortTableByDate(){
		SWFixtureTableModel lModel = (SWFixtureTableModel) mFixtureTable.getModel();
		lModel.getDataList().sort(new Comparator<SoccerwayMatchModel>() {
			@Override
			public int compare(SoccerwayMatchModel o1, SoccerwayMatchModel o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}
}
