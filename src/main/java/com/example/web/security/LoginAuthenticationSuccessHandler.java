package com.example.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.dal.dao.PushNotificationDao;
import com.example.dal.entity.PushNotificationCrd;
import com.example.web.model.response.UserDetailsResponse;

@Component
public class LoginAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter;
	
	@Autowired
	PushNotificationDao pushDao;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		UserDetailsResponse userDetails = (UserDetailsResponse)authentication.getPrincipal();
		PrintWriter writer = response.getWriter();
		mappingJacksonHttpMessageConverter.getObjectMapper().writeValue(writer, userDetails);
		try{
        	savePushCrd(userDetails);
        }catch(Exception e){
        	e.printStackTrace();
        }
		writer.flush();
		clearAuthenticationAttributes(request);
	}

	/*private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			clearAuthenticationAttributes(request);
			return;
		}
		String targetUrlParam = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParam != null && StringUtils.hasText(request
						.getParameter(targetUrlParam)))) {
			requestCache.removeRequest(request, response);
			clearAuthenticationAttributes(request);
			return;
		}
		clearAuthenticationAttributes(request);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}*/
	
	 private void savePushCrd(UserDetailsResponse userDetails){
	    	PushNotificationCrd push = pushDao.findByUserEmail(userDetails.getEmail());
	    	if(null == push) {
	    		push = new PushNotificationCrd();
	    		//push.setUserId(userDetails.getId());
	    		//TODO more Params
	    	}
	    	pushDao.save(push);
	    	
	    }
}