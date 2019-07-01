<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>广东省轻工业技师学院学生学籍卡</title>
<style>
.font_style td{font-size:11pt;font-family:宋体; }

.head_table td{font-size:12.0pt;font-family:宋体;
			   mso-ascii-font-family:"Times New Roman";
			   mso-hansi-font-family:"Times New Roman"}
</style>
<%--<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>--%>
<object id="webbrowser" classid="clsid:8856f961-340a-11d0-a96b-00c04fd705a2" height="0"
  width="0" viewastext>
  </object>

<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="<%=request.getContextPath()%>/xgWeb/images/webprint.cab" viewasext> </object>
<script>   

		//设置网页打印的页眉页脚为空 
		function PageSetup_Null() 
		{ 
			try 
			{ 
 
				//webbrowser.printing.portrait = true;//横印
				
			} 
			catch(e){
			    //alert(e);
			} 
		}
		
		function Print() {

			PageSetup_Null();
		  	document.all.webbrowser.ExecWB(6,2);// 打印
		}
		 
		window.onload = function(){	
			Print();
			window.setTimeout("a()","5000");
		}
		function a(){
			
			var xhstr = document.getElementById("xhstr").value;/**xhstr中包括了所有被选中的考生号*/
			var tempArray = xhstr.split(',');
			if(tempArray[0] == null || tempArray[0] == ""){
				alertInfo("打印结束！");
				return false;
			}
			else
			{
				document.forms[0].action="xsxx_tygl.do?method=plPrintJsp";
				document.forms[0].submit();
			}
					
		}
</script>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>



	
<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
	<input type="hidden" name="xhstr" id="xhstr" value="<bean:write name="xhstr" />"/>
