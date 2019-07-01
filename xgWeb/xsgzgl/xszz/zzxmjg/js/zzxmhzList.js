
var gridSetting = {
	caption:"资助结果列表",
	pager:"pager",
	url:"xszz_zzxmjg.do?method=zzxmhzList&type=query",
	colList:[
	   {label:'学年',name:'xn', index: 'xn',width:'6%'},
	   {label:'xq',name:'xq', index: 'xq',width:'7%',hidden:true},
	   {label:'学期',name:'xqmc', index: 'xqmc',width:'7%'},
	   {label:'项目类别',name:'lbmc', index: 'lbmc',width:'6%'},
	   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'13%'},
	   {label:'lbdm',name:'lbdm', index: 'lbdm',width:'13%',hidden:true},
	   {label:'获奖人数',name:'hjrs', index: 'hjrs',width:'19%',formatter:rsLink}
	],
	sortname: "xn,xq",
 	sortorder: "desc"
	};


jQuery(function(){
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function rsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='zzxmView(\""+rowObject["xn"]+"\",\""+rowObject["xq"]+"\",\""+rowObject["lbdm"]+"\",\""+rowObject["xmmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
}

function zzxmView(xn,xq,lbdm,xmmc){
	var url = 'xszz_zzxmjg.do?method=zzxmhzView&xn='+xn+'&xq='+xq+'&lbdm='+lbdm+'&xmmc='+xmmc;
	document.forms[0].action=url;
	document.forms[0].submit();
}


//打印excel
function getExcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	 if (rows.length ==0){
		showAlertDivLayer("请至少选择一条记录！");
		return false;
	 } else if (rows.length > 0){
		 if(rows.length>1){
	    	 for(var i=0;i<rows.length-1;i++){
	    		 if(rows[i+1]["xn"]!=rows[i]["xn"]&&rows[i+1]["xq"]!=rows[i]["xq"]){
	    			 showAlertDivLayer("请选择相同学年学期的资助项目！");
	    			 return false;
	    		 }
	    		 if(rows[i+1]["xmmc"]!=rows[i]["xmmc"]){
	    			 showAlertDivLayer("请选择相同名称的资助项目！");
	    			 return false;
	    		 }
	    	 }
		 }
		var rows=encodeURIComponent(encodeURIComponent(JSON.stringify(rows)));
    	var url="xszz_zzxmjg.do?method=downloadMultiExcel";
    	url += "&rows="+rows;
 		window.open(url);
     }
}

//打印excel（学院）
function getExcelByXy(){
    var rows = jQuery("#dataTable").getSeletRow();
    var xyArr = getClickXy();
    if (rows.length ==0){
        showAlertDivLayer("请至少选择一条记录！");
        return false;
    }
    if (xyArr.length ==0){
        showAlertDivLayer("请至少选择一个学院！");
        return false;
    }
    if (rows.length > 0){
        if(rows.length>1){
            for(var i=0;i<rows.length-1;i++){
                if(rows[i+1]["xn"]!=rows[i]["xn"]&&rows[i+1]["xq"]!=rows[i]["xq"]){
                    showAlertDivLayer("请选择相同学年学期的资助项目！");
                    return false;
                }
                if(rows[i+1]["xmmc"]!=rows[i]["xmmc"]){
                    showAlertDivLayer("请选择相同名称的资助项目！");
                    return false;
                }
            }
        }

        var rows=encodeURIComponent(encodeURIComponent(JSON.stringify(rows)));
        var url="xszz_zzxmjg.do?method=downloadMultiExcelByXy";
        url += "&rows="+rows+"&xydms="+xyArr.toString();
        window.open(url);
    }
}

//山东畜牧兽医职业学院（社会助学金汇总表）
function getshzxjHzexcel(){
	var xmlb = '';
	var xn = '';
	var xq = ''
	jQuery("a[name='tj_zzxmlb'].selectedValue").each(function(){
		xmlb = xmlb+jQuery(this).text()+',';
	})
	jQuery("a[name='tj_xn'].selectedValue").each(function(){
		xn = xn+jQuery(this).text()+',';
	})
	
	jQuery("a[name='tj_xq'].selectedValue").each(function(){
		xq = xq+jQuery(this).text()+',';
	})
	var value = xmlb.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("请选择一个类别，请确认！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var url="xszz_zzxmjg.do?method=getSdxm_shzxjhzexcel&value="+xmlb+"&xn="+xn+"&xq="+xq;
	window.open(url);
}

/**
 * 上海体育学院资助项目汇总表
 * 必须至少选中一条记录才允许做导出，否则直接返回弹出提示信息
 * 采用构造input的方式,利用表单提交参数，以防乱码和调高传输效率
 * @return
 */
function getZzxmHz_10277(){
	var rows = jQuery("#dataTable").getSeletRow();
	//首先清空构造表单
	jQuery("#shtyhzdcdiv").empty();
	if(rows.length == 0){
		showAlertDivLayer("请至少勾选一条记录！");
		return false;
	}
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		var xn = (row['xn'] == 'null' || row['xn'] == null) ? "":row['xn'];
		var xq = (row['xq'] == 'null' || row['xq'] == null) ? "":row['xq'];
		var lbdm = (row['lbdm'] == 'null' || row['lbdm'] == null) ? "":row['lbdm'];
		var paras = xn+xq+row['xmmc']+lbdm;
		var html = "<input value='"+paras+"' name='params' type='hidden'/>";
		jQuery("#shtyhzdcdiv").append(html);
	}
	var url = "xszz_zzxmjg.do?method=exportShty_hzdc";
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
