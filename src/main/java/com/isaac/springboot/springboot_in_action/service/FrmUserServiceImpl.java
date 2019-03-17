package com.isaac.springboot.springboot_in_action.service;

import com.isaac.springboot.springboot_in_action.dao.FrmUserRepository;
import com.isaac.springboot.springboot_in_action.entity.FrmUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "users")
public class FrmUserServiceImpl implements FrmUserService {
    private static final Log LOG =  LogFactory.getLog(FrmUserServiceImpl.class);

    @Autowired
    private FrmUserRepository frmUserDao;

    @Override
    @Transactional
    @CachePut(cacheNames = "users")//总执行方法并更新缓存
    public Integer addFrmUser(FrmUser user) {
        //entity主键属性为空——保存实体，不为空——更新实体
        user.setCreateTime(new Date());
        FrmUser user1 = frmUserDao.save(user);
//        user1.setName("updateName");
//        user1.setCreateTime(new Date());
//        FrmUser update = frmUserDao.save(user1);
        return user1.getId();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "users")//缓存存在则不进入方法,不存在则进入方法并缓存
    public List<FrmUser> querySortedUsers() {
        LOG.info("come in query sorted users...");
        Sort sort = createSort();
        return frmUserDao.findAll(sort);
    }

    private Sort createSort() {
//        return new Sort("departmentId");//指定属性，默认升序
        return new Sort(Sort.Direction.DESC, "departmentId", "id");//指定升降序
//        return new Sort(Sort.Order.asc("departmentId"));
    }

    @Override
    @Transactional(readOnly = true)
    @CacheEvict(cacheNames = "users",allEntries = true)//清除缓存 有了@CacheConfig就不用指定cacheNames了
    public List<FrmUser> queryPagedUsers(int page, int size) {
        LOG.info("query Paged Users page is " + page + " size is " + size);
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        Page<FrmUser> pageObject = frmUserDao.findAll(pageable);
        return pageObject.getContent();
    }
    //@Caching(evict={@CacheEvict(cacheName="users",key="#user.id"),...})//组合cache注解
}
