package com.nari.zyl.service.Impl;

import com.nari.zyl.mapper.SkyLightMapper;
import com.nari.zyl.mapper.WeekOtherLoadCycleMapper;
import com.nari.zyl.model.Cycle;
import com.nari.zyl.model.SkyLightDay;
import com.nari.zyl.service.SkyLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SkyLightServiceImpl implements SkyLightService {

    @Override
    public List<SkyLightDay> getSkyLightList(String wPId) {

        //日历
        Calendar calendar = Calendar.getInstance();

        List<SkyLightDay> skyLightDays = new ArrayList<>();

        //一天的毫秒数
        long time = 24 * 60 * 60 * 1000;

        //星期
        String [] week = {"周日","周一","周二","周三","周四","周五","周六"};
        Cycle cycle = weekOtherLoadCycleMapper.getPlanById(wPId);
        if(cycle == null) {
            Date start = cycle.getWPBeginTimeDate();
            Date end = cycle.getWPEndTimeDate();

            //计划天开始时间
            long startTime = start.getTime() - start.getTime() % time + time;
            //计划天结束时间
            long endTime = end.getTime() - start.getTime() % time + time;

            for (long i = startTime; i <= endTime; i += time) {
                boolean isSkyLight = false;
                if (skyLightMapper.getSkylightDate(new Timestamp(i)) != null) {
                    isSkyLight = true;
                }

                calendar.setTimeInMillis(i);

                SkyLightDay skyLightDay = new SkyLightDay();
                skyLightDay.setSkyLight(isSkyLight);
                skyLightDay.setDay(new java.sql.Date(i));
                skyLightDay.setWeek(week[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
                skyLightDay.setMonthAndDay(calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                skyLightDays.add(skyLightDay);
            }
        }
        return skyLightDays;
    }

    @Autowired
    private SkyLightMapper skyLightMapper;
    @Autowired
    private WeekOtherLoadCycleMapper weekOtherLoadCycleMapper;
}