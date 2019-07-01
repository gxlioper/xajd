<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">		
		function commitData(){
			saveData('xjxxdj.do?method=xjxxdjAdd&type=save','xh');
		}		
	</script>
</head>
	<body onload="checkWinType();">		
		<html:form action="/xjxxdj.do" method="post"  enctype="multipart/form-data">
			<input type="hidden" id="disableEle" name="disableEle" value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
			<input type="hidden" id="url" name="url" value="/xjxxdj.do?method=xjxxdjAdd" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}-增加</a>
				</p>
			</div>
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
   					alert("您输入的学号无效!");
   				</script>
			</logic:equal>	
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>学籍信息登记</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><font color="red">*</font>学号</th>
					<td>
						<html:text name='rs' 
						           property="save_xh" 
						           readonly="readonly"
							       styleId="xh" 
							       onkeypress="autoFillStuInfo(event.keyCode,this);" maxlength="20" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							id="buttonFindStu">
							选择
						</button>
					</td>
					<th>出生地</th>
					<td>
						<html:select name="rs" property="save_csd" styleId="csd">
						<html:option value=""></html:option>
						<html:options collection="ssList" property="ssdm" labelProperty="ssmc"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
					</td>
					<th>血型</th>
					<td>
						<html:select name="rs" property="save_xx" styleId="xx">
							<html:option value=""></html:option>
							<html:option value="A">A</html:option>
							<html:option value="B">B</html:option>
							<html:option value="O">O</html:option>
							<html:option value="AB">AB</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>姓名简拼</th>
					<td>
						<html:text name='rs' property="save_xmjp" styleId="xmjp" onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " maxlength="50"/>
					</td>
					<th>婚姻状况</th>
					<td>
						<html:select name="rs" property="save_hyzkdm" styleId="hyzkdm">
							<html:options collection="hyzkList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>户口性质</th>
					<td>
						<html:select name="rs" property="save_hkxzdm" styleId="hkxzdm">
							<html:option value=""></html:option>
							<html:option value="城镇">城镇</html:option>
							<html:option value="农村">农村</html:option>	
						</html:select>
					</td>
					<th>国别</th>
					<td>
						<html:select name="rs" property="save_gbdm" styleId="gbdm">
							<html:options collection="gbList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>乘车区间起始</th>
					<td>
						<html:text property="save_ccqjqs" name="rs" styleId="ccqjqs" maxlength="40"></html:text>
					</td>
					<th>乘车区间到达</th>
					<td>
						<html:text property="save_ccqjdd" name="rs" styleId="ccqjdd" maxlength="40"></html:text>
					</td>
				</tr>	
				<tr>					
					<th>校区</th>
					<td>
						<html:select name="rs" property="save_xqdm" styleId="xqdm">
							<html:options collection="xqList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>学生类别</th>
					<td>
						<html:select name="rs" property="save_xslbdm" styleId="xslbdm">
							<html:options collection="xslbList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>			
				<tr>
					<th>入党时间</th>
					<td>
						<html:text name='rs' property="save_rdsj" styleId="rdsj" onclick="return showCalendar('rdsj','y-mm-dd');"/>
					</td>
					<th>入团时间</th>
					<td>
						<html:text name='rs' property="save_rtsj" styleId="rtsj" onclick="return showCalendar('rtsj','y-mm-dd');"/>
					</td>					
				</tr>
				<tr>
					<th>证件类型</th>
					<td>
						<html:select name="rs" property="save_zjlxdm" styleId="zjlxdm">
							<html:option value=""></html:option>
							<html:options collection="zjlxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>证件号码</th>
					<td>
						<html:text name='rs' property="save_zjhm" styleId="zjhm" maxlength="20" onkeyup="value=value.replace(/[^\d|[^a-zA-Z]]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th>港澳台侨</th>
					<td>
						<html:select name="rs" property="save_gatqdm" styleId="gatqdm">
							<html:options collection="gatqList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>家庭出身</th>
					<td>
						<html:text name='rs' property="save_jtcs" styleId="jtcs" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>健康状况</th>
					<td>
						<html:text name='rs' property="save_jkzk" styleId="jkzk" maxlength="50"/>
					</td>
					<th>宗教信仰</th>
					<td>
						<html:text name='rs' property="save_zjxy" styleId="zjxy" maxlength="50"/>
					</td>
				</tr>
				<tr>					
					<th>辅导员姓名</th>
					<td>
						<html:text name='rs' property="save_fdyxm" styleId="fdyxm" maxlength="25"/>
					</td>
					<th>辅导员联系方式</th>
					<td>
						<html:text name='rs' property="save_fdylxfs" styleId="fdylxfs" maxlength="50" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
					</td>
				</tr>
				<tr>					
					<th>专业号</th>
					<td>
						<html:text name='rs' property="save_zyh" styleId="zyh" maxlength="10"/>
					</td>					
					<th>专业科类</th>
					<td>
						<html:select name="rs" property="save_zykldm" styleId="zykldm">
							<html:options collection="zyklList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>					
					<th>档案是否在校</th>
					<td>
						<html:select name="rs" property="save_dazxzt" styleId="dazxzt">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>					
					<th>是否在职</th>
					<td>
						<html:select name="rs" property="save_zzzt" styleId="zzzt">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>是否全日制</th>
					<td>
						<html:select name="rs" property="save_qrzzt" styleId="qrzzt">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>					
					<th>是否学历生</th>
					<td>
						<html:select name="rs" property="save_xlszt" styleId="xlszt">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>					
					<th>入学季节</th>
					<td>
						<html:text name='rs' property="save_rxjj" styleId="rxjj" maxlength="5"/>
					</td>					
					<th>培养类型</th>
					<td>
						<html:select name="rs" property="save_pylxdm" styleId="pylxdm">
							<html:options collection="pylxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>					
					<th>学习形式</th>
					<td>
						<html:select name="rs" property="save_xxxsdm" styleId="xxxsdm">
							<html:options collection="xxxsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>定向或委托单位</th>
					<td>
						<html:text name='rs' property="save_dxhwpdw" styleId="dxhwpdw" maxlength="100"/>						
					</td>
				</tr>
				<tr>					
					<th>定向或委托单位地址</th>
					<td>
						<html:text name='rs' property="save_dxhwpdw" styleId="dxhwpdwdz" maxlength="100"/>
					</td>					
					<th>定向或委托单位邮编</th>
					<td>
						<html:text name='rs' property="save_dxhwpdwyb" styleId="dxhwpdwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>						
					</td>
				</tr>
				<tr>					
					<th>定向或委托单位电话</th>
					<td>
						<html:text name='rs' property="save_dxhwpdwdh" styleId="dxhwpdwdh" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
					</td>					
					<th>是否联合培养</th>
					<td>
						<html:select name="rs" property="save_sflhpy" styleId="sflhpy">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>联合培养单位</th>
					<td>
						<html:text name='rs' property="save_lhpydw" styleId="lhpydw" maxlength="100"/>
					</td>					
					<th>联合培养单位地址</th>
					<td>
						<html:text name='rs' property="save_lhpydwdz" styleId="lhpydwdz" maxlength="100"/>						
					</td>
				</tr>
				<tr>					
					<th>联合培养单位邮编</th>
					<td>
						<html:text name='rs' property="save_lhpydwyb" styleId="lhpydwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>联合培养单位联系电话</th>
					<td>
						<html:text name='rs' property="save_lhpydwdh" styleId="lhpydwdh" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>						
					</td>
				</tr>
				<tr>					
					<th>是否专业学位</th>
					<td>
						<html:select name="rs" property="save_sfzyxw" styleId="sfzyxw">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>					
					<th>连读方式</th>
					<td>
						<html:select name="rs" property="save_ldfs" styleId="ldfs">
							<html:option value=""></html:option>
							<html:option value="无">无</html:option>
							<html:option value="本硕连读">本硕连读</html:option>
							<html:option value="本硕博连读">本硕博连读</html:option>
							<html:option value="硕博连读">硕博连读</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>获得学历方式</th>
					<td>
						<html:select name="rs" property="save_hdxlfsdm" styleId="hdxlfsdm">
							<html:options collection="hdxlfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>是否学分制学生</th>
					<td>
						<html:select name="rs" property="save_xfzxszk" styleId="xfzxszk">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>专业学位类别</th>
					<td>
						<html:text name='rs' property="save_zyxwlb" styleId="lhpydw" maxlength="25"/>
					</td>					
					<th>应毕业时间</th>
					<td>
						<html:text name='rs' property="save_ybysj" styleId="ybysj" onclick="return showCalendar('ybysj','y-mm-dd');"/>				
					</td>
				</tr>
				<tr>					
					<th>学习方式</th>
					<td>
						<html:select name="rs" property="save_xxfsdm" styleId="xxfsdm">
							<html:options collection="xxfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>报考照片</th>
					<td>
						<html:file property="bkzp" style="width:90%"></html:file>				
					</td>
				</tr>
				<tr>					
					<th>入学照片</th>
					<td>
						<html:file property="rxzp" style="width:90%"></html:file>	
					</td>					
					<th>毕业照片</th>
					<td>
						<html:file property="byzp" style="width:90%"></html:file>						
					</td>
				</tr>
				<tr>					
					<th>当前学籍毕业证书编号</th>
					<td>
						<html:text name='rs' property="save_dqxjbyzsbh" styleId="dqxjbyzsbh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>当前学籍学位证书编号</th>
					<td>
						<html:text name='rs' property="save_dqxjxwzsbh" styleId="dqxjxwzsbh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>新生标记</th>
					<td>
						<html:select name="rs" property="save_xsbj" styleId="xsbj">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>		
					</td>					
					<th>学生来源</th>
					<td>
						<html:select name="rs" property="save_xslydm" styleId="xslydm">
							<html:options collection="xslyList" property="dm" labelProperty="mc"/>
						</html:select>					
					</td>
				</tr>
				<tr>					
					<th>来源国别</th>
					<td>
						<html:select name="rs" property="save_lygbdm" styleId="lygbdm">
							<html:options collection="gbList" property="dm" labelProperty="mc"/>
						</html:select>		
					</td>					
					<th>来源洲别</th>
					<td>
						<html:select name="rs" property="save_lyzbdm" styleId="lyzbdm">
							<html:options collection="zbList" property="dm" labelProperty="mc"/>
						</html:select>					
					</td>
				</tr>
				<tr>					
					<th>就读方式</th>
					<td>
						<html:select name="rs" property="save_jdfsdm" styleId="jdfsdm">
							<html:options collection="jdfsList" property="dm" labelProperty="mc"/>
						</html:select>		
					</td>					
					<th>毕结业结论</th>
					<td>
						<html:text name='rs' property="save_bjyjl" styleId="bjyjl" maxlength="200"/>				
					</td>
				</tr>
				<tr>					
					<th>是否本校授予学位标记</th>
					<td>
						<html:select name="rs" property="save_sfzxsyxwbj" styleId="sfzxsyxwbj">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>		
					</td>					
					<th>授予学位时间</th>
					<td>
						<html:text name='rs' property="save_syxwsj" styleId="syxwsj" onclick="return showCalendar('syxwsj','y-mm-dd');"/>				
					</td>
				</tr>
				<tr>					
					<th>当前学籍毕业证书印制号</th>
					<td>
						<html:text name='rs' property="save_dqxjbyzsyzh" styleId="dqxjbyzsyzh" maxlength="10"/>
					</td>					
					<th>当前学籍学位证书印制号</th>
					<td>
						<html:text name='rs' property="save_dqxjxwzsyzh" styleId="dqxjxwzsyzh" maxlength="10"/>				
					</td>
				</tr>
				<tr>					
					<th>专业方向</th>
					<td>
						<html:text name='rs' property="save_zyfx" styleId="zyfx" maxlength="30"/>
					</td>					
					<th>现住址</th>
					<td>
						<html:text name='rs' property="save_xzz" styleId="xzz" maxlength="100"/>				
					</td>
				</tr>
				<tr>					
					<th>MSN</th>
					<td>
						<html:text name='rs' property="save_msn" styleId="msn" maxlength="30"/>
					</td>					
					<th>通讯地址</th>
					<td>
						<html:text name='rs' property="save_txdz" styleId="txdz" maxlength="100"/>				
					</td>
				</tr>
				<tr>					
					<th>邮政编码</th>
					<td>
						<html:text name='rs' property="save_yzbm" styleId="yzbm" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>主页地址</th>
					<td>
						<html:text name='rs' property="save_zydz" styleId="zydz" maxlength="100"/>				
					</td>
				</tr>
				<tr>					
					<th>第一外语语种</th>
					<td>
						<html:select property="save_dywyyzdm" styleId="dywyyzdm" name="rs">
							<html:options collection="wyyzList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>第一外语等级</th>
					<td>
						<html:text name='rs' property="save_dywydj" styleId="dywydj" maxlength="10"/>				
					</td>
				</tr>
				<tr>					
					<th>第二外语语种</th>
					<td>
						<html:select property="save_dewyyzdm" styleId="dewyyzdm" name="rs">
							<html:options collection="wyyzList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>第二外语等级</th>
					<td>
						<html:text name='rs' property="save_dewydj" styleId="dewydj" maxlength="10"/>				
					</td>
				</tr>
				<tr>					
					<th>录取通知书号</th>
					<td>
						<html:text name='rs' property="save_lqtzsh" styleId="lqtzsh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>准考(报考)号</th>
					<td>
						<html:text name='rs' property="save_dewydj" styleId="dewydj" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>毕业中学</th>
					<td>
						<html:text name='rs' property="save_byzx" styleId="byzx" maxlength="50"/>
					</td>					
					<th>毕业中学邮编</th>
					<td>
						<html:text name='rs' property="save_byzxyb" styleId="byzxyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>工龄/军龄</th>
					<td>
						<html:text name='rs' property="save_gn" styleId="gn" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>第一外语成绩</th>
					<td>
						<html:text name='rs' property="save_dywycj" styleId="dywycj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>是否转户口</th>
					<td>
						<html:select property="save_sfzhk" styleId="sfzhk" name="rs">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>					
					<th>第二外语成绩</th>
					<td>
						<html:text name='rs' property="save_dewycj" styleId="dewycj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>报考科目一</th>
					<td>
						<html:text name='rs' property="save_bkkm1" styleId="bkkm1" maxlength="50"/>
					</td>					
					<th>报考科目一成绩</th>
					<td>
						<html:text name='rs' property="save_bkkm1cj" styleId="bkkm1cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>报考科目二</th>
					<td>
						<html:text name='rs' property="save_bkkm2" styleId="bkkm2" maxlength="50"/>
					</td>					
					<th>报考科目二成绩</th>
					<td>
						<html:text name='rs' property="save_bkkm2cj" styleId="bkkm2cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>报考科目三</th>
					<td>
						<html:text name='rs' property="save_bkkm3" styleId="bkkm3" maxlength="50"/>
					</td>					
					<th>报考科目三成绩</th>
					<td>
						<html:text name='rs' property="save_bkkm3cj" styleId="bkkm3cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>报考科目四</th>
					<td>
						<html:text name='rs' property="save_bkkm4" styleId="bkkm4" maxlength="50"/>
					</td>					
					<th>报考科目四成绩</th>
					<td>
						<html:text name='rs' property="save_bkkm4cj" styleId="bkkm4cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>报考科目五</th>
					<td>
						<html:text name='rs' property="save_bkkm5" styleId="bkkm5" maxlength="50"/>
					</td>					
					<th>报考科目五成绩</th>
					<td>
						<html:text name='rs' property="save_bkkm5cj" styleId="bkkm5cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>考区</th>
					<td>
						<html:text name='rs' property="save_kq" styleId="kq" maxlength="50"/>
					</td>					
					<th>分数线类别</th>
					<td>
						<html:select property="save_fsxlb" styleId="fsxlb" name="rs">
							<html:option value=""></html:option>
							<html:option value="A类">A类</html:option>
							<html:option value="B类">B类</html:option>
							<html:option value="C类">C类</html:option>
						</html:select>				
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            	<button type="button" class="button2" onclick="commitData()"
								style="width:80px" id="buttonSave">
								保 存
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
								id="buttonClose">
								关 闭
							</button>			
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>				
		 </div>
		</html:form>

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
