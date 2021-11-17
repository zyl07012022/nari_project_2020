package com.nari.zyl.mapper;

import com.nari.zyl.model.Splmmsplitedit;
import com.nari.zyl.model.SplmwdiversionStationSum;
import com.nari.zyl.model.SplmwdiversionSum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SplmwdiversionSumMapper {

    //第一步，获取区内流向数据
    List<SplmwdiversionSum> getSplmwdiversionSumData(Map<String,Object> paramMap);

    //标记起始和目标管段,以及流向数据。
    int updateSplmwdiversionSumData(SplmwdiversionSum splSum);

    //第二步，按照站点,二级单位获取销售数据
    SplmwdiversionStationSum getSplmwdiversionSumDataByCondition(Map<String,Object> paramMap);

    List<SplmwdiversionSum> getSplmwdiversionSenvenDaysData(Splmmsplitedit splsplitObj);
}
