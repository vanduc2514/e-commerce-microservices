package com.rainbowforest.orderservice.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class CartRedisRepositoryImpl implements CartRedisRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Jedis jedis = new Jedis();

    @Override
    public void addItemToCart(String key, Object item) {
        try {
            String jsonObject = objectMapper.writeValueAsString(item);
            jedis.sadd(key, jsonObject);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Object> getCart(String key, Class type) {
        Collection<Object> cart = new ArrayList<>();
        for (String smember : jedis.smembers(key)) {
            try {
                cart.add(objectMapper.readValue(smember, type));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cart;
    }

    @Override
    public void deleteItemFromCart(String key, Object item) {
        try {
            String itemCart = objectMapper.writeValueAsString(item);
            jedis.srem(key, itemCart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(String key) {
        jedis.del(key);
    }
}
