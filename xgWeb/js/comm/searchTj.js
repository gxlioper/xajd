/** ===============================初始化数据==================================*/
jQuery(function(){
	initPlcxXhData();
	initPlcxXmData();
});
//拼音
var PY_BIG = ["0", "1", "2", "3", "4", "5", "6","7", "8", "9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","-"];

//简易条件
//[性别,年级,学年,学期,年度,培养层次,学制,学籍,是否在校,是否已毕业,
//政治面貌,民族,是否缴费,是否,户口,转档类别,异动类别,省份,是否通知,
//校区代码,园区代码,楼栋代码,层数,寝室号,可否混住,审核状态,床位分配状态,性别限定
//入住情况,部门,是否评分,是否确认,是否审核,是否提交,层号,退宿原因,审核结果,
//楼栋,是否入住,是否分配,楼栋性别,家庭困难类型,紧急程度,处理状态,值班人员,
//公寓管理类型,党团建设阶段代码,是否进修生, 审核状态1,审核状态2 ,辅导员审核状态、学习审核状态
//审核状态3,.项目代码，项目性质，项目类型, , ,公寓纪律类别大类代码,公寓纪律类别代码,是否征兵离校
//------------------------------请补上-----------------------------------
//------------------------------请补上-----------------------------------
//评奖周期，评奖历史项目、请假类别,申述结果,月份,岗位状态,岗位性质,在岗状态,获得荣誉,勤工部门,是否困难生，勤工审核状态，
//军训年级,军训状态,参训情况,军训信息,表现类别,具体表现,是否获得,是否已编制,团id,营id,连id,排id,入伍学年]
//入学方式,学期名称
//公寓纪律处分类别,处理状态代码,审核状态代码,评优类别,用户身份
//審核狀態1,審核狀態2,推荐档次,上级推荐档次
//特殊学生类型名称,评奖项目名称,心理咨询,勤工学生申请,申请项目名称，新评奖项目类型]
//zczt 学期注册状态
//txzt 学年小结填写状态,新项目类型
//rwfs 入伍方式   gybxlb 公寓保修类别 zzxmlb 资助项目类别
//qyzt 档案管理-档案模板启用状态、档案清单维护状态、费用发放项目,团学活动类别,公寓异动类型,绿色通道,评分状态,困难生申请性质
//]
var jytj = ["xb","nj","xn","xq","nd","pycc","xz","xj","sfzx","sfyby",
			"zm","mz","sfjf","sfsfs","xsfsfs","sf","hk","zdlb","ydlb","prov","sftz",
			"xqdm","yqdm","lddm","cs","qsh","kfhz","shzt","cwfp","xbxd",
			"rzqk","bm","sfpf","sfqr","sfsh","sftj","ch","tsyy","shjg","kg",
			"ld","sfrz","sffp","ldxb","jtknlx","jjcd","clzt","shztlx","zbry",
			"gygllx","jddm","sqjg","qjlx","sfhq","shzt1","shzt2","sfytj","dazxqk",
			"shzt3","xmdm","xmxz","xmlx","zw","sflx","gyjllbdldm","gyjllbdm","sfzblx","zd1","zd3",
			"zd5","zd24","zd26","sfbgb","zd8","zd9","zd10","zd11","zd13","zd5","zd17",
			"zd16","zd21","shzt4","shzt5","zczq","cflb","cfyy","cflbmc","cfyymc",
			"pjzq","pjlsxm","qjlb","ssjg","yf","shjg2","shjg3","gwzt","gwxz","zgzt","hdry","qgbm","qgsybm","qgsybmsq","sfkns","qgshzt",
			"jxnj","jxzt","cxqk","jxxx","bxlb","jtbx","sfhd","sfybz","tid","yid","lid","pid","bid","ssid","rwxn",
			"rxfs","xqmc","zzmm","xl","xw","zc","jhshzt","tjdc","zxjtjdc","jxlb","jsfs","jxdj","hjjxmc",
			"gyjlcflb","clztdm","shztdm","pylb","kssj","jssj","ksnum","jsnum","yhsf","qzxgzt",
			"shztOne","shztTwo","rddc","sjdc","zhzt","zzxm","zzxm1","sheng","shi","qu","cxdjdm","tjzt","cpz","sfzs","sfsqrd",
			"sfssmz","sfdgsx","sflspx","sfzjxy","sfjjkn","stsfcj","sfxxkn","sfxlkr","sfjtkr","sfyqtkr",
			"lxdm","xmmc","rcxwshzt","rcxwdl","rcxwlb","rcxwlbnew","rcxwdlnew","rcxwxlnew","zxbk","jydwxz","jyxs","sshy","pxlx","gslx",
			"hfzt","kycxxmlb","wjcddm","wjgabz","pjzt","zxztnew","gywpdl","gywplb","bzlx","bjdl","qjlxdm","xjzt","gyzgzt",
			"zgstatus","yystatus","gzzt","qjzt","qgxssq","pjsqxm","shztx","shztxbjpy","shztbjpyjg","zczt","sfbbhcyhk","xszbblx","bmlb","bbzt","txzt",
			"xjlb","xjlbmc","njNew","xyNew","zyNew","bjNew","bmNew","xxmlx","xxmxz","rwfs","gybxlb","zzxmlb","cbzkmc",
			"qyzt","whzt","xhqk","fbqk","gzlx","syzt","sfnz","gyyjfl","gyyjxfkqk","fyffXm","jxmc","pdxn","pdxq",
			"zxsstatus","zxszg","yyzt","zxzt","sfpkx","jclx","rcswbxlb","bxsfbl",
			"yw","zblx","gzdj","xlwtlx","txhdlb","ydlx","ydlxjg","lstdLy","wplb","wpmc","ghzt","cclx","qqlx","thjlGzdj","ylbxzt","yjnum","bynd","xljkgxlx","xljkgzlx","hjxz",
			"ytlb","fxzt","xqfl","xqfl1","cxblb","shbz","hdlx","bkgs",
			"zjjcDd","zjsyzc","jxkqlx","jxkqlb","xmjb","sskm","stlb","stxmlb","stxm","ystlb","ystxmlb","ystxmmc","qyztmc","pfzt","ffzt","yxzt","zyjrddj","lks","qsz","yqmc","hkxz",
			"zxsNj","zxsXy","zxsZy","zxsBj","zxsXz","zxsXjlb","fzxsNj","fzxsXy","fzxsZy","fzxsBj","fzxsXz","fzxsXjlb","qjlxmc","zjcmtjzt",
			"qjtslx","qjksjc","qjjsjc","kcmc","lb","jclb","gw","czr","knssqxz","hdpl","zjz","tnzt","sdzt","xmmkdm","jfxmdm","yyzc",
			"csms","rdzt","bzrcpdj","xscpdj","qgrq","qgmx","ysjxjxmlx","jxjb","jxlbnew", "yxzt1","jxsqzd",
			"lydq","xqah","drzw","sfqz","jxdmx","fkzt","sfhg","jd","gwlb","xiaoq","zjlysdzt","zzmmnew","sfsn","szdzb","zjlyshsdzt","sfxyyt","sfyyt","czlx","js","jjlx","tjztwpqs","jtgj","xmlb","xmdl",
			"fwjg","xslb","knlx","fwlx","sfbm","lxnew","bxxz","clzb","sfgz","pcjg","sfsxzt","lnjdhk","bxxs","zjxy","ybqk","gfqkfl","sfgcj","kslb","hdxs","yjyy","fdjslx","alzt","aljb","hdzl","sy","cqxxxjzt","hdjxzt","stzt","scly",
			"smnj","smlx","dxq","sjd","jssf","wtjjcd","zcfs","dazcxx","bxlx","drhd","hdlx10699","sfdsh","bmlx","bjsblx","grsblx","sfdb","newstype","zt","zwlx","shztHb","shztZj",
			"xxqk","qj","zsqk","zsqj","qskqlb","stxj","dwlb","gzxz","gwlx","xsgbzw","jldj","pm","sfkhtg","jb","thlx","sfzdgz",
			"jfxz","bz","jszgzt","sfzg","sfzb","sblx","kcxz","fsbj","jjlxdm","sfgk","sfxn","sfgmbx","kclbdm","cz"];

			
//设置模糊查询
function setMhcxValue(){

	var mkcx_value = $("mkcx_value").value;
	
	var arr_mkcx_value = new Array();
	
	if(mkcx_value!=""){
		arr_mkcx_value = mkcx_value.split("-");
	}
	
	if(arr_mkcx_value.length > 0){
		for(var i=0;i<arr_mkcx_value.length;i++){
			var id = "input_"+arr_mkcx_value[i];
			if($(id)){
				$(id).value = $("input_mhcx").value;
			}
		}
	}
}
	
//获得选中条件
function getClickTj(lx){

	var objName = "tj_"+lx;
	var obj_num = document.getElementsByName(objName).length;
	var obj_arr = new Array();
	var obj_count = 0;
	
	for(var i=0;i<obj_num;i++){
		var obj = document.getElementsByName(objName)[i];
		if(obj.className == "selectedValue"){
			var objId = "tj_"+lx+"_";
			var obj_v = obj.id.replace(objId,"");
			obj_arr[obj_count] = obj_v;
			obj_count++;
		}
	}
	
	return obj_arr;
}

//获得选中年级
function getClickNj(){

	//年级
	var nj_num = document.getElementsByName("tj_nj").length;
	var nj_arr = new Array();
	var nj_count = 0;
	
	for(var i=0;i<nj_num;i++){
		var obj = document.getElementsByName("tj_nj")[i];
		if(obj.className == "selectedValue"){
			var nj_v = obj.id.replace("tj_nj_","");
			nj_arr[nj_count] = nj_v;
			nj_count++;
		}
	}
	
	return nj_arr;
}
		
//获得选中学院
function getClickXy(){

	//学院
	var xy_num = document.getElementsByName("a_xy_mc").length;
	var xy_arr = new Array();
	var xy_count = 0;

	for(var i=0;i<xy_num;i++){
		var obj = document.getElementsByName("a_xy_mc")[i];
		if(obj.className == "selectedValue"){
			var xydm_v = obj.id.substring(9,obj.id.length);
			xy_arr[xy_count] = xydm_v;
			xy_count++;
		}
	}

	return xy_arr;
}
	

//获得选中专业
function getClickZy(){

	//专业
	var zy_num = document.getElementsByName("a_zy_mc").length;
	var zy_arr = new Array();
	var zy_count = 0;

	for(var i=0;i<zy_num;i++){
		var obj = document.getElementsByName("a_zy_mc")[i];
		if(obj.className == "selectedValue"){
			var zydm_v = obj.id.substring(9,obj.id.length);
			zy_arr[zy_count] = zydm_v;
			zy_count++;
		}
	}
	
	return zy_arr;
}

