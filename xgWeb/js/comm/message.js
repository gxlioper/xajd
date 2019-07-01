

/**
 * 弹出指定内容
 * 
 * @param title
 * @param content
 * @param width
 * @param height
 */
function tipsWindown(title, content, width, height) {
	
	if (content.indexOf("id:") > -1){
		var id = "#"+content.split("id:")[1];
		content = jQuery(id).html();
		// jQuery(id).remove();
	}
	
	ymPrompt.win({
			message:content,
			title:title,
			width:width,
			height:height,
			useSlide:true,
			maskAlphaColor:"#FFFFFF",
			winPos:'t',
			maskAlpha:0.3
		}
	);
}
/**
 * 弹出指定内容(针对div类型)
 * 
 * @param title
 * @param content
 * @param width
 * @param height
 * @param param
 *            原div中使用的参数（例如调用保存js方法传递的id）多个参数使用逗号隔开
 *            格式:(param:value,param2:value2)
 * @return
 */
function tipsWindownNew(title, content, width, height,param,other) {
	var url="browser.do?method=divshow&content=";
	if (content.indexOf("id:") > -1){
		// var id = "#"+content.split("id:")[1];
		content = content.split("id:")[1];
	}
	url+=content;
	url+="&param="+param;
	showDialog(title, width, height, url,other);
}
/**
 * 弹出页面内容层关闭
 */
function divLayerClose(){
	ymPrompt.close();
}


/**
 * 弹出询问框
 */
function showConfirmDivLayer() {
	
	var argumentsArr = Array.prototype.slice.call(arguments);
	var _fun = null;
	
	ymPrompt.confirmInfo({
			title:"系统提示",
			message:argumentsArr[0],
			width:340,
			height:160,
			maskAlphaColor:"#FFFFFF",
			maskAlpha:0.3,
			useSlide:true,
			winPos:[180,160],
			handler:function(type){
				if (type=="ok"){
					_fun = argumentsArr[1]["okFun"];
				} else if (type == "cancel"){
					_fun = argumentsArr[1]["cancelFun"];
				}
				
				if (_fun != null){
					_fun();
				}
				
			}
		}
	);
}


/**
 * 层上确认框（完全居中）
 * 
 * @return
 */
function showConfirm() {
	
	var argumentsArr = Array.prototype.slice.call(arguments);
	var _fun = null;
	
	var defaultConif = {
		title:"系统提示",
		message:argumentsArr[0],
		width:340,
		height:160,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		useSlide:true,
		handler:function(type){
			if (type=="ok"){
				_fun = argumentsArr[1]["okFun"];
			} else if (type == "cancel"){
				_fun = argumentsArr[1]["cancelFun"];
			}
			
			if (_fun != null){
				_fun();
			}
			
		}
	};
	
	
	ymPrompt.confirmInfo(jQuery.extend(defaultConif,argumentsArr[2]));
}




/**
 * 弹出警告框
 */
function showAlertDivLayer() {
	var argumentsArr = Array.prototype.slice.call(arguments);
	if(argumentsArr[0] == null) return;
	
	var clickFun = null;
	
	if (argumentsArr.length == 3){
		clickFun = argumentsArr[2]["clkFun"];
	}
	ymPrompt.alert({
		title:"系统提示",
		useSlide:true,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		message:argumentsArr[0],
		width:340,
		winPos:[180,160],
		height:150,
		// showMask:false,
		handler:clickFun
	});
	// setTimeout(function(){ymPrompt.doHandler();},3000);
}
/**
 * 弹出警告框（可变大小）
 */
function showAlertDivLayerSize(message, width, height, clickFun) {
	ymPrompt.alert({
		title:"系统提示",
		useSlide:true,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		message:message,
		width:width,
		winPos:[180,160],
		height:height,
		// showMask:false,
		handler:clickFun
	});
	// setTimeout(function(){ymPrompt.doHandler();},3000);
}


/**
 * 层上警告框（完全居中）
 * 
 * @return
 */
function showAlert() {
	var argumentsArr = Array.prototype.slice.call(arguments);
	if(argumentsArr[0] == null) return;
	
	var clickFun = null;
	
	if (argumentsArr.length == 3){
		clickFun = argumentsArr[2]["clkFun"];
	}
	ymPrompt.alert({
		title:"系统提示",
		useSlide:true,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		message:argumentsArr[0],
		width:340,
		height:160,
		// showMask:false,
		handler:clickFun
	});
	// setTimeout(function(){ymPrompt.doHandler();},3000);
}



/**
 * 层上警告框（完全居中）,弹出层页面高度太小而把警告框相应的变小
 * 
 * @return
 */
