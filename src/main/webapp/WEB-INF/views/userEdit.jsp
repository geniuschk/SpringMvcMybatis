<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/users/${userInfo.id}" method="POST">
		${userInfo.id} <br>
		<input type="text" name="age" value="${userInfo.age}" />
		<input type="text" name="email" value="${userInfo.email}" />
		<input type="text" name="name" value="${userInfo.name}" />
		<input type="text" name="phone" value="${userInfo.phone}" /> <br>
		
		<input type="submit" value="submit" />
	</form>
	${userInfo.id} / ${userInfo.name} / ${userInfo.age} /${userInfo.phone} /${userInfo.createDate}
</body>
</html>
