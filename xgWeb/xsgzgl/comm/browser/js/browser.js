/****************************������������******************************************/
/*��ʱΪ��extend.js��������Ӧ֧�֣����л�����Ըĳ������Զ�֧��
 * �� ֱ�Ӹ���ԭ����Ϊ  browser(save());����saveΪԭ����
 * [982] �Ų�·
 * */
/*jQuery(document).ready(function(){
	jQuery("*[disabled=disabled]").each(function(){
		jQuery(this).css({color:"#cccccc"});
	});
});*/
/**
 * �Զ�����
 */
var saveButton="buttonSave";
jQuery(function() {
	// �س�����
	var notautosubmit="<input type='text' name='notautosubmit' style='display:none'/>";
	jQuery("form").append(notautosubmit);
	// textare ��������
	jQuery("textarea").each(function(){
		var text=jQuery(this).val();
		text=text.replace(new RegExp("<br/>","gm"),'\n');
		jQuery(this).val(text);
	});
});
/**
 * ���ûس�����ð�ť
 * 
 * @deprecated
 * @param id
 * @return
 */
function setCallButton(id){
	saveButton=id;
}
/**
 * ��ȡ��Ӧ�������ֵ
 * 
 * @param id
 *            ��Ӧ������־
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
 * ���ò������Ѵ��ݹ����Ĳ������ݻ�ȥ��
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
 * ��ȡW���󣨶�㵯����
 * 
 * @return
 */
function getBase(){
	var api = frameElement.api;
	W = api.get('parentDialog');
	return W;
}
/**
 * ���ð�ť����
 * 
 * @param id
 *            ��ťid
 * @return
 */
function updateTrue(id){
	jQuery("#"+id).removeAttr("disabled");
	jQuery("#"+id).css({color:"#ffffff"});
}
/**
 * ���ð�ť������
 * 
 * @param id
 *            ��ťid
 * @return
 */
function updateFalse(id){
	jQuery("#"+id).attr("disabled","disabled");
	jQuery("#"+id).css({color:"#cccccc"});
}
/**
 * ������ť(�����ύ���ݺ��ֹ����ύ)
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
 * �������
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
 * �ص�ԭdiv(�ѵ�ǰ�������������ݸ��Ƶ�ԭdiv��)
 * 
 * @param w
 *            �������ڵĸ�����
 * @param id
 *            ԭdiv id
 * @return
 */
function callBack(w,id){
	var nowHtml=jQuery("#contentDiv").html();
	alert(nowHtml);
	W.jQuery("#"+id).html(nowHtml);
	alert(W.jQuery("#"+id).html());
}
/** *****************************���÷���****************************** */
/**
 * �Ƚ�ʱ��
 * 
 * @param aDate
 *            ʱ��a
 * @param bDate
 *            ʱ��b
 * @user [982] �Ų�·
 * @param splitStr
 *            ʱ��ָ�����2013-02-05 �ָ���Ϊ-��
 * @return 0:���/1:a����b/2:b����a ����:�����쳣
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
		// ��ʱ��a��bת��Ϊintֵ�������
		var a=aDates[0]+aDates[1]+aDates[2];
		var b=bDates[0]+bDates[1]+bDates[2];
		// ���ߵĲ�ֵ
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
// ��չreplaceAll����
String.prototype.replaceAll = function (AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText);
}
/**
 * ����table��ҳ�����·�
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
 * ���ؽ�����
 * 
 * @param barkey
 *            Ψһkey
 * @param calback
 *            �ص�����
 * @return
 */
function loadBar(barkey,calback,time){
	// Ĭ��ʱ��
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
				//���Ľ�����������ʾ
				jQuery("#bar").css("width",data.rate+"%");
				jQuery("#bl").text(data.rate+"%");
				//�������������ȡ
				if(data.finish){
					clearInterval(myBar);
				}
				// �����������
				if(calback&&!calback(data)){
					clearInterval(myBar);
				}else{
					jQuery("#progress").show();
				}
			}
		});	
	},time);
}
// ��������ʾ�йرյ�ǰҳ�棬ˢ�¸�ҳ��
function barAlter(message){
	showAlert(message,{},{"clkFun":function(){
		if (parent.window){
			refershParent();
		}
	}});
}
/**
 * ֹͣ������
 * 
 * @return
 */
function stopBar(){
	clearInterval(myBar);
	jQuery("#progress").hide();
}
/**
 * ����json�Զ�����ҳ��ֵ
 * 
 * @param data
 *            json����
 * @param isCover
 *            Ϊtrue���Ӧƥ��id���ã�Ϊfalse��ֻ�Զ�Ӧ����Ϊ��ֵ������
 * @author [982] �Ų�·
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
 * �ն�����
 * 
 * @param ids
 *            ����id�ַ��� �� - �ָ�
 * @author 982 �Ų�·
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
		showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������", {}, {
			"clkFun" : function() {
				jQuery(firstNull).focus();
			}
		});
	}
	return isok;
}
/**
 * ��ȡ���������Ҫ���name֧��
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
 * ��ȡ�������ָ���� radio��checkbox�����⴦��
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
 * ��ȡ�ն������Ӽ���
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
 * ��Ӽ����¼�
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
 * ���ö�Ӧ��ʽ
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
//���ڰ���
//����ת��ΪӢ��(201001)
function convertDate(date){
	var enDate=new Array("Jan.","Feb.","Mar."," Apr.","May.","Jun."," Jul.","Aug.","Sept.","Oct.","Nov.","Dec.");
	//������յ�����
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
//ת�����Ϊ����(2014)
function convertNumToChYear(date){
	  var newdate="";
	  var cn = ["��","һ","��","��","��","��","��","��","��","��"];
	  var dates=date.split("");
	  if(dates.length<2){
		  newdate+=cn[0];
	  }
	  for(var i in dates){
		  newdate+=cn[dates[i]];
	  }
	  return newdate;
}
//ת����Ϊ����(29)
function convertNumToChDay(date){
	  date=parseInt(date, 10);
	  var newdate="";
	  var j="";
	  var cn = ["ʮ","һ","��","��","��","��","��","��","��","��"];
	  var dates=date.toString().split("");
	  for(var i in dates){
		  //����С��20������
		  if(i==0&&parseInt(dates[i],10)<2){
			  continue;
		  }
		  //����ĩβΪ0��
		  if(i>0&&dates[i]!=0){
			  newdate+=cn[0];
		  }
		  newdate+=cn[dates[i]];
	  }
	  return newdate;
}