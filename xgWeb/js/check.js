/**
 * 测试是否为半角下的数字
 */
function chkNumInput(obj){ 
    var inValue = $(obj).value;
    if(inValue!=""&&inValue.match(/^\d+\.{0,1}\d{0,3}$/)==null){			
	   return true;
	}else{
	   return false;
	}
}

/**
 * 把特殊不能作为主键的字符换成全角以便能做为主键保存
 * 
 * @param obj
 * @return
 */
function chgPkValue(obj){
	var value = obj.value;
	var key = event.keyCode;

	// 左右，放行
	if (key == 37 || key == 39){
		return true;
	}
	
	obj.value = value.replace(/%/g,"％").replace(/&/g,"＆")
					 .replace(/[+]/g,"＋").replace(/[#]/g,"＃")
					 .replace(/[']/g,"’").replace(/[(]/g,"（")
					 .replace(/[)]/g,"）").trim();
}

/**
 * 是否整数
 */
function isMobile(s){ 
	s = trim(s); 
	var p = /1\d{10}/; 
	return p.test(s);
}

/**
 * 如果值不为整数就置为空
 */
function checkInputData(obj){
	obj.value = obj.value.replace(/[^\d]/g,'').replace(/^(0)(.*)/g,'$1');
}
/**
 * 简单的联系方式验证
 */
function checkInputLxfx(obj){
	obj.value = obj.value.replace(/[^\d-]/g,'');
}
function checkABC(obj){
	if(!/^[A-Z]+$/.test(obj.value) ){
		obj.value ="";
	}
}

function checkInput(obj){
	obj.value = obj.value.replace(/[^\d]/g,'');
}

/**
 * 检测数字可以带小数点
 * 
 * @param obj
 * @return
 */
function checkInputNum(obj){
	 // 先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	// 必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g,"");
	// 保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	// 保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	
	if(obj.value.length > 1 && obj.value.substr(0,1)==0 && obj.value.substr(1,2) != '.'){  
		obj.value = parseFloat(obj.value);
		//obj.value = obj.value.replace(/\b(0+)/gi,"");
	} 
}
/**
 * 只能输入正整数和小数点后1位
 * 
 * @param obj
 * @return
 */
function clearNoNum(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是
    obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/, '$1$2.$3'); //只能输入两个小数
};
/**
 * 是否double类型的数值
 */
function isDouble(s){
			var p = /^\d+(?=\.{0,1}\d+$|$)/;
			return p.test(s); 
}

/**
 * 验证是否是eEmail格式
 */
function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
}


/*
 * 验证电话号码
 */
function checkPhone(obj) {
	var text = obj.value;
	if (null !== text && ''!= text) {
		if(text.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			showAlertDivLayer("您输入的电话不符合规则!",{},{"clkFun":function(){
				obj.focus();
			}});
		 return false;
	 }
	}
}

/*
 * 验证金额
 */
function checkMoney(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (!/^[0-9]\d*\.[0-9]{2}$|^[0-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text)) {
			
			showAlertDivLayer("您输入的数字不符合规则!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
	}
}

/*
 * 验证数字首位不为0
 */
function checkNumNotZero(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (!/^(0|[1-9][0-9]*)$/.test(text)) {
			obj.value="";

	}
	}
}



/*
 * 验证数字
 */
function checkInt(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (/[^\d]/.test(text)) {
			
			showAlertDivLayer("您输入的数字不符合规则!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
	}
}



/**
 * 判断学生是否存在
 */
function chkIsStu(obj,tableName,zd){
		
	var xh = obj.value;
			
	if(xh !=""){
			
		var colList = zd.split("-");
		var pk = "xh";
		var pkValue = xh;
	
		getDtjsInfo.getStuDefInfo(tableName, pk, pkValue,colList,function(data){
			if( data != null && data.length > 0){
				for (i = 0; i < colList.length; i++) {
					var id = colList[i];
					if($(id)){
						$(id).value = data[i];
					}
				}
			}else{
				alert("该学生不存在，请确认！");
				obj.focus();
			}
		});
	}
}

/**
 * 输入时的值是否数字判断，但是用中文输入法可以绕过?
 */
function chkInput(obj,evt){
	var evt=evt?evt:(window.event?window.event:null);
	var key = evt.keyCode;
	if((key>=48&&key<=57)||key==46||key==127||key==8||(key>=96&&key<=105)||key==110){
	}
	else{
		var data=obj.value;
		var reg = /^\d+\.{0,1}\d{0,3}$/;
		var role = data.match(reg);
		if(role==null&&data!=""){
			alert("请输入英文状态下的数字!");
            obj.value="0";
            return false;
        }
	}
}

/**
 * 只能输入退格键maxlength='20'
 */
function onlyBackSpace(obj,evt){
	var evt=evt?evt:(window.event?window.event:null);
	if(evt.keyCode == 8){
		obj.value = "";
		return true;
	}else {
		return false;
	}
}

/**
 * 只能输入数字限定
 */
function chkonlynum(evt) {
	var evt=evt?evt:(window.event?window.event:null);
	if ((evt.keyCode >=48) && (evt.keyCode<=57)) {
		evt.returnValue=true;
	}
	else {
		evt.returnValue=false;
	}
}

/**
 * 验证所有字段是否必填
 */
function checkAllInput(inputStr){
	var input = inputStr.split("!!");
	for(var i=0;i<input.length;i++){
		var temp = document.getElementById(input[i]);
		if(temp==null || temp.value==null || temp.value=="" || trim(temp.value)==""){
			alert("请将必填信息，填写完整！");
			return false;
		}
	}
	return true;
}

/**
 * 验证是否有值，无值则为0
 */
function checkNull(obj){
	if(jQuery.trim(obj.value) == ""){
		obj.value = "0";
	}
}

/**
 * 定义公用方法:检测子窗口
 */
function checkWinType() {
	if (window.dialogArguments == null || window.parent == null) {
		alert("非法访问，窗口即将关闭！");
		Close();
		return false;
	}
}

/**
 * 验证学年，年度是否一致
 */
function checkXnNd(xn, nd) {
	if (document.getElementById(xn).tagName.toLowerCase() == "select" && document.getElementById(nd).tagName.toLowerCase() == "select") {
		if (document.getElementById(xn).value == "") {
			alert("请选择学年、年度!");
			return false;
		}
		if (!inArray(document.getElementById(nd).value, document.getElementById(xn).value.split("-"))) {
			alert("学年、年度不一致,请检查您要保存的数据！");
			return false;
		}
	}
	return true;
}

/**
 * 把指定id的值放入divid里
 */
function textAreaFormat(id,divid){
	var value = document.getElementById(id).value;
	document.getElementById(divid).innerText = value;
}

/**
 * 判断输入是否汉字
 */
function checkInputIshz(obj) {
	if (obj.value !="" && !obj.value.match(/[^\u4e00-\u9fa5]/g)) {
	  alertInfo("只能输入字符,不能输入中文!");
	  obj.value = "";
	  return false;
  	}
}

/**
 * 判断输入是否汉字
 */
function CheckChinese(obj){     
	　　var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	　　if(reg.test(obj.value)){     
		showAlert("不能输入汉字！",{},{"clkFun":function(){
	       obj.value = "";  
		   obj.focus();     
	    }});
		      
  }
}

/**
 * 判断输入是否汉字
 */
function CheckChineseByString(s){
    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    if(reg.test(s)){
        alertInfo("不能输入中文!");
		return false;
    }
    return true;
}


/**
 * 增加页面中选择学号后，如果再次对学号进行修改那么则清空姓名，性别，年级等信息
 */
function checkXhExists(idArr) {
		if (idArr == null || idArr == '') {
			idArr = ['xm','xb','nj','xymc','zymc','bjmc'];
		} else {
			idArr = idArr.split("-");
		}
		for (var i = 0; i < idArr.length; i++) {
			if ($(idArr[i])) {
				document.getElementById(idArr[i]).value = "";
			}
		}
	}

/**
 * 判断学生是否存在
 */
function chkIsStu(obj,tableName,zd){
		
	var xh = obj.value;
			
	if(xh !=""){
			
		var colList = zd.split("-");
		var pk = "xh";
		var pkValue = xh;
		var query = "";
		
		getOtherData.getTableInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				for (i = 0; i < colList.length; i++) {
					var id = colList[i];
					if($(id)){
						$(id).value = data[i];
					}
				}
			}else{
				alert("该学生不存在，请确认！");
				obj.focus();
			}
		});
	}
}

