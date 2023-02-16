package top.werls.novel.systemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import top.werls.novel.systemapi.entity.Dict;

import java.util.Optional;

public interface DictRepository extends CrudRepository<Dict, Long>, JpaSpecificationExecutor<Dict> {
    Optional<Dict> findByCode(String code);



}