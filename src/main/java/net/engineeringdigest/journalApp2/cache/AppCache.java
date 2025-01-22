package net.engineeringdigest.journalApp2.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp2.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp2.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalAppRepository configRepository;

    public Map<String,String> APP_CACHE;


    @PostConstruct
    public void init() {
        APP_CACHE=new HashMap<>();
        List<ConfigJournalAppEntity> all = configRepository.findAll();
        for(ConfigJournalAppEntity app : all) {
           APP_CACHE.put(app.getKey(),app.getValue());
        }

    }
}
