package br.com.senai.propetservice.repository.search;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.SearchSort;
import org.hibernate.search.engine.search.sort.dsl.FieldSortOptionsStep;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.scope.SearchScope;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.Assert;

import jakarta.persistence.EntityManager;

public class SearchRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements SearchRepository<T> {
    private final SearchSession searchSession;

    public SearchRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.searchSession = Search.session(entityManager);
    }

    public Page<T> searchBy(String text, String[] fields, Pageable pageable) {
        Assert.notNull(fields, "fields must not be null");
        Assert.notEmpty(fields, "fields must not be empty");

        var whereStep = searchSession.search(getDomainClass())
                .where(f -> f.match().fields(fields).matching(text).fuzzy())
                .sort(getSort(pageable.getSort(), getDomainClass()));

        if (pageable.isPaged()) {
            final var offset = PageableUtils.getOffsetAsInteger(pageable);
            final SearchResult<T> result = whereStep
                    .fetch(offset, pageable.getPageSize());
            return PageableExecutionUtils.getPage(result.hits(), pageable, () -> result.total().hitCount());
        } else {
            final SearchResult<T> result = whereStep.fetchAll();
            return new PageImpl<>(result.hits());
        }
    }

    private <S extends T> SearchSort getSort(Sort sort, Class<S> domainClass) {
        SearchScope<S> scope = searchSession.scope(domainClass);
        return sort.isUnsorted() ? scope.sort().score().toSort() : blabla(sort, scope);
    }

    private <S extends T> SearchSort blabla(Sort sort, SearchScope<S> scope) {
        final var orderList = sort.toList();

        Assert.notEmpty(orderList, "sort must not be empty");
        Assert.notNull(scope, "scope must not be null");

        final var first = orderList.get(0);

        var fieldSort = scope.sort().field(first.getProperty());
        var finalSort = getDirection(fieldSort, first.getDirection());

        if (orderList.size() > 1) {
            for (int i = 1; i < orderList.size(); i++) {
                SearchSortFactory factory = finalSort.then();
                Sort.Order order = orderList.get(i);
                var fieldStep = factory.field(order.getProperty());
                var orderStep2 = getDirection(fieldStep, order.getDirection());
                finalSort = orderStep2;
            }
            
        }

        return finalSort.toSort();
    }

    private FieldSortOptionsStep<?, ?> getDirection(FieldSortOptionsStep<?, ?> field, Sort.Direction direction) {
        return switch (direction) {
            case ASC -> field.asc();
            case DESC -> field.desc();
        };
    }
}
