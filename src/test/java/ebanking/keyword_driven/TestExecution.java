package ebanking.keyword_driven;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.ExcelHelper;
import utilities.GenericHelper;

/**
 * Unit test for simple App.
 */
public class TestExecution extends BaseReport{

	public static void main(String[] args) {
		// create an object of ExtentReports class
		ExtentReports reports = new ExtentReports();
		// create an object of ExtentHtmlReporter
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(GenericHelper.getFilePath("reports", "report.html"));
		// attach the reporter to the reports class object
		reports.attachReporter(reporter);
		
		
		// create Keywords class object
		Keywords keywords = new Keywords();
		// read the data from both testcases and teststeps excel
		ExcelHelper tcd = new ExcelHelper();
		tcd.openExcel("resources", "testcases.xlsx", "testcases");
		ExcelHelper tsd = new ExcelHelper();
		tsd.openExcel("resources", "testcases.xlsx", "teststeps");
		// iterate over every row of tcd
		for (int i = 1; i <= tcd.getRows(); i++) {
			String tcd_tcname = tcd.readCell(i, 0);
			String runMode = tcd.readCell(i, 1);
			if (runMode.equalsIgnoreCase("yes")) {
				test = reports.createTest(tcd_tcname);
				// iterate over every row in test steps document
				for (int j = 1; j <= tsd.getRows(); j++) {
					String tsd_tcname = tsd.readCell(j, 0);
					if(tcd_tcname.equals(tsd_tcname)) {
						String tsname = tsd.readCell(j, 1);
						String locType = tsd.readCell(j, 2);
						String locValue = tsd.readCell(j, 3);
						String keyword = tsd.readCell(j, 4);
						String testData = tsd.readCell(j, 5);
						System.out.println("executing "+tsname);
						keywords.call(keyword, locType, locValue, testData);
					}
				}

			}
		}
		
		// save the report
		reports.flush();
		

	}

}
