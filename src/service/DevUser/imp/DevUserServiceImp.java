package service.DevUser.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.developer.DevUser.DevUserMapper;
import pojo.DevUser;
import service.DevUser.DevUserService;

@Service("devUserService")
public class DevUserServiceImp implements DevUserService {
	@Autowired
	private DevUserMapper devUserMapper;	
	/**
	 * µ«»Î
	 */
	public DevUser getDevUserByCode(String devCode) {
		
		return devUserMapper.getDevUserByCode(devCode);
	}
		
}
