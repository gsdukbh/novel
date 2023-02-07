package top.werls.novel.system.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.werls.novel.common.entity.SysUser;

@Repository
public interface SysUserRepository extends CrudRepository<SysUser, Integer> {

  Optional<SysUser> findByUsername(String username);

}