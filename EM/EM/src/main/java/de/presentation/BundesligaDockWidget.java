package de.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.javadocking.DockingManager;
import com.javadocking.dock.FloatDock;
import com.javadocking.dock.Position;
import com.javadocking.dock.SplitDock;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DockingMode;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.drag.DragListener;
import com.javadocking.model.FloatDockModel;

import de.business.MatchModel;

public class BundesligaDockWidget extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Static fields.
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 450;

	// member fields.
	DefaultPanel panelBundesliga;	
	
	// Constructor.
	public BundesligaDockWidget(){
		// Create the frame.
		this.setTitle("Test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		
		// Create the panel and add it to the frame.
		panelBundesliga = new MatchDayPanel("test");
		this.getContentPane().add(panelBundesliga);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
				
		// Create the dock model for the docks.
		FloatDockModel dockModel = new FloatDockModel();
		dockModel.addOwner("frame0", this);

		// Give the dock model to the docking manager.
		DockingManager.setDockModel(dockModel);

		// Create the content components.
		TextPanel textPanel1 = new TextPanel("I am window 1.");
		TextPanel textPanel2 = new TextPanel("I am window 2.");
		TextPanel textPanel3 = new TextPanel("I am window 3.");
		TextPanel textPanel4 = new TextPanel("I am window 4.");
		TextPanel textPanel5 = new TextPanel("I am window 5.");

		// Create the dockables around the content components.
		Dockable dockable1 = new DefaultDockable("Window1", textPanel1, "Window 1", null, DockingMode.ALL);
		Dockable dockable2 = new DefaultDockable("Window2", textPanel2, "Window 2", null, DockingMode.ALL);
		Dockable dockable3 = new DefaultDockable("Window3", textPanel3, "Window 3", null, DockingMode.ALL);
		Dockable dockable4 = new DefaultDockable("Window4", textPanel4, "Window 4", null, DockingMode.ALL);
		Dockable dockable5 = new DefaultDockable("Window5", textPanel5, "Window 5", null, DockingMode.ALL);

		// Create the tab docks.
		TabDock topTabDock = new TabDock();
		TabDock bottomTabDock = new TabDock();
		TabDock rightTabDock = new TabDock();

		// Add the dockables to these tab docks.
		topTabDock.addDockable(dockable1, new Position(0));
		topTabDock.addDockable(dockable2, new Position(1));
		bottomTabDock.addDockable(dockable3, new Position(0));
		rightTabDock.addDockable(dockable4, new Position(0));

		// The windows of the tab docks should be able to split.
		// Put the tab docks in split docks.
		SplitDock topSplitDock = new SplitDock();
		topSplitDock.addChildDock(topTabDock, new Position(Position.CENTER));
		SplitDock bottomSplitDock = new SplitDock();
		bottomSplitDock.addChildDock(bottomTabDock, new Position(Position.CENTER));
		SplitDock rightSplitDock = new SplitDock();
		rightSplitDock.addChildDock(rightTabDock, new Position(Position.CENTER));
		
		// Add the 3 root docks to the dock model.
		dockModel.addRootDock("topdock", topSplitDock, this);
		dockModel.addRootDock("bottomdock", bottomSplitDock, this);
		dockModel.addRootDock("rightdock", rightSplitDock, this);
			
		// Dockable 5 should float. Add dockable 5 to the float dock of the dock model (
		// The float dock is a default root dock of the FloatDockModel.
		FloatDock floatDock = dockModel.getFloatDock(this);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		floatDock.addDockable(dockable5, new Point(screenSize.width / 2, screenSize.height / 2), new Point());

		// Create the split panes.
		JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		leftSplitPane.setDividerLocation(250);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setDividerLocation(400);

		// Add the root docks to the split panes.
		leftSplitPane.setLeftComponent(topSplitDock);
		leftSplitPane.setRightComponent(bottomSplitDock);
		splitPane.setLeftComponent(leftSplitPane);
		splitPane.setRightComponent(rightSplitDock);
		
		// Add the split pane to the panel.
		add(splitPane, BorderLayout.CENTER);
		
	}
	
	/**
	 * This is the class for the content.
	 */
	private class TextPanel extends JPanel implements DraggableContent
	{
		
		private JLabel label; 
		
		public TextPanel(String text)
		{
			super(new FlowLayout());
			
			// The panel.
			setMinimumSize(new Dimension(80,80));
			setPreferredSize(new Dimension(150,150));
			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(Color.lightGray));
			
			// The label.
			label = new JLabel(text);
			label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			add(label);
		}
		
		// Implementations of DraggableContent.

		public void addDragListener(DragListener dragListener)
		{
			addMouseListener(dragListener);
			addMouseMotionListener(dragListener);
			label.addMouseListener(dragListener);
			label.addMouseMotionListener(dragListener);
		}
	}



	public void initPanelBundesliga(MatchModel pMatch){
		panelBundesliga.addToContentPanel(pMatch);
	}	
	
	public static void main(String args[]){
        Runnable doCreateAndShowGUI = new Runnable() 
        {
            public void run() 
            {
                new BundesligaDockWidget();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
	
}
