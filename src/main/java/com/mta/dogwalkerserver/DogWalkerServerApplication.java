package com.mta.dogwalkerserver;

//import com.mta.dogwalkerserver.controller.UserRepository;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {DogWalkerRepo.class, DogOwnerRepo.class})
//@EnableJpaRepositories(basePackageClasses = DogOwnerRepo.class)
public class DogWalkerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogWalkerServerApplication.class, args);
    }
}
