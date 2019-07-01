//家庭信息
var jtxx;
//党员信息
var dyxx;
//预备党员
var ybdy;
//学生资助
var xszz;
//违纪处分
var wjcf;
//就业管理
var jygl;
//评奖评优
var pjpy;
//学生成绩
var xscj;
//勤工助学
var qgzx;
//公寓管理
var gygl;

//显示隐藏内容
function showHiddenNr(id){
	if($(id)){
		if($(id).style.display == "none"){
			$(id).style.display = "";
		}else{
			$(id).style.display = "none";
		}
	}
}

//显示隐藏内容
function showHiddenTable(id,tbid){
	
	if($(id)){
		var div_num = $(id).getElementsByTagName('div').length;

		for(var i=0;i<div_num;i++){
			var obj = $(id).getElementsByTagName('div')[i];
			var obj_id = obj.id;
			if(obj.style.display == "none"){
				obj.style.display = "";
			}else{
				obj.style.display = "none";
			}
		}
	}
	
	showHiddenNr(tbid);
}

//设置家庭信息
function setJtxx(){
	
	//=================家庭基本信息===========================
	
	if($("p_lxdh1") && jtxx.lxdh1!=null){//联系电话
		$("p_lxdh1").innerHTML = jtxx.lxdh1;
	}
	if($("p_yb") && jtxx.yb!=null){//邮编
		$("p_yb").innerHTML = jtxx.yb;
	}
	if($("p_jtszd") && jtxx.jtszd!=null){//家庭地址
		$("p_jtszd").innerHTML = jtxx.jtszd;
	}
	if($("p_jjzk") && jtxx.jjzk!=null){//经济状况
		$("p_jjzk").innerHTML = jtxx.jjzk;
	}
	
	//=================家庭成员1===========================
	
	if($("p_jtcy1_xm") && jtxx.jtcy1_xm!=null){//家庭成员1姓名
		$("p_jtcy1_xm").innerHTML = jtxx.jtcy1_xm;
	}
	if($("p_jtcy1_gx") && jtxx.jtcy1_gx!=null){//家庭成员1关系
		$("p_jtcy1_gx").innerHTML = jtxx.jtcy1_gx;
	}
	if($("p_jtcy1_nl") && jtxx.jtcy1_nl!=null){//家庭成员1出生日期
		$("p_jtcy1_nl").innerHTML = jtxx.jtcy1_nl;
	}
	if($("p_jtcy1_sfzh") && jtxx.jtcy1_sfzh!=null){//家庭成员1身份证号
		$("p_jtcy1_sfzh").innerHTML = jtxx.jtcy1_sfzh;
	}
	if($("p_jtcy1_mzmc") && jtxx.jtcy1_mzmc!=null){//家庭成员1民族
		$("p_jtcy1_mzmc").innerHTML = jtxx.jtcy1_mzmc;
	}
	if($("p_jtcy1_zzmmmc") && jtxx.jtcy1_zzmmmc!=null){//家庭成员1政治面貌
		$("p_jtcy1_zzmmmc").innerHTML = jtxx.jtcy1_zzmmmc;
	}
	if($("p_jtcy1_zy") && jtxx.jtcy1_zy!=null){//家庭成员1职业
		$("p_jtcy1_zy").innerHTML = jtxx.jtcy1_zy;
	}
	if($("p_jtcy1_zw") && jtxx.jtcy1_zw!=null){//家庭成员1职务
		$("p_jtcy1_zw").innerHTML = jtxx.jtcy1_zw;
	}
	if($("p_jtcy1_lxdh1") && jtxx.jtcy1_lxdh1!=null){//家庭成员1工作单位电话
		$("p_jtcy1_lxdh1").innerHTML = jtxx.jtcy1_lxdh1;
	}
	if($("p_jtcy1_lxdh2") && jtxx.jtcy1_lxdh2!=null){//家庭成员1个人电话
		$("p_jtcy1_lxdh2").innerHTML = jtxx.jtcy1_lxdh2;
	}
	if($("p_jtcy1_gzdz") && jtxx.jtcy1_gzdz!=null){//家庭成员1工作地址
		$("p_jtcy1_gzdz").innerHTML = jtxx.jtcy1_gzdz;
	}
	
	//=================家庭成员2===========================
	
	if($("p_jtcy2_xm") && jtxx.jtcy2_xm!=null){//家庭成员2姓名
		$("p_jtcy2_xm").innerHTML = jtxx.jtcy2_xm;
	}
	if($("p_jtcy2_gx") && jtxx.jtcy2_gx!=null){//家庭成员2关系
		$("p_jtcy2_gx").innerHTML = jtxx.jtcy2_gx;
	}
	if($("p_jtcy2_nl") && jtxx.jtcy2_nl!=null){//家庭成员2出生日期
		$("p_jtcy2_nl").innerHTML = jtxx.jtcy2_nl;
	}
	if($("p_jtcy2_sfzh") && jtxx.jtcy2_sfzh!=null){//家庭成员2身份证号
		$("p_jtcy2_sfzh").innerHTML = jtxx.jtcy2_sfzh;
	}
	if($("p_jtcy2_mzmc") && jtxx.jtcy2_mzmc!=null){//家庭成员2民族
		$("p_jtcy2_mzmc").innerHTML = jtxx.jtcy2_mzmc;
	}
	if($("p_jtcy2_zzmmmc") && jtxx.jtcy2_zzmmmc!=null){//家庭成员2政治面貌
		$("p_jtcy2_zzmmmc").innerHTML = jtxx.jtcy2_zzmmmc;
	}
	if($("p_jtcy2_zy") && jtxx.jtcy2_zy!=null){//家庭成员2职业
		$("p_jtcy2_zy").innerHTML = jtxx.jtcy2_zy;
	}
	if($("p_jtcy2_zw") && jtxx.jtcy2_zw!=null){//家庭成员2职务
		$("p_jtcy2_zw").innerHTML = jtxx.jtcy2_zw;
	}
	if($("p_jtcy2_lxdh1") && jtxx.jtcy2_lxdh1!=null){//家庭成员2工作单位电话
		$("p_jtcy2_lxdh1").innerHTML = jtxx.jtcy2_lxdh1;
	}
	if($("p_jtcy2_lxdh2") && jtxx.jtcy2_lxdh2!=null){//家庭成员2个人电话
		$("p_jtcy2_lxdh2").innerHTML = jtxx.jtcy2_lxdh2;
	}
	if($("p_jtcy2_gzdz") && jtxx.jtcy2_gzdz!=null){//家庭成员2工作地址
		$("p_jtcy2_gzdz").innerHTML = jtxx.jtcy2_gzdz;
	}
	
	//=================家庭成员3===========================
	
	if($("p_jtcy3_xm") && jtxx.jtcy3_xm!=null){//家庭成员3姓名
		$("p_jtcy3_xm").innerHTML = jtxx.jtcy3_xm;
	}
	if($("p_jtcy3_gx") && jtxx.jtcy3_gx!=null){//家庭成员3关系
		$("p_jtcy3_gx").innerHTML = jtxx.jtcy3_gx;
	}
	if($("p_jtcy3_nl") && jtxx.jtcy3_nl!=null){//家庭成员3出生日期
		$("p_jtcy3_nl").innerHTML = jtxx.jtcy3_nl;
	}
	if($("p_jtcy3_sfzh") && jtxx.jtcy3_sfzh!=null){//家庭成员3身份证号
		$("p_jtcy3_sfzh").innerHTML = jtxx.jtcy3_sfzh;
	}
	if($("p_jtcy3_mzmc") && jtxx.jtcy3_mzmc!=null){//家庭成员3民族
		$("p_jtcy3_mzmc").innerHTML = jtxx.jtcy3_mzmc;
	}
	if($("p_jtcy3_zzmmmc") && jtxx.jtcy3_zzmmmc!=null){//家庭成员3政治面貌
		$("p_jtcy3_zzmmmc").innerHTML = jtxx.jtcy3_zzmmmc;
	}
	if($("p_jtcy3_zy") && jtxx.jtcy3_zy!=null){//家庭成员3职业
		$("p_jtcy3_zy").innerHTML = jtxx.jtcy3_zy;
	}
	if($("p_jtcy3_zw") && jtxx.jtcy3_zw!=null){//家庭成员3职务
		$("p_jtcy3_zw").innerHTML = jtxx.jtcy3_zw;
	}
	if($("p_jtcy3_lxdh1") && jtxx.jtcy3_lxdh1!=null){//家庭成员3工作单位电话
		$("p_jtcy3_lxdh1").innerHTML = jtxx.jtcy3_lxdh1;
	}
	if($("p_jtcy3_lxdh2") && jtxx.jtcy3_lxdh2!=null){//家庭成员3个人电话
		$("p_jtcy3_lxdh2").innerHTML = jtxx.jtcy3_lxdh2;
	}
	if($("p_jtcy3_gzdz") && jtxx.jtcy3_gzdz!=null){//家庭成员3工作地址
		$("p_jtcy3_gzdz").innerHTML = jtxx.jtcy3_gzdz;
	}
}

