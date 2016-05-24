package logia.cophieu.report.form.implement;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import logia.cophieu.model.DataInterface;
import logia.cophieu.model.GetStockData;
import logia.cophieu.model.database.DatabaseShareData;

/**
 * The Class CotucSheet.
 *
 * @author Paul Mai
 */
public class CotucSheet extends AbstractSheet {

	/** The Constant YEAR_MAP. */
	private static final Map<Integer, Integer> YEAR_MAP = new HashMap<Integer, Integer>(); // {year,
	                                                                                       // column}
	static {
		int _currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int _columnIndex = 16; _columnIndex >= 5; _columnIndex--) {
			CotucSheet.YEAR_MAP.put(_currentYear, _columnIndex);
			_currentYear--;
		}
	}

	/**
	 * Instantiates a new cotuc sheet.
	 *
	 * @param __workbook
	 *            the __workbook
	 * @param __sheetname
	 *            the __sheetname
	 */
	public CotucSheet(XSSFWorkbook __workbook, String __sheetname) {
		super(__workbook, __sheetname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.cophieu.report.form.SheetInterface#createData(logia.cophieu.model.DataInterface)
	 */
	@Override
	public void createData(DataInterface __data) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.cophieu.report.form.SheetInterface#createData(java.util.List,
	 * javax.swing.JProgressBar)
	 */
	@Override
	public void createData(List<DataInterface> __listData) {
		int _rowIndex = 1;
		for (DataInterface _data : __listData) {
			if (_data instanceof GetStockData) {
				this.processShareData((GetStockData) _data, _rowIndex);
				_rowIndex++;
			}
			else if (_data instanceof DatabaseShareData) {
				this.processShareData((DatabaseShareData) _data, _rowIndex);
				_rowIndex++;
			}
			System.out.println("Row " + _rowIndex);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.cophieu.report.form.SheetInterface#createForm()
	 */
	@Override
	public void createForm() {
		this.createCell(0, 0, "STT", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 1, "Mã", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 2, "Tên công ty", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 3, "Giá hiện tại", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 4, "Tỉ lệ sinh lời ước tính", true, AbstractSheet.ALIGN_CENTER, false,
		        true);
	}

	private void processShareData(DatabaseShareData __data, int __rowIndex) {
		// STT
		this.createCell(__rowIndex, 0, __rowIndex, false, AbstractSheet.ALIGN_CENTER, false, false);

		// MaCK
		this.createCell(__rowIndex, 1, __data.getMaCoPhieu().getMaCoPhieu(), false,
		        AbstractSheet.ALIGN_CENTER, false, false);

		// Default 0 for share value
		for (Entry<Integer, Integer> _eachItem : YEAR_MAP.entrySet()) {
			this.createCell(0, _eachItem.getValue(), _eachItem.getKey(), true, ALIGN_CENTER, false,
			        false);
			this.createCell(__rowIndex, _eachItem.getValue(), 0, false, ALIGN_RIGHT, false, false);
		}

		// Share value of each year
		int _year = __data.getNam();
		try {
			int _columnIndex = CotucSheet.YEAR_MAP.get(_year);
			this.createCell(0, _columnIndex, _year, true, AbstractSheet.ALIGN_CENTER, false, false);
			this.createCell(__rowIndex, _columnIndex, __data.getCoTuc(), false,
			        AbstractSheet.ALIGN_RIGHT, false, false);
			this.createCell(__rowIndex, 2, __data.getMaCoPhieu().getTenCongTy(), false,
			        AbstractSheet.ALIGN_LEFT, false, false);
			this.createCell(__rowIndex, 3, __data.getMaCoPhieu().getGiaHienTai(), false,
			        AbstractSheet.ALIGN_RIGHT, false, false);
		}
		catch (Exception __e) {
			this.LOGGER.error("Error data of year " + _year, __e);
		}

	}

	/**
	 * Process share data.
	 *
	 * @param __data
	 *            the __data
	 * @param __rowIndex
	 *            the __row index
	 */
	private void processShareData(GetStockData __data, int __rowIndex) {
		// STT
		this.createCell(__rowIndex, 0, __rowIndex, false, AbstractSheet.ALIGN_CENTER, false, false);

		// MaCK
		this.createCell(__rowIndex, 1, __data.getMaCk(), false, AbstractSheet.ALIGN_CENTER, false,
		        false);

		// Default 0 for share value
		// for (Entry<Integer, Integer> _eachItem : YEAR_MAP.entrySet()) {
		// this.createCell(0, _eachItem.getValue(), _eachItem.getKey(), true, ALIGN_CENTER, false,
		// false);
		// this.createCell(__rowIndex, _eachItem.getValue(), 0, false, ALIGN_RIGHT, false, false);
		// }

		// Process share of each year
		Map<Integer, Float> _shareMap = __data.getCoTuc();
		for (Entry<Integer, Float> _eachShare : _shareMap.entrySet()) {

			// Put into report
			int _year = _eachShare.getKey();
			try {
				int _columnIndex = CotucSheet.YEAR_MAP.get(_year);
				this.createCell(0, _columnIndex, _year, true, AbstractSheet.ALIGN_CENTER, false,
				        false);
				this.createCell(__rowIndex, _columnIndex, _eachShare.getValue(), false,
				        AbstractSheet.ALIGN_RIGHT, false, false);

			}
			catch (Exception __e) {
				this.LOGGER.error("Error data of year " + _year, __e);
			}
		}
		this.createCell(__rowIndex, 2, __data.getTenCty(), false, AbstractSheet.ALIGN_LEFT, false,
		        false);
		this.createCell(__rowIndex, 3, __data.getGiaHienTai(), false, AbstractSheet.ALIGN_RIGHT,
		        false, false);
	}

}
