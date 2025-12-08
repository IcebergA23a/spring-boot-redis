package org.example.service;

import org.example.entity.Player;
import org.example.entity.UserSign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BitmapService {

     Boolean userLogin(Integer userId);

     Boolean userIsLogin(Integer userId);

     Boolean userLogout(Integer userId);

     Boolean userSign(Integer userId, UserSign userSign);

     Boolean userIsSign(Integer userId, UserSign userSign);

     Long countUserSign(Integer userId, String yearMouth);
}
