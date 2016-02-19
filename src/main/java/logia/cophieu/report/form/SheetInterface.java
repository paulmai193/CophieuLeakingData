package logia.cophieu.report.form;

import java.util.List;

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
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 * @param content the content. Content is instance of Boolean, Calenda, Date, Double, RichTextString, String
	 * @param isBold the is bold
	 * @param align the align
	 * @param haveBorder the have border
	 * @param wrapText the wrap text
	 * @return the XSSF cell
	 */
	public XSSFCell createCell(int __rowIndex, int __columnIndex, Object __content, boolean __isBold, byte __align, boolean __haveBorder,
	        boolean __wrapText);

	/**
	 * Creates the data.
	 *
	 * @param listData the list data
	 */
	public void createData(List<DataInterface> __listData);

	/**
	 * Creates the data.
	 *
	 * @param __data the __data
	 */
	public void createData(DataInterface __data);

	/**
	 * Creates the form.
	 */
	public void createForm();

	/**
	 * Gets the worksheet.
	 *
	 * @return the worksheet
	 */
	public XSSFSheet getWorksheet();

	/**
	 * Gets the cell value.
	 *
	 * @param __rowIndex the __row index
	 * @param __columnIndex the __column index
	 * @return the cell value
	 */
	public String getCellValue(int __rowIndex, int __columnIndex);
}
