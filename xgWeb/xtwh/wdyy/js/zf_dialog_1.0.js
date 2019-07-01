/**
 * @author yxy
 * @param 只传入父页面弹出参数 mainbody
 * @使用的时候传入父页面弹出对象，传入方式采用{mainbody:obj}
 * @date 2015-10-14 14:22
 * @学工首页编辑我的应用弹出框
 */
(function($){
	
	//弹出组件
	$.zfDialog = {
		Alert:function(options){
			var $dialog = this.init(options);
		},
		//初始化
		init:function(options){
			//对象的继承和覆盖,后面的对象覆盖前面相同属性的值,取并集，并且相同覆盖
			var opts = $.extend({},$.zfDialog.defaults,options);
			//弹出初始化
			var jsonData = getInitialData();//获取初始化数据
			var $dialog = this.template(opts,jsonData);
			//更改大小,改颜色，位置
			/*
			$dialog.css({
				zIndex:1000
			});*/
			this._position($dialog,opts);

			//拖拽
		    this._drag($dialog);
			//事件初始化
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
		//定位居中
		_position:function($dialog,opts){
			innerheight = window.parent.innerHeight || window.parent.document.documentElement.clientHeight;
			innerwidth =  window.parent.innerWidth || window.parent.document.documentElement.clientWidth;
			var left =(innerwidth - $dialog.width())/2;
			var top = (innerheight - $dialog.height())/2;
			$dialog.css({left:left,top:top});
			//绑定一个窗口的改变事件
		},
		//拖拽
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
		//模板
		template:function(opts,jsonData){
			var gncdArry = jsonData.gncd;
		    var flhead = jsonData.flhead;
			var flContent = jsonData.flContent;
			var gncdhtml = "";
			var flheadhtml = "";
			var flContenthtml = "";
			if(gncdArry == 'null' || gncdArry == null || gncdArry.length == 0 || !gncdArry){
				gncdhtml = "暂无数据...";
			}else{
				for(var i= 0;i<gncdArry.length;i++){
					gncdhtml +="<li><a  title = '"+gncdArry[i]['title']+"' href='"+gncdArry[i]['dyym']+"' target='_blank'><img  src='images/wdyy/"+gncdArry[i]['gnmklj']+"' width='48' height='48'><h5>"+gncdArry[i]['gnmkmc']+"</h5></a><span onclick=add_delApp(this) data-gnmkdm = '"+gncdArry[i]['gnmkdm']+"' data-flag = 'del' class='ico'></span></li>";
				}
			}
			flheadhtml = "<li id='lione' onclick='showsearch(this)' data-mkfldm = '' class = 'cur' style='cursor: pointer;'> <a>所有</a> <span>|</span> </li>";
			if(flhead == 'null' || flhead == null || flhead.length == 0 || !flhead){
				flheadhtml = "<li id='lione' onclick='showsearch(this)' data-mkfldm = '' class = 'cur' style='cursor: pointer;'> <a>所有</a> <span>|</span> </li>";;
			}else{
				for(var i= 0;i<flhead.length;i++){
					flheadhtml +="<li id='lione' onclick='showsearch(this)' data-mkfldm = '"+flhead[i]['mkfldm']+"' style='cursor: pointer;'> <a>"+flhead[i]['mkflmc']+"</a> <span>|</span> </li>";
				}
			}
			if(flContent == 'null' || flContent == null || flContent.length == 0 || !flContent){
				flContenthtml = "暂无数据...";
			}else{
				for(var i= 0;i<flContent.length;i++){
					flContenthtml +="<li><a title= '"+flContent[i]['title']+"' href='"+flContent[i]['dyym']+"' target='_blank'><img src='images/wdyy/"+flContent[i]['gnmklj']+"' width='48' height='48'><h5>"+flContent[i]['gnmkmc']+"</h5></a><span onclick=add_delApp(this) data-flag = 'add' data-gnmkdm = '"+flContent[i]['gnmkdm']+"' class='ico'></span></li>";

				}
			}
			//<em>拖拽可排序</em>
			var dialog = jQuery("<div class='opacity_box' style='z-index:1980'>"+
			"<div class='opacity_bg'></div>"+
			"<div class='open_box'>"+
				"<div class='open_title' id='open_title' style='cursor: pointer;'> <span>管理 我的应用 </span>"+
					"<div class='btn'>"+
					 "<button onclick='saveData();' type='button' class='new'> 保存</button>"+
						"<button onclick='save_close();' type='button' class='new'> 关闭 </button>"+
					"</div>"+
					"<p class='ts'> 点击加减号增删，试试看？ </p>"+
				"</div>"+
				"<div class='app_left'>"+
					"<h3> <span> 我的应用  (最多10个) </span> <em>拖拽可排序</em> </h3>"+
					"<ul class='connectedSortable' id='connectedSortable'> "+
					gncdhtml+
					"</ul>"+
				"</div>"+
				"<div class='app_right'> "+
					
					"<div class='tc_search'>"+
						"<ul id='searchul'>"+
							"<li class='all'><a href='#'>所有分类</a><span>&gt;</span></li>"+
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
			//追加到body中parent.document.getElementById("mainbody") .append("<div style='z-index:1979' class='zfui-overlay'></div>")
			jQuery(opts.mainbody).append(dialog).append("<div style='z-index:1979' class='zfui-overlay'></div>");
			return dialog;
		}
	};

	//默认参数,由于是首页个性化功能因此就不给其他css参数，如要改布局，请自行到本js的template中修改
	$.zfDialog.defaults = {
		/*从哪个窗口弹出，如果直接在父页面谈直接给body,如果选择在用iframe加载的页面弹出，
		 * 需要找到父页面的body元素：如parent.document.getElementById("mainbody")
		 */
		mainbody:parent.document.getElementById("mainbody"),//默认父页面参数，也可直接再此设置
		callback:function(ok){//回调函数
		
	    }
	};

})(jQuery);