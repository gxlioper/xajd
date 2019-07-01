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
		<script type="text/javascript">
		//查看
		function getView(url){
			url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			showTopWin(url,800,600);
		}		

		//修改
		function modi(url){
			if(curr_row != null){
				url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
				showTopWin(url,800,600);
				return false;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		//删除
		function submitInfo(url,doType,msg){
			var pkV=document.getElementsByName("checkVal");
			var	blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked){
					blog=true;
					break;
				}
			}
			if(blog){
				confirmInfo("确定"+msg+"吗？",function(data){
					if("ok"==data){
						url+="&doType="+doType;
						showTips('处理数据中，请等待......');
						refreshForm(url);
					}
				});
			}else{
				alertInfo('请勾选要操作的数据行！');
				return false;
			}
		}
		
		function searchRs(){
			refreshForm('xljk_zjzy_tsxswh.do');
		}
		</script>
	</head>
	<body>
		<html:form action="/xljk_tsxswh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			 <!-- 按钮 -->
			 <!-- 有读写权 -->
			 <div class="toolbox">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="showTopWin('/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=add',800,600);return false;" class="btn_zj"> 增加 </a> </li>
				    <li> <a href="#" onclick="modi('/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=modi');return false;" class="btn_xg"> 修改 </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/xljk_tsxswh.do?method=tsxswhManage','del','删除');return false;" class="btn_sc"> 删除 </a> </li>
					<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
					<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
					
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
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
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
								ondblclick="getView('/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=view');return false;">
								<td align="center">
									<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="hidden" id="pkValue" name="pkValue" 
											value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="0" length="7">
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=tsxswhForm"></jsp:include>
			  	<!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
