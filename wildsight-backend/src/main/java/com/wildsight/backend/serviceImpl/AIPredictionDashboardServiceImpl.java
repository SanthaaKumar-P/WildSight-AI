package com.wildsight.backend.serviceImpl;


import com.wildsight.backend.dto.AIPredictionDashboardResponse;
import com.wildsight.backend.repository.AIPredictionRepository;
import com.wildsight.backend.service.AIPredictionDashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class AIPredictionDashboardServiceImpl
implements AIPredictionDashboardService{


private final AIPredictionRepository repository;



@Override
public List<AIPredictionDashboardResponse> getRecentPredictions(){


return repository
.findTop6ByOrderByCreatedAtDesc()
.stream()
.map(p ->

AIPredictionDashboardResponse.builder()

.id(p.getId())

.species(p.getSpecies())

.confidence(p.getConfidence())

.location(p.getLocation())

.researcher(p.getResearcher())

.date(
p.getCreatedAt()!=null
?
p.getCreatedAt().toString()
:
""
)

.build()

)
.toList();


}


}