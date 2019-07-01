//表名，学院ID，专业ID，年级ID
function initPjZyList(tableName, xyid, zyid, njid) {
	// dataLoad(true);
	var xydm = "";
	var query = "";
	var userName = "";
	var userType = "";
	var nj = "";
	var isFdy = "";
	var isBzr = "";
	if ($("isFdy")) {
		isFdy = $("isFdy").value;
	}
	if ($("isBzr")) {
		isBzr = $("isBzr").value;
	}
	if ($(xyid)) {
		xydm = $(xyid).value;
	}
	if ($("userName")) {
		userName = $("userName").value;
	}
	if ($("userType")) {
		userType = $("userType").value;
	}
	if ("bzr" == userType) {
		isFdy = "true";
	}
	if ($(njid) && $(njid) != null) {
		nj = $(njid).value;
	}
				//if(xydm == null || xydm == ""){
				//	xydm = "%";
				//} else{
				//    query += " and xydm='"+xydm+"' ";
					//xydm = "%" + xydm +"%";
				//}
				//if(nj!=""){
				//    query += " and nj='"+nj+"' ";
				//}
				
				//query = xydm;
	ryqdDWR.getPjZyList(tableName, xydm, nj, userName, isFdy, isBzr, function initTjList(data) {
		if (data != null && data != "" && typeof data == "object") {
			var objId = zyid;
			if ($(objId) && $(objId).tagName.toLowerCase() == "select") {
				DWRUtil.removeAllOptions(objId);
				DWRUtil.addOptions(objId, data, "dm", "mc");
				$(objId).options[0].value = "";
				if (objId + "V") {
					if ($(objId + "V").value != "" && $(objId + "V").value != null) {
						for (var i = 0; i < $(objId).options.length; i++) {
							if ($(objId).options[i].value == $(objId + "V").value) {
								$(objId).options[i].selected = true;
								return true;
							}
						}
					}
				}
			}
		} else {
						//showMsgWin("有错误出现：远程数据读取失败！");
		}
	});
}

//表名，学院ID，专业ID，班级ID，年级ID
function initPjBjList(tableName, xyid, zyid, bjid, njid) {
    //dataLoad(true);
	var xydm = "";
	var zydm = "";
	var nj = "";
	var query = "";
	var userName = "";
	var userType = "";
	var isFdy = "false";
	var isBzr = "false";
	if ($("userName")) {
		userName = $("userName").value;
	}
	if ($(xyid)) {
		xydm = $(xyid).value;
	}
	if ($(zyid)) {
		zydm = $(zyid).value;
	}
	if ($(njid)) {
		nj = $(njid).value;
	}
	if ($("isFdy")) {
		isFdy = $("isFdy").value;
	}
	if ($("userType") && $("userType") != null) {
		userType = $("userType").value;
	}
	if ("bzr" == userType) {
		isFdy = "true";
	}
	if ($("isBzr")) {
		isBzr = $("isBzr").value;
	}
	if (xydm == null || xydm == "") {
		xydm = "%";
	} else {
		xydm = "%" + xydm + "%";
	}
	if (zydm == null || zydm == "") {
		zydm = "%";
	} else {
		zydm = "%" + zydm + "%";
	}
	if (nj == null || nj == "") {
		nj = "%";
	} else {
		nj = "%" + nj + "%";
	}
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;
	ryqdDWR.getPjBjList(tableName, query, userName, isFdy, isBzr, function initTjList(data) {
		if (data != null && typeof data == "object") {
			var objId = bjid;
			if ($(objId) && $(objId).tagName.toLowerCase() == "select") {
				DWRUtil.removeAllOptions(objId);
				DWRUtil.addOptions(objId, data, "dm", "mc");
				if ($("ysbjdm")) {//原始班级
					DWRUtil.removeAllOptions("ysbjdm");
					DWRUtil.addOptions("ysbjdm", data, "dm", "mc");
					$("ysbjdm").options[0].value = "";
				}
				if ($("mkbjdm")) {//模块班级
					DWRUtil.removeAllOptions("mkbjdm");
					DWRUtil.addOptions("mkbjdm", data, "dm", "mc");
					$("mkbjdm").options[0].value = "";
				}
				$(objId).options[0].value = "";
				if (objId + "V") {
					if ($(objId + "V").value != "" && $(objId + "V").value != null) {
						for (var i = 0; i < $(objId).options.length; i++) {
							if ($(objId).options[i].value == $(objId + "V").value) {
								$(objId).options[i].selected = true;
								return true;
							}
						}
					}
				}
					//dataLoad(false);
			}
		} else {
			showMsgWin("\u6709\u9519\u8bef\u51fa\u73b0\uff1a\u8fdc\u7a0b\u6570\u636e\u8bfb\u53d6\u5931\u8d25\uff01");
		}
	});
}

