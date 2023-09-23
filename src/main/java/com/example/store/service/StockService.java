package com.example.store.service;

import com.example.store.entity.Stock;
import com.example.store.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

}