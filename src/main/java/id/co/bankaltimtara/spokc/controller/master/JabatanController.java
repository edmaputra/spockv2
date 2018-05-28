package id.co.bankaltimtara.spokc.controller.master;

import id.co.bankaltimtara.spokc.controller.Logs;
import id.co.bankaltimtara.spokc.model.masters.Jabatan;
import id.co.bankaltimtara.spokc.service.JabatanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/a/jabatan")
public class JabatanController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String clazz = this.getClass().getSimpleName();

    @Autowired
    private JabatanService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> dapatkanSemua(@RequestParam(value = "c", required = false) String c, HttpServletRequest request) {
        try {
            List<Jabatan>  list = (List<Jabatan>) service.dapatkan(c);
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
            Jabatan jabatan = service.dapatkan(id);
            if (jabatan != null){
                logger.info(Logs.dapatkan(request, clazz, jabatan));
                return new ResponseEntity<>(jabatan, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "FIND ONE", null));
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> tambah(@Valid @RequestBody Jabatan jabatan, HttpServletRequest request) {
        try {
            service.tambah(jabatan);
            logger.info(Logs.tambah(request, clazz, jabatan));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "CREATE", jabatan));
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid @RequestBody Jabatan jabatan, HttpServletRequest request){
        try {
            service.update(jabatan);
            logger.info(Logs.update(request, clazz, jabatan));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "UPDATE", jabatan));
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> hapus(@Valid @PathVariable("id") Long id, HttpServletRequest request) {
        try {
            service.hapus(id);
            logger.info(Logs.hapus(request, clazz, id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "DELETE", id));
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
