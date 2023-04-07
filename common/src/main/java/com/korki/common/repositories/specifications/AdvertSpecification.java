package com.korki.common.repositories.specifications;


import com.korki.common.models.Advert;
import com.korki.common.models.Teacher;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AdvertSpecification{

    public static Specification<Advert> priceLessOrEqualsThan(float price){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<Advert> hasFreeLesson(){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("freeLesson"), 1);
    }
    public static Specification<Advert> isInLocation(List<String> locations){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("lessonLocation")).value(locations);
    }
    public static Specification<Advert> isInTeachingScope(List<String> scopes){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("teachingScopes")).value(scopes);
    }
    public static Specification<Advert> isInWeekdays(List<String> weekdays){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("weekdays")).value(weekdays);
    }
    public static Specification<Advert> hasMinEducation(int education){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.greaterThanOrEqualTo(teacherAdvertJoin.get("education"), education);
        };
    }
    public static Specification<Advert> hasMinExperience(int experience){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.greaterThanOrEqualTo(teacherAdvertJoin.get("experience"), experience);
        };
    }
    public static Specification<Advert> hasMinSeniority(int seniority){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.greaterThanOrEqualTo(teacherAdvertJoin.get("seniority"), seniority);
        };
    }
    public static Specification<Advert> olderThan(int age){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.greaterThanOrEqualTo(teacherAdvertJoin.get("age"), age);
        };
    }
    public static Specification<Advert> youngerThan(int age){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.lessThanOrEqualTo(teacherAdvertJoin.get("age"), age);
        };
    }
    public static Specification<Advert> gender(String gender){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.equal(teacherAdvertJoin.get("gender"), gender);
        };
    }
    public static Specification<Advert> city(String city){
        return (root, query, criteriaBuilder) -> {
            Join<Teacher, Advert> teacherAdvertJoin = root.join("teacher");
            return criteriaBuilder.equal(teacherAdvertJoin.get("city"), city);
        };
    }
}
