package com.example.teamfresh.domain.order.service;

import com.example.teamfresh.domain.order.dto.OrderItemParam;
import com.example.teamfresh.domain.order.dto.OrderRegisterParam;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExcelOrderService {
    private final OrderService orderService;

    public void registerOrdersFromExcel(MultipartFile file) {
        try {
            Map<String, OrderRegisterParam> orderMap = parseExcel(file);

            for (OrderRegisterParam orderParam : orderMap.values()) {
                orderService.registerOrder(orderParam);
            }
        } catch (IOException e) {
            throw new RuntimeException("엑셀 파일을 읽는 중 오류 발생", e);
        }
    }
    private Map<String, OrderRegisterParam> parseExcel(MultipartFile file) throws IOException {
        Map<String, OrderRegisterParam> orderMap = new HashMap<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Long marketOrderNo = (long) row.getCell(0).getNumericCellValue();
            String customerName = row.getCell(1).getStringCellValue();
            String customerAddress = row.getCell(2).getStringCellValue();
            Long productId = (long) row.getCell(3).getNumericCellValue();
            int quantity = (int) row.getCell(4).getNumericCellValue();

            orderMap.computeIfAbsent(String.valueOf(marketOrderNo), k -> new OrderRegisterParam(marketOrderNo, customerName, customerAddress, new ArrayList<>()))
                    .getOrderItems().add(new OrderItemParam(productId, quantity));
        }

        workbook.close();
        return orderMap;
    }

}
