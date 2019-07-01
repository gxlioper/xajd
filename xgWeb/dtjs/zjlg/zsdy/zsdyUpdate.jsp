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
		dd_html += "<span>组织关系转接</span>";
		dd_html += "</th>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "时间";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zjsj' id='zjsj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "转接类型";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='zjlx' id='zjlx' onchange='chZjlx(this.value)'><option value=''><option value='out'>转出</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "转出地址";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zjdz' id='zjdz' maxlength='50'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th align='right' width='30%'>";
		dd_html += "备注";
		dd_html += "</th>";
		dd_html += "<td align='left'>";
		dd_html += "<textarea name='zjbz' id='zjbz' rows='3' onblur='chLeng(this,60)'></textarea>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "<tfoot><tr><td colspan='2' align='right'>"
		dd_html += "<button type='button' onclick='saveZzgx()'>确定</button>";
		dd_html += "<button type='button' onclick='closeAdd1()'>关闭</button>";
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
				alert("请确定转接时间");
				return false;
			}
		}
		if($("zjlx") && $("zjdz")){
			if($("zjlx").value == ""){
				alert("请确定转接类型");
				return false;
			}
			if($("zjlx").value == "out" && $("zjdz").value == ""){
				alert("请确定转出地址");
				return false;
			}
		}

		var url = "/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=zz";
		showTips('处理数据中，请等待......');
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
			alert("学号为空，请确认！");
			return false;
		}
		if(xn=="" || xq == ""|| nd == ""){
			alert("年度，学年,学期不能为空！");
			return false;
		}
		if(kssj !="" && jssj != ""){
			if(kssj>jssj){
				alert("预备开始时间大于结束时间，请确认！");
				return false;
			}
		}
		var url = "/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=save";
		showTips('处理数据中，请等待......');
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
		if (confirm("添加前请先保存所修改信息")) {
			var xh = $("xh").value;
			if(xh == ""){
				alert("请确定党员学号");
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
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="searchtab">		
			<table class="formlist" id="rsTable">
			<thead>
				<tr>
					<th colspan="4">
						<span>正式党员</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height: 23px">
					<th>
						学号
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th>
						年度
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
						姓名
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						学年
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
						性别
					</th>
					<td align="left">
						<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th>
						学期
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
						所属支部
					</th>
					<td align="left">
						<html:text name='rs' property="zbmc" styleId="zbmc" readonly="true"/>
					</td>
					<th>
						入党时间
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
						转正时间
					</th>
					<td align="left">
						<html:text name="rs" property="zzsj" styleId="zzsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('zzsj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						专业
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th>
						预备党员开始时间
					</th>
					<td align="left">
						<html:text name="rs" property="ybdykssj" styleId="ybdykssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdykssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						班级
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<th>
						预备党员结束时间
					</th>
					<td align="left">
						<html:text name="rs" property="ybdyjssj" styleId="ybdyjssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('ybdyjssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						入党联系人
					</th>
					<td align="left">
						<html:text name='rs' property="rdlxr" styleId="rdlxr" readonly="true"/>
						<logic:notEqual name="doType" value="view">
						<button type="button" onclick="addLxr()"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
						</logic:notEqual>
					</td>
					<th>
						联系人姓名
					</th>
					<td align="left">
						<html:text name='rs' property="lxrxm" styleId="lxrxm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						联系人所在支部
					</th>
					<td align="left">
						<html:text name='rs' property="lxrzb" styleId="lxrzb" readonly="true"/>
					</td>
					<th>
						联系人所在班级
					</th>
					<td align="left">
						<html:text name='rs' property="lxrbj" styleId="lxrbj" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						联系人电话
					</th>
					<td align="left">
						<html:text name='rs' property="lxrdh" styleId="lxrdh" onkeyup="checkInputData(this);" maxlength="20"/>
					</td>
					<th>
						联系人政治面貌
					</th>
					<td align="left">
						<html:text name='rs' property="zzmm" styleId="zzmm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						参加活动情况<br/>
						<font color="red">(限制字数200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="cjhdqk" styleId="cjhdqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						支部生活情况<br/>
						<font color="red">(限制字数200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="zbshqk" styleId="zbshqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						党费缴纳情况<br/>
						<font color="red">(限制字数200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="dfjnqk" styleId="dfjnqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						参加政治学习情况<br/>
						<font color="red">(限制字数200)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="zzxxqk" styleId="zzxxqk" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						备注<br/>
						<font color="red">(限制字数60)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="3" style="width: 95%;word-break:break-all;" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" onclick="saveZsdy()">
								保存
							</button>
							<logic:equal name="doType" value="update">
							<button type="button" id="buttonZz" onclick="zzgx()">
								组织关系转接
							</button>
							</logic:equal>
						 </logic:notEqual>
						  <button type="button" name="关闭" onclick="window.close();return false;" id="buttonClose">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
