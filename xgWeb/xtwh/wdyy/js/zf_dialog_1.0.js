/**
 * @author yxy
 * @param ֻ���븸ҳ�浯������ mainbody
 * @ʹ�õ�ʱ���븸ҳ�浯�����󣬴��뷽ʽ����{mainbody:obj}
 * @date 2015-10-14 14:22
 * @ѧ����ҳ�༭�ҵ�Ӧ�õ�����
 */
(function($){
	
	//�������
	$.zfDialog = {
		Alert:function(options){
			var $dialog = this.init(options);
		},
		//��ʼ��
		init:function(options){
			//����ļ̳к͸���,����Ķ��󸲸�ǰ����ͬ���Ե�ֵ,ȡ������������ͬ����
			var opts = $.extend({},$.zfDialog.defaults,options);
			//������ʼ��
			var jsonData = getInitialData();//��ȡ��ʼ������
			var $dialog = this.template(opts,jsonData);
			//���Ĵ�С,����ɫ��λ��
			/*
			$dialog.css({
				zIndex:1000
			});*/
			this._position($dialog,opts);

			//��ק
		    this._drag($dialog);
			//�¼���ʼ��
			this.events($dialog,opts);
			return $dialog;
		},
		events:function($dialog,opts){
			jQuery($dialog).find('#text').keydown(function(event){
                if(event.keyCode == 13){ 
                	parent.search();
                 } 
          }); 
			/*
			parent.jQuery('#connectedSortable').dragsort({ 
					dragSelector: "img", 
					dragBetween: false, 
					//dragEnd: dradend, 
					placeHolderTemplate: "<li></li>", 
					scrollSpeed: 5 
				}); 
			
			parent.jQuery('#connectedSortable li a span').click(function(){
				add_delApp(this);
			}); */
			parent.jQuery( "#connectedSortable" ).sortable().disableSelection();
		},
		//��λ����
		_position:function($dialog,opts){
			innerheight = window.parent.innerHeight || window.parent.document.documentElement.clientHeight;
			innerwidth =  window.parent.innerWidth || window.parent.document.documentElement.clientWidth;
			var left =(innerwidth - $dialog.width())/2;
			var top = (innerheight - $dialog.height())/2;
			$dialog.css({left:left,top:top});
			//��һ�����ڵĸı��¼�
		},
		//��ק
		_drag:function($dialog){
			jQuery($dialog).find(".open_title").mousedown(function(e){
				var ev = e || parent.window.event;
				var x = ev.clientX - $dialog.offset().left;
				var y = ev.clientY - $dialog.offset().top;
				var w = $dialog.width();
				var h = $dialog.height();
				var maxl = window.innerWidth || window.parent.document.documentElement.clientWidth -  w;
				var maxt = window.parent.innerHeight || window.parent.document.body.clientHeight -  h;
				jQuery(parent.document).mousemove(function(e){
					var ev = e || window.event;
					var l = ev.clientX -x ;
					var t = ev.clientY -y;
					if(l<=0)l=0;
					if(t<=0)t=1;
					if(l>=maxl)l=maxl;
					if(t>=maxt)t=maxt;
					$dialog.css({left:l,top:t});
				}).mouseup(function(){
					jQuery(parent.document).unbind("mousemove");
					jQuery(parent.document).unbind("mouseup");
				});
			});
		},
		//ģ��
		template:function(opts,jsonData){
			var gncdArry = jsonData.gncd;
		    var flhead = jsonData.flhead;
			var flContent = jsonData.flContent;
			var gncdhtml = "";
			var flheadhtml = "";
			var flContenthtml = "";
			if(gncdArry == 'null' || gncdArry == null || gncdArry.length == 0 || !gncdArry){
				gncdhtml = "��������...";
			}else{
				for(var i= 0;i<gncdArry.length;i++){
					gncdhtml +="<li><a  title = '"+gncdArry[i]['title']+"' href='"+gncdArry[i]['dyym']+"' target='_blank'><img  src='images/wdyy/"+gncdArry[i]['gnmklj']+"' width='48' height='48'><h5>"+gncdArry[i]['gnmkmc']+"</h5></a><span onclick=add_delApp(this) data-gnmkdm = '"+gncdArry[i]['gnmkdm']+"' data-flag = 'del' class='ico'></span></li>";
				}
			}
			flheadhtml = "<li id='lione' onclick='showsearch(this)' data-mkfldm = '' class = 'cur' style='cursor: pointer;'> <a>����</a> <span>|</span> </li>";
			if(flhead == 'null' || flhead == null || flhead.length == 0 || !flhead){
				flheadhtml = "<li id='lione' onclick='showsearch(this)' data-mkfldm = '' class = 'cur' style='cursor: pointer;'> <a>����</a> <span>|</span> </li>";;
			}else{
				for(var i= 0;i<flhead.length;i++){
					flheadhtml +="<li id='lione' onclick='showsearch(this)' data-mkfldm = '"+flhead[i]['mkfldm']+"' style='cursor: pointer;'> <a>"+flhead[i]['mkflmc']+"</a> <span>|</span> </li>";
				}
			}
			if(flContent == 'null' || flContent == null || flContent.length == 0 || !flContent){
				flContenthtml = "��������...";
			}else{
				for(var i= 0;i<flContent.length;i++){
					flContenthtml +="<li><a title= '"+flContent[i]['title']+"' href='"+flContent[i]['dyym']+"' target='_blank'><img src='images/wdyy/"+flContent[i]['gnmklj']+"' width='48' height='48'><h5>"+flContent[i]['gnmkmc']+"</h5></a><span onclick=add_delApp(this) data-flag = 'add' data-gnmkdm = '"+flContent[i]['gnmkdm']+"' class='ico'></span></li>";

				}
			}
			//<em>��ק������</em>
			var dialog = jQuery("<div class='opacity_box' style='z-index:1980'>"+
			"<div class='opacity_bg'></div>"+
			"<div class='open_box'>"+
				"<div class='open_title' id='open_title' style='cursor: pointer;'> <span>���� �ҵ�Ӧ�� </span>"+
					"<div class='btn'>"+
					 "<button onclick='saveData();' type='button' class='new'> ����</button>"+
						"<button onclick='save_close();' type='button' class='new'> �ر� </button>"+
					"</div>"+
					"<p class='ts'> ����Ӽ�����ɾ�����Կ��� </p>"+
				"</div>"+
				"<div class='app_left'>"+
					"<h3> <span> �ҵ�Ӧ��  (���10��) </span> <em>��ק������</em> </h3>"+
					"<ul class='connectedSortable' id='connectedSortable'> "+
					gncdhtml+
					"</ul>"+
				"</div>"+
				"<div class='app_right'> "+
					
					"<div class='tc_search'>"+
						"<ul id='searchul'>"+
							"<li class='all'><a href='#'>���з���</a><span>&gt;</span></li>"+
							"<li id='replaceli'></li>"+
							"<div class='tc_s_form'>"+
								"<input type='text' id='text' value='' class='tc_s_input'>"+
								"<input type='button'  id='search' class='tc_s_but' onclick='search();'>"+
							"</div>"+
						"</ul>"+
					"</div>"+
					"<div class='xxk'>"+
						"<ul>"+
						flheadhtml+
						"</ul>"+
					"</div>"+
					"<div class='app_con'>"+
						"<ul id='all_list' class='app_con list'>"+
						flContenthtml+
						"</ul>"+
					"</div>"+
				"</div>"+
			"</div>"+
		"</div>");
			//׷�ӵ�body��parent.document.getElementById("mainbody") .append("<div style='z-index:1979' class='zfui-overlay'></div>")
			jQuery(opts.mainbody).append(dialog).append("<div style='z-index:1979' class='zfui-overlay'></div>");
			return dialog;
		}
	};

	//Ĭ�ϲ���,��������ҳ���Ի�������˾Ͳ�������css��������Ҫ�Ĳ��֣������е���js��template���޸�
	$.zfDialog.defaults = {
		/*���ĸ����ڵ��������ֱ���ڸ�ҳ��̸ֱ�Ӹ�body,���ѡ������iframe���ص�ҳ�浯����
		 * ��Ҫ�ҵ���ҳ���bodyԪ�أ���parent.document.getElementById("mainbody")
		 */
		mainbody:parent.document.getElementById("mainbody"),//Ĭ�ϸ�ҳ�������Ҳ��ֱ���ٴ�����
		callback:function(ok){//�ص�����
		
	    }
	};

})(jQuery);