//获得选中班级
function getClickBj(){

	//班级
	var bj_num = document.getElementsByName("a_bj_mc").length;
	var bj_arr = new Array();
	var bj_count = 0;

	for(var i=0;i<bj_num;i++){
		var obj = document.getElementsByName("a_bj_mc")[i];
		if(obj.className == "selectedValue"){
			var bjdm_v = obj.id.substring(9,obj.id.length);
			bj_arr[bj_count] = bjdm_v;
			bj_count++;
		}
	}
	
	return bj_arr;
}
/** ===============================初始化数据 end==============================*/

/** ===============================已选条件相关==============================*/

//设置已选择的时间条件
function setKssjOrJssj(){
	
	var kssj_count = document.getElementsByName("search_tj_kssj").length;
	var jssj_count = document.getElementsByName("search_tj_jssj").length;
	var sjmc_count = document.getElementsByName("search_tj_sjmc").length;
	
	//开始时间
	if(kssj_count>0){
		for(var i=0;i<kssj_count;i++){
			var obj = document.getElementsByName("search_tj_kssj")[i];
			if(obj.value != "" && document.getElementsByName("searchModel.search_tj_kssj")
				&& document.getElementsByName("searchModel.search_tj_kssj")[i]){
				document.getElementsByName("searchModel.search_tj_kssj")[i].value = obj.value;
			}
		}
	}
	
	//结束时间
	if(jssj_count>0){
		for(var i=0;i<jssj_count;i++){
			var obj = document.getElementsByName("search_tj_jssj")[i];
			if(obj.value != "" && document.getElementsByName("searchModel.search_tj_jssj")
				&& document.getElementsByName("searchModel.search_tj_jssj")[i]){
				document.getElementsByName("searchModel.search_tj_jssj")[i].value = obj.value;
			}
		}
	}

	//时间名称
	if(sjmc_count>0){
		for(var i=0;i<sjmc_count;i++){
			if(document.getElementsByName("search_tj_kssj")[i] 
				&& document.getElementsByName("search_tj_kssj")[i] 
				&& document.getElementsByName("search_tj_kssj")[i]){
			var kssj = document.getElementsByName("search_tj_kssj")[i];
			var jssj = document.getElementsByName("search_tj_jssj")[i];
			var obj = document.getElementsByName("search_tj_sjmc")[i];
	
				if((kssj && kssj.value != "") || (jssj && jssj.value != "")){
					/*
					$("yxtj_div").style.display = "";
		
					var dlHtml = $("yxtj_dl").innerHTML;
				
					dlHtml+= "<dd id=\""+i+"_sjtj_yxtj_dd\">";
					dlHtml+= "<a href=\"#\" onclick=\"removeYxsjtj('"+i+"');return false;\">";
					dlHtml+= "<h5>"+obj.value+"</h5>已选择";
					dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
					
					$("yxtj_dl").innerHTML = dlHtml;
					*/
					
					$("yxtj_div").style.display = "";
					var dlHtml = $("yxtj_dl").innerHTML;
					dlHtml+= "<dd id=\""+i+"_sjtj_yxtj_dd\">";
					dlHtml+= "<a href=\"#\" onclick=\"removeYxsjtj('"+i+"');return false;\">";
					dlHtml+= "<h5>"+obj.value+"</h5>";
					
					if (kssj.value != "" && jssj.value != ""){
						dlHtml += kssj.value;
						dlHtml += " 至 ";
						dlHtml += jssj.value;
					} else if (kssj.value == ""){
						dlHtml += "结束时间：";
						dlHtml += jssj.value;
					} else if (jssj.value == ""){
						dlHtml += "起始时间：";
						dlHtml += kssj.value;
					}
					dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
					$("yxtj_dl").innerHTML = dlHtml;
				}
			}
		}
	}

}
//设置已选择的数值区间
function setKsnumOrJsnum(){
	
	var ksnum_count = document.getElementsByName("search_tj_ksnum").length;
	var jsnum_count = document.getElementsByName("search_tj_jsnum").length;
	var nummc_count = document.getElementsByName("search_tj_nummc").length;
	
	//最小值
	if(ksnum_count>0){
		for(var i=0;i<ksnum_count;i++){
			var obj = document.getElementsByName("search_tj_ksnum")[i];
			if(obj.value != "" && document.getElementsByName("searchModel.search_tj_ksnum")
					&& document.getElementsByName("searchModel.search_tj_ksnum")[i]){
				document.getElementsByName("searchModel.search_tj_ksnum")[i].value = obj.value;
			}
		}
	}
	
	//最大值
	if(jsnum_count>0){
		for(var i=0;i<jsnum_count;i++){
			var obj = document.getElementsByName("search_tj_jsnum")[i];
			if(obj.value != "" && document.getElementsByName("searchModel.search_tj_jsnum")
					&& document.getElementsByName("searchModel.search_tj_jsnum")[i]){
				document.getElementsByName("searchModel.search_tj_jsnum")[i].value = obj.value;
			}
		}
	}
	
	//数值区间名称
	if(nummc_count>0){
		for(var i=0;i<nummc_count;i++){
			if(document.getElementsByName("search_tj_ksnum")[i] 
			   && document.getElementsByName("search_tj_ksnum")[i] 
			   && document.getElementsByName("search_tj_ksnum")[i]){
				var ksnum = document.getElementsByName("search_tj_ksnum")[i];
				var jsnum = document.getElementsByName("search_tj_jsnum")[i];
				var obj = document.getElementsByName("search_tj_nummc")[i];
				
				if((ksnum && ksnum.value != "") || (jsnum && jsnum.value != "")){
					/*
					$("yxtj_div").style.display = "";
		
					var dlHtml = $("yxtj_dl").innerHTML;
				
					dlHtml+= "<dd id=\""+i+"_numtj_yxtj_dd\">";
					dlHtml+= "<a href=\"#\" onclick=\"removeYxnumtj('"+i+"');return false;\">";
					dlHtml+= "<h5>"+obj.value+"</h5>已选择";
					dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
					
					$("yxtj_dl").innerHTML = dlHtml;
					 */
					
					$("yxtj_div").style.display = "";
					var dlHtml = $("yxtj_dl").innerHTML;
					dlHtml+= "<dd id=\""+i+"_numtj_yxtj_dd\">";
					dlHtml+= "<a href=\"#\" onclick=\"removeYxnumtj('"+i+"');return false;\">";
					dlHtml+= "<h5>"+obj.value+"</h5>";
					
					if (ksnum.value != "" && jsnum.value != ""){
						dlHtml += ksnum.value;
						dlHtml += " 至 ";
						dlHtml += jsnum.value;
					} else if (ksnum.value == ""){
						dlHtml += "最大值：";
						dlHtml += jsnum.value;
					} else if (jsnum.value == ""){
						dlHtml += "最小值：";
						dlHtml += ksnum.value;
					}
					
					dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
					$("yxtj_dl").innerHTML = dlHtml;
				}
			}
		}
	}
	
}

//构建已选择的时间条件显示层
function createKssjOrJssj(){
	// 时间控件对应名称
	var sjmc_count = document.getElementsByName("search_tj_sjmc").length;
	
	// 判断是否存在时间控件
	if(sjmc_count>0){
	
		for(var i=0;i<sjmc_count;i++){
		
			if(document.getElementsByName("searchModel.search_tj_kssj")[i] // 开始时间 是否存在对应对象
				&& document.getElementsByName("searchModel.search_tj_jssj")[i]// 结束时间 是否存在对应对象
				&& document.getElementsByName("search_tj_sjmc")[i]){ // 时间名称 是否存在对应对象
			
			var kssj = document.getElementsByName("searchModel.search_tj_kssj")[i];// 开始时间的值
			var jssj = document.getElementsByName("searchModel.search_tj_jssj")[i];// 结束时间的值
			var obj = document.getElementsByName("search_tj_sjmc")[i];// 条件名称的值
				
			// 判断 是否存在对应选中条件
			if (jQuery("#"+i+"_sjtj_yxtj_dd")){
				jQuery("#"+i+"_sjtj_yxtj_dd").remove();
			}
			
			
				// 仅创建开始或结束时间非空值的时间控件显示层
				if((kssj && kssj.value != "") || (jssj && jssj.value != "") ){
					
					//if($(i+"_sjtj_yxtj_dd")==null){
						$("yxtj_div").style.display = "";
						var dlHtml = $("yxtj_dl").innerHTML;
						dlHtml+= "<dd id=\""+i+"_sjtj_yxtj_dd\">";
						dlHtml+= "<a href=\"#\" onclick=\"removeYxsjtj('"+i+"');return false;\">";
						dlHtml+= "<h5>"+obj.value+"</h5>";
						
						if (kssj.value != "" && jssj.value != ""){
							dlHtml += kssj.value;
							dlHtml += " 至 ";
							dlHtml += jssj.value;
						} else if (kssj.value == ""){
							dlHtml += "结束时间：";
							dlHtml += jssj.value;
						} else if (jssj.value == ""){
							dlHtml += "起始时间：";
							dlHtml += kssj.value;
						}
						
						dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
						$("yxtj_dl").innerHTML = dlHtml;
						
					//}
				}
			}
		}
	}

}
//构建已选择的数值区间条件显示层
function createKsnumOrJsnum(){
	// 数值区间控件对应名称
	var nummc_count = document.getElementsByName("search_tj_nummc").length;
	
	// 判断是否存在数值区间控件
	if(nummc_count>0){
		
		for(var i=0;i<nummc_count;i++){
			
			if(document.getElementsByName("searchModel.search_tj_ksnum")[i] // 最小值 是否存在对应对象
			   && document.getElementsByName("searchModel.search_tj_jsnum")[i]// 最大值 是否存在对应对象
			   && document.getElementsByName("search_tj_nummc")[i]){ // 数值区间名称 是否存在对应对象
				
				var ksnum = document.getElementsByName("searchModel.search_tj_ksnum")[i];// 最小值
				var jsnum = document.getElementsByName("searchModel.search_tj_jsnum")[i];// 最大值
				var obj = document.getElementsByName("search_tj_nummc")[i];// 条件名称的值
				
				// 判断 是否存在对应选中条件
				if (jQuery("#"+i+"_numtj_yxtj_dd")){
					jQuery("#"+i+"_numtj_yxtj_dd").remove();
				}
				
				
				// 仅创建最小值或最大值非空值的数值区间控件显示层
				if((ksnum && ksnum.value != "") || (jsnum && jsnum.value != "") ){
					
					//if($(i+"_numtj_yxtj_dd")==null){
					$("yxtj_div").style.display = "";
					var dlHtml = $("yxtj_dl").innerHTML;
					dlHtml+= "<dd id=\""+i+"_numtj_yxtj_dd\">";
					dlHtml+= "<a href=\"#\" onclick=\"removeYxnumtj('"+i+"');return false;\">";
					dlHtml+= "<h5>"+obj.value+"</h5>";
					
					if (ksnum.value != "" && jsnum.value != ""){
						dlHtml += ksnum.value;
						dlHtml += " 至 ";
						dlHtml += jsnum.value;
					} else if (ksnum.value == ""){
						dlHtml += "最大值：";
						dlHtml += jsnum.value;
					} else if (jsnum.value == ""){
						dlHtml += "最小值：";
						dlHtml += ksnum.value;
					}
					
					dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
					$("yxtj_dl").innerHTML = dlHtml;
					
					//}
				}
			}
		}
	}
	
}

