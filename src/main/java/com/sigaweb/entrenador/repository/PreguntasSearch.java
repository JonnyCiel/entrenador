package com.sigaweb.entrenador.repository;

import com.sigaweb.entrenador.entities.Preguntas;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PreguntasSearch {

    // Spring will inject here the entity manager object
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * A basic search for the entity User. The search is done by exact match per
     * keywords on fields name, city and email.
     *
     * @param text The query text.
     */
    public Preguntas search(String text) {

        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Preguntas.class).get();

        // a very basic query by keywords
        org.apache.lucene.search.Query query =
                queryBuilder.
                        keyword().
                        onFields("texto").
                        matching(text).
                        createQuery();

        // wrap Lucene query in an Hibernate Query object
        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Preguntas.class);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        Preguntas result = (Preguntas) jpaQuery.getSingleResult();

        return  result;
    } // method search
}
