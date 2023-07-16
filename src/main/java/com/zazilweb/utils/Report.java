package com.zazilweb.utils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;

import java.sql.Connection;
import java.util.Map;

public class Report {

    //Este método produce un reporte segun los parametros especificados
    public static void hacerReporte(String reporte, Map<String, Object> parametros, Connection dataSource, String salida) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(reporte);
        // Create a data source (example: empty collection)
        //JRDataSource dataSources = new JREmptyDataSource();
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport, parametros, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, salida);
    }

}
