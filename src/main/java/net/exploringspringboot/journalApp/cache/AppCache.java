package net.exploringspringboot.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.exploringspringboot.journalApp.Entity.ConfigJournalAppEntity;
import net.exploringspringboot.journalApp.Repository.ConfigJournalApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalApp configJournalApp;

    public Map<String,String> app_Cache ;
    @PostConstruct
    public void init(){
        app_Cache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalApp.findAll();
        for (ConfigJournalAppEntity entity : all) {
            app_Cache.put(entity.getKey(), entity.getValue());
        }
    }

    public String get(String key) {
        return app_Cache.get(key);
    }


}

