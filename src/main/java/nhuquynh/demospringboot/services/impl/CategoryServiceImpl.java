package nhuquynh.demospringboot.services.impl;

import nhuquynh.demospringboot.entity.Category;
import nhuquynh.demospringboot.entity.repository.CategoryRepository;
import nhuquynh.demospringboot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
//   có constructor này mới kêu được những phương thức bên dưới
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
//  đếm tất cả các mẫu tin trong category
    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return categoryRepository.findAll(example, pageable);
    }

    @Override
    public Page<Category> findByNameContaining(String name, Pageable pageable) {
        return categoryRepository.findByNameContaining(name, pageable);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
//    @Override
//    public Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable) {
//        return categoryRepository.findByCategoryNameContaining(categoryName, pageable);
//    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return categoryRepository.findById(integer);
    }
}
