package com.jankin.springboot.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lyy
 * @date 2020/4/15 12:13 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    @NotNull
    @Min(0)
    private Long id;

    @NotBlank()
    private String name;
}