/**
 * 传入checkName，把所有的和checkName的值相同name里值的checkbox全部选中 luning 2010-6-23
 */ 
function checkedALL(obj,checkName){
	   if(obj.checked){
      	 for(i=0;i<document.getElementsByName(checkName).length;i++){
      	   	document.getElementsByName(checkName)[i].checked=true;
         }
	   }else{
	   	 for(i=0;i<document.getElementsByName(checkName).length;i++){
      	   	document.getElementsByName(checkName)[i].checked=false;
         }
	   }
}

/**
 * 传入上传文件的id，和上传文件的类型，为空则默认为'.xls'类型 luning 2010-6-24
 */ 
function check_File(fileId,fileFormat) {
	// 文件路径
	var filePath= document.getElementById(fileId).value;
	if(fileFormat==""){
		fileFormat="xls";
	}
	if (filePath.split(".")[1]!=fileFormat) {
		alert("您选择的文件不合法，请重新选择！");
		document.getElementById(fileId).value = "";
		return false;
	}else{
		return true;
	}
}

/**
 * 弹出框时的检测是否有选中的行
 */
function checkTzhd(url, w, h) {
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {
		url +=  curr_row.cells[1].innerText;		
		showTopWin(url, w, h);	
	}	
}

/**
 * 检测数组中的元素是否有重复
 */
