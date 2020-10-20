/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.igradiski.ejb.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.foi.nwtis.igradiski.ejb.eb.Airports;

/**
 *
 * @author Korisnik
 */
@Stateless
public class AirportsFacade extends AbstractFacade<Airports> {

    @PersistenceContext(unitName = "NWTiS_DZ3_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AirportsFacade() {
        super(Airports.class);
    }
    
    
    /**
     * Metoda za dohvacanju za zadacu 3 prema imenu
     * @param naziv
     * @return 
     */
    public List<Airports> pretraziPremaImenu(String naziv) {
        return em.createQuery("select a from Airports a where a.name like ?1")
                .setParameter(1, naziv)
                .getResultList();
    }
    
   
    /**
     * Metoda za dohvacanje prema nazivu prema criteria API 
     * @param naziv
     * @return 
     */
    public List<Airports> pretraziPremaImenuCAPI(String naziv) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Airports.class);
        Root<Airports> airports = cq.from(Airports.class);
        Expression<String> zaNaziv = airports.get("name");
        cq.where(cb.like(zaNaziv, naziv));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
}
