package com.sf.bcsp.service.base;


import com.sf.bcsp.base.entity.BcspSystemJob;
import com.sf.bcsp.core.task.ITask;
import com.sf.bcsp.exception.TimeOutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BcspBillControlTemplateLocal implements ITask {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(BcspBillControlTemplateLocal.class);

    private BcspSystemJob systemJob;



    public void setParameter(BcspSystemJob systemJob) {
        this.systemJob = systemJob;
    }


    // 1：加载客户信息
    @Override
    public boolean doStart() throws TimeOutException
    {
        logger.info("Thread Name: %s Started ! ", getName());

        return true;

    }


    @Override
    public String getName(){
        return systemJob.getAccountCode();
    }


    // 2:加载网点信息

    // 3：加载

}
