<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<%--<script type='text/javascript' src='js/calendar/calendar-zh.js'></script>--%>
		<%--<script type='text/javascript' src='js/calendar/calendar.js'></script>--%>
		<script type="text/javascript" src="xsgzgl/xsxx/general/xsxxcj/js/xsxxcj.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type="text/javascript">
		function change(id,cs){
			var temp=cs.split('!!');
			var obj=jQuery('#'+id);
			if(obj.val()=='是'){
				var jqTemp=null;
				for(i=0;i<temp.length;i++){
					jqTemp=jQuery('#'+temp[i])
					jqTemp.css("display","");
					
					
				}
			}
			if(obj.val()=='否'){
				var jqTemp=null;
				for(i=0;i<temp.length;i++){
					jqTemp=jQuery('#'+temp[i]);
					jqTemp.css("display","none");
					jqTemp.children("td").children("input").each(function(obj){
						this.value="";
					});
				}
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/xsxx_gygl_xsxxcj" method="post" styleId="xsxxcjForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:17%"><font color="red">*</font>学号</th>
							<td style="width:33%"><html:text property="xh" readonly="true" styleId="xh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个学生',680,480,'xsxx_gygl_xsxxcj.do?method=showStudents&goto=${path}');return false;">选择</button>
							</td>
							<th style="width:17%">姓名</th>
							<td style="width:33%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>年级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
								</logic:present>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>专业</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
								</logic:present>
							</td>
							<th>班级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>家庭联系方式</th>
							<td colspan="3"><html:text property="jtlxfs" styleId="jtlxfs" maxlength="30" style="width:97%"></html:text></td>
							
						</tr>
						<tr>
							<th style="width:15%"><font color="red">*</font>户口所在地</th>
							<td colspan="3">
								<html:select property="hkszdshen" styleId="hkszdshen" style="width:20%"
									onchange="loadShi('hkszdshen','hkszdshi','hkszdxian');">
									<html:option value="">----请选择----</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="hkszdshi" styleId="hkszdshi" style="width:20%"
									onchange="loadXian('hkszdshi','hkszdxian')">
									<html:options collection="hkszdshiList" property="shidm" labelProperty="shimc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="hkszdxian" styleId="hkszdxian" style="width:20%">
									<html:options collection="hkszdxianList" property="xiandm" labelProperty="xianmc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:text property="hkszdz" styleId="hkszdz" style="width:30%" maxlength="50"></html:text>
							</td>							
						</tr>
						<tr>
							<th style="width:15%"><font color="red">*</font>家庭住址</th>
							<td colspan="3">
								<html:select property="jtdzshen" styleId="jtdzshen" style="width:20%"
									onchange="loadShi('jtdzshen','jtdzshi','jtdzxian');">
									<html:option value="">----请选择----</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="jtdzshi" styleId="jtdzshi" style="width:20%"
									onchange="loadXian('jtdzshi','jtdzxian')">
									<html:options collection="jtdzshiList" property="shidm" labelProperty="shimc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="jtdzxian" styleId="jtdzxian" style="width:20%">
									<html:options collection="jtdzxianList" property="xiandm" labelProperty="xianmc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:text property="jtdzz" styleId="jtdzz" style="width:30%" maxlength="50"></html:text>
							</td>							
						</tr>
						<tr>
							<th style="width:15%"><font color="red">*</font>生源地</th>
							<td colspan="3">
								<html:select property="sydshen" styleId="sydshen" style="width:20%" 
									onchange="loadShi('sydshen','sydshi','sydxian');">
									<html:option value="">----请选择----</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="sydshi" styleId="sydshi" style="width:20%"
									onchange="loadXian('sydshi','sydxian')">
									<html:options collection="sydshiList" property="shidm" labelProperty="shimc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="sydxian" styleId="sydxian" style="width:20%">
									<html:options collection="sydxianList" property="xiandm" labelProperty="xianmc"/>
								</html:select>
								&nbsp;&nbsp;
							</td>							
						</tr>
						<tr>
							<th style="width:15%"><font color="red">*</font>籍贯</th>
							<td colspan="3">
								<html:select property="jgshen" styleId="jgshen" style="width:20%"
									onchange="loadShi('jgshen','jgshi','jgxian');">
									<html:option value="">----请选择----</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="jgshi" styleId="jgshi" style="width:20%"
									onchange="loadXian('jgshi','jgxian')">
									<html:options collection="jgshiList" property="shidm" labelProperty="shimc"/>
								</html:select>
								&nbsp;&nbsp;
								<html:select property="jgxian" styleId="jgxian" style="width:20%">
									<html:options collection="jgxianList" property="xiandm" labelProperty="xianmc"/>
								</html:select>
								&nbsp;&nbsp;
							</td>							
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生采集信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >户口是否迁入学校</th>
							<td >
								<html:select property="hksfjrxx" style="width:80%" styleId="hksfjrxx" >
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<th >是否住校</th>
							<td >
								<html:select property="sfzx" style="width:80%" styleId="sfzx" >
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th >是否申请入党</th>
							<td >
								<html:select property="sfsqrd" style="width:80%" styleId="sfsqrd" onchange="change('sfsqrd','sfsqrdcs!!sfsqrdcs2')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<th>是否顶岗实习</th>
							<td >
								<html:select property="sfdgsx" style="width:80%" styleId="sfdgsx" >
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="sfsqrdcs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>递交申请书时间</th>
							<td>
								<html:text onclick="return showCalendar('djsqssj','y-mm-dd');" readonly="true" property="djsqssj" styleId="djsqssj" style="width:80%"/>
							</td>
							<th><font color="red">*</font>入党时间</th>
							<td>
								<html:text onclick="return showCalendar('rdsj','y-mm-dd');" readonly="true" property="rdsj" styleId="rdsj" style="width:50%"/>
							</td>
						</tr>
						<tr id="sfsqrdcs2" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>转正时间</th>
							<td colspan="3" >
								<html:text onclick="return showCalendar('zzsj','y-mm-dd');" readonly="true" property="zzsj" styleId="zzsj" style="width:32%"/>
							</td>
						</tr>
						<tr>
							<th >是否少数民族</th>
							<td >
								<html:select property="sfssmz" style="width:80%" styleId="sfssmz" onchange="change('sfssmz','sfssmzcs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfssmzcs" style="display: none;" >
							<th><font color="red">*</font>少数民族名称</th>
							<td colspan="3" bgcolor="#f1f1f1">
								<html:select style="width:33%" property="ssmz" styleId="ssmz">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th >是否临时培训</th>
							<td >
								<html:select property="sflspx" style="width:80%" styleId="sflspx" onchange="change('sflspx','sflspxcs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sflspxcs" style="display: none;">
							<th><font color="red">*</font>培训名称</th>
							<td colspan="3" bgcolor="#f1f1f1">
								<html:text style="width:32%" styleId="pxmc" property="pxmc" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th >是否宗教信仰</th>
							<td >
								<html:select property="sfzjxy" style="width:80%" styleId="sfzjxy" onchange="change('sfzjxy','sfzjxycs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfzjxycs" style="display: none;">
							<th><font color="red">*</font>信仰宗教名称</th>
							<td  bgcolor="#f1f1f1"><html:text property="xyzjmc" styleId="xyzjmc" style="width:80%" maxlength="15"/>&nbsp;&nbsp;</td>
							<th><font color="red">*</font>参加宗教时间</th>
							<td  bgcolor="#f1f1f1">
								<html:text onclick="return showCalendar('cjzjsj','y-mm-dd');" readonly="true" property="cjzjsj" styleId="cjzjsj" style="width:50%"/>
							</td>
						</tr>
						<tr>
							<th >是否经济困难</th>
							<td >
								<html:select property="sfjjkn" style="width:80%" styleId="sfjjkn" onchange="change('sfjjkn','sfjjkncs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfjjkncs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>经济困难原因</th>
							<td colspan="3">
								<html:text property="jjknyy" styleId="jjknyy" style="width:80%" maxlength="200"></html:text>
							</td>
						</tr>
						<tr>
							<th >身体是否疾病</th>
							<td >
								<html:select property="stsfcj" style="width:80%" styleId="stsfcj" onchange="change('stsfcj','stsfcjcs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="stsfcjcs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>身体疾病原因</th>
							<td colspan="3">
								<html:text property="stcjyy" styleId="stcjyy" style="width:80%" maxlength="200"></html:text>
							</td>
						</tr>
						
						<tr>
							<th >是否学习困难</th>
							<td >
								<html:select property="sfxxkn" style="width:80%" styleId="sfxxkn" onchange="change('sfxxkn','sfxxkncs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfxxkncs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>学习困难原因</th>
							<td colspan="3">
								<html:text property="xxknyy" styleId="xxknyy" style="width:80%" maxlength="200"></html:text>
							</td>
						</tr>
						
						<tr>
							<th >是否心理困难</th>
							<td >
								<html:select property="sfxlkr" style="width:80%" styleId="sfxlkr" onchange="change('sfxlkr','sfxlkrcs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfxlkrcs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>心理困扰原因</th>
							<td colspan="3">
								<html:text property="xlkryy" styleId="xlkryy" style="width:80%" maxlength="200"></html:text>
							</td>
						</tr>
						
						<tr>
							<th >是否家庭困扰</th>
							<td >
								<html:select property="sfjtkr" style="width:80%" styleId="sfjtkr" onchange="change('sfjtkr','sfjtkrcs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfjtkrcs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>家庭困扰原因</th>
							<td colspan="3">
								<html:text property="jtkryy" styleId="jtkryy" style="width:80%" maxlength="200"></html:text>
							</td>
						</tr>
						
						<tr>
							<th >是否有其他困扰</th>
							<td >
								<html:select property="sfyqtkr" style="width:80%" styleId="sfyqtkr" onchange="change('sfyqtkr','sfyqtkrcs')">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfyqtkrcs" style="display: none;" bgcolor="#f1f1f1">
							<th><font color="red">*</font>其他困扰原因</th>
							<td colspan="3">
								<html:text property="qtkryy" styleId="qtkryy" style="width:80%" maxlength="200"></html:text>
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="save();">
										保存
									</button>
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>	
		</html:form>
	</body>
</html>