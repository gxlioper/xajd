<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
			<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>	
	<body>
		<html:form action="/xljk_xlkhxs" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ��������ѧ������ - ��������ѧ����Ϣ - ��Ϣ�鿴</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>��Ϣ�鿴</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button name="�ر�"  onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
					<tr style="height:22px" name="aa" id="a1">
							<th align="right" colspan="2">
								ѧ ��
							</th>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
							</td>	
						<th align="right">
							�� ��
						</th>
						<td align="left" colspan="2">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr >
					<tr style="height:22px" name="aa" id="a2">
						<th align="right" colspan="2" readonly="true" >
							�� ��
						</th>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<th align="right">
							�� ��
						</th>
						<td align="left" colspan="2">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px" name="aa" id="a3">
						<th align="right" colspan="2">
							ѧ Ժ
						</th>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true"/>
						</td>
						<th align="right" >
							�ص�۲����
						</th>
						<td align="left" colspan="2">
							<html:text  property="dmmc" styleId="dmmc"  readonly="true"/>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
