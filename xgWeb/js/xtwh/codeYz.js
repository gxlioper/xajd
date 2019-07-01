//验证代码格式
function codeYz(){	

	var inputV = document.getElementsByName("newCode");
	var table = document.forms[0].tName.value;
	//浙江传媒卫生检查等极
	if("zjcm_wsjcdjb" == table){
		var dm = inputV[0].value;//代码
		var mc = inputV[1].value;//等级
		var xxf = inputV[2].value;//下限分
		var sxf = inputV[3].value;//上限分
		if(sxf !="" && xxf != ""){
			if(dm.length > 10){
				alert("代码最多只能录入10个字符！");
				return false;
			}
			if(mc.length > 10){
				alert("名称最多只能录入10个字！");
				return false;
			}
			if(xxf.length > 3){
				alert("下限分最多只能录入3个字符！");
				return false;
			}
			if(sxf.length > 3){
				alert("上限分最多只能录入3个字符！");
				return false;
			}
			if(isNumber(xxf) == false){
				alert("下限分必须是数字格式！");
				return false;
			}
			if(isNumber(sxf) == false){
				alert("上限分必须是数字格式！");
				return false;
			}
			if(parseFloat(xxf) > parseFloat(sxf)){
				alert("下限分不能大于上限分！");
				return false;
			}
		}
	}else if("xszz_com_gjzxj1dmb" == table){
	//通用国助等极验证
		var je = inputV[2].value
		if(je !=""){
			if(isNumber(je) == false){
				alert("金额必须是数字格式！");
				return false;
			}
			if(je.length > 20){
				alert("金额的长度不能超过20！");
				return false;
			}
		}
	
	}else if("pxxmdmb" == table){
	//通用培训项目验证
		var dm = inputV[0].value;
		if(dm !=""){
			if(isNumber(dm) == false){
				alert("项目代码必须是数字格式！");
				return false;
			}
			if(dm.length > 5){
				alert("项目代码的长度不能超过5！");
				return false;
			}
		}
	}else if("dtjs_xsccb" == table){
	//通用_党团建设_学生层次表
		var xsccdm = inputV[0].value;//代码
		var xsccmc = inputV[1].value;//名称
		if(xsccdm.length > 20){
			alert("代码最多只能录入20个字符！");
			return false;
		}
		if(xsccmc.length > 25){
			alert("名称最多只能录入25个字！");
			return false;
		}
	}else if("jxjxdmb" == table){
	//评奖评优-军训评奖
		var jxdm = inputV[0].value;//代码
		var jxmc = inputV[1].value;//名称
		if(jxdm.length > 10){
			alert("代码最多只能录入10个字符！");
			return false;
		}
		if(jxmc.length > 25){
			alert("名称最多只能录入25个字！");
			return false;
		}
	}else if("dwjlfsdmb" == table){
	//对外交流-对外交流方式
		var dm = inputV[0].value;//代码
		var mc = inputV[1].value;//名称
		if(dm.length > 5){
			alert("代码最多只能录入5个字符！");
			return false;
		}
		if(mc.length > 10){
			alert("名称最多只能录入10个字！");
			return false;
		}
	}else if("jxjdmb" == table){
	//评奖评优-奖学金评优
		var dm = inputV[0].value;//代码
		var je = inputV[4].value;//金额
		var jb = inputV[3].value;//级别
		if(dm.length > 10){
			alert("代码最多只能录入10个字符！");
			return false;
		}
		if(je.length > 5){
			alert("金额最多只能录入5个字！");
			return false;
		}
		if(jb !=""){
			if(isNumber(jb) == false){
				alert("奖学金级别必须是数字格式！");
				return false;
			}
			if(jb.length > 2){
				alert("奖学金级别的长度不能超过2！");
				return false;
			}
		}
	}else if("dtjs_djdmb" == table){
	//党团建设-等级代码表
		var jb = inputV[2].value;//级别
		if(jb.length > 10){
			alert("级别最多只能录入10个字符！");
			return false;
		}
	}else if("rychdmb" == table){
	//荣誉称号代码
		var dm = inputV[0].value;//代码
		var mc = inputV[1].value;//名称
		if(dm.length > 10){
			alert("代码最多只能录入10个字符！");
			return false;
		}
		if(mc.length > 15){
			alert("名称最多只能录入15个字！");
			return false;
		}
	}else if("dwjlxmdmb" == table){
	//对外交流代码表
		var nd = inputV[4].value;//年度
		if(isNumber(nd) == false){
				alert("年度必须是数字格式！");
				return false;
			}
		if(nd.length > 4){
			alert("年度最多只能录入4个字符！");
			return false;
		}
	}else if("dwjlfldmb" == table){
	//对外交流类别表
		var dm = inputV[0].value;//代码
		var mc = inputV[1].value;//名称
		if(dm.length > 5){
			alert("代码最多只能录入5个字符！");
			return false;
		}
		if(mc.length > 10){
			alert("名称最多只能录入10个字！");
			return false;
		}
	}else if("dtjs_djdmb" == table){
	//党团建设（等级代码表）
		var dm = inputV[0].value;//代码
		var mc = inputV[1].value;//名称
		var jb = inputV[2].value;//级别
		if(dm.length > 10){
			alert("代码最多只能录入10个字符！");
			return false;
		}
		if(mc.length > 20){
			alert("名称最多只能录入20个字！");
			return false;
		}
		if(jb.length > 5){
			alert("级别最多只能录入5个字！");
			return false;
		}
	}else if("gwxzdmb" == table){
	//岗位性质代码表
		var dm = inputV[0].value;//代码
		var mc = inputV[1].value;//名称
		if(dm.length > 8){
			alert("岗位性质代码最多只能录入8个字符！");
			return false;
		}
		if(mc.length > 25){
			alert("岗位性质名称最多只能录入25个字符！");
			return false;
		}
	}else if("yrdwdmb" == table){
	//用人单位代码表
		var dm = inputV[0].value;//代码
		var yrdwmc = inputV[1].value;//用人单位名称
		var lxr = inputV[2].value;//联系人
		var lxdh = inputV[3].value;//联系电话
		var dlm = inputV[7].value;//登录名
		var yrdwdz = inputV[8].value;//用人单位地址 
		if(dm.length > 8){
			alert("代码最多只能录入8个字符！");
			return false;
		}
		if(yrdwmc.length>25){
			alert("用人单位名称最多只能录入25个字符！");
			return false;
		}
		if(lxr.length>10){
			alert("联系人最多只能录入10个字符！");
			return false;
		}
		if(lxdh.length>50){
			alert("联系电话最多只能录入50个字符！");
		}
		if(dlm.length > 10){
			alert("登陆名最多只能录入10个字！");
			return false;
		}
		if(yrdwdz.length>100){
			alert("用人单位地址最长只能录入100个字");
			return false;
		}
		if(isNumber(lxdh) == false){
			alert("联系电话必须是数字格式！");
			return false;
		}
	}else if("qgzx_jcfsdmb" == table){
	//勤工助学计酬方式代码表
		var bz = inputV[2].value;//计酬标准
		if(isNumber(bz) == false){
			alert("计酬标准必须是数字格式！");
			return false;
		}
		if(bz.length > 4){
			alert("计酬标准最多只能录入4个字符！");
			return false;
		}
	}
	return true;
}
