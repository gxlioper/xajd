var DCCLBH="xsxx_gygl_xsxxcj.do";
function addXsxxcj(){
	showDialog('增加学生信息采集',800,520,'xsxx_gygl_xsxxcj.do?method=addXsxxcj');
}

function save(){
	var flag=true;
	flag=checkNotNull();
	if(!flag){
		return false;
	}
	var url = "xsxx_gygl_xsxxcj.do?method=addXsxxcj&type=save";
    ajaxSubFormWithFun("xsxxcjForm",url,function(data){
  	 if(data["message"]=="保存成功！"){
  		 showAlert(data["message"],{},{"clkFun":function(){
  				if (parent.window){
  					refershParent();
  				}
  			}});
  	 }else{
  		 showAlert(data["message"]);
  		 
  	 }
	});
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xsxx_gygl_xsxxcj.do?method=updateXsxxcj&xh='+rows[0]["xh"];
		var title = "修改学生采集信息";
		showDialog(title,800,520,url);
	}
}

function viewXsxxcj(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		var url = 'xsxx_gygl_xsxxcj.do?method=viewXsxxcj&xh='+rows[0]["xh"];
		var title = "查看学生采集信息";
		showDialog(title,800,520,url);
	}
}
function xsxxcjView(xh){
	var url = 'xsxx_gygl_xsxxcj.do?method=viewXsxxcj&xh='+xh;
	var title = "查看学生采集信息";
	showDialog(title,800,520,url);
}


