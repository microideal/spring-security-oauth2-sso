package com.microideal.springsecurityoauth2sso.domain.projection;

/**
 * @author microideal on 2018/9/21
 */
/*@Data
@NoArgsConstructor
@AllArgsConstructor*/
public interface UserRoleWithSelected {
    /*private Long id;//角色id

    private String name;

    private Integer selected;*/

    Long getId();

    String getName();

    Integer getSelected();
}
