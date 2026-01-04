package org.example.service;

import java.util.List;
import java.util.Set;

public interface SetsService {

    Long addGoodFriend(String userName, List<String> friendNameList);

    Set<String> getCommonFriends(String userName1, String userName2);
}
