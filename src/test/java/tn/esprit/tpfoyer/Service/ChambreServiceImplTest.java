package tn.esprit.tpfoyer.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllChambres() {
        // Given
        List<Chambre> mockChambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAll()).thenReturn(mockChambres);

        // When
        List<Chambre> result = chambreService.retrieveAllChambres();

        // Then
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveChambre() {
        // Given
        Long chambreId = 123L;
        Chambre mockChambre = new Chambre();
        mockChambre.setIdChambre(chambreId);
        when(chambreRepository.findById(chambreId)).thenReturn(Optional.of(mockChambre));

        // When
        Chambre result = chambreService.retrieveChambre(chambreId);

        // Then
        assertNotNull(result);
        assertEquals(chambreId, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(chambreId);
    }

    @Test
    void testAddChambre() {
        // Given
        Chambre mockChambre = new Chambre();
        when(chambreRepository.save(mockChambre)).thenReturn(mockChambre);

        // When
        Chambre result = chambreService.addChambre(mockChambre);

        // Then
        assertNotNull(result);
        verify(chambreRepository, times(1)).save(mockChambre);
    }

    @Test
    void testModifyChambre() {
        // Given
        Chambre mockChambre = new Chambre();
        mockChambre.setIdChambre(123L);
        when(chambreRepository.save(mockChambre)).thenReturn(mockChambre);

        // When
        Chambre result = chambreService.modifyChambre(mockChambre);

        // Then
        assertNotNull(result);
        assertEquals(123L, result.getIdChambre());
        verify(chambreRepository, times(1)).save(mockChambre);
    }

    @Test
    void testRemoveChambre() {
        // Given
        Long chambreId = 123L;

        // When
        chambreService.removeChambre(chambreId);

        // Then
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        // Given
        TypeChambre typeChambre = TypeChambre.SIMPLE; // Adjust according to your enum values
        List<Chambre> mockChambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(mockChambres);

        // When
        List<Chambre> result = chambreService.recupererChambresSelonTyp(typeChambre);

        // Then
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(typeChambre);
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        // Given
        long cin = 12345678L;
        Chambre mockChambre = new Chambre();
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(mockChambre);

        // When
        Chambre result = chambreService.trouverchambreSelonEtudiant(cin);

        // Then
        assertNotNull(result);
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}
