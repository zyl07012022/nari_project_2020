package com.nari.zyl.facade;

public interface AreaFlowFacade {

    //区内、特殊流向分配,第一步
    public int compute(String wPid);

    //区内、特殊流向分配,第二步
    public int confirmSaleDemand() throws Exception;

}
