package logia.cophieu.report.form;

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
	 * @param idBranch the id branch
	 * @param from the from
	 * @param to the to
	 * @param dataSheet the data sheet
	 */
	public void createData(Integer idBranch, String from, String to, SheetInterface dataSheet);

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
