<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
 
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function dyzz(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 200;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top) / 3;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "-----------------�뵳��������-----------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "��ʼʱ��:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveZsdy()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
		
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	
	function saveZsdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("��ȷ����ʼʱ��");
				return false;
			}
		}
		var url = "/xgxt/czxxDtjsDyxx.do?method=rdsqUpdate&doType=zz";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonZz").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	</script>
	</head>
	
	<body>
		<html:form action="/czxxDtjsDyxx">
		<!-- ������Ϣ -->
		<logic:equal name="xxdm" value="12317">
			<input type="hidden" id="tableName" name="tableName" value="view_tyxxb"/>
			<input type="hidden" id="lx" name="lx" value="��Ա"/>
		</logic:equal>
		<!-- ͨ�� -->
		<logic:notEqual name="xxdm" value="12317">
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>
		</logic:notEqual>
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xydm-xymc-zymc-bjmc-nj-rxrq"/>
		<input type="hidden" id="url" name="url" value="/xgxt/czxxDtjsDyxx.do?method=rdsqUpdate&doType=add"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�뵳����</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	 <logic:notEqual name="doType" value="view">
			        			<div class="bz">
			        				"<span class="red">*</span>"Ϊ������
			        			</div>
			        	</logic:notEqual>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          	<button type="button" name="�ύ" onclick="saveUpdate('/xgxt/czxxDtjsDyxx.do?method=rdsqUpdate&doType=save','xh-sqsj')">�� ��</button>
			       		<logic:equal name="doType" value="update">
						<!-- ������Ϣ -->
						<logic:equal name="xxdm" value="12317">
						<button type="button" name="�ύ" onclick="dyzz();">תΪ�뵳��������</button>
						</logic:equal>
						</logic:equal>
			        	</logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          	</div></td>
			      </tr>
			</tfoot>
			
				<tbody>
				<tr  >
					<th >
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left" width="40%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th align="right">
						<logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						����ʱ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:text name="rs" property="sqsj" styleId="sqsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('sqsj','y-mm-dd');"/>	
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="sqsj" styleId="sqsj" readonly="true" />
						</logic:notEqual>
					</td>
				</tr>
				<tr  >
					<th align="right">
						����
					</th>
					<td align="left">
						${rs.xm }
					</td>
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						${rs.xb }
					</td>
				</tr>
				<tr  >
					<th align="right">
						�꼶
					</th>
					<td align="left">
						${rs.nj }
					</td>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						${rs.xymc }
					</td>
					
				</tr>
				<tr  >
					<th align="right">
						רҵ
					</th>
					<td align="left">
						${rs.zymc }
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th>��ѧ����</th>
					<td colspan="3">${rs.rxrq }</td>
				</tr>
				<tr  >
					<th align="right">
						��ע<br/><font color="red">(��250��)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" cols="66" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>