function checkArrayEleRepeat(array,splitStr){
	if(typeof array == "string"){
		array = array.split(splitStr);
	}
	for(var i=0; i<array.length; i++){
		for(var j=i+1; j<array.length-i+2; j++){
			if(array[i] == array[j]){
				return true;
			}
		}
	}
	return false;
}

/**
 * 传入拼接字符串,判断字符串所对应的数据是否为空
 */
function checkNotNull(str) {
	
	var result = true;
	
	var spt = str.split("-");
	for (var i = 0; i < spt.length; i++) {
		if(spt[i] != null && spt[i] != ""){
			//2015-09-28 修改人：yxy 判断前先trim()
			if (jQuery.trim(jQuery("#"+spt[i]).val()) == "") {
				result = false;
				break;
			}
		}
	}
	return result;
}

/**
 * 验证输入字数据不能超过限制字数(指定输入项)
 */
function checkLenN(lblname, obj, leng) {
	if(obj.value.length > leng){
		showAlert("【" + lblname + "】输入项最大字数为"+leng+",现已经超过，请确认！",{},{"clkFun":function(){
				obj.focus();
		}});
		// alert("字数过大，限"+leng+"字！");
	}
}

/**
 * 验证输入字数据不能超过限制字数
 */
function checkLen(obj, leng) {
	if(obj.value.length > leng){
		showAlert("该输入项最大字数为"+leng+",现已经超过，请确认！",{},{"clkFun":function(){
				obj.focus();
		}});
		// alert("字数过大，限"+leng+"字！");
	}
}

/**
 * 验证某项输入字数必须在leng1到leng2之间
 */
function checkLenBtwX(lblname,obj, leng1,leng2) {
	if(obj.value.length < leng1 || obj.value.length > leng2){
		showAlert("【" + lblname + "】输入项字数范围为"+leng1+"至"+leng2+"，请确认！",{},{"clkFun":function(){
				obj.focus();
		}});
	}
}

/**
 * 验证输入字数必须在leng1到leng2之间
 */
function checkLenBtw(obj, leng1,leng2) {

	if(obj.value.length < leng1||obj.value.length > leng2){
		showAlert("该输入项字数范围为"+leng1+"至"+leng2+"，请确认！",{},{"clkFun":function(){
				obj.focus();
		}});
	}
}

function chCount(obj,leng1,leng2) {
    var para = document.getElementById("chCount");
    if(para == null){
        para = document.createElement("div");
        para.id = "chCount";
    }
    para.innerText = "您已输入"+obj.value.length+"个字符";

    obj.parentNode.insertBefore(para,obj);
    if(obj.value.length < leng1||obj.value.length > leng2){
        para.style = "color:red";
    }else {
        para.style = "color:green";
    }
}

/**
 * 以前使用的批量录入的检测 检测文本框输入的字是否超限 未超返回TRUE ，反之 FALSE zdList 要检测文本模的ID列表， 格式 xh-xm-xb
 * zdmcList 提示信息中的中文列表 格式 学号-姓名-性别 zsnum 要检测文本模的字数的最大值 格式 正整数
 */
function checkTextAreaLength(zdList,zdmcList,zsnumList) {
	if (zdList == null ||zdmcList == null|| zsnumList == null) {
		return false;
	}
	var zdArray = zdList.split("-");
	var zdmcArray = zdmcList.split("-");
	var zsnumArray = zsnumList.split("-");
	for (var i=0;i<zdArray.length;i++) {
		if ($(zdArray[i])) {
			var textValue = document.getElementById(zdArray[i]).value;
			if (textValue != '' && textValue.length > zsnumArray[i]) {
				showAlert("输入" + zdmcArray[i] +"的字数过大,请控制在" + zsnumArray[i] + "以内.");
				return false;
			}
		}
	}
	return true;
}

/**
 * 使工具栏无效
 */
function disableChildren(obj) {
	if (obj) {
		obj.disabled = (!bEditMode);
		for (var i = 0; i < obj.children.length; i++) {
			disableChildren(obj.children[i]);
		}
	}
}

/**
 * 把页面上指定id(由ele拆成)的对象置为display luning 2010-6-24
 */
