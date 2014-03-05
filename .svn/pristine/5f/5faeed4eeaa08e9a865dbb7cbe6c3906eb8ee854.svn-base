<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@include file="/WEB-INF/jsp/includes.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>管理系统</title>
		<script language="javascript" type="text/javascript">
			function validate(formData, jqForm, options) {     
				for (var i=0; i < formData.length; i++) {         
					if (!formData[i].value) {
						alert(formData[i].name=='password');
						alert('Please enter a value for both Username and Password');             
						return false;         
					}     
				}     
				alert('Both fields contain values.'); 
			}
			$(document).ready(function() {     // bind form using ajaxForm 
				$("#myform").validate();   
				//$('#myform').ajaxForm( { beforeSubmit: validate } ); 
			});
		</script>
	</head>
	<body>

		<h1>
			<bean:message key="drp.appname" />
		</h1>
		<hr>
		<form id="myform" name="myform" action="user/login.do" method="post">
			<LABEL>
				<bean:message key="drp.username" />
				:
				<input type="text" name="username" class="required">
			</LABEL>
			<br>
			<label>
				<bean:message key="drp.password" />
				:
				<input type="password" name="password">
			</label>
			<br>
			<input type="submit" value='<bean:message key="drp.login"/>'>
		</form>
		<p>
			<a href="basedata/changelan.do?lan=zh"><bean:message
					key="drp.lan.zh" /> </a>&nbsp;&nbsp;
			<a href="basedata/changelan.do?lan=en"><bean:message
					key="drp.lan.en" /> </a>
			<br>
		</p>
		<p>
			<a href="basedata/item.do?command=listDisplaytag">listDisplaytag</a>
		</p>
	</body>
</html>