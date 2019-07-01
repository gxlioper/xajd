	var api = frameElement.api;
	W = api.get('parentDialog');
function editOk(){
	//开始时间
	var kssj=jQuery("#kssj").val();
	var dxjy = jQuery("#dxjy").val();
	var jysj = jQuery("#zd1").val();
	var zjsydxjy = jQuery("#zjsydxjy").val();
	//检查时间是否合法
	if(!checkDate(kssj)){return false;}
	if("true"==dxjy&&compareDate(jysj,kssj)==2){
		showAlert("结业时间不能小于开始时间");
		return false;
	}
	W.jQuery(".edit").text(kssj);
	W.jQuery(".finish")
	//个人信息
	var grxj;
	//如果存在审批流程才操作个人小结
	var ishave=jQuery("#ishave").val();
	if(ishave=="true"){
		grxj=jQuery("#grxj").val();
		W.jQuery(".edit").parents("li").find("input[name='grxj']").val(grxj);
		zd5 = jQuery("#zd5").val();
		W.jQuery(".edit").parents("li").find("input[name='zd5']").val(zd5);
	}
	if((kssj!=""&&null!=kssj)||(grxj!=""&&null!=grxj)){
		W.jQuery(".edit").parents("li").attr("class","current");
	}
	//宁波理工党校结业操作
	
	if("true"==dxjy){
		W.jQuery(".edit").parents("li").find("input[name='zd1']").val(jQuery("#zd1").val());
		W.jQuery(".edit").parents("li").find("input[name='zd2']").val(jQuery("#zd2").val());
	}
	if("true"==zjsydxjy){
		W.jQuery(".edit").parents("li").find("input[name='zd8']").val(jQuery("#zd8").val());
		W.jQuery(".edit").parents("li").find("input[name='zd9']").val(jQuery("#zd9").val());
		W.jQuery(".edit").parents("li").find("input[name='zd10']").val(jQuery("#zd10").val());
	}
	W.jQuery(".edit").parents("li").find("input[name='zd3']").val(jQuery("#zd3").val());
	
	W.autoChange();
	W.jQuery(".edit").attr("class","finish");
	iFClose();
}
function editClose(){
	W.jQuery(".edit").attr("class","finish");
	iFClose();
}
function checkDate(nowDate){
	var check=true;
	//正在编辑的
	var li=W.jQuery(".edit").parents("li").eq(0);
	var nowtext=W.jQuery(".edit").prevAll("span").text();
	//正在编辑的阶段
	var jddm=W.jQuery(".edit").parents("li").find("input[name='jddm']").val();
	
	W.jQuery(".Join_party ul li").each(function(){
		var checkDate=jQuery(this).find("span[name='sj']").text();
		var checkJddm=jQuery(this).find("input[name='jddm']").val();
		var checkJdmc=jQuery(this).find("input[name='jdmc']").val();
		//比当前阶段靠后
		//if(checkJddm>jddm && !(checkJddm == '05' && jddm == '04')){
		if(checkJddm>jddm){
			if(compareDate(nowDate,checkDate)==1){
				showAlert("<font color='red'>["+nowtext+"]</font>的开始时间("+nowDate+")不能大于<font color='red'>["+checkJdmc+"]</font>的开始时间("+checkDate+").");
				check=false;
				return false;
			}
		}
		//比当前阶段考前
		//if(checkJddm<jddm && !(checkJddm == '04' && jddm == '05')){
		if(checkJddm<jddm){
			if(compareDate(nowDate,checkDate)==2){
				showAlert("<font color='red'>["+nowtext+"]</font>的开始时间("+nowDate+")不能小于<font color='red'>["+checkJdmc+"]</font>的开始时间("+checkDate+").");
				check=false;
				return false;
			}
		}
	});
	
	
/*	//检测是否大于之前阶段时间
	jQuery(li).nextAll().each(function(){
		var prevDate=jQuery(this).find("span[name='sj']").text();
		//alert(nowDate+":"+prevDate+":"+compareDate(nowDate,prevDate));
		if(compareDate(nowDate,prevDate)==2){
			var nowtext=W.jQuery(".edit").prevAll("span").text();
			var prevtext=jQuery(this).find("span[name='sj']").prevAll("span").text();
			showAlert("<font color='red'>["+nowtext+"]</font>的开始时间("+nowDate+")不能小于<font color='red'>["+prevtext+"]</font>的开始时间("+prevDate+").");
			check=false;
			return false;
		}
	});
	//判断是否小于后续阶段
	if(!check){return check;}
	jQuery(li).prevAll().each(function(){
		var nextDate=jQuery(this).find("span[name='sj']").text();
		//alert(nowDate+":"+prevDate+":"+compareDate(nowDate,prevDate));
		if(compareDate(nowDate,nextDate)==1){
			var nowtext=W.jQuery(".edit").prevAll("span").text();
			var nexttext=jQuery(this).find("span[name='sj']").prevAll("span").text();
			showAlert("<font color='red'>["+nowtext+"]</font>的开始时间("+nowDate+")不能大于<font color='red'>["+nexttext+"]</font>的开始时间("+nextDate+").");
			check=false;
			return false;
		}
	});*/
	return check;
}