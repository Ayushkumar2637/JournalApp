package com.ayushkumar.journalApp.Scheduler;

import com.ayushkumar.journalApp.AppCache.AppCache;
import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Enum.Sementic;
import com.ayushkumar.journalApp.Repository.UserRepo;
import com.ayushkumar.journalApp.Services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EmailAndAppCacheScheduler {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;


//    corn = sec min hour etc. Below indicate that at 0 sec , 0 min , 0 hour it will trigger . i.e it will trigger on daily basic
//    @Scheduled(cron = "0 0 0 * * *")
    public void getSAandSendMail(){
        List<UserEntity> userEntityList=userRepo.getUserHaveSA();
        for(UserEntity user:userEntityList){
            List<Sementic> sementicList=user.getJournalEntities()
                    .stream()
                    .map(journal -> journal.getSementic())
                    .collect(Collectors.toList());
            log.info(sementicList.toString());
            String sementicValue=getSementicData(sementicList);
            emailService.sendMail(user.getEmail(),"Sementic Mail",sementicValue);
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    public void cacheSchedule(){
        appCache.init();
    }

    private String getSementicData(List<Sementic> sementicList){
        Map<Sementic,Integer> hm=new HashMap<>();
        int mx=Integer.MIN_VALUE;
        String maxFrequency="";
        for(Sementic sementic:sementicList){
            if(sementic!=null){
                hm.put(sementic,hm.getOrDefault(sementic,0)+1);
                int value=hm.get(sementic);
                if(value>mx){
                    mx=value;
                    maxFrequency=sementic.toString();
                }
            }
        }
        return maxFrequency;
    }

}
