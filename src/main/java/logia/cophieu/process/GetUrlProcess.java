package logia.cophieu.process;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import logia.cophieu.controller.GetUrlController;
import logia.cophieu.view.GetUrlFrame;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * The Class GetUrlProcess.
 *
 * @author Paul Mai
 */
public final class GetUrlProcess extends Thread {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GetUrlProcess.class);

	/** The frame. */
	private final GetUrlFrame   frame;

	/**
	 * Instantiates a new gets the url process.
	 *
	 * @param __frame the frame
	 */
	public GetUrlProcess(GetUrlFrame __frame) {
		this.frame = __frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// Disable UI components
		this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.frame.getBtnRun().setEnabled(false);
		this.frame.getBtnBrowseInput().setEnabled(false);
		this.frame.getBtnBrowseOutput().setEnabled(false);

		// Run scan data from url
		String _url = "http://www.cophieu68.vn/events.php";
		List<String> stocks = new ArrayList<String>();
		try {
			stocks = FileUtils.readLines(new File(this.frame.getTxfInput().getText()));

			GetUrlController controller = new GetUrlController(_url, this.frame.getTxfOutput().getText(), this.frame.getProgressBar(), stocks);
			controller.scanUrl();

			// Enable UI components
			this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.frame.getBtnRun().setEnabled(true);
			this.frame.getBtnBrowseInput().setEnabled(true);
			this.frame.getTxfOutput().setEnabled(true);

			JFileChooser chooseOutputDirectory = new JFileChooser(this.frame.getTxfOutput().getText());
			chooseOutputDirectory.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = chooseOutputDirectory.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					Desktop.getDesktop().open(chooseOutputDirectory.getSelectedFile());
				}
				catch (IOException e) {
					// Swallow this exception, not important
				}
			}
		}
		catch (IOException _e) {
			LOGGER.error(_e.getMessage(), _e);
		}

	}

}
