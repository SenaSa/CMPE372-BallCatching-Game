import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

//this is class table model
public class PlayerTableModel extends AbstractTableModel {
	//A new player array list is defined
	//columnNames that is array places the column names of table 
	//columnClass which is array places the column class of table
	private  ArrayList<Player> playersList;
	private  String[] columnNames = new String[] { "Name", "WinPoint", "FailPoint", "Time", "Date" };
	private  Class[] columnClass = new Class[] { Integer.class, Integer.class, String.class, String.class,
			String.class };

	//Constructor is created with one parameter that is player array list
	public PlayerTableModel(ArrayList<Player> players) {
		playersList = players;
	}
	// this method returns the name of columns according to given c. number in the table 
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	// this method returns the class of columns according to given column index  in the table 
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}
	// this method gives the number of columns in the table 
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	// this method gives the number of rows in the table 
	@Override
	public int getRowCount() {
		return playersList.size();
	}
    //This method provides to returns the value for the cell at columnIndex and rowIndex.
	//According to the index of player list places table.
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Player row = playersList.get(rowIndex);
		if (0 == columnIndex) {
			return row.getName();
		} else if (1 == columnIndex) {
			return row.getWinPoint();
		} else if (2 == columnIndex) {
			return row.getFailPoint();
		} else if (3 == columnIndex) {
			return row.getTime();
		} else if (4 == columnIndex) {
			return row.getDate();
		}

		return null;
	}



}
