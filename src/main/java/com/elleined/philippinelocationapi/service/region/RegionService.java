package com.elleined.philippinelocationapi.service.region;

import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.LocationService;

import java.util.List;

public interface RegionService extends LocationService<Region> {
    List<Region> getAll();
}
