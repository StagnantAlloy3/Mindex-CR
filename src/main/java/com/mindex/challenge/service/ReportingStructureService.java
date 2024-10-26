package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

public interface ReportingStructureService {

    /**
     * Read a ReportingStructure object
     * @param id Employee ID
     * @return ReportingStructure object
     */
    ReportingStructure read(String id);
}
