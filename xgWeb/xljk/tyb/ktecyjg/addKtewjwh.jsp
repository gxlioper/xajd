<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
</head>
<body onload="checkWinType();">
	<html:form action="/xljkXlcyjg" method="post">
       		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ���������ά�� - ���ض������ʾ�ά��</a>
				</p>
			</div>
       		
    		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��������</span></th>
			        </tr>
			    </thead>
    		
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-csrq" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-csrq" />
			<input type="hidden" id="url" name="url" value="/xljk_tyb_addKtewjwh.do" />
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button name="�ύ"id="btn_save"  onclick="saveinfo('xljk_tyb_addKtewjwh.do?act=save','xh-xm-cssj');">�� ��</button>
			          </logic:notEqual>
			          &nbsp;
			            <button name="�ر�" id="btn_close" onclick="Close();return false;">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
			
			<tbody>
				<tr style="width:22px">
					<th align="right">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
					<html:text name='rs' property="xh" styleId="xh" maxlength="20"
						onkeypress="autoFillStuInfo(event.keyCode,this);checkXhExists('xm-xb-csrq-nj-xymc-zymc-bjmc')" />
					<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						 id="buttonFindStu" class="btn_01">
						ѡ��
					</button>
				</td>
					<th align="right">
						<font color="red">*</font>����ʱ��
					</th>
					<td align="left">
						<html:text property="cssj" styleId="cssj" readonly="true" onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cssj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							<font color="red">*</font>����
						</th>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<th align="right">
						A���ӷ�ֵ
					</th>
					<td align="left">
						<html:text property="ayz" styleId="ayz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
					</td>
					
				</tr>
				<tr style="width:22px">
					<th align="right">
							�Ա�
						</th>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<th align="right">
						B���ӷ�ֵ
					</th>
					<td align="left">
						<html:text property="byz" styleId="byz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							��������
						</th>
						<td align="left">
							<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
						</td>
					<th align="right">
						C���ӷ�ֵ
					</th>
					<td align="left">
						<html:text property="cyz" styleId="cyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							�꼶
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<th align="right">
						D���ӷ�ֵ
					</th>
					<td align="left">
						<html:text property="dyz" styleId="dyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<th align="right">
						E���ӷ�ֵ
					</th>
					<td align="left">
						<html:text property="eyz" styleId="eyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							רҵ
						</th>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<th align="right">
						F���ӷ�ֵ
					</th>
					<td align="left">
						<html:text property="fyz" styleId="fyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
					</td>
				</tr>
				<tr>
						<th align="right">
							�༶
						</th>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<th align="right">
							G���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="gyz" styleId="gyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							H���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="hyz" styleId="hyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							I���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="iyz" styleId="iyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							L���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="lyz" styleId="lyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							M���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="myz" styleId="myz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							N���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="nyz" styleId="nyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							O���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="oyz" styleId="oyz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							Q1���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="q1yz" styleId="q1yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							Q2���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="q2yz" styleId="q2yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							Q3���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="q3yz" styleId="q3yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							Q4���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="q4yz" styleId="q4yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							X1���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="x1yz" styleId="x1yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							X2���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="x2yz" styleId="x2yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							X3���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="x3yz" styleId="x3yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							X4���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="x4yz" styleId="x4yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							Y1���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="y1yz" styleId="y1yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							Y2���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="y2yz" styleId="y2yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
				<tr>
						<th align="right">
							Y3���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="y3yz" styleId="y3yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
						<th align="right">
							Y4���ӷ�ֵ
						</th>
						<td align="left">
							<html:text property="y4yz" styleId="y4yz" onkeyup="ckinpdata(this)" style="width:90px" maxlength="5"></html:text>
						</td>
				</tr>
			</table>
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>