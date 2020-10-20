/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.igradiski.web.zrna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.igradiski.ejb.eb.Airports;
import org.foi.nwtis.igradiski.ejb.sb.AirportsFacade;

/**
 *
 * @author Korisnik
 */
@Named(value = "prikazAerodroma")
@ViewScoped
public class PrikazAerodroma implements Serializable {

    @Inject
    AirportsFacade airportsFacade;

    @Getter
    @Setter
    String nazivAerodroma = "";

    @Getter
    @Setter
    List<Airports> listaAerodroma = new ArrayList<>();

    @Getter
    @Setter
    String visibilityP1 = "vidljivo";

    @Getter
    @Setter
    String visibilityP2 = "sakriveno";

    /**
     * konstruktor za prikaz
     */
    public PrikazAerodroma() {
    }
    /**
     * 
     *Metoda za dohvacanje aerodorma prema nazivu iz fasade
     */
    public void DohvatiAerodromePremaNazivu() {
        List<Airports> lista = airportsFacade.pretraziPremaImenu(nazivAerodroma);
        listaAerodroma = lista;
        PrikaziP2();
    }
        /**
     * 
     *Metoda za dohvacanje aerodorma prema nazivu iz fasade sa Criteria APi
     */
    public void DohvatiAerodromePremaNazivuCAPI() {
        List<Airports> lista = airportsFacade.pretraziPremaImenuCAPI(nazivAerodroma);
        listaAerodroma = lista;
        PrikaziP2();
    }

        /**
         * Prikazivanje aerodrom apreko P2
         */
    public void PrikaziP2() {
        visibilityP1 = "sakriveno";
        visibilityP2 = "vidljivo";
    }

}
