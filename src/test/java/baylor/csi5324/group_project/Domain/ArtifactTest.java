package baylor.csi5324.group_project.Domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArtifactTest {

    @Autowired
    private TestEntityManager em;

    private Job job;
    private Artifact artifact;

    @BeforeEach
    void setUp() {
        job = new Job();
        job.setStartDate(LocalDateTime.now());
        job.setEndDate(LocalDateTime.now());

        artifact = new Artifact();
        artifact.setDescription("Diagrams");
        artifact.setArtifactType(ArtifactType.MANUAL);
    }

    @Test
    @DisplayName("Persist Artifact: Success")
    void persistArtifact() {
        Job savedJob = em.persistAndFlush(job);

        artifact.setJob(savedJob);
        assertNull(artifact.getId());
        Artifact savedArtifact = em.persistAndFlush(artifact);
        assertNotNull(savedArtifact.getId());
    }

    @Test
    @DisplayName("Persist Artifact: NULL description")
    void persistArtifactDescriptionNULL() {
        Job savedJob = em.persistAndFlush(job);

        artifact.setJob(savedJob);
        assertNull(artifact.getId());
        artifact.setDescription(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(artifact)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("description is required", violation.getMessage());
                    Assertions.assertEquals("description", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Artifact: NULL artifactType")
    void persistArtifactArtifactTypeNULL() {
        Job savedJob = em.persistAndFlush(job);

        artifact.setJob(savedJob);
        assertNull(artifact.getId());
        artifact.setArtifactType(null);
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(artifact)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("artifactType is required", violation.getMessage());
                    Assertions.assertEquals("artifactType", violation.getPropertyPath().toString());
                });
    }

    @Test
    @DisplayName("Persist Artifact: NULL job")
    void persistArtifactJobNULL() {

        assertNull(artifact.getId());
        ConstraintViolationException e = assertThrows(
                ConstraintViolationException.class,
                () -> em.persistAndFlush(artifact)
        );

        e.getConstraintViolations()
                .forEach(violation -> {
                    Assertions.assertEquals("valid job is required", violation.getMessage());
                    Assertions.assertEquals("job", violation.getPropertyPath().toString());
                });
    }

}