package br.com.senai.propetservice.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class FilterBuilder<T> {

    private final List<Specification<T>> specifications = new ArrayList<>();

    public void add(Specification<T> specification) {
        this.specifications.add(specification);
    }

    public Specification<T> build() {
        if (specifications.size() == 0)
            return null;

        Specification<T> result = specifications.get(0);

        for (int i = 1; i < specifications.size(); i++) {
            result = Specification.where(result).and(specifications.get(i));
        }

        return result;
    }
}