//设置党团建设
function setDtjs(){
	
	//=================正式党员===========================
	if($("p_dyxx_xn") && dyxx.xn!=null){//学年
		$("p_dyxx_xn").innerHTML = dyxx.xn;
	}
	if($("p_dyxx_xqmc") && dyxx.xqmc!=null){//学期
		$("p_dyxx_xqmc").innerHTML = dyxx.xqmc;
	}
	if($("p_dyxx_nd") && dyxx.nd!=null){//年度
		$("p_dyxx_nd").innerHTML = dyxx.nd;
	}
	if($("p_dyxx_rdsj") && dyxx.rdsj!=null){//入党时间
		$("p_dyxx_rdsj").innerHTML = dyxx.rdsj;
	}
	if($("p_dyxx_bz") && dyxx.bz!=null){//备注
		$("p_dyxx_bz").innerHTML = dyxx.bz;
	}
	//=================预备党员===========================
	if($("p_ybdy_xn") && ybdy.xn!=null){//学年
		$("p_ybdy_xn").innerHTML = ybdy.xn;
	}
	if($("p_ybdy_xqmc") && ybdy.xqmc!=null){//学期
		$("p_ybdy_xqmc").innerHTML = ybdy.xqmc;
	}
	if($("p_ybdy_nd") && ybdy.nd!=null){//年度
		$("p_ybdy_nd").innerHTML = ybdy.nd;
	}
	if($("p_ybdy_zzzt") && ybdy.zzzt!=null){//结束时间
		if(ybdy.zzzt == "yes"){
			$("p_ybdy_zzzt").innerHTML = "是";
		}else{
			$("p_ybdy_zzzt").innerHTML = "否";
		}
	}
	if($("p_ybdy_kssj") && ybdy.kssj!=null){//开始时间
		$("p_ybdy_kssj").innerHTML = ybdy.kssj;
	}
	if($("p_ybdy_jssj") && ybdy.jssj!=null){//结束时间
		$("p_ybdy_jssj").innerHTML = ybdy.jssj;
	}
	if($("p_ybdy_bz") && ybdy.bz!=null){//备注
		$("p_ybdy_bz").innerHTML = ybdy.bz;
	}
}

