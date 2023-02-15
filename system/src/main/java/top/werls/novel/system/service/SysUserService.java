package top.werls.novel.system.service;


import top.werls.novel.systemapi.param.LoginParam;
import top.werls.novel.systemapi.vo.LoginVo;


public interface SysUserService {


    /**
     *
     * 登录
     * @param param
     * @return
     */
    LoginVo login(LoginParam param);
}
