package de.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TipicoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// storing the objects in list
	List<TipicoModel> list;
	// the headers
	String[] header;
    
	public TipicoTableModel(){
		this(null, new String[]{"ID", "TEAM", "WINVALUE", "EXPENSES", "ATTEMPTS", "DATE", "SUCCESSFUL"});
	}

	public TipicoTableModel(TipicoModel [] entries, String [] header){
		this.header = header;
		list = new ArrayList<TipicoModel>();
		
		if(entries != null)
			for (int i = 0; i < entries.length; i++) {
				list.add(entries[i]);
			}
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return Float.class;
		case 3:
			return Float.class;
		case 4:
			return Integer.class;
		case 5:
			return LocalDate.class;
		case 6:
			return Boolean.class;
		default:
			return Object.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getTnr();
		case 1:
			return list.get(rowIndex).getTeam();
		case 2:
			return list.get(rowIndex).getWinValue();
		case 3:
			return list.get(rowIndex).getExpenses();
		case 4:
			return list.get(rowIndex).getAttempts();
		case 5:
			return list.get(rowIndex).getDate();
		case 6:
			return list.get(rowIndex).getSuccess();
		default:
			return null;
		}		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			list.get(rowIndex).setTnr((Integer) aValue); break;
		case 1:
			list.get(rowIndex).setTeam(aValue.toString()); break;
		case 2:
			list.get(rowIndex).setWinValue((Float) aValue); break;
		case 3:
			list.get(rowIndex).setExpenses((Float) aValue); break;
		case 4:
			list.get(rowIndex).setAttempts((Integer) aValue); break;
		case 5:
			list.get(rowIndex).setDate((LocalDate) aValue); break;
		case 6:
			list.get(rowIndex).setSuccess((Boolean) aValue); break;
		}		
	}

	public void addRow(TipicoModel pModel){
		if(!this.list.contains(pModel))
			this.list.add(pModel);
	}
	
	public void removeRow(int pRowIndex) {
		if (pRowIndex >= 0 && pRowIndex < list.size() )
			this.list.remove(pRowIndex);	
	}
	
	public TipicoModel getTipicoModelAtRow(int pRowIndex){
		return pRowIndex >= 0 && pRowIndex < list.size() ? list.get(pRowIndex) : null;
	}
	
	public int generateValidID(){
		return rec_generateValidID(list.size()+1);
	}
	
	private int rec_generateValidID(int id){
		if(isIDValid(id))
			return id;
		else
			return rec_generateValidID(id+1);
	}	
	
	public boolean isIDValid(int id){
		for(TipicoModel item : list)
			if(item.getTnr() == id)
				return false;
		return true;
	}	
	
	@Override
	public String toString(){
		return this.list.toString();
	}
	
	public List<TipicoModel> getAsList(){
		return list;
	}
	
	public float getBalance(){
		float lResult = 0.0f;
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getSuccess())
				lResult += list.get(i).getWinValue();
			else
				lResult -= list.get(i).getExpenses();
		}
		return lResult;
	}
}