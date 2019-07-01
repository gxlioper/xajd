<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
		    <p style="height:150px"></p>
		    <p><span style="font-size:26px;font-family:黑体">香港福田实业奖学金</span></p>
		    <p style="height:50px"></p>
            <p><span style="font-size:22px;font-family:楷体_GB2312">评  审  表</span></p>  
		    <p style="height:250px"></p>
           <table>
		    <tr>
		    <td>
            <p><span style="font-size:22px;font-family:楷体_GB2312">学   校：</span>
            </td>
            <td style="border-bottom:1px solid #070707"  align="center">
            <span style="font-size:22px;font-family:楷体_GB2312">
	               ${xxmc}
            </span>
            </td>
            </tr>
            <tr>
	        <td ><p><span style="font-size:22px;font-family:楷体_GB2312">院   系：</span></td>
	        <td  style="border-bottom:1px solid #070707" align="center"><span style="font-size:22px;font-family:楷体_GB2312">
	        ${rs.xymc}
	        </span></td>
	        </tr>
	        <tr>
	        <td ><p><span style="font-size:22px;font-family:楷体_GB2312">班   级：</span></td>
	        <td  style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:楷体_GB2312">
	         ${rs.bjmc}
	        </span>
	        </td>
	        </tr>
	        <tr>
	        <td>
	        <p><span style="font-size:22px;font-family:楷体_GB2312">姓   名：</span></td>
	         <td style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:楷体_GB2312;">
	        ${rs.xm}
	        </span></td>
	        </tr>
	        </table>
            <p style="height:200px"></p>
            <p><span style="font-size:18px;font-family:楷体_GB2312">香港福田实业奖学金管理委员会</span></p>
            <p style="height:20px"></p>
            <p><span style="font-size:18px;font-family:楷体_GB2312">   年  &nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp;   日</span></p>
            <p style="height:100px"></p>
		</center>
  <table class="printtab">
    <tr > 
      <td width=58  align=center >
         <p align="center">
			姓 名
		 </p>
      </td> 
      <td width=88  align=center >
         <p align="center">
			${rs.xm }
		 </p>
      </td> 
      <td width=65  align=center >
         <p align="center">
			性 别
		 </p>
      </td> 
      <td width=59  align=center >
        <p align="center">
			${rs.xb }
		</p>
      </td> 
      <td width=65  align=center >
        <p align="center">
                                 民族
		</p>
      </td> 
      <td width=68  align=center >
        <p align="center">
			${rs.mzmc }
		</p>
      </td> 
      <td width=60  align=center >
        <p align="center">
			出生<br/>年月
		</p>
      </td> 
      <td width=83  align=center >
        <p align="center">
		    ${rs.csrq }
		</p>
      </td> 
      <td width=106  align=center  rowspan=4 >
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr > 
      <td width=58  align=center  rowspan=2 >
         <p align="center">
			  政治<br/>面貌
		 </p>
      </td> 
      <td width=88  align=center  rowspan=2 >
         <p align="center">
			${rs.zzmmmc }
		 </p>
      
      </td> 
      <td width=65  align=center  rowspan=2 >
         <p align="center">
		          担任<br/>职务
	     </p>
      </td> 
      <td width=59  align=center  rowspan=2 >&nbsp;</td> 
      <td width=65  align=center  rowspan=2 >
         <p align="center">
			生源<br/>省市
		 </p>
      </td> 
      <td width=68  align=center  rowspan=2 >
         <p align="center">
			${rs.syd }
		 </p>
      </td> 
      <td width=60  align=center >
                                    农村
      </td> 
      <td width=83  align=center >
            &nbsp;
      </td> 
    </tr> 
    <tr > 
      <td width=60  align=center >
                                     城镇
      </td> 
      <td width=83  align=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td   align=center  colspan=3 >
                                   德育规程操行评定等级
      </td> 
      <td width=65  align=center >&nbsp;</td> 
      <td width=125  align=center  colspan=2 >
                                     专业<br/>名次
      </td> 
      <td width=128  align=center  colspan=2 >&nbsp;</td> 
      
    </tr> 
    <tr>
    <td align="center" colspan="3">
                      外语、计算机过级情况
    </td>
    <td align="center"></td>
    <td  align=center colspan="2">特长</td> 
      <td width=106  align=center colspan="2">&nbsp;</td> 
    </tr>
    <tr > 
      <td width=58  align=center >
                 大<br/>
                 学<br/>
                 期<br/>
                 间<br/>
                 获<br/>
                 奖<br/>
                 情<br/>
                 况<br/>
       </td> 
      <td width=597  align=center  colspan=8 ><p style="height:200px"></p></td> 
    </tr> 
    <tr > 
      <td width=655    colspan=9 >
       <p  style="height:10px"></p>
       <p style="height:10px" align="center"><span style="font-size:12px;font-family:宋体;font-weight:bold;">东莞福安纺织印染有限公司简介</span></p>
       <p  style="height:20px"></p>
       <p align="left">
       <span style="font-family:&#23435;&#20307;" >
       <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;	福田集团实力雄厚，在全球针织布的生产和销售中享有极高声誉。集团创办于1969年，于1988年在香港联合交易所上市，现拥有员工14800人，2002年销售额约49亿港元。集团主要业务为制造及销售针织成品布，包括针织、染色、印花及布匹整理服务及销售色纱、缝纫线和成衣等。集团总部设在香港。</p>
       <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;东莞福安纺织印染有限公司是福田集团的全资子公司及属下最大的生产基地，公司成立于1988年，位于广东省东莞市，员工6000多人。公司全套引进国外先进技术和设备，年产针织坯布16000万磅，色纱4000万磅，染色布14000万磅。公司先后取得了英美等国家检测机构及香港公正行颁发的产品测试许可证及ISO9000认证。 </p>
      </span>
      </p>
      </td> 
    </tr> 
    <tr > 
      <td width=655  align=top  colspan=9 >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:宋体;">本&nbsp;&nbsp;&nbsp;&nbsp;人&nbsp;&nbsp;&nbsp;&nbsp;申&nbsp;&nbsp;&nbsp;&nbsp;请</span></p>
          <p  style="height:20px"></p>
          <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p style="height:320px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:40px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=688  valign=top >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:宋体;"></span></p>
          <p  style="height:20px"></p>
          <p style="height:900px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:30px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=55  align=center >
                      班<br/>
                      级<br/>
                      辅<br/>
                      导<br/>
                      员<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:150px">&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align="right">负责教师签名:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
     <tr > 
      <td width=55  align=center >
                      院<br/>
                      审<br/>
                      核<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:150px">&nbsp;&nbsp;&nbsp;&nbsp;	该同学以上所填情况均属实，根据其各方面表现，经<bean:message key="lable.xb" />奖学金评审委员会审核，符合评奖条件，同意该生获得香港福田实业奖学金。</p>
          <p align="left">
           &nbsp;&nbsp;&nbsp;&nbsp;
                             签名:
           &nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;
           &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                           公   章
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >
                    香<br/>
                     港<br/>
                      福<br/>田<br/>实<br/>业<br/>奖<br/>学<br/>
                      学<br/>
                      金<br/>
                      评<br/>
                      审<br/>
                      委<br/>
                      员<br/>
                      会<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:300px">&nbsp;&nbsp;&nbsp;&nbsp;根据该同学各方面表现，经香港福田实业奖学金评审委员会审核，符合评奖条件，同意该生获得香港福田实业奖学金。</p>
          <p align="left">
           &nbsp;&nbsp;&nbsp;&nbsp;      
                              评审组长签名：                                   
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                           公   章
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >备注</td> 
      <td width=600  align=center >
            <p align="left">1.本表一式两份，表中填写内容要求电脑打印；</p>
            <p align="left">2.学校、院、系、班级不可缩写。</p>
      </td> 
    </tr> 
  </table> 
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
