package com.microideal.springsecurityoauth2sso.repository;

import com.microideal.springsecurityoauth2sso.domain.Authority;
import com.microideal.springsecurityoauth2sso.domain.projection.RoleAuthorityWithSelected;
import com.microideal.springsecurityoauth2sso.domain.projection.UserRoleWithSelected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author microideal on 2018/9/21
 */
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    //nativeQuery = true，以数据库sql运行，字段不需要和实体字段对应
    //springboot 2.0.5中spring-boot-starter-data-jpa用的是2.0.10版本的，还不支持返回class，只能返回接口。查官方文档，新版本已经支持返回class了
    @Query(nativeQuery = true,value = "SELECT a.id,a.name,a.parent_id AS parentId,a.value,a.type,(CASE WHEN EXISTS(SELECT 1 FROM role_authorities ra WHERE ra.authorities_id = a.id AND ra.role_id = ?1) THEN 'true' ELSE 'false' END) AS checked FROM authority a /*WHERE a.parent_id != 0*/ ORDER BY a.sort ASC")
    List<RoleAuthorityWithSelected> queryRoleAuthorityWithSelectedByRoleId(Long roleId);

    @Query(nativeQuery = true,value = "SELECT COUNT(*) FROM role_authorities WHERE authorities_id = ?1")
    Integer queryRoleAuthority(Long authorityId);

}
