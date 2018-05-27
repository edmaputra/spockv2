package id.co.bankaltimtara.spokc.controller.user;

import id.co.bankaltimtara.spokc.controller.Logs;
import id.co.bankaltimtara.spokc.model.users.Otoritas;
import id.co.bankaltimtara.spokc.service.OtoritasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/a/otor")
public class OtoritasController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String clazz = this.getClass().getSimpleName();

    @Autowired
    private OtoritasService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> dapatkanSemua(HttpServletRequest request) {
        try {
            List<Otoritas> list = (List<Otoritas>) service.dapatkanSemua();
            if (!list.isEmpty() || list != null) {
                logger.info(Logs.dapatkanSemua(request, clazz));
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "GET ALL", null));
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> dapatkan(@PathVariable Long id, HttpServletRequest request) {
        try {
            Otoritas otoritas = service.dapatkan(id);
            if (otoritas != null){
                logger.info(Logs.dapatkan(request, clazz, otoritas));
                return new ResponseEntity<>(otoritas, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "FIND ONE", null));
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
