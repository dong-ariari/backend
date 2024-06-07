package youngpeople.aliali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngpeople.aliali.entity.club.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

}
