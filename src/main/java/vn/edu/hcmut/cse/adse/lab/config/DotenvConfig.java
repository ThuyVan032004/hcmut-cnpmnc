package vn.edu.hcmut.cse.adse.lab.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DotenvConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .systemProperties()
                .load();

        Map<String, Object> envVars = new HashMap<>();

        String databaseUsername = dotenv.get("DB_USERNAME");
        String databasePassword = dotenv.get("DB_PASSWORD");
        
        String databaseUrl = dotenv.get("DATABASE_URL");

        envVars.put("DATABASE_URL", databaseUrl);
        envVars.put("DB_USERNAME", databaseUsername);
        envVars.put("DB_PASSWORD", databasePassword);

        applicationContext.getEnvironment().getPropertySources()
                .addFirst(new MapPropertySource("dotenvProperties", envVars));
    }
}