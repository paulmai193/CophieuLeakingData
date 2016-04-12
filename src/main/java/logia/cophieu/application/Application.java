package logia.cophieu.application;

import java.io.File;
import java.util.List;

import logia.cophieu.controller.GetUrlController;
import logia.hibernate.util.HibernateUtil;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * The Class Application.
 *
 * @author Paul Mai
 */
public final class Application {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(Application.class);

	/** The _frame. */
	// private GetUrlFrame _frame;

	/**
	 * Create the application.
	 */
	public Application() {
		// this.initialize();
	}

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// EventQueue.invokeLater(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		// Application window = new Application();
		// window._frame.setVisible(true);
		// }
		// catch (Exception e) {
		// Application.LOGGER.error("Error when running application", e);
		// }
		// }
		// });

		HibernateUtil.setConfigPath("hibernate.cfg.xml");
		try {
			if (args[0].equalsIgnoreCase("scandata")) {
				List<String> _stocks = FileUtils.readLines(new File(args[1]));
				if (args.length >= 3) {
					Integer _begin = Integer.parseInt(args[2]);
					Integer _end;
					if (args.length == 4) {
						_end = Integer.parseInt(args[3]);
					}
					else {
						_end = _stocks.size();
					}
					_stocks = _stocks.subList(_begin, _end);
				}
				GetUrlController.scanData(_stocks);
			}
			else if (args[0].equalsIgnoreCase("exportdata")) {
				GetUrlController.exportData();
			}
			else if (args[0].equalsIgnoreCase("initdata")) {
				List<String> _stocks = FileUtils.readLines(new File(args[1]));
				if (args.length >= 3) {
					Integer _begin = Integer.parseInt(args[2]);
					Integer _end;
					if (args.length == 4) {
						_end = Integer.parseInt(args[3]);
					}
					else {
						_end = _stocks.size();
					}
					_stocks = _stocks.subList(_begin, _end);
				}
				GetUrlController.initData(_stocks);
			}
		}
		catch (Exception _ex) {
			_ex.printStackTrace();
		}
		finally {
			HibernateUtil.releaseFactory();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	// private void initialize() {
	// this._frame = new GetUrlFrame();
	// }

}
