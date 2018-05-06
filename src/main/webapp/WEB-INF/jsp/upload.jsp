<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Budgeto</title>
<link href="../resources/style.css" rel="stylesheet" />
<body>
	<div class="content">
		<div class="uploadForm">
			<form method="POST" action="uploadPatch" enctype="multipart/form-data">
				<input class="inputLong" type="text" name="patchName" placeholder="Name of the patch"></br>
				<input class="inputLong" type="file" name="file" placeholder="Patch to upload"><br/>
				<input class="submitLong" type="submit" value="Upload">
			</form>
		</div>
		
		<div class="uploadHistory" style="margin-top: 20px;">
			<table style="color: #fff;width: 100%;">
				<c:forEach items="${patchHistory.getHistory()}" var="patch">    
					<tr>
			        	<td style="border-bottom: 1px solid #fff;">${patch.getID()}</td>
			        	<td style="border-bottom: 1px solid #fff;">${patch.getName()}</td>
			        	<td style="border-bottom: 1px solid #fff;">
			        		${patch.getStatus()} 
			        		<a href="applyPatch/${patch.getID()}"><img src="resources/images/applyIcon.png"></a>
			        		<a href="rollbackPatch/${patch.getID()}"><img src="resources/images/rollbackIcon.png"></a>
			        	</td>
			        	<td style="border-bottom: 1px solid #fff;">${patch.getDate()}</td>
		        	</tr>
		        </c:forEach>
	        </table>
		</div>
	</div>
	</body>
</html> 