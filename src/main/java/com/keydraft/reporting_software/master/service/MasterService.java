package com.keydraft.reporting_software.master.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keydraft.reporting_software.master.model.ExpenseType;
import com.keydraft.reporting_software.master.model.Ledger;
import com.keydraft.reporting_software.master.model.ExpenseGroup;
import com.keydraft.reporting_software.master.model.OtherExpenses;
import com.keydraft.reporting_software.master.model.OtherIncomes;
import com.keydraft.reporting_software.master.model.Product;
import com.keydraft.reporting_software.master.model.Plant;
import com.keydraft.reporting_software.common.enums.PlantType;
import com.keydraft.reporting_software.master.model.Bucket;
import com.keydraft.reporting_software.master.repository.ExpenseTypeRepository;
import com.keydraft.reporting_software.master.repository.ExpenseGroupRepository;
import com.keydraft.reporting_software.master.repository.OtherExpensesRepository;
import com.keydraft.reporting_software.master.repository.OtherIncomesRepository;
import com.keydraft.reporting_software.master.repository.ProductRepository;
import com.keydraft.reporting_software.master.repository.PlantRepository;
import com.keydraft.reporting_software.master.repository.BucketRepository;
import com.keydraft.reporting_software.master.repository.LedgerRepository;

@Service
public class MasterService {

    @Autowired
    private ExpenseTypeRepository repository;
    
    @Autowired
    private ExpenseGroupRepository expenseGroupRepository;
    
    @Autowired
    private OtherExpensesRepository otherExpensesRepository;
    
    @Autowired
    private OtherIncomesRepository otherIncomesRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private LedgerRepository ledgerRepository;

    // ExpenseType CRUD operations
    public List<ExpenseType> getAllExpenseTypes() {
        return repository.findAll();
    }

    public ExpenseType getExpenseTypeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ExpenseType createExpenseType(ExpenseType expenseType) {
        if(expenseType.getExpenseTypeId() <1) {
            if (repository.existsByExpenseTypeName(expenseType.getExpenseTypeName())) {
                throw new RuntimeException("Expense type with name " + expenseType.getExpenseTypeName() + " already exists");
            }
        }
        return repository.save(expenseType);
    }

    public void deleteExpenseType(Long id) {
        repository.deleteById(id);
    }

    // ExpenseGroup CRUD operations
    public List<ExpenseGroup> getAllExpenseGroups() {
        return expenseGroupRepository.findAll();
    }

    public ExpenseGroup getExpenseGroupById(Long id) {
        return expenseGroupRepository.findById(id).orElse(null);
    }

    public ExpenseGroup createExpenseGroup(ExpenseGroup expenseGroup) {
        if(expenseGroup.getExpenseGroupId() < 1) {
            if (expenseGroupRepository.existsByNameAndExpenseType_ExpenseTypeId(expenseGroup.getName(), expenseGroup.getExpenseType().getExpenseTypeId())) {
                throw new RuntimeException("Expense group with name " + expenseGroup.getName() + " already exists");
            }
        }
        return expenseGroupRepository.save(expenseGroup);
    }


    public void deleteExpenseGroup(Long id) {
        expenseGroupRepository.deleteById(id);
    }

    // OtherExpenses CRUD operations
    public List<OtherExpenses> getAllOtherExpenses() {
        return otherExpensesRepository.findAll();
    }

    public OtherExpenses getOtherExpensesById(Long id) {
        return otherExpensesRepository.findById(id).orElse(null);
    }

    public OtherExpenses createOtherExpenses(OtherExpenses otherExpenses) {
        if(otherExpenses.getOtherExpenseId() < 1) {
            if (otherExpensesRepository.existsByExpenseType(otherExpenses.getExpenseType())) {
                throw new RuntimeException("Other expense with type " + otherExpenses.getExpenseType() + " already exists");
            }
        }
        return otherExpensesRepository.save(otherExpenses);
    }

    public void deleteOtherExpenses(Long id) {
        otherExpensesRepository.deleteById(id);
    }

    // OtherIncomes CRUD operations
    public List<OtherIncomes> getAllOtherIncomes() {
        return otherIncomesRepository.findAll();
    }

    public OtherIncomes getOtherIncomesById(Long id) {
        return otherIncomesRepository.findById(id).orElse(null);
    }

    public OtherIncomes createOtherIncomes(OtherIncomes otherIncomes) {
        if(otherIncomes.getOtherIncomesId() < 1) {
            if (otherIncomesRepository.existsByIncomeType(otherIncomes.getIncomeType())) {
                throw new RuntimeException("Other income with type " + otherIncomes.getIncomeType() + " already exists");
            }
        }
        return otherIncomesRepository.save(otherIncomes);
    }

    public void deleteOtherIncomes(Long id) {
        otherIncomesRepository.deleteById(id);
    }

    // Product CRUD operations
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        if (product.getQuarry() != null) {
            // Fetch the complete Plant object
            Plant quarry = plantRepository.findById(product.getQuarry().getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));
            
            // Validate that the plant is a quarry
            if (!quarry.getPlantType().equals(PlantType.QUARRY)) {
                throw new RuntimeException("Selected plant '" + quarry.getPlantName() 
                    + "' is not a quarry. Only quarry type plants can be mapped.");
            }
        }
        
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Plant CRUD operations
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantById(Long id) {
        return plantRepository.findById(id).orElse(null);
    }

    public Plant createPlant(Plant plant) {
        if(plant.getPlantId() < 1) {
            if (plantRepository.existsByPlantName(plant.getPlantName())) {
                throw new RuntimeException("Plant with name " + plant.getPlantName() + " already exists");
            }
        }
        return plantRepository.save(plant);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    public List<Plant> getAllQuarries() {
        return plantRepository.findByPlantType(PlantType.QUARRY);
    }

    // Bucket CRUD operations
    public List<Bucket> getAllBuckets() {
        return bucketRepository.findAll();
    }

    public Bucket getBucketById(Long id) {
        return bucketRepository.findById(id).orElse(null);
    }

    public Bucket createBucket(Bucket bucket) {
        if(bucket.getBucketId() < 1) {
            if (bucketRepository.existsByBucketName(bucket.getBucketName())) {
                throw new RuntimeException("Bucket with name " + bucket.getBucketName() + " already exists");
            }
        }
        return bucketRepository.save(bucket);
    }

    public void deleteBucket(Long id) {
        bucketRepository.deleteById(id);
    }

    // Ledger CRUD operations
    public List<Ledger> getAllLedger() {
        return ledgerRepository.findAll();
    }

    public Ledger getLedgerById(Long id) {
        return ledgerRepository.findById(id).orElse(null);
    }

    public Ledger createLedger(Ledger ledger) {
        
        // Rest of your validation code
        if (ledger.getExpenseType() != null && ledger.getExpenseGroup() != null) {
            // Fetch the complete ExpenseGroup from database
            ExpenseGroup completeExpenseGroup = expenseGroupRepository.findById(ledger.getExpenseGroup().getExpenseGroupId())
                    .orElseThrow(() -> new RuntimeException("ExpenseGroup not found"));
            
            // Compare using the fetched complete data
            if (completeExpenseGroup.getExpenseType().getExpenseTypeId() != 
                ledger.getExpenseType().getExpenseTypeId()) {
                throw new RuntimeException("Selected Expense Group '" + completeExpenseGroup.getName() 
                    + "' does not belong to the selected Expense Type '" 
                    + ledger.getExpenseType().getExpenseTypeName() + "'");
            }
        }
        return ledgerRepository.save(ledger);
    }

    public void deleteLedger(Long id) {
        ledgerRepository.deleteById(id);
    }
}