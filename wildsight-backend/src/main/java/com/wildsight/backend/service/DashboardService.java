package com.wildsight.backend.service;


import com.wildsight.backend.dto.MapLocationResponse;

import java.util.List;


public interface DashboardService {


List<MapLocationResponse> getMapLocations();


}