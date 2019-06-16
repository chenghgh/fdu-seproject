package fudan.se.lab4.service;

import fudan.se.lab4.entity.User;

public interface AccountService {

    boolean login(User user);

    boolean signup(User user);

    /**
     * Check the login status, you can maintain this status in environment variable.
     *
     * @return if user has already login, return true, else return false.
     */
    boolean checkStatus();

    /**
     *
     * @param name
     * @return if the input name is valid:
     * start with "starbb_"
     * consists of numbers, letters and "_"
     * the length must be greater than 8 and less than 50
     */
    boolean checkName(String name);

    /**
     *
     * @param password
     * @return if the input password is valid:
     * the length must be greater than 8 and less than 100
     * must be consisted of numbers, letters and "_"
     */
    boolean checkPassword(String password);

}
