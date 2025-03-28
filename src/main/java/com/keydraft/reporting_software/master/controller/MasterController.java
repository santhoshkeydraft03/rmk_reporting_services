package com.keydraft.reporting_software.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keydraft.reporting_software.master.model.ExpenseType;
import com.keydraft.reporting_software.master.model.Ledger;
import com.keydraft.reporting_software.master.model.ExpenseGroup;
import com.keydraft.reporting_software.master.model.OtherExpenses;
import com.keydraft.reporting_software.master.model.OtherIncomes;
import com.keydraft.reporting_software.master.model.Product;
import com.keydraft.reporting_software.master.model.Plant;
import com.keydraft.reporting_software.master.model.Bucket;
import com.keydraft.reporting_software.master.service.MasterService;

@RestController
@CrossOrigin
@RequestMapping("/api/master")
public class MasterController {
    @Autowired
    private MasterService service;

    // Expense Type
    @GetMapping("/expense-types")
    public List<ExpenseType> getAllExpenseTypes() {
        return service.getAllExpenseTypes();
    }

    @GetMapping("/expense-types/{id}")
    public ExpenseType getExpenseTypeById(@PathVariable Long id) {
        return service.getExpenseTypeById(id);
    }

    @PostMapping("/expense-types")
    public ExpenseType createExpenseType(@RequestBody ExpenseType expenseType) {
        return service.createExpenseType(expenseType);
    }

    @DeleteMapping("/expense-types/{id}")
    public void deleteExpenseType(@PathVariable Long id) {
        service.deleteExpenseType(id);
    }

    // Expense Group
    @GetMapping("/expense-groups")
    public List<ExpenseGroup> getAllExpenseGroups() {
        return service.getAllExpenseGroups();
    }

    @GetMapping("/expense-groups/{id}")
    public ExpenseGroup getExpenseGroupById(@PathVariable Long id) {
        return service.getExpenseGroupById(id);
    }

    @PostMapping("/expense-groups")
    public ExpenseGroup createExpenseGroup(@RequestBody ExpenseGroup expenseGroup) {
        return service.createExpenseGroup(expenseGroup);
    }

    @DeleteMapping("/expense-groups/{id}")
    public void deleteExpenseGroup(@PathVariable Long id) {
        service.deleteExpenseGroup(id);
    }

    // Other Expenses
    @GetMapping("/other-expenses")
    public List<OtherExpenses> getAllOtherExpenses() {
        return service.getAllOtherExpenses();
    }

    @GetMapping("/other-expenses/{id}")
    public OtherExpenses getOtherExpensesById(@PathVariable Long id) {
        return service.getOtherExpensesById(id);
    }

    @PostMapping("/other-expenses")
    public OtherExpenses createOtherExpenses(@RequestBody OtherExpenses otherExpenses) {
        return service.createOtherExpenses(otherExpenses);
    }

    @DeleteMapping("/other-expenses/{id}")
    public void deleteOtherExpenses(@PathVariable Long id) {
        service.deleteOtherExpenses(id);
    }

    // Other Incomes
    @GetMapping("/other-incomes")
    public List<OtherIncomes> getAllOtherIncomes() {
        return service.getAllOtherIncomes();
    }

    @GetMapping("/other-incomes/{id}")
    public OtherIncomes getOtherIncomesById(@PathVariable Long id) {
        return service.getOtherIncomesById(id);
    }

    @PostMapping("/other-incomes")
    public OtherIncomes createOtherIncomes(@RequestBody OtherIncomes otherIncomes) {
        return service.createOtherIncomes(otherIncomes);
    }

    @DeleteMapping("/other-incomes/{id}")
    public void deleteOtherIncomes(@PathVariable Long id) {
        service.deleteOtherIncomes(id);
    }

    // Products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    // Plants
    @GetMapping("/plants")
    public List<Plant> getAllPlants() {
        return service.getAllPlants();
    }

    @GetMapping("/plants/{id}")
    public Plant getPlantById(@PathVariable Long id) {
        return service.getPlantById(id);
    }

    @PostMapping("/plants")
    public Plant createPlant(@RequestBody Plant plant) {
        return service.createPlant(plant);
    }

    @DeleteMapping("/plants/{id}")
    public void deletePlant(@PathVariable Long id) {
        service.deletePlant(id);
    }

    @GetMapping("/plants/quarries")
    public List<Plant> getAllQuarries() {
        return service.getAllQuarries();
    }

    // Buckets
    @GetMapping("/buckets")
    public List<Bucket> getAllBuckets() {
        return service.getAllBuckets();
    }

    @GetMapping("/buckets/{id}")
    public Bucket getBucketById(@PathVariable long id) {
        return service.getBucketById(id);
    }

    @PostMapping("/buckets")
    public Bucket createBucket(@RequestBody Bucket bucket) {
        return service.createBucket(bucket);
    }

    @DeleteMapping("/buckets/{id}")
    public void deleteBucket(@PathVariable long id) {
        service.deleteBucket(id);
    }

    // Ledger CRUD endpoints
    @GetMapping("/ledger")
    public List<Ledger> getAllLedger() {
        return service.getAllLedger();
    }
    
    @GetMapping("/ledger/{id}")
    public Ledger getLedgerById(@PathVariable Long id) {
        return service.getLedgerById(id);
    }
    
    @PostMapping("/ledger")
    public Ledger createLedger(@RequestBody Ledger ledger) {
        return service.createLedger(ledger);
    }
    
    @DeleteMapping("/ledger/{id}")
    public void deleteLedger(@PathVariable Long id) {
        service.deleteLedger(id);
    }
}
