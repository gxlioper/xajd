<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script type="text/javascript">	
		function printBb(){
			var xh = $("xh").value;
			var xn = $("xn").value;
			var pkValue = xh+xn;
			window.open('gzdxJxgl.do?method=mhxPrint&pkValue='+pkValue);
		}
		
		function getInfo(cod,xh){
			var doType = $("doType").value;
			if (cod == 13 && xh != "") {
				var url = "/xgxt/njjsDtjs.do?method=tyxxUpdate";
				url+="&xh="+xh;
				url+="&doType="+doType;
				refreshForm(url);
			}
		}
	</script>
	</head>
	<body onload="">
		<html:form action="/njjsDtjs">
		<!-- ������ -->
		<input type="hidden" id="url" name="url" value="/xgxt/njjsDtjs.do?method=tyxxUpdate"/>
		<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj"/>
		<input type="hidden" id="lx" name="lx" value="ѧ��"/>
		<%@ include file="/jxgl/hiddenValue.jsp"%>
		<!-- ������ end-->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
				<table class="formlist" border="0" id="rsTable" align="center"
					style="width: 100%">
				<thead>
					<tr>
						<th colspan="4"><span>ѧ����Ϣ</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<logic:equal name="doType" value="add">
								<html:text name='rs' property="xh" styleId="xh" onkeydown="getInfo(event.keyCode,this.value)"/>
								<button type="button" onclick="sendXx();return false;"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
							</logic:notEqual>
						</td>
						<th>
							����
						</th>
						<td>
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							${rs.xb }
						</td>
						<th>
							���֤��
						</th>
						<td>
							${rs.sfzh }
						</td>
					</tr>
					<tr>
						<th>
							�꼶
						</th>
						<td>
							${rs.nj }
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							${rs.xymc }
						</td>
					</tr>	
					<tr>
						<th>
							רҵ
						</th>
						<td>
							${rs.zymc }
						</td>
						<th>
							�༶
						</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td>
							${rs.csrq }
						</td>
						<th>
							��ϵ�绰
						</th>
						<td>
							${rs.lxdh }
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							${rs.mzmc }
						</td>
						<th>
							������ò
						</th>
						<td>
							${rs.zzmmmc }
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td colspan="3">
							${rs.jg }
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4"><span>������Ϣ</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							����ʱ��
						</th>
						<td>
							<html:text name="rs" property="rtsj" styleId="rtsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rtsj','y-mm-dd');"/>		
						</td>
						<th>
							�뵳ʱ��
						</th>
						<td>
							<html:text name="rs" property="rdsj" styleId="rdsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rdsj','y-mm-dd');"/>	
						</td>
					</tr>
					<tr>
						<th>
							���ŵص�
						</th>
						<td>
							<html:text name="rs" property="rtdd" maxlength="50"/>
						</td>
						<th>
							��Ա֤���
						</th>
						<td>
							<html:text name="rs" property="tyzbh" 
								onkeypress="return onlyNum(this,20)" 
								style="ime-mode:disabled"/>
						</td>
					</tr>
					<tr>
						<th>
							��������־Ը��
						</th>
						<td>
							<html:select name="rs" property="ywrtzys" style="" styleId="ywrtzys">
								<html:option value=""></html:option>
								<html:options collection="ywlxList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th>
							ȫ���ƽ���ѧ��
						</th>
						<td>
							<html:select name="rs" property="xl" style="" styleId="xl">
								<html:option value=""></html:option>
								<html:options collection="xlList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							��ע
							<font color="red">(��500��)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" name="�ύ" id="buttonSave" onclick="saveUpdate('/xgxt/njjsDtjs.do?method=tyxxUpdate&doType=save','xh');">�� ��</button>
						  </logic:notEqual>
						  <button type="button" name="�ر�" id="buttonClose"  onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<script>
					if($("message")	&& $("message").value != ""){
						alert($("message").value);
						dialogArgumentsQueryChick();
						window.close();
					}	
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