// 构建时间控件对应隐藏域
function createHidTime(node){
// 获取时间名称对象
	var obj = document.getElementsByName("search_tj_sjmc");
	// 判断是否存在 时间条件
	if(obj.length>0){
		
		for(var i=0;i<obj.length;i++){
			// 获取开始时间的值
			var kssj = document.getElementsByName("searchModel.search_tj_kssj")[i].value;
			// 获取结束时间的值
			var jssj = document.getElementsByName("searchModel.search_tj_jssj")[i].value;
			
			// ----------判断值是否为空 begin ----------
			if(kssj==null || kssj==""){
				kssj="!!DDBGG!!";// 空值处理
			}
			
			if(jssj==null || jssj==""){
				jssj="!!DDBGG!!";// 空值处理
			}
			// ----------判断值是否为空 end ----------
			
			// ----------构建隐藏域 begin---------------
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_kssj";
			tmp.value = kssj;
			node.appendChild(tmp);

			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_jssj";
			tmp.value = jssj;
			node.appendChild(tmp);
			// ----------构建隐藏域 end---------------
		}
	}	
}
// 构建数值区间控件对应隐藏域
function createHidNum(node){
// 获取数值区间名称对象
	var obj = document.getElementsByName("search_tj_nummc");
	// 判断是否存在 数值区间条件
	if(obj.length>0){
		
		for(var i=0;i<obj.length;i++){
			// 获取最小值
			var ksnum = document.getElementsByName("searchModel.search_tj_ksnum")[i].value;
			// 获取最大值
			var jsnum = document.getElementsByName("searchModel.search_tj_jsnum")[i].value;
			
			// ----------判断值是否为空 begin ----------
			if(ksnum==null || ksnum==""){
				ksnum="!!DDBGG!!";// 空值处理
			}
			
			if(jsnum==null || jsnum==""){
				jsnum="!!DDBGG!!";// 空值处理
			}
			// ----------判断值是否为空 end ----------
			
			// ----------构建隐藏域 begin---------------
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_ksnum";
			tmp.value = ksnum;
			node.appendChild(tmp);
			
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "searchModel.search_tj_jsnum";
			tmp.value = jsnum;
			node.appendChild(tmp);
			// ----------构建隐藏域 end---------------
		}
	}	
}

//创建已选中的条件
function creatClickedTjBySearch(){

	var input_num = $("searchTjDiv").getElementsByTagName('input').length;

	if(input_num != 0){
		
		for(var i=0;i<input_num;i++){
			var obj = $("searchTjDiv").getElementsByTagName('input')[i];
			var tjdm = obj.title;
			var dm = obj.value;

			if(tjdm != ""){
				//控件ID
				var a_id = "tj_" + tjdm+"_" + dm;
				var xs_id = tjdm+"_mc_xs_" + dm;
				var yc_id = tjdm+"_mc_yc_" + dm;
				
				if($(a_id)){
					$(a_id).onclick();
				}else if($(xs_id)){
					$(xs_id).onclick();
				}else if($(yc_id)){
					$(yc_id).onclick();
				}else{
					dwr.engine.setAsync(false);
					
					if(tjdm == "zy" || tjdm == "bj"){
						searchUtil.getBmpy(dm,tjdm,function(data){
							if(data != null && data != ""){
								var bmpy = data;
								
								var divid = "div_"+tjdm+"_"+bmpy;
								
								if(tjdm == "zy"){//部门为专业的话			
									
									clickZyQt(bmpy);
									hiddenZyDiv(divid);
									
									if($(yc_id)){
										$(yc_id).onclick();
									}
								}else if(tjdm == "bj"){//部门为班级的话		
									
									clickBjQt(bmpy);
									hiddenZyDiv(divid);
									if($(yc_id)){
										$(yc_id).onclick();
									}
								}
							}
						});
					}else if(tjdm == "qsh"){//寝室号
					
						var bmpy = dm.substring(0,1);
						var divid = "div_"+tjdm+"_"+bmpy;
						
						clickQshQt(bmpy);
						
						hiddenZyDiv(divid);
						if($(yc_id)){
							$(yc_id).onclick();
						}
					}
					
					dwr.engine.setAsync(true);
				}
			}
		}
	}
}

//创建已选中的条件
function creatClickedTj(tjdm,tjmc,dm,mc,obj){

	$("yxtj_div").style.display = "";
	
	var className = obj.className;
	var id = dm + "_" + tjdm + "_yxtj_dd";

	var dlHtml = $("yxtj_dl").innerHTML;

	if(className == "selectedValue"){//点击查询
		
		dlHtml+= "<dd id=\""+id+"\">";
		dlHtml+= "<a href=\"#\" onclick=\"removeYxtj('"+tjdm+"','"+dm+"');return false;\" name=\"a_name_"+tjdm+"\" id=\"a_id_"+dm+"\">";
		dlHtml+= "<h5>"+tjmc+"</h5>";
		dlHtml+= mc;
		dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
		$("yxtj_dl").innerHTML = dlHtml;
	}else{
		
		$("yxtj_dl").innerHTML = dlHtml;
		$(id).outerHTML = "";
	}
	
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
}

//批量查询条件构造
function creatClickedTjOfPlcx(tjdm,tjmc,dm,mc,tjid){
	$("tj_"+tjdm+"_"+tjdm).className = "selectedValue";
	var cxzd=tjid.split("_")[2];
	jQuery("#plcx_"+cxzd).val(dm);
	$("yxtj_div").style.display = "";
	var className = jQuery("#"+tjid).attr("class");
	var dmFormat = dm.replaceAll("\n",",").replaceAll(" ",",").split(",")[0];
	var id = dmFormat + "_" + tjdm + "_yxtj_dd";

	var dlHtml = $("yxtj_dl").innerHTML;
	
	if(className == "selectedValue"){//点击查询
		dlHtml+= "<dd id=\""+id+"\">";
		dlHtml+= "<a href=\"#\" onclick=\"removeYxtj('"+tjdm+"','"+dmFormat+"');return false;\" name=\"a_name_"+tjdm+"\" id=\"a_id_"+dmFormat+"\">";
		dlHtml+= "<h5>"+tjmc+"</h5>";
		dlHtml+= dmFormat;
		dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
		$("yxtj_dl").innerHTML = dlHtml;
	}else{
		$("yxtj_dl").innerHTML = dlHtml;
		$(id).outerHTML = "";
	}
	
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
}

//移除已选条件
function removeYxtj(tjdm,dm){

	var dd_id = dm + "_" + tjdm + "_yxtj_dd";
	var a_id = "tj_" + tjdm+"_" + dm;
	var pl_id = "tj_" + tjdm+"_" + tjdm;//批量查询
	var xs_id = tjdm+"_mc_xs_" + dm;
	var yc_id = tjdm+"_mc_yc_" + dm;
	
	if($(dd_id)){
		$(dd_id).outerHTML = "";
	}
	
	if($(pl_id)){
		jQuery("#plcx_"+tjdm).val("");
		$(pl_id).className="";
	}
	if($(a_id)){
		$(a_id).className="";
	}

	if($(xs_id)){
		$(xs_id).className="";
	}
	
	if($(yc_id)){
		$(yc_id).className="";
	}
	
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
	
	if(tjdm == "nj" || tjdm == "xy" || tjdm == "zy"){
		//存在班级条件
		if($("zybj_ul")){
			//联动班级
			//setNewBj();
			creatBmHtml("zybj");
			//清空班级其他DIV
			removeBjDiv();
		}
	}
	if(tjdm == "njNew" || tjdm == "xyNew" || tjdm == "zyNew"){
		//存在班级条件
		if($("bjNew_ul")){
			//联动班级
			//setNewBj();
			creatBmNewHtml("bjNew");
			//清空班级其他DIV
			removeBjNewDiv();
		}
	}

	if(tjdm == "xyNew"){
		//存在专业条件
		if($("zyNew_ul")){
			//联动专业
			//setNewZy();
			creatBmNewHtml("zyNew");
			//清空专业其他DIV
			removeZyNewDiv();
		}
	}
	if(tjdm == "xy"){
		//存在专业条件
		if($("zy_ul")){
			//联动专业
			//setNewZy();
			creatBmHtml("zy");
			//清空专业其他DIV
			removeZyDiv();
		}
	}
	
	if(tjdm == "xqdm"){
		//存在寝室号条件
		if($("yqdm_ul")){
			//联动班级
			creatGyHtml("yqdm");
			//清空层数已选条件
			removeYxtjByLd("yqdm");
		}
		//存在学院条件
		if($("xy_ul")){
			//联动学院
			creatGyHtml("xy");
			removeYxtjByLd("xy");
			removeYxtjByLd("zy");
			removeYxtjByLd("zybj");
		}
		//存在年级条件
		if($("nj_ul")){
			//联动年级
			creatGyHtml("nj");
			removeYxtjByLd("zy");
			removeYxtjByLd("zybj");
		}
	}	
	
	if(tjdm == "xqdm" || tjdm == "yqdm"){
		//存在寝室号条件
		if($("ld_ul")){
			//联动班级
			creatGyHtml("ld");
			//清空层数已选条件
			removeYxtjByLd("ld");
		}
		//存在学院条件
		if($("xy_ul")){
			//联动学院
			creatGyHtml("xy");
			removeYxtjByLd("xy");
			removeYxtjByLd("zy");
			removeYxtjByLd("zybj");
		}
		//存在年级条件
		if($("nj_ul")){
			//联动年级
			creatGyHtml("nj");
			removeYxtjByLd("zy");
			removeYxtjByLd("zybj");
		}
	}	
	
	if(tjdm == "xmmkdm"){
		var xmmk_click = getClickTj("xmmkdm");
		creatJfxmHtml("jfxmdm",xmmk_click);
	
	}
	if(tjdm == "ld" || tjdm == "xqdm" || tjdm == "yqdm"){
		//存在寝室号条件
		if($("ch_ul")){
			//联动班级
			creatGyHtml("ch");
			//清空层数已选条件
			removeYxtjByLd("ch");
		}
	}
	
	if(tjdm == "ld" || tjdm == "ch" || tjdm == "xqdm" || tjdm == "yqdm"){
		//存在寝室号条件
		if($("qsh_ul")){
			//联动班级
			creatGyHtml("qsh");
			//清空寝室号其他DIV
			removeQshDiv();
			//清空层数已选条件
			removeYxtjByLd("qsh");

		}
	}
	
	if(tjdm == "bid" || tjdm == "pid" || tjdm == "lid" || tjdm == "yid" || tjdm == "tid") {
		//存在宿舍条件
		if($("ssid_ul")){
			//联动宿舍
			createJxjzHtml("ssid");
			//清空已选条件
			removeYxtjByLd("ssid");

		}
	}
	
	if(tjdm == "pid" || tjdm == "lid" || tjdm == "yid" || tjdm == "tid") {
		//存在班条件
		if($("bid_ul")){
			//联动班
			createJxjzHtml("bid");
			//清空已选条件
			removeYxtjByLd("bid");

		}
	}
	
	if(tjdm == "lid" || tjdm == "yid" || tjdm == "tid"){
		//存在排条件
		if($("pid_ul")){
			//联动排
			createJxjzHtml("pid");
			//清空已选条件
			removeYxtjByLd("pid");

		}
	}
	
	if(tjdm == "yid" || tjdm == "tid"){
		//存在连条件
		if($("lid_ul")){
			//联动排
			createJxjzHtml("lid");
			//清空层数已选条件
			removeYxtjByLd("lid");

		}
	}
	
	if(tjdm == "tid"){
		//存在营条件
		if($("yid_ul")){
			//联动排
			createJxjzHtml("yid");
			//清空已选条件
			removeYxtjByLd("yid");

		}
	}
	
	if(tjdm == "gyjllbdldm"){
		//存在条件
		if($("gyjllbdm_ul")){
			//联动违纪类别
			creatWjHtml("gyjllbdm");
			//清空已选条件
			removeYxtjByLd("gyjllbdm");

		}
	}
}

