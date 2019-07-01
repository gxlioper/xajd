<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			// 显示结果对象
			var resultSet = jQuery("#resultSet").val();
			allNotEmpThenGo('gyglnew_cwfpgl_cwfp.do?resultSet='+resultSet);
		}
		
		//设置查询提示层位置
		function setSearchMsgWz(left,top){
			$("input_mhcx_msg").style.left=left;
			$("input_mhcx_msg").style.top=top;
		}		

		//床位手动分配
		function goQssdfpByHand(act){

			if(!curr_row){
				alertInfo('请选择一行');
				return false;
			}

			// 显示结果对象
			var resultSet = jQuery("#resultSet").val();
			// userType
			var userType = jQuery("#userType").val();
			if("xy"==userType && "xyli" == resultSet){
				alertInfo(jQuery("#xbmc").val()+"用户不能对"+jQuery("#xbmc").val()+"进行分配，请切换结果集再进行操作！");
				return false;
			}
			
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			$("primarykey_checkVal").value=pkValue;
			
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&act="+act+"&resultSet="+resultSet;
			refreshForm(url);
			BatAlert.showTips('正在操作，请稍等...');
		}	
		
		jQuery(function() {

			setSearchMsgWz('130px','145px');
			
			// 床位分配对象
			var cwfpdx = jQuery("#cwfpdx").val();
			
			// 显示结果对象
			var resultSet = jQuery("#resultSet").val();
			
			// 床位分配对象：专业
			if("zydm" == cwfpdx){
				//隐藏显示结果集(班级）
				jQuery(".imitation_xl ul li:last-child").hide();
			}
			
			// 床位分配对象：学院
			if("xydm" == cwfpdx){
				//隐藏显示结果集
				jQuery("#xsjglb").hide();
			}else{
				if("xyli" == resultSet){
					resultSet = jQuery("#xbmc").val();
				}else if("zyli"== resultSet){
					resultSet = "专业";
				}else if("bjli"== resultSet){
					resultSet = "班级";
				}
				jQuery(".imitation_xl div").html(resultSet);
			
				jQuery(".imitation_xl div").click(function(event){
					event.stopPropagation();
					if(jQuery(this).prev("ul").is(":hidden")){
						jQuery(this).prev("ul").show();
					}else{
						jQuery(this).prev("ul").hide();
					}
				});
				jQuery(".imitation_xl ul li a").click(function(){
					var html1	=	jQuery(this).html();
					jQuery(".imitation_xl div").html(html1);
					jQuery(".imitation_xl ul").hide();
					
					var liid = jQuery(this).parent().attr("id");
					allNotEmpThenGo('gyglnew_cwfpgl_cwfp.do?resultSet='+liid);
					BatAlert.showTips('正在操作，请稍等...');
				});
				
				jQuery("body").click(function(){jQuery(".imitation_xl ul").hide()});
	
			}
	    });
		    
		</script>
	</head>
	<body >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				查询结果中的“已分配床位数”指分配给该年级<bean:message key="lable.xsgzyxpzxy" />/专业/班级该性别的床位总数；<br/>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gyglnew_cwfpgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="primarykey_checkVal" id="primarykey_checkVal" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>						
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand('to_fp');return false;"
								class="btn_sh">
								分配
							</a>
						</li>
						<!-- 取消分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand('to_qxfp');return false;"
								class="btn_qxsh">
								取消分配
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>		
				<!-- 按钮 end-->	
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
					</span>
					<logic:notEmpty name="rsArrList">
					    <input type="hidden" id="userType" name="resultSet" value="${userType }" />
						<input type="hidden" id="resultSet" name="resultSet" value="${resultSet }" />
						<input type="hidden" id="cwfpdx" name="cwfpdx" value="${cwfpdx }" />
				        <div class="xl" style="float:right" id="xsjglb">
				        	<div class="xl1">切换显示结果集：</div>
				            <div class="imitation_xl">
				                <ul style="width:65px;">
				                    <li id="xyli"><a href="javascript:;"><bean:message key="lable.xb" /></a></li>
				                    <li id="zyli"><a href="javascript:;">专业</a></li>
				                    <li id="bjli"><a href="javascript:;">班级</a></li>
				                </ul>
				                <div style="width:65px;"><bean:message key="lable.xb" /></div>
				            </div>
				        </div>
					</logic:notEmpty>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort_hc(this,1)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:empty name="rsArrList">
					  	<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px" style="display: none;">
									<input type="checkbox" id="pk_${index }"
									value="${v }"/>
								</td>
							</logic:iterate>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<!--内容 end-->
					<!-- 补空行 -->
					<%--<%@ include file="/comm/noRows.jsp"%>
					--%><!-- 补空行 end-->
				</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwfpglForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
		</html:form>
	</body>
</html>