package dao.developer.DevUser;

import org.apache.ibatis.annotations.Param;

import pojo.DevUser;

public interface DevUserMapper {
	/**
	 * ����
	 */
	public DevUser getDevUserByCode(@Param("devCode")String devCode);
}