//删除已选条件（时间）
function removeYxsjtj(num){
	
	var dd_id = num+"_sjtj_yxtj_dd";
	if($(dd_id)){
		$(dd_id).outerHTML = "";
	}
	
	var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
	var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
		
	//开始时间
	if(kssj_count>0)
	document.getElementsByName("searchModel.search_tj_kssj")[num].value = "";
	
	//结束时间
	if(jssj_count>0)
	document.getElementsByName("searchModel.search_tj_jssj")[num].value = "";
		
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
}

//删除已选条件（数值区间）
function removeYxnumtj(num){
	
	var dd_id = num+"_numtj_yxtj_dd";
	if($(dd_id)){
		$(dd_id).outerHTML = "";
	}
	
	var ksnum_count = document.getElementsByName("searchModel.search_tj_ksnum").length;
	var jsnum_count = document.getElementsByName("searchModel.search_tj_jsnum").length;
	
	//最小值
	if(ksnum_count>0)
		document.getElementsByName("searchModel.search_tj_ksnum")[num].value = "";
	
	//最大值
	if(jsnum_count>0)
		document.getElementsByName("searchModel.search_tj_jsnum")[num].value = "";
	
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
}

//因联动而删除已选条件
function removeYxtjByLd(dd_Lx){

	var dd_num = $("yxtj_div").getElementsByTagName('dd').length;

	for(var i=dd_num-1;i>=0;i--){
		var obj = $("yxtj_div").getElementsByTagName('dd')[i];
		var obj_id = obj.id;
		lx = obj_id.split("_")[1];
		if(lx == dd_Lx){
			obj.outerHTML = "";
		}
	}
	
	if($("yxtj_div").getElementsByTagName('dd').length == 0){
		$("yxtj_div").style.display = "none";
	}
}

//获得已选条件
function getYxtj(dd_Lx){

	var arr = new Array();
	var count = 0;
	
	var dd_num = $("yxtj_div").getElementsByTagName('dd').length;
	
	for(var i=dd_num-1;i>=0;i--){
		var obj = $("yxtj_div").getElementsByTagName('dd')[i];
		var obj_id = obj.id;
		var lx = obj_id.split("_")[1];
		var dm = obj_id.split("_")[0];
		if(lx == dd_Lx){
			arr[count]=dm;
			count++;
		}
	}
	
	return arr;
}

//隐藏已选条件DIV
function hiddenYxtjDiv(){

	var dd_num = $("yxtj_div").getElementsByTagName('dd').length;
	
	if(dd_num == 0){
		$("yxtj_div").style.display = "none";
	}
}
/** ===============================已选条件相关 end==============================*/

/** ===============================点击事件==================================*/
//高级查询项的收起，更多操作
function showTbody(obj,className1,className2,html1,html2){

	if(obj.className==className1){//收起
		obj.className=className2;
		obj.innerHTML=html2;
		$("gjcx_div").style.display = "none";
	}else{//更多
		obj.className=className1;
		obj.innerHTML=html1;
		$("gjcx_div").style.display = "";
	}
	parent.moreClick();
}

//查询条件展示，收起
function showHiddenBm(obj,tjdm,className1,className2,html1,html2){

	var py_num = "0";
	if($(tjdm+"_py_num")){
		py_num = $(tjdm+"_py_num").value;
	}

	var a_num = document.getElementsByName("a_"+tjdm+"_mc").length;

	var lx = "";
	
	if(obj.className==className1){//收起
		lx = "sq";
		obj.className=className2;
		obj.innerHTML=html2;
	}else{
		obj.className=className1; 
		lx = "zk";
	}

	//拼音
	for(var i=0;i<py_num;i++){
		var yc_id = tjdm+"_py_yc_"+i;
		if($(yc_id)){
			if(lx == "sq"){
				$(yc_id).style.display = "none";
			}else{
				$(yc_id).style.display = "";
			}
		}
	}
	
	//部门
	for(var i=0;i<a_num;i++){
		var bm_obj =  document.getElementsByName("a_"+tjdm+"_mc")[i];
		var bm_id = bm_obj.id;
		var show_lx = bm_id.split("_")[2];
		//只处理隐藏的部门
		if(show_lx == "yc"){
			if(bm_obj.style.display == "none"){
				bm_obj.style.display = "";
			}else{
				bm_obj.style.display = "none";
			}
		}
	}
	
	//其他			
	if(tjdm != "xy"){
	
		var hid_qt_num = document.getElementsByName(tjdm+"_hidv_qt").length;
		
		for(var i=0;i<hid_qt_num;i++){
			var obj = document.getElementsByName(tjdm+"_hidv_qt")[i];
			var bmdm = obj.value;
	
			//部门名称音ID
			var qt_id = tjdm+"_qt_"+bmdm;
			
			if(lx == "zk"){//展开
					
				if($(qt_id)){
					$(qt_id).style.display = "";
				}
						
			}else{//收起
	
				if($(qt_id)){
					$(qt_id).style.display = "none";
				}
			}
		}
				
	}
}

//查询条件展示，收起
function showHiddenOther(obj,tjdm,className1,className2,html1,html2){

	if(obj.className==className1){//收起
		lx = "sq";
		obj.className=className2;
		obj.innerHTML=html2;
	}else{
		obj.className=className1;
		obj.innerHTML=html1;
		lx = "zk";
	}

	var a_num = document.getElementsByName("tj_"+tjdm).length;
	
	//部门
	for(var i=0;i<a_num;i++){
		var tj_obj =  document.getElementsByName("tj_"+tjdm)[i];
		var tj_id = tj_obj.id;
		var show_lx = tj_id.split("_")[2];
		//只处理隐藏的部门
		if(show_lx == "yc"){
			if(tj_obj.style.display == "none"){
				tj_obj.style.display = "";
			}else{
				tj_obj.style.display = "none";
			}
		}
	}
}

//重置过滤条件
function czSuperSearch(){

	var del_message = "确定要删除所有的过滤条件?";
	
	confirmInfo(del_message, function(tag){
		if(tag == 'ok'){
			//重置模糊查询
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//重置高级查询
			$("yxtj_div").style.display = "none";
			$("yxtj_dl").innerHTML = "";
			if($("yxtj_gxh_div")){
				$("yxtj_gxh_div").style.display  = "none";
				$("yxtj_gxh_dl").innerHTML = "";
			}
			
			var a_num = document.getElementsByTagName('a').length;

			for(var i=0;i<a_num;i++){
				
				var a_className = document.getElementsByTagName('a')[i].className;
				
				if(a_className == "selectedValue"){
					document.getElementsByTagName('a')[i].className = "";
				}
			}
			
			var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
			var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
			
			//开始时间
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
				document.getElementsByName("searchModel.search_tj_kssj").value = "";
			}
			
			//结束时间
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
				document.getElementsByName("searchModel.search_tj_jssj").value = "";
			}
			
			var ksnum_count = document.getElementsByName("searchModel.search_tj_ksnum").length;
			var jsnum_count = document.getElementsByName("searchModel.search_tj_jsnum").length;
			
			//最小值
			for(var i=0;i<ksnum_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_ksnum")[i];
				obj.value = "";
				document.getElementsByName("searchModel.search_tj_ksnum").value = "";
			}
			
			//最大值
			for(var i=0;i<jsnum_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jsnum")[i];
				obj.value = "";
				document.getElementsByName("searchModel.search_tj_jsnum").value = "";
			}
		}
	});
}


