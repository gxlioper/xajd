//前往学生申请
function cxJzwh(){
	var url = "jxjzgl_cxJxjz.do?method=cxJzwh";
	window.location.href=url;
	//refreshForm(url);
}

//前往已建制名单
function cxJzmd(){
	var url = "jxjzgl_cxJxjz.do?method=cxJzmd";
	window.location.href=url;
	//refreshForm(url);
}

//增加保存建制
function saveZjBcXjjz(){
	if(checkNotNullZjBcXjjz()){
		var url="jxjzgl_cxJxjz_ajax.do?method=zjBcJzwh";
		var map={};
		map["jzjb"]=jQuery("#djdm").val();
		map["jzmc"]=escape(jQuery("#jzmc").val());
		map["jgmc"]=escape(jQuery("#jgmc").val());
		map["jgdh"]=jQuery("#jgdh").val();
		map["jsmc"]=escape(jQuery("#jsmc").val());
		map["jsdh"]=jQuery("#jsdh").val();
		map["sjid"]=getSjid();
		jQuery.post(url,map,function(data){
			if(data != null){
				alertInfo("增加成功!");
				zjBcXjjxNode(data);
			}else{
				alertInfo("增加失败!");
			}
			//createNode
			closeWindown();
			refurbishJxjz();
		},"json");
		
	}
}

//修改保存建制
function saveXgBcXjjz(){
	if(checkNotNullZjBcXjjz()){
		var url="jxjzgl_cxJxjz_ajax.do?method=xgBcJzwh";
		var map={};
		map["jzjb"]=jQuery("#djdm").val();
		map["jzmc"]=escape(jQuery("#jzmc").val());
		map["jgmc"]=escape(jQuery("#jgmc").val());
		map["jgdh"]=jQuery("#jgdh").val();
		map["jsmc"]=escape(jQuery("#jsmc").val());
		map["jsdh"]=jQuery("#jsdh").val();
		map["jzid"]=jQuery("#jzid").val();
		jQuery.post(url,map,function(data){
			if(data != null && data.jzid != null){
				alertInfo("修改成功!");
				xgBcXjjxNode(data);
			}else{
				alertInfo("修改失败!");
			}
			//createNode
			closeWindown();
			refurbishJxjzInfo();
		},"json");
		
	}
}

//查询已建制学生名单
function cxYjzxsmd(obj){
	var jqSearchJzid=jQuery("#searchJzid");
	var jzid=getSjid();
	jqSearchJzid.val(jzid);
	searchRs();
	
}



//修改保存建制节点
function xgBcXjjxNode(data){
	var xzId=getSjid();
	var jqXz=jQuery("#"+xzId);
	jqXz.children().next().children().html(data.jzmc);
}

//删除建制节点
function scXjjxNode(){
	var jzid=getSjid();
	var jqJzNode=jQuery("#"+jzid);
	jqJzNode.next().remove();
	jqJzNode.remove();
}

//获取上级ID
function getSjid(){
	var hitTd=jQuery(".hitTd");
	if(hitTd.length == 0){
		alertInfo("请选择节点!");
		return "";
	}
	var sjId=hitTd.parent().attr("id");
	return sjId;
}

//获取选中的当前节点jQuery对象
function getClickNode(){
	var hitTd=jQuery(".hitTd");
	if(hitTd.length == 0){
		alertInfo("请选择节点!");
		return null;
	}
	var clickNode=hitTd.children();
	return clickNode;
}

//增加下级建制
function zjXjjz(){
	if(checkZjxjjz() && checkXjjz()){
		jQuery.ajaxSetup({async:false});
		loadZjXjpzPage();
		tipsWindown("增加下级建制","id:div_zjjz","340","300","true","","true","id");
		jQuery.ajaxSetup({async:true});
	}
}

