package com.ssafy.mogakgong.service;

import com.ssafy.mogakgong.domain.Category;
import com.ssafy.mogakgong.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryList(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
