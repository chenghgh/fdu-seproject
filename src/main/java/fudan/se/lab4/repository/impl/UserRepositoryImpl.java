package fudan.se.lab4.repository.impl;

import fudan.se.lab4.entity.User;
import fudan.se.lab4.repository.UserRepository;
import org.mortbay.util.ajax.JSON;


import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {




    public String[] objectToStringArray(User user) {
        // if user already exists, throw exception
//        if (FileUtil.exist(user.getName(), FileConstant.USER_CSV)) {
//            throw new RuntimeException(MessageFormat.format(InfoConstant.Entity_EXIST, "User",
//                    user.getName()));
//        }
        String[] array = new String[2];
        array[0] = user.getName();
        array[1] = user.getPassword();
        return array;
    }

    public User stringArrayToObject(String[] array) {
        User user = new User();
        user.setName(array[0]);
        user.setPassword(array[1]);
        return user;
    }

    public String userToJsonString(User user){
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("password", user.getPassword());
        userMap.put("name", user.getName());
        userMap.put("tableName", "userInfo");
        return JSON.toString(userMap);
    }
}
