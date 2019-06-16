package fudan.se.lab4.repository;

import fudan.se.lab4.entity.User;

public interface UserRepository {

    String[] objectToStringArray(User user);

    User stringArrayToObject(String[] array);


}
