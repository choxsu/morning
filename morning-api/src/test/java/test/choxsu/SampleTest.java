/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package test.choxsu;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.syc.MorningApplication;
import com.syc.model.entity.MoSysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author choxsu
 * @date 2021/3/4
 * @describe
 */
@SpringBootTest(classes = MorningApplication.class)
@RunWith(SpringRunner.class)
public class SampleTest {

//    @Autowired
//    private MoSysUserMapper moSysUserMapper;
//
//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<MoSysUser> userList = moSysUserMapper.selectList(Wrappers.lambdaQuery());
//        userList.forEach(System.out::println);
//    }

}
