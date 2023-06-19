package br.com.senai.propetservice.repository.search;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.senai.propetservice.models.exceptions.IndexException;
import jakarta.persistence.EntityManager;

@Component
@Transactional
public class Indexer {

    private EntityManager entityManager;

    private static final int THREAD_NUMBER = 4;

    public Indexer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void indexPersistedData() throws IndexException {

        try {
            SearchSession searchSession = Search.session(entityManager);
            MassIndexer indexer = searchSession
                    .massIndexer()
                    .threadsToLoadObjects(THREAD_NUMBER);

            indexer.startAndWait();
        } catch (InterruptedException e) {
            throw new IndexException("Index Interrupted", e);
        }
    }
}