//设置公寓管理
function setGygl(){
	
	if($("p_gygl_ldmc") && gygl.ldmc!=null){//学年
		$("p_gygl_ldmc").innerHTML = gygl.ldmc;
	}
	if($("p_gygl_cs") && gygl.cs!=null){//学期
		$("p_gygl_cs").innerHTML = gygl.cs;
	}
	if($("p_gygl_qsh") && gygl.qsh!=null){//年度
		$("p_gygl_qsh").innerHTML = gygl.qsh;
	}
	if($("p_gygl_cwh") && gygl.cwh!=null){//入党时间
		$("p_gygl_cwh").innerHTML = gygl.cwh;
	}
	if($("p_gygl_qsdh") && gygl.qsdh!=null){//备注
		$("p_gygl_qsdh").innerHTML = gygl.qsdh;
	}
	if($("p_gygl_sfbz") && gygl.sfbz!=null){//备注
		$("p_gygl_sfbz").innerHTML = gygl.sfbz;
	}
}

//设置评奖评优内容
function setPjpy(){

	var jspName = pjpy[0].jspName;

	if(pjpy.length >0){
		if(jspName == "area.jsp"){//显示区
			setPjpyArea();
		}else if(jspName == "list.jsp"){//列表
			setPjpyList();
		}
	}
}

