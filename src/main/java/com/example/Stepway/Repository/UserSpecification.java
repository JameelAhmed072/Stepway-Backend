package com.example.Stepway.Repository;

import com.example.Stepway.Domain.User;
import com.example.Stepway.dto.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    private SearchCriteria searchCriteria;

    public UserSpecification(SearchCriteria searchCriteria){
        this.searchCriteria = searchCriteria;
    }


    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(searchCriteria.getOperation().equalsIgnoreCase(":")){
            return criteriaBuilder.equal(root.get(searchCriteria.getKey()),searchCriteria.getValue());
        } else if (searchCriteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString());
        } else if (searchCriteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString());
        }
        return null;

    }
}
