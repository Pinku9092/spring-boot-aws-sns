package com.pinku.aws.sns.api.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSSNSExampleController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    String TOPIC_ARN = "";

    @GetMapping("/addSubscription/{email}")
    public String addSubscription(@PathVariable String email){
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN,"email","email.1@gmail.com");
        amazonSNSClient.subscribe(request);
        return "Subscription request is pending. To confirm the subscription, check your email : " + email;

    }

    @GetMapping("/sendNotification")
    public String publishMessageToTopic(){
        PublishRequest publishRequest=new PublishRequest(TOPIC_ARN,buildEmailBody(),"Notification: Network connectivity issue");
        amazonSNSClient.publish(publishRequest);
        return "Notification send successfully !!";
    }

    private String buildEmailBody(){
        return "Dear Employee ,\n" +
                "\n" +
                "\n" +
                "Connection down Bangalore."+"\n"+
                "All the servers in Bangalore Data center are not accessible. We are working on it ! \n" +
                "Notification will be sent out as soon as the issue is resolved. For any questions regarding this message please feel free to contact IT Service Support team";
    }
}
