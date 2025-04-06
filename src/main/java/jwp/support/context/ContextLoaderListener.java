package jwp.support.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.logging.Logger;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ContextLoaderListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(new ClassPathResource("jwp.sql"));
//        ConnectionManager.getDataSource();
//        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());

        logger.info("Completed Load ServletContext!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}