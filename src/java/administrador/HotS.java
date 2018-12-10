package administrador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class HotS extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xml=request.getRealPath("WEB-INF\\practica.xml");
        String id="";
        String texto="";
        String pond="";
        String imagen="";
        String[][] P= new String[2][3];
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

    if (isMultipart) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
         try {
        List items = upload.parseRequest(request);
        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
            FileItem item = (FileItem) iterator.next();
            if (item.isFormField()){
                String name = item.getFieldName();
                System.out.println(name);
                if(name.equals("id")){
                     id=item.getString();
                    System.out.println(id);
                }
                else if(name.equals("texto")){
                     texto=item.getString();
                    System.out.println(texto);
                }
                else if(name.equals("pond")){
                     pond=item.getString();
                    System.out.println(pond);
                }
                //String value = item.getString();
                //System.out.println(value);
            }
            else {
                String fileName = item.getName();
                //System.out.println("");
                String root = getServletContext().getRealPath("/");
                File path = new File(root + "/uploads");
                if (!path.exists()) {
                    boolean status = path.mkdirs();
                }

                File uploadedFile = new File(path + "/" + fileName);
                imagen="/uploads/" + fileName;
                System.out.println(uploadedFile.getAbsolutePath());
                item.write(uploadedFile);
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        
        
        try (PrintWriter out = response.getWriter()) {
///////////////////////////////////////////////////////////////////////////////////////////
// ------------  HEADER  ------------------------------------------------------------------
///////////////////////////////////////////////////////////////////////////////////////////
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet Adm</title>"); 
out.println("<meta charset=\"utf-8\">\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"<script src=\"js/vendor/modernizr-3.6.0.min.js\"></script>\n" +
"<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\" integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>\n" +
"<script src=\"js/plugins.js\"></script>\n" +
"<script src=\"js/main.js\"></script>\n" +
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
"<script src=\"https://cdn.jsdelivr.net/npm/vue/dist/vue.js\"></script>\n" +
"<link rel=\"stylesheet\" href=\"css/normalize.css\">\n" +
"<link rel=\"stylesheet\" href=\"css/main.css\">\n" +
"<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n" +
"</head>\n" +
"<body>\n" +
"<div class=\"contenedor_barra\">\n"+
"<h1>Sistema evaluador</h1>\n"+
"</div>");



///////////////////////////////////////////////////////////////////////////////////////////
// ------------  CONTENIDO  ---------------------------------------------------------------
///////////////////////////////////////////////////////////////////////////////////////////
                out.println(
                "<div class=\"contenedor bg_amarillo sombra\">\n"+
                    "<div class=\"contenedor_alertas\">\n"+
                    "<legend>Holi </legend>");
                            out.println("<h1>"+id+"</h1>");          //        out.println("");
                            out.println("<h1>"+texto+"</h1>");
                            out.println("<h1>"+pond+"</h1>");
                            out.println("<h1>"+imagen+"</h1>");
                
                out.println("</div>\n"+
                "</div>"
                );



        ////////////////////////////////////////////////////////////////////////////////////////////
        // ------------  FOOTER  -------------------------------------------------------------------
        ///////////////////////////////////////////////////////////////////////////////////////////               
        out.println("</body>");
        out.println("</html>");  
        }
    }
    }
}