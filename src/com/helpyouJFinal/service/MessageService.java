package com.helpyouJFinal.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class MessageService {

}
