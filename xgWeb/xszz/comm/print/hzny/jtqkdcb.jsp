<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- 头文件 -->
<html:html>
<body>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<p align=center style='text-align:center'>
			<b><span
				style='font-size:18.0pt;font-family:黑体;"Times New Roman"'>华中农业大学学生家庭情况调查表</span>
			</b>
		</p>
		<br />
		<p align=left>
			<b><span
				style='font-size:14.0pt;font-family:新宋体;"Times New Roman";"Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院（系）：</span>
			</b><u><span lang=EN-US style='font-size:14.0pt;'>${rs.xymc }
			</span>
			</u><b><span
				style='font-size:14.0pt;font-family:新宋体;"Times New Roman"'>专业：</span>
			</b><u><span lang=EN-US style='font-size:14.0pt;'>${rs.zymc }
			</span>
			</u><b><span
				style='font-size:14.0pt;font-family:新宋体;"Times New Roman"'>学号：</span>
			</b><u><span lang=EN-US style='font-size:14.0pt;'>
				${rs.xh }
			</span>
			</u>
		</p>
		<table width="100%" class="printstyle" align="center">
			<tr style="height:40px">
				<td width="5.5%" rowspan=5>
					<p align=center style='
  text-align:center'>
						<b><span style='font-family:新宋体'>学<br/>生<br/>本<br/>人<br/>基<br/>本<br/>情<br/>况</span> </b>
					</p>
				</td>
				<td width="11%" colspan=2 >
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>姓名</span>
					</p>
				</td>
				<td width="22%" colspan=4 align=center>
					${rs.xm }
				</td>
				<td width="5.5%">
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>性别</span>
					</p>
				</td>
				<td width="11%" colspan=2 align=center>
					${rs.xb }
				</td>
				<td width="13%" colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>出生年月</span>
					</p>
				</td>
				<td width="13%" colspan=3 align=center>
					${rs.csrq }
				</td>
				<td width="15%" colspan=2 rowspan=4>
					<p align=center style='text-align:center'>
						<b><span style='  font-family:宋体'>贴照片处</span> </b>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>政治<br/>面貌</span>
					</p>
				</td>
				<td colspan=4 align=center>
					${rs.zzmmmc }
				</td>
				<td>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>民族</span>
					</p>
				</td>
				<td colspan=2 align=center>
					${rs.mzmc }
				</td>
				<td colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>毕业中学</span>
					</p>
				</td>
				<td colspan=3 align=center>
					
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>身份证<br/>号码</span>
					</p>
				</td>
				<td colspan=5 align=center>
					${rs.sfzh}
				</td>
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>个人<br/>特长</span>
					</p>
				</td>
				<td colspan=6 align=center>
					&nbsp;
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>家庭详<br/>细地址</span>
					</p>
				</td>
				<td colspan=13 align=center>
					${rs.jtdz }
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>邮政<br/>编码</span>
					</p>
				</td>
				<td colspan=5 align=center>
					${rs.jtyb }
				</td>
				<td colspan=3 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>联系电话</span>
					</p>
				</td>
				<td colspan=7 align=center>
					<p>
						${rs.jtdh }
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td rowspan=2>
					<p align=center style='text-align:center'>
						<b><span style='  font-family:宋体;"Times New Roman"'>家庭<br/>经济<br/>收入</span>
						</b>
					</p>
				</td>
				<td colspan=2 align=center>
					<p align=center style='text-align:center;line-height:90%'>
						<span style='font-family:宋体;"Times New Roman"'>城镇</span>
					</p>
				</td>
				<td colspan=15 align=center>
					<logic:equal name="rs" property="jthk" value="城镇">
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>家庭人口共</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrs }&nbsp;&nbsp; </span> </u><span
							style='font-family:宋体;"Times New Roman"'>人，全家年总收入</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtnzsr }&nbsp;&nbsp;
						</span> </u><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>元，人均年收入</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;</span> </u><span
							style='font-family:宋体;"Times New Roman"'>元</span>
					</p>
					</logic:equal>
					<logic:notEqual name="rs" property="jthk" value="城镇">
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>家庭人口共</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:宋体;"Times New Roman"'>人，全家年总收入</span><u><span
							lang=EN-US>&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </u><span
							style='font-family:宋体;"Times New Roman"'>元，人均年收入</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:宋体;"Times New Roman"'>元</span>
					</p>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=2 align=center>
					<p align=center style='text-align:center;line-height:90%'>
						<span style='font-family:宋体;"Times New Roman"'>农村</span>
					</p>
				</td>
				<td colspan=15 align=center>
					<logic:equal name="rs" property="jthk" value="农村">
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>家庭人口共</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrs }&nbsp;&nbsp; </span> </u><span
							style='font-family:宋体;"Times New Roman"'>人，全家年总收入</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtnzsr }&nbsp;&nbsp;
						</span> </u><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>元，人均年收入</span><u><span
							lang=EN-US>&nbsp;&nbsp;${rs.jtrjsr }&nbsp;&nbsp;</span> </u><span
							style='font-family:宋体;"Times New Roman"'>元</span>
					</p>
					</logic:equal>
					<logic:notEqual name="rs" property="jthk" value="农村">
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>家庭人口共</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:宋体;"Times New Roman"'>人，全家年总收入</span><u><span
							lang=EN-US>&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </u><span
							style='font-family:宋体;"Times New Roman"'>元，人均年收入</span><u><span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> </u><span
							style='font-family:宋体;"Times New Roman"'>元</span>
					</p>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:40px">
				<td rowspan=6>
					<p align=center style='
  text-align:center'>
						<b><span style='font-family:新宋体'>家<br/>庭<br/>成<br/>员<br/>情<br/>况</span> </b>
					</p>
				</td>
				<td colspan=2 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>姓名</span>
					</p>
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>年龄</span>
					</p>
				</td>
				<td colspan=3 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>与本人关系</span>
					</p>
				</td>
				<td colspan=6 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>工作（学习）单位</span>
					</p>
				</td>
				<td width="6%" align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>职业</span>
					</p>
				</td>
				<td colspan=2 width=11% align=center>
					<p >
						<span style='font-family:新宋体'>年收入（元）</span>
					</p>
				</td>
				<td colspan=2  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>健康状况</span>
					</p>
				</td>
			</tr>
			
				<%
				ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
				int len=0;
				if(cyList!=null && cyList.size()>0){
					len=cyList.size();
				}
				
				for(int i=0;i<len;i++){
					HashMap<String,String>cyMap=cyList.get(i);
				%>
				<tr>
				 <td colspan=2 align=center>
						<div align="center">
							<%=cyMap.get("cyxm")==null ? "" : cyMap.get("cyxm")%>
						</div>
					</td>
					<td align=center>
						<div align="center">
							<%=cyMap.get("cynl")==null ? "" : cyMap.get("cynl")%>
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							<%=cyMap.get("mc")==null ? "" : cyMap.get("mc")%>
						</div>
					</td>
					<td colspan=6 align=center>
						<div align="center">
							<%=cyMap.get("cygzdw")==null ? "" : cyMap.get("cygzdw")%>
						</div>
					</td>
					<td width="6%" align=center>
						<div align="center">
							<%=cyMap.get("cyzy")==null ? "" : cyMap.get("cyzy")%>
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							<%=cyMap.get("cynsr")==null ? "" : cyMap.get("cynsr")%>&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=2  align=center>
						<div align="center">
							<%=cyMap.get("cyjkzk")==null ? "" : cyMap.get("cyjkzk")%>
						</div>
					</td>
				</tr>
				<%
				}
				%> 
				 
				 
				<%
				
				for(int i=0;i<5-len;i++){
				%>
				<tr>
					<td colspan=2 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=6 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td width="6%" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=2  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<%
				}
				%>
			<tr style="height:40px">
				<td rowspan=3>
					<p align=center style='text-align:center'>
						<b><span style='  font-family:宋体;"Times New Roman"'>特殊<br/>情况<br/>说明</span>
						</b>
					</p>
				</td>
				<td colspan=17>
					<p style='line-height:90%'>
						<span lang=EN-US style='font-family:
  宋体'>1.</span><span
							style='font-family:宋体'>在当地是否申请或受到资助<u><span lang=EN-US>
							<logic:equal name="rs" property="kzzd4" value="是">
							&nbsp;&nbsp;①&nbsp;&nbsp;
							</logic:equal>
							<logic:equal name="rs" property="kzzd4" value="否">
							&nbsp;&nbsp;②&nbsp;&nbsp;
							</logic:equal>
							</span> </u>①是②否；如果是，资助金额<u><span lang=EN-US>&nbsp;&nbsp;${rs.kzzd5 }&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>；资助单位<u>：<span lang=EN-US>&nbsp;${rs.kzzd6 }&nbsp;
							</span> </u> </span>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=17>
					<p style='line-height:90%'>
						<span lang=EN-US style='font-family:
  宋体'>2.</span><span
							style='font-family:宋体'>本人为<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>（①孤儿②单亲子女③残疾④烈士子女⑤优抚家庭子女⑥下岗职工子女）</span>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=17>
					
						<span lang=EN-US style='font-family:宋体'>3.</span><span lang=EN-US
							style='font-family:新宋体'> </span><span style='font-family:新宋体'>家庭遭受自然灾害情况：<u><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>。家庭遭受突发意外事件：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。</span><br/>
						<span style='font-family:新宋体'>&nbsp;&nbsp;&nbsp;家庭成员因残疾、年迈而劳动能力弱情况：<u><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>。</span>
						<br/>
						<span style='font-family:新宋体'>&nbsp;&nbsp;&nbsp;家庭成员失业情况：<u><span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>。家庭欠债情况：<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>。</span>
						<br/>
						<span style='font-family:新宋体'>&nbsp;&nbsp;&nbsp;其他情况：<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span> </u>。</span>
					
				</td>
			</tr>
			<tr >
				<td>
					<p align=center style='
  text-align:center'>
						<b><span style='font-family:新宋体'>签<br/>章</span> </b>
					</p>
				</td>
				<td width="5.5%">
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>学<br/>生<br/>本<br/>人</span>
					</p>
				</td>
				<td colspan=3 width=14%>
					&nbsp;
				</td>
				<td>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>学生<br/>家长<br/>或监<br/>护人</span>
					</p>
				</td>
				<td colspan=3>
					&nbsp;
				</td>
				<td colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>学生家庭<br/>所在地乡<br/>镇或街道<br/>民政部门</span>
					</p>
				</td>
				<td colspan=6>
					<p>
						<span style='font-family:新宋体'>经办人签字：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span> </span>
					</p>
					<p>
						<span style='font-family:新宋体'>单位名称：</span>
					</p>
					<p>
						<span style='font-family:新宋体'>（加盖公章）</span>
					</p>
					<p align=center style='text-align:center;
  '>
						<span lang=EN-US style='font-family:新宋体'>&nbsp;&nbsp;&nbsp;&nbsp;
						</span><span style='font-family:新宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;
						</span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span>日</span>
					</p>
				</td>
			</tr>
			<tr style="height:40px">
				<td rowspan=2>
					<p align=center style='text-align:center'>
						<b><span style='font-family:新宋体'>民政<br/>部门<br/>信息</span> </b>
					</p>
				</td>
				<td colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>详细通讯地址</span>
					</p>
				</td>
				<td colspan=13>
					${rs.kzzd1 }
				</td>
			</tr>
			<tr style="height:40px">
				<td colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>邮政编码</span>
					</p>
				</td>
				<td colspan=4>
					${rs.kzzd3 }
				</td>
				<td colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>联系电话</span>
					</p>
				</td>
				<td colspan=6>
					<p>
						${rs.kzzd2 }
					</p>
				</td>
			</tr>

		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
