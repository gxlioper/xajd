<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>宋庆龄基金会中海油助学基金</title>
	<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div style='layout-grid:15.6pt' align="center"> 
  <b><span style='font-size:20.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>中国宋庆龄基金会“中海油大学生助学基金”</span></b>
  <br/>
  <b><span style='font-size:20.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>受助学生申请表</span></b>
  <br/>
  <b>初次申请□&nbsp;&nbsp;&nbsp; 再次申请□&nbsp;&nbsp;&nbsp;学校<u>&nbsp;&nbsp;&nbsp;${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>&nbsp;&nbsp;编号<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u></b><u></u> 
  <table class="tbstyle" width="75%" height="333px"> 
    <tr> 
      <td colspan=10 align=center bgcolor="#E0E0E0"> <b>个人资料</b></td> 
    </tr> 
    <tr> 
      <td align=center width="16%"> 姓&nbsp;&nbsp; 名</td> 
      <td colspan=2 align=center width="15%">${rs.xm } </td> 
      <td align=center width="10%"> 性&nbsp;&nbsp;别</td> 
      <td colspan=2 align=center width="15%">${rs.xb } </td> 
      <td align=center width="11%"> 民&nbsp;&nbsp;族</td> 
      <td colspan=2 align=center width="16%">${rs.mzmc } </td> 
      <td rowspan=4 align=center width="17%"> 近 期 免 冠 照 片</td> 
    </tr> 
    <tr> 
      <td align=center> 出生日期</td> 
      <td colspan=2 align=center>${rs.csrq } </td> 
      <td colspan=2 align=center> 政治面貌&nbsp;&nbsp;&nbsp;&nbsp;</td> 
      <td colspan=4 align=center>${rs.zzmmmc } </td> 
    </tr> 
    <tr> 
      <td align=center> 特&nbsp;&nbsp;&nbsp;&nbsp; 长</td> 
      <td colspan=8 align=center>${rs.tc } </td> 
    </tr> 
    <tr> 
      <td colspan=2 align=center> 所在院系、班级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
      <td colspan=7 align=center>${rs.xymc }${rs.bjmc } </td> 
    </tr> 
    <tr> 
      <td align=center> 通信地址</td> 
      <td colspan=5 align=center>${rs.xxdz }</td> 
      <td colspan=2 align=center> 联系电话</td> 
      <td colspan=2>${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <td align=center> 邮政编码</td> 
      <td colspan=5 align=center>${rs.xxyb } </td> 
      <td colspan=2 align=center> 电子邮件</td> 
      <td colspan=2>${rs.lxdzxx } </td> 
    </tr> 
    <tr> 
      <td align=center> 身份证号</td> 
      <td colspan=5 align=center>${rs.sfzh } </td> 
      <td colspan=2 align=center> 月生活费</td> 
      <td colspan=2>${rs.shf } </td> 
    </tr> 
    <tr> 
      <td colspan=6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否担任班、校、学生会、党(团)组织干部</td> 
      <td colspan=4 align="center">
      		<logic:equal value="" name="rs" property="sfbgb">
      		否□&nbsp;&nbsp;/&nbsp;&nbsp;
      		是□ &nbsp;&nbsp;&nbsp;&nbsp;
      		请注明 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
      		</logic:equal>
      		
      		<logic:equal value="是" name="rs" property="sfbgb">
      		否□&nbsp;&nbsp;/&nbsp;&nbsp;
      		是■ &nbsp;&nbsp;&nbsp;&nbsp;
      		请注明 <u>${rs.zw }</u>
      		</logic:equal>
      		
      		<logic:equal value="否" name="rs" property="sfbgb">
      		否■&nbsp;&nbsp;/&nbsp;&nbsp;
      		是□ &nbsp;&nbsp;&nbsp;&nbsp;
      		请注明 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
      		</logic:equal>
      </td> 
    		
    </tr> 
    <tr height=0> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td width=35></td> 
      <td width=77></td> 
      <td></td> 
      <td width=42></td> 
      <td></td> 
      <td></td> 
    </tr> 
  </table> 
  
  <br/><br/>
  <table class="tbstyle" width="75%" height="296px"> 
    <tr> 
      <td colspan=8 align=center bgcolor="#E0E0E0"> <b>家庭资料</b></td> 
    </tr> 
    <tr> 
      <td align="center"> 原籍</td> 
      <td colspan=7 align=center> &nbsp;&nbsp;${szsheng } &nbsp;&nbsp;省/市&nbsp;&nbsp;${szshi }&nbsp;&nbsp; 地区/县&nbsp;&nbsp;${szxian }&nbsp;&nbsp;区/乡&nbsp;&nbsp;${rs.szzhen }&nbsp;&nbsp; 街道/村&nbsp;&nbsp;${rs.szcun }&nbsp;&nbsp; 号</td> 
    </tr> 
    <tr> 
      <td align="center"> 地址</td> 
      <td colspan=2 align=center>${rs.jtdz } </td> 
      <td align=center> 邮编</td> 
      <td colspan=2 align=center>${rs.jtyb } </td> 
      <td align=center> 电话</td> 
      <td align=center>${rs.jtdh } </td> 
    </tr> 
    <tr> 
      <td colspan=2 align="center"> 是否贫困县</td> 
      <td colspan=6 align=center> 
      <logic:equal value="" name="rs" property="sfpkx">
      □否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      □是（□国家级贫困县&nbsp;&nbsp;&nbsp;&nbsp; □省级贫困县）
      </logic:equal>
      
      <logic:equal value="否" name="rs" property="sfpkx">
      ■否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      □是（□国家级贫困县&nbsp;&nbsp;&nbsp;&nbsp; □省级贫困县）
      </logic:equal>
      
      <logic:equal value="是" name="rs" property="sfpkx">
      □否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      ■是（
      <logic:equal value="" name="rs" property="pkxjb">
      □国家级贫困县&nbsp;&nbsp;&nbsp;&nbsp; □省级贫困县）
      </logic:equal>
      
      <logic:equal value="国家级贫困县" name="rs" property="pkxjb">
      ■国家级贫困县&nbsp;&nbsp;&nbsp;&nbsp; □省级贫困县）
      </logic:equal>
      
       <logic:equal value="省级贫困县" name="rs" property="pkxjb">
      □国家级贫困县&nbsp;&nbsp;&nbsp;&nbsp; ■省级贫困县）
      </logic:equal>
      
      </logic:equal>
      </td> 
    </tr> 
    <tr> 
      <td colspan=2 align=center> 上年家庭收入</td> 
      <td align=center>${rs.jtnzsr } </td> 
      <td colspan=2 align=center> 家庭成员数</td> 
      <td colspan=3 align=center>${rs.jtrs } </td> 
    </tr> 
    <tr> 
      <td colspan=2 align=center> 父亲职业</td> 
      <td align=center>
     	 	<logic:iterate name="cyList" id="cy">
					<logic:equal value="父亲" name="cy" property="mc">
						${cy.cyzy }
					</logic:equal>
			</logic:iterate>
      </td> 
      <td colspan=2 align=center> 母亲职业</td> 
      <td colspan=3 align=center>
      	<logic:iterate name="cyList" id="cy">
					<logic:equal value="母亲" name="cy" property="mc">
						${cy.cyzy }
					</logic:equal>
			</logic:iterate>
      </td> 
    </tr> 
    <tr> 
      <td colspan=2 align="center"> 家中是否有欠债</td> 
      <td> 
      	<logic:equal value="" name="rs" property="sfqz">
      	有□ / 无□
      	</logic:equal>
      	
      	<logic:equal value="是" name="rs" property="sfqz">
      	有■ / 无□
      	</logic:equal>
      	
      	<logic:equal value="否" name="rs" property="sfqz">
      	有□ / 无■
      	</logic:equal>
      </td> 
      <td colspan=2 align="center"> 父母是否有病或残疾</td> 
      <td colspan=3 align="center"> 
      	<logic:equal value="" name="rs" property="fmjk">
      	父亲有□ &nbsp;&nbsp;/&nbsp;&nbsp;母亲有□&nbsp;&nbsp; /&nbsp;&nbsp;无□
      	</logic:equal>
      	
      	<logic:equal value="父亲有" name="rs" property="fmjk">
      	父亲有■ &nbsp;&nbsp;/&nbsp;&nbsp;母亲有□&nbsp;&nbsp; /&nbsp;&nbsp;无□
      	</logic:equal>
      	
      	<logic:equal value="母亲有" name="rs" property="fmjk">
      	父亲有□ &nbsp;&nbsp;/&nbsp;&nbsp;母亲有■&nbsp;&nbsp; /&nbsp;&nbsp;无□
      	</logic:equal>
      	
      	<logic:equal value="无" name="rs" property="fmjk">
      	父亲有□ &nbsp;&nbsp;/&nbsp;&nbsp;母亲有□&nbsp;&nbsp; /&nbsp;&nbsp;无■
      	</logic:equal>
      	
      	<logic:equal value="父母皆有" name="rs" property="fmjk">
      	父亲有■ &nbsp;&nbsp;/&nbsp;&nbsp;母亲有■&nbsp;&nbsp; /&nbsp;&nbsp;无□
      	</logic:equal>
      </td> 
    </tr> 
    <tr> 
      <td colspan=2 align="center"> 父母是否健在</td> 
      <td colspan=6 align="center">
      	<logic:equal value="" name="rs" property="fmjz">
      	 父母双全□ / 父母双亡□ / 父亡母在□ / 父在母亡□
      	</logic:equal>
      	
      	<logic:equal value="父母双全" name="rs" property="fmjz">
      	 父母双全■ / 父母双亡□ / 父亡母在□ / 父在母亡□
      	</logic:equal>
      	
      	<logic:equal value="父母双亡" name="rs" property="fmjz">
      	 父母双全□ / 父母双亡■ / 父亡母在□ / 父在母亡□
      	</logic:equal>
      	
      	<logic:equal value="父亡母在" name="rs" property="fmjz">
      	 父母双全□ / 父母双亡□ / 父亡母在■ / 父在母亡□
      	</logic:equal>
      	
      	<logic:equal value="父在母亡" name="rs" property="fmjz">
      	 父母双全□ / 父母双亡□ / 父亡母在□ / 父在母亡■
      	</logic:equal>
      </td> 
    </tr> 
    <tr height=0> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td></td> 
      <td width=28></td> 
      <td></td> 
      <td></td> 
    </tr> 
  </table> 
  <br clear=all style='page-break-before:always'> 
  <table class="tbstyle" width="75%"> 
    <tr> 
      <td align=center bgcolor="#E0E0E0">
        <b>申请理由</b></td> 
    </tr> 
    <tr> 
      <td height="200px"> 
      	<p align="left"> &nbsp;&nbsp; ${rs.sqsm }</p>
  		<p align="right">    
      		本人签字<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/> 
        	日期<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </p>
       </td> 
    </tr> 
  </table> 
  
  <br/><br/>
  <table class="tbstyle" width="75%"> 
    <tr> 
      <td align=center bgcolor="#E0E0E0"> <b>学校意见</b></td> 
    </tr>
				<tr height="200px">
					<td align=center>
						<p>
							&nbsp;&nbsp;
						</p>
						<p align="right">
							学校（盖章）
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br />
							日期
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;
								</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						注：请注明该生是否属于特困生或贫困生。
					</td>
				</tr>
			</table> 
</div> 

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
</html>
