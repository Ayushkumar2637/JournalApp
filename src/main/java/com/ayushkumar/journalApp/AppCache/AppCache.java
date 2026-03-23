package com.ayushkumar.journalApp.AppCache;

import com.ayushkumar.journalApp.Entity.UrlConfigEntity;
import com.ayushkumar.journalApp.Services.UrlConfigServices;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public Map<String,String> urls;

    @Autowired
    private UrlConfigServices urlConfigServices;

    @PostConstruct
    public void init(){
        List<UrlConfigEntity> urlConfigEntityList=urlConfigServices.findAllValues();
        urls=new HashMap<>();
        for(UrlConfigEntity e : urlConfigEntityList){
            urls.put(e.getKey(),e.getValue());
        }
    }

}
