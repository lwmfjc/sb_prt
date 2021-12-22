package com.ly.servise;

import com.ly.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SayService implements ISay {
    private final User user;
    @Override
    public String sayWord() {
        return "hello," + user;
    }
}
