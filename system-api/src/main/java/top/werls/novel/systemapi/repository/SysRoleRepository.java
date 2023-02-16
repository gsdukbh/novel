package top.werls.novel.systemapi.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.systemapi.entity.SysRole;

public interface SysRoleRepository extends CrudRepository<SysRole, Long> {

  List<SysRole> findBySysUsersUid(Long uid);

  Optional<SysRole> findByCode(String code);

}