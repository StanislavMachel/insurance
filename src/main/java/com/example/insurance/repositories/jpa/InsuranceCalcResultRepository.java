package com.example.insurance.repositories.jpa;

import com.example.insurance.model.InsuranceCalcResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface InsuranceCalcResultRepository extends JpaRepository<InsuranceCalcResult, Long> {
    @Query(value = "SELECT icr FROM InsuranceCalcResult icr JOIN icr.parameterRisks pr WHERE pr.parameterName IN :includeParameterNames GROUP BY icr.id HAVING COUNT(pr.parameterName) = :includeParameterNamesCount")
    Page<InsuranceCalcResult> findAllByIncludeParameterNamesListWithPagination(@Param("includeParameterNames") Collection<String> includeParameterNames, @Param("includeParameterNamesCount") Long includeParameterNamesCount, Pageable pageable);
}