<table width="980px" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<b><br/>
					<span style='font-size:21.0pt;font-family:华文中宋;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						${xxmc }学生学籍卡
					</span>
					<br/>
					</b>
					<br/>
					<div align="left">
						<table class="head_table">
							<tr>
								<td>专业系：</td>
								<td width="120px">${jbxx.xymc}</td>
								<td>专业：</td>
								<td width="120px">${jbxx.zymc}</td>
								<td>班级：</td>
								<td width="120px">${jbxx.bjmc}</td>
								<td>学制：</td>
								<td width="60px">${jbxx.xz}</td>
								<td>入学时间：</td>
								<td width="100px">${jbxx.rxrq}</td>
								<td>学籍号：</td>
								<td width="80px">${jbxx.xh}</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
							<td style="width: 50px"></td>
						</tr>
						<tr style="height: 50px">
							<td align="center" colspan="1" >
								<b>姓名</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.xm }
							</td>
							<td align="center" colspan="1" >
								<b>性别</b>
							</td>
							<td align="center" colspan="1" >
								${jbxx.xb }
							</td>
							<td align="center" colspan="1" >
								<b>出生<br/>年月</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.csrq }
							</td>
							<td align="center" colspan="1" >
								<b>身份<br/>证号</b>
							</td>
							<td align="center" colspan="4" >
								${jbxx.sfzh }
							</td>
							<td align="center" colspan="1" >
								<b>民族</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.mzmc }
							</td>
							<td align="center" colspan="2" rowspan="3" >
								<img style="width:100px;height:130px;" src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh}" border="0"/>
							</td>
						</tr>
						<tr style="height: 50px">
							<td align="center" colspan="1" >
								<b>政治<br/>面貌</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.zzmmmc }
							</td>
							<td align="center" colspan="1" >
								<b>籍贯</b>
							</td>
							<td align="center" colspan="5" >
								${jbxx.jgmc }
							</td>
							<td align="center" colspan="1">
								<b>户口<br/>性质</b>
							</td>
							<td align="center" colspan="4">
							<logic:equal name="jbxx" property="zd5" value="城市">
								▉城市&nbsp;□县镇&nbsp;□农村
							</logic:equal>
							<logic:equal name="jbxx" property="zd5" value="县镇非农">
								□城市&nbsp;▉县镇&nbsp;□农村
							</logic:equal>
							<logic:equal name="jbxx" property="zd5" value="农村">
								□城市&nbsp;□县镇&nbsp;▉农村
							</logic:equal>
							<logic:notEqual name="jbxx" property="zd5" value="城市">
								<logic:notEqual name="jbxx" property="zd5" value="县镇非农">
									<logic:notEqual name="jbxx" property="zd5" value="农村">
										□城市&nbsp;□县镇&nbsp;□农村
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
							</td>
							<td align="center" colspan="1">
								<b>是否<br/>低保</b>
							</td>
							<td align="center" colspan="2" >
								
							</td>
						</tr>
						<tr style="height: 50px">
							<td align="center" colspan="1" >
								<b>文化<br/>程度</b>
							</td>
							<td align="center" colspan="2" >
								${jbxx.pyccmc }
							</td>
							<td align="center" colspan="1" >
								<b>毕业<br/>学校</b>
							</td>
							<td align="center" colspan="5" >
								${jbxx.RXQDW }
							</td>
							<td align="center" colspan="2">
								<b>爱好、特长</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.tc }
							</td>
							<td align="center" colspan="1">
								<b>身体<br/>状况</b>
							</td>
							<td align="center" colspan="2">
								${jbxx.jkzk }
							</td>
						</tr>
						<tr style="height: 25px">
							<td rowspan="2">
								<b>家庭详<br/>细地址</b>
							</td>
							<td rowspan="2" colspan="8">
								${jbxx.jtszd}
							</td>
							<td align="center" colspan="2">
								<b>家庭联系人</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy1_xm}
							</td>
							<td align="center" colspan="2">
								<b>本人手机号码</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.sjhm}
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>家庭固定电话</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtdh}
							</td>
							<td align="center" colspan="2">
								<b>家长手机号码</b>
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy1_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td rowspan="5">家<br/>庭<br/>主<br/>要<br/>成<br/>员</td>
							<td colspan="2">
								<b>姓名</b>
							</td>
							<td>
								<b>性别</b>
							</td>
							<td>
								<b>年龄</b>
							</td>
							<td colspan="2">
								<b>与本人关系</b>
							</td>
							<td style="width: 60px">
								<b>政治面貌</b>
							</td>
							<td colspan="6">
								<b>工作单位</b>
							</td>
							<td align="center" colspan="2">
								<b>职务</b>
							</td>
							<td align="center" colspan="3" >
								<b>电话号码</b>
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
								${jbxx.jtcy1_xm}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
								${jbxx.jtcy1_gx}
							</td>
							<td>
							</td>
							<td colspan="6">
								${jbxx.jtcy1_gzdz}
							</td>
							<td align="center" colspan="2">
								${jbxx.jtcy1_zw}
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy1_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
								${jbxx.jtcy2_xm}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
								${jbxx.jtcy2_gx}
							</td>
							<td>
							</td>
							<td colspan="6">
								${jbxx.jtcy2_gzdz}
							</td>
							<td align="center" colspan="2">
								${jbxx.jtcy2_zw}
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy2_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
								${jbxx.jtcy3_xm}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
								${jbxx.jtcy3_gx}
							</td>
							<td>
							</td>
							<td colspan="6">
								${jbxx.jtcy3_gzdz}
							</td>
							<td align="center" colspan="2">
								${jbxx.jtcy3_zw}
							</td>
							<td align="center" colspan="3" >
								${jbxx.jtcy3_lxdh2}
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="2">
							</td>
							<td>
							</td>
							<td>
							</td>
							<td colspan="2">
							</td>
							<td>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						<tr style="height: 40px">
							<td rowspan="4"><b>学<br/>习<br/>经<br/>历</b></td>
							<td colspan="7"><b>起始时间</b></td>
							<td colspan="6">
								<b>就读学校</b>
							</td>
							<td align="center" colspan="2">
								<b>职务</b>
							</td>
							<td align="center" colspan="3" >
								<b>备注</b>
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="7">
								<b>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月</b>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="7">
								<b>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月</b>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						<tr style="height: 40px">
							<td colspan="7">
								<b>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月</b>
							</td>
							<td colspan="6">
							</td>
							<td align="center" colspan="2">
							</td>
							<td align="center" colspan="3" >
							</td>
						</tr>
						
					</table>
				</td>
			</tr>
			<tr>
				<td align="left">
				<br/>
					<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					注：此学籍卡一式两份，一份作为学生毕业档案，一份<bean:message key="lable.xb" />存档。
					</span>
				</td>
			</tr>
			<tr><td><br/><br/></td></tr>
			<tr>
				<td>
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
							<td style="width: 6.25%"></td>
						</tr>
						<tr>
							<td colspan="8">
								<b>第一学年操行评语</b>
							</td>
							<td colspan="8">
								<b>第二学年操行评语</b>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<b>第一学期</b>
							</td>
							<td colspan="4">
								<b>第二学期</b>
							</td>
							<td colspan="4">
								<b>第一学期</b>
							</td>
							<td colspan="4">
								<b>第二学期</b>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py001}
								</p>
								<p align="left">操行等级：${xscxpyxx.dj001}&nbsp;&nbsp;班主任：${xscxpyxx.bzr001}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py002}
								</p>
								<p align="left">操行等级：${xscxpyxx.dj002}&nbsp;&nbsp;班主任：${xscxpyxx.bzr002}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py101}
								</p>
								<p align="left">操行等级：${xscxpyxx.dj101}&nbsp;&nbsp;班主任：${xscxpyxx.bzr101}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py102}
								</p>
								<p align="left">操行等级：${xscxpyxx.dj102}&nbsp;&nbsp;班主任：${xscxpyxx.bzr102}</p>
								<br/>
							</td>
						</tr>
						<tr>
							<td style="height: 50px"><b>奖惩<br/>情况</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>奖惩<br/>情况</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>奖惩<br/>情况</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>奖惩<br/>情况</b></td>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td colspan="8">
								<b>第三学年操行评语</b>
							</td>
							<td colspan="2" rowspan="2"><b>学籍异动记录</b></td>
							<td colspan="6" rowspan="2"><b>毕业鉴定</b></td>
						</tr>
						<tr>
							<td colspan="4">
								<b>第一学期</b>
							</td>
							<td colspan="4">
								<b>第二学期</b>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py201}
								</p>
								<p align="left">操行等级：${xscxpyxx.dj201}&nbsp;&nbsp;班主任：${xscxpyxx.bzr201}</p>
								<br/>
							</td>
							<td colspan="4">
								<p align="left" style="height: 200px">
								<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${xscxpyxx.py202}
								</p>
								<p align="left">操行等级：${xscxpyxx.dj202}&nbsp;&nbsp;班主任：${xscxpyxx.bzr202}</p>
								<br/>
							</td>
							<td rowspan="2" colspan="2">
								
							</td>
							<td rowspan="2" colspan="6">
								<p align="left" style="font-size: 11pt">鉴定意见：</p>
								<p style="height: 210px"></p>
								<p align="center">鉴定部门盖章</p>
							</td>
						</tr>
						<tr>
							<td style="height: 50px"><b>奖惩<br/>情况</b></td>
							<td colspan="3"></td>
							<td style="height: 50px"><b>奖惩<br/>情况</b></td>
							<td colspan="3"></td>
						</tr>
						
						
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</body>
</html>