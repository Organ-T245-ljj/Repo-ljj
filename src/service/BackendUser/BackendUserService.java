package service.BackendUser;


import pojo.BackendUser;
import pojo.DevUser;

public interface BackendUserService {
	/**
	 * ����
	 */
	public BackendUser getBackendUserByNameANDPwd(String userCode);
}
