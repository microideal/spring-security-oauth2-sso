package com.microideal.springsecurityoauth2sso.domain.projection;

/**
 * @author microideal on 2018/9/21
 */
public interface RoleAuthorityWithSelected {
    Long getId();

    String getName();

    Integer getParentId();

    Integer getSort();

    String getValue();

    Boolean getChecked();// 前端框架只识别checked
}
