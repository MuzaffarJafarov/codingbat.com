package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.LanguageDto;
import uz.muzaffar.codingbat.service.HelpService;
import uz.muzaffar.codingbat.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    HelpService helpService;

    @GetMapping
    public List<Help> getAll() {
        return helpService.getAllHelp();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse helpById = helpService.getHelpById(id);
        return ResponseEntity.status(helpById.isSuccess() ? 201 : 404).body(helpById);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody HelpDto helpDto) {
        ApiResponse apiResponse = helpService.addHelp(helpDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = helpService.deleteHelp(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody HelpDto helpDto, @PathVariable Integer id) {
        ApiResponse apiResponse = helpService.editHelp(id, helpDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
