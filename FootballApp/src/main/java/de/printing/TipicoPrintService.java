package de.printing;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import de.business.TipicoModel;
import de.services.ResourceService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

public class TipicoPrintService {

	/**
	 * @param args unused
	 * @throws ParseException 
	 * @throws FileNotFoundException 
	 */
	public static boolean printTipicoTableModel(List<TipicoModel> pTipicoList) {

		JasperReport jasperReport;
		JasperPrint jasperPrint;

		HashMap<String, Object> parameter = new HashMap<String, Object>();

		parameter.put("monat", LocalDate.now().getMonth().toString());
		parameter.put("user", "Rene Frank");
		

		//		String[] header = { "tnr", "team", "winValue", "expenses", "attempts", "date", "success" };
		//
		//		JRCsvDataSource csv = new JRCsvDataSource(new File(System.getProperty("user.dir") + "/src/main/resources/printing/test.csv"));
		//		csv.setFieldDelimiter(';');
		//		csv.setColumnNames(header);

		Collection<Map<String,?>> fields = new ArrayList<Map<String,?>>();
		HashMap<String, String> hm;
		
		for (TipicoModel lTipicoModel : pTipicoList){
			hm = new HashMap<String, String>();
			hm.put("tnr", lTipicoModel.getID());
			hm.put("team", lTipicoModel.getTeam());
			hm.put("winValue", lTipicoModel.getWinValue() + "");
			hm.put("expenses", lTipicoModel.getExpenses() + "");
			hm.put("date", lTipicoModel.getDate().toString());
			hm.put("success", lTipicoModel.getSuccess() + "");
			fields.add(hm);
		}
		
		JRMapCollectionDataSource ds = new JRMapCollectionDataSource(fields);

		try {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String lFile = fc.getSelectedFile().getAbsolutePath();
				if (!(lFile.endsWith(".pdf") || lFile.endsWith(".PDF"))){
					lFile += ".pdf";
				}
				jasperReport = JasperCompileManager.compileReport(ResourceService.getInstance().getResourceJRMXL("TipicoGesamtUebersicht.jrxml"));
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, ds);
				JasperExportManager.exportReportToPdfFile(jasperPrint, lFile);
			}

			return true;
		} catch (JRException e) {
			e.printStackTrace();
			return false;
		}
	}
}