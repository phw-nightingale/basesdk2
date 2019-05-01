package xyz.frt.basesdk2.redis;

import com.google.gson.Gson;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xyz.frt.basesdk2.common.AppContext;
import xyz.frt.basesdk2.entity.BaseEntity;
import xyz.frt.basesdk2.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RedisTemplateService {

    private final Gson gson;

    private final StringRedisTemplate srt;

    public RedisTemplateService(StringRedisTemplate srt, Gson gson) {
        this.srt = srt;
        this.gson = gson;
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param <T> item
     * @return 是否完成
     */
    public <T extends BaseEntity> boolean set(String key, T value) {

        if (value != null) {
            String val = gson.toJson(value);
            User user = AppContext.getCurrentUser();
            if (user != null) {
                value.setCreateUser(user.getUsername());
                value.setCreateUserId(user.getId());
                value.setCreateTime(new Date());
            }
            srt.opsForValue().set(key, val);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 从缓存中获取
     * @param key 键
     * @param clazz 类型
     * @param <T> item
     * @return 对象
     */
    public <T extends BaseEntity> T get(String key, Class<T> clazz) {
        String value = srt.opsForValue().get(key);
        if (value != null && value.length() != 0) {
            return gson.fromJson(value, clazz);
        } else {
            return null;
        }
    }

    /**
     * 根据key的匹配结果来获取值的集合
     * @param pattern 匹配的键
     * @param clazz 类型
     * @param <T> item
     * @return 结果集
     */
    public <T extends BaseEntity> Set<T> getAll(String pattern, Class<T> clazz) {
        if (pattern == null || pattern.length() == 0) {
            pattern = "*";
        }
        Set<T> vals = new HashSet<>();
        Set<String> keys = srt.keys(pattern);
        for(String key: keys) {
            T item = gson.fromJson(srt.opsForValue().get(key), clazz);
            vals.add(item);
        }
        return vals;
    }

    /**
     * 查询缓存是否有次key
     * @param key key
     * @return 结果
     */
    public boolean hasKey(String key) {
        return srt.hasKey(key);
    }

    /**
     * 清除缓存
     */
    public void clear() {
        Set<String> keys = srt.keys("*");
        if (keys != null && keys.size() > 0) {
            for (String key: keys) {
                srt.delete(key);
            }
        }
    }

}
