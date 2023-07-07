package Bside.Dreamers.service;

import Bside.Dreamers.domin.Category;
import Bside.Dreamers.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    

    public List<Category> findCategory() {
        return categoryRepository.findAll();
    }
    
    public Category findOne(Long categoryNo) {
        return categoryRepository.findOne(categoryNo);
    }
}
