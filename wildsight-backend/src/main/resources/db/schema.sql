-- WildSight AI - Initial Enterprise Schema (Part 1 Foundation)
-- Continue with remaining domain tables in subsequent iterations.

CREATE TABLE roles (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    organization VARCHAR(150),
    designation VARCHAR(100),
    profile_image VARCHAR(255),
    status ENUM('ACTIVE','INACTIVE','BLOCKED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL
    
);

CREATE TABLE user_roles (
    user_role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    UNIQUE(user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY(user_id) REFERENCES users(user_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY(role_id) REFERENCES roles(role_id)
        ON DELETE CASCADE
);

CREATE TABLE surveys (
    survey_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    survey_name VARCHAR(150) NOT NULL,
    description TEXT,
    habitat_type VARCHAR(100),
    protected_area VARCHAR(150),
    survey_date DATE NOT NULL,
    status ENUM('PLANNED','ONGOING','COMPLETED','CANCELLED') DEFAULT 'PLANNED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_surveys_user
        FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE monitoring_locations (
    location_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    survey_id BIGINT NOT NULL,
    location_name VARCHAR(150),
    latitude DECIMAL(10,7),
    longitude DECIMAL(10,7),
    district VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    CONSTRAINT fk_location_survey
        FOREIGN KEY(survey_id) REFERENCES surveys(survey_id)
        ON DELETE CASCADE
);

CREATE TABLE device_types (
    device_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE monitoring_devices (
    device_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    survey_id BIGINT NOT NULL,
    device_type_id BIGINT NOT NULL,
    serial_number VARCHAR(100),
    device_name VARCHAR(100),
    location_id BIGINT NOT NULL,
    status ENUM('ACTIVE','INACTIVE','MAINTENANCE') DEFAULT 'ACTIVE',
    CONSTRAINT fk_device_survey
        FOREIGN KEY(survey_id) REFERENCES surveys(survey_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_device_type
        FOREIGN KEY(device_type_id) REFERENCES device_types(device_type_id),
    CONSTRAINT fk_device_location
    FOREIGN KEY(location_id)
    REFERENCES monitoring_locations(location_id)
    ON DELETE CASCADE
);
-- WildSight AI - Schema Part 2 (Wildlife + AI Modules)

CREATE TABLE species_categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE species (
    species_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    common_name VARCHAR(150) NOT NULL,
    scientific_name VARCHAR(200),
    conservation_status VARCHAR(100),
    iucn_status VARCHAR(100),
    description TEXT,
    CONSTRAINT fk_species_category
        FOREIGN KEY(category_id) REFERENCES species_categories(category_id)
);

CREATE TABLE observations (
    observation_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    survey_id BIGINT NOT NULL,

    species_id BIGINT,

    location_id BIGINT NOT NULL,

    device_id BIGINT,

    observation_time DATETIME NOT NULL,

    observer_notes TEXT,

    weather VARCHAR(100),

    confidence_score DECIMAL(5,2),

    image_count INT DEFAULT 0,

    audio_count INT DEFAULT 0,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_obs_survey
        FOREIGN KEY (survey_id)
        REFERENCES surveys(survey_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_obs_species
        FOREIGN KEY (species_id)
        REFERENCES species(species_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_obs_location
        FOREIGN KEY (location_id)
        REFERENCES monitoring_locations(location_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_obs_device
        FOREIGN KEY (device_id)
        REFERENCES monitoring_devices(device_id)
        ON DELETE SET NULL
);


CREATE TABLE uploaded_images (
    image_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    observation_id BIGINT NOT NULL,

    file_name VARCHAR(255),

    file_path VARCHAR(500) NOT NULL,

    captured_at DATETIME,

    file_size BIGINT,

    uploaded_by BIGINT,

    upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    image_quality DECIMAL(5,2),

    CONSTRAINT fk_img_obs
        FOREIGN KEY (observation_id)
        REFERENCES observations(observation_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_uploaded_by
        FOREIGN KEY (uploaded_by)
        REFERENCES users(user_id)
        ON DELETE SET NULL
);
CREATE TABLE uploaded_audio (
    audio_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    observation_id BIGINT NOT NULL,

    file_name VARCHAR(255),

    file_path VARCHAR(500) NOT NULL,

    captured_at DATETIME,

    file_size BIGINT,

    uploaded_by BIGINT,

    duration_seconds DECIMAL(8,2),

    upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_audio_obs
        FOREIGN KEY (observation_id)
        REFERENCES observations(observation_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_audio_uploaded_by
        FOREIGN KEY (uploaded_by)
        REFERENCES users(user_id)
        ON DELETE SET NULL
);


CREATE TABLE audio_detections (
    audio_detection_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    audio_id BIGINT NOT NULL,
    species_id BIGINT,
    confidence DECIMAL(5,2),
    detected_call VARCHAR(150),
    noise_level DECIMAL(5,2),
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_audio_det_audio
        FOREIGN KEY(audio_id) REFERENCES uploaded_audio(audio_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_audio_det_species
        FOREIGN KEY(species_id) REFERENCES species(species_id)
);
-- WildSight AI - Schema Part 3 (Analytics, Habitat & Reporting)

CREATE TABLE habitats (
    habitat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    habitat_name VARCHAR(150) NOT NULL,
    habitat_type VARCHAR(100),
    vegetation_type VARCHAR(100),
    description TEXT
);

CREATE TABLE environment_conditions (
    condition_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    habitat_id BIGINT NOT NULL,

    survey_id BIGINT NOT NULL,

    temperature DECIMAL(5,2),

    humidity DECIMAL(5,2),

    rainfall DECIMAL(6,2),

    air_quality VARCHAR(100),

    wind_speed DECIMAL(5,2),

    weather_condition VARCHAR(100),

    recorded_at DATETIME,

    CONSTRAINT fk_env_habitat
        FOREIGN KEY (habitat_id)
        REFERENCES habitats(habitat_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_env_survey
        FOREIGN KEY (survey_id)
        REFERENCES surveys(survey_id)
        ON DELETE CASCADE
);

CREATE TABLE population_estimates (
    estimate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    species_id BIGINT NOT NULL,
    survey_id BIGINT NOT NULL,
    estimated_population INT,
    density DECIMAL(10,2),
    growth_rate DECIMAL(8,2),
    migration_pattern VARCHAR(255),
    calculated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pop_species FOREIGN KEY(species_id) REFERENCES species(species_id),
    CONSTRAINT fk_pop_survey FOREIGN KEY(survey_id) REFERENCES surveys(survey_id)
);

CREATE TABLE population_history (
    history_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estimate_id BIGINT NOT NULL,
    previous_population INT,
    current_population INT,
    trend ENUM('INCREASING','STABLE','DECLINING'),
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_history_est FOREIGN KEY(estimate_id)
        REFERENCES population_estimates(estimate_id) ON DELETE CASCADE
);

CREATE TABLE biodiversity_scores (
    biodiversity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    survey_id BIGINT NOT NULL,
    habitat_id BIGINT,
    species_diversity_score DECIMAL(5,2),
    habitat_quality_score DECIMAL(5,2),
    ecosystem_health_score DECIMAL(5,2),
    overall_score DECIMAL(5,2),

species_count INT,

health_status VARCHAR(100),
    calculated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bio_survey FOREIGN KEY(survey_id) REFERENCES surveys(survey_id),
    CONSTRAINT fk_bio_habitat FOREIGN KEY(habitat_id) REFERENCES habitats(habitat_id)
);

CREATE TABLE conservation_recommendations (
    recommendation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    biodiversity_id BIGINT,
    priority ENUM('LOW','MEDIUM','HIGH','CRITICAL'),
    recommendation TEXT,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cons_bio FOREIGN KEY(biodiversity_id)
        REFERENCES biodiversity_scores(biodiversity_id)
);

CREATE TABLE reports (
    report_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    survey_id BIGINT NOT NULL,
    generated_by BIGINT NOT NULL,
    report_title VARCHAR(200),
    report_type VARCHAR(100),
    report_path VARCHAR(500),
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    report_status ENUM('GENERATING','READY','FAILED'),
    CONSTRAINT fk_report_survey FOREIGN KEY(survey_id) REFERENCES surveys(survey_id),
    CONSTRAINT fk_report_user FOREIGN KEY(generated_by) REFERENCES users(user_id)
);

CREATE TABLE report_exports (
    export_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    report_id BIGINT NOT NULL,
    export_format ENUM('PDF','EXCEL','CSV'),
    exported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    export_path VARCHAR(500),
    CONSTRAINT fk_export_report FOREIGN KEY(report_id)
        REFERENCES reports(report_id) ON DELETE CASCADE
);

CREATE TABLE notifications (
    notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200),
    read_at TIMESTAMP NULL,
    message TEXT,
    notification_type VARCHAR(100),
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_notification_user FOREIGN KEY(user_id)
        REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE audit_logs (
    audit_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    user_id BIGINT,

    action VARCHAR(255) NOT NULL,

    module_name VARCHAR(100) NOT NULL,

    entity_name VARCHAR(100),

    entity_id BIGINT,

    ip_address VARCHAR(50),

    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_audit_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE SET NULL
);
CREATE TABLE endangered_species (
    endangered_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    species_id BIGINT NOT NULL,

    risk_level ENUM('LOW','MEDIUM','HIGH','CRITICAL') NOT NULL,

    remarks TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_endangered_species
        FOREIGN KEY (species_id)
        REFERENCES species(species_id)
        ON DELETE CASCADE
);
