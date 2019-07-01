//�����ϱ�


/**
 * ����������
 * @returns {___anonymous69_187}
 */
function getGridSetting(){
	
	var gridSetting = {
		caption:"����ѧ���б� ",
		pager:"pager",
		url:"bzjl_sqsh.do?method=viewJxsbList&type=query",
		multiselect:false
	};

	var colList=[
		   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',key:true,formatter:function(cell,rowObject){
			   return "<a href='javascript:void(0);' onclick=\"showDialog('ѧ��������Ϣ',700,500,'stu_info_details.do?xh="+cell+"');\" class='name'>"+cell+"</a>";
		   }},
		   {label:'����',name:'xm', index: 'xm',width:'10%'},
		   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'}
		];	
	
	
	var zcxm = jQuery("[name=zcxm]");
	jQuery.each(zcxm,function(i,n){
		
		var checked = jQuery(n).prop("checked");
		
		var xmfsJson = {
				label:jQuery(n).attr("xmmc"),
				name:"fs"+i,
				index:"fs"+i,
				hidden:(!checked)
		};
		var xmpmJson = {
				label:"����",
				name:"pm"+i,
				index:"pm"+i,
				hidden:(!checked)
		};
		colList.push(xmfsJson);
		colList.push(xmpmJson);
		
	});
	
	colList.push({label:"����",name:"sfsb",index:"sfsb",noSort:true,formatter:function(cell,rowObject){
		if (cell == "1"){
			return "<font color='blue'>���ϱ�</font>";
		} else {
			return "<a href=\"javascript:toJxsb('"+rowObject["xh"]+"');\" class='name'>�ϱ�</a>";
		}
	}});
	
	gridSetting["colList"] = colList;
	
	if (zcxm.size() > 0){
		gridSetting["sortname"] ="pm0";
		gridSetting["sortorder"] ="asc";
	}
	
	return gridSetting;
}


/**
 * ���ϱ����� 
 * @param xh
 */
function toJxsb(xh){
	var xmdm = jQuery("#xmdm").val();
	showDialog("�ϱ�����",600,450,"bzjl_sqsh.do?method=toJxsb&xh="+xh+"&xmdm="+xmdm);
}


/**
 * ��ʼ�����
 * @param xmdm
 */
function initGrid(xmdm){
	
	jQuery("#xmdm").val(xmdm);
	
	var params = {xmdm:xmdm};
	var gridSetting = getGridSetting();
	gridSetting["params"] = params;
	
	var rows = jQuery("#dataTable tr");
	
	if (rows.size() > 0){
		var map = getSuperSearch();
		map["xmdm"] = xmdm;
		jQuery("#dataTable").reloadGrid(map);
	} else {
		jQuery("#dataTable").initGrid(gridSetting);
	}
	
}

/**
 * ��ѯ
 */
function searchRs(){
	var map = getSuperSearch();
	var xmdm = jQuery("#xmdm").val();
	map["xmdm"] = xmdm;
	
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����ѡ���ϱ���Ŀ
 */
function resetPjxm(){
	showDialog("ѡ���ϱ�����",300,150,"bzjl_sqsh.do?method=selectSbjx",{max:false,min:false});
}


/**
 * �����ϱ�
 * @returns {Boolean}
 */
function saveJxsb(){
	if (jQuery("#sqly").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	
	var url = "bzjl_sqsh.do?method=saveJxsb";
	ajaxSubFormWithFun("sqshForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}