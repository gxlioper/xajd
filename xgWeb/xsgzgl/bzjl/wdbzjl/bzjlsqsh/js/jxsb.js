//奖项上报


/**
 * 构建表格参数
 * @returns {___anonymous69_187}
 */
function getGridSetting(){
	
	var gridSetting = {
		caption:"参评学生列表 ",
		pager:"pager",
		url:"bzjl_sqsh.do?method=viewJxsbList&type=query",
		multiselect:false
	};

	var colList=[
		   {label:'学号',name:'xh', index: 'xh',width:'15%',key:true,formatter:function(cell,rowObject){
			   return "<a href='javascript:void(0);' onclick=\"showDialog('学生基本信息',700,500,'stu_info_details.do?xh="+cell+"');\" class='name'>"+cell+"</a>";
		   }},
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'}
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
				label:"排名",
				name:"pm"+i,
				index:"pm"+i,
				hidden:(!checked)
		};
		colList.push(xmfsJson);
		colList.push(xmpmJson);
		
	});
	
	colList.push({label:"操作",name:"sfsb",index:"sfsb",noSort:true,formatter:function(cell,rowObject){
		if (cell == "1"){
			return "<font color='blue'>已上报</font>";
		} else {
			return "<a href=\"javascript:toJxsb('"+rowObject["xh"]+"');\" class='name'>上报</a>";
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
 * 打开上报界面 
 * @param xh
 */
function toJxsb(xh){
	var xmdm = jQuery("#xmdm").val();
	showDialog("上报奖项",600,450,"bzjl_sqsh.do?method=toJxsb&xh="+xh+"&xmdm="+xmdm);
}


/**
 * 初始化表格
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
 * 查询
 */
function searchRs(){
	var map = getSuperSearch();
	var xmdm = jQuery("#xmdm").val();
	map["xmdm"] = xmdm;
	
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 重新选择上报项目
 */
function resetPjxm(){
	showDialog("选择上报奖项",300,150,"bzjl_sqsh.do?method=selectSbjx",{max:false,min:false});
}


/**
 * 保存上报
 * @returns {Boolean}
 */
function saveJxsb(){
	if (jQuery("#sqly").val() == ""){
		showAlert("请将必填项填写完整！");
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