package com.ayushkumar.journalApp.Services;

import com.ayushkumar.journalApp.Entity.UrlConfigEntity;

import java.util.List;

public interface UrlConfigServices {
    public UrlConfigEntity createUrlConfig(UrlConfigEntity urlConfigEntity);
    public UrlConfigEntity findValueByKey(String key);
    public List<UrlConfigEntity> findAllValues();
}
