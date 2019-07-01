<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script type="text/javascript">	
	
	function dyzz(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 3;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<div class='tab'><table width='300' class='formlist'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<th colspan='2'>";
		dd_html += "<span>תΪ��չ����ʱ��<span>";
		dd_html += "</th>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "ʱ��:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' onclick='saveYbdy()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table></div>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
		
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveYbdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("��ȷ��תΪ��չ����ʱ��");
				return false;
			}
		}
		var url = "/xgxt/czxxDtjsDyxx.do?method=jjfzUpdate&doType=zz";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonZz").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function getJjfzxx(){
		var xh=$("xh").value;
		var xn=$("xn").value;
		var xq=$("xq").value;
		if(xh !="" && xn!="" && xq!=""){
			var tableName="view_sjxy_rdjjfzxx";
			var colList =["kssj","bz","zzzt"];
			var pk = "xn||xq||xh";
			var pkValue = xn + xq + xh;
			getDtjsInfo.getDyxxInfo(tableName, pk, pkValue,colList,function(data){
				if(data!=null){
					if(data.bz !=""){
						$("bz").value = data.bz;
					}else{
						$("bz").value = "";
					}
					if(data.kssj !=""){
						$("kssj").value = data.kssj;
					}else{
						$("kssj").value = "";
					}
					if(data.zzzt !=""){
						$("zzzt").value = data.zzzt;
					}else{
						$("zzzt").value = "";
					}
				}
			});
		}
	}
	function jd(){
		if($("jd")){
			$("jd").focus();
		}
	}
	</script>
	</head>
	
	<body onload="jd()">
		<html:form action="/czxxDtjsDyxx">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xydm-xymc-zymc-bjmc-mzmc"/>
		<input type="hidden" id="url" name="url" value="/xgxt/czxxDtjsDyxx.do?method=jjfzUpdate&doType=add"/>
		<!-- ������Ϣ -->
		<logic:equal name="xxdm" value="12317">
			<input type="hidden" id="tableName" name="tableName" value="view_czxx_rdsq"/>
			<input type="hidden" id="lx" name="lx" value="������"/>
		</logic:equal>
		<!-- ͨ�� -->
		<logic:notEqual name="xxdm" value="12317">
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>
		</logic:notEqual>
		<html:hidden name='rs' property="zzzt" styleId="zzzt"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�뵳��������</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
					<logic:equal name="doType" value="add">
			        	<div class="bz">"<span class="red">*</span>"Ϊ������</div>
			        </logic:equal>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          	<button type="button" name="�ύ" onclick="saveUpdate('/xgxt/czxxDtjsDyxx.do?method=jjfzUpdate&doType=save','xh-xn-xq-nd-kssj')">�� ��</button>
			          <logic:equal name="doType" value="update">
						<!-- ������Ϣ -->
						<logic:equal name="xxdm" value="12317">
							<button type="button" id="buttonZz" onclick="dyzz()">ת�뷢չ����</button>
						</logic:equal>
					  </logic:equal>
			          
			          </logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height: 23px">
					<th align="right">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="sendXx();return false;"
								class="btn_01" id="buttonFindStu">
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
						���
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="nd" style="" styleId="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="nd" styleId="nd"/>
							<html:select name="rs" property="nd" style="" styleId="nd" disabled="true">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th align="right">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="" styleId="xn" onchange="getJjfzxx()">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th align="right">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="" styleId="xq" onchange="getJjfzxx()">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xq" styleId="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:hidden name='rs' property="xydm" styleId="xydm"/>
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th align="right">
						 <logic:notEqual name="doType" value="view">
						<font color="red">*</font>
						</logic:notEqual>
						����ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="kssj" styleId="kssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('kssj','y-mm-dd');"/>	
					</td>
					<th align="right">
						ȷ���뵳��������ʱ��
					</th>
					<td align="left">
						<html:text property="lwjjfzsj" styleId="lwjjfzsj"
								   value="${rs.lwjjfzsj }" 
								   onblur="dateFormatChg(this)"
							       onclick="return showCalendar(this.id,'y-mm-dd');"
								   readonly="true"></html:text>
					</td>
				</tr>
				<%@ include file="dtxxInfo.jsp"%>
				<tr style="height: 23px">
					<th align="right">
						��ע<br/><font color="red">(��60��)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" cols="66" onblur="chLeng(this,60)"/>
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
