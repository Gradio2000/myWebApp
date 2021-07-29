package ru.laskin.myWebApp.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Company;
import ru.laskin.myWebApp.utils.EntityFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class CompanyDao {

    private EntityManager em;

    public List<Company> getAllCompany(){
        em = EntityFactoryUtil.getEntityManager();
        List<Company> companyList = em.createQuery("select p from companies p order by idCompany").getResultList();
        em.close();
        return companyList;
    }

    public Company getCompanyById(Integer id) {
        em = EntityFactoryUtil.getEntityManager();
        Company company = em.find(Company.class, id);
        em.close();
        return company;
    }

    public void saveCompany(Company company) {
        em = EntityFactoryUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        session.saveOrUpdate(company);
        session.getTransaction().commit();
        em.clear();
        session.flush();
        session.close();
    }
}
