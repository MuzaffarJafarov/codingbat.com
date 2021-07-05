package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.repository.HelpRepository;
import uz.muzaffar.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HelpService {
    @Autowired
    HelpRepository helpRepository;
    @Autowired
    LanguageRepository languageRepository;

    public List<Help> getAllHelp() {
        List<Help> all = helpRepository.findAll();
        return all;
    }

    public ApiResponse getHelpById(Integer id) {
        Optional<Help> byId = helpRepository.findById(id);
        return byId.map(help -> new ApiResponse("Success", true, help)).orElseGet(() -> new ApiResponse("Help not found!", false));
    }

    public ApiResponse addHelp(HelpDto helpDto) {
        Optional<Language> byId = languageRepository.findById(helpDto.getLanguageId());
        if (!byId.isPresent())
            return new ApiResponse("Language not found!", false);
        boolean b = helpRepository.existsBySeccionNameAndLanguage(helpDto.getSectionName(), byId.get());
        if (b)
            return new ApiResponse("This Help already exist! ", false);
        Help help = new Help();
        help.setLanguage(byId.get());
        help.setSectionName(helpDto.getSectionName());
        helpRepository.save(help);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse deleteHelp(Integer id) {
        Optional<Help> byId = helpRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Help not found!", false);
        helpRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editHelp(Integer id, HelpDto helpDto) {
        Optional<Help> byId = helpRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Help not found!", false);
        boolean b1 = helpRepository.existsBySeccionName(helpDto.getSectionName());
        if (b1)
            return new ApiResponse("This name already exist!", false);

        Help help = byId.get();
        help.setSectionName(helpDto.getSectionName());
        helpRepository.save(help);
        return new ApiResponse("Edited successfully!", true);
    }

}