package club.jackzhan.cloudstore.service.impl;

import club.jackzhan.cloudstore.constant.Constants;
import club.jackzhan.cloudstore.module.dto.MemberDTO;
import club.jackzhan.cloudstore.module.request.MemberQueryRequest;
import club.jackzhan.cloudstore.service.LoginService;
import club.jackzhan.cloudstore.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: hxy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	/**
	 * 登录表单提交
	 */
	@Override
	public ResultResponse authLogin(MemberQueryRequest request) {
		String loginName = request.getLoginName();
		String password = request.getPassword();
		if (StringUtils.isEmpty(loginName) && StringUtils.isEmpty(password)) {
			return ResultResponse.failure("账号或密码不能为空");
		}
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			return ResultResponse.failure();
		}
		return ResultResponse.success();
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public ResultResponse getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute(Constants.MEMBER_IN_SESSION);
//		List<RoleDTO> roles = memberDTO.getRoles();
//		session.setAttribute(Constants.MEMBER_ROLE_IN_SESSION, roles);
//		List<PermissionsDTO> list = new ArrayList<>();
//		for (RoleDTO role : roles) {
//			list.addAll(role.getPermissions());
//		}
//		session.setAttribute(Constants.MEMBER_PERMISSION_IN_SESSION, list);
		return ResultResponse.success(memberDTO);
	}

	/**
	 * 退出登录
	 */
	@Override
	public ResultResponse logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
			log.error("[LoginServiceImpl][logout]失败");
		}
		return ResultResponse.success();
	}
}
