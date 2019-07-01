var action="qjgz.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var kssj = rowObject["kssj"];
	var jssj = rowObject["jssj"];
	return getShow(kssj,jssj);
}
/**
 * 自动显示提示
 * @return
 */
function autoShowLable(){
	var kssj=jQuery("#kssj").val();
	var jssj=jQuery("#jssj").val();
	if(kssj!=""&&jssj!=""){
		jQuery("#lable").html(getShow(kssj,jssj));
		jQuery("#div_help").show();
	}else{
		jQuery("#div_help").hide();
	}
}
function getShow(kssj,jssj){
	return "大于<font color=\"#FF8040\">"+kssj+"</font>天小于等于<font color=\"#FF8040\">"+jssj+"</font>天&nbsp;(<font color=\"red\">不包括"+kssj+"天</font>)";
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "增加请假规则";
		showDialog(title, 400, 250, url);
		jQuery("#dataTable").reloadGrid();
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		return alertError("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkother()){
		return false;
	}
	var kssj=jQuery("#kssj").val();
	var jssj=jQuery("#jssj").val();
	var id=jQuery("#"+keyid).val(); 
    var qjlxid = jQuery("#qjlxid").val();
    var ssxydm = jQuery("#ssxydm").val();
	jQuery.post("qjgz.do?method=isCanAdd", {
		kssj:kssj,jssj:jssj,qjgzid:id,qjlxid:qjlxid,ssxydm:ssxydm
	}, function(data) {
		if(data["success"]=="true"){
		 	jQuery("#form").ajaxSubmit({
				url:url,
				type:"post",
				dataType:"json",
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
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});	
		}else{
			if(data["message"]=="-1"){
				showAlert("此规则已经被使用，不能修改!");
			}else{
				showAlert("与其他规则&nbsp;<font color='red'>["+data["message"]+"]</font>&nbsp;请假区间相冲突！");
			}
		}
	}, 'json');
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
    var userXy = jQuery("#userXy").val();
    var userXymc = jQuery("#userXymc").val();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else if(rows[0]["ssxydm"] != userXy){
		if("xx" == userXy){
            showAlertDivLayer("只能修改所属学院为全校的记录！");
		}else{
            showAlertDivLayer("只能修改所属学院为"+userXymc+"的记录！");
		}

	}else{
		var url = action+'?method=update&qjgzid=' + rows[0]["qjgzid"];
		var title = "修改请假规则";
		showDialog(title, 400, 250, url);
		jQuery("#dataTable").reloadGrid();
	}
}
function checkother(){
	var kssj=jQuery("#kssj").val();
	var jssj=jQuery("#jssj").val();
	if(kssj<0||jssj<0){
		showAlert("请假区间不能负数区间!");
		return false;
	}
	if(chkNumInput("kssj")||chkNumInput("jssj")){
		showAlert("请假区间不能为非法数字区间!");
		return false;
	}
	if(kssj.indexOf(".")!=-1||jssj.indexOf(".")!=-1){
		showAlert("请假区间必须为整数!");
		return false;
	}
	if(checkNum()){
		showAlert("请填写正确的区间数,当前为非法数字!");
		return false;
	}
	if(parseInt(jssj)<=parseInt(kssj)){
		showAlert("请填写正确的区间，结束区间应该大于开始区间!");
		return false;
	}
	if(parseInt(jssj)<=0){
		showAlert("请填写正确的区间，结束区间应该大于0");
		return false;
	}
	return true;
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
	var ids = jQuery("#dataTable").getSeletIds();
    var userXy = jQuery("#userXy").val();
    var userXymc = jQuery("#userXymc").val();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if(rows[0]["ssxydm"] != userXy){
        if("xx" == userXy){
            showAlertDivLayer("只能修改所属学院为全校的记录！");
        }else{
            showAlertDivLayer("只能修改所属学院为"+userXymc+"的记录！");
        }

    } else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="区间为<font color='red'>["+data["nodel"]+"]</font>";
						mes+="的请假规则已经被使用不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

//查看项目链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["qjgzid"] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	}
	showDialog("请假规则查看", 400, 200, "qjgz.do?method=ckQjgz&qjgzid="
			+ rows[0]["qjgzid"]);
}


//开关链接
function openLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='linkClick(\""
	+ rowObject["qjgzid"] + "\");'>" + cellValue
	+ "</a>";
}

//点击链接弹窗
function linkClick(qjgzid){
	showDialog("开启关闭开关", 400, 200, "qjgz.do?method=openZt&qjgzid="
			+ qjgzid);
}
