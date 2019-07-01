<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
		<script language="javascript">
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
		//验证数据格式是否是数字
		function ckinpdata(obj){
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;
			
			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
				alert('数据格式不正确，扣分字段只能是两位数字！');
				obj.value = '';
				return false;
			}
			return true;
		}
		function savewjxx(url, pkFields, tabFlag, act) {
		
			
			var eles = pkFields.split("-");
			var valu = "";
			for (i = 0; i < eles.length; i++) {
				if($(eles[i])){
					if ($(eles[i]).value == "") {
						alert("请将带\"*\"号的项目输入完整！");
						return false;
					}
				}
			}
			
			for (i = 0; i < eles.length; i++) {
				if (eles[i] != 'bz') {
					valu += document.getElementById(eles[i]).value;					
				}
			}
			
			if($("cfwj")){
			var cfwj = document.getElementById('cfwj').value;
		   	if (cfwj != null && cfwj != '') {
		   		if (cfwj.length <= 4) {
		   			alert("上传文件路径不正确,请重新选择!");
		   			return false;
		   		}
		   		var kzm = cfwj.substr(cfwj.length-3,3);
		   		if (kzm == 'txt' || kzm == 'xls' ||kzm == 'doc' ||kzm == 'pdf' ||kzm == 'chm' || kzm=='rar') {
		   			
		   		} else{
		   			alert("上传文件格式不正确,只能上传txt,doc,xls,pdf,chm,rar类型的文件!");
		   			return false;
		   		}
		   	}
		   	}
			if(confirm('你确定要提交申请吗？')){
				url = url + "?tab=" + tabFlag + "&pkValue=" + valu + "&act=" + act;
				document.forms[0].action = url;
				document.forms[0].submit();
			
				BatAlert.showTips('正在操作，请等待...');
				return true;
			}
		}
		function cbbprint() {
		var cfpk = document.getElementById('cfpk').value;
    	window.open('wjcf_nbcs_cbbprint.do?cfpk=' + cfpk);
    }
    function zjcmcfbprint() {
		var cfpk = document.getElementById('cfpk').value;
    	window.open('wjcf_zjcm_cfbprint.do?cfpk=' + cfpk);
    }
