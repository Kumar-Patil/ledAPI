package com.ledVan.repository;

import com.ledVan.model.LedDisplay;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LedDisplayRepository extends CrudRepository<LedDisplay, Long> {

    @Query("select count(e) from LedDisplay e where e.status=:status")
    long unreviewedReport(@Param("status") String status);

    @Query("select e from LedDisplay e where e.reportDate like %:reportDate%")
    List<LedDisplay> getDailyReportData(@Param("reportDate") String reportDate);

    @Query("select e from LedDisplay e where e.reportDate like %:reportDate% AND e.districtName=:districtName")
    List<LedDisplay> getMonthlyReportData(@Param("reportDate") String reportDate, @Param("districtName") String districtName);
    
    @Query("select count(e) from LedDisplay e where e.status !=:status")
    long reviewedReport(@Param("status") String status);
    
    @Query("select e from LedDisplay e where e.status=:status")
    List<LedDisplay> unReviewed(@Param("status") String status);
    
    @Query("select e from LedDisplay e where e.status !=:status")
    List<LedDisplay> reviewed(@Param("status") String status);
    
    @Query("select count(e) from LedDisplay e where e.status=:status and e.districtId=:districtId")
    long unreviewedReport(@Param("status") String status, @Param("districtId") long districtId);
    
    @Query("select count(e) from LedDisplay e where e.status !=:status and e.districtId=:districtId")
    long reviewedReport(@Param("status") String status, @Param("districtId") long districtId);
    
    @Query("select e from LedDisplay e where e.status=:status and e.districtId=:districtId")
    List<LedDisplay> unReviewed(@Param("status") String status, @Param("districtId") long districtId);
    
    @Query("select e from LedDisplay e where e.status !=:status and e.districtId=:districtId")
    List<LedDisplay> reviewed(@Param("status") String status, @Param("districtId") long districtId);
}
