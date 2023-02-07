package top.werls.novel.system.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.common.entity.SysRole;

public interface SysRoleRepository extends CrudRepository<SysRole, Long> {

  List<SysRole> findBySysUsersUid(Long uid);

}