</script>
		<script type="text/javascript">
	function print(typ) {
		var url = 'wjcf_nblg_cfprint.do';
			var pk = document.getElementById('cfpk').value;
			var xh = document.getElementById('xh').value;
			if (pk==null || pk=='') {
				url += '?xh=';
				url += xh;	
			} else {
				url += '?pk=';
				url += pk;
			}
			url += '&typ='+typ;
			window.open(url);
			return;
	}
	function myPrintReport(url){			
		url += document.getElementById("xh").value;
		var xxdm = document.getElementById('xxdm').value;	
		var cflb = document.getElementById('cflb').value;
		var cfyy = document.getElementById('cfyy').value;
		if ('11641'==xxdm) {
			var xh = document.getElementById('xh').value;
			
			url = 'wjcf_hhgxy_cfbprint.do?xh=';
			url += xh;
			url += '&cflb=';
			url += cflb;
			url += '&cfyy=';
			url += cfyy;
			window.open(url);
			return;
		} 
		if ($('bz')) {
			url += "&bz=";
			url += document.getElementById('bz').value;
			url += "&cflb=";
			url += cflb;
			url += "&cfyy=";
			url += cfyy;
		}
	   	window.open(url);
    }
    
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>


		<html:form action="/stuPunishApp" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="cfpk" id="cfpk" value="${cfpk }" />
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
    alert("您输入的学号无效!");
    </script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/stuPunishApp.do" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<!-- 福建工程学院 学院处理意见 必填写 -->
									<logic:equal name="xxdm" value="10388">
										<button type="button"
										onclick="savewjxx('/xgxt/applySave.do','xh-xm-cflb-cfyy-bz-xyclyj','wjcf')">
										提交申请
									</button>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10388">
									<button type="button"
										onclick="savewjxx('/xgxt/applySave.do','xh-xm-cflb-cfyy-bz','wjcf')">
										提交申请
									</button>
									</logic:notEqual>
									<logic:equal value="13022" name="xxdm">
										<button type="button" onclick="print('1')">
											打印(格式1)
										</button>
										<button type="button" onclick="print('2')">
											打印(格式2)
										</button>
									</logic:equal>
									<logic:equal value="12645" name="xxdm">
										<button type="button" onclick="cbbprint()">
											呈报表打印
										</button>
									</logic:equal>
									<logic:equal value="11647" name="xxdm">
										<button type="button" onclick="zjcmcfbprint()">
											呈报表打印
										</button>
									</logic:equal>
									
									<!-- 成都体育报表打印 -->
									<logic:equal value="true" name="print">
										<button type="button" onclick="window.open('wjcf_cdty_cfbprint.do?cfpk=' + document.getElementById('cfpk').value);">
											呈报表打印
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this);checkXhExists('xm-xb-zzmmmc-nj-xymc-zymc-bjmc')" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<html:hidden name='rs' property="xh" styleId="xh" />
									<bean:write name='rs' property="xh" />
								</logic:equal>

							</td>
							<th width="16%">
								年度
							</th>
							<td width="34%">
								<bean:write name='rs' property="nd" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>姓名
							</th>
							<td>
								<html:text property="xm" name="rs" disabled="true"></html:text>
							</td>
							<th>
								学年
							</th>
							<td>
								<bean:write name='rs' property="xn" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<html:text property="xb" name="rs" disabled="true"></html:text>
							</td>
							<th>
								
								<logic:equal value="10290" name="xxdm"><font color="red">*</font>处分种类</logic:equal>
								<logic:notEqual value="10290" name="xxdm"><font color="red">*</font>处分类别</logic:notEqual>
							</th>
							<td>
								<html:select name="rs" property="cflb" style="width:150px"
									styleId="cflb">
									<html:option value=""></html:option>
									<html:options collection="cflbList" property="cflbdm"
										labelProperty="cflbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:text property="nj" name="rs" disabled="true"></html:text>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:text property="zymc" name="rs" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:text property="xymc" name="rs" disabled="true"></html:text>
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<html:text property="zzmmmc" name="rs" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								<html:text property="bjmc" name="rs" disabled="true"></html:text>
							</td>
							<th>
								<font color="red">*</font>处分事由
							</th>
							<td>
								<html:select name="rs" property="cfyy" styleId="cfyy" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="cfyyList" property="cfyydm"
										labelProperty="cfyymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								违纪时间
							</th>
							<td>
								<html:text name='rs' property="wjsj" styleId="wjsj"
									onclick="return showCalendar('wjsj','y-mm-dd');"
									onblur="getRqVal('wjsj')" />
							</td>
							<logic:equal value="1049701" name="xxdm">
								<th>
									扣分
								</th>
								<td>
									<html:text property="kf" name="rs" onkeyup="ckinpdata(this)"
										maxlength="10" style="90px" />
								</td>
							</logic:equal>
							<logic:notEqual value="1049701" name="xxdm">
								<logic:equal value="10388" name="xxdm">
									<th>
										申报人
									</th>
									<td>
										<input type="text" value="${rs.sbr}" readonly="true"/>
									</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10388">
								<logic:equal name="jsqr" value="true">
									<th>
										是否告知学生
									</th>
									<td>
										<html:select name ="rs" property="xsqr" styleId="xsqr">
											<html:option value="否">否</html:option>
											<html:option value="是">是</html:option>
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual name="jsqr" value="true">
								<th>

								</th>
								<td>

								</td>
								</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</tr>
					<thead>
						<tr>
							<td colspan="4">
								<span>历史违纪处分信息 &nbsp;&nbsp;<a
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfxx();">查看</a>
								</span>
							</td>
						</tr>

					</thead>

					<tr>
						<td colspan="4">
							<div id="child4" style="display:none">
								<table width="95%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center" width="80px">
												学年
											</td>
											<td align="center" width="110px">
												处分类别
											</td>
											<td align="center" width="110px">
												处分原因
											</td>
											<td align="center" width="80px">
												处分时间
											</td>
											<td align="center" width="110px">
												处分文号
											</td>
											<td align="center" width="80px">
												违纪时间
											</td>
											<td align="center">
												违纪事实
											</td>
										</tr>
									</thead>
									<!-- 这里是通过DWR进行调用的 -->
									<tbody width="95%" class="tbstyle" id="cfxx" align="center"></tbody>
									
								</table>
							</div>
						</td>
					</tr>
					<logic:equal value="10290" name="xxdm">
						<tr style="height:22px">
							<th>
								处分依据
								<br />
								<font color="red">(字数在300字左右)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea name="rs" property="cfyj" styleId="cfyj" rows="9"
									style="width:95%" onkeyup="checkLen(this,300)">
								</html:textarea>
							</td>
						</tr>
					</logic:equal>
					<tr style="height:22px">
						<th>
							<logic:notEqual value="yes" name="isXBEMY">
								<font color="red">*</font>违纪事实<br />
								<font color="red">(字数在1000字以内)</font>
							</logic:notEqual>
							<logic:equal value="yes" name="isXBEMY">
								<font color="red">*</font>违纪内容
									</logic:equal>
						</th>
						<td colspan="3" align="left" style="word-break:break-all;">
							<font color="red">(请如实说明违纪时间,地点,事件经过和造成后果)</font>
							<html:textarea rows="10" name='rs' style="width:95%"
								property="bz" styleId="bz" onkeyup="checkLen(this,1000)" />
						</td>
					</tr>
					<logic:notEqual value="11078" name="xxdm">
						<tr>
							<th>
								处理决定书、书面材料或附件
							</th>
							<td align="left" colspan="3">
								<font color="red">(如材料过多，请打包上传)</font>
								<br />
								<input type="file" name="uploadFile" id="cfwj"
									contentEditable="false" style="width:60%" />
							</td>
						</tr>
					</logic:notEqual>

					<tr>
						<th>
							<logic:equal name="xxdm" value="10388">
							<font color="red">*</font>
							</logic:equal>
							<bean:message key="lable.xsgzyxpzxy" />
							、系处理依据&nbsp;&nbsp;
							<br />
							及处理意见&nbsp;&nbsp;&nbsp;
							<br />
							<font color="red">(字数在500字以内)</font>
						</th>
						<td align="left" colspan="3" style="word-break:break-all;">
							<html:textarea name="rs" property="xyclyj" styleId="a" rows="6"
								style="width:95%" onkeyup="checkLen(this,500)">
							</html:textarea>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
				    alert("申请成功！");
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
				    alert("申请失败！");
				    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
