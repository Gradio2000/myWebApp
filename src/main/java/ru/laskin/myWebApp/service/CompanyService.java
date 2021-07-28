package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.CompanyDao;
import ru.laskin.myWebApp.model.Company;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public List<Company> getAllCompanies() {
       return companyDao.getAllCompany();
    }

    public Company getCompanyById(Integer company_id) {
        return companyDao.getCompanyById(company_id);
    }
}
