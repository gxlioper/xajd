/**
	 * 待处理、已处理面签切换
	 * @param obj
	 * @param shzt
	 * @return
	 */
	function selectTab(obj,query_type){
		if("zb" == query_type){
			document.location.href = "xg_xlzxnew_zqrcgl.do";
		}else{
			document.location.href = "xlzxnew_zqrcgl.do?method=getYrcList";
		}
	}
	
	//查看月日程
	function zqLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='zqView(\""
				+ rowObject["zbid"] + "\");'>" + cellValue
				+ "</a>";
	}

	function zqView(zbid) {
		showDialog("班级周报日程", 580, 420, "xlzxnew_zqrcgl.do?method=ckZqsz&zbid="
				+ zbid );
	}
	
	//查看周日程
	function yzqLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='yzqView(\""
				+ rowObject["ybid"] + "\");'>" + cellValue
				+ "</a>";
	}

	function yzqView(ybid) {
		showDialog("月周报日程", 580, 420, "xlzxnew_zqrcgl.do?method=ckYzqsz&ybid="
				+ ybid );
	}