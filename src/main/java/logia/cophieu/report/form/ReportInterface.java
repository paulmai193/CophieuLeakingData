package logia.cophieu.report.form;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import logia.cophieu.model.DataInterface;

/**
 * The Interface ReportInterface.
 * 
 * @author Paul Mai
 */
public interface ReportInterface extends AutoCloseable {

	/**
	 * Creates the data.
	 *
	 * @param __datas
	 *            the __datas
	 * @param __sheet
	 *            the __sheet
	 */
	public void createData(List<DataInterface> __datas, SheetInterface __sheet);

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
