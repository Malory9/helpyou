package com.helpyouJFinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * @author Administrator
 * 用于拦截AJAX未登录情况下的非浏览操作
 */
public class AJAXAuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation invocation) {
		Controller controller = invocation.getController();
		//ajax时无法获取session，因此会将userId作为参数传递过来
		Integer userId = controller.getParaToInt("userId");
		//判断用户是否有修改信息的权限
		if (userId != null && userId > 0) {			
			invocation.invoke();
		} else {
			controller.renderText("noUser");
		}

	}

}
