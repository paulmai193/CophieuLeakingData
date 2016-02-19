package logia.cophieu.report.form.implement;

import java.util.Calendar;
import java.util.Date;

import logia.cophieu.report.form.SheetInterface;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Class AbstractSheet.
 * 
 * @author Paul Mai
 */
public abstract class AbstractSheet implements SheetInterface {

	/** The Constant ALIGN_CENTER. */
	public static final byte  ALIGN_CENTER = 3;

	/** The Constant ALIGN_LEFT. */
	public static final byte  ALIGN_LEFT   = 1;

	/** The Constant ALIGN_RIGHT. */
	public static final byte  ALIGN_RIGHT  = 2;

	/** The logger. */
	protected final Logger    LOGGER       = Logger.getLogger(this.getClass());

	/** The worksheet. */
	protected final XSSFSheet worksheet;

	/**
	 * Instantiates a new abstract sheet.
	 *
	 * @param workbook the workbook
	 * @param sheetname the sheetname
	 */
	public AbstractSheet(XSSFWorkbook workbook, String sheetname) {
		this.worksheet = workbook.createSheet(sheetname);
		this.createForm();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowktv.report.form.SheetInterface#createCell(int, int, java.lang.Object, boolean, byte, boolean)
	 */
	@Override
	public XSSFCell createCell(int rowIndex, int columnIndex, Object content, boolean isBold, byte align, boolean haveBorder, boolean wrapText) {
		XSSFRow row = this.worksheet.getRow(rowIndex);
		if (row == null) {
			row = this.worksheet.createRow(rowIndex);
		}
		XSSFCell cell = row.createCell(columnIndex);

		if (content instanceof Boolean) {
			cell.setCellValue((boolean) content);
		}
		else if (content instanceof Calendar) {
			cell.setCellValue((Calendar) content);
		}
		else if (content instanceof Date) {
			cell.setCellValue((Date) content);
		}
		else if (content instanceof Number) {
			cell.setCellValue(((Number) content).doubleValue());
		}
		else {
			cell.setCellValue(content.toString());
		}

		// Set style
		XSSFCellStyle cellStyle = this.worksheet.getWorkbook().createCellStyle();

		if (wrapText) {
			// cellStyle.setWrapText(true);
			DataFormat format = this.worksheet.getWorkbook().createDataFormat();
			cellStyle.setDataFormat(format.getFormat("@"));
		}
		else {
			this.worksheet.autoSizeColumn(columnIndex, true);
		}

		// // Border
		if (haveBorder) {
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		}
		// // Align
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		switch (align) {
			case ALIGN_CENTER:
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				break;

			case ALIGN_RIGHT:
				cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
				break;

			default:
				cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
				break;
		}
		// // Set font
		XSSFFont cellFont = this.worksheet.getWorkbook().createFont();
		cellFont.setFontName("Arial");
		cellFont.setFontHeightInPoints((short) 11);
		if (isBold) {
			cellFont.setBold(true);
		}
		else {
			cellFont.setBold(false);
		}
		cellStyle.setFont(cellFont);

		cell.setCellStyle(cellStyle);

		return cell;
	}

	/**
	 * Gets the worksheet.
	 *
	 * @return the worksheet
	 */
	@Override
	public XSSFSheet getWorksheet() {
		return this.worksheet;
	}

	@Override
	public String getCellValue(int __rowIndex, int __columnIndex) {
		XSSFRow _row = this.worksheet.getRow(__rowIndex);
		XSSFCell _cell = _row.getCell(__columnIndex);
		return _cell.getRawValue();
	}
}
