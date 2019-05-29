package jdurao.kschool.services;

import javax.persistence.EntityManager;

public class AreaService {
    protected EntityManager em;

    public AreaService(EntityManager em) {
        this.em = em;
    }

    public void addArea(String area) {
        em
                .createNativeQuery("INSERT INTO areas (area) VALUES ('?')")
                .setParameter(1, area)
                .executeUpdate();
    }
}
