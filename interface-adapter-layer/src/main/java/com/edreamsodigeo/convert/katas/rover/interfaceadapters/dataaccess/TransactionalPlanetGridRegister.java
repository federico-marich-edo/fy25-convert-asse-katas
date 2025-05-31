package com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;

public class TransactionalPlanetGridRegister implements PlanetGridRegisterDSGateway {

    private final PlanetGridRegisterDSGateway planetGridRegisterDSGateway;

    public TransactionalPlanetGridRegister(PlanetGridRegisterDSGateway planetGridRegisterDSGateway) {
        this.planetGridRegisterDSGateway = planetGridRegisterDSGateway;
    }
    @Override
    public boolean existsByName(String name) {
        return planetGridRegisterDSGateway.existsByName(name);
    }
    @Override
    public void save(com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridRequestDS planetGridRequestDS) {
        planetGridRegisterDSGateway.save(planetGridRequestDS);
    }
    @Override
    public PlanetGridResponseDS findByPlanetName(String name) {
        return planetGridRegisterDSGateway.findByPlanetName(name);
    }
    @Override
    public PlanetGridResponseDS findByRoverName(String roverName) {
        return planetGridRegisterDSGateway.findByRoverName(roverName);
    }
}
