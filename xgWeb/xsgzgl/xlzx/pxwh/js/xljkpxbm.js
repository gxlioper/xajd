function pxztLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='pxztView(\""+row["pxid"]+"\")'>"+cellValue+"</a>";
}

function pxztView(pxid){
	showDialog("�鿴��ѵ����",600, 400,"xlzx_pxwh.do?method=pxwhView&pxid="+pxid);
}

function bmcz(cellValue,row){
	if(row["sfkfbm"]=="0"){
		return "���ɱ���";
	}
	return "<a href='javascript:void(0);' class='name' onClick='bm(\""+row["pxid"]+"\",\""+1+"\")'>����</a>";
}

function qxbmcz(cellValue,row){
	if("1"==row["bmyg"]){
		return "ȡ������";
	}
	return "<a href='javascript:void(0);' class='name' onClick='bm(\""+row["pxid"]+"\",\""+0+"\")'>ȡ������</a>";
}

function bm(pxid,bmtype){
	var xh=jQuery("#userName").val();
	if("1"==bmtype){
		var message="��ȷ��ҪԤԼ��������ѵ��";
	}else{
		var message="��ȷ��Ҫȡ����������ѵ��";
	}
	showConfirmDivLayer(message,{"okFun":function(){
		jQuery.ajax({
			url:"xlzx_pxwh.do?method=bmcz",
			data:{xh:xh,bmtype:bmtype,pxid:pxid},
			type:"post",
			dataType:"json",
			success:function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						searchRs();
					}
				});
			}
		});	
	}});
}



//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zjly_ylbx.do?method=exportData&dcclbh=" + DCCLBH+"&cxblb="+jQuery("#cxblb").val();//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function searchRs(){
	var map = getSuperSearch();	
	var sfybm = jQuery("#sfybm").val();
	map["sfybm"] = sfybm;
	if("1" ==sfybm){
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}
}

// ҳǩ�л�
function selectTab(obj,sfybm){
	jQuery("#sfybm").val(sfybm);
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}	
