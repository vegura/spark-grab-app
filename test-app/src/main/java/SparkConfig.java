import org.apache.spark.SparkConf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SparkConfig {

    // Constants

    private static final String MASTER_NODE_HOST_KEY = "spark.master.node.host";
    private static final String MASTER_NODE_PORT_KEY = "spark.master.node.port";
    private static final String APPLICATION_NAME_KEY = "spark.app.name";

    // Variables

    private String applicationName;
    private String masterNode;

    // Public

    public SparkConfig() {
        Properties sparkProperties = checkOrDefault(loadConfig());
        setFieldsFromLoadedProperties(sparkProperties);
    }

    public SparkConf getSparkConfig() {
        return new SparkConf()
                .setAppName(applicationName)
                .setMaster(masterNode);
    }

    // Private

    private void setFieldsFromLoadedProperties(Properties properties) {
        this.applicationName = properties.getProperty(APPLICATION_NAME_KEY);
        this.masterNode = String.format("spark://%s:%s",
                properties.getProperty(MASTER_NODE_HOST_KEY),
                properties.getProperty(MASTER_NODE_PORT_KEY));
    }

    private Properties checkOrDefault(Properties properties) {
        if (!properties.contains(MASTER_NODE_HOST_KEY)) {
            properties.setProperty(MASTER_NODE_HOST_KEY, "localhost");
        }

        if (!properties.contains(MASTER_NODE_PORT_KEY)) {
            properties.setProperty(MASTER_NODE_PORT_KEY, "17077");
        }

        return properties;
    }

    private Properties loadConfig() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return properties;
    }
}