//点击其他条件
function clickTj(lx,dm){

	if(lx == 'sy'){

		return false;
	}
	var id = "tj_"+lx+"_"+dm;
			
	if($(id)){

		var className = $(id).className;
		
		if(className == "selectedValue"){
			$(id).className = "";
		}else{
			$(id).className = "selectedValue";
		}
	}
	if(lx=='xmmkdm'){
		removeYxtjByLd("jfxmdm");
		var xmmk_click = getClickTj("xmmkdm");
		creatJfxmHtml("jfxmdm",xmmk_click);
	}
}
//点击批量查询条件
function clickPlcxTj(method,obj){
	if("selectedValue"==obj.className){
		obj.className="";
		var aName ="a_name_"+obj.id.split("_")[2]; 
		var dlHtml = $("yxtj_dl").innerHTML;
		$("yxtj_dl").innerHTML = dlHtml;
		var aid=jQuery("a[name="+aName+"]").parent().attr("id");
		$(aid).outerHTML = "";
		hiddenYxtjDiv();
		return;
	}
	var url = "seachTjManage.do?method="+method;
	var title = "批量查询学生";
	showDialog(title, 730,450, url);

}
function initPlcxXhData(){	
	
	demoHtml = "请按如下格式输入批量查询学号\n\n";
	demoHtml += "例如：\n";
	demoHtml += "20110019\n20100019\n20090026";
	demoHtml += "\n或者：\n";
	demoHtml += "20110019 20100019 20090026";
	
	jQuery("#plcxxh").val(demoHtml);

	jQuery("#plcxxh").css("color", "#999");
	jQuery("#plcxxh").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#plcxxh").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
}
function initPlcxXmData(){	
	xmdemoHtml = "请按如下格式输入批量查询姓名\n\n";
	xmdemoHtml += "例如：\n";
	xmdemoHtml += "张三\n李四\n王五";
	xmdemoHtml += "\n或者：\n";
	xmdemoHtml += "张三 李四 王五";

	jQuery("#plcxxm").val(xmdemoHtml);
	jQuery("#plcxxm").css("color", "#999");
	jQuery("#plcxxm").focus( function() {
		if (jQuery(this).val() == xmdemoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#plcxxm").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(xmdemoHtml);
			jQuery(this).css("color", "#999");
		}
	});
}
//点击其他条件
function clickOtherTj(lx,dm){

	var xsid = lx+"_mc_xs_"+dm;
	var ycid = lx+"_mc_yc_"+dm;
	var id = "";
	
	if($(xsid)){
		id = xsid;
	}
	
	if($(ycid)){
		id = ycid;
	}
	
	if($(id)){

		var className = $(id).className;
		
		if(className == "selectedValue"){
			$(id).className = "";
		}else{
			$(id).className = "selectedValue";
		}
	}

}

//点击年级
function clickNj(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//存在专业条件
	if($("zy_ul")){
		//联动专业
		//setNewZy();
		//creatBmHtml("zy");
		//清空专业其他DIV
		//removeZyDiv();
	}
	
	//存在班级条件
	if($("zybj_ul")){
		//联动班级
		//setNewBj();	
		creatBmHtml("zybj");
		//清空班级其他DIV
		removeBjDiv();
	}
	
	//清空专业已选条件
	removeYxtjByLd("zy");
	//清空班级已选条件
	removeYxtjByLd("zybj");
}

//点击学院
function clickXy(obj){
		
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}

	//存在专业条件
	if($("zy_ul")){
		//联动专业
		//setNewZy();
		creatBmHtml("zy");
		//清空专业其他DIV
		removeZyDiv();
	}

	//存在班级条件
	if($("zybj_ul")){
		//联动班级
		//setNewBj();
		creatBmHtml("zybj");
		//清空班级其他DIV
		removeBjDiv();
	}
	//清空专业已选条件
	removeYxtjByLd("zy");
	//清空班级已选条件
	removeYxtjByLd("zybj");
}
//点击专业
function clickZy(obj){
	
	var className = obj.className;

	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//存在班级条件
	if($("bj_ul")){
		//联动班级
		//setNewBj();
		creatBmHtml("zybj");
		//清空班级其他DIV
		removeBjDiv();
	}
	
	//清空班级已选条件
	removeYxtjByLd("bj");
}

//点击班级
function clickBj(obj){

	var className = obj.className;

	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
}

//点击专业其他
function clickZyQt(zypy){
	var divid = "div_zy_"+zypy;
	var qtid = "div_qt_zy_"+zypy;
	var hiid = "hi_"+zypy;
	var sfzxs="";
	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	var searchPath = jQuery("#searchPath").val();
	if(searchPath=="xsxx_xsxxgl_cxfzxs.do"){
		sfzxs="0";
	}
	if(searchPath=="xsxx_xsxxgl_cxzxs.do"){
		sfzxs="1";
	}
	var divHtml ="<div class='search_advanced'><div class='prop-item'  style='border:none !important'>";
		divHtml+="<table width='80%' border='0' class='formlist open01'>";
		divHtml+="<tbody><tr><td colspan='4'>";
		divHtml+="<font color='orange' size='3'>"+zypy+"字打头专业</font>";
		divHtml+="</td></tr>";
		
		//构造的tr数量
		var tr_num = "6";
		
		dwr.engine.setAsync(false);
				
		searchUtil.getZyInfoByPy(zypy,userStatus,userName,userDep,sfzxs,function(data){
		
			if(data !=null && data.length >0){
				
				var size = data.length;
				
				//数据量过大，以实际数据为准
				if((size/4) > tr_num){
					tr_num = parseInt(size/4);
					
					//最后一行非满格的数量
					var sy_num = parseInt(size%4);
					if(sy_num != "0"){
						tr_num =parseInt(tr_num)+1;
					}
				}
				
				var n = size;
				for(var i=0;i<tr_num;i++){
					
					divHtml+="<tr>";
					
					for(var j=0;j<4;j++){
					
						if(n <= size && n >= 0){
						
							n--;
							
							if(n!=-1){
							
								var zyid = "a_zy_"+data[n].zydm;
								var xsid = "zy_mc_xs_"+data[n].zydm;
								var ycid = "zy_mc_yc_"+data[n].zydm;
							
								divHtml+="<td width='25%'>";
								if(!$(xsid) && !$(ycid)){
									
									var zymc = data[n].zymc;

									if(zymc.length > 6){
										zymc = zymc.substring(0,6)+"...";
									}
									divHtml+="<li><a href='#' class='bg_none' name='a_zy_mc'id=\""+ycid+"\" style=\"white-space: nowrap;\"";
									divHtml+="title=\""+data[n].zymc+"\"";
									divHtml+="onclick=\"clickZy(this);";
									divHtml+="creatClickedTj('zy','专业','"+data[n].zydm+"','"+data[n].zymc+"',this);return false;\"";
									divHtml+=">"+zymc+"</a></li>";
								}else{
									divHtml+="&nbsp;";
								}
								divHtml+="</td>";
							
							}else{
								divHtml+="<td width='25%'>&nbsp;</td>";
							}	
						}else{
							divHtml+="<td width='25%'>&nbsp;</td>";
						}
					}
					
					divHtml+="</tr>";
				}
			}
		});
				
		dwr.engine.setAsync(true);
			
		divHtml+="</tbody>";
		divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
		divHtml+="<button onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
		divHtml+="</div></td></tr></tfoot>";
		divHtml+="</table></div></div>";
		
	createOtherDiv("专业选择",divHtml,divid,"600","380");

}

//点击班级其他
function clickBjQt(bjpy){
	var divid = "div_bj_"+bjpy;
	var qtid = "div_qt_bj_"+bjpy;
	var hiid = "hi_"+bjpy;
	var sfzxs="";
	var nj_click = getClickNj();//以选中年级
	var xy_click = getClickXy();//以选中学院
	var zy_click = getClickZy();//以选中专业
	
	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	var searchPath = jQuery("#searchPath").val();
	if(searchPath=="xsxx_xsxxgl_cxfzxs.do"){
		sfzxs="0";
	}
	if(searchPath=="xsxx_xsxxgl_cxzxs.do"){
		sfzxs="1";
	}
	var divHtml ="<div class='search_advanced'><div class='prop-item'  style='border:none !important'>";
		divHtml+="<table width='80%' border='0' class='formlist open01'>";
		divHtml+="<tbody><tr><td colspan='4'>";
		divHtml+="<font color='orange' size='3'>"+bjpy+"字打头班级</font>";
		divHtml+="</td></tr>";
		
		divHtml+="<tr><td colspan='4'>";
		divHtml+="<div style=\"height:260px;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";

		//构造的tr数量
		var tr_num = "6";
		
		dwr.engine.setAsync(false);
				
		searchUtil.getBjInfoByTj(bjpy,nj_click,xy_click,zy_click,userStatus,userName,userDep,sfzxs,function(data){
		
			if(data !=null && data.length >0){
				
				var size = data.length;
				
				//数据量过大，以实际数据为准
				if((size/2) > tr_num){
					tr_num = parseInt(size/2);
					
					//最后一行非满格的数量
					var sy_num = parseInt(size%2);
					if(sy_num != "0"){
						tr_num =parseInt(tr_num)+1;
					}
				}
				
				var n = size;
				
				for(var i=0;i<tr_num;i++){
					
					divHtml+="<tr style=\"width:100%;height:30px;\">";
					
					for(var j=0;j<2;j++){
					
						if(n <= size && n >= 0){
						
							n--;
							
							if(n!=-1){
							
								var bjid = "a_bj_"+data[n].bjdm;
								var xsid = "bj_mc_xs_"+data[n].bjdm;
								var ycid = "bj_mc_yc_"+data[n].bjdm;
							
								divHtml+="<td style=\"width:50%;\">";
								if(!$(xsid) && !$(ycid)){
									
									var bjmc = data[n].bjmc;

									if(bjmc.length > 20){
										bjmc = bjmc.substring(0,20)+"...";
									}
									divHtml+="<li><a href='#' class='bg_none' name='a_bj_mc'id=\""+ycid+"\" style=\"white-space: nowrap;\"";
									divHtml+="title=\""+data[n].bjmc+"\"";
									divHtml+="onclick=\"clickBj(this);";
									divHtml+="creatClickedTj('bj','班级','"+data[n].bjdm+"','"+data[n].bjmc+"',this);return false;\"";
									divHtml+=">"+bjmc+"</a></li>";
								}else{
									divHtml+="&nbsp;";
								}
								divHtml+="</td>";
							
							}else{
								divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
							}	
						}else{
							divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
						}
					}
					
					divHtml+="</tr>";
				}
			}
		});
				
		dwr.engine.setAsync(true);		
			
		divHtml+="</table></div>";
		divHtml+="</td></tr>";
		divHtml+="</tbody>";
		divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
		divHtml+="<button onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
		divHtml+="</div></td></tr></tfoot>";
		divHtml+="</table></div></div>";
		
	createOtherDiv("班级选择",divHtml,divid,"600","380");
}

