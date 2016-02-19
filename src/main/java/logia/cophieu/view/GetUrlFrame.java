package logia.cophieu.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logia.cophieu.controller.GetUrlController;
import logia.cophieu.process.GetUrlProcess;

/**
 * The Class GetUrlFrame.
 *
 * @author Paul Mai
 */
public class GetUrlFrame extends JFrame {

	// /** The Constant LOGGER. */
	// private static final Logger LOGGER = Logger.getLogger(GetUrlFrame.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The _btn browse. */
	private JButton           btnBrowseOutput;

	/** The _btn new button. */
	private JButton           btnBrowseInput;

	/** The _content pane. */
	private JPanel            contentPane;

	/** The _lbl link. */
	private JLabel            lblInput;

	/** The _lbl output. */
	private JLabel            lblOutput;

	/** The _progress bar. */
	private JProgressBar      progressBar;

	/** The _sp button. */
	private JSplitPane        spButton;

	/** The _sp component. */
	private JSplitPane        spComponent;

	/** The _sp label. */
	private JSplitPane        spLabel;

	/** The sp progress. */
	private JSplitPane        spProgress;

	/** The _text field. */
	private JTextField        txfInput;

	/** The _txf output. */
	private JTextField        txfOutput;

	/** The btn run. */
	private JButton           btnRun;

	/**
	 * Create the frame.
	 */
	public GetUrlFrame() {
		this.setResizable(false);
		this.setTitle("Export Data");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension _dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(450, 150);
		this.setLocation(_dim.width / 2 - this.getSize().width / 2, _dim.height / 2 - this.getSize().height / 2);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);

		JLabel _lblExportZaraData = new JLabel("EXPORT COPHIEU DATA FROM LINK");
		_lblExportZaraData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		_lblExportZaraData.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(_lblExportZaraData, BorderLayout.NORTH);

		this.spProgress = new JSplitPane();
		spProgress.setResizeWeight(0.99);
		this.spProgress.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.contentPane.add(this.spProgress, BorderLayout.SOUTH);

		this.progressBar = new JProgressBar();
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(GetUrlController.MAX);
		this.progressBar.setValue(0);
		this.progressBar.setStringPainted(true);
		this.progressBar.setString("");
		this.spProgress.setTopComponent(this.progressBar);

		this.btnRun = new JButton("Run");
		this.btnRun.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spProgress.setRightComponent(btnRun);

		// this.contentPane.add(this.progressBar, BorderLayout.SOUTH);

		this.spLabel = new JSplitPane();
		this.spLabel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.contentPane.add(this.spLabel, BorderLayout.WEST);

		this.lblInput = new JLabel("Input");
		this.lblInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.lblInput.setHorizontalAlignment(SwingConstants.CENTER);
		this.spLabel.setRightComponent(this.lblInput);

		this.lblOutput = new JLabel("Output");
		this.lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		this.spLabel.setLeftComponent(this.lblOutput);

		this.spComponent = new JSplitPane();
		this.spComponent.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.contentPane.add(this.spComponent, BorderLayout.CENTER);

		this.txfInput = new JTextField();
		txfInput.setEditable(false);
		this.spComponent.setRightComponent(this.txfInput);
		this.txfInput.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent __event) {
				if (__event.getKeyCode() == KeyEvent.VK_ENTER) {
					GetUrlProcess _process = new GetUrlProcess(GetUrlFrame.this);
					_process.start();
				}
			}
		});
		this.txfInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txfInput.setToolTipText("Type or copy Zara's product link here");
		this.txfInput.setColumns(10);

		this.txfOutput = new JTextField(System.getProperty("user.dir") + File.separator + "output");
		txfOutput.setEditable(false);
		this.txfOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.spComponent.setLeftComponent(this.txfOutput);
		this.txfOutput.setColumns(10);

		this.spButton = new JSplitPane();
		this.spButton.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.contentPane.add(this.spButton, BorderLayout.EAST);

		this.btnBrowseInput = new JButton("Browse");
		this.btnBrowseInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.spButton.setRightComponent(this.btnBrowseInput);

		this.btnBrowseOutput = new JButton("Browse");
		this.btnBrowseOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.btnBrowseOutput.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent __event) {
				JFileChooser _chooseOutputDirectory = new JFileChooser(GetUrlFrame.this.txfOutput.getText());
				_chooseOutputDirectory.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int _returnVal = _chooseOutputDirectory.showOpenDialog(null);
				if (_returnVal == JFileChooser.APPROVE_OPTION) {
					File _file = _chooseOutputDirectory.getSelectedFile();
					GetUrlFrame.this.txfOutput.setText(_file.getAbsolutePath());
				}
			}
		});
		this.spButton.setLeftComponent(this.btnBrowseOutput);
		this.btnBrowseInput.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent __event) {
				GetUrlProcess _process = new GetUrlProcess(GetUrlFrame.this);
				_process.start();
			}
		});
	}

	/**
	 * Gets the btn browse output.
	 *
	 * @return the btnBrowseOutput
	 */
	public JButton getBtnBrowseOutput() {
		return this.btnBrowseOutput;
	}

	/**
	 * Gets the btn browse input.
	 *
	 * @return the btnBrowseInput
	 */
	public JButton getBtnBrowseInput() {
		return this.btnBrowseInput;
	}

	/**
	 * Gets the content pane.
	 *
	 * @return the contentPane
	 */
	public JPanel getContentPane() {
		return this.contentPane;
	}

	/**
	 * Gets the lbl input.
	 *
	 * @return the lblInput
	 */
	public JLabel getLblInput() {
		return this.lblInput;
	}

	/**
	 * Gets the lbl output.
	 *
	 * @return the lblOutput
	 */
	public JLabel getLblOutput() {
		return this.lblOutput;
	}

	/**
	 * Gets the progress bar.
	 *
	 * @return the progressBar
	 */
	public JProgressBar getProgressBar() {
		return this.progressBar;
	}

	/**
	 * Gets the sp button.
	 *
	 * @return the spButton
	 */
	public JSplitPane getSpButton() {
		return this.spButton;
	}

	/**
	 * Gets the sp component.
	 *
	 * @return the spComponent
	 */
	public JSplitPane getSpComponent() {
		return this.spComponent;
	}

	/**
	 * Gets the sp label.
	 *
	 * @return the spLabel
	 */
	public JSplitPane getSpLabel() {
		return this.spLabel;
	}

	/**
	 * Gets the sp progress.
	 *
	 * @return the spProgress
	 */
	public JSplitPane getSpProgress() {
		return this.spProgress;
	}

	/**
	 * Gets the txf input.
	 *
	 * @return the txfInput
	 */
	public JTextField getTxfInput() {
		return this.txfInput;
	}

	/**
	 * Gets the txf output.
	 *
	 * @return the txfOutput
	 */
	public JTextField getTxfOutput() {
		return this.txfOutput;
	}

	/**
	 * Gets the btn run.
	 *
	 * @return the btnRun
	 */
	public JButton getBtnRun() {
		return this.btnRun;
	}

}
