package com.example.wating.util.enums;

public enum UserRoleEnum {
  CUSTOMER(Authority.CUSTOMER),
  MANAGER(Authority.MANAGER),
  OWNER(Authority.OWNER);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static class Authority {
    public static final String CUSTOMER = "ROLE_CUSTOMER";
    public static final String MANAGER = "ROLE_MANAGER";
    public static final String OWNER = "ROLE_OWNER";
  }
}
