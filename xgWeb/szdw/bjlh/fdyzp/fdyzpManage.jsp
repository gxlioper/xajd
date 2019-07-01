<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		//查看自评表
		function getFdyzp(url){
			url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			showTopWin(url,800,600);
		}		

		//修改自评表
		function modiFdyzp(url){
			if(curr_row != null){
				if(curr_row.getElementsByTagName('input')[1].value=="0"){
					url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
					showTopWin(url,800,600);
					return true;	
				}else{
					alertInfo('此记录已被使用，不能再次进行修改！');
					return false;
				}
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		//显示复制操作层	
		function showCopyFdyzp(){
			if(curr_row != null){
				viewTempDiv('复制','showDiv',280,200);
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		//复制自评表
		function copyFdyzp(url,tj){
			url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			saveUpdate(url,tj);
		}

		//更改数据启用状态 ,删除
		function submitInfo(url,doType,msg){
			if(curr_row != null){
				if(doType=="del" && curr_row.getElementsByTagName('input')[1].value=="1"){
					alertInfo('此记录已被使用，不能进行删除！');
					return false;
				}else{
					confirmInfo("确定"+msg+"吗？",function(data){
						if("ok"==data){
							url+="&doType="+doType+"&pkValue="+curr_row.getElementsByTagName('input')[0].value;
							showTips('处理数据中，请等待......');
							refreshForm(url);
						}
					});
				}
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}
		
		function searchRs(){
			refreshForm('bjlh_fdykh_fdyzp.do');
		}
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
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
					已有辅导员作答的自评表不可“修改”或“删除”，未启用的自评表无法作答；
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			 <!-- 按钮 -->
			 <!-- 有读写权 -->
			 <div class="toolbox">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="showTopWin('/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=add',800,600);return false;" class="btn_zj"> 增加 </a> </li>
				    <li> <a href="#" onclick="modiFdyzp('/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=modi');return false;" class="btn_xg"> 修改 </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/bjlh_fdyzp.do?method=fdyzpManage','del','删除');return false;" class="btn_sc"> 删除 </a> </li>
					<li> <a href="#" onclick="showCopyFdyzp();return false;" class="btn_sx"> 复制 </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/bjlh_fdyzp.do?method=fdyzpManage','qy','启用');return false;" class="btn_shtg"> 启用 </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/bjlh_fdyzp.do?method=fdyzpManage','ty','停用');return false;" class="btn_shbtg"> 停用 </a> </li>
					
				    </ul>
				 </div>
				 <!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									提示：双击一行可以查看详细信息；单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
			    	<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none"></td>
								<logic:iterate id="tit" name="topTr" indexId="index">
									<td onclick="tableSort_hc(this,1)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="getFdyzp('/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=view');return false;">
								<%--<input type="checkbox" id="checkVal" name="checkVal" 
									<logic:iterate id="v" name="s" offset="5" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>											
								--%>
								<td style="display: none">
									<input type="hidden" id="pkValue" name="pkValue" 
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="hidden" id="kfxg" name="kfxg" 
										value="<logic:iterate id="v" name="s" offset="5" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="4">
									<td>
											<bean:write name="v" />
									</td>
								</logic:iterate>
								
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdyzpForm"></jsp:include>
			  	<!--分页显示-->
			</logic:notEmpty>
			</div>
			<div id="showDiv" style="display:none;height: 500px" align="center">
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="2">
								<span>复制信息</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td align='right' width='25%'>
								<font class="red">*</font>学年
							</td>
							<td align='left'>
								<html:select property="xn" styleId="select_xn">
										<html:options collection="addxnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align='right' width='25%'>
								<font class="red">*</font>名称
							</td>
							<td align='left'>
								<input type="text" name="mc" id="mc" maxlength="100"/>
							</td>
						</tr>
					</tbody>		
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<button type="button" onclick="copyFdyzp('/xgxt/bjlh_fdyzp.do?method=fdyzpManage&doType=copy','xn-mc')">
										复制
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick='hiddenMessage(true,true)'>
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
