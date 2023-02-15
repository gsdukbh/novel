package top.werls.novel.systemapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;
import top.werls.novel.common.SecurityRequirementConfig;
import top.werls.novel.systemapi.param.LoginParam;
import top.werls.novel.systemapi.vo.LoginVo;

/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/2/15
 * @since on
 */
@RestController
@SecurityRequirement(name =  SecurityRequirementConfig.TOKEN_HEADER)
public interface LoginController {

  ResultData<LoginVo> login(@RequestBody LoginParam param);

}
