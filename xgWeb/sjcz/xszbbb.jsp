<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function savefa(){
		var bz = document.getElementById("bz").value;
		if(bz.length>60){
			alert("��ע�������ܳ���60��");
			return false;
		}
		if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh-bbsj-jbr-sflq');
	}
	function ffupdate(){
		var bz = document.getElementById("bz").value;
		if(bz.length>60){
			alert("��ע�������ܳ���60��");
			return false;
		}
		
		dataCanModi(true)
	}
	</script>
</head>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ϸ��Ϣ�鿴</a>
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
					value="xm-xb-xymc-nj-zymc-bjmc-sfzh" />
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="${userOnLine}" />
				<input type="hidden" id="url" name="url" value="/sjcz/xszbbb.jsp" />
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣά��</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									ѡ��
								</button>
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
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
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
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
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
								ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" readonly="true"/>
							</td>
							<th>
								��������
							</th>
							<td align="left">
								<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<%--								<html:text name='rs' property="sqsj" styleId="sqsj" />--%>
								<html:text name='rs' property="sqsj" styleId="sqsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('sqsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								<font color="red">*</font>������
							</th>
							<td align="left">
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
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th>
								<font color="red">*</font>����ʱ�䣺
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
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th>
								<font color="red">*</font>�Ƿ���ȡ
							</th>
							<td align="left">
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
								���֤��
							</th>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh"
									readonly="true" />
							</td>
							<th>
								��Դ��
							</th>
							<td align="left">
								<html:text name='rs' property="syd" styleId="syd" />
							</td>
						</tr>

						<tr>
							<th>
								����ԭ��
							</th>
							<td align="left">
								<html:text name='rs' property="bbyy" styleId="bbyy"
									style="width: 200px" />
							</td>
							<th>
								��վ��
							</th>
							<td align="left">
								<html:text name='rs' property="hczm" styleId="hczm"
									style="width: 200px" />
							</td>
						</tr>
						<tr align="left">
							<th>
								��ע
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="word-break:break-all;width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					          <button type="button"  onclick="ffupdate();" id="buttonModi">
									�� ��
								</button>
								<button type="button" onclick="savefa();" id="buttonSave">
									�� ��
								</button>
					            <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