function isNotSee(ele,dis){
	var tmp = ele.split("-");      
	if(dis=="dis"){
	  for (i = 0; i < tmp.length; i++) {
		ocument.getElementById(tmp[i]).style.display = "none";
	  }
	}else{
	  for (i = 0; i < tmp.length; i++) {
		document.getElementById(tmp[i]).style.display = "";
	  }
	}		
}

/**
 * 判断是否选择有复选框
 */	
function isSelect(name) {
	var checkBoxArr = document.getElementsByName(name);
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (!flag) {
		alert("没有选择相应记录，请选择之后再进行操作！");
		return false;
	} else {
		return true;
	}
}


/** 定义公用方法:设定一种tab的类型为disable */
function setEleDisable(sTagName){
	var subNum = document.getElementsByTagName(sTagName).length;
	for(var i = 0; i < subNum; i++){
		document.getElementsByTagName(sTagName)[i].disabled = true;
	}
}

/**
 * 指定增加学号字段数据出处 luojw 2010-6-28
 */
 
// function sendXx(){
// //页面存隐藏域，指定所查询数据出处，例如（<input type="hidden" id="tableName" name="tableName"
// value="view_ybdyxx"/>）
// var tableName=$("tableName").value;
// //数据源的描述，展示jsp的表头，例如（<input type="hidden" id="lx" name="lx" value="预备党员"/>）
// var lx="";
// //数据源的特殊条件，例如:
// //<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
// //<input type="hidden" id="va" name="va" value="是-通过"/>
// var zd="";
// var va="";
// var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
//	
// if($("lx")){
// lx=$("lx").value
// };
// if($("zd")){
// zd=$("zd").value
// };
// if($("va")){
// va=$("va").value
// };
//	
// url+="&tableName="+tableName;
// if(lx !=""){
// url+="&lx="+lx;
// }
// if(zd !=""){
// url+="&zd="+zd;
// }
// if(va !=""){
// url+="&va="+va;
// }
// showTopWin(url,800,600);
// }

/**
 * 检测为空则置为0,用于提交数值 luojw 2010-6-28
 */

function checkNullInput(obj){
	if(obj.value == ""){
		obj.value = "0";
	}
}


/** 定义公用方法:检验身份证号码 */
function chkSfzh(obj) {
	
	if ("" == obj.value){
		return ;
	}
	
	var sfzh = obj.value.toLowerCase();
	var OldID;
	if(sfzh.length == 15){
/*
 * if(sfzh.substring(6,8)<'80'){
 * BatAlert.MyAlert("您输入的身份证号码是８０年以前的，请输入考生本人的身份证号码！","",function(){
 * obj.select(); obj.focus(); }); return false; }else{ OldID = sfzh; return
 * true; }
 */
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
/*
 * if(sfzh.substring(6,10)<'1980'){
 * BatAlert.MyAlert("您输入的身份证号码是１９８０年以前的，请输入考生本人的身份证号码！","",function(){
 * obj.select(); obj.focus(); }); return false; }else{ OldID = sfzh.substring(0,
 * 6) + sfzh.substring(8,sfzh.length-1); }
 */
		
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		showAlert("请输入正确的身份证号码！");
		obj.select();
		obj.focus();
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "x","9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		showAlert("请输入正确的身份证号码！");
		obj.select();
		obj.focus();
		return false;
	}
	return true;
}

/** 定义公用方法:检验身份证号码 */
function checkSfzh(obj) {
	var num = obj.value;
	if (num==''||num==null) return true;
    num = num.toUpperCase();  
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))   
    { 
    	showAlert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
    	obj.value="";
        return false; 
    } 
    // 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    // 下面分别分析出生日期和校验位
    var len, re; 
    len = num.length; 
    if (len == 15) 
    { 
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
        var arrSplit = num.match(re); 

        // 检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
        var bGoodDay; 
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
        if (!bGoodDay) 
        { 
            showAlert('输入的身份证号里出生日期不对！'); 
            obj.value="";
            return false; 
        } 
        else 
        { 
                // 将15位身份证转成18位
                // 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
                var nTemp = 0, i;   
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
                for(i = 0; i < 17; i ++) 
                { 
                    nTemp += num.substr(i, 1) * arrInt[i]; 
                } 
                num += arrCh[nTemp % 11];   
                return true;   
        }   
    } 
    if (len == 18) 
    { 
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
        var arrSplit = num.match(re); 

        // 检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
        var bGoodDay; 
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
        if (!bGoodDay) 
        { 
            showAlert('输入的身份证号里出生日期不对！'); 
        	obj.value="";
            return false; 
        } 
    else 
    { 
        // 检验18位身份证的校验码是否正确。
        // 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
        var valnum; 
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
        var nTemp = 0, i; 
        for(i = 0; i < 17; i ++) 
        { 
            nTemp += num.substr(i, 1) * arrInt[i]; 
        } 
        valnum = arrCh[nTemp % 11]; 
        if (valnum != num.substr(17, 1)) 
        { 
        	showAlert('输入的身份证号的最后一位不正确！应该为：' + valnum); 
        	obj.value="";
            return false; 
        } 
        return true; 
    } 
    } 
    return true; 
}


