package service.BackendUser;


import pojo.BackendUser;
import pojo.DevUser;

public interface BackendUserService {
	/**
	 * µ«»Î
	 */
	public BackendUser getBackendUserByNameANDPwd(String userCode);
}
