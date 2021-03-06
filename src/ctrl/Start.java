package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.ReviewBean;
import model.StoreModel;

@WebServlet(urlPatterns = { "/Store", "/Store/*" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Start() {
		super();
	}

	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			StoreModel storeModel = new StoreModel();
			String pathInfo = request.getPathInfo();

			if (pathInfo != null && pathInfo.equalsIgnoreCase("/book")) {

				if (request.getParameter("addReview") != null) {
					storeModel.addReview(request.getParameter("name"), request.getParameter("review"),
							request.getParameter("bid"));
				}
				BookBean book = storeModel.getBookDetails(request.getParameter("bid"));
				request.setAttribute("book", book);
				List<ReviewBean> reviews = storeModel.getReviewList(request.getParameter("bid"));
				if(reviews != null)
					request.setAttribute("reviews", reviews);
				request.getRequestDispatcher("/Book.jspx").forward(request, response);
			} else {
				List<BookBean> books = storeModel.retrieveBooks(request.getParameter("category"),
						request.getParameter("search"));
				List<String> categories = storeModel.getCategoryList();
				request.setAttribute("books", books);
				request.setAttribute("categories", categories);
				request.getRequestDispatcher("/MainPage.jspx").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
