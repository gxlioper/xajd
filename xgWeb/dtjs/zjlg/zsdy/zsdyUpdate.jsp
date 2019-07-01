<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function zzgx(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top);
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<div class='tab'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<th colspan='2'>";
		dd_html += "<span>��֯��ϵת��</span>";
		dd_html += "</th>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "ʱ��";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zjsj' id='zjsj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "ת������";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='zjlx' id='zjlx' onchange='chZjlx(this.value)'><option value=''><option value='out'>ת��</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "ת����ַ";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zjdz' id='zjdz' maxlength='50'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "��ע";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<textarea name='zjbz' id='zjbz' rows='3' onblur='chLeng(this,60)'></textarea>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "<tfoot><tr><td colspan='2' align='right'>"
		dd_html += "<button type='button' onclick='saveZzgx()'>ȷ��</button>";
		dd_html += "<button type='button' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td></tr></tfoot>";
		dd_html += "</table>";
		dd_html += "</div>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
		
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveZzgx(){
		if($("zjsj")){
			if($("zjsj").value == ""){
				alert("��ȷ��ת��ʱ��");
				return false;
			}
		}
		if($("zjlx") && $("zjdz")){
			if($("zjlx").value == ""){
				alert("��ȷ��ת������");
				return false;
			}
			if($("zjlx").value == "out" && $("zjdz").value == ""){
				alert("��ȷ��ת����ַ");
				return false;
			}
		}

		var url = "/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=zz";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonZz").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function chZjlx(zjlx){
		if(zjlx == "out"){
			$("zjdz").disabled=false;;
		}else{
			$("zjdz").value="";
			$("zjdz").disabled=true;
		}
	}
	function saveZsdy(){
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var nd = $("nd").value;
		//var xsccdm = $("xsccdm").value;
		var kssj = $("ybdykssj").value;
		var jssj = $("ybdyjssj").value;
		if(xh==""){
			alert("ѧ��Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		if(xn=="" || xq == ""|| nd == ""){
			alert("��ȣ�ѧ��,ѧ�ڲ���Ϊ�գ�");
			return false;
		}
		if(kssj !="" && jssj != ""){
			if(kssj>jssj){
				alert("Ԥ����ʼʱ����ڽ���ʱ�䣬��ȷ�ϣ�");
				return false;
			}
		}
		var url = "/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=save";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function getZbmc(){
		var xh = $("xh").value;
		var zbmc = $("zbmc").value;
		dwr.engine.setAsync(false);
		if(xh !="" && zbmc == ""){
			zjlgZbglDAO.getZbmc(xh,function(data){
				$("zbmc").value=data;		
			});
		}
		dwr.engine.setAsync(true);
	}
	
	function addLxr(){
		if (confirm("���ǰ���ȱ������޸���Ϣ")) {
			var xh = $("xh").value;
			if(xh == ""){
				alert("��ȷ����Աѧ��");
				return false;
			}
			showTopWin('/xgxt/zjlgDtjsZsdy.do?method=lxrxxManage&blxr='+xh,750,550);
		}
	}
	</script>
	</head>
	<body onload="getZbmc()">
		<html:form action="/zjlgDtjsZsdy">
		<input type="hidden" id="url" name="url" value="/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
		<input type="hidden" name="doType" id="doType" value="${doType }" />
		<input type="hidden" name="pk" id="pk" value="${pk }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="searchtab">		
			<table class="formlist" id="rsTable">
			<thead>
				<tr>
					<th colspan="4">
						<span>��ʽ��Ա</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height: 23px">
					<th>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th>
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
					<th>
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="" styleId="xn">
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
					<th>
						�Ա�
					</th>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="" styleId="xq">
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
					<th>
						����֧��
					</th>
					<td align="left">
						<html:text name='rs' property="zbmc" styleId="zbmc" readonly="true"/>
					</td>
					<th>
						�뵳ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="rdsj" styleId="rdsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rdsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<th>
						ת��ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="zzsj" styleId="zzsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('zzsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						רҵ
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th>
						Ԥ����Ա��ʼʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="ybdykssj" styleId="ybdykssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdykssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<th>
						Ԥ����Ա����ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="ybdyjssj" styleId="ybdyjssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdyjssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�뵳��ϵ��
					</th>
					<td align="left">
						<html:text name='rs' property="rdlxr" styleId="rdlxr" readonly="true"/>
						<logic:notEqual name="doType" value="view">
						<button type="button" onclick="addLxr()"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
						</logic:notEqual>
					</td>
					<th>
						��ϵ������
					</th>
					<td align="left">
						<html:text name='rs' property="lxrxm" styleId="lxrxm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						��ϵ������֧��
					</th>
					<td align="left">
						<html:text name='rs' property="lxrzb" styleId="lxrzb" readonly="true"/>
					</td>
					<th>
						��ϵ�����ڰ༶
					</th>
					<td align="left">
						<html:text name='rs' property="lxrbj" styleId="lxrbj" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						��ϵ�˵绰
					</th>
					<td align="left">
						<html:text name='rs' property="lxrdh" styleId="lxrdh" onkeyup="checkInputData(this);" maxlength="20"/>
					</td>
					<th>
						��ϵ��������ò
					</th>
					<td align="left">
						<html:text name='rs' property="zzmm" styleId="zzmm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�μӻ���<br/>
						<font color="red">(��������200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="cjhdqk" styleId="cjhdqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						֧���������<br/>
						<font color="red">(��������200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="zbshqk" styleId="zbshqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						���ѽ������<br/>
						<font color="red">(��������200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="dfjnqk" styleId="dfjnqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�μ�����ѧϰ���<br/>
						<font color="red">(��������200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="zzxxqk" styleId="zzxxqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						��ע<br/>
						<font color="red">(��������60)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="3" style="width: 95%;word-break:break-all;" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" onclick="saveZsdy()">
								����
							</button>
							<logic:equal name="doType" value="update">
							<button type="button" id="buttonZz" onclick="zzgx()">
								��֯��ϵת��
							</button>
							</logic:equal>
						 </logic:notEqual>
						  <button type="button" name="�ر�" onclick="window.close();return false;" id="buttonClose">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
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
