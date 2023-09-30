package uz.pdp.networkcompany.service;

public interface USSDService {
    boolean existsByCode(String code);
    boolean existsByCodeAndIdNot(String code, Long id);
}
