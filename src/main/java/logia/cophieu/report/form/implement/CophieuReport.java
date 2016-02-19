package logia.cophieu.report.form.implement;

import java.io.FileNotFoundException;
import java.util.List;

import logia.cophieu.model.DataInterface;
import logia.cophieu.report.form.SheetInterface;

public class CophieuReport extends AbstractReport {

	public CophieuReport(String __filePath) throws FileNotFoundException {
		super(__filePath);
	}

	@Override
	public void createData(List<DataInterface> __datas, SheetInterface __sheet) {
		// TODO Auto-generated method stub

	}

}