//设置评奖评优显示区内容
function setPjpyArea(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_pjpy_Info";
	}
	
	for(var i=1;i<pjpy.length;i++){
		var xmid = "div_"+pjpy[i].xmmc;
		var tbid = "tb_"+pjpy[i].xmmc;
		var divHtml = "";
		
		var pjsj = "";
		
		if(pjpy[i].pjxn!=null && pjpy[i].pjxn!="无"){
			pjsj+="学年："+pjpy[i].pjxn;
		}
		if(pjpy[i].xqmc!=null && pjpy[i].pjxq!="无"){
			pjsj+="学期："+pjpy[i].xqmc;
		}
		if(pjpy[i].pjnd!=null && pjpy[i].pjnd!="无"){
			pjsj+="年度："+pjpy[i].pjnd;
		}
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"3\"><span>";
			divHtml+= "评奖评优信息";
			divHtml+= "(";
			divHtml+= pjpy[i].xmmc;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='30%'>评奖时间</td>";
			divHtml+= "<td width='30%'>申请时间</td>";
			divHtml+= "<td>项目金额</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+pjsj+"</td>";
			divHtml+= "<td width='30%'>"+pjpy[i].sqsj+"</td>";
			divHtml+= "<td>"+pjpy[i].xmje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+pjsj+"</td>";
			divHtml+= "<td width='30%'>"+pjpy[i].sqsj+"</td>";
			divHtml+= "<td>"+pjpy[i].xmje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//设置评奖评优列表内容
function setPjpyList(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_pjpy_Info";
	}
	var divHtml = "";
	
	var xmid = "div_pjpy_thead";
	var tbid = "tb_pjpy_thead";
		
	divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
	divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"4\"><span>";
	divHtml+= "历年评奖信息";
	divHtml+= "</span></th></tr></thead>";
	divHtml+= "<tbody id='"+tbid+"'><tr>";
	divHtml+= "<td width='25%'>项目名称</td>";
	divHtml+= "<td width='25%'>评奖时间</td>";
	divHtml+= "<td width='25%'>申请时间</td>";
	divHtml+= "<td>项目金额</td>";
	divHtml+= "</tr>";

			
	for(var i=1;i<pjpy.length;i++){
			
		var pjsj = "";
		
		if(pjpy[i].pjxn!=null && pjpy[i].pjxn!="无"){
			pjsj+="学年："+pjpy[i].pjxn;
		}
		if(pjpy[i].xqmc!=null && pjpy[i].pjxq!="无"){
			pjsj+="学期："+pjpy[i].xqmc;
		}
		if(pjpy[i].pjnd!=null && pjpy[i].pjnd!="无"){
			pjsj+="年度："+pjpy[i].pjnd;
		}

		divHtml+= "<tr>";
		divHtml+= "<td width='25%'>"+pjpy[i].xmmc+"</td>";
		divHtml+= "<td width='25%'>"+pjsj+"</td>";
		divHtml+= "<td width='25%'>"+pjpy[i].sqsj+"</td>";
		divHtml+= "<td>"+pjpy[i].xmje+"</td>";
		divHtml+= "</tr>";		
	}
	
	divHtml+= "</tbody></table>";
	$(div_id).innerHTML+=divHtml;
}

