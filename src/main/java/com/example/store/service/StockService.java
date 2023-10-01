package com.example.store.service;

import com.example.store.entity.Stock;
import com.example.store.repository.StockRepository;
import com.example.store.repository.StockSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void addNew(Stock stock) {
        stockRepository.save(stock);
    }

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }

    public Stock getById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Stocks not found with id: " + id));
    }

    public void update(Stock stock) {
        stockRepository.save(stock);
    }

    public List<Stock> findStocksByFilters(Integer cellNumber, Long productId, Integer quantity) {
        Specification<Stock> spec1 = StockSpecifications.filterByCellNumber(cellNumber);
        Specification<Stock> spec2 = StockSpecifications.filterByProductId(productId);
        Specification<Stock> spec3 = StockSpecifications.filterByQuantity(quantity);

        Specification<Stock> finalSpec = Specification.where(spec1).and(spec2).and(spec3);

        return stockRepository.findAll(finalSpec);
    }
}