<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
		function jxjSqSavett(){
			var xmdm = document.getElementById('jxjdm').value;
			var xh = document.getElementById('xh').value;
			var xxjl = document.getElementById('xxjl').value;
			var fdyyj = document.getElementById('fdyyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var yhkh = document.getElementById('yhkh').value;

			if(!isNumber(yhkh)){
				alert("银行卡号只能是数字!");
				return false;
			}
			if(xmdm == null || xmdm == ""){
				alert("请选择要申请的奖助学金!");
				return false;
			}
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
			if(fdyyj != null){
	         	if(fdyyj.length > 200){	         
	          		 alert("班级推荐意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			if(xxjl != null){
	         	if(xxjl.length > 800){	         
	          		 alert("本人申请不能大于400个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.length > 200){	         
	          		 alert(jQuery("#xbmc").val()+"意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			if(xxshyj != null){
	         	if(xxshyj.length > 200){	         
	          		 alert("学校意见不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&doType=save&jxjtype=yxxsjxj";
			document.forms[0].submit();
		}
		
		function chang(){
			
			alert('tt');
			return false;
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("请选择要下载的奖助学金!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmmbxz&xmdm="+xmdm;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function initjxjList(){
			var jxjdm = document.getElementById("jxjdm").value;
			GetListData.getJxjdm(jxjdm,function initTjList(data){
					if (data != null) {
						if(document.getElementById("jxjlb").value!=data || document.getElementById("jxjlb").value=="专项奖学金"){
							document.getElementById("jxjlb").value=data;
							if(data=="突出贡献奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=tcgxjxj";
								document.forms[0].submit();
							}else if(data=="优秀学生奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxxsjxj";
								document.forms[0].submit();
							}else if(data=="社会工作奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=shgzjxj";
								document.forms[0].submit();
							}else if(data=="科研创新奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=kycxjxj";
								document.forms[0].submit();
							}else if(data=="单项奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=dxjxj";
								document.forms[0].submit();
							}else if(data=="优秀毕业生奖学金"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxbysjxj";
								document.forms[0].submit();
							}else if(data=="专项奖学金"){
								var jxjlb;
								if(jxjdm=="00071"){
									jxjlb="fzzgjxj";
								}else if(jxjdm=="00072"){
									jxjlb="gjjxj";
								}else if(jxjdm=="00073"){
									jxjlb="hdjxj";
								}else if(jxjdm=="00074"){
									jxjlb="smjxj";
								}else{
										alert("选择错误，请重新选择");
								}
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype="+jxjlb;
								document.forms[0].submit();
							}
						}else{
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
		}

		function heckistype(){
			var userlx = document.getElementById("userlx").value;
			if(userlx=="stu"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xylxyj2").style.display = "none";
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
			}else if(userlx=="xy"){
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
			}else if(userlx=="xx"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xylxyj2").style.display = "none";
			}
		}
		function pjpyjxjprint(){
			var jxjdm = document.getElementById("jxjdm").value;

			if(jxjdm == ""){
				alert("请选择奖学金名称");
				return false;
			}
			var xh = document.getElementById("xh").value;
			var xn = $('xn').value;
			window.open('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+xn+jxjdm+"&jxjcxzj="+jxjdm);
			//"height=867,width=1024,status=yes,toolbar=no,menubar=no,location=no");
			//showOpenWindow('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjcxzj='+jxjdm,'900','700');
			//showOpenWindow('/xgxt/zjlgPjpy.do?method=jxjReport&xh='+xh,'900','700');
			//document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjcxzj='+jxjdm;
			//document.forms[0].target = "_blank";
			//document.forms[0].submit();
			//document.forms[0].target = "_self";
			}
	</script>
</head>

<body onload="heckistype();">
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>评奖评优 - 奖学金申请</a>
		</p>
	</div>
	
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或不符合申请条件！！！
			</p>
		</center>
	</logic:equal>
	
		<html:form action="/zjlgPjpy" method="post">
			
<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=yxxsjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />"/>
			<input type="hidden" id="xq" name="xq"
				value="<bean:write name="rs" property="xq" />"/>
			<input type="hidden" id="jxjmc" name="jxjmc"
				value="yxxsjxj"/>
				
			<input id="userlx" type="hidden" value="<bean:write name="userType" scope="session" />"/>	
			
			
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>

			<div class="div">
			<table class="formlist" width="90%">
			<thead>
				<tr><th colspan='4'><span>填写申请表</span></th></tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<font color="red">*</font>奖学金名称
					</th>
					<td width="25%">
						<html:select property="jxjdm" style="width:160px"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
					</td>
					<th>
						<div>
							奖学金类别
						</div>
					</th>
					<td>
						<html:text property="jxjlb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td>
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td>
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly" />
						</td>
					</logic:equal>
					<th>
						<div>
							姓名
						</div>
					</th>
					<td>
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<font color="red">*</font>银行卡号
						</div>
					</th>
					<td>
						<html:text property="yhkh" maxlength="50"></html:text>
					</td>
					<th>
						<div>
							银行类型
						</div>
					</th>
					<td>
						<!--<html:text property="yhlx" maxlength="100" ></html:text>-->
						<html:select property="yhlx" name="rs" styleId="yhlx"
									>
									<html:option value=""></html:option>
									<html:options collection="yhklxList" property="yhklxmc" labelProperty="yhklxmc"/>
								</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学年
						</div>
					</th>
					<td>
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<th>
						<div>
							学期
						</div>
					</th>
					<td>
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							性别
						</div>
					</th>
					<td>
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<th>
						<div>
							出生年月
						</div>
					</th>
					<td>
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							政治面貌
						</div>
					</th>
					<td>
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<th>
						<div>
							担任职务
						</div>
					</th>
					<td>
						<html:text name="rs" property="zw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<html:text name="rs" property="xymc" readonly="true"></html:text>
					</td>
					<th>
						<div>
							班级
						</div>
					</th>
					<td>
						<html:text name="rs" property="bjmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
								参评组排名
						</div>
					</th>
					<td>
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<th>
						<div>
							德育
						</div>
					</th>
					<td>
						<html:text name="rs" style="width:60px" property="dycj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							智育
						</div>
					</th>
					<td>
						<html:text name="rs" style="width:60px" property="zycj" readonly="true"></html:text>
					</td>
					<th>
						<div>
							体育
						</div>
					</th>
					<td>
						<html:text name="rs" style="width:60px" property="tycj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th rowspan="2" align="center">
						<div>
							本人申请
						</div>
					</th>
					<td onpropertychange="this.style.posHeight=this.scrollHeight" colspan="3">
						（本人德智体等诸方面的表现，包含参与科研活动、论文发表、社会实践等方面）
					</td>
				</tr>
				<tr>
					<td class="word-break" colspan="3">
						<html:textarea property="xxjl" rows='6' style="width:100%"/>
					</td>
				</tr>
				<tr id="xylxyj1">
					<th align="center">
						<div>
							班级推荐意见
						</div>
					</th>
					<td class="word-break" colspan="3">
						<html:textarea property="fdyyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xylxyj2">
					<th rowspan="1">
						<div>
							<bean:message key="lable.xb" />意见
						</div>
					</th>
					<td class="word-break" colspan="3">
						<html:textarea property="xyshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj1">
					<th rowspan="1">
						<div>
							学校意见
						</div>
					</th>
					<td class="word-break" colspan="3">
						<html:textarea property="xxshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj2">
					<th>
						<div>
							备注
						</div>
					</th>
					<td colspan="3">
					<html:textarea name="rs" property="bz" rows='6' style="width:100%;display: none" />
						1. 本表一式二份，表中填写内容要求电脑打印；<br/>
						2. <bean:message key="lable.xb" />、班级填写清楚。
					</td>
				</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
				        <button type="button" id="buttonSave" onclick="jxjSqSavett();">
							提交申请
						</button>
						<button type="button" onclick="pjpyjxjprint()">
							打印报表
						</button>
						<button type="button" id="btn_cjprint" onclick="showOpenWindow('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
							学生成绩单
						</button>
						<button type="button" id="btn_cfqk" onclick="showOpenWindow('zjlgPjpy.do?method=jxjcfqk&xh='+document.getElementById('xh').value+'&xn='+document.getElementById('xn').value+'&xq='+document.getElementById('xq').value,700,600)">
							处罚情况
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
			</table>
			</div>
			
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该奖学金已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf1">
			<script>
			        alert("智育成绩递增不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf2">
			<script>
			        alert("智育分不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf3">
			<script>
			        alert("德育分不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf4">
			<script>
			        alert("智育分排名不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf5">
			<script>
			        alert("德育成绩排名不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf6">
			<script>
			        alert("外语成绩不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf7">
			<script>
			        alert("综合素质分排名不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf8">
			<script>
			        alert("综合素质分不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("你不是毕业生不符合申请该奖学金的条件！");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("该学生不存在综合素质测评分！");			    
			        </script>
			</logic:equal>
			<logic:equal name="jxjcftj" value="yes">
			<script>
			        alert("该奖学金已申请");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd1">
			<script>
			        alert("励志单项奖学金和优秀学生奖学金不能兼得");			    
			        </script>
			</logic:equal>
			<logic:equal name="isczcpf" value="isczcpf">
			<script>
			        alert("找不到该学生的综合素质测评成绩，不能申请");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd2">
			<script>
			        alert("优秀学生奖学金不能兼得");			    
			        </script>
			</logic:equal>
			<logic:equal name="cpzisnull" value="cpzisnull">
			<script>
			        alert("该学生所在参评组为空，不能申请");			    
			        </script>
			</logic:equal>
		</html:form>
</body>
</html>