//修改军训建制
function xgJxjz(){
	if(checkDjjz()){
		//当前节点为军训节点不能修改
		alertInfo("不能修改军训信息!");
		return false;
	}
	if(checkZjxjjz()){
		jQuery.ajaxSetup({async:false});
		loadXgXjpzPage();
		tipsWindown("修改下级建制","id:div_zjjz","340","300","true","","true","id");
		jQuery.ajaxSetup({async:true});
	}
	
}

//验证增加保存下级建制
function checkNotNullZjBcXjjz(){
	var jzmc=jQuery("#jzmc").val();
	if(jzmc == ""){
		alertInfo("请填写建制名称!");
		return false;
	}
	return true;
}

//验证增加下级建制
function checkZjxjjz(){
	var jqHitTd = jQuery(".hitTd");
	if(jqHitTd.length >0){
		return true;
	}else{
		alertInfo("请选择建制!");
		return false;
	}
	return false;
}

//验证下级建制
function checkXjjz(){
	var jddjdmZd=jQuery("#jddjdmZd").val();
	var jzjb=jQuery(".hitTd").children().attr("class");
	if(jzjb == jddjdmZd){
		alertInfo("当前是最小编制,不能再添加下级编制!");
		return false;
	}
	return true;
}

//验证是否是顶级建制
function checkDjjz(){
	var jzid=getSjid();
	var jqJzid=jQuery("#"+jzid);
	if(jqJzid.children().next().children().attr("class") ==null){
		return true;
	}
	return false;
}

//加载增加下级编制  页面
function loadZjXjpzPage(){
	var url="jxjzgl_cxJxjz_ajax.do?method=zjJzwh";
	var sjid=getSjid();
	var map={"sjid":sjid};
	map["jzjb"]=jQuery(".hitTd").children().attr("class");
	jQuery("#div_zjjz").load(url,map,function(data){
	});
}

function qhShow(obj){
	if(obj.value=='002'){
		jQuery("#th1").text("营长");
		jQuery("#th2").text("营长电话");
		jQuery("#th3").text("教导员");
		jQuery("#th4").text("教导员电话");
	}else if(obj.value=='003'){
		jQuery("#th1").text("连长");
		jQuery("#th2").text("连长电话");
		jQuery("#th3").text("指导员");
		jQuery("#th4").text("指导员电话");
	}else{
		jQuery("#th1").text("教官姓名");
		jQuery("#th2").text("教官电话");
		jQuery("#th3").text("教师姓名");
		jQuery("#th4").text("教师电话");
	}
}

//加载增加下级编制  页面
function loadXgXjpzPage(){
	var url="jxjzgl_cxJxjz_ajax.do?method=xgJzwh";
	var jzid=getSjid();
	var map={"jzid":jzid};
	jQuery("#div_zjjz").load(url,map,function(data){
		//alert(jQuery("#div_zjjz").html()+"--"+data);
	});
}

//验证唯一的建制名称
function checkOnlyJzmc(obj){
	var sjid=getSjid();
	var jqObj=jQuery(obj);
	var url="jxjzgl_cxJxjz_ajax.do?method=checkJzmc";
	var map={};
	map["sjid"]=sjid;
	map["jzmc"]=escape(jqObj.val());
	jQuery.post(url,map,function(data){
		if(data == "false"){
			alertInfo("建制名称已经存在!");
			jqObj.val("");
			return false;
		}
	});
}

//查询维护建制名单
function cxWhjzmd(){
	var jqClickNode=getClickNode();
	var jzid=getSjid();
	if(jqClickNode != null){
		if(checkIsChildrenNode(jqClickNode[0])){
			var url="jxjzgl_cxJxjz.do?method=cxWhjzmd&jzid="+jzid;
			showTopWin(url,'900','600');
		}else{
			alertInfo("请选择最后一级建制维护建制名单!");
		}
	}else{
		return false;
	}
}

//刷新页面
function refurbishJxjz(){
	searchRs();
	//刷新统计信息
	loadDqjzxx();
}

