<%-- 
    Document   : consultarMusica
    Created on : 09/04/2017, 22:20:38
    Author     : Lenovo
--%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    String chave = "";
    File diretorio = null;
    File[] arquivos = null;
%>




<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <script type="text/javascript">function menuMusica(){ location.href = "index.html";}
           
        </script>

    </head>



    <body>
        <div class="container">
            <h2>Pesquisar Música</h2>
            <form action="consultarMusica.jsp">

                <div class="form-group">
                    <label for="nome">Palavra-chave:</label>
                    <input type="text" class="form-control" id="nome" name="nome" placeholder="Enter nome musica">
                </div>

                <button type="submit" class="btn btn-default" onclick="" >Pesquisar</button>
                <button type="button" class="btn btn-default" onclick="menuMusica()" >Voltar</button>
            </form>
        </div>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Tocar</th>

                </tr>
            </thead>
            <tbody>


                <%
                    try
                    {
                        chave = request.getParameter("nome");
                    } catch (Exception e)
                    {
                        System.out.println("nao deu certo");
                    }

                    if (chave != null)
                    {
                        diretorio = new File(getServletContext().getRealPath("/")+"/musicas");
                        arquivos = diretorio.listFiles();

                        if (chave.isEmpty())
                        {

                            for (int i = 0; i < arquivos.length; i++)
                            {

                                out.println("<tr>");
                                out.println("<td>" + arquivos[i].getName() + "</td>");
                                out.println("<td><audio controls><source src='musicas/"+arquivos[i].getName()+"' type='audio/mpeg'></audio> </td>");
                                out.println("</tr>");

                            }

                        } else
                        {
                            boolean flag;

                            String pal[];
                            int i = 0;
                            while (i < arquivos.length)
                            {

                                pal = arquivos[i].getName().split("_");
                               pal[pal.length-1]=pal[pal.length-1].substring(0,pal[pal.length-1].indexOf("."));
                               
                                flag = false;
                                for (int j = 0; j < pal.length; j++)
                                {

                                    if (chave.toLowerCase().equals(pal[j].toLowerCase()))
                                    {
                                        flag = true;
                                        break;
                                    }

                                }

                                if (flag)
                                {
                                    out.println("<tr>");
                                    out.println("<td>" + arquivos[i].getName() + "</td>");
                                    out.println("<td><audio controls><source src='musicas/"+arquivos[i].getName()+"' type='audio/mpeg'></audio> </td>");
                                    out.println("</tr>");

                                }
                                 i++;
                            }

                        }

                    }


                %>
            </tbody>
        </table>

    </body> 
</html>