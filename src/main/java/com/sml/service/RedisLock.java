package com.sml.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis分布式锁
 * Created by 神迷的亮
 * 2018-05-04 09:56
 */
@Component
@Slf4j
public class RedisLock
{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间＋超时时间
     * @return
     */
    public boolean lock(String key, String value)
    {
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value))
        {
            return true;
        }
        //如果锁过期
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis())
        {
            //获取上一个锁的时间
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value)
    {
        String currentVaule = stringRedisTemplate.opsForValue().get(key);
        try
        {
            if (!StringUtils.isEmpty(currentVaule) && currentVaule.equals(value))
            {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }
        catch (Exception ex)
        {
            log.error("[redis分布式锁] 解锁异常,{}", ex.getMessage());
        }

    }
}
