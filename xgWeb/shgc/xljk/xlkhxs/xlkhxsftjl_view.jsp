<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript">
	function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
	</script>
	</head>
	<body onload="jd()">
		<html:form action="/xljk_xlkhxsftjl" method="post">
		<input type="hidden" id="add_flag" name="add_flag" value="no" />	
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>�鿴��������ѧ����̸��¼</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
				         <button   onclick="Close();return false;" style="width:80px">
							�� ��
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
				<tbody>
					<tr style="height:22px">
							<th align="right" colspan="2" nowrap="nowrap">
								<font color="red">*</font>����ѧ��ѧ��
							</th>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
							</td>
							
						<th align="right">
							����
						</th>
						<td align="left">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" colspan="2" readonly="true" >
							�Ա�
						</th>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<th align="right" >
							<bean:message key="lable.xb" />
						</th>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" colspan="2">
							�༶
						</th>
						<td align="left">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
						<th align="right" nowrap="nowrap">
							<font color="red">*</font>��̸����
						</th>
						<td align="left">
							<html:text style="width:85px;" property="ftsj" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th align="right" colspan="2">
							<font color="red">*</font>��̸�ص�
						</th>
						<td align="left">
							<html:text  property="ftdd" readonly="true" />
						</td>
						<th align="right">
						    <font color="red">*</font> ��̸��
						</th>
						<td align="left" colspan="4">
							<html:text  property="ftr"  readonly="true"/>
						</td>
					</tr>	
					<tr>
						<th align="right" colspan="2">
							�ֻ�����
						</th>
						<td align="left">
							<html:text  property="sjhm" readonly="true" />
						</td>
						<td align="right">
						</td>
						<td align="left" colspan="4">
						</td>
					</tr>
				  <tr>
				  		<th align="right" colspan="2"> <font color="red">*</font>��̸��¼</th>
                    	<td  align="left" colspan="6" style="word-break:break-all;"><html:textarea rows="20"  style="width:98%" property="ftjl" styleId="a" readonly="true"/></td>
				  </tr>		
				   <tr>
				  		<th align="right" colspan="2"> ��ע </th>
                    	<td  align="left" colspan="6" style="word-break:break-all;"><html:textarea rows="3"  style="width:98%" property="bz" styleId="a" readonly="true"/></td>
				  </tr>
				</tbody>
				</table>
				</div>
		</html:form>
	</body>
</html>