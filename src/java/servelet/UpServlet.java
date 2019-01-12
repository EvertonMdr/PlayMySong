
package servelet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig(
    location="/", 
    fileSizeThreshold=1024*1024,    // 1 MB
    maxFileSize=1024*1024*100,       // 100 MB 
    maxRequestSize=1024*1024*100    // 100 MB
)

@WebServlet(name = "UpServlet", urlPatterns = {"/UpServlet"})
public class UpServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
              String estilo= request.getParameter("estilo");
              String n_cantor = request.getParameter("cantor");
              String n_music = request.getParameter("nome");
            
              Part filePart = request.getPart("file");  // Lê o arquivo de upload
               
              n_cantor=n_cantor.replace(" ", "");
              n_cantor=n_cantor.replace("_", "");
              
              estilo=estilo.replace(" ", "");
              
              n_music=n_music.replace(" ", "");
              n_music =n_music .replace("_", "");
               
             
              String fileName = filePart.getSubmittedFileName();
              String novo_nome=n_music+"_"+estilo+"_"+n_cantor+".mp3";
           
              OutputStream outps = null;
              InputStream filecontent = null;
            try {  //criando a pasta
                //File fpasta = new File(getServletContext().getRealPath("/") + "/" + pasta);
                //fpasta.mkdir();
                outps = new FileOutputStream(new File(
                        getServletContext().getRealPath("/") + "/Musicas/"+novo_nome));
                filecontent = filePart.getInputStream();
                int read;
                byte[] bytes = new byte[1024];
                while ((read = filecontent.read(bytes)) != -1) // recebendo o arquivo 
                {
                    outps.write(bytes, 0, read);
                }
               out.println("<!DOCTYPE html>");
               out.println("<html>");
            
               out.println("<script type=\"text/javascript\">\n"+
              "var timer=setInterval(alerta,200);\n" +"</script>");
               out.println("<head>");
               out.println("<title>Servlet NewServlet</title>");            
               out.println("</head>");
               out.println("<body>");
               out.println("<h1>Musica Enviada com Sucesso!</h1>");
               out.println("</body>");
               out.println("</html>");
              
                outps.close();
                filecontent.close();
                response.sendRedirect("index.html");
                out.close();
                

            } catch (Exception fne) {
                out.println("Erro ao receber o arquivo" + fne.getMessage());
            }

            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
