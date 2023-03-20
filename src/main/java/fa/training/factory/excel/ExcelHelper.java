package fa.training.factory.excel;

import fa.training.entites.Category;
import fa.training.entites.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "id","productName", "desc", "price","pricesSale","color","image","create_day",
            "category_id","isDeleted"};
    static String SHEET = "product";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Product> excelToProduct(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Product product = new Product();


                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            product.setProductName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            product.setDesc(currentCell.getStringCellValue());
                            break;
                        case 2:
                            product.setPrice((long) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            product.setPricesSale((long) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            product.setColor(currentCell.getStringCellValue());
                            break;
                        case 5:
                            product.setImage(currentCell.getStringCellValue());
                            break;
                        case 6:
                            Category category = new Category();
                            category.setCategoryId((long) currentCell.getNumericCellValue());
                            product.setCategory(category);
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                products.add(product);
            }
            workbook.close();
            return products;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream productToExcel(List<Product> products) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }
            int rowIdx = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getProductName());
                row.createCell(2).setCellValue(product.getDesc());
                row.createCell(3).setCellValue(product.getPrice());
                row.createCell(4).setCellValue(product.getPricesSale());
                row.createCell(5).setCellValue(product.getColor());
                row.createCell(6).setCellValue(product.getImage());
                row.createCell(7).setCellValue(product.getCreateDay());
                row.createCell(8).setCellValue(product.getCategory().getCategoryName());
                row.createCell(9).setCellValue(product.isDeleted());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
