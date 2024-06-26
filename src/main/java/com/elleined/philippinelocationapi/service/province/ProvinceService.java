package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.LocationService;

import java.util.List;

public interface ProvinceService extends LocationService<Province> {
    List<Province> getAllBy(Region region);
}
