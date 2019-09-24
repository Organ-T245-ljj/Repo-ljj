package dao.backend.BackendUser;

import org.apache.ibatis.annotations.Param;

import pojo.BackendUser;
import pojo.DevUser;

public interface BackendUserMapper {
	/**
	 * µ«»Î
	 */
	public BackendUser getBackendUserByNameANDPwd(@Param("userCode")String userCode);
}
