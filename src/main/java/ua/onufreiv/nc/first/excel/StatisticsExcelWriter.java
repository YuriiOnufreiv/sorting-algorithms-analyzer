package ua.onufreiv.nc.first.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.onufreiv.nc.first.fillers.Filler;
import ua.onufreiv.nc.first.reflection.ReflectionSorter;
import ua.onufreiv.nc.first.sorters.Sorting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class writes arrays sortings to the excel file
 *
 * @author  Yurii Onufreiv
 * @version 1.0
 * @since 02/12/2016
 */
public class StatisticsExcelWriter {
    private static final Logger logger = Logger.getLogger(ReflectionSorter.class.toString());
    private SortersStatistics statistics;

    /**
     * Parametrized constructor
     * @param statistics statistics that should be written to the file
     */
    public StatisticsExcelWriter(SortersStatistics statistics) {
        this.statistics = statistics;
    }

    /**
     * Writes statistics contained in {@code statistics} to the specified file
     * @param file file in which statistics will be written
     * @throws IOException
     */
    public void write(File file) throws IOException {
        // create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        Map<Filler.Type, List<SortersStatistics.StatisticsEntry>> map = statistics.getMap();
        for (Filler.Type fillerType : map.keySet()) {
            XSSFSheet spreadsheet = workbook.createSheet(fillerType.name());
            writeArraySizesRow(spreadsheet);
            int rowid = 1;
            writeSortAndTimeRow(spreadsheet, rowid, fillerType);
        }
        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        logger.info("Statistics has been written successfully");
    }

    /**
     * THis method writes rows with sorting type and it's duration in the specified spreadsheet
     * @param spreadsheet spreadsheet in which rows will be written
     * @param rowid id of row
     * @param fillerType type of filler of created row
     */
    private void writeSortAndTimeRow(XSSFSheet spreadsheet, int rowid, Filler.Type fillerType) {
        for (Sorting.Type sortingType : Sorting.Type.values()) {
            XSSFRow row = spreadsheet.createRow(rowid++);
            row.createCell(0).setCellValue(sortingType.name());
            int cellid = 1;
            for (Integer arrSize : statistics.getArraySizesSet()) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue(statistics.getTimeFor(fillerType, sortingType, arrSize));
            }
        }
    }

    /**
     * Writes sizes header to the specified spreadsheet
     * @param spreadsheet spreadsheet in which sizes row will be written
     */
    private void writeArraySizesRow(XSSFSheet spreadsheet) {
        XSSFRow row = spreadsheet.createRow(0);
        Set<Integer> arraySizes = statistics.getArraySizesSet();
        int cellid = 1;
        for (Integer size : arraySizes) {
            Cell cell = row.createCell(cellid++);
            cell.setCellValue(size.toString());
        }
    }
}