//设置学生成绩内容
function setXscj(){

	var jspName = xscj[0].jspName;

	if(xscj.length >0){
		if(jspName == "groupByXn.jsp"){//学年成绩
			setXscjByXn();
		}else if(jspName == "groupByXq.jsp"){//学期成绩
			setXscjByXq();
		}
	}
}

//设置评学生学年成绩
function setXscjByXn(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_xscj_Info";
	}
	
	for(var i=1;i<xscj.length;i++){
		var xmid = "div_"+xscj[i].xn;
		var tbid = "tb_"+xscj[i].xn;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"3\"><span>";
			divHtml+= "成绩信息";
			divHtml+= "(";
			divHtml+= "学年："+xscj[i].xn;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='30%'>学期</td>";
			divHtml+= "<td width='30%'>课程名称</td>";
			divHtml+= "<td>成绩</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+xscj[i].xqmc+"</td>";
			divHtml+= "<td width='30%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+xscj[i].xqmc+"</td>";
			divHtml+= "<td width='30%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//设置评学生学期成绩
function setXscjByXq(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_xscj_Info";
	}
	
	for(var i=1;i<xscj.length;i++){
		var xmid = "div_"+xscj[i].xn+xscj[i].xq;
		var tbid = "tb_"+xscj[i].xn+xscj[i].xq;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"2\"><span>";
			divHtml+= "成绩信息";
			divHtml+= "(";
			divHtml+= "学年："+xscj[i].xn+" 学期："+xscj[i].xqmc;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='50%'>课程名称</td>";
			divHtml+= "<td>成绩</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//设置勤工助学内容
function setQgzx(){

	var jspName = qgzx[0].jspName;

	if(qgzx.length >0){
		if(jspName == "qggwOnly.jsp"){//勤工岗位
			setQggw();
		}else if(jspName == "cjffOnly.jsp"){//酬金发放
			setCjff();
		}
	}
}

//设置勤工岗位
function setQggw(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_qgzx_Info";
	}
	
	for(var i=1;i<qgzx.length;i++){
		var xmid = "div_"+qgzx[i].gwdm;
		var tbid = "tb_"+qgzx[i].gwdm;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"2\"><span>";
			divHtml+= "勤工助学信息";
			divHtml+= "(";
			divHtml+= "岗位名称："+qgzx[i].gwdm;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='50%'>岗位申报时间</td>";
			divHtml+= "<td>学生申请时间</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+qgzx[i].gwsbsj+"</td>";
			divHtml+= "<td>"+qgzx[i].sqsj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+qgzx[i].gwsbsj+"</td>";
			divHtml+= "<td>"+qgzx[i].sqsj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//设置酬金发放
function setCjff(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_qgzx_Info";
	}
	
	for(var i=1;i<qgzx.length;i++){
		var xmid = "div_"+qgzx[i].gwdm+qgzx[i].sqsj;
		var tbid = "tb_"+qgzx[i].gwdm+qgzx[i].sqsj;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"5\"><span>";
			divHtml+= "勤工助学信息";
			divHtml+= "(";
			divHtml+= "岗位名称："+qgzx[i].gwdm;
			divHtml+= "       ";
			divHtml+= "岗位申报时间："+qgzx[i].sqsj;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='20%'>学年</td>";
			divHtml+= "<td width='20%'>学期</td>";
			divHtml+= "<td width='20%'>年度</td>";
			divHtml+= "<td width='20%'>月份</td>";
			divHtml+= "<td>金额</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='20%'>"+qgzx[i].xn+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].xqmc+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].nd+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].yf+"</td>";
			divHtml+= "<td>"+qgzx[i].cjje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='20%'>"+qgzx[i].xn+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].xqmc+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].nd+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].yf+"</td>";
			divHtml+= "<td>"+qgzx[i].cjje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//设置资助详细信息
function setXszz() {
	var html = "";
	if (xszz[1].zzxxPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < xszz.length; i++) {
			html += "<tr>";
			html += "<td width='16%'>" + xszz[i].xmmc + "</td>";
			html += "<td width='16%'>" + xszz[i].sqsj + "</td>";
			html += "<td width='16%'>" + xszz[i].sqzq + "</td>";
			html += "<td width='16%'>" + xszz[i].xmzzjb + "</td>";
			html += "<td width='16%'>" + xszz[i].xmzzje + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		if ($("zzxx_tbody")) {
			$("zzxx_tbody").innerHTML = html;
		}
		
	} else {
		if (xszz[1].zzglPage == "yes") {
			var div_id = "div_otherInfo";
			if(!$(div_id)){
				div_id = "div_xszz_Info";
			}
			
			for (var i = 2; i < xszz.length; i++) {
			
				var xmid = "div_" + xszz[i].xmmc;
				var tbid = "tb_"+xszz[i].xmmc;
			
				var divHtml = "";
				if (!$(xmid)) {
					divHtml = "<div id=\"" + xmid + "\">";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"4\"><span>";
					divHtml += "学生资助";
					divHtml += "(";
					divHtml += xszz[i].xmmc;
					divHtml += ")";
					divHtml += "</span></th></tr></thead>";
					divHtml += "<tbody id='"+tbid+"'><tr>";
					divHtml += "<td width='16%'>\u7533\u8bf7\u65f6\u95f4</td>";
					divHtml += "<td width='16%'>\u7533\u8bf7\u5468\u671f</td>";
					divHtml += "<td width='16%'>\u9879\u76ee\u7ea7\u522b</td>";
					divHtml += "<td width='16%'>\u9879\u76ee\u91d1\u989d</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='16%'>" + xszz[i].sqsj + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].sqzq + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzjb + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzje + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					divHtml += "</div>";
					$(div_id).innerHTML += divHtml;
				} else {
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='16%'>" + xszz[i].sqsj + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].sqzq + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzjb + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzje + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					$(xmid).innerHTML += divHtml;
				}
			}
		}
	}
}

