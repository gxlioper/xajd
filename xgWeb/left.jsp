<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-template" prefix="template"%>
<html>
<head>
	<style>
		.jspContainer{
			height:800px !important;
		}

		#wrap{
			width: 180px;
		}
		.posleft{
			position: absolute !important;
			left: 178px;
			top: 0;
			width: 180px;
			background-color: white;
			z-index: 100;
		}
		.posleft>.child-menu>li{
			padding: 8px 20px !important;
		}
		.child-menu{
			position: relative;
		}
		.scroll-pane{
			overflow: visible !important;
			width:180px !important;
		}
		.jspContainer{
			overflow: visible !important;
		}
		.posleft>ul>li>span>a{
			color: #888 !important;
			font-size: 12px !important;
			line-height: 1;
		}
		.system-menu>li>.child-menu>li{
			border-bottom: 0 !important;
		}
		.child-menu>li:hover{
			/*background: lightblue;*/
			/*background-color:#e2ecf9;*/
		}
		.child-menu>.fa{
			top: 5px !important;
		}
		.child-menu>.posleft{
			display: none;
		}
		.firLi>.child-menu>.posleft{
			left: 196px;
		}
		.system-menu>li>.child-menu>li:hover{background-color: transparent;}

	</style>
	<%@ include file="/syscommon/xg_v4.ini"%>
	<script type="text/javascript">

        jQuery(function(){

            var bars = '.jspHorizontalBar, .jspVerticalBar';
            var setting={autoReinitialise:false};
            //初始化滚动条
            var jspApi=$(".scroll-pane").jScrollPane(setting);
            //获取滚动条
            var refreshApi=jspApi.data("jsp");

			$('.scroll-pane').bind('jsp-initialised', function(event, isScrollable) {
				//hide the scroll bar on first load
				$(this).find(bars).hide();
			}).jScrollPane().hover(
				//hide show scrollbar
				function() {
					$(this).find(bars).stop().fadeTo('fast', 0.9);
				},
				function() {
					$(this).find(bars).stop().fadeTo('fast', 0);
				}
			);
            /*左侧菜单导航收缩效果*/
            $(".system-menu .firLi").click(function() {
                $(this).children('span').toggleClass('active');
//				$(this).children('.fa').replaceWith('<i class="fa fa-angle-up pull-right">');
                $(this).children('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
                $(this).children('.child-menu').toggle();
                $(this).find('.posleft').hide();
                $(this).children('.child-menu').css('background-color','transparent');

                $(this).siblings('.firLi').children('.fa').replaceWith('<i class="fa fa-angle-down pull-right">');
                $(this).siblings('.firLi').children('span').removeClass('active');
                $(this).siblings('.firLi').children('.child-menu').hide();
                $(this).siblings('.firLi').children('.child-menu').find('.posleft').hide();
                $(this).siblings('.firLi').children('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
                event.stopPropagation();
                if($(this).children('span').hasClass('active')) {
//					var jspPaneHeight=$(".jspPane").height();
                    //jQuery(".jspContainer").height(jspPaneHeight);
//					$(this).parent('.system-menu').find('span').removeClass('active');
//					$(this).children('span').addClass('active');

//					$(this).parent('.system-menu').find('.fa').replaceWith('<i class="fa fa-angle-down pull-right">');

                    $(this).children('.fa').replaceWith('<i class="fa fa-angle-up pull-right">');
					$('#midFrame').css
//					$(this).parent('.system-menu').find('.child-menu').hide();
//					$(this).children('.child-menu').toggle();
                }else{
//					$(this).parent('.system-menu').find('.fa').replaceWith('<i class="fa fa-angle-down pull-right">');
                    $(this).children('.fa').replaceWith('<i class="fa fa-angle-down pull-right">');
//					$(this).parent('.system-menu').find('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
                }

//				refreshApi.reinitialise(setting);
            });
            $(".system-menu .child-menu").children("li").click(function(){
                event.stopPropagation();
            });
            $('.firLi').find('.fa-angle-right').click(function(){
                event.stopPropagation();
            })
            $('.child-menu').click(function(){
                event.stopPropagation();
            })


//            $('.nextpage').click(function(){
//                event.stopPropagation();
//                console.log(111);
//                $(this).siblings('.posleft').toggle();
//                $(this).siblings('.posleft').children('.child-menu').show();
//                $(this).parent().parent().show();
//                if($(this).siblings('.fa').hasClass('fa-angle-right')){
//                    $(this).siblings('.fa').replaceWith('<i class="fa fa-angle-up pull-right">');
//                }else{
//                    $(this).siblings('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
//                }
//                $(this).parent().siblings('.child-menu').find('.posleft').hide();
//                $(this).parent().siblings('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
//            })
//            $('.firLi').children('.child-menu').children('.nextpage').click(function(){
//                event.stopPropagation();
//                $(this).parent().siblings('.child-menu').find('.posleft').hide();
//                $(this).parent().siblings('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
//            })
//            $('.child-menu').mouseover(function(){
//                $(this).css('background-color','#e2ecf9');
////          	$(this).parent().parent().siblings('.nextpage').css('background-color','green');
//            })
//            $('.child-menu').mouseout(function(){
//                $(this).css('background-color','transparent');
////          	$(this).parent().parent().siblings('.nextpage').css('background-color','transparent');
//            })



            $('.child-menu').children('li').mouseenter(function(){
//				$('#midFrame').css('width','1000');
//console.log($(window.parent ,'#midFrame').css('width'));
//				console.log($(window.parent).find('#midFrame').css('background'));
//				console.log($('#midFrame',parent.document).css('width'));
                $('#midFrame',parent.document).css('width','1000px');
                $(this).siblings('.posleft').show();
                $(this).siblings('.posleft').children('.child-menu').show();
                $(this).parent().siblings('.child-menu').find('.posleft').hide();
                $(this).parent().siblings('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
                $(this).siblings('.fa').replaceWith('<i class="fa fa-angle-up pull-right">');
                $(this).parent().css('background-color','#e2ecf9');
                $(this).parent().siblings('.child-menu').css('background-color','transparent');
                $(this).parent().find('.child-menu').css('background-color','transparent');
                event.stopPropagation();
            })
//          $('.child-menu').children('li').mouseleave(function(){
//          	$(this).parent().css('background-color','transparent');
//          })
//          $('.child-menu').children('li').mouseleave(function(){
//          	$(this).siblings('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
//          	console.log('right');
//          	event.stopPropagation();
//          })
//          $('.nextpage').mouseout(function(){
//          	$(this).siblings('.posleft').hide();
//          	event.stopPropagation();
//          })
//          $('.posleft').mouseenter(function(){
//          	$(this).siblings('.fa').replaceWith('<i class="fa fa-angle-up pull-right">');
//          	console.log('up');
//          	event.stopPropagation();
//          })
            $('.posleft').mouseleave(function(){
                $('#midFrame',parent.document).css('width','180px');
                $(this).hide();
                $(this).siblings('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');


                $(this).parents('.firLi').find('.posleft').hide();
                $(this).parents('.firLi').find('.child-menu').children('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
                $(this).parents('.child-menu').css('background-color','transparent');
                event.stopPropagation();
            })
            $('.firLi').mouseleave(function(){
                $('#midFrame',parent.document).css('width','180px');
                $(this).find('.child-menu').css('background-color','transparent');
                $(this).find('.child-menu').find('.fa').replaceWith('<i class="fa fa-angle-right pull-right">');
                $(this).find('.posleft').hide();
                event.stopPropagation();
            })



            //切换菜单
//			jQuery(".textlink h3").bind("click",function(){
//				var _self = jQuery(this);
//				var className = jQuery(this).attr("class");
//
//				jQuery("h3.open").not(_self).next().css("display","none");
//				jQuery("h3.open").not(_self).removeClass().addClass("close");
//
//				if (className == "open"){
//					_self.removeClass().addClass("close");
//					_self.next().css("display","none");
//				} else {
//					_self.removeClass().addClass("open");
//					_self.next().css("display","block");
//				}
//			});

            //绑定增加快捷功能菜单事件
//			jQuery(".cygn_add").bind("click",addKjfs);
            jQuery.ajaxSetup({async:false});
            //加载快捷方式
//			loadKjfs();
            //加载主体内容
			loadContent();
            jQuery.ajaxSetup({async:true});

        });


        function loadContent(){

            var menuTop = jQuery("#menuTop").val();
            var url = jQuery(".open_03").eq(0).attr("href");
            var userType = jQuery("#userType").val();
            if(menuTop == "N16"){//公寓管理
                url = "gygl_jbsz_gylcxs.do";
            }else if(menuTop == "N35"){//评奖评优（伪）
                url = "pjpy_jbsz_gylcxs.do";
            }else if(menuTop == "N38" && userType != "stu"){//公寓管理（第三版）
                url = "gyglnew_gylcxs.do";
            }else if(menuTop == "N82" && userType == "stu"){
                url = "hdgl_hdgl_xsdbt.do";
            }

			if(url!=null && typeof(url)!="undefined"){
				if (url.indexOf("http:") > -1){
					window.open(url);
				} else {
					chgRight(url, "framecon");
				}
			}
        }



        function loadKjfs(){
            var menuTop = jQuery("#menuTop").val();

            jQuery.post("xtwh_menu.do?method=queryKjfs",{"gnmkdm":menuTop},function(data){

                if (data.length == 0){
                    //隐藏快捷方式区域
                    jQuery("#kjfsDiv").css("display","none");
                    //将第一个二级菜单展开
                    jQuery(".textlink[id!=kjfsDiv] h3").eq(0).click();
                }

                for (var i = 0 ; i < data.length ; i++){
                    var _self = jQuery("a[name="+data[i]+"]");
                    var li = _self.parent().clone();
                    var icon = li.find("a").eq(1);
                    //绑定删除快捷功能菜单事件
                    icon.unbind("click").bind("click",removeKjfs);
                    icon.removeClass("cygn_add");
                    icon.addClass("cygn_delete");

                    jQuery("#kjfsUl").append(li);
                    _self.removeClass("cygn_add");
                    _self.addClass("cygn_disabled");
                    _self.unbind("click");
                }
            },"json");
        }



        function addKjfs(){

            if(jQuery("#kjfsUl li").length == 5){
                parent.window.alertInfo("最多可设置5个快捷功能。");
                return false;
            }

            var li = jQuery("<li></li>");
            li.append(jQuery(this).parent().find(".open_03").clone());

            //切换图标样式、绑定删除快捷功能菜单事件
            var icon = jQuery(this).clone();
            icon.removeClass("cygn_add");
            icon.addClass("cygn_delete");
            icon.bind("click",removeKjfs);
            li.append(icon);

            jQuery("#kjfsUl").append(li);
            jQuery(this).removeClass("cygn_add");
            jQuery(this).addClass("cygn_disabled");
            jQuery(this).unbind("click");

            jQuery.post("xtwh_menu.do?method=addKjfs",{"gnmkdm":this.name},function(data){
            });
            //显示快捷方式区域
            jQuery("#kjfsDiv").css("display","");
        }



        function removeKjfs(){
            var icon = jQuery("a[class=cygn_disabled][name="+this.name+"]");
            //切换图标样式、绑定增加快捷功能事件
            icon.removeClass("cygn_disabled");
            icon.addClass("cygn_add");
            icon.unbind("click");
            icon.bind("click",addKjfs);
            jQuery(this).parent().remove();

            jQuery.post("xtwh_menu.do?method=removeKjfs",{"gnmkdm":this.name},function(data){
            });

            //隐藏快捷方式区域
            if (jQuery("#kjfsUl li").length == 0){
                jQuery("#kjfsDiv").css("display","none");
            }
            return false;
        }
        /*垂直滚动条的效果*/

	</script>
	
	
</head>
<body>
		<input type="hidden" value="${menuTop }" id="menuTop"/>

<%--	<html:form action="/initMenu" styleId="menuIni">--%>
<%--		<input type="hidden" value="<bean:write name="menuList"/>" name="menuLeft" />--%>
<%--		<input type="hidden" value="<bean:write name="xxdm"/>" id="xxdm" />--%>
		<input type="hidden" value="<bean:write name="userType"/>" id="userType" />
<%--		<input type="hidden" name="chMenu" id="chMenu" value="${chMenu }" />--%>
<%--		<input type="hidden" name="gnmkpath" id="gnmkpath" value="${gnmkpath }" />--%>

			<!-- urls是一个很神奇的隐藏域，谁要把它去掉了整个系统切换数据权限就烂掉了。本人已被坑，立此为据2013-1-10 qph-->
			<input type="hidden" name="urls" id="urls" />

	
		<div class="scroll-pane" style="background: #fff;">	
			<ul class="system-menu">

				${menuHtml}
		 	
	 		</ul>
      </div>
    	<script type="text/javascript" src="js/jquery/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.jscrollpane.min.js"></script> 	
   </body>
</html>
