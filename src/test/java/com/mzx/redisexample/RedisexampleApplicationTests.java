package com.mzx.redisexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzx.redisexample.dao.UserDao;
import com.mzx.redisexample.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RedisexampleApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() throws JsonProcessingException {


        // 防止redis缓存残留，程序运行前预清理
        redisTemplate.delete("users");

        // 使用for循环模拟连续请求
        for (int i = 0; i <= 1; i++) {

            // BoundValueOperations 封装了key对应的内容，可以通过ops对象操作：设置、获取key对应的值 同时通过setIfPresent方法可设置key过期时间
            // 根据项目需求 数据存储都为json格式，此处不使用Object而使用String 可以减少将Object类型对象转换为指定的实体类对象所耗费的多余内存与时间
            // 1. 先读取redis中key值为users的存储对象
            BoundValueOperations<String, String> ops = redisTemplate.boundValueOps("users");

            // 2. 获得key对应的值
            String obj = ops.get();

            //准备jackson 方便json <=> List 数据互转
            ObjectMapper objectMapper = new ObjectMapper();

            // 3. 判断redis是否存在对应值
            if (obj == null){ //redis当中没有获得值,说明数据没有缓存到redis,进行数据查询 缓存操作

                // 3.1 调用dao查询数据
                List<User> allUsers = userDao.findUserAll();
                System.out.println("从数据库获取当前值："+allUsers);

                // 3.2 将List数据集合转成json，缓存入redis 这里使用jackson进行转换
                String jsonUser = objectMapper.writeValueAsString(allUsers);
                ops.set(jsonUser);

            }else{ // redis已经缓存数据

                // redis中json数据转List集合
                // jackson中json数据转List<User>操作
                // readValue方法传入 redis中的json数据(obj)与JavaType 即可将json数据转化为指定对象
                List<User> redisAllUsers = objectMapper.readValue(obj,objectMapper.getTypeFactory().constructParametricType(ArrayList.class,User.class));
                System.out.println("从redis获取当前值："+redisAllUsers);
            }

            // 模拟完成，进行删除缓存操作
            if (i==1){
                System.out.println("两次请求操作完成，开始删除缓存");
                redisTemplate.delete("users");
            }
        }

    }

}
