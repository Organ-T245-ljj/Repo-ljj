package service.BackendUser.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.backend.BackendUser.BackendUserMapper;
import pojo.BackendUser;
import service.BackendUser.BackendUserService;

@Service("backendUserService")
public class BackendUserServiceImp implements BackendUserService {
	@Autowired
	private BackendUserMapper backendUserMapper;
	@Override
	public BackendUser getBackendUserByNameANDPwd(String userCode) {
		
		return backendUserMapper.getBackendUserByNameANDPwd(userCode);
	}
	
}