/***************************身份证验证开始***********************************************/
function chkSfzhNew(obj) { 
    var idCard = obj.value;               //去掉字符串头尾空格                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
        	
            return true;   
        }else {
        	alert("请输入正确的身份证号码！");
    		obj.select();
    		obj.focus();
            return false;   
        }   
    } else {
    	alert("请输入正确的身份证号码！");
		obj.select();
		obj.focus();
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {
	var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
	var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }   
    valCodePosition = sum % 11;                // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }  
  /***************************身份证验证结束***********************************************/
  

 /**
	 * 检查火车车次格式 quph 2010-7-19
	 */
function checkCc(obj) {
		var text = obj.value;
		if ('' != text) {
			if (!/^[A-Z]\d{2,4}$|^[1-9]\d{2,4}$/.test(text)) {
				alert('车次格式不正确！');
				obj.focus();
				return false;
			}
		}
}		

 /**
	 * 验证时间格式（23:00） quph 2010-7-19
	 */
function checkTime(obj) {
		var text = obj.value;
		if ('' != text) {
			if (!/^[0-23]{2}\:[0-59]{2}$/.test(text)) {
				alert('非法的时间格式');
				obj.focus();
				 return false;
			}
		}
	}
	
// add author:lt time:2010-7-28
// 在进行操作前，检测查询条件中的必选字段是否有选中
// id要选中的ID列表字符串 example: xh-xm-xb
// msg要提示的信息列表字符串 example: 学号-姓名-性别
// id 和 msg 的字符串拼接必须一致
function checkListIsSelect(id, msg) {
		var idArray = id.split("-");
		var msgArray = msg.split("-");
		for (var i=0;i<idArray.length;i++) {
			if ($(idArray[i]).value == null || $(idArray[i]).value == "") {
				alert("请先选择" + msgArray[i] + "条件，然后再进行操作!");
				return false;
			}
		}	
	return true;
}



// 验证开始结束时间
function checkSearchTj(kssjId,jssjId){
	var kssj = jQuery("#"+kssjId).value;
	var jssj = jQuery("#"+jssjId).value;
	
	if(kssj != "" && jssj != "" && kssj > jssj){
		alertInfo("时间段先后次序不正确，请确认！");
		return false;
	}
	return true;
	
}

// 验证开始结束时间
function checkSjTj(kssjId,kssjMc,jssjId,jssjMc){
	var kssj = jQuery("#"+kssjId).value;
	var jssj = jQuery("#"+jssjId).value;
	if(kssj != "" && jssj != "" && kssj > jssj){
		alertError(kssjMc+"不能晚于"+jssjMc+"，请确认");
		return false;
	}
	return true;
	
}

// 验证开始结束时间
function checkTimeRule(obj,kssjId,kssjMc,jssjId,jssjMc){
	var kssj = jQuery("#"+kssjId).value;
	var jssj = jQuery("#"+jssjId).value;
	
	if(kssj != "" && jssj != "" && kssj > jssj){
		alert(kssjMc+"不能晚于"+jssjMc+"，请确认");
		obj.value="";
		return false;
	}
	return true;
	
}

function isPhone(phone){
	var p = /^\d{0,4}\-{0,1}\d{7,8}$/;
	return p.test(phone);
}


// 年龄验证 【1-120】
function isAge(age){
	var p = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	
	return p.test(age);
}



// 验证网址http+\:\/\/
function isURL(url){
	var p =/([\w\-]+\.)+[\w-]+(\/[\w- \.\/?%&=]*)?/;
	return p.test(url);
}



/*
 * 调用zfstyle_v4样式验证文本框格式 保存按钮的id 必须为saveButton 验证网址，显示错误消息的DIV id必须为urlErrow
 * author:屈朋辉
 */
function checkURL(obj){
	var text = obj.value;
	if(''!=text && !isURL(text)) {
		$('urlErrow').className='msg_error';
		$('saveButton').disabled=true;
		obj.focus();
	}else {
		$('urlErrow').className='hide';
		$('saveButton').disabled=false;
	}
}



/*
 * 调用zfstyle_v4样式验证文本框格式 保存按钮的id 必须为saveButton 验证邮箱格式，显示错误消息的DIV id必须为emaliErrow
 * author:屈朋辉
 */
function checkEmaile(obj){
	var text = obj.value;
	if(''!=text && !isEmail(text)) {
		$('emaliErrow').className='msg_error';
		if($('saveButton')){
			$('saveButton').disabled=true;
		}
		obj.focus();
	}else {
		$('emaliErrow').className='hide';
		if($('saveButton')){
			$('saveButton').disabled=false;
		}
	}
}



/*
 * 调用zfstyle_v4样式验证文本框格式 保存按钮的id 必须为saveButton 验证电话格式，显示错误消息的DIV id必须为phoneErrow
 * author:屈朋辉
 */	
function checkPhoneV4(obj){
	var text = obj.value;
	if(''!=text && !isPhone(text)){
		$("phoneErrow").className='msg_error';
		$('saveButton').disabled=true;
		obj.focus();
	} else{
		$("phoneErrow").className='hide';
		$('saveButton').disabled=false;
	} 
}

/*
 * 调用zfstyle_v4样式验证文本框格式 保存按钮的id 必须为saveButton 验证电话格式，显示错误消息的DIV author:屈朋辉
 */	
function checkPhoneError(obj,errorId){
	var text = obj.value;
	if(''!=text && !isPhone(text)){
		$(errorId).className='msg_error';
		$('saveButton').disabled=true;
		obj.focus();
	} else{
		$(errorId).className='hide';
		$('saveButton').disabled=false;
	} 
}

/*
 * 调用zfstyle_v4样式验证文本框格式 保存按钮的id 必须为saveButton 验证文本框格式，显示错误消息的DIV id必须为
 * author:屈朋辉
 */	
function checkTextError(obj,errorId){
	var text = obj.value;
	if(''==text){
		return true;
	}
	if(''!=text && !isPhone(text)){
		$(errorId).className='msg_error';
		if($('saveButton')){
			$('saveButton').disabled=true;
		}
		obj.focus();
	} else{
		$(errorId).className='hide';
		if($('saveButton')){
			$('saveButton').disabled=true;
		}
	} 
}


function checkPsw(s) {
	if (s.length < 6) {
		alertError('密码不得小于6位');
		return false; // 不是6位
	}
	if (/^(\d)\1+$/.test(s) ) {
		alertError('密码不能为相同字符');
		return false;  // 全一样
	}
	if (/^([a-z A-Z])\1+$/.test(s) ) {
		alertError('密码不能为相同字符');
		return false;  // 全一样
	}
	var str = s.replace(/\d/g, function($0, pos) {return parseInt($0)-pos;});     
	if (/^(\d)\1+$/.test(str)) {
		alertError('密码不能为顺序数值');
		return false;  // 顺增
	}
	str = s.replace(/\d/g, function($0, pos) {return parseInt($0)+pos;});     
	if (/^(\d)\1+$/.test(str)) {
		alertError('密码不能为顺序数值');
		return false;   // 顺减
	}
	return true;
}



/*
 * 验证金额
 */
function checkMoney2(text) {
	if (null !== text && ''!= text) {
		if (!/^[0-9]\d*\.[0-9]{2}$|^[0-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text)) {
			 return false;
		}
	}
	return true;
}


/**
 * 测试是否为半角下的数字
 */
function chkNumInput2(value){
    if(value!=""&&value.match(/^\d*$/)==null){
	   return true;
	}else{
	   return false;
	}
}


/**
 * 验证年龄
 * 
 * @param obj
 * @return
 */
function checkAge(obj){
	if (obj.value != "" && !isAge(obj.value)){
		showAlertDivLayer("您输入的年龄不符合规则！",{},{"clkFun":function(){
			obj.focus();
		}});
		return false;
	}
}

function isNotChar(obj){
	obj.value = obj.value.replace(/[^\u0000-\u00ff]/g,'');
	return true;
}
/**
 * 同步下拉框 把A下拉框和b下拉框同步（使两者不能选择同一选项）
 * 
 * @param id1
 *            a下拉框 id
 * @param id2
 *            b下拉框 id
 * @user [982] zhangCl
 * @return
 */
function tbxlk(id1,id2){
	var jlscx="-1";// 记录删除的
	var jlscx2="-1";
	// 对a下拉框增加更改事件，同步删除掉b下拉框相同的值，这里是根据value是否相同判断
	jQuery("#"+id1).bind("change",function(){
		var yhdm=jQuery(this).val();
		if(yhdm!=""){
			jQuery("#"+id2).append(jlscx);
			jlscx=jQuery("#"+id2+" option[value="+yhdm+"]");
		
			jQuery("#"+id2+" option[value="+yhdm+"]").remove();
		}
	});
	// 对b增加
	jQuery("#"+id2).bind("change",function(){
		if(yhdm!=""){
			var yhdm=jQuery(this).val();

			jQuery("#"+id1).append(jlscx2);
			jlscx2=jQuery("#"+id1+" option[value="+yhdm+"]");
			jQuery("#"+id1+" option[value="+yhdm+"]").remove();
		}
	});
}

var firstNull;// 第一个空对象
/**
 * 空对象标红
 * 
 * @param ids
 *            对象id字符串 用 - 分隔
 * @author 982 张昌路
 * @return
 */
function checkNull(ids){
	var isok=true;
	var id = ids.split("-");
	jQuery.each(id,function(i){
		var arry=getObject(id[i]);
		jQuery.each(arry,function(j){
			if(!checkFunction(arry[j])){
				isok=false;
			}
		});
	});
	if(!isok){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！", {}, {
			"clkFun" : function() {
				jQuery(firstNull).focus();
				firstNull=null;
			}
		});
	}
	return isok;
}
/**
 * 获取具体对象，主要针对name支持
 * 
 * @param obj
 * @return
 */
