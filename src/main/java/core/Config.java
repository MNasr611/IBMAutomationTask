package core;

import java.util.Properties;

import static helpers.PropertiesLoader.readPropertyFile;

public class Config {

    Properties config;
    Properties data ;

    private static String env;
    private static String url;
    private static String browser;

    private static String username;
    private static String pwd;


    public Config() {
        config = readPropertyFile("config/config.properties");
        // if u want to run on test then hash the remote and pre and unhash the test and pass coreect values
        setConfig(config);
//        test Data is based on the env so if uwant to read test env data then put ENV : test
        setDataPreparation(env);
    }


    public void setConfig(Properties prop){
        env = System.getProperty("ENV", config.getProperty("ENV"));
        url = System.getProperty("URL", config.getProperty("URL"));
        browser = System.getProperty("BROWSER", config.getProperty("BROWSER"));
    }
    public void setDataPreparation(String env){
        if (env.equalsIgnoreCase("test")) {
            data = readPropertyFile("config/test_Data.properties");
        } else if (env.equalsIgnoreCase("preprod")) {
            data = readPropertyFile("config/preprod_Data.properties");
        }
        username = data.getProperty("USERNAME", config.getProperty("USERNAME"));
        pwd = data.getProperty("PWD", config.getProperty("PWD"));
    }

    public String getEnv() {
        return env;
    }

    public String getUrl() {
        return url;
    }


    public String getBrowser() {
        return browser;
    }


    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

}
