//�������

var shzt='0';
/**
 * ��ȡ�����������
 * @returns {___anonymous54_1173}
 */
function getDclGird(){
	
	var colList = [
	   {label:'�꼶',name:'nj', index: 'nj',width:'14%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'20%'},
	   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
	   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
	   {label:'xn',name:'xn', index: 'xn',hidden:true},
	   {label:'xq',name:'xq', index: 'xq',hidden:true},
	   {label:'bjdm',name:'bjdm', index: 'bjdm',hidden:true},
	   {label:'�༶������',name:'bjzrs', index: 'bjzrs',width:'13%'},
	   {label:'���������',name:'gs', index: 'gs',width:'13%'}
	   
	];
		
	return {
		caption:"����ѧ���б� ",
		pager:"pager",
		url:"bzjl_sqsh.do?method=viewJxplshList&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}


/**
 * ��ʾ���ҳ��
 */
function viewJxsh(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length ==0){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		var xn = rows[0]["xn"];
		var xq = rows[0]["xq"];
		var result = true;
		var bjdms = "";
		for(var i=0;i<ids.length;i++){
			bjdms+=rows[i]["bjdm"]+",";
			if(rows[i]["xn"]!=xn&&rows[i]["xq"]!=xq){
				result = false;
			}
		}
		
		if(bjdms.length>0){
			bjdms = bjdms.substring(0, bjdms.length-1);
		}
		
		if(!result){
			showAlertDivLayer("��ѡ��ѧ��ѧ��һ�µļ�¼��");
			return false;
		}
		//showDialog("�������",700,500,"bzjl_sqsh.do?method=toCheckPjpy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
		refreshForm("bzjl_sqsh.do?method=toCheckPjpy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
	}
	
	
}


/**
 * ��ȡ�Ѵ��������
 * @returns {___anonymous1466_2571}
 */
function getYclGrid(){
	
	var colList = [
	   {label:'�꼶',name:'nj', index: 'nj',width:'14%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'20%'},
	   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
	   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
	   {label:'xn',name:'xn', index: 'xn',hidden:true},
	   {label:'xq',name:'xq', index: 'xq',hidden:true},
	   {label:'bjdm',name:'bjdm', index: 'bjdm',hidden:true},
	   {label:'�༶������',name:'bjzrs', index: 'bjzrs',width:'13%'},
	   {label:'���������',name:'gs', index: 'gs',width:'13%'}
	];
	
	
	return {
		caption:"����ѧ���б� ",
		pager:"pager",
		url:"bzjl_sqsh.do?method=viewJxplshList&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}
		

/**
 * ȡ�����
 */
function cancelAuding(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else {
		jQuery.post("bzjl_sqsh.do?method=runCancel",{sqid:rows[0]["sqid"],shid:rows[0]["shid"]},function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				var yclGrid = getYclGrid();
				jQuery("#dataTable").initGrid(yclGrid);
			}});
		},"json");
	}
	
	
	
}



/**
 * �л��Ѵ���δ����
 * @param obj
 * @param shzt
 */
function chageShzt(obj,shzt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	if (shzt=="0"){
		var dclGrid = getDclGird();
		var map = getSuperSearch();
			map["shzt"] = "0"; 
		dclGrid["params"] = map;
		jQuery("#dataTable").initGrid(dclGrid);
		
		jQuery("#li_qx").hide();
		jQuery("#li_sh").show();
		jQuery("#shzt").val("0");
	} else {
		var yclGrid = getYclGrid();
		var map = getSuperSearch();
			map["shzt"] = "1"; 
		yclGrid["params"] = map;
		jQuery("#dataTable").initGrid(yclGrid);
		
		jQuery("#li_qx").show();
		jQuery("#li_sh").hide();
		jQuery("#shzt").val("1");
	}
}


/**
 * ������������
 * @returns {___anonymous3504_3637}
 */
function getShqkGrid(){
	
	var tjdw = jQuery("#tjdw").val();
	var gridSetting = {
		caption:"�б� ",
		pager:"pager",
		url:"bzjl_sqsh.do?method=viewShqkList&type=query",
		sortname:"dsrs",
		sortorder:"desc",
		radioselect:true
	};
	
	var colList;
	
	if ("bj" == tjdw){
		colList=[
		   {label:'bjdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
		   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xydm',width:'20%'},
		   {label:'�༶',name:'bjmc', index: 'bmdm',width:'20%'},
		   {label:'������',name:'bjrs', index: 'bjrs',width:'10%'},
		   {label:'����/�ϱ�����',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'��������',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'ͨ������',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'��ͨ������',name:'btgrs', index: 'btgrs',width:'15%'}
		];
		
		
	} else if ("njzy" == tjdw){
		colList=[
		   {label:'bmdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
		   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
		   {label:'������',name:'zrs', index: 'zrs',width:'10%'},
		   {label:'����/�ϱ�����',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'��������',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'ͨ������',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'��ͨ������',name:'btgrs', index: 'btgrs',width:'15%'}
		];
	} else {
		colList=[
		   {label:'bmdm',name:'bmdm', index: 'bmdm',key:true,hidden:true},
		   {label:'������',name:'cpzmc', index: 'bmdm',width:'20%'},
		   {label:'������',name:'cpzrs', index: 'cpzrs',width:'10%'},
		   {label:'����/�ϱ�����',name:'sqrs', index: 'sqrs',width:'15%'},
		   {label:'��������',name:'dsrs', index: 'dsrs',width:'10%'},
		   {label:'ͨ������',name:'tgrs', index: 'tgrs',width:'10%'},
		   {label:'��ͨ������',name:'btgrs', index: 'btgrs',width:'15%'}
		];
	}
	
	gridSetting["colList"] = colList;
	return gridSetting;
}



/**
 * ѡ��ͳ�Ʒ�ʽ
 */
function showSeletTjdw(){
	showDialog("ѡ��ͳ�Ʒ�ʽ",300,150,"bzjl_sqsh.do?method=selectTjdw",{max:false,min:false});
}


/**
 * �����ϸҳ��
 */
function showAudingList(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else {
		var tjdw = jQuery("#tjdw").val();
		var shid = jQuery("#shid").val();
		var id = rows[0]["bmdm"];
		
		showDialog("�������",750,500,"bzjl_sqsh.do?method=viewJxshList&tjdw="+tjdw+"&shid="+shid+"&bmdm="+id);
	}
}


/**
 * ���̸���
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		var url = "bzjl_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
		showDialog("���̸���",550,450,url,{max:false,min:false});
	}
}


/**
 * ��ѯ
 */
function searchRs(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ������ͳ�� 
 */
function viewShqk(){
	showDialog("��������������ͳ��",500,400,"bzjl_sqsh.do?method=pjxmShtj");
}




//����
function exportConfig(){
	var DCCLBH='pj_jxsh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='pj_jxsh.do';
	
	setSearchTj();//���ø߼���ѯ����
	
	var url = "bzjl_sqsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}