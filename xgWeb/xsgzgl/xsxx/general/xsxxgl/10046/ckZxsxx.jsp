<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			displayYwmkxx();	
		}
		//显示各业务模块列表数据
		function displayYwmkxx() {
			
			var url = "xsxx_tygl.do?method=xsYwmklb";
			var xh = jQuery('#xh').val();
			var tdarray = jQuery(".ywmkclass");
			if (tdarray != null && tdarray.length > 0) {
				jQuery(tdarray).each(function(i,n) {
					var td_mkmc = jQuery(n).attr("id");
					var parameter = {
						"gnmk":escape(td_mkmc.replace("td_","")),
						"xh":xh
					};
					jQuery("#"+td_mkmc).load(url,parameter,function(){
						
					});
				});
			}
		}

		//显示TBODY内容 
		function displayTbody(tbodyId) {
			if (document.getElementById(tbodyId)) {
				document.getElementById(tbodyId).style.display=(document.getElementById(tbodyId).style.display==''?'none':'');
			}
		}
		//显示上传相片
		function showZpscDiv(){
	
			var xh = jQuery("#xh").val();
			
			if(xh == ""){
				alert("请先填写学号！");
			}else{
				tipsWindown("系统提示","id:addPic","380","130","true","","true","id");
			}
		}

		
		$(function(){
	$(".college_title").toggle(function(){
		$(this).siblings().hide();
		$(this).children(".up").attr("class","down").text("展开");
	return false;
	},function(){
		$(this).siblings().show();
		$(this).children(".down").attr("class","up").text("收起");
	})
	$(".college_title a:eq()").toggle(function(){
		$(this).siblings().hide();
		$btn.attr("class","down");
		$btn.text("展开");
	return false;
	},function(){
		$(".demo_college .con").show();
		$btn.attr("class","up");
		$btn.text("收起");
	})
	$(".demo_college li .college_li").hover(function(){
		$(this).next().show();
		$(this).parent().css("position","relative")
	},function(){
		$(this).next().hide();
		$(this).parent().css("position","")
	})
	$(function(){
	$(".list_xxxx li").hover(function(){
		$(this).children(".list_xxxx_downmenu").show();
		$(this).css("position","relative")
	},function(){
		$(this).children(".list_xxxx_downmenu").hide();
		$(this).css("position","")
	})
	$(".list_xxxx_downmenu").hover(function(){
		$(this).show();
		$(this).prev().attr("class","hover")
	},function(){
		$(this).hide();
		$(this).prev().removeClass("hover")
	});
})
})
		//称动表格位置
		function moveTable(tabid) {
			jQuery("#"+tabid+"_a").click(function(){
				jQuery("#"+tabid).prependTo("#demo_xxxx");
				var tbody = jQuery("#"+tabid).find("tbody").attr("id");
				document.getElementById(tbody).style.display="";
				return false;
			});
			
		}
		
		jQuery(function(){
			onShow();
			});
		</script>
	</head>
	<body >
	
		<html:form action="/xsxx_tygl"  method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>

<!-- 浮动框架 -->
<div style="height:50px;">
<div id="position-fixed" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
<div class="title_xxxx">
	<span class="people_xx">${rs.xm } （学号： ${rs.xh }）</span>
    <span class="wxts">温馨提醒： <span>点击下面的类别， 可以快速定位到您所要查看的信息</span></span>
