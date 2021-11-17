package com.nari.zyl.service;

import com.nari.zyl.model.SkyLightDay;
import java.util.List;

public interface SkyLightService {

    List<SkyLightDay> getSkyLightList(String wPid);

}
