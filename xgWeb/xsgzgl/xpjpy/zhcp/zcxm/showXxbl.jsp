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
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox {overflow:scroll}
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		
	</head>

	<body>
	
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" >
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1、<font color="#9BDF70">绿色</font>表示为二级综测项目;<br/>
					2、<font color="#FF0000">红色</font>文本框表示详细比例有调整;<br/>
					3、不可修改的比例，表示已有班级提交综测分。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
	
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
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>详细比例列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
