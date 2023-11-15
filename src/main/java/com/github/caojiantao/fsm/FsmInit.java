package com.github.caojiantao.fsm;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

public class FsmInit implements InitializingBean {

    @Resource
    private ApplicationContext context;

    @Override
    public void afterPropertiesSet() throws Exception {
        FsmFactory.init(context);
    }
}
