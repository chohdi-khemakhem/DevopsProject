package tn.esprit.tpfoyer.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReservations() {
        // Given
        List<Reservation> mockReservations = List.of(new Reservation(), new Reservation());
        when(reservationRepository.findAll()).thenReturn(mockReservations);

        // When
        List<Reservation> result = reservationService.retrieveAllReservations();

        // Then
        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        // Given
        String reservationId = "123";
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation(reservationId);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));

        // When
        Reservation result = reservationService.retrieveReservation(reservationId);

        // Then
        assertNotNull(result);
        assertEquals(reservationId, result.getIdReservation());
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @Test
    void testAddReservation() {
        // Given
        Reservation mockReservation = new Reservation();
        when(reservationRepository.save(mockReservation)).thenReturn(mockReservation);

        // When
        Reservation result = reservationService.addReservation(mockReservation);

        // Then
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(mockReservation);
    }

    @Test
    void testModifyReservation() {
        // Given
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation("123");
        when(reservationRepository.save(mockReservation)).thenReturn(mockReservation);

        // When
        Reservation result = reservationService.modifyReservation(mockReservation);

        // Then
        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).save(mockReservation);
    }

    @Test
    void testRemoveReservation() {
        // Given
        String reservationId = "123";

        // When
        reservationService.removeReservation(reservationId);

        // Then
        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    void testTrouverResSelonDateEtStatus() {
        // Given
        Date date = new Date();
        boolean status = true;
        List<Reservation> mockReservations = List.of(new Reservation(), new Reservation());
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status)).thenReturn(mockReservations);

        // When
        List<Reservation> result = reservationService.trouverResSelonDateEtStatus(date, status);

        // Then
        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(date, status);
    }

    public static class FoyerServiceImplTest {
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
}