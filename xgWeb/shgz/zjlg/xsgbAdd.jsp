<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/xsgbxx.do?method=xsgbxxwhAdd&act=add";
		 var yzdz = "grjlV-jcqkV-zwpdV";
		 var yzcd = "800-800-800";
		 var mustFill = "xh";
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
	     
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("请将带\"*\"号的项目输入完整！");
	    			return false;
	    		}
	    	}
	    	 var cheangelx = document.getElementById("zwpdlx").value;
			 if(cheangelx == ""){
					alert("请选在自我评定!!");
					return false;
				}
				if(cheangelx == "学年"){
					var xn = document.getElementById("xn").value;
					if(xn == ""){
						alert("学年不能为空！！");
						return false;
					}
				}else if(cheangelx == "学期"){
					var xn = document.getElementById("xn").value;
					var xq = document.getElementById("xq").value;
					if(xn == ""){
						alert("学年不能为空！！");
						return false;
					}
					if(xq == ""){
						alert("学期不能为空！！");
						return false;
					}
				}else if(cheangelx == "月"){
					var xn = document.getElementById("xn").value;
					var xq = document.getElementById("xq").value;
					var yf = document.getElementById("yf").value;
					if(xn == ""){
						alert("学年不能为空！！");
						return false;
					}
					if(xq == ""){
						alert("学期不能为空！！");
						return false;
					}
					if(yf == ""){
						alert("月份不能为空！！");
						return false;
					}
				}
	    	var splitDz = "";
	    	var splitCd = "";
	    	if(yzdz != ""){
	    		splitDz = yzdz.split("-");
	    	}
	    	if(yzcd != ""){
	    		splitCd = yzcd.split("-");
	    	}
	    	for (i = 0; i < splitDz.length; i++) {
	    		var dzsjcd = document.getElementById(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
	    		var dzsjmc = document.getElementById(splitDz[i]).cells[0].innerHTML;
	    		if (dzsjcd.length>splitCd[i]) {
	    			alert(dzsjmc.replace("<BR>", "")+"不能超过"+splitCd[i]+"个字！");
	    			return false;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	    	document.getElementById("buttonQx").disabled=true;
	 }
	 
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	</script>
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN onload="cheangelx();">
		<html:form action="/xsgbxx" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>社会工作 - 学生干部 - 学生干部信息录入</a>
				</p>
			</div>
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" id="tableName" name="tableName"
					value="zjlg_xsgbxxb" />
				<input type="hidden" id="url" name="url"
					value="/xsgbxx.do?method=getxsInfo" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>字段维护</span>
								</th>
							</tr>
						</thead>
						<%--				<thead>--%>
						<%--					<tr>--%>
						<%--						<td colspan="8" align="center" >--%>
						<%--							<logic:equal name="userOnLine" value="teacher" scope="session">--%>
						<%--								<font color="red">*</font>学号：--%>
						<%--							<html:text name='rs' property="yf" styleId="xsxh"--%>
						<%--									style="width:150px"--%>
						<%--									readonly="true" />--%>
						<%--								<button onclick="showTopWin('/xgxt/grjlTurnInfo.do',750,550);"--%>
						<%--									class="btn_01" id="buttonFindStu">--%>
						<%--									选择--%>
						<%--								</button>--%>
						<%--							</logic:equal>--%>
						<%--							<logic:equal name="userOnLine" value="student" scope="session">--%>
						<%--								<font color="red">*</font>学号：--%>
						<%--							<html:text property="xsxh" name="rs" styleId="yf"--%>
						<%--									readonly="true" style="width:150px" />--%>
						<%--							</logic:equal>--%>
						<%--						</td>--%>
						<%--					</tr>--%>
						<%--				</thead>--%>
						<tbody>
						<tr>
							<th align="right" width="70">
								<font color="red">*</font>学号
							</th>
							<td>
								<html:hidden property="id" name="rs" />
								<html:text name="rs" property="xh" 
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal value="add" name="doType">
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:equal>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text name="rs" property="xm" 
									readonly="true" />
							</td>
							<td rowspan="4" align="center" width="100">
								<img border="0" style="height:133px;width:100px;"
									src="/xgxt<bean:write name="rs" property="zhaop" />"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								性别
							</th>
							<td>
								<html:text name="rs" property="xb"
									maxlength="10" readonly="true" />
							</td>
							<th  nowrap="nowrap">
								担任现职时间
							</th>
							<td>
								<html:text name="rs" property="drxzsj"
									onclick="return showCalendar('drxzsj','y-mm-dd');"
									 maxlength="30" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:text name="rs" property="xydm"
									style="display: none" readonly="true" />
								<input value="<bean:write name="rs" property="xymc"/>"
									readonly="readonly" disabled="disabled"
									style="display: " />
							</td>
							<th>
								班级
							</th>
							<td>
								<html:text name="rs" property="bjdm" style="display: none"
									readonly="true" />
								<input value="<bean:write name="rs" property="bjmc"/>"
									disabled="disabled" style="display: " />
							</td>
						</tr>
						<tr>
							<th>
								所属组织
							</th>
							<td>
								<html:text name="rs" property="sszz"
									maxlength="40" />
							</td>
							<th align="right">
								所任职务
							</th>
							<td>
								<html:text name="rs" property="srzw"
									maxlength="40" />
							</td>
						</tr>
						<tr id="grjlV">
							<th>
								个人简历<br/>
								<font color="red">(限制字数800)</font>
							</th>
							<td colspan="6">
								<html:textarea name="rs" property="grjl" rows="4" onblur="chLeng(this,800);"
									style="word-break:break-all;width:99%" />
							</td>
						</tr>
						<tr id="jcqkV">
							<th align="center">
								奖惩情况<br/>
								<font color="red">(限制字数800)</font>
							</th>
							<td colspan="6">
								<html:textarea name="rs" property="jcqk" rows="4" onblur="chLeng(this,800);"
									style="word-break:break-all;width:99%" />
							</td>
						</tr>
						<%--				<tr>--%>
						<%--					<td align="center">--%>
						<%--						工作总结--%>
						<%--					</td>--%>
						<%--					<td colspan="6">--%>
						<%--						<html:textarea name="rs" property="yf" rows="4"--%>
						<%--							style="width=100%" />--%>
						<%--					</td>--%>
						<%--				</tr>--%>
						<%--				<tr>--%>
						<%--					<td align="center">--%>
						<%--						活动策划--%>
						<%--					</td>--%>
						<%--					<td colspan="6">--%>
						<%--						<html:textarea name="rs" property="yf" rows="4"--%>
						<%--							style="width=100%" />--%>
						<%--					</td>--%>
						<%--				</tr>--%>
						<tr>
							<th rowspan="2">
								自我评定<br/>
								<font color="red">(限制字数800)</font>
							</th>
							<td colspan="6">
								<html:select name="rs" property="zwpdlx" onchange="cheangelx();">
									<html:option value=""></html:option>
									<html:option value="学年">学年</html:option>
									<html:option value="学期">学期</html:option>
									<html:option value="月">月</html:option>
								</html:select>
								<span id="xnV">学年： <html:select name="rs" property="xn"
										style="width:" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select> </span>
								<span id="xqV">学期： <html:select name="rs" property="xq"
										style="width:" styleId="xn">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqmc"
											labelProperty="xqmc" />
									</html:select> </span>
								<span id="yfV">月份： <html:select name="rs" property="yf"
										style="width:">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select> </span>
							</td>
						</tr>
						<tr id="zwpdV">
							<th align="center" style="display: none">
								自我评定
							</th>
							<td colspan="6">
								<html:textarea name="rs" property="zwpd" rows="4" onblur="chLeng(this,800);"
									style="word-break:break-all;width:99%" />
							</td>
						</tr>
						<%--				<tr>--%>
						<%--					<td align="center">--%>
						<%--						上级部门评定意见--%>
						<%--					</td>--%>
						<%--					<td colspan="6">--%>
						<%--						<html:textarea name="rs" property="yf" rows="4"--%>
						<%--							style="width=100%" />--%>
						<%--					</td>--%>
						<%--				</tr>--%>
						<%--				<tr>--%>
						<%--					<td align="center">--%>
						<%--						总评--%>
						<%--					</td>--%>
						<%--					<td colspan="6">--%>
						<%--						<html:textarea name="rs" property="yf" rows="4"--%>
						<%--							style="width=100%" />--%>
						<%--					</td>--%>
						<%--				</tr>--%>
						<%--				<tr>--%>
						<%--					<td align="center">--%>
						<%--						备注--%>
						<%--					</td>--%>
						<%--					<td colspan="7">--%>
						<%--						<html:textarea name="rs" property="yf" rows="6"--%>
						<%--							style="width=100%" />--%>
						<%--					</td>--%>
						<%--				</tr>--%>
						</tbody>
						 <tfoot <logic:equal value="view" name="view">
									style="display: none"
								</logic:equal>>
						      <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
										<button onclick="dataSave();" id="buttonSave">
											保存
										</button>
										<button onclick="Close();return false;" id="buttonQx">
											取消
										</button>				           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
					</table>
				</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
			alert("操作成功！");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
			alert("该楼幢已经在本学年申请了，不必重复申请！");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
				alert("操作失败！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
		</script>
		</logic:equal>
		<logic:equal value="yes" name="existx">
			<script language="javascript">
			alert("用户已存在！");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<script type="text/javascript">
		function cheangelx(){
			var cheangelx = document.getElementById("zwpdlx").value;
			if(cheangelx == "学年"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="none";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "学期"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "月"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("yfV").style.display="inline";
			}else{
				document.getElementById("xnV").style.display="none";
				document.getElementById("xqV").style.display="none";
				document.getElementById("yfV").style.display="none";
			}
	 	}
	</script>
	</body>
</html>