</div>
<!-- 详细信息定位 -->
<div class="position_xxxx after" id="position-fixed">
    <ul class="list_xxxx">
    	<li><a href="#tab_jbxx" class="smooth">学生信息</a>
    		<div class="list_xxxx_downmenu" style="display:none">
    			<dl>
    				<dd id="tab_jbxx_a" style="text-align: left; text-indent:1em;">
    					<a href="#tab_jbxx" class="smooth">基本信息</a>
    				</dd>
    				<dd id="tab_lxfs_a" style="text-align: left; text-indent:1em;">
    					<a href="#tab_lxfs" class="smooth">联系方式</a>
    				</dd>
    				<dd id="tab_jtcyxx_a" style="text-align: left; text-indent:1em;">
    					<a href="#tab_jtcyxx" class="smooth">家庭成员信息</a>
    				</dd>
    				<dd id="tab_qtxx_a" style="text-align: left; text-indent:1em;">
    					<a href="#tab_qtxx" class="smooth">其他信息</a>
    				</dd>
    				<dd id="tab_szxx_a" style="text-align: left; text-indent:1em;">
    					<a href="#tab_szxx" class="smooth">思政信息</a>
    				</dd>
    			</dl>
    	</li>
        <!-- 业务模块名称 -->
        <logic:present name="ckList">
        <logic:equal value="true" name="mksize">
        	<logic:iterate id="ckobj" name="ckList" indexId="index" offset="0" length="6">
        		 <li id="tab_${ckobj.gnmk}_a" >
        		    <!-- 一级业务模块 -->
        		 	<a href="#tab_${ckobj.gnmk}" class="smooth">${ckobj.gnmk }</a>
	    				<!-- 二级模块菜单 -->
	        		 	<logic:iterate id="ywlb" name="ywlbList">
	        		 		<logic:equal value="${ywlb.dm}" name="ckobj" property="gnmk">
	        		 			<div class="list_xxxx_downmenu" style="display:none">
    							<dl>
	        		 			<logic:iterate id="y" name="ywlb" property="xsmc">
	        		 				<dd id="tab_jbxx_a" style="text-align: left; text-indent:1em;"><a href="#tab_${y }">${y }</a></dd>
	        		 			</logic:iterate>
	        		 			</dl>
        		 			</div>
	        		 		</logic:equal>
	        		 	</logic:iterate>
        		 </li>
        	</logic:iterate>
        	 <li id="tab_more_a" >
        	 <a href="#tab_more">更多</a>
        	 <div class="list_xxxx_downmenu" style="display:none">
    							<dl>
    							 <logic:iterate id="ckobj" name="ckList" indexId="index" offset="6" >
	        		 				<dd id="tab_jbxx_a" style="text-align: left; text-indent:1em;"><a href="#tab_${ckobj.gnmk }" class="smooth">${ckobj.gnmk }</a></dd>
	        		 			</logic:iterate> 	
	        		 			</dl>
        		</div>
        	 </li>
        	</logic:equal>
        	<logic:notEqual value="true" name="mksize">
        		<logic:iterate id="ckobj" name="ckList">
        		 <li id="tab_${ckobj.gnmk}_a" >
        		    <!-- 一级业务模块 -->
        		 	<a href="#tab_${ckobj.gnmk}" class="smooth">${ckobj.gnmk }</a>
	    				<!-- 二级模块菜单 -->
	        		 	<logic:iterate id="ywlb" name="ywlbList">
	        		 		<logic:equal value="${ywlb.dm}" name="ckobj" property="gnmk">
	        		 			<div class="list_xxxx_downmenu" style="display:none">
    							<dl>
	        		 			<logic:iterate id="y" name="ywlb" property="xsmc">
	        		 				<dd id="tab_jbxx_a" style="text-align: left; text-indent:1em;"><a href="#tab_${y }">${y }</a></dd>
	        		 			</logic:iterate>
	        		 			</dl>
        		 			</div>
	        		 		</logic:equal>
	        		 	</logic:iterate>
        		 </li>
        	</logic:iterate>
        	</logic:notEqual>
        </logic:present>
        
    </ul>
