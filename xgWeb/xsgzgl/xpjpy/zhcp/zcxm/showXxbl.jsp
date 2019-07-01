<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcxm/js/xxbl.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				initXxblTable();
				setThead();
			});
		</script>
		
		<style>
			/*���ղ���Ŀ����ʱ����ҳ�б����ʾ��ȫ�������б��������ʾ*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>
	
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" >
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1��<font color="#9BDF70">��ɫ</font>��ʾΪ�����۲���Ŀ;<br/>
					2��<font color="#FF0000">��ɫ</font>�ı����ʾ��ϸ�����е���;<br/>
					3�������޸ĵı�������ʾ���а༶�ύ�۲�֡�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
	
		<input type="hidden" value="${csszModel.zcxmjb }" id="zcxmjb" />
	
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }" name="zcxm" xmmc="${z.xmmc }" xmbm="${z.name }" type="ejxm" qzbl="${z.qzbl }"></font>
				<logic:present property="sjxmList" name="z">
					<logic:iterate property="sjxmList" name="z" id="s">
						<font style="display:none;" xmdm="${s.xmdm }" name="zcxm" xmmc="${s.xmmc }" xmbm="${s.name }" qzbl="${s.qzbl }"></font>
					</logic:iterate>
				</logic:present>
			</logic:iterate>
		</logic:present>
		
		
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ϸ�����б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
