package hello.se.domain.respository;

import hello.se.domain.DBdata.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerRepository {
    @PersistenceContext
    EntityManager em;

    public Customer save(Customer customer) {
        customer.setName(customer.getName());
        customer.setPhoneNumber(customer.getPhoneNumber());

        em.persist(customer);
        return customer;
    }

    public Customer findCustomer(int oid) {
        return em.find(Customer.class, oid);
    }

    public List<Customer> findForName(String name) {
        return em.createQuery("select c from Customer c where c.name = :name", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }
}
