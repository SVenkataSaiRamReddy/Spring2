package services;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.Session;

import beans.EProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdderServlet")

public class AdderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		long price=Integer.parseInt(request.getParameter("price"));
		String name=request.getParameter("name");
		EProduct ep=new EProduct();
		ep.setName(name);
		ep.setPrice(price);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = (Session) sessionFactory.openSession();
		((SharedSessionContract) session).beginTransaction();
		@SuppressWarnings("deprecation")
		Long i=(Long)((org.hibernate.Session) session).save(ep);
		
		((SharedSessionContract) session).getTransaction().commit();

		((PrintWriter) session).close();
		if (i > 0)
			out.println("Record inserted");
		else
			out.println("Record not inserted");
	
	}


} 


