<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.utils.GetTime"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function savefa(){
			var bz = document.getElementById("bz").value;
			if(bz.length>60){
				alertInfo("��ע�������ܳ���60��",function(){});
				return false;
			}
			if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh-bbsj-jbr-sflq');
		}
		function ffupdate(){
			var bz = document.getElementById("bz").value;
			if(bz.length>60){
				alertInfo("��ע�������ܳ���60��",function(){});
				return false;
			}
			
			dataCanModi(true);
		}
	</script>
	<body onload="checkWinType();dataManLoad()">
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ճ����� - У�չ��� - ���� </a>
				</p>
			</div>

			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
			    	alert("�������ѧ����Ч!");
			    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle"
					value="sqsj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/xhbbb.jsp" />

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										ע������<font color="red">*</font>���ֶα�����д
									</div>
									<div class="btn">
										<button type="button" class="button2" id="buttonModi" onclick="ffupdate()">
											�� ��
										</button>
										<button type="button" class="button2" onclick="savefa();" style="width:80px"
											id="buttonSave">
											�� ��
										</button>
										<button type="button" class="button2" id="buttonClose" onclick="Close();return false;">
											�� ��
										</button>
									</div>

								</td>
							</tr>
						</tfoot>
						<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<th>
									<font color="red">*</font>ѧ��
								</th>
								<td>
									<html:text name='rs' property="xh" readonly="readonly"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:equal name="doType" value="add">
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
								</td>
								<th>
									<font color="red">*</font>���
								</th>
								<td align="left">
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td>
									<html:text name='rs' property="xm" styleId="xm" />
								</td>
								<th>
									<font color="red">*</font>ѧ��
								</th>
								<td>
									<html:select name="rs" property="xn" style="width:90px"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									<html:text name='rs' property="xb" styleId="xb" />
								</td>
								<th>
									<font color="red">*</font>ѧ��
								</th>
								<td>
									<html:select name="rs" property="xq" style="width:90px"
										styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:text name='rs' property="nj" styleId="nj" />
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<logic:empty name="rs" property="sqsj">
										<html:text name='rs' property="sqsj" styleId="sqsj" value="${sqsjnyr}" />
									</logic:empty>
									<logic:notEmpty name="rs" property="sqsj">
										<html:text name='rs' property="sqsj" styleId="sqsj"/>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:text name='rs' property="xymc" styleId="xy" />
								</td>
								<th>
									<font color="red">*</font>������
								</th>
								<td>
									<html:select name="rs" property="jbr" style="width:90px"
										styleId="jbr">
										<html:option value=""></html:option>
										<html:options collection="jbrList" property="jbrgh"
											labelProperty="jbrxm" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:text name='rs' property="zymc" styleId="zy" />
								</td>
								<th>
									<font color="red">*</font>����ʱ��
								</th>
								<td align="left">
									<html:text name='rs' property="bbsj" styleId="bbsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('bbsj','y-mm-dd');" />
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:text name='rs' property="bjmc" styleId="bj" />
								</td>
								<th>
									<font color="red">*</font>�Ƿ���ȡ
								</th>
								<td>
									<html:select name="rs" property="sflq" style="width:90px"
										styleId="sflq">
										<html:option value=""></html:option>
										<html:options collection="ynList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									����ԭ��
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="bbyy" styleId="bbyy"
										style="width: 350px" maxlength="25"/>
								</td>
							</tr>
							<tr>
								<th>
									��ע<br/>
									<font color="red">
										(��60��)
									</font>
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='bz' style="word-break:break-all;width:99%" onblur="chLeng(this,60)"
										rows='5' />
								</td>
							</tr>
						</tbody>
					</table>
			</logic:notEmpty>
			<logic:equal name="result" value="true">
				<script type="text/javascript">
				alertInfo("����ɹ�!",function(t){
					if(t=="ok"){
						window.dialogArguments.document.getElementById('search_go').onclick();
						Close();
					}
				});
				
				</script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script type="text/javascript">
				alertInfo("����ʧ��!",function(t){
					if(t=="ok"){
						window.dialogArguments.document.getElementById('search_go').onclick();
						Close();
					}
				});
				</script>
			</logic:equal>
		</html:form>

	</body>
</html>
