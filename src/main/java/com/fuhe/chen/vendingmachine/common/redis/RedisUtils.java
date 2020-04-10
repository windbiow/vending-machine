package com.fuhe.chen.vendingmachine.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Component
public class RedisUtils {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

//=========================================================common===================================================================================================================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 缓存失效时间
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存失效时间
     * @param key 键
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key删除缓存
     * @param key 键数组
     */
    public void del(String... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

//==============================================================String==============================================================================================================

    /**
     * 获取缓存
     * @param key 键
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 放入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  放入缓存并设置过期时间 (以秒计)
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try{
            if(time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 增加[delta]
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key,long delta){
        Assert.isTrue(delta>=0,"自增因子必须大于0");
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 减少[delta]
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key,long delta){
        Assert.isTrue(delta>=0,"自减因子必须大于0");
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * HashGet
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key,String item){
        Assert.isTrue(key!=null,"键不能为null");
        Assert.isTrue(item!=null,"项不能为null");
       return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * hmget
     * 获取key键对应的所有item和value
     * @param key
     * @return
     */
    public Map<Object,Object> hmget(String key){
        Assert.isTrue(key!=null,"键不能为null");
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashMSet
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key,Map<Object,Object> map){
        return hmset(key,map,-1);
    }

    /**
     * HashMSet并设置过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmset(String key,Map<Object,Object> map,long time){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key,String item,String value){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key,String item,Integer value){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key
     * @param item
     */
    public void hdel(String key, Object... item) {
        Assert.isTrue(key!=null,"键不能为null");
        Assert.isTrue(item!=null,"项不能为null");
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        Assert.isTrue(key!=null,"键不能为null");
        Assert.isTrue(item!=null,"项不能为null");
        return redisTemplate.opsForHash().hasKey(key, item);
    }
        /**
         * hash递增 如果不存在,就会创建一个 并把新增后的值返回
         * @param key 键
         * @param item 项
         * @param by 要增加几(大于0)
         * @return
         */
    public double hincr(String key, String item, double by) {
        Assert.isTrue(key!=null,"键不能为null");
        Assert.isTrue(item!=null,"项不能为null");
        Assert.isTrue(by>0,"自增因子必须大于0");
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    public double hincr(String key, String item, Integer by) {
        Assert.isTrue(key!=null,"键不能为null");
        Assert.isTrue(item!=null,"项不能为null");
        Assert.isTrue(by>0,"自增因子必须大于0");
        return redisTemplate.opsForHash().increment(key, item, by);
    }
        /**
         * hash递减
         * @param key 键
         * @param item 项
         * @param by 要减少记(小于0)
         * @return
         */
    public double hdecr(String key, String item, double by) {
        Assert.isTrue(key!=null,"键不能为null");
        Assert.isTrue(item!=null,"项不能为null");
        Assert.isTrue(by>0,"自增因子必须大于0");
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


}
