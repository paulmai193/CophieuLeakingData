package logia.cophieu.report.form;

import java.util.List;

import javax.swing.JProgressBar;

import logia.cophieu.model.DataInterface;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Interface ReportInterface.
 * 
 * @author Paul Mai
 */
public interface ReportInterface extends AutoCloseable {

	/**
	 * Creates the data.
	 *
	 * @param __datas the __datas
	 * @param __sheet the __sheet
	 * @param __progressBar the __progress bar
	 */
	public void createData(List<DataInterface> __datas, SheetInterface __sheet, JProgressBar __progressBar);

	/**
	 * Export report.
	 */
	public void exportReport();

	/**
	 * Gets the workbook.
	 *
	 * @return the workbook
	 */
	public XSSFWorkbook getWorkbook();
}
