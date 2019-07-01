/**
 * @���� �ı����������ݸ�����ʾ
 * 		ʾ��һ�� jQuery("#id").highLight();  ������ʾЧ�� 1234 1234 1234 1234
 * 		ʾ������ jQuery("#id").highLight({type:"phone"});  �����ֻ��ţ�������ʾЧ�� 181 0650 0602
 *     ʾ������ jQuery("#id").highLight({type:"card"}); �������֤�ţ�������ʾЧ�� 410 185 19** **** 301X
 *     ʾ���� :  jQuery("#id").highLight({width:200}); ���ø�����ʾ�����Զ���
 * @���� Penghui.Qu
 * @ʱ�� 2014-04-22
 */
(function($){
	//���������ʾ����ʽ
	var style = ".highLight{text-overflow:ellipsis;white-space:nowrap;overflow:hidden;";
		  style+="position: absolute;z-index: 2;left: 0px;top: -32px;*top:-30px;background: rgb(252, 249, 248);";
		  style+="height: 30px;border: 1px solid rgb(252, 215, 70);color: rgb(238, 33, 15);";
		  style+="font: bold 14px/30px '����';text-indent: 5px;}";
	//����ʽ����head	  
	$("<style type='text/css'>"+style+"</style>").appendTo("head");
	
	//������ĺ���
    $.fn.highLight = function(options){
    	//Ĭ�ϲ���
    	var defaults = {
    			type : "",
    			width:0
        };
    	//�����Զ��������Ĭ�ϲ���
    	 var opts = $.extend(defaults,options);
    	 var $this = $(this);//�ؼ�����
    	 var id = $this.attr("id");//�ؼ�ID
    	 
    	 //�󶨻�ȡ�����¼�
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
    	 
    	 //������ʾ������
    	 function _getText(){
    		 var val = $.trim($this.val()).replace(/\s/g, '');
    		 var text="";
    		 
    		 //�ֻ������������
    		 if (opts["type"] == "phone"){
    			 for (var i = 0 , j = val.length; i < j ; i++){
	    			 text += val.split('')[i];
	    			 if (i == 2 || (i != 0 && (i-2)%4 == 0)){
	    				 text += " ";
	    			 }
	    		 }
    			 return text;
    		 } 
    		 
    		 //���֤�Ž�������
    		 if(opts["type"] == "card"){
    			 for (var i = 0 , j = val.length; i < j ; i++){
	    			 text += val.split('')[i];
	    			 if (i == 2  || i== 5 || (i > 6 && (i-5)%4 == 0)){
	    				 text += " ";
	    			 }
	    		 }
    			 return text;
    		 } 
    		 
    		 //Ĭ���Ϲ���ÿ��λһ���ո�
    		 for (var i = 0 , j = val.length; i < j ; i++){
    			 text += val.split('')[i];
    			 if (i != 0 && (i+1)%4 == 0){
    				 text += " ";
    			 }
    		 }
    		return text;
    	 }
    	 
    	 //�������¼�
    	 $this.bind("keyup",function(){
    		 var text = _getText();
			 $("#"+id+"_highLight").html(text);
    	 });
    	 
    	//���뿪�¼�
    	 $this.bind("blur",function(){
    		 $("#"+id+"_highLight").hide();
    	 });
    };
})(jQuery);