<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function searchRs(){
		var act = $("zzlb").value;
		if(act == "jgmb"){
			allNotEmpThenGo('/xgxt/cadremanInfo.do?act=jgmb');
		}else if(act == "ybybd"){
			allNotEmpThenGo('/xgxt/cadremanInfo.do?act=ybybd');
		}
	}
	
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/ArmyStuInfo" method="post">
			<div class="tab_cur" id="title_m">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="toolbox">
				 <!-- ��ť -->
				 <div class="buttonbox">
				    <ul>
				    	 <logic:equal value="yes" name="writeAble" scope="request">
							<logic:equal value="jgmb" name="act" scope="request">
								<li> <a href="#"  onclick="showDataFrame('viewCadremanInfo.do','add',550,350,'jgmb')" class="btn_zj"> �� �� </a> </li>
				     		</logic:equal>
				     		<logic:equal value="ybybd" name="act" scope="request">
				     	 		<li> <a href="#" onclick="showDataFrame('viewCadremanInfo.do','add',550,350,'ybybd')" class="btn_zj"> �� �� </a> </li>
				     		</logic:equal>
				     		<logic:equal value="jgmb" name="act" scope="request">
				     		 <li> <a href="#" onclick="showDataFrame('viewCadremanInfo.do','modi',550,350,'jgmb')" class="btn_xg"> �޸� </a> </li>
							</logic:equal>
							<logic:equal value="ybybd" name="act" scope="request">
				     		 <li> <a href="#"onclick="showDataFrame('viewCadremanInfo.do','modi',550,350,'ybybd')" class="btn_xg"> �޸� </a> </li>
							</logic:equal>
							<li> <a href="#"  onclick="showDataFrame('viewCadremanInfo.do','del',550,350)" class="btn_sc"> ɾ�� </a> </li>
							</logic:equal>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a> </li>
							<li> <a href="#" onclick="dataExport()" class="btn_dc"> ���� </a> </li>
				    </ul>
				 </div>
				 <div class="searchtab">
					<table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					              <input type="hidden" name="go" value="a" />
					              <button type="button" class="btn_cx" id="search_go" 
					              	onclick="searchRs();return false;">
					              	�� ѯ
					              </button>
					              &nbsp;&nbsp;&nbsp;&nbsp;
					              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
					              	�� ��
					              </button>
					            </div>
					          </td>
					        </tr>
					      </tfoot>
						<tbody>
							<tr>
								<th nowrap>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ��
								</th>
								<td>
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ѧ��
								</th>
								<td colspan="5">
									<html:text property="xh" style="width:100px"></html:text>
								</td>
									
									<html:select property="zzlb" styleId="zzlb" style="display:none"
										onchange="">
										<html:option value="jgmb">�������</html:option>
										<html:option value="ybybd">Ԥ���۲���</html:option>
									</html:select>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 				 ��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
						  <table summary="" class="datelist" align="" width="100%">
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
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showDataFrame('viewCadremanInfo.do','modi',550,350)">
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
							</tbody>
						</table>
				</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