//刷新页面信息
function refurbishJxjzInfo(){
	var jqNode=getClickNode();
	jqNode.click();
}

//查询历史建制名单
function loadDqjzxx(){
	var url="jxjzgl_cxJxjz_ajax.do?method=cxDqjxxxAjax";
	jQuery("#jxjzrsTj").load(url,parameter,function(data){
	});
}

//取消编制
function qxbz(){
	var jqCheck = jQuery("[name=div_pkValue]:checked");
	var pkValue = "";
	var jzids = "";
	if(jqCheck.length > 0){
		jqCheck.each(function(){
			var xh=jQuery(this).parent().next().html();
			var jzid=jQuery(this).val();
			if(pkValue == ""){
				pkValue=xh;
				jzids=jzid;
			}else{
				pkValue=pkValue+"!!@@!!"+xh;
				jzids=jzids+"!!@@!!"+jzid;
			}
		});
	}else{
		alertInfo("请选择学生!");
		return false;
	}
	var jxid=jQuery("#jxid").val();
	var url="jxjzgl_cxJxjz_ajax.do?method=qxbz";
	var map={};
	map["pkValue"]=pkValue;
	map["jxid"]=jxid;
	map["jzids"]=jzids;
	jQuery.post(url,map,function(data){
		if(data != null){
			alertInfo(data);
			//刷新学生建制
			if(data == "取消编制成功!"){
				refurbishJxjz();
			}
		}
	});
	
	
}

//删除军训建制
function scXjjz(){
	var jzid=getSjid();
	var parentJzid=jQuery("#"+jzid).parent().parent().parent().parent().prev().attr("id");
	if(checkDjjz()){
		//当前节点为军训节点不能删除
		alertInfo("不能删除军训!");
		return false;
	}
	if(jzid != ""){
		confirmInfo("删除本级建制后本级建制的下级建制也会被删除,是否确认删除?",function(t){
			if(t=="ok"){
				var url="jxjzgl_cxJxjz_ajax.do?method=scJzwh";
				var map={};
				map["jzid"]=jzid;
				jQuery.post(url,map,function(data){
					if(data == "true"){
						scXjjxNode();
						gotoNode(parentJzid);
						alertInfo("删除成功!");
					}else{
						alertInfo("删除失败!");
					}
				});	
			}
		});
	}
}

//删除建制后定位到上级节点
function gotoNode(jzid){
	var jqJzNode=jQuery("#"+jzid);
	jqJzNode.children().next().children().click();
}

//验证建制名单根据军训成绩和军训表现
function checkJzmdByJxcjAndJxbx(){
	var url="jxjzgl_cxJxjz_ajax.do?method=checkXscjAndBx";
	var jxid=jQuery("#jxid").val();
	var jzid=getSjid();
	var map={"jxid":jxid,"jzid":jzid};
	jQuery.post(url,map,function(data){
		if(data !=null){
			if(data.isJxcj == "true" || data.isJxbx == "true"){
				alertInfo("删除建制下的部分学生已被录入军训成绩或军训表现,不能删除当前建制.");
			}else{
				scXjjz();
			}
		}
	},"json");	
}

//增加保存建制节点
function zjBcXjjxNode(data){
	var jzwhList=weaveData(data);
	var jqNodeHtml=null;
	var xzId=getSjid();
	var jqXz=jQuery("#"+xzId);
	var jqParentNode=getClickNode();

	//判断节点是否有子节点目录
	if(jqXz.next().attr("class") == "chlidNode"){
		//产生子节点
		jqNodeHtml=createNodeOption(jzwhList);
		jqXz.next().children().next().children().children().append(jqNodeHtml);
		return false;
	}else{
		//创建去查询是否有子节点目录
		createChildrenNode(jqParentNode);
	}
	
}

//验证只能输入数字
function cehckInt(obj){
	obj.value=obj.value.replace(/[^\d]/g,'');
}