//点击专业班级其他
function clickZyBjQt(bjpy){
    var divid = "div_zybj_"+bjpy;
    var qtid = "div_qt_zybj_"+bjpy;
    var hiid = "hi_"+bjpy;
    var sfzxs="";
    var nj_click = getClickNj();//以选中年级
    var xy_click = getClickXy();//以选中学院
    var zy_click = getClickZy();//以选中专业

    var userStatus = $("userStatus").value;
    var userName = $("userName").value;
    var userDep = $("userDep").value;
    var searchPath = jQuery("#searchPath").val();
    if(searchPath=="xsxx_xsxxgl_cxfzxs.do"){
        sfzxs="0";
    }
    if(searchPath=="xsxx_xsxxgl_cxzxs.do"){
        sfzxs="1";
    }
    var divHtml ="<div class='search_advanced'><div class='prop-item'  style='border:none !important'>";
    divHtml+="<table width='80%' border='0' class='formlist open01'>";
    divHtml+="<tbody><tr><td colspan='4'>";
    divHtml+="<font color='orange' size='3'>"+bjpy+"字打头班级</font>";
    divHtml+="</td></tr>";

    divHtml+="<tr><td colspan='4'>";
    divHtml+="<div style=\"height:260px;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";

    //构造的tr数量
    var tr_num = "6";

    dwr.engine.setAsync(false);

    searchUtil.getBjInfoByTj(bjpy,nj_click,xy_click,zy_click,userStatus,userName,userDep,sfzxs,function(data){

        if(data !=null && data.length >0){

            var size = data.length;

            //数据量过大，以实际数据为准
            if((size/2) > tr_num){
                tr_num = parseInt(size/2);

                //最后一行非满格的数量
                var sy_num = parseInt(size%2);
                if(sy_num != "0"){
                    tr_num =parseInt(tr_num)+1;
                }
            }

            var n = size;

            for(var i=0;i<tr_num;i++){

                divHtml+="<tr style=\"width:100%;height:30px;\">";

                for(var j=0;j<2;j++){

                    if(n <= size && n >= 0){

                        n--;

                        if(n!=-1){

                            var bjid = "a_zybj_"+data[n].bjdm;
                            var xsid = "zybj_mc_xs_"+data[n].bjdm;
                            var ycid = "zybj_mc_yc_"+data[n].bjdm;

                            divHtml+="<td style=\"width:50%;\">";
                            if(!$(xsid) && !$(ycid)){

                                var bjmc = data[n].bjmc;

                                if(bjmc.length > 20){
                                    bjmc = bjmc.substring(0,20)+"...";
                                }
                                divHtml+="<li><a href='#' class='bg_none' name='a_zybj_mc'id=\""+ycid+"\" style=\"white-space: nowrap;\"";
                                divHtml+="title=\""+data[n].bjmc+"\"";
                                divHtml+="onclick=\"clickBj(this);";
                                divHtml+="creatClickedTj('zybj','班级','"+data[n].bjdm+"','"+data[n].bjmc+"',this);return false;\"";
                                divHtml+=">"+bjmc+"</a></li>";
                            }else{
                                divHtml+="&nbsp;";
                            }
                            divHtml+="</td>";

                        }else{
                            divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
                        }
                    }else{
                        divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
                    }
                }

                divHtml+="</tr>";
            }
        }
    });

    dwr.engine.setAsync(true);

    divHtml+="</table></div>";
    divHtml+="</td></tr>";
    divHtml+="</tbody>";
    divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
    divHtml+="<button onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
    divHtml+="</div></td></tr></tfoot>";
    divHtml+="</table></div></div>";

    createOtherDiv("班级选择",divHtml,divid,"600","380");
}

/** ===============================点击事件 end===============================*/

/** ===============================联动================================*/

//生成新的专业
function setNewZy(){

	var nj_click = getClickNj();
	var xy_click = getClickXy();

	var noxy = false;

	if(xy_click.length == 0){
		noxy = true;
	}
	
	dwr.engine.setAsync(false);
			
	var divHtml = "<ul id=\"zy_ul\">";

	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	//根据学院代码取得专业
	searchUtil.getZyInfoByXy(nj_click,xy_click,userStatus,userName,userDep,function(data){
		if(data !=null && data.length >0){
			
			var zy_count = 0;
			var zy_py_num = 0;
			
			//循环拼音
			for(var i=0;i<PY_BIG.length;i++){
			
				var	flag = true;
				var over_flag = false;
				var qt_flag = true;
				var qt_num = 0;
				
				//专业内容列表
				for(var j=0;j<data.length;j++){
					
					if(PY_BIG[i] == data[j].zypy){
					
						var qi_id = "zy_qt_zy_"+PY_BIG[i];

						zy_py_num++;
						
						if(!noxy){//选中学院
							if(zy_count < 12){
								
								if(flag){
								
									divHtml += "<li>";
									
									divHtml += "<h5 id=\"zy_py_xs_";
									divHtml += zy_py_num;
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;
									flag = false;
								}
							
								divHtml += " <a href=\"#\" class=\"\" name=\"a_zy_mc\" ";
								divHtml += " id=\"zy_mc_xs_";
								divHtml += data[j].zydm;
								divHtml += "\"";
								divHtml += "onclick=\"clickZy(this);";
								divHtml += "creatClickedTj('zy','专业','"+data[j].zydm+"','"+data[j].zymc+"',this);return false;\">";
								divHtml += data[j].zymc;
								divHtml += "</a>";
								
							}else{	
								if(flag){
									divHtml += "<li>";
												
									divHtml += "<h5 style=\"display:none\" id=\"zy_py_yc_";
									divHtml += zy_py_num;
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;
									flag = false;
								}					
								
								divHtml += " <a href=\"#\" class=\"\" name=\"a_zy_mc\" ";
								divHtml += " id=\"zy_mc_yc_";
								divHtml += data[j].zydm;
								divHtml += "\" style=\"display:none;\"";
								divHtml += "onclick=\"clickZy(this);";
								divHtml += "creatClickedTj('zy','专业','"+data[j].zydm+"','"+data[j].zymc+"',this);return false;\">";
								divHtml += data[j].zymc;
								divHtml += "</a>";
								
								divHtml += "<input type=\"hidden\" name=\"zy_hidv_yc\"";
								divHtml += "id=\"xy_hidv_yc_";
								divHtml += data[j].zydm;
								divHtml += "\" value=\"";
								divHtml += data[j].zydm;
								divHtml += "\"/>";
							}
						}else{//未选中学院
						
							if(zy_count < 12){
								
								if(flag){
									divHtml += "<li>";
									
									divHtml += "<h5 id=\"zy_py_xs_";
									divHtml += zy_py_num;
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;
									flag = false;
								}
							
								if(qt_flag){
									divHtml += " <a href=\"#\" class=\"\" name=\"a_zy_mc\" ";
									divHtml += " id=\"zy_mc_xs_";
									divHtml += data[j].zydm;
									divHtml += "\"";
									divHtml += "onclick=\"clickZy(this);";
									divHtml += "creatClickedTj('zy','专业','"+data[j].zydm+"','"+data[j].zymc+"',this);return false;\">";
									divHtml += data[j].zymc;
									divHtml += "</a>";
																				
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										zy_count++;
										zy_count++;
										
										divHtml += "<a href=\"#\" class=\"moreValue_click\" onclick=\"clickZyQt(\'";
										divHtml += data[j].zypy;
										divHtml += "\')\"";
										divHtml += "id=\"";
										divHtml += qi_id;
										divHtml += "\">更多</a>";							
										
										qt_num = 0;
										
										qt_flag = false;
															
									}
								}														
							}else{
							
								if(flag){
									divHtml += "<li>";
									
									divHtml += "<h5 style=\"display:none\" id=\"zy_py_yc_";
									divHtml += zy_py_num;
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;
									flag = false;
								}
							
								if(qt_flag){
									divHtml += " <a href=\"#\" class=\"\" name=\"a_zy_mc\" ";
									divHtml += " id=\"zy_mc_yc_";
									divHtml += data[j].zydm;
									divHtml += "\" style=\"display:none;\"";
									divHtml += "onclick=\"clickZy(this);";
									divHtml += "creatClickedTj('zy','专业','"+data[j].zydm+"','"+data[j].zymc+"',this);return false;\">";
									divHtml += data[j].zymc;
									divHtml += "</a>";
										
									divHtml += "<input type=\"hidden\" name=\"zy_hidv_yc\" id=\"zy_hidv_yc_";
									divHtml += data[j].zydm;
									divHtml += "\" value=\"";
									divHtml += data[j].zydm;
									divHtml += "\"/>";
								
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										zy_count++;
										zy_count++;
																
										divHtml += "<a href=\"#\" class=\"moreValue_click\" style=\"display:none;\"onclick=\"clickZyQt(\'";
										divHtml += data[j].zypy;
										divHtml += "\')\"";
										divHtml += "id=\"";
										divHtml += qi_id;
										divHtml += "\">更多</a>";
										
										divHtml += "<input type=\"hidden\" name=\"zy_hidv_qt\" id=\"zy_hidv_qt_";
										divHtml += data[j].zydm;
										divHtml += "\" value=\"zy_";
										divHtml += data[j].zypy;
										divHtml += "\"/>";
										
										qt_num = 0;
										
										qt_flag = false;
															
									}
								}
							}
						}
						
						if(qt_flag){
							zy_count++;
						}
						
					}
				}	
				
				if(over_flag){
					divHtml+="</li>";
					over_flag = false;
				}	
			}

			if(zy_count < 12){
				if($("zy_more")){
					$("zy_more").style.display = "none";
				}
			}else{
				$("zy_more").style.display = "";
				$("zy_more").innerHTML = "更多";
				//$("zy_more").innerHTML = "展开";
				$("zy_more").className = "more_down";
			}
		
			divHtml += "<input type=\"hidden\" id=\"zy_py_num\" value=\""+zy_py_num+"\"/>";
	
		}
	});

	divHtml += "</ul>";

	$("zy_ul").outerHTML = divHtml;	

	dwr.engine.setAsync(true);

}

