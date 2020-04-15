package com.jankin.springboot.demo.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author lyy
 * @date 2020/4/15 12:13 下午
 */
@Data
public class UserVO {

    @Min(value = 3)
    private int id;

    @NotBlank()
    private String name;
}
