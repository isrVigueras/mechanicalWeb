/**
 *   Copyright 2013 Nekorp
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.tikal.tallerWeb.servicio.reporte.cliente;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.tikal.tallerWeb.modelo.reporte.cliente.ReporteCliente;

/**
 *
 */
@Component
public class DatosAutoReporteCliente extends AbstractSeccionXLS implements GeneradorSeccionXLS {

    private String[] encabezados = new String[] {
        "Marca",
        "Tipo",
        "Version",
        "Serie",
        "Modelo",
        "Color",
        "Placas",
        "Kilometraje"
    };
    private String[] atributos = new String[] {
        "marca",
        "tipo",
        "version",
        "serie",
        "modelo",
        "color",
        "placas",
        "kilometraje"
    };
    @Override
    public BordeSeccion generar(BordeSeccion borde, ContextoSeccion contexto, ReporteCliente datos) {
        Sheet sheet = contexto.getSheet();
        XSSFWorkbook wb = contexto.getWb();
        int initialRow = borde.getUpperRow();
        int initialColumn = borde.getLeftColumn();
        BordeSeccion r = new BordeSeccion();
        r.setLeftColumn(initialColumn);
        r.setUpperRow(initialRow);
        
        //escribir el primer renglon
        Row row = getRow(sheet, initialRow);
        Cell cell = row.createCell(initialColumn);
        cell.setCellValue("Auto");
        //estilo .-.
        XSSFCellStyle cellStyle = wb.createCellStyle();
        addHeaderStyle(cellStyle, wb);
        addBorders(wb, cellStyle, CellStyle.BORDER_THIN);
        cell.setCellStyle(cellStyle);
        for (int i = 1; i < encabezados.length; i++) {
            cell = row.createCell(initialColumn + i);
            cellStyle = wb.createCellStyle();
            addBorders(wb, cellStyle, CellStyle.BORDER_THIN);
            cell.setCellStyle(cellStyle);
        }
        //merge de celdas
        sheet.addMergedRegion(new CellRangeAddress(
                initialRow, //first row (0-based)
                initialRow, //last row  (0-based)
                initialColumn, //first column (0-based)
                initialColumn + 7  //last column  (0-based)
        ));
        //segundo renglon encabezado
        initialRow = initialRow + 1;
        row = getRow(sheet, initialRow);
        for (int i = 0; i < encabezados.length; i++) {
            cell = row.createCell(initialColumn + i);
            cell.setCellValue(encabezados[i]);
            cellStyle = wb.createCellStyle();
            addHeaderStyle(cellStyle, wb);
            addBorders(wb, cellStyle, CellStyle.BORDER_THIN);
            cell.setCellStyle(cellStyle);
        }
        //tercer renglon encabezado
        initialRow = initialRow + 1;
        row = getRow(sheet, initialRow);
        for (int i = 0; i < atributos.length; i++) {
            cell = row.createCell(initialColumn + i);
            try {
                cell.setCellValue(PropertyUtils.getProperty(datos, "auto." + atributos[i]).toString());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                cell.setCellValue("");
            }
            cellStyle = wb.createCellStyle();
            addBorders(wb, cellStyle, CellStyle.BORDER_THIN);
            cell.setCellStyle(cellStyle);
        }
        
        r.setLowerRow(initialRow);
        r.setRightColumn(initialColumn + 7);
        paintBorder(wb, sheet, CellStyle.BORDER_MEDIUM, r);
        return r;
    }

}
