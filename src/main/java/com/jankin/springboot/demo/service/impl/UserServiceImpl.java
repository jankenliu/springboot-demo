package com.jankin.springboot.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jankin.springboot.demo.mapper.UserMapper;
import com.jankin.springboot.demo.model.po.User;
import com.jankin.springboot.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
* UserService实现
*  @author lyy
*  @date 2018-12-9
*/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Object findAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public Object findUserByPage(int pageNum, int pageSize) {
        //配置文件已将rowBounds做主动物理分页
        return userMapper.selectByRowBounds(new User(),new RowBounds(pageNum,pageSize));
    }

    @Override
    public Object findUserByPage2(int pageNum, int pageSize) {
        //插件物理分页用法
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll();
        return new PageInfo<>(userList);
    }

    @Transactional
    @Override
    public Object addUser(String name, int gender, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setGender(""+gender);
        // TODO: 2019/2/27 运行generator时会自动删除 mapper.java文件 需要解决不能让它删除的问题
        int addUser = userMapper.insertTest(user);
        log.info("当前新增用户主键："+user.getUserId());
        if (gender==100){
            return "用户添加成功!"+addUser;
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "用户添加失败!";
        }
    }
}