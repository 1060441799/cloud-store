package club.jackzhan.cloudstore.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author : JackZhan
 * Date: 2018-12-21
 * Time: 17:56
 */
public enum TrueFalseEnum {
    TRUE(1, "是"),
    FALSE(2, "否");

    @Getter
    private Integer code;
    @Getter
    private String desc;

    TrueFalseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
