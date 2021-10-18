package com.yclin.simplecarlease.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties({"password"})
public class User extends BaseModel {

    protected String nickname;

    protected String password;
}
