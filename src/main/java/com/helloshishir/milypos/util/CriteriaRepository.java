package com.helloshishir.milypos.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public <T> List<T> findAllWithFilter(int pageNumber, int pageSize, String sortDirection, String sortBy, String searchQuery, Class<T> resultClass) {

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(resultClass);

        Root<T> root = criteriaQuery.from(resultClass);

        String[] searchableAttributes = getSearchAttributes(resultClass);

        Predicate predicate = getPredicate(searchableAttributes, searchQuery, root);
        criteriaQuery.where(predicate);
        setOrder(sortDirection, sortBy, criteriaQuery, root);

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageNumber * pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    private <T> String[] getSearchAttributes(Class<T> resultClass) {
        try {
            Method[] fields = resultClass.getMethods();

            for (Method method: fields) {
                if(method.getName().equalsIgnoreCase("getSearchableAttributes")) {
                    String[] objects = (String[]) method.invoke(resultClass.getDeclaredConstructor().newInstance());
                    return objects;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private <T> Predicate getPredicate(String[] searchableAttributes, String searchQuery, Root<T> customerRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if(searchableAttributes != null) {
            for (String searchAttr : searchableAttributes) {
                predicates.add(criteriaBuilder.like(customerRoot.get(searchAttr), "%"+searchQuery+"%"));
            }
        }

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private <T> void setOrder(String sortDirection, String sortBy, CriteriaQuery<T> criteriaQuery, Root<T> root) {
        if(sortDirection.equalsIgnoreCase(String.valueOf(Sort.Direction.ASC))) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
        }else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
        }
    }

    public Pageable getPageable(int pageNumber,int pageSize, String sortDirection, String sortBy) {
        Sort sort = Sort.by(sortDirection, sortBy);
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    public <T> long getResultCount(String searchQuery, Class<T> resultClass) {

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

        Root<T> root = countQuery.from(resultClass);

        String[] searchableAttributes = getSearchAttributes(resultClass);

        Predicate predicate = getPredicate(searchableAttributes, searchQuery, root);

        countQuery.select(criteriaBuilder.count(root)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
