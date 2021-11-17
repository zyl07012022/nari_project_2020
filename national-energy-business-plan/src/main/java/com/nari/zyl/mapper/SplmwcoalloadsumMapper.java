package com.nari.zyl.mapper;

import com.nari.zyl.model.SplmwcoalloadSum;
import com.nari.zyl.model.SplmwdiversionSum;
import com.nari.zyl.model.StationTotalplanSum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SplmwcoalloadsumMapper
{
    List<SplmwcoalloadSum> getSplmwcoalloadSumListByCondition(SplmwdiversionSum splSum);

    int updateSplmwcoalloadSumStatusByCondition(Map<String,Object> paramMap);

    int insertSplmwcoalloadSumByCondition(SplmwcoalloadSum splmwcoalloadSum);

    List<StationTotalplanSum> getStationTotalplanSumByStationId();

    List<SplmwcoalloadSum> getNoSatisfiedSplmwcoalloadSumList(Map<String,Object> paraMap);
}