function saveUpdate(){
	var flag=true;
	flag=checkNotNull();
	if(!flag){
		return false;
	}
	var url = "xsxx_gygl_xsxxcj.do?method=updateXsxxcj&type=update";
	ajaxSubFormWithFun("xsxxcjForm",url,function(data){
	  	 if(data["message"]=="保存成功！"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  				if (parent.window){
	  					refershParent();
	  				}
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
	});
	
}


function delXsxxcj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xsxx_gygl_xsxxcj.do?method=delXsxxcj",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}

function saveXscj(){
	var flag=true;
	flag=checkNotNull();
	if(!flag){
		return false;
	}
	var url = "xsxx_gygl_xsxxcj.do?method=saveXsxxcj";
	ajaxSubFormWithFun("xsxxcjForm",url,function(data){
	  	 if(data["message"]=="保存成功！"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
	});
	
}

function exportConfig(){
	customExport(DCCLBH, exportData);
}

//导出查询结果
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xsxx_gygl_xsxxcj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导出学生基本信息统计“台账”

function exportXsjbxxtz(){
	setSearchTj();//设置高级查询条件
	var url = "xsxx_gygl_xsxxcj.do?method=exportXsjbxxtz";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导出困难情况统计“台账”

function exportXsknxxtz(){
	setSearchTj();//设置高级查询条件
	var url = "xsxx_gygl_xsxxcj.do?method=exportXsknxxtz";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function checkNotNull(){
	var xh=jQuery("#xh").val();
	if(xh==null||xh==""){
		showAlert("请选择学生！");
		flag=false;
		return false;
	}
	
	
	//户口所在地判断
	var hkszdshen=jQuery("#hkszdshen").val();
	var hkszdshi=jQuery("#hkszdshi").val();
	var hkszdxian=jQuery("#hkszdxian").val();
	var hkszdz=jQuery("#hkszdz").val();
	if(hkszdshen==""||hkszdshen==null||hkszdshi==""||hkszdshi==null||hkszdxian==""||hkszdxian==null||hkszdz==""||hkszdz==null){
		showAlert("请填写完整户口所在地！");
		flag=false;
		return false;
	}
	//家庭地址判断
	var jtdzshen=jQuery("#jtdzshen").val();
	var jtdzshi=jQuery("#jtdzshi").val();
	var jtdzxian=jQuery("#jtdzxian").val();
	var jtdzz=jQuery("#jtdzz").val();
	if(jtdzshen==""||jtdzshen==null||jtdzshi==""||jtdzshi==null||jtdzxian==""||jtdzxian==null||jtdzz==""||jtdzz==null){
		showAlert("请填写完整家庭地址！");
		flag=false;
		return false;
	}
	//生源地判断
	var sydshen=jQuery("#sydshen").val();
	var sydshi=jQuery("#sydshi").val();
	var sydxian=jQuery("#sydxian").val();
	if(sydshen==""||sydshen==null||sydshi==""||sydshi==null||sydxian==""||sydxian==null){
		showAlert("请填写完整生源地！");
		flag=false;
		return false;
	}
	//籍贯判断
	var jgshen=jQuery("#jgshen").val();
	var jgshi=jQuery("#jgshi").val();
	var jgxian=jQuery("#jgxian").val();
	if(jgshen==""||jgshen==null||jgshi==""||jgshi==null||jgxian==""||jgxian==null){
		showAlert("请填写完整籍贯！");
		flag=false;
		return false;
	}
	
	var jtlxfs=jQuery("#jtlxfs").val();
	if(jtlxfs==""){
		showAlert("请填写家庭联系方式！");
		flag=false;
		return false;
	}
	
	//入党申请为“是”判断
	var sfsqrd=jQuery("#sfsqrd").val();
	if(sfsqrd=="是"){
		var djsqssj=jQuery("#djsqssj").val();
		var rdsj=jQuery("#rdsj").val();
		var zzsj=jQuery("#zzsj").val();
		if(djsqssj==null||djsqssj==""){
			showAlert("请填写入党信息！");
			flag=false;
			return false;
		}
	}
	
	//少数名族为“是”判断
	var sfssmz=jQuery("#sfssmz").val();
	if(sfssmz=="是"){
		var ssmz=jQuery("#ssmz").val();
		if(ssmz==null||ssmz==""){
			showAlert("请填选择少数名族！");
			flag=false;
			return false;
		}
	}
	
	//临时培训为“是”判断
	var sflspx=jQuery("#sflspx").val();
	if(sflspx=="是"){
		var pxmc=jQuery("#pxmc").val();
		if(pxmc==null||pxmc==""){
			showAlert("请填写培训名称！");
			flag=false;
			return false;
		}
	}
	
	//宗教信仰为“是”判断
	var sfzjxy=jQuery("#sfzjxy").val();
	if(sfzjxy=="是"){
		var xyzjmc=jQuery("#xyzjmc").val();
		var cjzjsj=jQuery("#cjzjsj").val();
		if(cjzjsj==null||cjzjsj==""||xyzjmc==null||xyzjmc==""){
			showAlert("请填写宗教信息！");
			flag=false;
			return false;
		}
	}
	
	//经济困难为“是”判断
	var sfjjkn=jQuery("#sfjjkn").val();
	if(sfjjkn=="是"){
		var jjknyy=jQuery("#jjknyy").val();
		if(jjknyy==null||jjknyy==""){
			showAlert("请填写经济困难原因！");
			flag=false;
			return false;
		}
	}
	
	//身体是否疾病为“是”判断
	var stsfcj=jQuery("#stsfcj").val();
	if(stsfcj=="是"){
		var stcjyy=jQuery("#stcjyy").val();
		if(stcjyy==null||stcjyy==""){
			showAlert("请填写身体疾病原因！");
			flag=false;
			return false;
		}
	}
	
	//学习困难为“是”判断
	var sfxxkn=jQuery("#sfxxkn").val();
	if(sfxxkn=="是"){
		var xxknyy=jQuery("#xxknyy").val();
		if(xxknyy==null||xxknyy==""){
			showAlert("请填写学习困难原因！");
			flag=false;
			return false;
		}
	}
	
	//心理困难为“是”判断
	var sfxlkr=jQuery("#sfxlkr").val();
	if(sfxlkr=="是"){
		var xlkryy=jQuery("#xlkryy").val();
		if(xlkryy==null||xlkryy==""){
			showAlert("请填写心理困难原因！");
			flag=false;
			return false;
		}
	}
	
	//家庭困扰为“是”判断
	var sfjtkr=jQuery("#sfjtkr").val();
	if(sfjtkr=="是"){
		var jtkryy=jQuery("#jtkryy").val();
		if(jtkryy==null||jtkryy==""){
			showAlert("请填写家庭困扰原因！");
			flag=false;
			return false;
		}
	}
	
	//其他困扰为“是”判断
	var sfyqtkr=jQuery("#sfyqtkr").val();
	if(sfyqtkr=="是"){
		var qtkryy=jQuery("#qtkryy").val();
		if(qtkryy==null||qtkryy==""){
			showAlert("请填写其他困扰原因！");
			flag=false;
			return false;
		}
	}
	return true;
	
}
