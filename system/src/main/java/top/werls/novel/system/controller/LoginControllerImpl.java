package top.werls.novel.system.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;
import top.werls.novel.common.SecurityRequirementConfig;
import top.werls.novel.systemapi.controller.LoginController;
import top.werls.novel.systemapi.param.LoginParam;
import top.werls.novel.system.service.SysUserService;
import top.werls.novel.systemapi.vo.LoginVo;

@Slf4j
@RestController
@SecurityRequirement(name =  SecurityRequirementConfig.TOKEN_HEADER)
public class LoginControllerImpl implements LoginController {

    @Resource
    private SysUserService userService;

    @Override
    @Operation(summary = "用户登陆")
    @PostMapping("/login")
    public ResultData<LoginVo> login(@RequestBody LoginParam param) {
        return ResultData.success(userService.login(param));
    }
}
