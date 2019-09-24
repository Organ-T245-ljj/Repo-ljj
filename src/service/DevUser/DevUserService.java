package service.DevUser;

import org.apache.ibatis.annotations.Param;

import pojo.DevUser;

public interface DevUserService {
	/**
	 * µ«»Î
	 */
	public DevUser getDevUserByCode(@Param("devCode")String devCode);
}
