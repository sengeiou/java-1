package com.htstar.ovms.admin.api.dto;

import com.htstar.ovms.admin.api.vo.CarInfoVO;
import com.htstar.ovms.admin.api.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ovms
 * @date 2017年11月9日23:33:45
 */
@Data
@ApiModel(value = "树形节点")
public class TreeNode {
	@ApiModelProperty(value = "当前节点id")
	protected int id;
	@ApiModelProperty(value = "父节点id")
	protected int parentId;
	@ApiModelProperty(value = "子节点列表")
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	@ApiModelProperty(value = "企业树中的车辆信息")
	protected List<CarInfoVO> carInfoVOList;

	@ApiModelProperty(value = "部门员工")
	List<UserVO> staff;

	public void add(TreeNode node) {
		children.add(node);
	}
}