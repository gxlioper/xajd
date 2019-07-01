<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 头文件 -->
<html>
	<body>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
				.noPrin{display:none;}
			</style>
		<!-- end -->
	</head>
	<center>
		<table><tr><td><span style="font-size:24px;font-family:黑体">江苏省南京技师学院</span></td><td rowspan="2" >
		<span style="font-size:26px;font-family:黑体">学籍卡</span></td></tr>
                          <tr><td><span style="font-size:24px;font-family:黑体">南京市高级技术学校</span> </td></tr> </table>

	</center>
	<br />
	<table width="100%" style="font-size:12px;">
	<tr>
	<td width="80%">
	<u>${rs.xymc }</u>系 <u>${rs.nj }</u>级 <u>${rs.zymc }</u> 专业<u>${rs.bjmc }</u>  班 
	</td>
	<td width="20%">
	学号： <u>  ${rs.xh }</u>
	</td>
	</tr>
	</table>
	<table class="printtab" width="100%" style="font-size:13px;">
		<tr style="height:35px">
			<td align="center" width="15%" colspan="2">
				姓名
			</td>
			<td  align="center" width="10%">
			${rs.xm }
			</td>
			<td  align="center" width="10%">
				性 别
			</td>
			<td align="center" width="12%">
			${rs.xb }
			</td>
			<td  align="center" width="12%">
				民族
			</td>
			<td  align="center" width="10%">
			${rs.mzmc }
			</td>
			<td  align="center" width="10%">
				籍贯
			</td>
			<td  align="center" width="15%">
			${rs.sydmc }
			</td>
			<td rowspan="6" width="16%">
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				身份证号码
			</td>
			<td colspan="4" align="center">
			${rs.sfzh }
			</td>
			<td align="center" colspan="2">
				政治面貌
			</td>
			<td align="center" >
			${rs.zzmmmc}
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				家庭住址
			</td>
			<td colspan="4" align="center">
			${jtcy.jtszd }
			</td>
			<td colspan="2" align="center">
				邮编
			</td>
			<td align="center" colspan="1">
			${jtcy.jtyb }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				联系电话
			</td>
			<td colspan="2" align="center">
			${rs.save_lxdh }
			</td>
			<td  align="center" colspan="2">
				入学前毕业学校
			</td>
			<td align="center" colspan="3">
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="2">
				学制
			</td>
			<td colspan="2" align="center">
			${rs.xz }
			</td>
			<td  align="center" colspan="2">
				毕(肄)业证书号
			</td>
			<td colspan="3" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" colspan="3">
				取得何种技术等级及证书等级
			</td>
			<td colspan="3"  align="center">
			</td>
			<td align="center" colspan="2" >
				等级证书编号
			</td>
			<td  align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="4" align="center">
				家庭
				<br />
				主要
				<br />
				成员
			</td>
			<td align="center" width="9%">
				称谓
			</td>
			<td colspan="1" align="center" width="10%">
				姓名
			</td>
			<td colspan="4" align="center">
				工作单位
			</td>
			<td colspan="4" align="center">
				联系电话
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
			${jtcy.jtcy1_gx }
			</td>
			<td colspan="1" align="center">
			${jtcy.jtcy1_xm }
			</td>
			<td colspan="4" align="center">
			</td>
			<td colspan="3" align="center">
			${jtcy.jtcy1_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
			${jtcy.jtcy2_gx }
			</td>
			<td  colspan="1" align="center">
			${jtcy.jtcy2_xm }
			</td>
			<td colspan="4" align="center">
			</td>
			<td colspan="3" align="center">
			${jtcy.jtcy2_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
			${jtcy.jtcy3_gx }
			</td>
			<td colspan="1" align="center">
			${jtcy.jtcy3_xm }
			</td>
			<td colspan="4" align="center">
			</td>
			<td colspan="3" align="center">
			${jtcy.jtcy3_lxdh1 }
			</td>
		</tr>
			<tr style="height:35px">
			<td rowspan="3" align="center">
				学籍
				<br />
				变更
				<br />
				记载
			</td>
			<td colspan="6" align="center">
				日 期 及 原 因
			</td>
			<td colspan="5" align="center">
				批准部门
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="5" align="center">
				奖
				<br />
				惩
				<br />
				记
				<br />
				载
			</td>
			<td colspan="6" align="center">
				何 年 何 月 何 日 何 种 奖 励
			</td>
			<td colspan="5" align="center">
				批准部门
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="6" align="center">
			</td>
			<td colspan="5" align="center">
			</td>
		</tr>
	</table>
	<table height='300px'><tr><td></td></tr></table>
	<table class="printtab" width="100%" style="font-size:13px;">
	<tr>
	<td rowspan="3" width='5%'> 
	学<br> 
	年 
	<br> 
	鉴 
	<br> 
	定</td><td width="5%">
	第<br/>
	一<br/>
	学<br/>
	年<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          操行：&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;班主任: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</td>
	</tr>
	  <tr>
	<td width="5%">
	第<br/>
	二<br/>
	学<br/>
	年<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          操行：&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;班主任: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</td></tr>
	<tr>
	<td width="5%">
	第<br/>
	三<br/>
	学<br/>
	年<br/>
	</td>
	<td> <br/><br/><br/>
	&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
     &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
          操行：&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;班主任: &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
      &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;       
          年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</td></tr>
	</table>
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
	</body>
</html>
