package com.htstar.ovms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htstar.ovms.admin.api.entity.SysDept;
import com.htstar.ovms.admin.api.entity.SysDeptRelation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ovms
 * @since 2018-02-12
 */
public interface SysDeptRelationService extends IService<SysDeptRelation> {

	/**
	 * 新建部门关系
	 *
	 * @param sysDept 部门
	 */
	void insertDeptRelation(SysDept sysDept);

	/**
	 * 通过ID删除部门关系
	 *
	 * @param id
	 */
	void deleteAllDeptRealtion(Integer id);

	/**
	 * 更新部门关系
	 *
	 * @param relation
	 */
	void updateDeptRealtion(SysDeptRelation relation);
}