//生成新的班级
function setNewBj(){

	var nj_click = getClickNj();//选中的年级
	var xy_click = getClickXy();//选中的学院
	var zy_click = getClickZy();//选中的专业


	var noTj = false;

	//判断是否有相关联的条件被选中
	if(nj_click.length == 0 && xy_click.length == 0 && zy_click.length == 0){
		noTj = true;
	}
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"bj_ul\">";

	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	//根据学院代码取得班级
	searchUtil.getBjInfoByTj("",nj_click,xy_click,zy_click,userStatus,userName,userDep,function(data){
	
		if(data !=null && data.length >0){
				
			var bj_count = 0;
			var bj_py_num = 0;

			//循环拼音
			for(var i=0;i<PY_BIG.length;i++){
			
				var	flag = true;
				over_flag = false;
				var qt_flag = true;
				var qt_num = 0;
				
				//班级内容列表
				for(var j=0;j<data.length;j++){
					
					if(PY_BIG[i] == data[j].bjpy){
					
						var qi_id = "bj_qt_bj_"+PY_BIG[i];

						bj_py_num++;
						
							if(bj_count < 12){
								
								if(flag){
								
									divHtml += "<li>";
									
									divHtml += "<h5 id=\"bj_py_xs_";
									divHtml += bj_py_num;
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;								
									flag = false;
								}
							
								if(qt_flag){
									divHtml += " <a href=\"#\" class=\"\" name=\"a_bj_mc\" ";
									divHtml += " id=\"bj_mc_xs_";
									divHtml += data[j].bjdm;
									divHtml += "\"";
									divHtml += "onclick=\"clickBj(this);";
									divHtml += "creatClickedTj('bj','班级','"+data[j].bjdm+"','"+data[j].bjmc+"',this);return false;\">";
									divHtml += data[j].bjmc;
									divHtml += "</a>";
																				
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										bj_count++;
										bj_count++;
										
										var bj_num = getQtBjNum(data[j].bjpy);

										if(bj_num >3){
											divHtml += "<a href=\"#\" class=\"moreValue_click\" onclick=\"clickBjQt(\'";
											divHtml += data[j].bjpy;
											divHtml += "\')\"";
											divHtml += "id=\"";
											divHtml += qi_id;
											divHtml += "\">更多</a>";
										}
										qt_num = 0;
										
										qt_flag = false;
															
									}
								}														
							}else{
							
								if(flag){
								
									divHtml += "<li>";
									
									divHtml += "<h5 style=\"display:none\" id=\"bj_py_yc_";
									divHtml += bj_py_num;
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;										
									flag = false;
								}
							
								if(qt_flag){
								
									divHtml += " <a href=\"#\" class=\"\" name=\"a_bj_mc\" style=\"display:none;\"";
									divHtml += " id=\"bj_mc_yc_";
									divHtml += data[j].bjdm;
									divHtml += "\"";
									divHtml += "onclick=\"clickBj(this);";
									divHtml += "creatClickedTj('bj','班级','"+data[j].bjdm+"','"+data[j].bjmc+"',this);return false;\">";
									divHtml += data[j].bjmc;
									divHtml += "</a>";			
										
									divHtml += "<input type=\"hidden\" name=\"bj_hidv_yc\" id=\"bj_hidv_yc_";
									divHtml += data[j].bjdm;
									divHtml += "\" value=\"";
									divHtml += data[j].bjdm;
									divHtml += "\"/>";
								
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										bj_count++;
										bj_count++;
										
										divHtml += "<a href=\"#\" style=\"display:none;\" class=\"moreValue_click\" onclick=\"clickBjQt(\'";
										divHtml += data[j].bjpy;
										divHtml += "\')\"";
										divHtml += "id=\"";
										divHtml += qi_id;
										divHtml += "\">更多</a>";														
										
										divHtml += "<input type=\"hidden\" name=\"bj_hidv_qt\" id=\"bj_hidv_qt_";
										divHtml += data[j].bjdm;
										divHtml += "\" value=\"bj_";
										divHtml += data[j].bjpy;
										divHtml += "\"/>";
										
										qt_num = 0;
										
										qt_flag = false;
															
									}
								}
							}
						
						if(qt_flag){
							bj_count++;
						}
						
					}

				}	
				
				if(over_flag){
					divHtml+="</li>";
					over_flag = false;
				}		
			}

			if(bj_count <= 12){
				if($("bj_more")){
					$("bj_more").style.display = "none";
				}
			}else{
				$("bj_more").style.display = "";
				$("bj_more").innerHTML = "更多";
				//$("bj_more").innerHTML = "展开";
				$("bj_more").className = "more_down";
			}
			
			divHtml += "<input type=\"hidden\" id=\"bj_py_num\" value=\""+bj_py_num+"\"/>";
		}
	});
			
	divHtml += "</ul>";
	$("bj_ul").outerHTML = divHtml;	
	
	dwr.engine.setAsync(true);
	
}

//获得其他班级数量
function getQtBjNum(bjpy){
	var divid = "div_bj_"+bjpy;
	var qtid = "div_qt_bj_"+bjpy;
	var hiid = "hi_"+bjpy;
	
	var nj_click = getClickNj();//以选中年级
	var xy_click = getClickXy();//以选中学院
	var zy_click = getClickZy();//以选中专业
	
	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	var num = 0;
	dwr.engine.setAsync(false);
				
	searchUtil.getBjInfoByTj(bjpy,nj_click,xy_click,zy_click,userStatus,userName,userDep,function(data){
		
		if(data !=null && data.length >0){
			num = data.length;
		}
	});
		
	dwr.engine.setAsync(true);
	
	return num;
}
/** ===============================联动 end================================*/

/** ===============================相关方法================================*/

//生成层	
function createOtherDiv(title,divHtml,divid,width,height){
	var isbackDiv = true;
	if($(divid)==null){//显示操作区 
		var floatDiv = document.createElement('div');
		floatDiv.id = divid;
		floatDiv.style.position = "absolute";
		floatDiv.style.width = width;
		floatDiv.style.height = height;
		floatDiv.style.backgroundColor = "#FFFFFF";
		floatDiv.style.overflow = "hidden";
		floatDiv.style.border = "1px solid rgb(167, 189, 211)";
		
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight;
		var d_left_top = (d_width - width) / 2 +"px";
		var d_top_top =  "100" + "px";
		
		floatDiv.style.top = d_top_top;
		floatDiv.style.left = d_left_top;
		floatDiv.style.zIndex  = 1001;
		
	
		//temp_divHtml=divHtml;
		
		var dd_html="";
		dd_html += "<div id=\"windown-title\" align='left'><h2>"+title+"</h2>";
		dd_html += "<span id=\"windown-close\" onclick=\"hiddenZyDiv('"+divid+"');return false;\">确定</span>";
		dd_html += "</div>";
		dd_html += "<div id=\"open_win\" class=\"open_win01\" style=\"padding-top:5px;padding-bottom:5px;\">";
		floatDiv.innerHTML = dd_html+divHtml+"</div>";
		//document.getElementById(divId).innerHTML = "";
		//document.body.removeAttribute($(divId))
		document.forms[0].appendChild(floatDiv);
	}else{
		$(divid).style.display = "block";
	}
	
	if($('##backDiv##')==null && isbackDiv ==true){//显示背景  
	
		var backDiv = document.createElement('div');
		backDiv.id = "##backDiv##";
		backDiv.style.backgroundColor = "Black";
		backDiv.style.filter = "alpha(opacity=60)";
		backDiv.style.MozOpacity = "0.70";
		backDiv.style.position = "absolute";
		backDiv.style.left = "0px";
		backDiv.style.top = "0px";
		backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth);
		var t_height = Math.max(document.body.scrollHeight, document.body.clientHeight);
		if(t_height <800){
			t_height = 800; 
		}
		backDiv.style.height = t_height+"px";
		document.body.appendChild(backDiv);
		$('##backDiv##').style.xIndex = 1000;
	}else if(isbackDiv ==true){
		$('##backDiv##').style.display = "block";
	}
	
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr;
		var splitName;
		
		if ($(id)){
			arr = id.split('_');
		}
		
		if (null != arr && arr.length>0){
			splitName=arr[0];
		}
		
		if (splitName!="select"){
			obj.style.display = "none";
		}
		
	}
}

//清空专业其他层
function removeZyDiv(){
	//循环拼音
	for(var i=0;i<PY_BIG.length;i++){
		var id = "div_zy_"+PY_BIG[i];
		if($(id)){
			//清空已选条件
			var a_num = $(id).getElementsByTagName('a').length;
			for(var j=0;j<a_num;j++){
				var obj = $(id).getElementsByTagName('a')[j];
				if(obj.name == "a_zy_mc"){
					var a_id = obj.id;
					var zydm = a_id.substring(9,obj.id.length);
					var dd_id = zydm + "_zy_yxtj_dd";
					
					if($(dd_id)){
						$(dd_id).outerHTML = "";
					}
				}
			}
			//清空层
			$(id).outerHTML = "";
		}
	}
}

//清空班级其他层
function removeBjDiv(){
	//循环拼音
	for(var i=0;i<PY_BIG.length;i++){
		var id = "div_bj_"+PY_BIG[i];
		if($(id)){
			//清空已选条件
			var a_num = $(id).getElementsByTagName('a').length;
			for(var j=0;j<a_num;j++){
				var obj = $(id).getElementsByTagName('a')[j];
				if(obj.name == "a_bj_mc"){
					var a_id = obj.id;
					var zydm = a_id.substring(9,obj.id.length);
					var dd_id = zydm + "_bj_yxtj_dd";
					
					if($(dd_id)){
						$(dd_id).outerHTML = "";
					}
				}
			}
			//清空层
			$(id).outerHTML = "";
		}
	}
}

//隐藏专业层
function hiddenZyDiv(id){

	$(id).style.display="none";
	
	if($('##backDiv##')!=null){ 
		$('##backDiv##').style.display = "none";
	}
	
	i = document.getElementsByTagName("select").length;

	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
		document.getElementsByTagName("select")[j].style.display = "";
	} 
}
/** ===============================相关方法 end================================*/

//点击学院
function clickXyNew(obj){
		
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}

	//存在专业条件
	if($("zyNew_ul")){
		//联动专业
		//setNewZy();
		creatBmNewHtml("zyNew");
		//清空专业其他DIV
		removeZyNewDiv();
	}

	//存在班级条件
	if($("bjNew_ul")){
		//联动班级
		//setNewBj();
		creatBmNewHtml("bjNew");
		//清空班级其他DIV
		removeBjNewDiv();
	}

	//清空专业已选条件
	removeYxtjByLd("zyNew");
	//清空班级已选条件
	removeYxtjByLd("bjNew");
}


//清空专业其他层
function removeZyNewDiv(){
	//循环拼音
	for(var i=0;i<PY_BIG.length;i++){
		var id = "div_zyNew_"+PY_BIG[i];
		if($(id)){
			//清空已选条件
			var a_num = $(id).getElementsByTagName('a').length;
			for(var j=0;j<a_num;j++){
				var obj = $(id).getElementsByTagName('a')[j];
				if(obj.name == "a_zyNew_mc"){
					var a_id = obj.id;
					var zydm = a_id.substring(9,obj.id.length);
					var dd_id = zydm + "_zyNew_yxtj_dd";
					
					if($(dd_id)){
						$(dd_id).outerHTML = "";
					}
				}
			}
			//清空层
			$(id).outerHTML = "";
		}
	}
}

//清空班级其他层
function removeBjNewDiv(){
	//循环拼音
	for(var i=0;i<PY_BIG.length;i++){
		var id = "div_bjNew_"+PY_BIG[i];
		if($(id)){
			//清空已选条件
			var a_num = $(id).getElementsByTagName('a').length;
			for(var j=0;j<a_num;j++){
				var obj = $(id).getElementsByTagName('a')[j];
				if(obj.name == "a_bjNew_mc"){
					var a_id = obj.id;
					var zydm = a_id.substring(9,obj.id.length);
					var dd_id = zydm + "_bj_yxtjNew_dd";
					
					if($(dd_id)){
						$(dd_id).outerHTML = "";
					}
				}
			}
			//清空层
			$(id).outerHTML = "";
		}
	}
}
//点击专业
function clickZyNew(obj){
	
	var className = obj.className;

	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//存在班级条件
	if($("bjNew_ul")){
		//联动班级
		//setNewBj();
		creatBmNewHtml("bjNew");
		//清空班级其他DIV
		removeBjDiv();
	}
	
	//清空班级已选条件
	removeYxtjByLd("bjNew");
}

