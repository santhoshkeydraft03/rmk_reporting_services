package com.keydraft.reporting_software.input.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.keydraft.reporting_software.input.dto.ClosingStockDTO;
import com.keydraft.reporting_software.input.dto.ExpenseDTO;
import com.keydraft.reporting_software.input.dto.IncomeDTO;
import com.keydraft.reporting_software.input.dto.LedgerEntryDTO;
import com.keydraft.reporting_software.input.dto.SalesDTO;
import com.keydraft.reporting_software.input.dto.VsiHoursDTO;
import com.keydraft.reporting_software.input.dto.InwardConsumptionSlurryDTO;
import com.keydraft.reporting_software.input.service.InputService;

@RestController
@RequestMapping("/api/input")
@CrossOrigin(origins = "*")
@Tag(name = "Input Controller", description = "Endpoints for importing various data from Excel files")
public class InputController {

    @Autowired
    private InputService inputService;

    // ______________________ SALES ______________________
    @PostMapping("/import-sales")
    public ResponseEntity<String> importSales(@RequestBody List<Map<String, Object>> salesData) {
        try {
            inputService.importSalesData(salesData);
            return ResponseEntity.ok("Sales data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import sales data: " + e.getMessage());
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<?> getSales() {
        try {
            List<SalesDTO> sales = inputService.getAllSales();
            return ResponseEntity.ok(sales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve sales data: " + e.getMessage());
        }
    }

    @GetMapping("/check-sales-exists")
    public ResponseEntity<Boolean> checkSalesExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkSalesExists(month, year);
        return ResponseEntity.ok(exists);
    }

    // ______________________ LEDGER ______________________
    @PostMapping(path = "/import-ledger-entries")
    public ResponseEntity<String> importLedgerEntries(
            @RequestBody List<Map<String, Object>> ledgerEntriesData) {
        try {
            inputService.importLedgerEntries(ledgerEntriesData);
            return ResponseEntity.ok("Ledger entries imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import ledger entries: " + e.getMessage());
        }
    }

    @GetMapping("/ledger-entries")
    public ResponseEntity<?> getLedgerEntries() {
        try {
            List<LedgerEntryDTO> ledgerEntries = inputService.getAllLedgerEntries();
            return ResponseEntity.ok(ledgerEntries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve sales data: " + e.getMessage());
        }
    }

    @GetMapping("/check-ledger-exists")
    public ResponseEntity<Boolean> checkLedgerExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkLedgerExists(month, year);
        return ResponseEntity.ok(exists);
    }

    // ______________________ INCOME ______________________
    @PostMapping(path = "/import-income")
    public ResponseEntity<String> importIncome(
            @RequestBody List<Map<String, Object>> incomeData) {
        try {
            inputService.importIncomeData(incomeData);
            return ResponseEntity.ok("Income data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import income data: " + e.getMessage());
        }
    }

    @GetMapping("/income")
    public ResponseEntity<?> getIncome() {
        try {
            List<IncomeDTO> income = inputService.getAllIncome();
            return ResponseEntity.ok(income);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve income data: " + e.getMessage());
        }
    }

    @GetMapping("/check-income-exists")
    public ResponseEntity<Boolean> checkIncomeExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkIncomeExists(month, year);
        return ResponseEntity.ok(exists);
    }

    // ______________________ EXPENSE ______________________
    @PostMapping(path = "/import-expense")
    public ResponseEntity<String> importExpense(
            @RequestBody List<Map<String, Object>> expenseData) {
        try {
            inputService.importExpenseData(expenseData);
            return ResponseEntity.ok("Expense data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import expense data: " + e.getMessage());
        }
    }

    @GetMapping("/expense")
    public ResponseEntity<?> getExpense() {
        try {
            List<ExpenseDTO> expense = inputService.getAllExpense();
            return ResponseEntity.ok(expense);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve income data: " + e.getMessage());
        }
    }

    @GetMapping("/check-expense-exists")
    public ResponseEntity<Boolean> checkExpenseExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkExpenseExists(month, year);
        return ResponseEntity.ok(exists);
    }

    // ______________________ CLOSING STOCK ______________________
    @PostMapping(path = "/import-closing-stock")
    public ResponseEntity<String> importClosingStock(
            @RequestBody List<Map<String, Object>> closingStockData) {
        try {
            inputService.importClosingStockData(closingStockData);
            return ResponseEntity.ok("Closing stock data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import closing stock data: " + e.getMessage());
        }
    }

    @GetMapping("/closing-stock")
    public ResponseEntity<?> getClosingStock() {
        try {
            List<ClosingStockDTO> closingStock = inputService.getAllClosingStock();
            return ResponseEntity.ok(closingStock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve closing stock data: " + e.getMessage());
        }
    }

    @GetMapping("/check-closing-stock-exists")
    public ResponseEntity<Boolean> checkClosingStockExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkClosingStockExists(month, year);
        return ResponseEntity.ok(exists);
    }

    // ______________________ VSI HOURS ______________________
    @PostMapping(path = "/import-vsi-hours")
    public ResponseEntity<String> importVsiHours(@RequestBody List<Map<String, Object>> vsiHoursData) {
        try {
            inputService.importVsiHoursData(vsiHoursData);
            return ResponseEntity.ok("VSI hours data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import VSI hours data: " + e.getMessage());
        }
    }

    @GetMapping("/vsi-hours")
    public ResponseEntity<?> getVsiHours() {
        try {
            List<VsiHoursDTO> vsiHours = inputService.getAllVsiHours();
            return ResponseEntity.ok(vsiHours);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve closing stock data: " + e.getMessage());
        }
    }

    @GetMapping("/check-vsi-hours-exists")
    public ResponseEntity<Boolean> checkVsiHoursExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkVsiHoursExists(month, year);
        return ResponseEntity.ok(exists);
    }

    // ______________________ INWARD CONSUMPTION SLURRY ______________________
    @PostMapping("/import-inward-consumption-slurry")
    public ResponseEntity<String> importInwardConsumptionSlurry(
            @RequestBody List<Map<String, Object>> inwardConsumptionSlurryData) {
        try {
            inputService.importInwardConsumptionSlurryData(inwardConsumptionSlurryData);
            return ResponseEntity.ok("Inward consumption slurry data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import inward consumption slurry data: " + e.getMessage());
        }
    }

    @GetMapping("/inward-consumption-slurry")
    public ResponseEntity<?> getInwardConsumptionSlurry() {
        try {
            List<InwardConsumptionSlurryDTO> inwardConsumptionSlurry = inputService.getAllInwardConsumptionSlurry();
            return ResponseEntity.ok(inwardConsumptionSlurry);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve inward consumption slurry data: " + e.getMessage());
        }
    }

    @GetMapping("/check-inward-consumption-slurry-exists")
    public ResponseEntity<Boolean> checkInwardConsumptionSlurryExists(
            @RequestParam("month") String month,
            @RequestParam("year") String year) {
        boolean exists = inputService.checkInwardConsumptionSlurryExists(month, year);
        return ResponseEntity.ok(exists);
    }
}
