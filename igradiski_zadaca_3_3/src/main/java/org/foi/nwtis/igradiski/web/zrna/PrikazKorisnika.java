/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.igradiski.web.zrna;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.igradiski.ejb.eb.Airplanes;
import org.foi.nwtis.igradiski.ejb.eb.Airports;
import org.foi.nwtis.igradiski.ejb.eb.Korisnici;
import org.foi.nwtis.igradiski.ejb.eb.Myairports;
import org.foi.nwtis.igradiski.ejb.eb.Myairportslog;
import org.foi.nwtis.igradiski.ejb.sb.AirportsFacade;
import org.foi.nwtis.igradiski.ejb.sb.KorisniciFacade;
import org.foi.nwtis.igradiski.ejb.sb.MyairportsFacade;
import org.foi.nwtis.igradiski.ejb.sb.MyairportslogFacade;

/**
 *
 * @author Korisnik
 */
@Named(value = "prikazKorisnika")
@ViewScoped
public class PrikazKorisnika implements Serializable {

    @Inject
    KorisniciFacade korisniciFacade;

    @Inject
    MyairportsFacade myairportsFacade;

    @Inject
    MyairportslogFacade myairportslogFacade;

    @Inject
    AirportsFacade airportsFacade;

    @Getter
    List<Myairports> mojiAerodromi = new ArrayList<>();

    @Getter
    List<Myairports> odabraniAerodromi = new ArrayList<>();

    @Getter
    List<Myairportslog> logoviAerodroma = new ArrayList<>();

    @Getter
    List<Myairportslog> logoviAerodromaP3 = new ArrayList<>();

    @Getter
    List<Airplanes> avioniAerodroma = new ArrayList<>();

    @Getter
    List<Myairports> odabraniAerodromiKorisnika = new ArrayList<>();

    @Getter
    List<Korisnici> korisnici = new ArrayList<>();

    @Getter
    @Setter
    Airports aerodromP3 = new Airports();

    @Getter
    @Setter
    String visibilityP1 = "vidljivo";

    @Getter
    @Setter
    String visibilityP2 = "sakriveno";

    @Getter
    @Setter
    String visibilityP3 = "sakriveno";

    @Getter
    @Setter
    String odabraniKorisnik = null;

    /**
     * Creates a new instance of PrikazKorisnika
     */
    public PrikazKorisnika() {

    }

    /**
     * Dohvacanje korisnika iz baze podataka
     */
    @PostConstruct
    private void dohvatiKorisnike() {
        korisnici = korisniciFacade.findAll();
        visibilityP1 = "vidljivo";

    }

    /**
     * Metoda za dohvacanje korisnika prema imenu
     *
     * @param ime korisnicko ime korisnika
     */
    public void odaberiKorisnika(String ime) {
        promijeniVidljivostPogledaP2();
        odabraniKorisnik = ime;
        UcitajAerodromeKorisnika();
    }

    /**
     * Metoda za ucitavanje aerodroma prema korisniku
     */
    public void UcitajAerodromeKorisnika() {
        Korisnici korisnik = new Korisnici();
        korisnik = korisniciFacade.find(odabraniKorisnik);
        mojiAerodromi.clear();
        odabraniAerodromiKorisnika = korisnik.getMyairportsList();
    }

    /**
     * Metoda za promjenu pogleda u P2
     */
    public void promijeniVidljivostPogledaP2() {
        visibilityP1 = "sakriveno";
        visibilityP2 = "vidljivo";
        visibilityP3 = "sakriveno";
    }

    /**
     * Metoda za otvaranje pogleda P3
     *
     * @param airport aerodrom za pregled
     */
    public void otvoriP3(Airports airport) {
        visibilityP1 = "sakriveno";
        visibilityP2 = "sakriveno";
        visibilityP3 = "vidljivo";
        aerodromP3 = airport;
        logoviAerodromaP3 = airport.getMyairportslogList();
    }

    /**
     * Metoda za brojenje aerodroma korisnika
     *
     * @param korisnickoIme korisniko ime korisnika
     * @return broj aerodroma
     */
    public int brojAerodromaKorisnika(String korisnickoIme) {
        Korisnici korisnik = new Korisnici();
        korisnik = korisniciFacade.find(korisnickoIme);
        mojiAerodromi.clear();
        mojiAerodromi = korisnik.getMyairportsList();
        return mojiAerodromi.size();
    }

    /**
     * Metoda za broj korisnika koji prate odredeni aerodrom
     *
     * @param airport praceni aerodrom
     * @return broj korisnika
     */
    public int brojKorisnikaKojiPrateAerodrom(Airports airport) {
        odabraniAerodromi = airport.getMyairportsList();
        return odabraniAerodromi.size();
    }

    /**
     * Metoda koja dohvaca broj dana preuzimanja za aerodorom
     *
     * @param airport aerodrom za koji se podaci preuzimaju
     * @return broj dana
     */
    public int brojDanaPreuzimanja(Airports airport) {
        logoviAerodroma = airport.getMyairportslogList();
        return logoviAerodroma.size();
    }

    /**
     * Metoda za dohvacanje broja aviona aerodroma
     *
     * @param airport trazeni aerodrom
     * @return broj aviona aerodroma
     */
    public int brojAvionaAerodroma(Airports airport) {
        avioniAerodroma = airport.getAirplanesList();
        return avioniAerodroma.size();
    }

    /**
     * metoda za pretvaranje datum u zeljeni format
     *
     * @param datum datum za pretvaranje
     * @return pretvoreni datum
     */
    public String formatiranjeDatuma(Date datum) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
        String strDate = dateFormat.format(datum);
        return strDate;
    }

    /**
     * Formatiranje datuma preuzima u zeljeni datum
     *
     * @param datum datum za formatiranje
     * @return
     */
    public String formatiranjeDanaPreuzimanjaP3(Date datum) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = dateFormat.format(datum);
        return strDate;
    }

    /**
     * Metoda za brisanje zapisa iz baze podataka
     *
     * @param log log za brisanje
     */
    public void obrisiZapisIzBP(Myairportslog log) {
        myairportslogFacade.remove(log);
        logoviAerodromaP3.remove(log);
        aerodromP3.setMyairportslogList(logoviAerodromaP3);
        airportsFacade.edit(aerodromP3);
    }

    /**
     * Metoda koja vraca starnicu na pocetak odabira
     */
    public void VratiNaPocetak() {

        visibilityP1 = "vidljivo";
        visibilityP2 = "sakriveno";
        visibilityP3 = "sakriveno";

    }

}
