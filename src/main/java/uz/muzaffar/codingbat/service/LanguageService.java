package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.payload.LanguageDto;
import uz.muzaffar.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public List<Language> getAllLanguage() {
        List<Language> all = languageRepository.findAll();
        return all;
    }

    public ApiResponse getLanguageById(Integer id) {
        Optional<Language> byId = languageRepository.findById(id);
        return new ApiResponse("Success",true,byId.get());
    }

    public ApiResponse addLanguage(LanguageDto languageDto) {

        Language language = new Language();
        language.setName(languageDto.getLanguage());
        boolean b = languageRepository.existsByName(languageDto.getLanguage());
        if (b)
            return new ApiResponse("This Language already exist!", false);
        languageRepository.save(language);
        return new ApiResponse("Language saved!", true);
    }

    public ApiResponse deleteLanguage(Integer id) {
        Optional<Language> byId = languageRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Language not found!", false);
        languageRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        Optional<Language> byId = languageRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Language not found!", false);
        Language language = byId.get();
        language.setName(languageDto.getLanguage());
        boolean b = languageRepository.existsByNameAndIdNot(languageDto.getLanguage(), id);
        if (b)
            return new ApiResponse("his Language already exist!", false);
        languageRepository.save(language);
        return new ApiResponse("Language edited!", true);
    }

}