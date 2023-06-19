package br.com.senai.propetservice.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchRepository<T> {
    Page<T> searchBy(String text, String[] fields, Pageable pageable);
}
