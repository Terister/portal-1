package com.pab.framework.portal.controller;

import com.alibaba.fastjson.JSON;
import com.pab.framework.portal.biz.PortalUserRoleBiz;
import com.pab.framework.portal.models.PortalUserRoleModel;
import com.pab.framework.portal.utils.mybatis.Pagination;
import com.pab.framework.portal.utils.mybatis.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/PortalUserRole")
public class PortalUserRoleController {
    @Autowired
    PortalUserRoleBiz portaluserroleBiz;

    @RequestMapping(value = "/get")
    public ResponseData<PortalUserRoleModel> get(Long id) {
        ResponseData<PortalUserRoleModel> responseData = new ResponseData<>();
        try {
            responseData.setCode(0);
            responseData.setMsg("success");
            responseData.setData(portaluserroleBiz.getModelById(id));
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setCode(1);
            responseData.setMsg(e.getMessage());
        }
        return responseData;
    }

    @PostMapping(value = "/save")
    public ResponseData<PortalUserRoleModel> save(String datas) {
        PortalUserRoleModel item = JSON.parseObject(datas, PortalUserRoleModel.class);
        ;
        ResponseData<PortalUserRoleModel> responseData = new ResponseData<>();
        try {
            responseData.setCode(0);
            responseData.setMsg("success");
            if (item.getId() > 0) {
                portaluserroleBiz.update(item);
            } else {
                portaluserroleBiz.add(item);
            }
            responseData.setData(item);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setCode(1);
            responseData.setMsg(e.getMessage());
        }
        return responseData;
    }

    @RequestMapping(value = "/getPageList")
    public ResponseData<Pagination> getPageList(@RequestParam(value = "pageIndex", required = true) int pageIndex, @RequestParam(value = "pageSize", required = true) int pageSize, @RequestParam(value = "key", required = false) String key) {
        ResponseData<Pagination> responseData = new ResponseData<>();
        responseData.setCode(0);
        responseData.setMsg("success");
        responseData.setData(portaluserroleBiz.getPageList(pageIndex, pageSize, key));
        return responseData;
    }
}