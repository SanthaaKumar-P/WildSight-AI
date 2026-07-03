package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.HabitatRequest;
import com.wildsight.backend.dto.HabitatResponse;
import com.wildsight.backend.entity.Habitat;
import com.wildsight.backend.repository.HabitatRepository;
import com.wildsight.backend.service.HabitatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitatServiceImpl implements HabitatService {

    private final HabitatRepository habitatRepository;

    @Override
    public HabitatResponse createHabitat(HabitatRequest request) {

        Habitat habitat = Habitat.builder()
                .habitatName(request.getHabitatName())
                .habitatType(request.getHabitatType())
                .vegetationType(request.getVegetationType())
                .description(request.getDescription())
                .build();

        habitat = habitatRepository.save(habitat);

        return mapToResponse(habitat);
    }

    @Override
    public List<HabitatResponse> getAllHabitats() {

        return habitatRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public HabitatResponse getHabitatById(Long id) {

        Habitat habitat = habitatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitat not found"));

        return mapToResponse(habitat);
    }

    @Override
    public HabitatResponse updateHabitat(Long id,
                                         HabitatRequest request) {

        Habitat habitat = habitatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitat not found"));

        habitat.setHabitatName(request.getHabitatName());
        habitat.setHabitatType(request.getHabitatType());
        habitat.setVegetationType(request.getVegetationType());
        habitat.setDescription(request.getDescription());

        habitat = habitatRepository.save(habitat);

        return mapToResponse(habitat);
    }

    @Override
    public void deleteHabitat(Long id) {

        Habitat habitat = habitatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitat not found"));

        habitatRepository.delete(habitat);
    }

    private HabitatResponse mapToResponse(Habitat habitat) {

        return HabitatResponse.builder()
                .habitatId(habitat.getHabitatId())
                .habitatName(habitat.getHabitatName())
                .habitatType(habitat.getHabitatType())
                .vegetationType(habitat.getVegetationType())
                .description(habitat.getDescription())
                .build();
    }
}