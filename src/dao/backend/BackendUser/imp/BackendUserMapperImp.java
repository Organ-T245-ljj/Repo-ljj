package dao.backend.BackendUser.imp;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.backend.BackendUser.BackendUserMapper;
import pojo.BackendUser;
import pojo.DevUser;

public class BackendUserMapperImp extends SqlSessionDaoSupport implements BackendUserMapper {
	
	/**
	 * µ«»Î
	 */
	
	public BackendUser getBackendUserByNameANDPwd(@Param("userCode")String userCode){
		return super.getSqlSession().getMapper(BackendUserMapper.class).getBackendUserByNameANDPwd(userCode);
	}
}
