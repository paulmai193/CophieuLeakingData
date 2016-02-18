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
	 * @param content the content
	 * @param isBold the is bold
	 * @param align the align
	 * @param haveBorder the have border
	 * @return the XSSF cell
	 */
	@Deprecated
	public XSSFCell createCell(int rowIndex, int columnIndex, String content, boolean isBold, byte align, boolean haveBorder);

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
	public XSSFCell createCell(int rowIndex, int columnIndex, Object content, boolean isBold, byte align, boolean haveBorder, boolean wrapText);

	/**
	 * Creates the data.
	 *
	 * @param listData the list data
	 */
	public void createData(List<DataInterface> listData);

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
}
