package com.keydraft.reporting_software.input.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keydraft.reporting_software.common.enums.BillingStatus;
import com.keydraft.reporting_software.common.enums.PaymentType;
import com.keydraft.reporting_software.common.enums.ProductStatus;
import com.keydraft.reporting_software.input.dto.ClosingStockDTO;
import com.keydraft.reporting_software.input.dto.ExpenseDTO;
import com.keydraft.reporting_software.input.dto.IncomeDTO;
import com.keydraft.reporting_software.input.dto.LedgerEntryDTO;
import com.keydraft.reporting_software.input.dto.SalesDTO;
import com.keydraft.reporting_software.input.dto.VsiHoursDTO;
import com.keydraft.reporting_software.input.model.ClosingStock;
import com.keydraft.reporting_software.input.model.Expense;
import com.keydraft.reporting_software.input.model.Income;
import com.keydraft.reporting_software.input.model.LedgerEntry;
import com.keydraft.reporting_software.input.model.Sales;
import com.keydraft.reporting_software.input.model.VsiHours;
import com.keydraft.reporting_software.input.repository.ClosingStockRepository;
import com.keydraft.reporting_software.input.repository.ExpenseRepository;
import com.keydraft.reporting_software.input.repository.IncomeRepository;
import com.keydraft.reporting_software.input.repository.LedgerEntryRepository;
import com.keydraft.reporting_software.input.repository.SalesRepository;
import com.keydraft.reporting_software.input.repository.VsiHoursRepository;
import com.keydraft.reporting_software.master.model.Ledger;
import com.keydraft.reporting_software.master.model.OtherExpenses;
import com.keydraft.reporting_software.master.model.OtherIncomes;
import com.keydraft.reporting_software.master.model.Product;
import com.keydraft.reporting_software.master.repository.LedgerRepository;
import com.keydraft.reporting_software.master.repository.OtherExpensesRepository;
import com.keydraft.reporting_software.master.repository.OtherIncomesRepository;
import com.keydraft.reporting_software.master.repository.ProductRepository;
import com.keydraft.reporting_software.master.model.Plant;
import com.keydraft.reporting_software.master.repository.PlantRepository;
import com.keydraft.reporting_software.common.enums.PlantType;