function showAlertx() {
	var argumentsArr = Array.prototype.slice.call(arguments);
	if(argumentsArr[0] == null) return;
	
	var clickFun = null;
	
	if (argumentsArr.length == 3){
		clickFun = argumentsArr[2]["clkFun"];
	}
	ymPrompt.alert({
		title:"系统提示",
		useSlide:true,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		message:argumentsArr[0],
		width:280,
		height:130,
		// showMask:false,
		handler:clickFun
	});
	// setTimeout(function(){ymPrompt.doHandler();},3000);
}


/**
 * 弹出iframe窗口关闭
 */
function iFClose(){
	
	if(frameElement.api){
		closeDialog();
	} else {
		parent.window.ymPrompt.close();
	}
	
	
}


/**
 * 在弹出iframe窗口中刷新父页面，并关闭窗口
 */
function refershParent(){
	if(frameElement.api){
		var api = frameElement.api,W = api.opener;
		jQuery(W.document).find('#search_go').click();
		closeDialog();
	} else {
		jQuery(parent.window.document).find('#search_go').click();
		iFClose();
	}
}
/**
 * 在弹出iframe窗口中获取父页面
 */
function getParentWindow(){
	if(frameElement.api){
		var api = frameElement.api,W = api.opener;
		return W;
	} else {
		return parent.window;
	}
}


/**
 * 把原有的弹出窗口转成ymPormpt式弹出
 * 
 * @param title
 * @param width
 * @param height
 * @param url
 */
function showWindow(title,width,height,url,handler){
	
	ymPrompt.win({message:url,
				  width:width,
				  height:height,
				  title:title,
				  fixPosition:true,
				  maxBtn:true,
				  minBtn:true,
				  winPos: 't',
				  iframe:true,
				  showShadow:false,
				  useSlide:true,
				  maskAlphaColor:"#FFFFFF",
				  maskAlpha:0.3,
				  handler:handler
			}
	);
}

/**
 * 弹出窗口，非iframe
 * 
 * @param title
 * @param url
 * @param width
 * @param height
 */
function showDivWindow(title,width,height,url){
	
	
	jQuery.post(url,{},function(data){
		ymPrompt.win({
			message:data,
			title:title,
			width:width,
			winPos:'t',
			height:height,
			useSlide:true,
			maskAlphaColor:"#FFFFFF",
			maskAlpha:0.3
		});
	},'html');
}
/**
 * 
 * @param html 消息html
 * @param callback 关闭后执行方法
 * @param width 
 * @param height
 * @param other
 * @author [982] 张昌路
 * @return
 */
function alertMessage(html,callback,width,height,other){
	if(null==width||width==""){
		width='380px';
	}
	if(null==height||height==""){
		height='160px';
	}
	var myHtml="<html><body >";
	myHtml+="<table width=\"100%\" height=\"100%\">";
	myHtml+="<tr>";
	myHtml+="<td style=\"width: 20%;\" align=\"right\"><img src=\"/xgxt/comm/message/info.gif\"/></td>";
	myHtml+="<td style=\"width: 80%\" align=\"left\">";
	myHtml+="<div style=\"margin-left: 20px;max-height:"+height+";overflow-x:hidden;overflow-y:auto;\">";
	myHtml+=html;
	myHtml+="</div>";
	myHtml+="</td>";
	myHtml+="</tr>";
	myHtml+="</table>";
	myHtml+="</body></html>";
	var title = "提示信息";
	var api = frameElement.api;
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		zIndex: 1978,
		min:false,
		max: false,
		init:function(){
		},
		resize: false,
		close:callback,
		content:myHtml,
		ok:function(){
			this.reload();
		}
	};
	var params = jQuery.extend(setting, other || {});
	if (api){
		var W = api.opener;
		params["id"] = "childDialog";
		params["parent"] = api;
		return W.lhgdialog(params);
	} else {
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
}
/**
 * 
 * @param html 消息html
 * @param callback 关闭后执行方法
 * @param width 
 * @param height
 * @param other
 * @author [982] 张昌路
 * @return
 */
function alertMessageUrl(html,callback,width,height,other){
	var message="<div id='html' style=''>"+html+"</div>";
	jQuery("body").append(message);
	jQuery("#html").hide();
	var url="browser.do?method=showMessage";
	if(null==width||width==""){
		width='380px';
	}
	if(null==height||height==""){
		height='160';
	}
	url+="&height="+height;
	height+="px";
	var title = "提示信息";
	var api = frameElement.api;
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		max: false,
		zIndex: 1978,
		init:function(){
		},
		resize: false,
		close:callback,
		content:'url:'+url
	};
	var params = jQuery.extend(setting, other || {});
	if (api){
		var W = api.opener;
		params["id"] = "childDialog";
		params["parent"] = api;
		return W.lhgdialog(params);
	} else {
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
}
/**
 * 弹出模态窗口，替换原来的showTopWin
 * 
 * @param width
 * @param height
 * @param url
 * @param t
 * @param 其它参数
 */
