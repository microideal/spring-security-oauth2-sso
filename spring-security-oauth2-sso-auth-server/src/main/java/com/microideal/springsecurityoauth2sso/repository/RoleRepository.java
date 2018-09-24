package com.microideal.springsecurityoauth2sso.repository;

import com.microideal.springsecurityoauth2sso.domain.Role;
import com.microideal.springsecurityoauth2sso.domain.projection.UserRoleWithSelected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    //nativeQuery = true，以数据库sql运行，字段不需要和实体字段对应
    //springboot 2.0.5中spring-boot-starter-data-jpa用的是2.0.10版本的，还不支持返回class，只能返回接口。查官方文档，新版本已经支持返回class了
    @Query(nativeQuery = true,value = "SELECT r.id,r.name,(CASE WHEN (SELECT ur.user_id FROM user_roles ur WHERE ur.user_id= ?1 AND ur.roles_id = r.id) THEN 1 ELSE 0 END) AS selected FROM role r")
    List<UserRoleWithSelected> queryUserRoleWithSelectedByUserId(Long userId);

    @Query(nativeQuery = true,value = "SELECT COUNT(*) FROM user_roles WHERE roles_id = ?1")
    Integer queryUserRole(Long roleId);
}
