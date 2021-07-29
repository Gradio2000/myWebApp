package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.CompanyDao;
import ru.laskin.myWebApp.model.Company;
import ru.laskin.myWebApp.model.Position;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyDao companyDao;
    private final PositionService positionService;

    public CompanyService(CompanyDao companyDao, PositionService positionService) {
        this.companyDao = companyDao;
        this.positionService = positionService;
    }

    public List<Company> getAllCompanies() {
       return companyDao.getAllCompany();
    }

    public Company getCompanyById(Integer company_id) {
        return companyDao.getCompanyById(company_id);
    }

    public void saveCompany(String companyName) {
        Company company = new Company();
        company.setCompanyName(companyName);
        companyDao.saveCompany(company);
        Position position = new Position();
        position.setPosition("Администратор");
        position.setCompanyId(company.getIdCompany());
        positionService.addPosition(position);
    }
}
