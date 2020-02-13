<%@ page language="java"
contentType="text/html; charset=windows-1256"
pageEncoding="windows-1256"
import=" entities.Author,entities.Book,java.util.List"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=windows-1256">
    <title>  Author Details   </title>
</head>
    <%  Author author= (Author) session.getAttribute("author"); %>

<body>

<center>
Name <%=author.getAuthorName() %>
</center>
<h1>
    Books:
</h1>
<table border="1">
    <thead>
    <tr>
        <th>Book Id</th>
        <th>Book Name </th>
        <th>Date Published</th>
        <th>Chapters</th>
    </tr>
    </thead>

   <tbody>
    <%List<Book> bookList= author.getBookList(); %>

        <%for(int i=0;i<bookList.size();i++) { %>
        <tr>
            <td> <%= bookList.get(i).getBookId()%> </td>
            <td> <%= bookList.get(i).getBookName()%> </td>
            <td> <%= bookList.get(i).getDatePublished()%> </td>
            <td> <%= bookList.get(i).getNoChapters()%> </td>
        </tr>
        <% } %>
    </tbody>
</table>


</body>
</html>