package club.jackzhan.cloudstore.module.request;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date: 2019-05-17 17:43
 *
 * @Author: JackZhan
 */
public class BaseRequest{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
