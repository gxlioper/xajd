/****************************浏览器兼容相关******************************************/
/*暂时为在extend.js中增加相应支持，后有机会可以改成匿名自动支持
 * 如 直接更改原方法为  browser(save());即可save为原方法
 * [982] 张昌路
 * */
/*jQuery(document).ready(function(){
	jQuery("*[disabled=disabled]").each(function(){
		jQuery(this).css({color:"#cccccc"});
	});
});*/
/**
 * 自动处理
 */
var saveButton="buttonSave";
jQuery(function() {
	// 回车问题
	var notautosubmit="<input type='text' name='notautosubmit' style='display:none'/>";
	jQuery("form").append(notautosubmit);
	// textare 换行问题
	jQuery("textarea").each(function(){
		var text=jQuery(this).val();
		text=text.replace(new RegExp("<br/>","gm"),'\n');
		jQuery(this).val(text);
	});
});
/**
 * 设置回车后调用按钮
 * 
 * @deprecated
 * @param id
 * @return
 */
function setCallButton(id){
	saveButton=id;
}
/**
 * 获取对应所需参数值
 * 
 * @param id
 *            对应参数标志
 * @return
 */
function getParam(id){
	var param=jQuery("#param").val();
	var params=param.split(",");
	for(var i=0;i<params.length;i++){
		var myparams=params[i].split(":");
		if(myparams[0]==id){
			return myparams[1];
		}
	}
}
/**
 * 设置参数（把传递过来的参数传递回去）
 * 
 * @param base
 * @return
 */
function setParam(base){
	var param=jQuery("#param").val();
	var params=param.split(",");
	for(var i=0;i<params.length;i++){
		var myparams=params[i].split(":");
		base.jQuery("#")+myparams[0].val(myparams[1]);
	}
}
/**
 * 获取W对象（多层弹出）
 * 
 * @return
 */
function getBase(){
	var api = frameElement.api;
	W = api.get('parentDialog');
	return W;
}
/**
 * 设置按钮可用
 * 
 * @param id
 *            按钮id
 * @return
 */
function updateTrue(id){
	jQuery("#"+id).removeAttr("disabled");
	jQuery("#"+id).css({color:"#ffffff"});
}
/**
 * 设置按钮不可用
 * 
 * @param id
 *            按钮id
 * @return
 */
function updateFalse(id){
	jQuery("#"+id).attr("disabled","disabled");
	jQuery("#"+id).css({color:"#cccccc"});
}
/**
 * 锁定按钮(用于提交数据后防止多次提交)
 * 
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
 * 
 * @return
 */
function unlock(){
	jQuery("button").each(function(){
		jQuery(this).removeAttr("disabled");
		jQuery(this).css({color:"#ffffff"});
	});
}
/**
 * 回调原div(把当前弹出层的相关数据复制到原div中)
 * 
 * @param w
 *            弹出窗口的父对象
 * @param id
 *            原div id
 * @return
 */
function callBack(w,id){
	var nowHtml=jQuery("#contentDiv").html();
	alert(nowHtml);
	W.jQuery("#"+id).html(nowHtml);
	alert(W.jQuery("#"+id).html());
}
/** *****************************常用方法****************************** */
/**
 * 比较时间
 * 
 * @param aDate
 *            时间a
 * @param bDate
 *            时间b
 * @user [982] 张昌路
 * @param splitStr
 *            时间分隔符（2013-02-05 分隔符为-）
 * @return 0:相等/1:a大于b/2:b大于a 其他:数据异常
 */
function compareDate(aDate,bDate,splitStr){
	if(splitStr==null||""==splitStr){
		splitStr="-";
	}
	var aDates=aDate.split(splitStr);
	var bDates=bDate.split(splitStr);
	if(aDates.length!=bDates.length){
		return "-1";
	}else{
		// 把时间a和b转换为int值进行相减
		var a=aDates[0]+aDates[1]+aDates[2];
		var b=bDates[0]+bDates[1]+bDates[2];
		// 两者的差值
		var c=parseInt(a)-parseInt(b);
		if(c==0){
			return "0";
		}else if(c>0){
			return "1";
		}else if(c<0){
			return "2";
		}
	}
	return "3";
}
// 扩展replaceAll方法
String.prototype.replaceAll = function (AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText);
}
/**
 * 设置table在页面最下方
 */
