function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function dcmcLink(cellValue, rowObject) {
	var jgid = rowObject["jgid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + jgid + "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(jgid) {
	var url ="dtjs_dxbmgl_dxpxjgCk.do?&jgid=" + jgid;
	var title = "培训结果信息";
	showDialog(title, 800, 450, url);
}
//申请
function add() {
		var url ="dtjs_dxbmgl_dxpxjgZj.do";
		var title = "增加培训结果信息";
		showDialog(title, 800, 460, url);
		jQuery("#dataTable").reloadGrid();
}
function formatStr(str){
	if(str==""||null==str){
		return "-1";
	}
	return str;
}
function save(checkId){
	if(!check(checkId)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	lock();
 	jQuery("#form").ajaxSubmit({
		url:'dtjs_dxbmgl_dxpxjgZj.do',
		type:"post",
		dataType:"json",
		data:{'type':'save'},
		success:function(data){
			if(data||data=='true'){
				showAlertDivLayer("保存成功!");
	 			var api = frameElement.api;
	 			api.opener.reload();
				api.close();
	    	}else{
	    		showAlertDivLayer("保存失败!");
		 		unlock();
	    	}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
function saveXg(){
	lock();
 	jQuery("#form").ajaxSubmit({
		url:'dtjs_dxbmgl_dxpxjgXg.do',
		type:"post",
		dataType:"json",
		data:{'type':'save'},
		success:function(data){
			if(data||data=='true'){
				showAlertDivLayer("保存成功!");
	 			var api = frameElement.api;
	 			api.opener.reload();
				api.close();
	    	}else{
	    		showAlertDivLayer("保存失败!");
		 		unlock();
	    	}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
function checkother(){
	var is=false;
	jQuery("li.current").each(function(){
		//存在数据
		is=true;
	});
	return is;
}
function checkNum(){
	var kssj=jQuery("#kssj").val();
	var str=kssj.substring(0,1);//截取第一位
	if(parseInt(str)<=0&&kssj.length>1){
		return true;
	}
	var jssj=jQuery("#jssj").val();
	str=jssj.substring(0,1);//截取第一位
	if(parseInt(str)<=0&&jssj.length>1){
		return true;
	}
	return false; 
}
//删除
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var ids='';
		var sjyls=0;
		var gxts=rows.length;
		for(var i=0;i<gxts;i++){
			if(rows[i]["sjly"]=='0'){
				ids+=rows[i]["jgid"];
			}else{
				sjyls++;
			}
		}
		if(sjyls==gxts){//所选都不能删除
			showAlertDivLayer("勾选项目中均为结果库信息,不能删除！");
			return false;
		}else if(sjyls>0&&sjyls<gxts){
			showConfirmDivLayer("勾选项目中包含结果库信息,确定只删除不是结果库信息的记录吗？", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxjgSc.do", {values : ids}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data+"&nbsp;</font>条数据";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
			});
		}else{
			showConfirmDivLayer("您确定要删除选择的记录吗？", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxjgSc.do", {values : ids}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data+"&nbsp;</font>条数据";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				}
			});
		}
	}
}
//评分
function pf(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {		
		var sjly=rows[0]["sjly"]
         if(sjly!="0"){//为结果库数据
        	 showAlertDivLayer("结果库信息,无法修改！");
        	 return false;
         }
		showDialog("评分",800, 450,'dtjs_dxbmgl_dxpxjgXg.do?jgid='+rows[0]['jgid']);
	}
}
//自定义导出 功能
function exportConfig() {
	var xxdm=jQuery("#xxdm").val();
	if("13871"==xxdm){
		customExport("dtxxjg_13871.do",exportData);
	}else{
		customExport(action,exportData);
	}
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = action+"?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function edit(obj,jdmc,jddm){
	jQuery(obj).parent().find("span[name='sj']").attr("class","edit");
	var grxj=jQuery(obj).parents("li").find("input[name='grxj']").val();
	var zd5=jQuery(obj).parents("li").find("input[name='zd5']").val();
	var kssj=jQuery(obj).parent().find("span[name='sj']").text();
	var jysj = jQuery(obj).parents("li").find("input[name='zd1']").val();
	var cjlr = jQuery(obj).parents("li").find("input[name='zd2']").val();
	var zd3 = jQuery(obj).parents("li").find("input[name='zd3']").val();
	var zd8 = jQuery(obj).parents("li").find("input[name='zd8']").val();
	var zd9 = jQuery(obj).parents("li").find("input[name='zd9']").val();
	var zd10 = jQuery(obj).parents("li").find("input[name='zd10']").val();
	var url="dtxxjg.do?method=editorView&type=edit&jdmc="+jdmc+"&jddm="+jddm+"&kssj="+kssj+"&grxj="+grxj+"&zd5="+zd5+"&zd1="+jysj+"&zd2="+cjlr
	        +"&zd8="+zd8+"&zd9="+zd9+"&zd10="+zd10+"&zd3="+zd3;
	showDialog("编辑", 600, 320, url);
}
function show(obj,jdmc,jddm){
	jQuery(obj).parent().find("span[name='sj']").attr("class","edit");
	var grxj=jQuery(obj).parents("li").find("input[name='grxj']").val();
	var zd5=jQuery(obj).parents("li").find("input[name='zd5']").val();
	var kssj=jQuery(obj).parent().find("span[name='sj']").text();
	var jysj = jQuery(obj).parents("li").find("input[name='zd1']").val();
	var cjlr = jQuery(obj).parents("li").find("input[name='zd2']").val();
	var zd3 = jQuery(obj).parents("li").find("input[name='zd3']").val();
	var zd8 = jQuery(obj).parents("li").find("input[name='zd8']").val();
	var zd9 = jQuery(obj).parents("li").find("input[name='zd9']").val();
	var zd10 = jQuery(obj).parents("li").find("input[name='zd10']").val();
	var url="dtxxjg.do?method=editorView&type=view&jdmc="+jdmc+"&jddm="+jddm+"&kssj="+kssj+"&grxj="+grxj+"&zd5="+zd5+"&zd1="+jysj+"&zd2="+cjlr
	+"&zd8="+zd8+"&zd9="+zd9+"&zd10="+zd10+"&zd3="+zd3;
	showDialog("查看", 600, 350, url);
}
function autoChange(){
	jQuery(".Join_party ul").css("float","right"); 
	jQuery(".Join_party ul .clearall").nextAll("li").css("float","right"); 

	var nowDate=new Date();
	var currentDate=nowDate.getFullYear()+"-"+nowDate.getMonth()+"-"+nowDate.getDate();
	jQuery("span[name='sj']").each(function(){
		var kssj=jQuery(this).text();
		var grxj=jQuery(this).parents("li").find("input[name='grxj']").val();
		var zd5=jQuery(this).parents("li").find("input[name='zd5']").val();
		//如果存在对应信息则更改显示样式
		if((null!=kssj&&""!=kssj)||(null!=grxj&&grxj!="")){
			jQuery(this).parents("li").attr("class","current");
		}else{
			jQuery(this).parents("li").attr("class","dtxxglnewColorWZDX");
		}
		//如果数据来源是学生申请则不可以修改
		var sjly=jQuery(this).parents("li").find("input[name='sjly']").val();
		if(sjly=="1"){//为学生申请
			var jdmc=jQuery(this).parents("li").find("input[name='jdmc']").val();
			var jddm=jQuery(this).parents("li").find("input[name='jddm']").val();
			jQuery(this).prevAll("a").children("i.i2").attr("class","i1");
			jQuery(this).prevAll("a").removeAttr('onclick').click(function(){
				show(this,jdmc,jddm);
			});
		}
/*		//设置是否可以编辑
		var ksqkssj=jQuery(this).parents("li").find("input[name='ksqkssj']").val();
		var ksqjssj=jQuery(this).parents("li").find("input[name='ksqjssj']").val();
		//当前时间小于于可申请开始时间或者大于可申请结束时间
		//则不可编辑，只可查看
		if(compareDate(currentDate,ksqkssj)==2||compareDate(currentDate,ksqjssj)==1){
			jQuery(this).prevAll("a>li").attr("class","i1");
		}*/
		//没有个人小结则不能查看
		if(grxj==null||grxj==""){
			jQuery(this).prevAll("a").children(".i1").remove();
		}
	});
	//更新各个阶段显示状态
	updateStyle();
}
function updateStyle(){
	var mm = "0"
	jQuery(".Join_party ul li.current").each(function(i){
		var jddm  = jQuery(this).find("input[name='jddm']").val();
		if(jddm>mm){
			mm = jddm;
		}
	});
	jQuery(".Join_party ul li").each(function(){
		var checkJddm=jQuery(this).find("input[name='jddm']").val();
		if(checkJddm<mm){
			
			jQuery(this).attr("class","current");
		}
	});
}
//计算总分
function jszf(){
	var pxqc=jQuery('#pxqc').val();
	if(pxqc!=null&&pxqc!=''){
		var kqcjbfb=jQuery('#kqcjbfb').val()/100;
		var sjcjbfb=jQuery('#sjcjbfb').val()/100;
		var bjcjbfb=jQuery('#bjcjbfb').val()/100;
		var kscjbfb=jQuery('#kscjbfb').val()/100;
		var kqcj=jQuery('#kqcj').val()==''?0:jQuery('#kqcj').val();
		var sjcj=jQuery('#sjcj').val()==''?0:jQuery('#sjcj').val();
		var bjcj=jQuery('#bjcj').val()==''?0:jQuery('#bjcj').val();
		var kscj=jQuery('#kscj').val()==''?0:jQuery('#kscj').val();
		jQuery('#zpcj').val(kqcj*kqcjbfb+sjcj*sjcjbfb+bjcj*bjcjbfb+kscj*kscjbfb);
	}
}