package com.ai.opt.data.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.ai.opt.data.dao.mapper.bo.UcMembersCriteria;
import com.ai.opt.data.dao.mapper.factory.MapperFactory;
import com.ai.opt.data.dao.mapper.interfaces.UcMembersMapper;
import com.ai.opt.data.service.atom.interfaces.ILoginAtomSV;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class LoginAtomSVImpl implements ILoginAtomSV {


    @Override
    public UcMembers queryByUserName(UcMembers ucMembers) {

    	UcMembersCriteria conditon = new UcMembersCriteria();
    	UcMembersCriteria.Criteria criteria = conditon.or();
        if (!StringUtil.isBlank(ucMembers.getMobilephone())) {
            criteria.andMobilephoneEqualTo(ucMembers.getMobilephone());
            criteria.andEnablestatusEqualTo("1");
        }
        if (!StringUtil.isBlank(ucMembers.getEmail())) {
            criteria.andEmailEqualTo(ucMembers.getEmail());
            criteria.andEnablestatusEqualTo("1");
        }
        if (!StringUtil.isBlank(ucMembers.getUsername())) {
            criteria.andUsernameEqualTo(ucMembers.getUsername());
            criteria.andEnablestatusEqualTo("1");
        }
//        // 登录标记为1
////        criteria.andLoginFlagEqualTo("1");
//        // 删除标记为0
//        
//        criteria.andDelFlagEqualTo("0");

        UcMembersMapper ucMembersMapper = MapperFactory.getUcMembersMapper();
        List<UcMembers> list = ucMembersMapper.selectByExample(conditon);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }


}