function getObject(obj){
	var object=jQuery("#"+obj);
	var list=new Array();
	if(!object.val()){
		jQuery("[name='"+obj+"']").each(function(){
			list.push(jQuery(this));
		});
	}else{
		list.push(object);
	}
	return list;
}
/**
 * 获取对象具体指，对 radio和checkbox做特殊处理
 * 
 * @param obj
 * @return
 */
function getObjValue(obj){
	var type=jQuery(obj).attr("type");
	if(type=="radio"||type=="checkbox"){
		var name=jQuery(obj).attr("name");
		if(!jQuery("[name="+name+"]:checked").val()){
			return false;
		}
	}
	return jQuery(obj).val();
}
/**
 * 获取空对象，增加监听
 * 
 * @param obj
 * @return
 */
function checkFunction(obj){
	return checkObject(obj,function(){
		var v=getObjValue(obj);
		if(!v||jQuery.trim(v)==""){
			isok=false;
			if(!firstNull){
				firstNull=obj;
			}
			addEvent(obj);
			return false;
		}
		return true;
	});
}
/**
 * 添加监听事件
 * 
 * @param obj
 * @return
 */
function addEvent(obj){
	jQuery(obj).bind("keyup keydown change",function(){
		checkObject(obj,function(){
			var v=getObjValue(obj);
			if(!v||jQuery.trim(v)==""){
				return false;
			}
			return true;
		});
	});
}
/**
 * 设置对应样式
 * 
 * @param obj
 * @param check
 * @return
 */