function belowTable(id){
	var style="position: fixed; _position: absolute; bottom: 0;";
	if(!id){
		id="below";
	}
	jQuery("#"+id).attr("style",style);
}
var myBar;
/**
 * 加载进度条
 * 
 * @param barkey
 *            唯一key
 * @param calback
 *            回调方法
 * @return
 */
function loadBar(barkey,calback,time){
	// 默认时间
	if(!time){
		time=1000;
	}
	myBar=setInterval(function(){
	 	jQuery.ajax({
			url:"browser.do?method=getProgressBar",
			data:{barkey:barkey},
			type:"post",
			dataType:"json",
			success:function(data){
				//更改进度条进度显示
				jQuery("#bar").css("width",data.rate+"%");
				jQuery("#bl").text(data.rate+"%");
				//如果完成则结束获取
				if(data.finish){
					clearInterval(myBar);
				}
				// 如果完成则结束
				if(calback&&!calback(data)){
					clearInterval(myBar);
				}else{
					jQuery("#progress").show();
				}
			}
		});	
	},time);
}
// 进度条提示切关闭当前页面，刷新父页面
function barAlter(message){
	showAlert(message,{},{"clkFun":function(){
		if (parent.window){
			refershParent();
		}
	}});
}
/**
 * 停止进度条
 * 
 * @return
 */
function stopBar(){
	clearInterval(myBar);
	jQuery("#progress").hide();
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
		showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！", {}, {
			"clkFun" : function() {
				jQuery(firstNull).focus();
			}
		});
	}
	return isok;
}
/**
 * 获取具体对象，主要针对name支持
 * @param obj
 * @return
 */
function getObject(obj){
	var object=jQuery("#"+obj);
	var list=new Array();
	if(!object.val()){
		jQuery("[name="+obj+"]").each(function(){
			list.push(jQuery(this));
		});
	}else{
		list.push(object);
	}
	return list;
}
/**
 * 获取对象具体指，对 radio和checkbox做特殊处理
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
//日期帮助
//日期转换为英文(201001)
function convertDate(date){
	var enDate=new Array("Jan.","Feb.","Mar."," Apr.","May.","Jun."," Jul.","Aug.","Sept.","Oct.","Nov.","Dec.");
	//处理带日的日期
	if(date.length>6){
		var n=date.substring(0,4);
		var y=date.substring(4,6);
		var r=date.substring(6,8);
		newDate=enDate[parseInt(y,10)-1];
		newDate+=" "+r+","+n;
		return newDate;
	}
	var newDate;
	if(date.length!=6){
		return date;
	}else{
		var n=date.substring(0,4);
		var r=date.substring(4,6);
		newDate=enDate[parseInt(r,10)-1];
		newDate+=n;
	}
	return newDate;
}
//转换年份为中文(2014)
function convertNumToChYear(date){
	  var newdate="";
	  var cn = ["〇","一","二","三","四","五","六","七","八","九"];
	  var dates=date.split("");
	  if(dates.length<2){
		  newdate+=cn[0];
	  }
	  for(var i in dates){
		  newdate+=cn[dates[i]];
	  }
	  return newdate;
}
//转换日为中文(29)
function convertNumToChDay(date){
	  date=parseInt(date, 10);
	  var newdate="";
	  var j="";
	  var cn = ["十","一","二","三","四","五","六","七","八","九"];
	  var dates=date.toString().split("");
	  for(var i in dates){
		  //处理小于20的日期
		  if(i==0&&parseInt(dates[i],10)<2){
			  continue;
		  }
		  //处理末尾为0的
		  if(i>0&&dates[i]!=0){
			  newdate+=cn[0];
		  }
		  newdate+=cn[dates[i]];
	  }
	  return newdate;
}