package XAir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface AirSystemRepository extends JpaRepository<AirSystemEntity, Long> {
    List<AirSystemEntity> findAll();
    Optional<AirSystemEntity> findById(long id);

    @Query("SELECT function('date_format', a.date, '%Y-%m-%d %H:%i'), a.ppm FROM AirSystemEntity a  WHERE a.date > ?1 AND a.date < CURRENT_TIMESTAMP ORDER BY date DESC")
    List<AirSystemEntity> findPPMByLimit(Date endedDate);

    @Query("SELECT function('date_format', a.date, '%Y-%m-%d %H:%i'), a.temp FROM AirSystemEntity a  WHERE a.date > ?1 AND a.date < CURRENT_TIMESTAMP ORDER BY date DESC")
    List<AirSystemEntity> findTempByLimit(Date endedDate);

    @Query("SELECT function('date_format', a.date, '%Y-%m-%d %H:%i'), a.humidity FROM AirSystemEntity a  WHERE a.date > ?1 AND a.date < CURRENT_TIMESTAMP ORDER BY date DESC")
    List<AirSystemEntity> findHumByLimit(Date endedDate);
}