function checkObject(obj,check){
	var type=jQuery(obj).attr("type");
	if(type=="radio"||type=="checkbox"){
		obj=jQuery(obj).parent();
	}
	if(!check()){
		jQuery(obj).css("border","1px solid #FF9999");
		return false;
	}else{
		jQuery(obj).css("border","");
		return true;
	}
}
/**
 * 锁定按钮(用于提交数据后防止多次提交)
 * @author [982] 张昌路
 * @return
 */
function lock(){
	jQuery("button").each(function(){
		jQuery(this).attr("disabled","disabled");
		jQuery(this).css({color:"#cccccc"});
	});
}
/**
 * 解除锁定
 * @author [982] 张昌路
 * @return
 */
function unlock(){
	jQuery("button").each(function(){
		jQuery(this).removeAttr("disabled");
		jQuery(this).css({color:"#ffffff"});
	});
}
/**
 * 根据json自动设置页面值
 * 
 * @param data
 *            json数据
 * @param isCover
 *            为true则对应匹配id设置，为false则只对对应对象为空值的设置
 * @author [982] 张昌路
 * @return
 */
function autoSetParam(data,isCover){
	for(var param in data){
		if(isCover){
			if(jQuery("#"+param)){
				jQuery("#"+param).val(data[param]);
			}
		}else{
			if(jQuery("#"+param)&&jQuery("#"+param).val()==""){
				var str=data[param];
				if(jQuery("#"+param).is("textarea")){
					str = str.replace(/<br\s*\/?>/g, "\n");
				}
				jQuery("#"+param).val(str);
			}
		}
	}
}


//验证字数 by yxy 2015-09-24
function checkzsinput_yz(obj,num){
	if(jQuery(obj).val().length > num){
		showAlert("最大字数为"+num+"，现已经超过，请确认！");
		return false;
	}
}

/*
 * 验证金额（只可输入大于0的整数或者两位小数）
 */
function checkMoneyBykeyUp(obj) {
	obj.value = obj.value.replace(/[^\d.]/g,'');
	var text = obj.value;
	if (null !== text && ''!= text) {
		
		obj.value = obj.value.replace(/^(.*)/g,'$1');
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		obj.value = obj.value.replace(/([0-9]+\.[0-9]{2})[0-9]*/, "$1");
			
	}

}

