package net.engineeringdigest.journalApp2.scheduler;

import net.engineeringdigest.journalApp2.cache.AppCache;
import net.engineeringdigest.journalApp2.entity.JournalEntry;
import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.enums.Sentiment;
import net.engineeringdigest.journalApp2.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp2.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchduler {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepositoryImpl userRepository;



    @Autowired
    private AppCache appCache;

 //   @Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendMail(){

        List<User> users = userRepository.getUserForSA();

        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getPublishedDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());


            Map<Sentiment,Integer> sentimentCount=new HashMap<>();

            for(Sentiment sentiment : sentiments)
            {
                if(sentiment != null)
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount=0;
            for(Map.Entry<Sentiment,Integer> entry : sentimentCount.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    mostFrequentSentiment=entry.getKey();

                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",mostFrequentSentiment.toString());
            }

            // String entry= String.join(" ", filterEntires);
            // String sentiment= sentimentAnalysisService.getSentiment(entry);
            // emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",sentiment);

        }

    }

    @Scheduled(cron="0 0/10 * ? * *")
    public void clearAppcache(){
        appCache.init();
    }

}
