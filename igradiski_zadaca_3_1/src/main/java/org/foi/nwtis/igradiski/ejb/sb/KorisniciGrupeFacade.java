/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.igradiski.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.igradiski.ejb.eb.KorisniciGrupe;

/**
 *
 * @author Korisnik
 */
@Stateless
public class KorisniciGrupeFacade extends AbstractFacade<KorisniciGrupe> {

    @PersistenceContext(unitName = "NWTiS_DZ3_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KorisniciGrupeFacade() {
        super(KorisniciGrupe.class);
    }
    
}