/**
 * 验证金额（只可输入大于0的整数或者两位小数）keyup与blur组合
 */
function checkMoneyForKeyup(obj){    
    
     //先把非数字的都替换掉，除了数字和.     
     obj.value = obj.value.replace(/[^\d\.]/g,'');       
     //必须保证第一个为数字而不是.       
     obj.value = obj.value.replace(/^\./g,'');       
     //保证只有出现一个.而没有多个.       
     obj.value = obj.value.replace(/\.{2,}/g,'.');       
     //保证.只出现一次，而不能出现两次以上       
     obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.');  
     //去除开头的0（如果其后不是小数点）
     obj.value = obj.value.replace(/^0+(?=[0-9])/, '');
     //只能输入两位小数  
     obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');  
     
} 

/**
 * 验证金额（只可输入大于0的整数或者两位小数）blur与keyup组合
 */
function checkMoneyForBlur(obj){    
	checkMoneyForKeyup(obj);
	
    //处理最后一个小数点
	obj.value = obj.value.replace(/\.$/, '');
} 

/**
 * 验证小时数（只可输入大于0的整数或者0/5结尾的一位小数）keyup与blur组合
 */
function checkTimeNumForKeyup(obj){
	obj.value=obj.value.replace(/(?:\D*)?(\d*)?(\.)?(0|5)?(?:\d*)?/ig,'$1$2$3');
}

/**
 * 验证小时数（只可输入大于0的整数或者0/5结尾的一位小数）blur与keyup组合
 */
function checkTimeNumForBlur(obj){
	checkTimeNumForKeyup(obj);
	//处理最后一个小数点
	obj.value = obj.value.replace(/\.$/, '');
}

//验证只能输入数字
function validateNUM(val){  
 if(!/^[0-9]*$/.test(val)){  
     return false; 
 }else{
	 return true;
 }
}  

//替换前置0
function replaceAboveZero(obj){
	var len = jQuery(obj).val().length;
	var Value =  jQuery(obj).val();
	var value = "";
	var flag = "";
	if(len > 1){
		for(var i=0;i<len-1;i++){
			if(Value.substring(i,i+1) != '0'){
				flag = i;
				break;
			}
			flag=i;
		}
		value =Value.substring(i,len);
		jQuery(obj).val(value);
	}
}

/**
 * 1位小数
 */
function checkMoneyBykeyUpByone(obj) {
	obj.value = obj.value.replace(/[^\d.]/g,'');
	var text = obj.value;
	if (null !== text && ''!= text) {
		
		obj.value = obj.value.replace(/^(.*)/g,'$1');
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		obj.value = obj.value.replace(/([0-9]+\.[0-9]{1})[0-9]*/, "$1");
			
	}

}

function checkZs(obj) {
	var num = jQuery(obj).val().trim();
	if(num != ''){
		if(!/^100$|^(\d|[1-9]\d)$/.test(num)){
			showAlert("请输入0-100的整数", {}, {
				"clkFun" : function() {
					obj.focus();
					return false;
				}
			});
		}else{
			return true;
		}	
	}else{
		return true;
	}
}

//验证人数(大于零的正整数，首位不为零)
function checkNumForPeople(obj){
	replaceAboveZero(obj);
	obj.value = obj.value.replace(/[^\d\.]/g,'');
	if(obj.value.indexOf(".") != -1){
		if(obj.value.indexOf(".") == 0){
			obj.value = obj.value.substring(1);
		}else{							
			var position = obj.value.indexOf(".");
			obj.value = obj.value.substring(0,position);		
		}
	}
	if(obj.value == "0"){
		showAlertDivLayer("限定人数必须大于0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
	}	
}

//验证工时(大于零的一位小数，首位不能为零，如末尾为小数点，则取出小数点)
function checkTimeForDekt(obj){
	if(obj.value.indexOf(".") == obj.value.length-1){
		obj.value = obj.value.substring(0,obj.value.length-1);
	}
	if((obj.value.indexOf(".") < 0 && obj.value.indexOf("0") == 0) || (obj.value == null || obj.value == '')){
		showAlertDivLayer("工时必须大于0!", {}, {
			"clkFun" : function() {
				obj.focus();
			}
		})
		return false;
	}
	return true;
}

/**
 * 包括手机，固定电话，带区号，不带区号
 * @param objId
 * @returns {boolean}
 */
function isTelephone(objId) {
    var obj = document.getElementById(objId);
    var pattern=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}1[0-9]{10}$)/;
    return pattern.test(obj.value);
}
