/*奖项审核 */
var shzt = '0';
/**
 * 获取待处理表格参数
 * @return
 */
function getDclGird(){
	var colList = [
	   {label:'key',name:'id', index: 'id',hidden:true,key:true},
	   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:function(cell,rowObject){
		   return "<a href='javascript:void(0);' onclick=\"showDialog('查看申请表',800,500,'xpjpy_xmsq.do?method=xmsqView&id="+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
	   }},
	   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
	   {label:'学院',name:'xymc', index: 'xydm',width:'11%'},
	   {label:'班级',name:'bjmc', index: 'bjdm',width:'11%'},
	   {label:'项目名称',name:'xmmc', index: 'xmdm',width:'10%'},
	   {label:'金额',name:'xmje', index: 'xmje',width:'3%'},
	   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'11%'}
	];
	colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
	colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
	colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
	colList.push({label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'});
	return {
		caption:"申请学生列表 ",
		pager:"pager",
		url:"xpjpy_xmsh.do?method=getXmshListDate&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}

/**
 * 获取已处理表格参数
 * @return
 */
function getYclGrid(){
	var colList = [
	   {label:'key',name:'id', index: 'id',hidden:true,key:true},
	   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:function(cell,rowObject){
		   return "<a href='javascript:void(0);' onclick=\"showDialog('查看申请表',800,500,'xpjpy_xmsq.do?method=xmsqView&id="+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
	   }},
	   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
	   {label:'学院',name:'xymc', index: 'xydm',width:'11%'},
	   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
	   {label:'项目名称',name:'xmmc', index: 'xmdm',width:'10%'},
	   {label:'金额',name:'xmje', index: 'xmje',width:'3%'}
	];
	colList.push({label:'审核时间',name:'shsj', index: 'shsj',width:'11%'});
 	colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
 	colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
 	colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
 	colList.push({label:'xmdm',name:'xmdm', index: 'xmdm',hidden:true});
 	colList.push({label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'});
	return {
		caption:"申请学生列表 ",
		pager:"pager",
		url:"xpjpy_xmsh.do?method=getXmshListDate&type=query",
		colList:colList,
		sortname:"nj",
		sortorder:"asc",
		radioselect:false
	};
}

jQuery(function(){
	jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
		var index = jQuery(this).attr("index");
		var liName = jQuery("#tabUl li.ha").attr("clzt");
		if ("dcl" == liName){
			var gridSetting = getDclGird();
			jQuery("#dataTable").initGrid(gridSetting);
		} else {
			var gridSetting = getYclGrid();
			jQuery("#dataTable").initGrid(gridSetting);
		}
	});
	loadCookie();
	var dclGrid = getDclGird();
	var map = getSuperSearch();
		map["shzt"] = "0";
		dclGrid["params"] = map;
	jQuery("#dataTable").initGrid(dclGrid);
});

/**
 * 查询
 * @return
 */
function searchRs(){
	//检验是否可查询
	var xn_num = jQuery("a[name=a_name_xn]").length;
	
	if(xn_num != "1"){
		alertError("学年条件不可为空，且只能选择一个！");
		return false;
	}
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
	
}



/**
 * 装载cookie
 * @return
 */
function loadCookie(){
	var indexStr = jQuery.cookie("jxshGrid");
	if(indexStr != null && indexStr != undefined){
		var indexArray = indexStr.split("-");
		jQuery.each(indexArray,function(i,n){
			if (n != ""){
				jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
			}
		});
	}
}

/**
 * 设置cookie
 * @return
 */
function setJxshCookie(){
	var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
	var indexStr = "";
	
	jQuery.each(chekedZcxm,function(i,n){
		var index = jQuery(n).attr("index");
		indexStr += index+"-";
	});
	jQuery.cookie("jxshGrid",indexStr, { expires: 7});
}

/**
 * 切换已处理、未处理
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
 * 批量审核
 * @return
 */
function xmshPlsh(){
	var ids = jQuery("#dataTable").getSeletIds();
	showDialog("奖项审核",750,570,"xpjpy_xmsh.do?method=xmshPlsh&id="+ids.toString());
}

/**
 * 批量审核保存
 * @param shzt
 * @param shyj
 * @param xyXmdm
 * @param jsonStr
 * @param id
 * @return
 */
function savePlsh(shzt,shyj,xyXmdm,jsonStr,id){
	var map = jsonStr;
	map["shzt"]=shzt;
	map["shyj"]=shyj;
	map["xyXmdm"]=xyXmdm;
	/*所有id都是申请id*/
	map["id"]=id;
	jQuery.post("xpjpy_xmsh.do?method=savePlsh",map,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			jQuery("#dataTable").reloadGrid();
		}});
	},'json');
}

/**
 * 流程跟踪
 */
function xmshTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

/**
 * 撤销
 * @return
 */
function xmshRevoke(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var xmdm = rows[0]["xmdm"];
		var xh = rows[0]["xh"];
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("xpjpy_xmsh.do?method=cxshnew",{splc:splc,shid:shid,xmdm:xmdm,xh:xh},function(data){
						/*判断是否最后一级撤销(1:最后一级撤销成功）*/
						if("1" == data["cancelFlg"]){
							jQuery.post("xpjpy_xmsh.do?method=cancel",{splcid:splc,shid:shid,xmdm:xmdm},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
				},'json');
			}
		});
	}
}


/**
 * 导出
 */
function xmshExport(){
	var DCCLBH='xpjpy_wdpj_pjsh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='xpjpy_wdpj_pjsh.do';
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_xmsh.do?method=xmshExport&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 下载登记表
 */
function getWord() {
    /*选择的记录*/
    var rows = jQuery("#dataTable").getSeletRow();
    /*多选记录*/
    var ids = jQuery("#dataTable").getSeletIds();
    var len = ids.length;
    /*判断打印*/
    if(0 == len){
        showAlertDivLayer("请选择您要下载的记录！");
        return false;
    }else if(1 == len){
        var url = "xpjpy_xmsq.do?method=getWordForid&id="+rows[0]["id"];
        window.open(url);
    }else{
        var url = "xpjpy_xmsq.do?method=getDjbZip&value="+ids;
        window.open(url);
    }
}
