package logia.cophieu.report.form.implement;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JProgressBar;

import logia.cophieu.model.DataInterface;
import logia.cophieu.report.form.SheetInterface;

/**
 * The Class CophieuReport.
 *
 * @author Paul Mai
 */
public class CophieuReport extends AbstractReport {

	/**
	 * Instantiates a new cophieu report.
	 *
	 * @param __filePath the __file path
	 * @throws FileNotFoundException the file not found exception
	 */
	public CophieuReport(String __filePath) throws FileNotFoundException {
		super(__filePath);
	}

	/* (non-Javadoc)
	 * @see logia.cophieu.report.form.ReportInterface#createData(java.util.List, logia.cophieu.report.form.SheetInterface, javax.swing.JProgressBar)
	 */
	@Override
	public void createData(List<DataInterface> __datas, SheetInterface __sheet, JProgressBar __progressBar) {
		__sheet.createData(__datas, __progressBar);
	}

}
