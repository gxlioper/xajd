<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">

			function check(ids){
				var id=ids.split("-");
				for(var i=0;i<id.length;i++){
					var lddm=jQuery("#"+id[i]).val();
					if(lddm==null||""==lddm){
						//alert(id[i]);
						return false;
					}
				}
				return true;
			}
			
			function save(){

				var checkId = 'ly';	
				if(!check(checkId)){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
				var parameter={}
				var url="jxgl_jxxxwh.do?method=jxxx";
				parameter["jxid"]=escape(jQuery("#jxid").val());
				parameter["xh"]=escape(jQuery("#xh").val());
				parameter["ly"]=escape(jQuery("#ly").val());
				parameter["fj"]=escape(jQuery("#fj").val());
				parameter["type"]='save';
				parameter["cxqk"]=escape(jQuery("#cxz").val());
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						alertInfo(result,function(tag){
				 			if(tag=="ok"){
								if (parent.window) {
									refershParent();
								}
				 				return false;
				 			}
				 		});
						//searchRs();
				 		return false;
					}
				);			
			}
				
		</script>
	</head>
	<body>
		<html:form method="post" styleId="jxglJxxxwhForm" action="/jxgl_jxxxwh"  enctype="multipart/form-data">
		 <html:hidden property="jxid" styleId="jxid"/>
		 <html:hidden property="xh" styleId="xh"/>
		 <html:hidden property="cxz" styleId="cxz"/>
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr >
				<td colspan="4">
				<table width="100%" class="formlist" >
	            <tr >
				<th style="text-align:center;">��ѵ����</th>
				<th style="text-align:center;">��ѵ��ʼʱ��</th>
				<th style="text-align:center;">��ѵ����ʱ��</th>
				</tr>
				<tr>
				<td align="center">${jxxxMap.jxmc}</td>
				<td align="center">${jxxxMap.kssj}</td>
				<td align="center">${jxxxMap.jssj}</td>
				</tr>
				</table>
				</td>
				</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>��ϸ��Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="cxz" value="hx">
								<span class="red">*</span>��ѵ����&nbsp;
							</logic:equal>
							<logic:equal name="cxz" value="mx">
								<span class="red">*</span>��ѵ����&nbsp;
							</logic:equal>
							<logic:equal name="cxz" value="sc">
								<span class="red">*</span>ȡ������&nbsp;
							</logic:equal>
							<br />
							<font color="red">(��200��)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="ly" styleId="ly" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							������Ϣ
						</th>
						<td colspan="3">
							<html:hidden property="fj" styleId="fj" />
							<input type="file" id="filepath_f" name="fj" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery('#filepath_f').multiUploader({
										maxcount : 3,
										//��׺
										accept : 'png|gif|jpg|zip|rar|doc|docx',
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'fj',

										eid : 'filepath_f'
									});
								});
							</script>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
