package logia.cophieu.report.form.implement;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JProgressBar;

import logia.cophieu.model.DataInterface;
import logia.cophieu.model.GetStockData;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CotucSheet extends AbstractSheet {

	private static final Map<Integer, Integer> YEAR_MAP = new HashMap<Integer, Integer>(); // {year, column}
	static {
		int _currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int _columnIndex = 15; _columnIndex >= 5; _columnIndex--) {
			CotucSheet.YEAR_MAP.put(_currentYear, _columnIndex);
			_currentYear--;
		}
	}

	public CotucSheet(XSSFWorkbook __workbook, String __sheetname) {
		super(__workbook, __sheetname);
	}

	@Override
	public void createData(DataInterface __data) {
	}

	@Override
	public void createData(List<DataInterface> __listData, JProgressBar __progressBar) {
		int _rowIndex = 1;
		__progressBar.setValue(0);
		for (DataInterface _data : __listData) {
			if (_data instanceof GetStockData) {
				this.processShareData((GetStockData) _data, _rowIndex);
				_rowIndex++;
				__progressBar.setValue(_rowIndex);
			}
		}

	}

	@Override
	public void createForm() {
		this.createCell(0, 0, "STT", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 1, "Mã", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 2, "Tên công ty", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 3, "Giá hiện tại", true, AbstractSheet.ALIGN_CENTER, false, true);
		this.createCell(0, 4, "Tỉ lệ sinh lời ước tính", true, AbstractSheet.ALIGN_CENTER, false, true);
	}

	private void processShareData(GetStockData __data, int __rowIndex) {
		// STT
		this.createCell(__rowIndex, 0, __rowIndex, false, AbstractSheet.ALIGN_CENTER, false, false);

		// MaCK
		this.createCell(__rowIndex, 1, __data.getMaCk(), false, AbstractSheet.ALIGN_CENTER, false, false);

		// Process share of each year

		Map<Date, Float> _shareMap = __data.getCoTuc();
		Calendar _calendar = Calendar.getInstance();
		int _year = 0;
		float _totalShare = 0;
		for (Entry<Date, Float> _eachShare : _shareMap.entrySet()) {
			_calendar.setTime(_eachShare.getKey());
			if (_eachShare.getValue() != null) {
				if (_year == _calendar.get(Calendar.YEAR)) {
					_totalShare += _eachShare.getValue();
				}
				else {
					_year = _calendar.get(Calendar.YEAR);
					_totalShare = _eachShare.getValue();
				}
			}

			// Put into report
			try {
				int _columnIndex = CotucSheet.YEAR_MAP.get(_year);
				this.createCell(0, _columnIndex, _year, true, AbstractSheet.ALIGN_CENTER, false, false);
				this.createCell(__rowIndex, _columnIndex, _totalShare, false, AbstractSheet.ALIGN_RIGHT, false, false);
				this.createCell(__rowIndex, 2, __data.getTenCty(), false, AbstractSheet.ALIGN_LEFT, false, false);
				this.createCell(__rowIndex, 3, __data.getGiaHienTai(), false, AbstractSheet.ALIGN_RIGHT, false, false);
			}
			catch (Exception __e) {
				this.LOGGER.error("Error data of year " + _year, __e);
			}

		}
	}

}
