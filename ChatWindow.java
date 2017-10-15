import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ChatWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel loginPanel;
	private JTextField username;
	
	private JPanel chatPanel;
	private JTabbedPane chatPane;
	private JTextField messageField;
	private JTextArea messageArea;
	private JButton sendButton;
	private JPanel messagePanel;
	
	private boolean isChatExpanded = false;
	
	private Vector<Chat> chats = new Vector<>();
	private Vector<JPanel> panels = new Vector<>();
	private Student owner;
	
	public ChatWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginPanel = new JPanel();
		
		username = new JTextField();
		username.setText("Name");
		username.setPreferredSize(new Dimension(200, 30));
		loginPanel.add(username);
		
		JButton login = new JButton();
		login.setText("Log in");
		login.setPreferredSize(new Dimension(120, 30));
		login.addActionListener(event -> onLogin());
		loginPanel.add(login);
		
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		
		chatPane = new JTabbedPane();
		chatPane.setPreferredSize(new Dimension(600, 570));
		chatPanel.add(chatPane, BorderLayout.CENTER);
		
		messagePanel = new JPanel();
		messagePanel.setLayout(new FlowLayout());
		messagePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control ENTER"), "Toggle");
		messagePanel.getActionMap().put("Toggle", new ToggleChat());
		
		messageField = new JTextField();
		messageField.setPreferredSize(new Dimension(525, 30));
		messagePanel.add(messageField);
		
		messageArea = new JTextArea();
		messageArea.setPreferredSize(new Dimension(525, 300));
		
		sendButton = new JButton();
		sendButton.setPreferredSize(new Dimension(75, 30));
		sendButton.setText("Send");
		sendButton.addActionListener(event -> onSend());
		messagePanel.add(sendButton);
		
		add(loginPanel);
		pack();
		revalidate();
		setVisible(true);
	}
	
	private void onLogin() {
		owner = new Student(username.getText());
		owner.setClient(this);
		
		remove(loginPanel);
		add(chatPanel);
		add(messagePanel, BorderLayout.SOUTH);

		for (Chat chat : chats)
			chat.addStudent(owner);

		pack();
		revalidate();
		repaint();
	}
	
	private void onSend() {
		Chat chat = chats.elementAt(chatPane.getSelectedIndex());
		chat.addMessage(owner, isChatExpanded ? messageArea.getText() : messageField.getText());
		
		messageArea.setText("");
		messageField.setText("");
	}
	
	void onRecieve(String msg, Chat chat) {
		JPanel panel = panels.get(chats.indexOf(chat));
		
		JLabel msgLabel = new JLabel();
		msgLabel.setText(msg);
		msgLabel.setPreferredSize(new Dimension(600, 30));
		panel.add(msgLabel);

		pack();
		revalidate();
		repaint();
	}
	
	void joinGroup(Chat chat) {
		chats.add(chat);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panels.add(panel);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatPane.addTab("Group", scrollPane);
		
		if (owner != null)
			chat.addStudent(owner);
	}
	
	private class ToggleChat extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private final Dimension expandedSize = new Dimension(600, 300);
		private final Dimension unexpandedSize = new Dimension(600, 570);
		
		public void actionPerformed(ActionEvent arg0) {
			isChatExpanded = !isChatExpanded;
			JTextComponent adding;
			JTextComponent removing;
			Dimension size;
			
			if (isChatExpanded) {
				adding = messageArea;
				removing = messageField;
				size = expandedSize;
			} else {
				adding = messageField;
				removing = messageArea;
				size = unexpandedSize;
			}
			
			chatPanel.setPreferredSize(size);
			
			messagePanel.remove(removing);
			messagePanel.remove(sendButton);
			
			messagePanel.add(adding);
			messagePanel.add(sendButton);
			
			adding.setText(removing.getText());
			adding.grabFocus();

			pack();
			revalidate();
			repaint();
		}
	}
	
}
