package logia.cophieu.report.form.implement;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import logia.cophieu.model.DataInterface;
import logia.cophieu.model.GetUrlData;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CotucSheet extends AbstractSheet {

	private static final Map<Integer, Integer> YEAR_MAP = new HashMap<Integer, Integer>(); // {year, column}
	static {
		int _currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int _columnIndex = 5; _columnIndex <= _columnIndex + 10; _columnIndex++) {
			YEAR_MAP.put(_currentYear, _columnIndex);
			_currentYear++;
		}
	}

	public CotucSheet(XSSFWorkbook __workbook, String __sheetname) {
		super(__workbook, __sheetname);
	}

	@Override
	public void createData(List<DataInterface> __listData) {
		int _rowIndex = 1;
		for (DataInterface _data : __listData) {
			if (_data instanceof GetUrlData) {
				processShareData((GetUrlData) _data, _rowIndex);
			}
			else {

			}
		}

	}

	private void processShareData(GetUrlData __data, int __rowIndex) {
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
			if (_year == _calendar.get(Calendar.YEAR)) {
				_totalShare += _eachShare.getValue();
			}
			else {
				_year = _calendar.get(Calendar.YEAR);
				_totalShare = _eachShare.getValue();
			}

			// Put into report
			int _columnIndex = YEAR_MAP.get(_year);
			this.createCell(__rowIndex, _columnIndex, _totalShare, false, ALIGN_RIGHT, false, false);
		}
	}

	@Override
	public void createData(DataInterface __data) {
		if (__data instanceof GetUrlData) {
		}
		else {

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

}
