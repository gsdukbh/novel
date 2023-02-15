package top.werls.novel.system.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.systemapi.entity.SysRole;

public interface SysRoleRepository extends CrudRepository<SysRole, Long> {

  List<SysRole> findBySysUsersUid(Long uid);

}