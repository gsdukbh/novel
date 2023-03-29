package top.werls.novel.system.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Resource;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.werls.novel.common.ResultData;
import top.werls.novel.common.SecurityRequirementConfig;
import top.werls.novel.systemapi.entity.SysUser;
import top.werls.novel.systemapi.param.LoginParam;
import top.werls.novel.system.service.SysUserService;
import top.werls.novel.systemapi.vo.LoginVo;

@Slf4j
@RestController
@SecurityRequirement(name =  SecurityRequirementConfig.TOKEN_HEADER)
public class LoginController {

    @Resource
    private SysUserService userService;

    @Operation(summary = "用户登陆")
    @PostMapping("/login")
    public ResultData<LoginVo> login(@RequestBody LoginParam param) {
        return ResultData.success(userService.login(param));
    }

    @Operation(summary = "获取当前用户信息",description = "根据当前取登录信息")
    @GetMapping("/me")
    public ResultData<SysUser> userInfo( Principal principal){
        var result = userService.me(principal.getName());
        return ResultData.success(principal.getName(),result);
    }
    @Operation(summary = "根据id获取",description = "更具用户id获取信息")
    @GetMapping("/u/{id:\\d+}")
    public  ResultData<SysUser> userById(@PathVariable Integer id){
        return ResultData.success(userService.userById(id));
    }
}
