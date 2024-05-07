package com.example.FXDeals.repository;


import com.example.FXDeals.model.FXDeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FXDealsRepository extends JpaRepository<FXDeals, Long> {
        @Query(value = "SELECT COUNT(*) AS ExistenceCount\n" +
                "FROM (\n" +
                "    SELECT CurrencyCode\n" +
                "    FROM iso_currency\n" +
                "    WHERE CurrencyCode = ?1\n" +
                "    UNION ALL\n" +
                "    SELECT CurrencyCode\n" +
                "    FROM iso_currency\n" +
                "    WHERE CurrencyCode = ?2\n" +
                ") AS temp;\n", nativeQuery = true)
        int checkIfCurrencyCodeExist(String getFromCurrencyIso , String getToCurrencyIso);

}