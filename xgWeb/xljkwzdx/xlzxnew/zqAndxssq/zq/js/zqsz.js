/**
	 * �������Ѵ�����ǩ�л�
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
	
	//�鿴���ճ�
	function zqLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='zqView(\""
				+ rowObject["zbid"] + "\");'>" + cellValue
				+ "</a>";
	}

	function zqView(zbid) {
		showDialog("�༶�ܱ��ճ�", 580, 420, "xlzxnew_zqrcgl.do?method=ckZqsz&zbid="
				+ zbid );
	}
	
	//�鿴���ճ�
	function yzqLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='yzqView(\""
				+ rowObject["ybid"] + "\");'>" + cellValue
				+ "</a>";
	}

	function yzqView(ybid) {
		showDialog("���ܱ��ճ�", 580, 420, "xlzxnew_zqrcgl.do?method=ckYzqsz&ybid="
				+ ybid );
	}