function showDialog(t,width,height,url,other){
	var title = t||"学生工作管理系统";
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		zIndex: 1976,
		content:'url:'+url
		
	};
	var params = jQuery.extend(setting, other || {});
	try{
		if(frameElement&& frameElement.api){
			var api = frameElement.api;
			var W = api.opener;
			var oZdx = frameElement.api.opener.lhgdialog.setting.zIndex;
			params["id"] = "childDialog";
			params["parent"] = api;
			params["zIndex"] = oZdx + 10;
			return W.lhgdialog(params);
		}else{
			params["id"] = "parentDialog";
			params["parent"] = window;
			return lhgdialog(params);
		}
	}catch(e){
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
	
	
}
/**
 * 弹出模态窗口(第三层弹窗专用)
 * 
 * @param width
 * @param height
 * @param url
 * @param t
 * @param 其它参数
 */
function showDialog2(t,width,height,url,other){
	var title = t||"学生工作管理系统";
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		zIndex: 1976,
		content:'url:'+url
		
	};
	var params = jQuery.extend(setting, other || {});
	try{
		if(frameElement&& frameElement.api){
			var api = frameElement.api;
			var W = api.opener;
			var oZdx = frameElement.api.opener.lhgdialog.setting.zIndex;
			params["id"] = "childDialog2";
			params["parent"] = api;
			params["zIndex"] = oZdx + 10;
			return W.lhgdialog(params);
		}else{
			params["id"] = "parentDialog";
			params["parent"] = window;
			return lhgdialog(params);
		}
	}catch(e){
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
}
/**
 * 关闭模态窗口
 */
function closeDialog(){
	var api = frameElement.api; 
	api.close();
}



function loading(){
	var argumentsArr = Array.prototype.slice.call(arguments);
	
	var reIcon = function(){
		this.DOM.icon[0].innerHTML = '<img src="'+stylePath+'/js/lhgdialog/skins/icons/loading.gif" class="ui_icon_bg"/>';
		this.DOM.icon[0].style.display = '';
	};
	
	var option = {
		title: false,
		cancel: false,
		fixed: true,
		lock: true,
		resize: false,
		zIndex: 1999,	
		close:function(){
			
		}
	};
	try{
		if(frameElement&& frameElement.api){
			var api = frameElement.api;
			var W = api.opener;
			option["id"] = "Tips1";
			option["parent"] = api;
		}else{
			option["id"] = "Tips";
			option["parent"] = window;
		}
	}catch(e){
		option["id"] = "Tips";
		option["parent"] = window;
	}
	
	var msg = "数据加载中";
	if(argumentsArr[0] != null){
		msg = argumentsArr[0];
	}
	return lhgdialog(option).content(msg).time(9999, reIcon);
}

function importLoading(){
	return loading("数据导入中,可能需要几分钟,请耐心等待");
}
function submitLoading(){
	return loading("数据提交中,可能需要几分钟,请耐心等待");
}

/**右下脚弹出提示
 * @param message 提示消息
 * @param time 显示时长
 * @param title 标题
 * @param other 其它参数
 * @return
 */
function alertNotice(message,time,title,other){
	var param = { 
		    title: default_title(title), 
		    width: 220,  /*必须指定一个像素宽度值或者百分比，否则浏览器窗口改变可能导致lhgDialog收缩 */
		    height:100,
		    content: message, 
		    time: time
		};
	notice(jQuery.extend(param,other));
}

/**
郭钦霸(郭钦霸) 15:46:19
 扩展窗口外部方法 */ 
var notice = function( options ) 
{ 
    var opts = options || {}, 
        api, aConfig, hide, wrap, top, 
        duration = opts.duration || 800; 
         
    var config = { 
        id: 'Notice', 
        left: '98%', 
        top: '100%', 
        fixed: true, 
        drag: false, 
        resize: false, 
        max : false,
		min :false,
        init: function(here){ 
            api = this; 
            aConfig = api.config; 
            wrap = api.DOM.wrap; 
            top = parseInt(wrap[0].style.top); 
            hide = top + wrap[0].offsetHeight; 
                         
            wrap.css('top', hide + 'px') 
            .animate({top: top + 'px'}, duration, function(){ 
                opts.init && opts.init.call(api, here); 
            }); 
        }, 
        close: function(here){ 
            wrap.animate({top: hide + 'px'}, duration, function(){ 
                opts.close && opts.close.call(this, here); 
                aConfig.close = $.noop; 
                api.close(); 
            }); 
                         
            return false; 
        } 
    }; 
         
    for(var i in opts) 
    { 
        if( config[i] === undefined ) config[i] = opts[i]; 
    } 
         
    return jQuery.dialog( config ); 
}; 

function default_title(title){
	return title;
}
