package com.example.service.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dal.dao.PushNotificationDao;
import com.example.dal.entity.PushNotificationCrd;
import com.example.service.PushService;
import com.example.web.model.GcmContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

@Service
@Transactional(readOnly = true)
public class PushServiceImpl implements PushService {
    private static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    private static final String GCM_SERVER_URL = "https://android.googleapis.com/gcm/send";
    private static final String DEVICE_ANDROID = "ANDROID";
    private static final String DEVICE_IOS = "IOS";

    @Value("${android.gcm.apikey}")
    public String androidGcmApiKey;

    @Autowired
    Environment env;

    @Autowired
    PushNotificationDao pushNotificationDao;

    ApnsService apnsService =  APNS.newService()
            .withCert(getClass().getResourceAsStream("apns_cert.p12"), "creative")
            .withProductionDestination()
            .build();


    @Override
    public void sendMessage(String email, String title, String message) {
        PushNotificationCrd pushNotificationCrd = pushNotificationDao.findByUserEmail(email);
        if(pushNotificationCrd == null) return;

        if(StringUtils.equalsIgnoreCase(pushNotificationCrd.getDevice(), DEVICE_ANDROID)) {
            GcmContent c = new GcmContent();
            c.addRegId(pushNotificationCrd.getRegistrationId());
            c.createData(title, message);
            this.pushAndroidMessage(c);
        } else if (StringUtils.equalsIgnoreCase(pushNotificationCrd.getDevice(), DEVICE_IOS)) {
            this.pushIOSMessage(pushNotificationCrd.getRegistrationId(), title, message);
        }
    }

    @Override
    public void pushAndroidMessage(GcmContent content) {
        try {
            URL url = new URL(GCM_SERVER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + androidGcmApiKey);
            conn.setDoOutput(true);
            ObjectMapper mapper = new ObjectMapper();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            mapper.writeValue(wr, content);
            wr.flush();
            wr.close();
            int responseCode = conn.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void pushIOSMessage(String regId, String title, String message) {
		// TODO Auto-generated method stub
		
	}
}
