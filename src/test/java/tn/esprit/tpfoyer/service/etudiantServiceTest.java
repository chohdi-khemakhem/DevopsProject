package tn.esprit.tpfoyer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class etudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    public void testRetrieveAllEtudiants() {
        // Given
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        // When
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Then
        assertNotNull(etudiants);
        assertEquals(2, etudiants.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    @Order(2)
    public void testRetrieveEtudiant() {
        // Given
        Long etudiantId = 1L;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant));

        // When
        Etudiant foundEtudiant = etudiantService.retrieveEtudiant(etudiantId);

        // Then
        assertNotNull(foundEtudiant);
        verify(etudiantRepository, times(1)).findById(etudiantId);
    }

    @Test
    @Order(3)
    public void testAddEtudiant() {
        // Given
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        // Then
        assertNotNull(savedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    @Order(4)
    public void testModifyEtudiant() {
        // Given
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant);

        // Then
        assertNotNull(modifiedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    @Order(5)
    public void testRemoveEtudiant() {
        // Given
        Long etudiantId = 1L;

        // When
        etudiantService.removeEtudiant(etudiantId);

        // Then
        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    @Order(6)
    public void testRecupererEtudiantParCin() {
        // Given
        long cin = 12345678L;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findEtudiantByCinEtudiant(cin)).thenReturn(etudiant);

        // When
        Etudiant foundEtudiant = etudiantService.recupererEtudiantParCin(cin);

        // Then
        assertNotNull(foundEtudiant);
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(cin);
    }
}
