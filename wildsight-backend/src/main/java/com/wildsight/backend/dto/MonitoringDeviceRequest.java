package com.wildsight.backend.dto;

import com.wildsight.backend.entity.DeviceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitoringDeviceRequest {

    @NotNull
    private Long surveyId;

    @NotNull
    private Long deviceTypeId;

    @NotBlank
    private String serialNumber;

    @NotBlank
    private String deviceName;

    @NotNull
    private Long locationId;

    @NotNull
    private DeviceStatus status;
}