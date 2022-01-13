package member.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DriverLoadingListener implements ServletContextListener {

    public DriverLoadingListener() {
        
    }

    public void contextInitialized(ServletContextEvent sce) {
    	//context 읽어오기
        ServletContext context = sce.getServletContext();
        String driverClass = context.getInitParameter("driver class");
        try {
        	//드라이버 로딩
			Class.forName(driverClass);
			System.out.println("-------Driver Loading 성공--------");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce) {
       
    }
	
}
