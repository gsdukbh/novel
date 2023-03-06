package top.werls.novel.systemapi.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.systemapi.entity.SysRole;

public interface SysRoleRepository extends CrudRepository<SysRole, Long> {

  List<SysRole> findBySysUsersUid(Long uid);

  Optional<SysRole> findByCode(String code);

  @Query("select s from SysRole s inner join s.sysUsers sysUsers where sysUsers.username = ?1")
  Optional<SysRole> findBySysUsersUsername(String username);

}