@Service
public class InputService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private LedgerEntryRepository ledgerEntryRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ClosingStockRepository closingStockRepository;

    @Autowired
    private VsiHoursRepository vsiHoursRepository;

    @Autowired
    private OtherIncomesRepository otherIncomesRepository;

    @Autowired
    private OtherExpensesRepository otherExpensesRepository;

    @Autowired
    private PlantRepository plantRepository;

    // ______________________ SALES ______________________
    public void importSalesData(List<Map<String, Object>> salesDataList) {
        try {
            // Create product map for quick lookup
            List<Product> allProducts = productRepository.findAll();
            Map<String, Product> productMap = new HashMap<>();
            for (Product product : allProducts) {
                String key = product.getProductName() + "|" + product.getQuarry().getShortName();
                productMap.put(key, product);
            }

            List<Sales> salesList = new ArrayList<>();

            // Process each row of data
            for (Map<String, Object> row : salesDataList) {
                String productName = (String) row.get("productName");
                String quarryName = (String) row.get("quarryName");
                String key = productName + "|" + quarryName;

                Product product = productMap.get(key);
                if (product != null) {
                    Sales sales = new Sales();
                    sales.setProduct(product);
                    sales.setQuarry(product.getQuarry());
                    sales.setSalesInTons(Double.valueOf(row.get("salesInTons").toString()));
                    sales.setSalesInValue(new BigDecimal(row.get("salesInValue").toString()));
                    sales.setMonth((String) row.get("month"));
                    sales.setYear((String) row.get("year"));

                    // Production/Stock
                    String productionStatus = (String) row.get("productionStatus");
                    sales.setProductionStatus(productionStatus.equalsIgnoreCase("Stock")
                            ? ProductStatus.STOCK
                            : ProductStatus.PRODUCTION);

                    // Billed Or Unbilled
                    String billingStatus = (String) row.get("billingStatus");
                    sales.setBillingStatus(billingStatus.equalsIgnoreCase("Billed")
                            ? BillingStatus.BILLED
                            : BillingStatus.UNBILLED);

                    // GST/CASH
                    String paymentType = (String) row.get("paymentType");
                    sales.setPaymentType(paymentType.equalsIgnoreCase("GST")
                            ? PaymentType.GST
                            : PaymentType.CASH);

                    // GST Value
                    sales.setGstValue(new BigDecimal(row.get("gstValue").toString()));

                    salesList.add(sales);
                    productMap.remove(key);
                }
            }

            // Save all sales entries to the repository
            salesRepository.saveAll(salesList);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<SalesDTO> getAllSales() {
        return salesRepository.getAllSales();
    }

    public boolean checkSalesExists(String month, String year) {
        return salesRepository.existsByMonthAndYear(month, year);
    }

    // ______________________ LEDGER ______________________
    public void importLedgerEntries(List<Map<String, Object>> ledgerEntriesData) {
        try {
            // Get all ledgers for lookup
            List<Ledger> allLedgers = ledgerRepository.findAll();
            Map<String, Ledger> ledgerMap = new HashMap<>();
            for (Ledger ledger : allLedgers) {
                ledgerMap.put(ledger.getLedgerName(), ledger);
            }

            List<LedgerEntry> entries = new ArrayList<>();

            // Process each entry
            for (Map<String, Object> row : ledgerEntriesData) {
                String ledgerName = (String) row.get("ledgerName");
                BigDecimal amount = new BigDecimal(row.get("amount").toString());
                String month = (String) row.get("month");
                String year = (String) row.get("year");

                Ledger ledger = ledgerMap.get(ledgerName);
                if (ledger != null) {
                    LedgerEntry entry = new LedgerEntry();
                    entry.setLedger(ledger);
                    entry.setAmount(amount);
                    entry.setMonth(month);
                    entry.setYear(year);
                    entries.add(entry);
                }
            }

            // Save all ledger entries to the repository
            ledgerEntryRepository.saveAll(entries);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process ledger entries: " + e.getMessage());
        }
    }

    public List<LedgerEntryDTO> getAllLedgerEntries() {
        return ledgerEntryRepository.getAllLedgerEntries();
    }

    public boolean checkLedgerExists(String month, String year) {
        return ledgerEntryRepository.existsByMonthAndYear(month, year);
    }

    // ______________________ INCOME ______________________
    public void importIncomeData(List<Map<String, Object>> incomeData) {
        try {
            // Get all other incomes for lookup
            List<OtherIncomes> otherIncomesList = otherIncomesRepository.findAll();
            Map<String, OtherIncomes> otherIncomesMap = new HashMap<>();
            for (OtherIncomes otherIncomes : otherIncomesList) {
                otherIncomesMap.put(otherIncomes.getIncomeType(), otherIncomes);
            }

            List<Income> incomeList = new ArrayList<>();

            // Process each row of data
            for (Map<String, Object> row : incomeData) {
                String incomeName = (String) row.get("incomeType");
                BigDecimal amount = new BigDecimal(row.get("amount").toString());
                String month = (String) row.get("month");
                String year = (String) row.get("year");

                OtherIncomes otherIncomes = otherIncomesMap.get(incomeName);
                if (otherIncomes != null) {
                    Income income = new Income();
                    income.setOtherIncomes(otherIncomes);
                    income.setAmount(amount);
                    income.setMonth(month);
                    income.setYear(year);
                    incomeList.add(income);
                }
            }

            // Save all income entries to the repository
            incomeRepository.saveAll(incomeList);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process income data: " + e.getMessage());
        }
    }

    public List<IncomeDTO> getAllIncome() {
        return incomeRepository.getAllIncome();
    }

    public boolean checkIncomeExists(String month, String year) {
        return incomeRepository.existsByMonthAndYear(month, year);
    }

    // ______________________ EXPENSE ______________________
    public void importExpenseData(List<Map<String, Object>> expenseData) {
        try {
            // Get all other expenses for lookup
            List<OtherExpenses> otherExpensesList = otherExpensesRepository.findAll();
            Map<String, OtherExpenses> otherExpensesMap = new HashMap<>();
            for (OtherExpenses otherExpenses : otherExpensesList) {
                otherExpensesMap.put(otherExpenses.getExpenseType(), otherExpenses);
            }

            List<Expense> expenseList = new ArrayList<>();

            // Process each row of data
            for (Map<String, Object> row : expenseData) {
                String expenseName = (String) row.get("expenseType");
                BigDecimal amount = new BigDecimal(row.get("amount").toString());
                String month = (String) row.get("month");
                String year = (String) row.get("year");

                OtherExpenses otherExpenses = otherExpensesMap.get(expenseName);
                if (otherExpenses != null) {
                    Expense expense = new Expense();
                    expense.setOtherExpenses(otherExpenses);
                    expense.setAmount(amount);
                    expense.setMonth(month);
                    expense.setYear(year);
                    expenseList.add(expense);
                }
            }

            // Save all expense entries to the repository
            expenseRepository.saveAll(expenseList);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process expense data: " + e.getMessage());
        }
    }

    public List<ExpenseDTO> getAllExpense() {
        return expenseRepository.getAllExpense();
    }

    public boolean checkExpenseExists(String month, String year) {
        return expenseRepository.existsByMonthAndYear(month, year);
    }

    // ______________________ CLOSING STOCK ______________________
    public void importClosingStockData(List<Map<String, Object>> closingStockData) {
        try {
            // Fetch all products and create a map
            List<Product> allProducts = productRepository.findAll();
            Map<String, Product> productMap = new HashMap<>();
            for (Product product : allProducts) {
                String key = product.getProductName().toLowerCase().trim() + "|"
                        + product.getQuarry().getShortName().toLowerCase().trim();
                productMap.put(key, product);
            }

            List<ClosingStock> stockList = new ArrayList<>();

            // Declare month and year variables outside the loops
            String month = null;
            String year = null;

            // Process each row of data
            for (Map<String, Object> row : closingStockData) {
                String productName = (String) row.get("productName");
                String quarryName = (String) row.get("quarryName");
                Double stockValue = Double.valueOf(row.get("closingStockInTons").toString());
                // Store month and year from the first row
                if (month == null) {
                    month = (String) row.get("month");
                    year = (String) row.get("year");
                }

                String key = productName.toLowerCase().trim() + "|" + quarryName.toLowerCase().trim();
                Product product = productMap.get(key);

                if (product != null) {
                    ClosingStock stock = new ClosingStock();
                    stock.setProduct(product);
                    stock.setQuarry(product.getQuarry());
                    stock.setClosingStockInTons(stockValue);
                    stock.setMonth(month);
                    stock.setYear(year);
                    stockList.add(stock);
                    productMap.remove(key);
                }
            }

            // Create zero-value entries for remaining products
            for (Product product : productMap.values()) {
                ClosingStock stock = new ClosingStock();
                stock.setProduct(product);
                stock.setQuarry(product.getQuarry());
                stock.setClosingStockInTons(0.0);
                stock.setMonth(month);
                stock.setYear(year);
                stockList.add(stock);
            }

            // Save all closing stock entries to the repository
            closingStockRepository.saveAll(stockList);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process closing stock data: " + e.getMessage());
        }
    }

    public List<ClosingStockDTO> getAllClosingStock() {
        return closingStockRepository.getAllClosingStock();
    }

    public boolean checkClosingStockExists(String month, String year) {
        return closingStockRepository.existsByMonthAndYear(month, year);
    }

    // ______________________ VSI HOURS ______________________
    public void importVsiHoursData(List<Map<String, Object>> vsiHoursData) {
        try {
            // Get all plants with type QUARRY
            List<Plant> allQuarries = plantRepository.findByPlantType(PlantType.QUARRY);
            Map<String, Plant> quarryMap = new HashMap<>();
            for (Plant quarry : allQuarries) {
                quarryMap.put(quarry.getShortName(), quarry);
            }

            List<VsiHours> vsiHoursList = new ArrayList<>();

            // Declare month and year variables outside the loops
            String month = null;
            String year = null;

            // Process each row of data
            for (Map<String, Object> row : vsiHoursData) {
                String quarryName = (String) row.get("quarryName");
                Double hours = Double.valueOf(row.get("vsiHours").toString());

                // Store month and year from the first row
                if (month == null) {
                    month = (String) row.get("month");
                    year = (String) row.get("year");
                }

                if (quarryName != null && !quarryName.trim().isEmpty()) {
                    Plant quarry = quarryMap.get(quarryName.trim());
                    if (quarry != null) {
                        VsiHours vsiHours = new VsiHours();
                        vsiHours.setQuarry(quarry);
                        vsiHours.setVsiHours(hours);
                        vsiHours.setMonth(month);
                        vsiHours.setYear(year);
                        vsiHoursList.add(vsiHours);
                        quarryMap.remove(quarryName);
                    }
                }
            }

            // Add default entries (0 hours) for quarries not in input
            for (Plant quarry : quarryMap.values()) {
                VsiHours vsiHours = new VsiHours();
                vsiHours.setQuarry(quarry);
                vsiHours.setVsiHours(0.0);
                vsiHours.setMonth(month);
                vsiHours.setYear(year);
                vsiHoursList.add(vsiHours);
            }

            // Save all VSI hours entries to the repository
            vsiHoursRepository.saveAll(vsiHoursList);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process VSI hours data: " + e.getMessage());
        }
    }

    public List<VsiHoursDTO> getAllVsiHours() {
        return vsiHoursRepository.getAllVsiHours();
    }

    public boolean checkVsiHoursExists(String month, String year) {
        return vsiHoursRepository.existsByMonthAndYear(month, year);
    }

}
