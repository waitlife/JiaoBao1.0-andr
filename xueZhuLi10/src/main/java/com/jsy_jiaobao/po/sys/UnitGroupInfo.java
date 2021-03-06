package com.jsy_jiaobao.po.sys;

import java.io.Serializable;

/**
 * 单位群组
 */
public class UnitGroupInfo implements Serializable{
	private static final long serialVersionUID = -5985967811668864649L;
	private int GroupID;//": 0,
    private String GroupName;//": "基本人员组",
    private int HideSign;//": 0,
    private int UnitID;//": 5150001
    
	public int getGroupID() {
		return GroupID;
	}
	public void setGroupID(int groupID) {
		GroupID = groupID;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public int getHideSign() {
		return HideSign;
	}
	public void setHideSign(int hideSign) {
		HideSign = hideSign;
	}
	public int getUnitID() {
		return UnitID;
	}
	public void setUnitID(int unitID) {
		UnitID = unitID;
	}
}