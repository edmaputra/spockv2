package id.co.bankaltimtara.spokc.controller.master;

import id.co.bankaltimtara.spokc.controller.Logs;
import id.co.bankaltimtara.spokc.exception.ResponseService;
import id.co.bankaltimtara.spokc.exception.SuccessDetail;
import id.co.bankaltimtara.spokc.model.masters.Username;
import id.co.bankaltimtara.spokc.service.UsernameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/a/u")
public class UsernameController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String clazz = this.getClass().getSimpleName();

    @Autowired
    private UsernameService service;
    
    @Autowired
    private ResponseService responseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> dapatkanSemua(
            @RequestParam(value = "c", required = false) String c,
            @RequestParam(value = "h", required = false, defaultValue = "1") Integer h,
            HttpServletRequest request) {
        try {
            Page<Username> page = service.dapatkan(h, c);
            if (page != null) {
                logger.info(Logs.dapatkanSemua(request, clazz));
                return new ResponseEntity<>(page, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseService.notFound("Resource Tidak Ditemukan", "List Username Tidak Ada"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "GET ALL", null));
            return new ResponseEntity<>(responseService.badRequest("Server Error", "Server Error"), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<?> dapatkan(@PathVariable Long id, HttpServletRequest request) {
        try {
            if (service.count(id) != 0) {
                Username username = service.dapatkan(id);
                logger.info(Logs.dapatkan(request, clazz, username));
                return new ResponseEntity<>(username, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseService.notFound(
                        "Resource Tidak Ditemukan",
                        "Resource dengan ID "+ id +" Tidak Ditemukan"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "FIND ONE", null));
            return new ResponseEntity<>(responseService.badRequest("Server Error", "Server Error"), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> tambah(@Valid @RequestBody Username username, HttpServletRequest request) {
        try {
            service.tambah(username);
            logger.info(Logs.tambah(request, clazz, username));
            return new ResponseEntity<>(new SuccessDetail(
                    "Add OK",
                    HttpStatus.CREATED.value(),
                    "Add "+username.getId()+" OK"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "CREATE", username));
            return new ResponseEntity<>(responseService.badRequest("Server Error", "Server Error"), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid @RequestBody Username username, HttpServletRequest request){
        try {
            if (service.count(username.getId()) != 0){
                service.update(username);
                logger.info(Logs.update(request, clazz, username));
                return new ResponseEntity<>(responseService.ok(
                        "Update OK",
                        "Update "+username.getId()+" OK"),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseService.notFound(
                        "Resource Tidak Ditemukan",
                        "Resource dengan ID "+ username.getId() +" Tidak Ditemukan"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "UPDATE", username));
            return new ResponseEntity<>(responseService.badRequest("Server Error", "Server Error"), HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> hapus(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            if (service.dapatkan(id) != null) {
                service.hapus(id);
                logger.info(Logs.hapus(request, clazz, id));
                return new ResponseEntity<>(responseService.ok(
                        "Delete OK",
                        "Delete " + id + " OK"),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseService.notFound(
                        "Resource Tidak Ditemukan",
                        "Resource dengan ID " + id + " Tidak Ditemukan"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "DELETE", id));
            return new ResponseEntity<>(responseService.badRequest("Server Error", "Server Error"), HttpStatus.BAD_GATEWAY);
        }
    }
}
