package logia.cophieu.report.form.implement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import logia.cophieu.report.form.ReportInterface;

/**
 * The Class AbstractReport.
 * 
 * @author Paul Mai
 */
public abstract class AbstractReport implements ReportInterface {

	/** The export file. */
	protected final FileOutputStream exportFile;

	/** The logger. */
	protected final Logger			 LOGGER	= Logger.getLogger(this.getClass());

	/** The workbook. */
	protected final XSSFWorkbook	 workbook;

	/**
	 * Instantiates a new abstract report.
	 *
	 * @param filePath
	 *            the file path
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public AbstractReport(String filePath) throws FileNotFoundException {
		this.exportFile = new FileOutputStream(filePath);
		this.workbook = new XSSFWorkbook();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() throws Exception {
		this.exportFile.close();
		this.workbook.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowktv.report.form.ReportInterface#exportReport()
	 */
	@Override
	public void exportReport() {
		try {
			this.workbook.write(this.exportFile);
			this.exportFile.flush();
		}
		catch (IOException e) {
			this.LOGGER.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowktv.report.form.ReportInterface#getWorkbook()
	 */
	@Override
	public XSSFWorkbook getWorkbook() {
		return this.workbook;
	}
}
