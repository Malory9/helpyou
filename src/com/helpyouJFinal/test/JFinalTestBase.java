package com.helpyouJFinal.test;

import org.junit.After;
import org.junit.BeforeClass;

import com.helpyouJFinal.model.Message;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * @author Administrator 
 * JFinal的Model测试用例，使用时继承这个类，并在这个类中添加相关数据库表和model的映射即可
 */
public class JFinalTestBase {
	protected static C3p0Plugin c3p0Plugin;
	protected static ActiveRecordPlugin activeRecordPlugin;

	@BeforeClass
	public static void setUpBeforeClass() {

		// 用PropKit读取jdbc.properties文件中的配置信息
		PropKit.use("jdbc.properties");
		final String URL = PropKit.get("jdbcUrl");
		final String USERNAME = PropKit.get("username");
		final String PASSWORD = PropKit.get("password");
		final String DRIVERCLASS = PropKit.get("driverClass");
		final Integer INITIALPOOLSIZE = PropKit.getInt("initialPoolSize");
		final Integer MAXIDLETIME = PropKit.getInt("maxIdleTime");
		final Integer MAXPOOLSIZE = PropKit.getInt("maxPoolSize");
		final Integer MINPOOLSIZE = PropKit.getInt("minPoolSize");
		
		c3p0Plugin = new C3p0Plugin(URL, USERNAME, PASSWORD, DRIVERCLASS);
		c3p0Plugin.setInitialPoolSize(INITIALPOOLSIZE); // 初始化连接池中的连接数
		c3p0Plugin.setMaxIdleTime(MAXIDLETIME); // 设置最大空闲时间
		c3p0Plugin.setMaxPoolSize(MAXPOOLSIZE); // 设置连接池中的最大连接数
		c3p0Plugin.setMinPoolSize(MINPOOLSIZE); // 设置连接池中的最小连接数
		
	    c3p0Plugin.start();
	    
	    activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);
	    //使用开发模式并打印sql语句
	    activeRecordPlugin.setDialect(new MysqlDialect()).setDevMode(true).setShowSql(true);
	    
	    //添加数据库表和model的映射
	    activeRecordPlugin.addMapping("message", "messageId", Message.class);
	    
	    activeRecordPlugin.start();
	}
	
	@After
	public void close() {
		activeRecordPlugin.stop();
		c3p0Plugin.stop();
	}
}
