package tn.esprit.tpfoyer;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;


@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class TpFoyerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpFoyerApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UniversiteRepository universiteRepository) {
        return args -> {

            Universite esprit = new Universite();
            esprit.setNomUniversite("esprit");
            esprit.setAdresse("ariana");



            universiteRepository.save(esprit);




        };
    }
}
