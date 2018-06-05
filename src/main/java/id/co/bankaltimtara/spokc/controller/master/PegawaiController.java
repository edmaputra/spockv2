package id.co.bankaltimtara.spokc.controller.master;

import id.co.bankaltimtara.spokc.controller.Logs;
import id.co.bankaltimtara.spokc.exception.ResponseService;
import id.co.bankaltimtara.spokc.exception.SuccessDetail;
import id.co.bankaltimtara.spokc.model.masters.Pegawai;
import id.co.bankaltimtara.spokc.service.PegawaiService;
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
@RequestMapping("/a/p")
public class PegawaiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String clazz = this.getClass().getSimpleName();

    @Autowired
    private PegawaiService service;
    
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
            Page<Pegawai> page = service.dapatkan(h, c);
            if (page != null) {
                logger.info(Logs.dapatkanSemua(request, clazz));
                return new ResponseEntity<>(page, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseService.notFound("Resource Tidak Ditemukan", "List Pegawai Tidak Ada"), HttpStatus.NOT_FOUND);
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
                Pegawai pegawai = service.dapatkan(id);
                logger.info(Logs.dapatkan(request, clazz, pegawai));
                return new ResponseEntity<>(pegawai, HttpStatus.OK);
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
    public ResponseEntity<?> tambah(@Valid @RequestBody Pegawai pegawai, HttpServletRequest request) {
        try {
            service.tambah(pegawai);
            logger.info(Logs.tambah(request, clazz, pegawai));
            return new ResponseEntity<>(new SuccessDetail(
                    "Add OK",
                    HttpStatus.CREATED.value(),
                    "Add "+pegawai.getId()+" OK"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "CREATE", pegawai));
            return new ResponseEntity<>(responseService.badRequest("Server Error", "Server Error"), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid @RequestBody Pegawai pegawai, HttpServletRequest request){
        try {
            if (service.count(pegawai.getId()) != 0){
                service.update(pegawai);
                logger.info(Logs.update(request, clazz, pegawai));
                return new ResponseEntity<>(responseService.ok(
                        "Update OK",
                        "Update "+pegawai.getId()+" OK"),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseService.notFound(
                        "Resource Tidak Ditemukan",
                        "Resource dengan ID "+ pegawai.getId() +" Tidak Ditemukan"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Logs.error(request, clazz, "UPDATE", pegawai));
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
