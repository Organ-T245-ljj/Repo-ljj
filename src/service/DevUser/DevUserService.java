package service.DevUser;

import org.apache.ibatis.annotations.Param;

import pojo.DevUser;

public interface DevUserService {
	/**
	 * ����
	 */
	public DevUser getDevUserByCode(@Param("devCode")String devCode);
}
