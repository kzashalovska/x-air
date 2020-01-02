package XAir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AirSystemController {

    @Autowired
    private AirSystemRepository airSystemRepository;


    @GetMapping("/getAirSystems")
    public List<AirSystemEntity> retrieveAllAirSystems() {
        return airSystemRepository.findAll();
    }

    @GetMapping("/airSystems/{id}")
    public AirSystemEntity retrieveAirSystem(@PathVariable long id) {
        Optional<AirSystemEntity> system = airSystemRepository.findById(id);

        return system.get();
    }

    @GetMapping("/getAirSystems/{limit}/{stat}")
    public List<AirSystemEntity> retrieveAirSystemByLimit(@PathVariable long limit, @PathVariable String stat) throws ParseException {

        Date newDate = retrieveDate(limit);
        List<AirSystemEntity> systems;

        if ("ppm".equals(stat))
        {
            systems = airSystemRepository.findPPMByLimit(newDate);
        }
        else if ("temp".equals(stat))
        {
            systems = airSystemRepository.findTempByLimit(newDate);
        }
        else
        {
            systems = airSystemRepository.findHumByLimit(newDate);
        }


        return systems;
    }

    private Date retrieveDate(@PathVariable long limit) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy HH:mm");
        long a = new Date().getTime();
        long b = (limit+120L)*60000L;
        Date ex = new Date(a - b);
        String dateToStr = format.format(ex);
        return format.parse(dateToStr);
    }

    @DeleteMapping("/airSystems/{id}")
    public void deleteStudent(@PathVariable long id) {
        airSystemRepository.deleteById(id);
    }

    @PostMapping("/airSystems")
    public ResponseEntity<Object> createAirSystem(@RequestBody AirSystemEntity entity) throws ParseException {

        Date added = getDate();
        entity.setDate(added);

        AirSystemEntity savedSystem = airSystemRepository.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSystem.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    private Date getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy HH:mm");
        String dateToStr = format.format(new Date());
        return format.parse(dateToStr);
    }

}
