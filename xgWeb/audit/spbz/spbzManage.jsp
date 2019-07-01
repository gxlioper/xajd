<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		
		function addSpbz(){
			var splc = document.getElementById("splc").value;
			var url="/xgxt/xtwhSpbz.do?method=spbzManage&doType=add&splc="+splc;
			showTopWin(url);
		}
		
		function modiSpbz(){
			if (null == curr_row) {
				alert('请选择一行');
			} else {
				var tid = curr_row.getElementsByTagName('input')[0].value;
				var splc = document.getElementById("splc").value;
				var url = '/xgxt/xtwhSpbz.do?method=spbzManage&doType=update&id='+tid+"&splc="+splc;
				showTopWin(url);
			}
		}
		
		function delSpbz(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				var url="/xgxt/xtwhSpbz.do?method=spbzManage&";
				url+="&doType=del";
				refreshForm(url);
				hiddenMessage(true,true);
				BatAlert.showTips('正在操作，请稍等...');
			}else{
				alert("没有需要保存的记录!");
				return false;
			}
			
		}
		
		function ret(){
			var url="/xgxt/xtwhSplc.do?method=splcManage";
			window.location = url;
		}
		
		//查看审批流程中岗位用户
		function viewAssessor(){
			if (null == curr_row) {
				alert('请选择一行');
			} else {
				var tid = curr_row.getElementsByTagName('input')[0].title;
				//var tid2 = curr_row.getElementsByTagName('input')[0].value;
				var url="/xgxt/xtwhSpgw.do?method=spgwYhManage&doType=view&spgw="+tid;
				showTopWin(url,480,390);
			}
		
		}
		</script>
	</head>
	<body>

		<html:form action="/xtwhSpbz" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
            <html:hidden property="splc" styleId="splc"/>
			<html:hidden property="realTable" styleId="realTable"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="addSpbz();return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modiSpbz();return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="delSpbz();return false;" class="btn_sc"> 删除 </a></li>
                        <li><a href="#" onclick="ret();return false;" class="btn_fh"> 返回 </a></li>
                        <li><a href="#" onclick="viewAssessor();return false;" class="btn_ck" >查看岗位用户</a></li>
					</ul>
				</div>
				
				
				
				<!-- new 版本 -->
<%--			     <logic:equal name="superSearch" value="yes">--%>
<%--			     	<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
<%--			     </logic:equal>--%>
			     
			     <!-- old 版本 -->
<%--			     <logic:equal name="superSearch" value="no">--%>
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xtwhSpbz.do?method=spbzManage')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
<%--				</logic:equal>--%>
			</div>
			<div class="formbox">
			  <h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
					</span>
				</h3>

			  <logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
										title="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="2" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				  </div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=spbzForm"></jsp:include>
			  </logic:notEmpty>
			</div>
		</html:form>
		<logic:present  name="dxResult">
		<logic:equal value="false" name="dxResult">
			<script>
				alert("发送短信失败!");
			</script>
		</logic:equal>
		<logic:equal value="true" name="dxResult">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		</logic:present>
		<logic:present  name="result">
		<logic:equal value="false" name="result">
			<script>
				alert("保存失败!");
			</script>
		</logic:equal>
		</logic:present>
	</body>
</html>