//设置违纪处分信息
function setWjcf() {
	var html = "";
	if (wjcf[1].wjxxPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < wjcf.length; i++) {
			html += "<tr>";
			html += "<td width='12%'>" + wjcf[i].xn + "</td>";
			html += "<td width='12%'>" + wjcf[i].xqmc + "</td>";
			html += "<td width='12%'>" + wjcf[i].nd + "</td>";
			html += "<td width='12%'>" + wjcf[i].cflbmc + "</td>";
			html += "<td width='12%'>" + wjcf[i].cfyymc + "</td>";
			html += "<td width='12%'>" + wjcf[i].cfsj + "</td>";
			html += "<td width='12%'>" + wjcf[i].wjsj + "</td>";
			html += "<td width='12%'>" + wjcf[i].cxjg + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		if ($("wjxx_tbody")) {
			$("wjxx_tbody").innerHTML = html;
		}
	} else {
		if (wjcf[1].wjglPage == "yes") {
			var div_id = "div_otherInfo";
			if(!$(div_id)){
				div_id = "div_wjcf_Info";
			}
				
			for (var i = 2; i < wjcf.length; i++) {
				
				var xmid = "div_" + wjcf[i].cflbmc;
				var tbid = "tb_"+wjcf[i].cflbmc;
				var divHtml = "";
				if (!$(xmid)) {
					divHtml = "<div id=\"" + xmid + "\">";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"7\"><span>";
					divHtml += "违纪信息";
					divHtml += "(";
					divHtml += wjcf[i].cflbmc;
					divHtml += ")";
					divHtml += "</span></th></tr></thead>";
					divHtml += "<tbody id='"+tbid+"'><tr>";
					divHtml += "<td width='12%'>\u5b66\u5e74</td>";
					divHtml += "<td width='12%'>\u5b66\u671f</td>";
					divHtml += "<td width='12%'>\u5e74\u5ea6</td>";
					divHtml += "<td width='12%'>\u5904\u5206\u539f\u56e0</td>";
					divHtml += "<td width='12%'>\u5904\u5206\u65f6\u95f4</td>";
					divHtml += "<td width='12%'>\u8fdd\u7eaa\u65f6\u95f4</td>";
					divHtml += "<td width='12%'>\u64a4\u6d88\u7ed3\u679c</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='12%'>" + wjcf[i].xn + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].xqmc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].nd + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfyymc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].wjsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cxjg + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					divHtml += "</div>";
					$(div_id).innerHTML += divHtml;
				} else {
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='12%'>" + wjcf[i].xn + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].xqmc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].nd + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfyymc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].wjsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cxjg + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					$(xmid).innerHTML += divHtml;
				}
			}
		}
	}
}

