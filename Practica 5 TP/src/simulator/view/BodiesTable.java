package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import simulator.control.Controller;

public class BodiesTable extends JPanel {
		
	
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private BodiesTableModel _tableModel;
	
	BodiesTable(Controller ctrl) {
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.black, 2), "Bodies",     
		TitledBorder.LEFT, TitledBorder.TOP));
		_ctrl = ctrl;
		_tableModel = new BodiesTableModel(_ctrl);
		initGUI();
	}
	
	private void initGUI(){
		JTable table = new JTable(_tableModel);
		JScrollPane scrollPane;
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.LEFT);
		table.setDefaultRenderer(String.class, centerRenderer);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}
}