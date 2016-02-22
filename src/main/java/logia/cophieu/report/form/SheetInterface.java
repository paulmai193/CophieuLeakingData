package logia.cophieu.report.form;

import java.util.List;

import javax.swing.JProgressBar;

import logia.cophieu.model.DataInterface;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * The Interface SheetInterface.
 * 
 * @author Paul Mai
 */
public interface SheetInterface {

	/**
	 * Creates the cell.
	 *
	 * @param __rowIndex the __row index
	 * @param __columnIndex the __column index
	 * @param __content the __content
	 * @param __isBold the __is bold
	 * @param __align the __align
	 * @param __haveBorder the __have border
	 * @param __wrapText the __wrap text
	 * @return the XSSF cell
	 */
	public XSSFCell createCell(int __rowIndex, int __columnIndex, Object __content, boolean __isBold, byte __align, boolean __haveBorder,
			boolean __wrapText);

	/**
	 * Creates the data.
	 *
	 * @param __data the __data
	 */
	public void createData(DataInterface __data);

	/**
	 * Creates the data.
	 *
	 * @param __listData the __list data
	 * @param __progressBar the __progress bar
	 */
	public void createData(List<DataInterface> __listData, JProgressBar __progressBar);

	/**
	 * Creates the form.
	 */
	public void createForm();

	/**
	 * Gets the cell value.
	 *
	 * @param __rowIndex the __row index
	 * @param __columnIndex the __column index
	 * @return the cell value
	 */
	public String getCellValue(int __rowIndex, int __columnIndex);

	/**
	 * Gets the worksheet.
	 *
	 * @return the worksheet
	 */
	public XSSFSheet getWorksheet();
}