//设置心理健康信息
function setXljk() {
	var html = "";
	if (xljk[1].xlcsPage != null && xljk[1].xlcsPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < xljk.length; i++) {
			if (xljk[i].xslx == "xlcs") {
				html += "<tr>";
				html += "<td width='33%'>" + xljk[i].xlcsxmmc + "</td>";
				html += "<td width='33%'>" + xljk[i].cssj + "</td>";
				html += "<td width='33%'>" + xljk[i].xlcsjgmc + "</td>";
				html += "</tr>";
			}
		}
		html += "</table>";
		if ($("xlcs_tbody")) {
			$("xlcs_tbody").innerHTML = html;
		}
	}
	html = "";
	if (xljk[1].tsxsPage != null && xljk[1].tsxsPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < xljk.length; i++) {
			if (xljk[i].xslx == "tsxs") {
				html += "<tr>";
				html += "<td width='33%'>" + xljk[i].tbgxxslbmc + "</td>";
				html += "<td width='33%'>" + xljk[i].zxqjzysj + "</td>";
				html += "<td width='33%'>" + xljk[i].xytbgxcs + "</td>";
				html += "</tr>";
			}
		}
		html += "</table>";
		$("tsxs_tbody").innerHTML = html;
	}
}

//设置资助详细信息
function setJygl() {
	if ($("bys_rxnf") && jygl.rxnf != null) {//入学年份
		$("bys_rxnf").innerHTML = jygl.rxnf;
	}
	if ($("bys_bynf") && jygl.bynf != null) {//毕业年份
		$("bys_bynf").innerHTML = jygl.bynf;
	}
	if ($("jyxy_dwmc") && jygl.dwmc != null) {//单位名称
		$("jyxy_dwmc").innerHTML = jygl.dwmc;
	}
	if ($("jyxy_dwxzmc") && jygl.dwxzmc != null) {//单位性质名称
		$("jyxy_dwxzmc").innerHTML = jygl.dwxzmc;
	}
	if ($("jyxy_zgdwmc") && jygl.zgdwmc != null) {//主管单位名称
		$("jyxy_zgdwmc").innerHTML = jygl.zgdwmc;
	}
	if ($("jyxy_bdkssj") && jygl.bdkssj != null) {//报到开始时间
		$("jyxy_bdkssj").innerHTML = jygl.bdkssj;
	}
	if ($("jyxy_bdjssj") && jygl.bdjssj != null) {//报到结束时间
		$("jyxy_bdjssj").innerHTML = jygl.bdjssj;
	}
}