//点击班级
function clickBjNew(obj){

	var className = obj.className;

	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
}


//联动部门
function creatBmNewHtml(bmlx){
	
	var ul_id = bmlx+"_ul";
	
	var nj_click = getClickNjNew();
	var xy_click = getClickXyNew();
	var zy_click = getClickZyNew();
	var v4Path = stylePath;

	//参数
 	var parameter = {
 		"njNew":nj_click.join("!!@@!!"),
 		"xyNew":xy_click.join("!!@@!!"),
 		"zyNew":zy_click.join("!!@@!!"),
 		"bmlx":bmlx,
 		"stylePath":v4Path
	};
	var url="seachTjManage.do?method=creatBmNewHtml";

	jQuery.ajaxSetup({async:false});
	
	if($(ul_id)){
		jQuery("#"+ul_id).load(url,parameter,function(){
		
			var bm_num_id = bmlx+"_num";
			var bm_more_id = bmlx+"_more";
			var num = $(bm_num_id).value;
			
			if(num < 12){
				if($(bm_more_id)){
					$(bm_more_id).style.display = "none";
				}
			}else{
				$(bm_more_id).style.display = "";
				$(bm_more_id).innerHTML = "更多";
				$(bm_more_id).className = "more_down";
			}
		});
	}
	
	jQuery.ajaxSetup({async:true});	
}

//获得选中年级
function getClickNjNew(){

	//年级
	var nj_num = document.getElementsByName("tj_njNew").length;
	var nj_arr = new Array();
	var nj_count = 0;
	
	for(var i=0;i<nj_num;i++){
		var obj = document.getElementsByName("tj_njNew")[i];
		if(obj.className == "selectedValue"){
			var nj_v = obj.id.replace("tj_njNew_","");
			nj_arr[nj_count] = nj_v;
			nj_count++;
		}
	}
	
	return nj_arr;
}
//获得选中学院
function getClickXyNew(){

	//学院
	var xy_num = document.getElementsByName("a_xyNew_mc").length;
	var xy_arr = new Array();
	var xy_count = 0;

	for(var i=0;i<xy_num;i++){
		var obj = document.getElementsByName("a_xyNew_mc")[i];
		if(obj.className == "selectedValue"){
			var xydm_v = obj.id.substring(12,obj.id.length);
			xy_arr[xy_count] = xydm_v;
			xy_count++;
		}
	}
	return xy_arr;
}
//获得选中专业
function getClickZyNew(){

	//专业
	var zy_num = document.getElementsByName("a_zyNew_mc").length;
	var zy_arr = new Array();
	var zy_count = 0;

	for(var i=0;i<zy_num;i++){
		var obj = document.getElementsByName("a_zyNew_mc")[i];
		if(obj.className == "selectedValue"){
			var zydm_v = obj.id.substring(12,obj.id.length);
			zy_arr[zy_count] = zydm_v;
			zy_count++;
		}
	}
	
	return zy_arr;
}


//点击专业其他
function clickZyNewQt(zypy){
	var divid = "div_zyNew_"+zypy;
	var qtid = "div_qt_zyNew_"+zypy;
	var hiid = "hi_"+zypy;
	var userStatus = "";
	var userName = "";
	var userDep = "";
	var divHtml ="<div class='search_advanced'><div class='prop-item'  style='border:none !important'>";
		divHtml+="<table width='80%' border='0' class='formlist open01'>";
		divHtml+="<tbody><tr><td colspan='4'>";
		divHtml+="<font color='orange' size='3'>"+zypy+"字打头专业</font>";
		divHtml+="</td></tr>";
		
		//构造的tr数量
		var tr_num = "6";
		dwr.engine.setAsync(false);
		searchUtil.getZyNewInfoByPy(zypy,userStatus,userName,userDep,function(data){
		
			if(data !=null && data.length >0){
				
				var size = data.length;
				
				//数据量过大，以实际数据为准
				if((size/4) > tr_num){
					tr_num = parseInt(size/4);
					
					//最后一行非满格的数量
					var sy_num = parseInt(size%4);
					if(sy_num != "0"){
						tr_num =parseInt(tr_num)+1;
					}
				}
				
				var n = size;
				for(var i=0;i<tr_num;i++){
					
					divHtml+="<tr>";
					
					for(var j=0;j<4;j++){
					
						if(n <= size && n >= 0){
						
							n--;
							
							if(n!=-1){
							
								var zyid = "a_zyNew_"+data[n].zydm;
								var xsid = "zyNew_mc_xs_"+data[n].zydm;
								var ycid = "zyNew_mc_yc_"+data[n].zydm;
							
								divHtml+="<td width='25%'>";
								if(!$(xsid) && !$(ycid)){
									
									var zymc = data[n].zymc;

									if(zymc.length > 6){
										zymc = zymc.substring(0,6)+"...";
									}
									divHtml+="<li><a href='#' class='bg_none' name='a_zyNew_mc'id=\""+ycid+"\" style=\"white-space: nowrap;\"";
									divHtml+="title=\""+data[n].zymc+"\"";
									divHtml+="onclick=\"clickZyNew(this);";
									divHtml+="creatClickedTj('zyNew','专业','"+data[n].zydm+"','"+data[n].zymc+"',this);return false;\"";
									divHtml+=">"+zymc+"</a></li>";
								}else{
									divHtml+="&nbsp;";
								}
								divHtml+="</td>";
							
							}else{
								divHtml+="<td width='25%'>&nbsp;</td>";
							}	
						}else{
							divHtml+="<td width='25%'>&nbsp;</td>";
						}
					}
					
					divHtml+="</tr>";
				}
			}
		});
				
		dwr.engine.setAsync(true);
			
		divHtml+="</tbody>";
		divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
		divHtml+="<button onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
		divHtml+="</div></td></tr></tfoot>";
		divHtml+="</table></div></div>";
		
	createOtherDiv("专业选择",divHtml,divid,"600","380");

}

//点击班级其他
function clickBjNewQt(bjpy){
	var divid = "div_bj_"+bjpy;
	var qtid = "div_qt_bj_"+bjpy;
	var hiid = "hi_"+bjpy;
	
	var nj_click = getClickNjNew();//以选中年级
	var xy_click = getClickXyNew();//以选中学院
	var zy_click = getClickZyNew();//以选中专业
	
	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	var divHtml ="<div class='search_advanced'><div class='prop-item' style='border:none !important'>";
		divHtml+="<table width='80%' border='0' class='formlist open01'>";
		divHtml+="<tbody><tr><td colspan='4'>";
		divHtml+="<font color='orange' size='3'>"+bjpy+"字打头班级</font>";
		divHtml+="</td></tr>";
		
		divHtml+="<tr><td colspan='4'>";
		divHtml+="<div style=\"height:260px;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";

		//构造的tr数量
		var tr_num = "6";
		
		dwr.engine.setAsync(false);
				
		searchUtil.getBjNewInfoByTj(bjpy,nj_click,xy_click,zy_click,userStatus,userName,userDep,function(data){
		
			if(data !=null && data.length >0){
				
				var size = data.length;
				
				//数据量过大，以实际数据为准
				if((size/2) > tr_num){
					tr_num = parseInt(size/2);
					
					//最后一行非满格的数量
					var sy_num = parseInt(size%2);
					if(sy_num != "0"){
						tr_num =parseInt(tr_num)+1;
					}
				}
				
				var n = size;
				
				for(var i=0;i<tr_num;i++){
					
					divHtml+="<tr style=\"width:100%;height:30px;\">";
					
					for(var j=0;j<2;j++){
					
						if(n <= size && n >= 0){
						
							n--;
							
							if(n!=-1){
							
								var bjid = "a_bjNew_"+data[n].bjdm;
								var xsid = "bjNew_mc_xs_"+data[n].bjdm;
								var ycid = "bjNew_mc_yc_"+data[n].bjdm;
							
								divHtml+="<td style=\"width:50%;\">";
								if(!$(xsid) && !$(ycid)){
									
									var bjmc = data[n].bjmc;

									if(bjmc.length > 20){
										bjmc = bjmc.substring(0,20)+"...";
									}
									divHtml+="<li><a href='#' class='bg_none' name='a_bj_mc'id=\""+ycid+"\" style=\"white-space: nowrap;\"";
									divHtml+="title=\""+data[n].bjmc+"\"";
									divHtml+="onclick=\"clickBjNew(this);";
									divHtml+="creatClickedTj('bjNew','班级','"+data[n].bjdm+"','"+data[n].bjmc+"',this);return false;\"";
									divHtml+=">"+bjmc+"</a></li>";
								}else{
									divHtml+="&nbsp;";
								}
								divHtml+="</td>";
							
							}else{
								divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
							}	
						}else{
							divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
						}
					}
					
					divHtml+="</tr>";
				}
			}
		});
				
		dwr.engine.setAsync(true);		
			
		divHtml+="</table></div>";
		divHtml+="</td></tr>";
		divHtml+="</tbody>";
		divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
		divHtml+="<button onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
		divHtml+="</div></td></tr></tfoot>";
		divHtml+="</table></div></div>";
		
	createOtherDiv("班级选择",divHtml,divid,"600","380");
}


//点击年级
function clickNjNew(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//存在专业条件
	if($("zyNew_ul")){
		//联动专业
		//setNewZy();
		//creatBmHtml("zy");
		//清空专业其他DIV
		//removeZyDiv();
	}
	
	//存在班级条件
	if($("bjNew_ul")){
		//联动班级
		//setNewBj();	
		creatBmNewHtml("bjNew");
		//清空班级其他DIV
		removeBjNewDiv();
	}
	
	//清空专业已选条件
	removeYxtjByLd("zyNew");
	//清空班级已选条件
	removeYxtjByLd("bjNew");
}

//点击书院
function clickSy(obj){

    var className = obj.className;

    if(className == "selectedValue"){
        obj.className = "";
    }else{
        obj.className = "selectedValue";
    }
    //存在行政班级条件
    if($("bj_ul")){
        //联动班级
        //setNewBj();
        creatSyHtml("bj");
        //清空班级其他DIV
        removeBjDiv();
    }
    removeYxtjByLd("bj");
}

//获得选中书院
function getClickSy(){

    //书院
    var sy_num = document.getElementsByName("tj_sy").length;
    var sy_arr = new Array();
    var sy_count = 0;

    for(var i=0;i<sy_num;i++){
        var obj = document.getElementsByName("tj_sy")[i];
        if(obj.className == "selectedValue"){
            var sy_v = obj.id.replace("tj_sy_","");
            sy_arr[sy_count] = sy_v;
            sy_count++;
        }
    }

    return sy_arr;
}