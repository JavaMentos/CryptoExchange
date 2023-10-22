package ru.home.crypto.service.impl;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.home.crypto.dto.CoinExportDTO;
import ru.home.crypto.entity.Coin;
import ru.home.crypto.mapper.CoinMapper;
import ru.home.crypto.repository.CoinRepository;
import ru.home.crypto.service.ExchangeService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.home.crypto.utils.WorkbookUtils.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExchangeServiceImpl implements ExchangeService {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

    /**
     * Экспортирует список монет в архивированный zip файл XLSX.
     *
     * @return byte[] содержащий архивированный zip файл XLSX.
     * @throws BadRequestException Если произошла ошибка при формировании XLSX.
     */
    public byte[] exportCoinsToZippedXlsx() {
        List<Coin> allCoins = coinRepository.findAll();

        List<CoinExportDTO> exportedCoins  = allCoins.stream()
                .map(coinMapper::coinToCoinExportDTO)
                .toList();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            createHeaderRow(sheet);

            fillDataInSheet(sheet, exportedCoins);

            byte[] bytes = workbookToByteArray(workbook);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            return createZip(byteArrayOutputStream, bytes);

        } catch (IOException e) {
            String errorMessage = "Ошибка при формировании xlsx";
            log.error(errorMessage, e);
            throw new BadRequestException(errorMessage);
        }

    }

    /**
     * Создает архив ZIP из набора byte[] файла и записывает его в byte[]
     *
     * @param byteArrayOutputStream Поток для записи архива ZIP в byte[]
     * @param fileData              Данные файла, которые нужно добавить в архив.
     * @return byte[] содержащий архив ZIP.
     * @throws RuntimeException Если произошла ошибка при создании архива ZIP.
     */
    private byte[] createZip(ByteArrayOutputStream byteArrayOutputStream, byte[] fileData) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
             ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData)) {

            ZipEntry zipEntry = new ZipEntry(UUID.randomUUID() + FORMAT_FILE);
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = byteArrayInputStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }

            zipOutputStream.closeEntry();
            zipOutputStream.finish();

           return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для создания заголовка в листе.
     *
     * @param sheet Лист workbook, в который будет добавлен заголовок.
     */
    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue(COIN_ID);
        headerRow.createCell(1).setCellValue(COIN_NAME);
        headerRow.createCell(2).setCellValue(COIN_RATING);
        headerRow.createCell(3).setCellValue(COIN_TICKER);
        headerRow.createCell(4).setCellValue(COIN_AMOUNT);
    }


    /**
     * Метод для заполнения данных в workbook.
     *
     * @param sheet           Лист workbook, в который будут добавляться данные.
     * @param coinExportDTOS  Лист монет, которые нужно записать в лист эксель.
     */
    private void fillDataInSheet(Sheet sheet, List<CoinExportDTO> coinExportDTOS) {
        coinExportDTOS.forEach(dto -> {
            int rowNum = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowNum);

            row.createCell(0).setCellValue(dto.coinId());
            row.createCell(1).setCellValue(dto.coinName());
            row.createCell(2).setCellValue(dto.coinRank());
            row.createCell(3).setCellValue(dto.coinSymbol());
            row.createCell(4).setCellValue(dto.currentPrice()
                    .setScale(10, RoundingMode.HALF_UP).toString());
        });
    }

    /**
     * Преобразует workbook в byte[]
     *
     * @param workbook Рабочая книга, которую необходимо преобразовать.
     * @return byte[] представляющий содержимое рабочей книги.
     * @throws BadRequestException Если произошла ошибка при записи в byte[]
     */
    private byte[] workbookToByteArray(Workbook workbook) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            String errorMessage = "Ошибка при записи xlsx в массив байтов";
            log.error(errorMessage, e);
            throw new BadRequestException(errorMessage);
        }
    }
}
