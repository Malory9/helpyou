package com.helpyouJFinal.config;

import com.helpyouJFinal.controller.MainController;
import com.helpyouJFinal.controller.TaskController;
import com.helpyouJFinal.controller.UserController;
import com.helpyouJFinal.model.Admin;
import com.helpyouJFinal.model.Message;
import com.helpyouJFinal.model.Notice;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.TaskAccept;
import com.helpyouJFinal.model.TaskPublish;
import com.helpyouJFinal.model.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class MainConfig extends JFinalConfig {

	// 进行一些通用设置
	@Override
	public void configConstant(Constants me) {
		// 用PropKit读取config.properties文件中的配置信息
		PropKit.use("config.properties");
		// 设置开发模式（log日志）
		me.setDevMode(PropKit.getBoolean("devMode"));
		// 设置编码
		me.setEncoding(PropKit.get("encoding"));
		// 设置视图渲染(render)模式
		me.setViewType(ViewType.JSP);
		me.setBaseViewPath("/WEB-INF/view/");
	}

	@Override
	public void configRoute(Routes me) {
		/* 
		 * "/"访问到MainController这个类的index()方法，这是约定,
		 * 第三个参数viewPath表示接下来的跳转相对路径，需要配成"/"否则会出现路径跳转错误,
		 */
		 me.add("/",MainController.class,"/");
		 me.add("/task", TaskController.class, "/");
		 me.add("/user", UserController.class, "/");
		 
	}

	// 用于使用JFinal的插件
	@Override
	public void configPlugin(Plugins me) {

		final String URL = PropKit.get("jdbcUrl");
		final String USERNAME = PropKit.get("username");
		final String PASSWORD = PropKit.get("password");
		final String DRIVERCLASS = PropKit.get("driverClass");
		final Integer INITIALPOOLSIZE = PropKit.getInt("initialPoolSize");
		final Integer MAXIDLETIME = PropKit.getInt("maxIdleTime");
		final Integer MAXPOOLSIZE = PropKit.getInt("maxPoolSize");
		final Integer MINPOOLSIZE = PropKit.getInt("minPoolSize");

		// 注册数据库连接池c3p0Plugin插件，配置数据库连接
		C3p0Plugin c3p0Plugin = new C3p0Plugin(URL, USERNAME, PASSWORD,DRIVERCLASS);
		c3p0Plugin.setInitialPoolSize(INITIALPOOLSIZE);	//初始化连接池中的连接数
		c3p0Plugin.setMaxIdleTime(MAXIDLETIME);			//设置最大空闲时间
		c3p0Plugin.setMaxPoolSize(MAXPOOLSIZE);			//设置连接池中的最大连接数
		c3p0Plugin.setMinPoolSize(MINPOOLSIZE);			//设置连接池中的最小连接数
		me.add(c3p0Plugin);
		
		//注册activeRecord插件，配置数据库映射
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);
		//设置MySQL方言
		activeRecordPlugin.setDialect(new MysqlDialect());
		//添加model类和数据库表的映射，user指的是表名，userid指的是主键
		//表的主键名默认为id，所以需要手动配置
		activeRecordPlugin.addMapping("user", "userId", User.class);
		activeRecordPlugin.addMapping("task", "taskId", Task.class);
		activeRecordPlugin.addMapping("admin", "adminId", Admin.class);
		activeRecordPlugin.addMapping("message", "messageId", Message.class);
		activeRecordPlugin.addMapping("notice", "noticeId", Notice.class);
		activeRecordPlugin.addMapping("taskAccept", "acceptId", TaskAccept.class);
		activeRecordPlugin.addMapping("taskPublish", "publishId", TaskPublish.class);
		me.add(activeRecordPlugin);
	}

	// 这里用于配置全局的拦截器，对所有请求进行拦截
	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		/*
		 * ContextPathHandler，在每次请求时将request.getContextPath()
		 * 这里指"/helpyouJFinal"） 设置到HttpServletRequest的属性"BASE_PATH"中
		 * 在jsp页面可以直接使用${basePath}，可以解决路径问题
		 */
		me.add(new ContextPathHandler("BASE_PATH"));
	}

	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

}
