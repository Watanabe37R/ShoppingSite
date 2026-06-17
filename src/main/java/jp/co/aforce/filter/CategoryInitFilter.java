package jp.co.aforce.filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import jp.co.aforce.bean.Categorys;
import jp.co.aforce.dao.CategoryDAO;

/**
 * Servlet Filter implementation class CategoryInitFilter
 */
@WebFilter("*.action")
public class CategoryInitFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//アプリケーションスコープにカテゴリーリストを入れる
		ServletContext app = request.getServletContext();

		// 既にあるか確認
		if (app.getAttribute("categoryList") == null) {
			try {
				CategoryDAO dao = new CategoryDAO();
				List<Categorys> list = dao.findAll();
				app.setAttribute("categoryList", list);
			} catch (Exception e) {
				//DBエラー
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
