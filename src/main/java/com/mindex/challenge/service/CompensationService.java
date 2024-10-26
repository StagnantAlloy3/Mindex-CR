package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {

    /**
     * Create a new Compensation object
     * @param compensation Compensation object
     * @return Compensation object
     */
    Compensation create(Compensation compensation);

    /**
     * Read a Compensation object
     * @param id Employee ID
     * @return Compensation object
     */
    Compensation read(String id);
}
