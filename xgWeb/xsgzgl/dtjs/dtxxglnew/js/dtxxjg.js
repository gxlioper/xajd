var action="dtxxjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function dcmcLink(cellValue, rowObject) {
	var dtxxjgid = rowObject["dtxxjgid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + dtxxjgid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(dtxxjgid) {
	var url = action+"?method=showView&dtxxjgid=" + dtxxjgid;
	var title = "党团申请信息";
	showDialog(title, 800, 450, url);
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "增加党团信息";
		showDialog(title, 800, 460, url);
		jQuery("#dataTable").reloadGrid();
}
function formatStr(str){
	if(str==""||null==str){
		return "-1";
	}
	return str;
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(!checkother()){
		showAlert("学生党团发展进程不能空！");
		return false;
	}
	var jddm="-1";
	var kssj="-1";
	var grxj="-1";
	var zd5="-1";
	var dtxxjgid="-1";
	var zd1 = "-1";
	var zd2 = "-1";
	var zd3 = "-1";
	var zd8 = "-1";
	var zd9 = "-1";
	var zd10 = "-1";
	jQuery(".finish").each(function(){
		//alert(jQuery(this).parents("li").html());
		//获取所有修改过的阶段代码
		jddm+=",";
		jddm+=formatStr(jQuery(this).parents("li").find("input[name='jddm']").val());
		//获取所有修改过的个人信息
		grxj+=",";
		grxj+=formatStr(jQuery(this).parents("li").find("input[name='grxj']").val());
		//附件
		zd5+=",";
		zd5+=formatStr(jQuery(this).parents("li").find("input[name='zd5']").val());
		//获取所有修改过的开始时间
		kssj+=",";
		kssj+=formatStr(jQuery(this).text());
		//获取党团结业时间
		zd1+=",";
		zd1+=formatStr(jQuery(this).parents("li").find("input[name='zd1']").val());
		//获取党团结业成绩
		zd2+=",";
		zd2+=formatStr(jQuery(this).parents("li").find("input[name='zd2']").val());
		
		zd3+=",";
		zd3+=formatStr(jQuery(this).parents("li").find("input[name='zd3']").val());
		
		zd8+=",";
		zd8+=formatStr(jQuery(this).parents("li").find("input[name='zd8']").val());
		
		zd9+=",";
		zd9+=formatStr(jQuery(this).parents("li").find("input[name='zd9']").val());
		
		zd10+=",";
		zd10+=formatStr(jQuery(this).parents("li").find("input[name='zd10']").val());
		
		//获取所有修改过的党团结果id
		dtxxjgid+=",";
		dtxxjgid+=formatStr(jQuery(this).parents("li").find("input[name='dtxxjgid']").val());
	});
	var data={'jddmstr':jddm,'kssjstr':kssj,'grxjstr':grxj,'zd5str':zd5,'dtxxjgidstr':dtxxjgid,'zd1str':zd1,'zd2str':zd2,'zd3str':zd3,'zd8str':zd8,'zd9str':zd9,'zd10str':zd10};
	//alert(jddm+":"+kssj+":"+grxj+":"+dtxxjgid);
	//return false;
	lock();
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		data:data,
		success:function(data){
	 		 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
	 		unlock();
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
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var sjly=rows[0]["sjly"];
		if(sjly=="1"){
			showAlertDivLayer("该条党团信息已<font color='red'>走完审核流</font>,不能修改！");
			return false;
		}
		var url = action+'?method=update&dtxxjgid=' + rows[0]["dtxxjgid"];
		var title = "修改党团信息";
		showDialog(title, 800, 450, url);
		jQuery("#dataTable").reloadGrid();
	}
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
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="学生<font color='red'>["+data["nodel"]+"]</font>";
						mes+="已走完审核流，不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		var sjly=rows[0]["sjly"]
         if(sjly!="1"){//为结果库数据
        	 showAlertDivLayer("结果库信息,无相关审批流程！");
        	 return false;
         }
		showDialog("党团管理审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
//自定义导出 功能
function exportConfig(value) {
	var xxdm=jQuery("#xxdm").val();
	if("13871"==xxdm){
		customExport("dtxxjg_13871.do",exportData);
	} else {
        customExport(action,exportData);
    }
}

function exportYbzzConfig() {
    customExport("dtxxjg_zsdy.do",exportYbzzData);
}

function exportYbzzData() {
    setSearchTj();//设置高级查询条件
    var url = action+"?method=exportData&dcclbh=dtxxjg_zsdy.do";
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function exportYbdyConfig() {
    customExport("dtxxjg_ybdy.do",exportYbdyData);
}

function exportYbdyData() {
    setSearchTj();//设置高级查询条件
    var url = action+"?method=exportData&dcclbh=dtxxjg_ybdy.do";
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function exportFzdxConfig() {
    customExport("dtxxjg_fzdx.do",exportFzdxData);
}

function exportFzdxData() {
    setSearchTj();//设置高级查询条件
    var url = action+"?method=exportData&dcclbh=dtxxjg_fzdx.do";
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function exportJjfzConfig() {
    customExport("dtxxjg_jjfz.do",exportJjfzData);
}

function exportJjfzData() {
    setSearchTj();//设置高级查询条件
    var url = action+"?method=exportData&dcclbh=dtxxjg_jjfz.do";
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
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
/*
数据导入
*/	
function impData(){
	var realTable = "";
	var tableName = "";
	var sty = "toolbar=no,location=no,directories=no,status=yes";
	sty += ",menubar=no,scrollbars=yes,resizable=yes,width=650,height=420,top=100";
	sty += ",left=200";
	if($("realTable")) realTable = document.getElementById("realTable").value;
	if($("tableName")) tableName = document.getElementById("tableName").value;
	url = 'dtjs_dtxxgl.do?method=importData';
	url += "&realTable=" + realTable;
	url += "&tableName=" + tableName;
	//showTopWin(url,600,500);
	//refreshForm(url);
	window.open(url,'',sty);
}
//同步更新政治面貌
function tbgxzzmm(){
	confirmInfo("确定同步更新政治面貌信息吗？",function(ok){
			if("ok"==ok){
				jQuery.post(action+"?method=tbdtxx",function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
}

//导出方法
function mcexport() {
	setSearchTj();//设置高级查询条件
	var url = action+"?method=mcexport";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//重庆三峡医高专-预审发展对象
function getYsqs() {
	var xy = '';
	jQuery("a[name='a_xy_mc'].selectedValue").each(function(){
		xy = xy+jQuery(this).text()+',';
	})
	var value = xy.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("请选择一个系部，请确认！");
		return false;
	}
	var xymc = jQuery.trim(jQuery("a[name='a_xy_mc'].selectedValue").text());
	var url= action+"?method=getYsqs&value="+xymc;
	window.open(url);
}

//重庆三峡医高专-预备党员
function getXsyb() {
	var xy = '';
	jQuery("a[name='a_xy_mc'].selectedValue").each(function(){
		xy = xy+jQuery(this).text()+',';
	})
	var value = xy.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("请选择一个系部，请确认！");
		return false;
	}
	var xymc = jQuery.trim(jQuery("a[name='a_xy_mc'].selectedValue").text());
	var url= action+"?method=getXsyb&value="+xymc;
	window.open(url);
}

//重庆三峡医高专-预备党员转正（正式党员）
function getYbzz() {
	var xy = '';
	jQuery("a[name='a_xy_mc'].selectedValue").each(function(){
		xy = xy+jQuery(this).text()+',';
	})
	var value = xy.split(",");
	if(value.length != 2  ){
		showAlertDivLayer("请选择一个系部，请确认！");
		return false;
	}
	var xymc = jQuery.trim(jQuery("a[name='a_xy_mc'].selectedValue").text());
	var url= action+"?method=getYbzz&value="+xymc;
	window.open(url);
}