</div>
</div>
</div>
<!-- 浮动层脚本 -->
<script type="text/javascript">
jQuery(function(){
	
jQuery('#position-fixed').find(".list_xxxx li").hover(function(){

		jQuery(this).children(".list_xxxx_downmenu").show();
		jQuery(this).css("position","relative")
	},function(){
		jQuery(this).children(".list_xxxx_downmenu").hide();
		jQuery(this).css("position","")
	})
	jQuery('#position-fixed').find(".list_xxxx_downmenu").hover(function(){
		jQuery(this).show();
		jQuery(this).prev().attr("class","hover")
	},function(){
		jQuery(this).hide();
		jQuery(this).prev().removeClass("hover")
	})
	jQuery(".list_xxxx_downmenu dd").live('click',function(){
		jQuery(this).parent().parent().hide();
	})
})
</script>
<!-- 基本信息 -->
<logic:equal value="stu" name="userType">
<div class="demo_xxxx" style="margin-top:20px;" id="demo_xxxx" >
</logic:equal>
<logic:notEqual value="stu" name="userType">
<div class="demo_xxxx" style="margin-top:20px;" id="demo_xxxx" >
</logic:notEqual>
	
				<table width="100%" border="0" style="margin-bottom: 2px;" class="formlist" id="tab_jbxx">
					<!-- 学生基本信息 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5" >
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jbxx">
						<tr>
						<th width="13%">
							学号
							</th>
							<td width="30%">
								${rs.xh }
								<input type="hidden" name="xh" id="xh" value="${rs.xh }"/>
							</td>
							<th width="13%">
								姓名
							</th>
							<td >
								${rs.xm }
							</td>
							<td rowspan="5" class="nohover"
								style="vertical-align:middle; text-align:left;width:115px;">
									<div id="stuImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
										<img style="width:100px;height:130px;" 
											src="xsxx_xsgl.do?method=showPhoto&xh=${rs.xh}"
											border="0">
									</div>
							</td>
						</tr>
						<tr>
							<th width="13%">
							性别
							</th>
							<td width="30%">
								${rs.xb }
							</td>
							<th width="13%">
								出生日期
							</th>
							<td >
								${rs.csrq }
							</td>
						</tr>
						
						<tr>
							<th width="13%">
							年级
							</th>
							<td width="30%">
								${rs.nj }
							</td>
							<th>
								学制(年)
							</th>
							<td colspan="">
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th>
								
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								政治面貌
							</th>
							<td>
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							
							<th>
								民族
							</th>
							<td>
								${rs.mzmc }
							</td>
							
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td colspan="">
								
								${rs.bjmc }
							</td>
							<th>
								学籍
							</th>
							<td align="left" colspan="2">
								${rs.xjztm }
							</td>
						</tr>
					
						<tr>
							<th>
								入学时间
							</th>
							<td colspan="">
							${rs.rxrq }
								
							</td>
							<th>
							身份证号
							</th>
							<td align="left" colspan="2">
								${rs.sfzh}
							</td>
						</tr>
						
						<tr>
							<th>
								籍贯
							</th>
							<td colspan="4">
									${rs.jgmc }
							</td>
						</tr>
						
						<tr>
							<th>
								户口所在地
							</th>
							<td colspan="4">
								${rs.hkszdmc }	
							</td>
						</tr>
						<tr>
							<th>
								生源地(高考时户籍所在地)
							</th>
							<td colspan="4">
								${rs.sydqmc }
							</td>
						</tr>
					</tbody>
					
				</table>
				
				<table width="100%" style="margin-bottom: 2px" border="0" class="formlist" id="tab_lxfs">
					<!-- 学生联系方式 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>联系方式</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_lxfs" >
					<tr>
							<th width="13%">
								联系电话
							</th>
							<td colspan="" width="30%">
							${rs.sjhm }
								
							</td>
							<th width="13%">
							电子邮箱
							</th>
							<td align="left" colspan="2">
									${rs.dzyx }
							</td>
						</tr>
						<tr>
						<th>
								QQ号码
							</th>
							<td>
								${rs.qqhm }
							</td>
							<th>
							家庭电话
							</th>
							<td align="left" colspan="2">
							${rs.jtdh }
							</td>
							</tr>
							<tr>
							<th>
								家庭邮编
							</th>
							<td>
								${rs.jtyb }
							</td>
							<th>
							家庭地址
							</th>
							<td align="left" colspan="2">
							${rs.jtszd }
							</td>
							</tr>
					</tbody>
				</table>
				
				
				<table style="margin-bottom: 2px" width="100%" border="0" class="formlist" id="tab_jtcyxx">
					<!-- 学生家庭成员信息 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>家庭成员信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jtcyxx" >
						<tr>
							<th width="13%">
								<div align="center">姓名</div>
							</th>
							<th >
								<div align="center">与本人关系</div>
							</th>
							<th>
								<div align="center">工作单位及地址</div>
							</th>
							<th>
								<div align="center">单位电话</div>
							</th>
							<th>
								<div align="center">个人电话</div>
							</th>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy1_xm }
							</td>
							<td align="center">
							${rs.jtcy1_gx }
							</td>
							<td align="center">
							${rs.jtcy1_gzdz }
							</td>
							<td align="center">
							${rs.jtcy1_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy1_lxdh1}
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy2_xm }
							</td>
							<td align="center">
							${rs.jtcy2_gx }
							</td>
							<td align="center">
							${rs.jtcy2_gzdz }
							</td>
							<td align="center">
							${rs.jtcy2_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy2_lxdh1}
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy3_xm }
							</td>
							<td align="center">
							${rs.jtcy3_gx }
							</td>
							<td align="center">
							${rs.jtcy3_gzdz }
							</td>
							<td align="center">
							${rs.jtcy3_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy3_lxdh1}
							</td>
						</tr>
					</tbody>
					
				</table>
				<div>
				<table style="margin-bottom: 2px" width="100%" border="0" class="formlist" id="tab_qtxx">
					<!-- 学生其他信息 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx" >
					<tr>
							<th>
								银行名称
							</th>
							<td align="left">
								${rs.yhmc }
							</td>
							<th>
								银行卡号
							</th>
							<td colspan="2">
								${rs.yhkh}
							</td>
						</tr>
					<tr>
							<th width="13%">
								姓名拼音
							</th>
							<td width="30%">
								${rs.xmpy }
							</td>
							<th width="13%">
								曾用名
							</th>
							<td align="left" colspan="2">
								${rs.cym }
							</td>

						</tr>
						<tr>
							<th>
								身高(cm)
							</th>
							<td align="left">
								${rs.sg }
							</td>
							<th>
								体重(kg)
							</th>
							<td colspan="2">
								${rs.tz}
							</td>
						</tr>
						<tr>
						<th>
								特长
							</th>
							<td>
								${rs.tc }
							</td>
							<th>
										健康状况
									</th>
									<td colspan="2">
									${rs.jkzk }
									</td>
							</tr>
								<tr>
							<th>
								培养层次
							</th>
							<td colspan="">
								${rs.pyccmc }
							</td>
							<th>
								是否走读生
							</th>
							<td align="left" colspan="2">
								${rs.sfzd }
							</td>
						</tr>
							<tr>
							<th>
								考生类别
							</th>
							<td>
							${rs.kslbmc }
									</td>
									<th>
								入学方式
							</th>
							<td colspan="2">
								${rs.rxfsmc }
							</td>
							</tr>
							<tr>
							<th>
								培养方式
							</th>
							<td>
							${rs.pyfsmc }
									</td>
									<th>
								
							</th>
							<td colspan="2">
								
							</td>
							</tr>
							<tr>
							<th>
								学习经历
							</th>
							<td colspan="4">
								<div  style="word-break:break-all;width:97%">${rs.zd1 }</div>
							</td>
							</tr>
							<tr>
							<th>
								获奖经历
							</th>
							<td colspan="4">
								<div  style="word-break:break-all;width:97%">${rs.zd2 }</div>
							</td>
							</tr>
							<tr>
							<th>
								备注
							</th>
							<td colspan="4">
								<div  style="word-break:break-all;width:97%">${rs.bz }</div>
							</td>
							</tr>
					</tbody>
				</table>
				</div>
				<div>
				<table style="margin-bottom: 2px" width="100%" border="0" class="formlist" id="tab_szxx">
					<!-- 学生其他信息 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>思政信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_szxx" >
					
					<tr>
							<th width="13%">
								<div align="center">辅导员姓名</div>
							</th>
							<th >
								<div align="center">辅导员职工号</div>
							</th>
							<th>
								<div align="center">辅导员联系电话</div>
							</th>
							
						</tr>
						<logic:notEmpty name="fdyList">
							<logic:iterate id="fdy" name="fdyList" >
								<tr>
								<td align="center">${fdy.xm }</td>
								<td align="center">${fdy.zgh }</td>
								<td align="center">${fdy.lxdh }</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="fdyList">
							<tr><td colspan="3" align="center">暂无数据！</td></tr>
						</logic:empty>
						<tr>
							<th width="13%">
								<div align="center">班主任姓名</div>
							</th>
							<th >
								<div align="center">班主任职工号</div>
							</th>
							<th>
								<div align="center">班主任联系电话</div>
							</th>
							
						</tr>
						<logic:notEmpty name="bzrList">
							<logic:iterate id="bzr" name="bzrList">
								<tr>
								<td align="center">${bzr.xm }</td>
								<td align="center">${bzr.zgh }</td>
								<td align="center">${bzr.lxdh }</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bzrList">
							<tr><td colspan="3"  align="center">暂无数据！</td></tr>
						</logic:empty>
					</tbody>
				</table>
				</div>
				<!--  学生信息end -->
				 <!-- 业务模块详细信息列表 -->
			    <logic:present name="ckList">
	        	<logic:iterate id="ckmxobj" name="ckList">
	        		 <table width="100%" border="0" style="margin-bottom: 2px" class="formlist" id="tab_${ckmxobj.gnmk }">
						<thead>
							<tr onclick="" style="cursor: hand;">
								<th colspan="5">
									<span>${ckmxobj.gnmk }</span>
								</th>
							</tr>
						</thead>
						<tbody id="hi_${ckmxobj.gnmk }" >
							<tr>
								<td colspan="5" class="ywmkclass" id="td_${ckmxobj.gnmk }" style="padding:0;"></td>
							</tr>
						</tbody>
					</table>
	        	</logic:iterate>
	        </logic:present>
		       
		      <logic:notPresent name="sfxsgban">
				<div style="height:15px"></div>
				<table width="100%" border="0" class="formlist" style="position:fixed;_position:absolute; bottom:0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="关闭" onclick="Close()" type="button" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<script type="text/javascript">
			         jQuery(".smooth").click(function(){
			             var href = jQuery(this).attr("href");
			             var pos = jQuery(href).offset().top;
			             jQuery("html,body").animate({scrollTop: pos-70}, 400);
			             return false;
			         });
				</script>
			</logic:notPresent> 
			</div>
</html:form>
</body>
</html>