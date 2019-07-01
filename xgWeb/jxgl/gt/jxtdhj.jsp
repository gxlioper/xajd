<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script language="javascript">
		function chBzList(jxnd){
		getJxglDAO.getTdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
		}
		</script>
		<html:form action="/jxglgt?method=jgxx" method="post">
			<div class="tab_cur">
	          <p class="location">
	          	<em>��ǰ����λ��:</em>
		          <a>
		         		 ��ѵ���� - ��ѵ���� - ��ѵ�Ŷӻ�
		          </a>
	          </p>
	        </div>
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="lddmV" id="lddmV" />
			<input type="hidden" name="ljdmV" id="ljdmV" />
			<input type="hidden" name="pjdmV" id="pjdmV" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
					
			<logic:equal value="yes" name="writeAble">
		        <div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showTopWin('jxglgt.do?method=jxtdhjOne&type=add',600,400,false);return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="#" onclick="if(curr_row == null){alert('��ѡ��Ҫ�޸ĵ���Ϣ!');return false;}showTopWin('/xgxt/jxglgt.do?method=jxtdhjOne&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,600,400);return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="if(curr_row == null){alert('��ѡ��Ҫɾ������Ϣ!');return false;}refreshForm('/xgxt/jxglgt.do?method=jxtdhjOne&type=del&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value);return false;" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a>
							</li>
							<li>
								<a href="#" onclick="dataExport();return false;" class="btn_dc">��������</a>
							</li>
						</ul>
					</div>
				</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jxglgt.do?method=jxtdhj');">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
						<tr>
							<th >
								ѧ�꣺
							</th>
							<td >
								<html:select property="xn" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th >
								�꼶��
							</th>
							<td >
								<html:select property="jxnd" style="width:120px" styleId="jxnd" onchange="chBzList(this.value);">
									<html:options collection="njList" labelProperty="njmc" property="njdm"/>
								</html:select>
							</td>
							<th >
								ѧ�ڣ�
							</th>
							<td >
								<html:select property="xq" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���ӣ�
							</th>
							<td>
								<html:select property="lddm" style="width:120px">
									<html:options collection="ldList" labelProperty="bzmc" property="bzdm"/>
								</html:select>
							</td>
							<th>
								���
							</th>
							<td>
								<html:select property="jxdm" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="jxList" labelProperty="jxmc" property="jxdm"/>
								</html:select>
							</td>
							<th></th>
							<td ></td>
						</tr>
				</tbody>
			</table>
		</div>			
				<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������;˫��һ����¼���Խ����޸�ҳ�档</font>
							
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showTopWin('/xgxt/jxglgt.do?method=jxtdhjOne&type=edit&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,600,400)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxglForm"></jsp:include>
				</logic:notEmpty>
				
				</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
