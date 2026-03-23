package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Entity.UrlConfigEntity;
import com.ayushkumar.journalApp.Repository.UrlConfigRepository;
import com.ayushkumar.journalApp.Services.UrlConfigServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlConfigServicesImpl implements UrlConfigServices {

    @Autowired
    private UrlConfigRepository urlConfigRepository;

    @Override
    public UrlConfigEntity createUrlConfig(UrlConfigEntity urlConfigEntity) {
        UrlConfigEntity savedUrlconfigEntity=urlConfigRepository.save(urlConfigEntity);
        return savedUrlconfigEntity;
    }

    @Override
    public UrlConfigEntity findValueByKey(String key) {
        UrlConfigEntity fetchedUrlConfigEntity=urlConfigRepository.findByKey(key);
        return fetchedUrlConfigEntity;
    }

    @Override
    public List<UrlConfigEntity> findAllValues() {
        List<UrlConfigEntity> urlConfigEntityList= urlConfigRepository.findAll();
        return urlConfigEntityList;
    }

}
