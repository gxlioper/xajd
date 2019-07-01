<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function delData(){
				var pkValue=document.getElementsByName("primarykey_pkValue");
				var blog=false;
				for(i=0;i<pkValue.length;i++){
					if(pkValue[i].checked){
						blog=true;
						break;
					}
				}
				if(!blog){
					alert("��ѡ����Ҫɾ��������!");
					return false;
				}
				
				if(confirm("ȷ��Ҫɾ���ѹ�ѡ���쳣������")){
					refreshForm("/xgxt/dataDetect.do?method=disposeManage&doType=del");
				}
			}
			
			function goDataDetect(){
				refreshForm("/xgxt/dataDetect.do?method=detectManage&mklx="+$("mklx").value);
			}
		</script>
	</head>

	<body onload="">
		<html:form action="/dataDetect" >
			<button id="search_go"
				onclick="allNotEmpThenGo('gyglYcjc.do?method=queryYcsj')"
				style="display:none"></button>
			<html:hidden property="tableName" styleId="tableName" />
			<html:hidden property="yclxdm" styleId="yclxdm" />
			<html:hidden property="mklx" styleId="mklx" />
			<html:hidden property="pk" styleId="pk" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�ڱ�ģ���ѯ������������,��Ϊ"�쳣����"����ѡ��Ҫɾ����"�쳣����"�󣬵��"ɾ��"��ť���Խ���ɾ����
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ���� end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="delData();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="goDataDetect();return false;"
									class="btn_fh">����</a>
							</li>
						</logic:equal>
					</ul>
				</div>
			</div>
			<!-- ��ѯ���-->
			<div class="formbox">
				<h3 class="datetitle_01">
					<logic:empty name="rs">
						<font color="red">���쳣����</font>
					</logic:empty>
					<logic:notEmpty name="rs">
					�����쳣 &nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">${yclxmc}</font>
					</logic:notEmpty>
				</h3>
				
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center">
									<td>
										<input type="checkbox" style="display:none" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
										<td nowrap="nowrap"
											id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:notEmpty name="rs">
							<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" ondblclick="">
									<!-- checkbox -->
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td nowrap="nowrap">
											<input type="checkbox" id="checkVal"
												name="primarykey_pkValue" value="${v }" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>
							<!--���� end-->
						</table>
					</div>
					<!--��ҳ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=dataDetectForm"></jsp:include>
					<!--��ҳend-->
				
			</div>
			<!-- ��ѯ��� end-->

		</html:form>

	</body>
</html>
