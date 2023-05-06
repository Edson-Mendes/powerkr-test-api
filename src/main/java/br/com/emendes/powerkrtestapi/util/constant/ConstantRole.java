package br.com.emendes.powerkrtestapi.util.constant;

import br.com.emendes.powerkrtestapi.model.entity.Role;

public class ConstantRole {

  private ConstantRole(){}

  public static Integer USER_ROLE_ID = 1;
  public static String USER_ROLE_NAME = "ROLE_USER";
  public static Role USER_ROLE = new Role(USER_ROLE_ID, USER_ROLE_NAME);

}
