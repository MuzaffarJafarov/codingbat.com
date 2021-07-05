package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLHeadElement;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.payload.LanguageDto;
import uz.muzaffar.codingbat.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping
    public List<Language> getAll() {
        return languageService.getAllLanguage();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse languageById = languageService.getLanguageById(id);
        return ResponseEntity.status(languageById.isSuccess() ? 201 : 404).body(languageById);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody LanguageDto languageDto, @PathVariable Integer id) {
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
