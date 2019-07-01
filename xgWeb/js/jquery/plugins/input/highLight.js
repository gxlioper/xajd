/**
 * @描述 文本框输入内容高亮显示
 * 		示例一： jQuery("#id").highLight();  输入显示效果 1234 1234 1234 1234
 * 		示例二： jQuery("#id").highLight({type:"phone"});  用于手机号，输入显示效果 181 0650 0602
 *     示例三： jQuery("#id").highLight({type:"card"}); 用于身份证号，输入显示效果 410 185 19** **** 301X
 *     示例四 :  jQuery("#id").highLight({width:200}); 设置高亮显示层宽度自定义
 * @作者 Penghui.Qu
 * @时间 2014-04-22
 */
(function($){
	//定义高亮显示层样式
	var style = ".highLight{text-overflow:ellipsis;white-space:nowrap;overflow:hidden;";
		  style+="position: absolute;z-index: 2;left: 0px;top: -32px;*top:-30px;background: rgb(252, 249, 248);";
		  style+="height: 30px;border: 1px solid rgb(252, 215, 70);color: rgb(238, 33, 15);";
		  style+="font: bold 14px/30px '宋体';text-indent: 5px;}";
	//将样式加入head	  
	$("<style type='text/css'>"+style+"</style>").appendTo("head");
	
	//定义核心函数
    $.fn.highLight = function(options){
    	//默认参数
    	var defaults = {
    			type : "",
    			width:0
        };
    	//整合自定义参数与默认参数
    	 var opts = $.extend(defaults,options);
    	 var $this = $(this);//控件本身
    	 var id = $this.attr("id");//控件ID
    	 
    	 //绑定获取焦点事件
    	 $this.bind("focus",function(){
    		 
    		 if ($("#"+id+"_highLight").html()  == null){
					var width = $this.width();
					var wDiv = $("<div style='position: relative;z-index: 1; '></div>");
					var bDiv = $("<div id='"+id+"_highLight' class='highLight'></div>").css("width",opts["width"] || width+2);
					$this.wrap(wDiv);
					$this.before(bDiv);
					$this.focus();
					
					var text = _getText();
					 $("#"+id+"_highLight").html(text);
				} else {
					 $("#"+id+"_highLight").show();
				}
		});
    	 
    	 //设置显示内容项
    	 function _getText(){
    		 var val = $.trim($this.val()).replace(/\s/g, '');
    		 var text="";
    		 
    		 //手机号码解析规则
    		 if (opts["type"] == "phone"){
    			 for (var i = 0 , j = val.length; i < j ; i++){
	    			 text += val.split('')[i];
	    			 if (i == 2 || (i != 0 && (i-2)%4 == 0)){
	    				 text += " ";
	    			 }
	    		 }
    			 return text;
    		 } 
    		 
    		 //身份证号解析规则
    		 if(opts["type"] == "card"){
    			 for (var i = 0 , j = val.length; i < j ; i++){
	    			 text += val.split('')[i];
	    			 if (i == 2  || i== 5 || (i > 6 && (i-5)%4 == 0)){
	    				 text += " ";
	    			 }
	    		 }
    			 return text;
    		 } 
    		 
    		 //默认认规则，每四位一个空格
    		 for (var i = 0 , j = val.length; i < j ; i++){
    			 text += val.split('')[i];
    			 if (i != 0 && (i+1)%4 == 0){
    				 text += " ";
    			 }
    		 }
    		return text;
    	 }
    	 
    	 //绑定输入事件
    	 $this.bind("keyup",function(){
    		 var text = _getText();
			 $("#"+id+"_highLight").html(text);
    	 });
    	 
    	//绑定离开事件
    	 $this.bind("blur",function(){
    		 $("#"+id+"_highLight").hide();
    	 });
    };
})(jQuery);