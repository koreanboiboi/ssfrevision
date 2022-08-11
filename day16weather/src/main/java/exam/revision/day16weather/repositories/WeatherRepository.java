package exam.revision.day16weather.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {
    

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public void save(String city, String payload) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(city.toLowerCase(), payload);
    }

    public Optional<String> get(String city) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(city.toLowerCase());
        if (null == value)
            return Optional.empty(); // empty box
        return Optional.of(value); // box with data
    }
    


}
