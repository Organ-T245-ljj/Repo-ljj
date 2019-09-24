package dao.developer.DevUser.imp;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.developer.DevUser.DevUserMapper;
import pojo.DevUser;

public class DevUserMapperImp extends SqlSessionDaoSupport implements DevUserMapper {

	/**
	 * µ«»Î
	 */
	public DevUser getDevUserByCode(String devCode) {
		
		return super.getSqlSession().getMapper(DevUserMapper.class).getDevUserByCode(devCode);
	}

}
