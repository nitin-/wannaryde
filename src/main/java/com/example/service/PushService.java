package com.example.service;

import com.example.web.model.GcmContent;

public interface PushService {
	
	void pushAndroidMessage(GcmContent content);
	
	void  pushIOSMessage(String regId, String title, String message);
	
	void sendMessage(String email, String title, String message);
	
}
