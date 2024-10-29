package tn.esprit.tpfoyer.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class FoyerServiceImplTest {
    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllFoyers() {
        Foyer foyer1 = new Foyer(1L, "Foyer A", 100, null, null);
        Foyer foyer2 = new Foyer(2L, "Foyer B", 150, null, null);
        List<Foyer> foyers = Arrays.asList(foyer1, foyer2);

        when(foyerRepository.findAll()).thenReturn(foyers);

        List<Foyer> result = foyerService.retrieveAllFoyers();
        assertEquals(2, result.size());
        assertEquals("Foyer A", result.get(0).getNomFoyer());
        assertEquals("Foyer B", result.get(1).getNomFoyer());

        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer() {
        Foyer foyer = new Foyer(1L, "Foyer A", 100, null, null);

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);
        assertNotNull(result);
        assertEquals("Foyer A", result.getNomFoyer());

        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void testAddFoyer() {
        Foyer foyer = new Foyer(1L, "Foyer A", 100, null, null);

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.addFoyer(foyer);
        assertNotNull(result);
        assertEquals("Foyer A", result.getNomFoyer());

        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testModifyFoyer() {
        Foyer foyer = new Foyer(1L, "Foyer A", 120, null, null);

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.modifyFoyer(foyer);
        assertNotNull(result);
        assertEquals(120, result.getCapaciteFoyer());

        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testRemoveFoyer() {
        Long foyerId = 1L;

        doNothing().when(foyerRepository).deleteById(foyerId);

        foyerService.removeFoyer(foyerId);

        verify(foyerRepository, times(1)).deleteById(foyerId);
    }
}
