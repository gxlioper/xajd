<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("sqdw").value;
		if(pk!=null && pk!=""){
		cqkjFunc.getPrincipalInfo(pk,function(data){
			document.getElementById("fzr").value = data.fzr;
			document.getElementById("gwlxdh").value = data.lxdh;
		});
		}		
	}
	
	function changeGwmc(){
	var sqdw = document.getElementById("sqdw").value;
	cqkjFunc.getGwmcList(sqdw,function (data){
			if (data != null && typeof data == 'object') {
				DWRUtil.removeAllOptions("xmdm");			
				DWRUtil.addOptions("xmdm",data,"gwdm","gwmc");
			}
		});
	}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="isModi" id="isModi" value="modi"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
		<input type="hidden" id="url" name="url" value="/qgzxHgsq.do" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>勤工助学 - 审核 - 换岗申请审核 - 修改换岗信息表</a>
			</p>
		</div>
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				有错误发生！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<logic:equal name="sqsjFlag" value="1">
				<script>
   				alert("不在设定时间范围内,暂不开放申请!");
   				location.href="about:blank";
   				</script>
			</logic:equal>
	
			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>修改换岗申请表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>学号</th>
					<td>
						<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />"/>
						<bean:write name='rs' property="xh" />
					</td>
					<th>姓名</th>
					<td>
						<bean:write name='rs' property="xm" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>现岗</th>
					<td>
					<input type="hidden" name="xmdmmodi" id="xmdmmodi" value="<bean:write name='rs' property='gwdmgwsbsj'/>"/>
						<html:select name="rs" property="gwdmgwsbsj" styleId="xmdm"
							style="width:150px" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th><span class="red">*</span>拟申请岗位</th>
					<td>
						<html:select name="rs" property="hgdmhgsj" styleId="xmdm"
							style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>政治面貌</th>
					<td>
						<bean:write name='rs' property="zzmmmc" />
					</td>
					<th>住址</th>
					<td>
						<bean:write name='rs' property="jtdz" />
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<th>学年</th>
					<td>
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
					<th>学期</th>
					<td>
						${rs.xqmc}
						<html:hidden name="rs" property="xq"></html:hidden>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
					<th>年度</th>
					<td>
						<html:text name="rs" property="nd" readonly="true"></html:text>
					</td>						
				</tr>
				<tr>
					<th>专业</th>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
					<th>负责人</th>
					<td>
						<input type="text" id="fzr" name="fzr" readonly="readonly"
							value="<bean:write name="rs" property="fzr"/>" />
					</td>
				</tr>
				<tr>
					<th>班级</th>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<th>联系电话</th>
					<td>
						<input type="text" id="gwlxdh" name="gwlxdh" readonly="readonly"
							value="<bean:write name="rs" property="gwlxdh"/>"
							disabled="disabled" />
					</td>
				</tr>
				<tr>
					<th>是否贫困生</th>
					<td>
						<bean:write name='rs' property="sfpks" />
					</td>
					<th>本人联系电话</th>
					<td>
						<html:text property="lxdh" name="rs" styleId="lxdh" />
					</td>
				</tr>
				<tr>
					<th>申请理由</th>
					<td colspan="3">
						<html:textarea name='rs' property='sqly' styleId="sqly"
							style="width:99%" rows='5' />
					</td>
				</tr>
				<tr>
					<th>特长</th>
					<td colspan="3">
						<html:textarea name='rs' property='yhtc' styleId="yhtc"
							style="width:99%" rows='5' />
					</td>
				</tr>
				<tr>
					<th>空闲时间</th>
					<logic:present name="kxList">
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" class="formlist">
								<tr>
									<th>
										时间
									</th>
									<th>
										星期一
									</th>
									<th>
										星期二
									</th>
									<th>
										星期三
									</th>
									<th>
										星期四
									</th>
									<th>
										星期五
									</th>
									<th>
										星期六
									</th>
									<th>
										星期日
									</th>
								</tr>
								<logic:iterate id="kxsj" name="kxList">
									<tr>
										<td>
											${kxsj.sj}
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}1" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}1"
												value="${kxsj.mon}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}2" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}2"
												value="${kxsj.tue}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}3" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}3"
												value="${kxsj.wed}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}4" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}4"
												value="${kxsj.thu}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}5" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}5"
												value="${kxsj.fri}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}6" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}6"
												value="${kxsj.sat}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}7" value="□" />
											<input type="hidden" name="index${kxsj.sjIndex}7"
												value="${kxsj.sun}" />
										</td>
									</tr>
								</logic:iterate>
							</table>
								<script language="javascript">
									for(var i=0;i<7;i++){
										for(var j=1;j<8;j++){
										 	if(document.getElementById("index"+i+j)){
										 		if(document.getElementById("index"+i+j).value==1){
													document.getElementById(i+""+j).checked="checked";
											    }
										 	}
										}
									}
								</script>
						</td>
					</logic:present>
					<logic:notPresent name="kxList">
						<td height="22" colspan="3">
							<table id="tbSj" class="formlist">
								<tr>
									<th>
										时间
									</th>
									<th>
										星期一
									</th>
									<th>
										星期二
									</th>
									<th>
										星期三
									</th>
									<th>
										星期四
									</th>
									<th>
										星期五
									</th>
									<th>
										星期六
									</th>
									<th>
										星期日
									</th>
								</tr>
								<logic:iterate id="kxsj" name="whkxList">
									<tr>
										<td>
											${kxsj.sj}
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</td>
					</logic:notPresent>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<html:textarea name='rs' property='bz' styleId="bz"
							style="width:99%" rows='5' />
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="zgdzdx_chkisDataExist('xh-xmdm');">
							保 存
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</logic:notEmpty>

		<logic:notEmpty name="inserted">
			<logic:equal name="inserted" value="ok">
				<script>
			    alert("操作成功！");
			    Close();
			    </script>
			</logic:equal>
			<logic:equal name="inserted" value="no">
				<script>
				var html_reason= '<bean:write name="reason"/>';
			    alert("操作失败！"+html_reason);
			    Close();
			    </script>
			</logic:equal>
		</logic:notEmpty>
		</html:form>
	</body>
</html>
