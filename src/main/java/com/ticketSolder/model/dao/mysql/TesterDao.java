package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.NewUser;
import com.ticketSolder.model.domain.mysql.TestBean;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */
public interface TesterDao {

    List<String> test(@Param("TestBean")TestBean testBean);

    List<String> hello(@Param("name")String name);

    List<String> testTime(@Param("time")Time time);

    String testIf(@Param("fast") boolean fast);

    NewUser testOneToMany();
}
